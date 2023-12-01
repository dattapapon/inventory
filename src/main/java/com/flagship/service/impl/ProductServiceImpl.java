package com.flagship.service.impl;

import com.flagship.constant.enums.UOM;
import com.flagship.constant.enums.Warehouse;
import com.flagship.dto.request.*;
import com.flagship.dto.response.*;
import com.flagship.exception.RequestValidationException;
import com.flagship.model.db.*;
import com.flagship.repository.*;
import com.flagship.service.ProductService;
import com.flagship.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ImportDetailsRepository importDetailsRepository;
    private final CountryRepository countryRepository;
    private final ImportMasterRepository importMasterRepository;
    private final CategoriesRepository categoriesRepository;
    private final BrandRepository brandRepository;
    private final StockRepository stockRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository,
                              ImportDetailsRepository importDetailsRepository, CountryRepository countryRepository,
                              ImportMasterRepository importMasterRepository, CategoriesRepository categoriesRepository,
                              BrandRepository brandRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.importDetailsRepository = importDetailsRepository;
        this.countryRepository = countryRepository;
        this.importMasterRepository = importMasterRepository;
        this.categoriesRepository = categoriesRepository;
        this.brandRepository = brandRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Boolean productExist = checkProduct(productRequest.getProductId());
        if (productExist) {
            throw new RequestValidationException("Product Id is exist. Give a valid Product Id");
        }

        Product product = new Product();
        product.setProductId(productRequest.getProductId());
        product.setProductName(productRequest.getProductName());
        Optional<User> user = userRepository.findByEmail(productRequest.getUserEmail());
        if (user.isEmpty()) {
            throw new RequestValidationException("User Email did not exist");
        }
        product.setCreatedBy(user.get());
        productRepository.save(product);
        return ProductResponse.from("Product Added Successful", product);
    }

    @Override
    public AllProductResponse getProduct() {
        List<Product> allProduct = productRepository.findAll();
        List<SingleProductResponse> getProductRespons = new ArrayList<>();
        for (Product product : allProduct) {
            getProductRespons.add(SingleProductResponse.from(product));
        }
        return AllProductResponse.from(getProductRespons);
    }

    @Override
    public ImportResponse addImport(ImportRequest importRequest) {
        importRequest.validate();
        ImportMaster importMaster = new ImportMaster();
        importMaster.setShipmentNo(importRequest.getShipmentNo());
        importMaster.setCountry(getCountry(importRequest.getCountry()));
        importMaster.setDate(DateUtil.getZoneDateTime(importRequest.getDate() + "T00:00:00"));
        importMaster.setCreatedBy(getUser(importRequest.getUser()));
        ImportMaster saveImportMaster = importMasterRepository.save(importMaster);
        List<ImportDetailsResponse> importDetailsResponseList = saveImportDetails(saveImportMaster,
                importRequest.getImportDetailsRequestList());
        return ImportResponse.from("Import Added Successfully", importMaster, importDetailsResponseList);
    }

    @Override
    public AllStockResponse getStock() {
        List<Stock> stockList = (List<Stock>) stockRepository.findAll();
        List<StockResponse> stockResponseList = new ArrayList<>();
        for(Stock stock : stockList){
            stockResponseList.add(StockResponse.from(stock));
        }
        return AllStockResponse.from(stockResponseList);
    }

    private User getUser(String createdBy) {
        Optional<User> optionalUser = userRepository.findByEmail(createdBy);
        if(optionalUser.isEmpty()){
            throw new RequestValidationException("User not exist");
        }else {
            return optionalUser.get();
        }
    }

    private Country getCountry(String country) {
        Optional<Country> optionalCountry = countryRepository.findByCountryId(country);
        if(optionalCountry.isEmpty()){
            throw new RequestValidationException("Country is not exist");
        }else {
            return optionalCountry.get();
        }
    }

    private List<ImportDetailsResponse> saveImportDetails(ImportMaster saveImportMaster,
                                                          List<ImportDetailsRequest> importDetailsRequestList) {
        List<ImportDetailsResponse> importDetailsResponseList = new ArrayList<>();
        for(ImportDetailsRequest importDetailsRequest : importDetailsRequestList){
            importDetailsRequest.validate();
            ImportDetails importDetails = new ImportDetails();
            importDetails.setImportMaster(saveImportMaster);
            importDetails.setProduct(getProducts(importDetailsRequest.getProduct()));
            importDetails.setCategories(getCategories(importDetailsRequest.getCategory()));
            importDetails.setBrand(getBrand(importDetailsRequest.getBrand()));
            importDetails.setCountry(getCountry(importDetailsRequest.getCountry()));
            importDetails.setProduction(DateUtil.getZoneDateTime(importDetailsRequest.getProduction() + "T00:00:00"));
            importDetails.setWarehouse(Warehouse.fromName(importDetailsRequest.getWarehouse().getName()));
            importDetails.setExpire(DateUtil.getZoneDateTime(importDetailsRequest.getExpire() + "T00:00:00"));
            importDetails.setCartoon(importDetailsRequest.getCartoon());
            importDetails.setPiece(importDetailsRequest.getPiece());
            importDetails.setKgLt(importDetailsRequest.getKgLt());
            importDetails.setUom(UOM.fromName(importDetailsRequest.getUom().getName()));
            importDetails.setPrice(importDetailsRequest.getPrice());
            importDetails.setTotal(getTotalPrice(importDetailsRequest));
            importDetails.setCreatedBy(saveImportMaster.getCreatedBy());
            importDetailsRepository.save(importDetails);
            ImportDetailsResponse importDetailsResponse = ImportDetailsResponse.from(importDetails);
            importDetailsResponseList.add(importDetailsResponse);
        }
        return importDetailsResponseList;
    }

    private Double getTotalPrice(ImportDetailsRequest importDetailsRequest) {
        Product product = getProducts(importDetailsRequest.getProduct());
        UOM uom = UOM.fromName(importDetailsRequest.getUom().getName());
        if(Objects.equals(uom, UOM.CARTOON)){
            addStockByCartoon(product, importDetailsRequest);
            return (importDetailsRequest.getCartoon() * importDetailsRequest.getPrice());
        }else if(Objects.equals(uom, UOM.KG_LT)){
            addStockByKgLt(product, importDetailsRequest);
            return (importDetailsRequest.getKgLt() * importDetailsRequest.getPrice());
        }else{
            addStockByPiece(product, importDetailsRequest);
            return (importDetailsRequest.getPiece() * importDetailsRequest.getPrice());
        }
    }

    private void addStockByPiece(Product product, ImportDetailsRequest importDetailsRequest) {
        Optional<Stock> optionalStock = stockRepository.findByProduct(product);
        if(optionalStock.isEmpty()){
            Stock stock = new Stock();
            stock.setProduct(product);
            stock.setUom(UOM.fromName(UOM.PIECE.getName()));
            stock.setTotalBuy(importDetailsRequest.getPiece());
            stock.setTotalSell(0.0);
            stock.setInStock(importDetailsRequest.getPiece());
            stockRepository.save(stock);
        }else {
            optionalStock.get().setProduct(optionalStock.get().getProduct());
            optionalStock.get().setTotalBuy(optionalStock.get().getTotalBuy() + importDetailsRequest.getPiece());
            optionalStock.get().setInStock(optionalStock.get().getInStock() + importDetailsRequest.getPiece());
            stockRepository.save(optionalStock.get());
        }
    }

    private void addStockByKgLt(Product product, ImportDetailsRequest importDetailsRequest) {
        Optional<Stock> optionalStock = stockRepository.findByProduct(product);
        if(optionalStock.isEmpty()){
            Stock stock = new Stock();
            stock.setProduct(product);
            stock.setUom(UOM.fromName(UOM.KG_LT.getName()));
            stock.setTotalBuy(importDetailsRequest.getKgLt());
            stock.setTotalSell(0.0);
            stock.setInStock(importDetailsRequest.getKgLt());
            stockRepository.save(stock);
        }else {
            optionalStock.get().setProduct(optionalStock.get().getProduct());
            optionalStock.get().setTotalBuy(optionalStock.get().getTotalBuy() + importDetailsRequest.getKgLt());
            optionalStock.get().setInStock(optionalStock.get().getInStock() + importDetailsRequest.getKgLt());
            stockRepository.save(optionalStock.get());
        }

    }

    private void addStockByCartoon(Product product, ImportDetailsRequest importDetailsRequest) {
        Optional<Stock> optionalStock = stockRepository.findByProduct(product);
        if(optionalStock.isEmpty()){
            Stock stock = new Stock();
            stock.setProduct(product);
            stock.setUom(UOM.fromName(UOM.CARTOON.getName()));
            stock.setTotalBuy(importDetailsRequest.getCartoon());
            stock.setTotalSell(0.0);
            stock.setInStock(importDetailsRequest.getCartoon());
            stockRepository.save(stock);
        }else {
            optionalStock.get().setProduct(optionalStock.get().getProduct());
            optionalStock.get().setTotalBuy(optionalStock.get().getTotalBuy() + importDetailsRequest.getCartoon());
            optionalStock.get().setInStock(optionalStock.get().getInStock() + importDetailsRequest.getCartoon());
            stockRepository.save(optionalStock.get());
        }
    }

    private Brand getBrand(String brand) {
        Optional<Brand> optionalBrand = brandRepository.findByBrandId(brand);
        if(optionalBrand.isEmpty()){
            throw new RequestValidationException("Brand not exist");
        }else {
            return optionalBrand.get();
        }
    }

    private Categories getCategories(String category) {
        Optional<Categories> optionalCategories = categoriesRepository.findByCategoryId(category);
        if(optionalCategories.isEmpty()){
            throw new RequestValidationException("Category not exist");
        }else{
            return optionalCategories.get();
        }
    }

    private Product getProducts(String product) {
        Optional<Product> optionalProduct = productRepository.findByProductId(product);
        if(optionalProduct.isEmpty()){
            throw new RequestValidationException("Product is not exist");
        }else{
            return optionalProduct.get();
        }
    }

    private Boolean checkProduct(String productId) {
        Optional<Product> product = productRepository.findByProductId(productId);
        return product.isPresent();
    }
}
