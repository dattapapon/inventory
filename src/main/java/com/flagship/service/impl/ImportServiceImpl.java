package com.flagship.service.impl;

import com.flagship.constant.enums.UOM;
import com.flagship.constant.enums.Warehouse;
import com.flagship.dto.request.ImportDetailsRequest;
import com.flagship.dto.request.ImportRequest;
import com.flagship.dto.response.*;
import com.flagship.exception.RequestValidationException;
import com.flagship.model.db.*;
import com.flagship.repository.*;
import com.flagship.service.ImportService;
import com.flagship.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ImportServiceImpl implements ImportService {
  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final ImportDetailsRepository importDetailsRepository;
  private final CountryRepository countryRepository;
  private final ImportMasterRepository importMasterRepository;
  private final CategoriesRepository categoriesRepository;
  private final BrandRepository brandRepository;
  private final StockRepository stockRepository;
  private final WastageRepository wastageRepository;

  @Autowired
  public ImportServiceImpl(ProductRepository productRepository, UserRepository userRepository,
                           ImportDetailsRepository importDetailsRepository, CountryRepository countryRepository,
                           ImportMasterRepository importMasterRepository, CategoriesRepository categoriesRepository,
                           BrandRepository brandRepository, StockRepository stockRepository,
                           WastageRepository wastageRepository) {
    this.productRepository = productRepository;
    this.userRepository = userRepository;
    this.importDetailsRepository = importDetailsRepository;
    this.countryRepository = countryRepository;
    this.importMasterRepository = importMasterRepository;
    this.categoriesRepository = categoriesRepository;
    this.brandRepository = brandRepository;
    this.stockRepository = stockRepository;
    this.wastageRepository = wastageRepository;
  }

  @Override
  public ImportResponse addImport(ImportRequest importRequest) {
    importRequest.validate();
    Optional<ImportMaster> optionalImportMaster = importMasterRepository.findByShipmentNo(importRequest.getShipmentNo());
    if (optionalImportMaster.isPresent()) {
      optionalImportMaster.get().setCountry(getCountry(importRequest.getCountry().getValue()));
      optionalImportMaster.get().setDate(DateUtil.getZoneDateTime(importRequest.getDate() + "T00:00:00"));
      List<ImportDetailsResponse> importDetailsResponseList = saveImportDetails(optionalImportMaster.get(),
              importRequest.getImportDetailsRequestList());
      importMasterRepository.save(optionalImportMaster.get());
      return ImportResponse.from("Import Added Successfully", optionalImportMaster.get(), importDetailsResponseList);
    } else {
      ImportMaster importMaster = new ImportMaster();
      importMaster.setShipmentNo(importRequest.getShipmentNo());
      importMaster.setCountry(getCountry(importRequest.getCountry().getValue()));
      importMaster.setDate(DateUtil.getZoneDateTime(importRequest.getDate() + "T00:00:00"));
      importMaster.setCreatedBy(getUser(importRequest.getUser()));
      ImportMaster saveImportMaster = importMasterRepository.save(importMaster);
      List<ImportDetailsResponse> importDetailsResponseList = saveImportDetails(saveImportMaster,
              importRequest.getImportDetailsRequestList());
      return ImportResponse.from("Import Added Successfully", importMaster, importDetailsResponseList);
    }
  }

  @Override
  public ShipmentResponse getShipment(String product) {
    List<ImportDetails> importDetailsList = importDetailsRepository.findByProduct(getProducts(product));
    List<SingleShipment> shipmentList = new ArrayList<>();
    for (ImportDetails importDetails : importDetailsList) {
      shipmentList.add(SingleShipment.from(importDetails));
    }
    return ShipmentResponse.from(shipmentList);
  }

  @Override
  public AllImportResponse getAllImport() {
    List<ImportMaster> importMasterList = (List<ImportMaster>) importMasterRepository.findAll();
    List<SingleImportResponse> importResponseList = new ArrayList<>();
    for (ImportMaster importMaster : importMasterList) {
      List<ImportDetails> importDetailsList = importDetailsRepository.findByImportMaster(importMaster);
      for (ImportDetails importDetails : importDetailsList) {
        importResponseList.add(SingleImportResponse.from(importMaster, importDetails));
      }
    }
    return AllImportResponse.from(importResponseList);
  }

  @Override
  public GetUomAndAvailableResponse getProductUomAndAvailable(String product, String shipment) {
    Optional<ImportDetails> importDetails = importDetailsRepository.findByProductAndImportMaster(
            getProducts(product), getImportMaster(shipment));
    GetUomAndAvailableResponse getUomAndAvailableResponse = null;
    if (importDetails.isPresent()) {
      if (importDetails.get().getUom().equals(UOM.LT)) {
        getUomAndAvailableResponse = GetUomAndAvailableResponse.from(importDetails.get().getUom(), importDetails.get().getKgLt());
      }
      if (importDetails.get().getUom().equals(UOM.KG)) {
        getUomAndAvailableResponse = GetUomAndAvailableResponse.from(importDetails.get().getUom(), importDetails.get().getKgLt());
      }
      if (importDetails.get().getUom().equals(UOM.PIECE)) {
        getUomAndAvailableResponse = GetUomAndAvailableResponse.from(importDetails.get().getUom(), importDetails.get().getPiece());
      }
      if (importDetails.get().getUom().equals(UOM.CARTOON)) {
        getUomAndAvailableResponse = GetUomAndAvailableResponse.from(importDetails.get().getUom(), importDetails.get().getCartoon());
      }
    } else {
      throw new RequestValidationException("Any record is not exist for Product: " + product + " and Shipment: " + shipment);
    }
    return getUomAndAvailableResponse;
  }

  @Override
  public WastageDetailsResponse getWastage(String shipment) {
    List<Wastage> wastageList = wastageRepository.findByShipment(getImportMaster(shipment));
    List<SingleWastageSlipResponse> singleWastageSlipResponses = new ArrayList<>();
    double piece = 0.0;
    double cartoon = 0.0;
    double kgLt = 0.0;
    for (Wastage wastage : wastageList) {
      piece = piece + wastage.getPiece();
      cartoon = cartoon + wastage.getCartoon();
      kgLt = kgLt + wastage.getKgLt();
      singleWastageSlipResponses.add(SingleWastageSlipResponse.from(wastage));
    }
    if (!wastageList.isEmpty()) {
      return WastageDetailsResponse.from(piece, cartoon, kgLt, singleWastageSlipResponses);
    } else {
      return null;
    }
  }

  private ImportMaster getImportMaster(String shipment) {
    Optional<ImportMaster> optionalImportMaster = importMasterRepository.findByShipmentNo(shipment);
    return optionalImportMaster.orElseThrow(() -> new RequestValidationException("ImportMaster not found for shipment: "
            + shipment));
  }

  private User getUser(String createdBy) {
    Optional<User> optionalUser = userRepository.findByEmail(createdBy);
    if (optionalUser.isEmpty()) {
      throw new RequestValidationException("User not exist");
    } else {
      return optionalUser.get();
    }
  }

  private Country getCountry(String country) {
    Optional<Country> optionalCountry = countryRepository.findByCountryId(country);
    if (optionalCountry.isEmpty()) {
      throw new RequestValidationException("Country is not exist");
    } else {
      return optionalCountry.get();
    }
  }

  private List<ImportDetailsResponse> saveImportDetails(ImportMaster saveImportMaster,
                                                        List<ImportDetailsRequest> importDetailsRequestList) {
    List<ImportDetailsResponse> importDetailsResponseList = new ArrayList<>();
    List<ImportDetails> importDetailsList = new ArrayList<>();
    for (ImportDetailsRequest importDetailsRequest : importDetailsRequestList) {
      importDetailsRequest.validate();

      ImportDetails importDetails = new ImportDetails();
      importDetails.setImportMaster(saveImportMaster);
      importDetails.setProduct(getProducts(importDetailsRequest.getProduct().getValue()));
      importDetails.setCategories(getCategories(importDetailsRequest.getCategory().getValue()));
      importDetails.setBrand(getBrand(importDetailsRequest.getBrand().getValue()));
      importDetails.setCountry(getCountry(importDetailsRequest.getImportCountry().getValue()));
      if (importDetailsRequest.getProduction() != null && !importDetailsRequest.getProduction().isEmpty()) {
        importDetails.setProduction(DateUtil.getZoneDateTime(importDetailsRequest.getProduction() + "T00:00:00"));
      }
      importDetails.setWarehouse(Warehouse.fromName(importDetailsRequest.getWarehouse().getName()));
      if (importDetailsRequest.getExpire() != null && !importDetailsRequest.getExpire().isEmpty()) {
        importDetails.setExpire(DateUtil.getZoneDateTime(importDetailsRequest.getExpire() + "T00:00:00"));
      }
      importDetails.setUnitCartoon(importDetailsRequest.getKgLt() / importDetailsRequest.getCartoon());
      importDetails.setCartoon(importDetailsRequest.getCartoon());
      importDetails.setUnitPiece(importDetailsRequest.getKgLt() / importDetailsRequest.getPiece());
      importDetails.setPiece(importDetailsRequest.getPiece());
      importDetails.setKgLt(importDetailsRequest.getKgLt());
      importDetails.setUom(UOM.fromName(importDetailsRequest.getUom().getName()));
      importDetails.setPrice(importDetailsRequest.getPrice());
      importDetails.setTotal(importDetailsRequest.getTotal());
      importDetails.setCreatedBy(saveImportMaster.getCreatedBy());
      importDetailsList.add(importDetails);
      importDetailsResponseList.add(ImportDetailsResponse.from(importDetails));
    }
    importDetailsRepository.saveAll(importDetailsList);
    for (ImportDetails importDetails : importDetailsList) {
      addStock(importDetails);
    }
    return importDetailsResponseList;
  }

  private void addStock(ImportDetails importDetails) {
    Product product = importDetails.getProduct();
    UOM uom = UOM.fromName(importDetails.getUom().getName());
    if (Objects.equals(uom, UOM.CARTOON)) {
      addStockByCartoon(product, importDetails);
    } else if (Objects.equals(uom, UOM.KG)) {
      addStockByKgLt(product, importDetails);
    } else if (Objects.equals(uom, UOM.LT)) {
      addStockByLt(product, importDetails);
    } else {
      addStockByPiece(product, importDetails);
    }
  }

  private void addStockByLt(Product product, ImportDetails importDetails) {
    Optional<Stock> optionalStock = stockRepository.findByProduct(product);
    if (optionalStock.isEmpty()) {
      Stock stock = new Stock();
      stock.setProduct(product);
      stock.setUom(UOM.fromName(UOM.LT.getName()));
      stock.setTotalBuy(importDetails.getKgLt());
      stock.setTotalSell(0.0);
      stock.setInStock(importDetails.getKgLt());
      stockRepository.save(stock);
    } else {
      optionalStock.get().setProduct(optionalStock.get().getProduct());
      optionalStock.get().setTotalBuy(optionalStock.get().getTotalBuy() + importDetails.getKgLt());
      optionalStock.get().setInStock(optionalStock.get().getInStock() + importDetails.getKgLt());
      stockRepository.save(optionalStock.get());
    }
  }

  private void addStockByPiece(Product product, ImportDetails importDetails) {
    Optional<Stock> optionalStock = stockRepository.findByProduct(product);
    if (optionalStock.isEmpty()) {
      Stock stock = new Stock();
      stock.setProduct(product);
      stock.setUom(UOM.fromName(UOM.PIECE.getName()));
      stock.setTotalBuy(importDetails.getPiece());
      stock.setTotalSell(0.0);
      stock.setInStock(importDetails.getPiece());
      stockRepository.save(stock);
    } else {
      optionalStock.get().setProduct(optionalStock.get().getProduct());
      optionalStock.get().setTotalBuy(optionalStock.get().getTotalBuy() + importDetails.getPiece());
      optionalStock.get().setInStock(optionalStock.get().getInStock() + importDetails.getPiece());
      stockRepository.save(optionalStock.get());
    }
  }

  private void addStockByKgLt(Product product, ImportDetails importDetails) {
    Optional<Stock> optionalStock = stockRepository.findByProduct(product);
    if (optionalStock.isEmpty()) {
      Stock stock = new Stock();
      stock.setProduct(product);
      stock.setUom(UOM.fromName(UOM.KG.getName()));
      stock.setTotalBuy(importDetails.getKgLt());
      stock.setTotalSell(0.0);
      stock.setInStock(importDetails.getKgLt());
      stockRepository.save(stock);
    } else {
      optionalStock.get().setProduct(optionalStock.get().getProduct());
      optionalStock.get().setTotalBuy(optionalStock.get().getTotalBuy() + importDetails.getKgLt());
      optionalStock.get().setInStock(optionalStock.get().getInStock() + importDetails.getKgLt());
      stockRepository.save(optionalStock.get());
    }

  }

  private void addStockByCartoon(Product product, ImportDetails importDetails) {
    Optional<Stock> optionalStock = stockRepository.findByProduct(product);
    if (optionalStock.isEmpty()) {
      Stock stock = new Stock();
      stock.setProduct(product);
      stock.setUom(UOM.fromName(UOM.CARTOON.getName()));
      stock.setTotalBuy(importDetails.getCartoon());
      stock.setTotalSell(0.0);
      stock.setInStock(importDetails.getCartoon());
      stockRepository.save(stock);
    } else {
      optionalStock.get().setProduct(optionalStock.get().getProduct());
      optionalStock.get().setTotalBuy(optionalStock.get().getTotalBuy() + importDetails.getCartoon());
      optionalStock.get().setInStock(optionalStock.get().getInStock() + importDetails.getCartoon());
      stockRepository.save(optionalStock.get());
    }
  }

  private Brand getBrand(String brand) {
    Optional<Brand> optionalBrand = brandRepository.findByBrandId(brand);
    if (optionalBrand.isEmpty()) {
      throw new RequestValidationException("Brand not exist");
    } else {
      return optionalBrand.get();
    }
  }

  private Categories getCategories(String category) {
    Optional<Categories> optionalCategories = categoriesRepository.findByCategoryId(category);
    if (optionalCategories.isEmpty()) {
      throw new RequestValidationException("Category not exist");
    } else {
      return optionalCategories.get();
    }
  }

  private Product getProducts(String product) {
    Optional<Product> optionalProduct = productRepository.findByProductId(product);
    if (optionalProduct.isEmpty()) {
      throw new RequestValidationException("Product is not exist");
    } else {
      return optionalProduct.get();
    }
  }
}
