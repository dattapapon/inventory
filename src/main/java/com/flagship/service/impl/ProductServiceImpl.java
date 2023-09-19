package com.flagship.service.impl;

import com.flagship.constant.enums.Warehouse;
import com.flagship.dto.request.AddCuttingRequest;
import com.flagship.dto.request.AddImportRequest;
import com.flagship.dto.request.ProductRequest;
import com.flagship.dto.response.*;
import com.flagship.exception.RequestValidationException;
import com.flagship.model.db.Cutting;
import com.flagship.model.db.Import;
import com.flagship.model.db.Product;
import com.flagship.model.db.User;
import com.flagship.repository.ProductRepository;
import com.flagship.repository.UserRepository;
import com.flagship.repository.CuttingRepository;
import com.flagship.repository.ImportRepository;
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
    private final CuttingRepository cuttingRepository;
    private final ImportRepository importRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CuttingRepository cuttingRepository,
                              ImportRepository importRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cuttingRepository = cuttingRepository;
        this.importRepository = importRepository;
    }

    @Override
    public CreateProductResponse createProduct(ProductRequest productRequest) {
        Boolean productExist = checkProduct(productRequest.getProductId());
        if(productExist){
            throw new RequestValidationException("Product Id is exist. Give a valid Product Id");
        }

        Product product = new Product();
        product.setProductId(productRequest.getProductId());
        product.setProductName(productRequest.getProductName());
        Optional<User> user = userRepository.findByEmail(productRequest.getUserEmail());
        if(user.isEmpty()){
            throw new RequestValidationException("User Email did not exist");
        }
        product.setCreatedBy(user.get());
        productRepository.save(product);
        return CreateProductResponse.from("Product Added Successful", product);
    }

    @Override
    public GetAllProductResponse getProduct() {
        List<Product> allProduct = productRepository.findAll();
        List<GetProductResponse> getProductRespons = new ArrayList<>();
        for(Product product : allProduct){
            getProductRespons.add(GetProductResponse.from(product));
        }
        return GetAllProductResponse.from(getProductRespons);
    }

    @Override
    public AddImportResponse addImport(AddImportRequest addImportRequest) {
        List<AddCuttingRequest> cuttings = addImportRequest.getCartons();
        List<GetCuttingResponse> getCuttingResponses = new ArrayList<>();
        Optional<User> user = userRepository.findByEmail(addImportRequest.getUserEmail());
        if(user.isEmpty()){
            throw new RequestValidationException("User Email did not exist");
        }
        Optional<User> receiver = userRepository.findById(addImportRequest.getReceiverName());
        if(receiver.isEmpty()){
            throw new RequestValidationException("User Email did not exist");
        }
        Import imports = new Import();
        imports.setShipmentNo(addImportRequest.getShipmentNo());
        imports.setProductId(addImportRequest.getProductId());
        imports.setProductName(addImportRequest.getProductName());
        imports.setProduction(DateUtil.getZoneDateTime(addImportRequest.getProduction() + "T00:00:00"));
        imports.setCountry(addImportRequest.getCountry());
        imports.setCartoonQuantity(addImportRequest.getCartoonQuantity());
        imports.setCartoonWeight(addImportRequest.getCartoonWeight());
        imports.setCartoonBuyingPrice(addImportRequest.getCartoonBuyingPrice());
        imports.setCategory(addImportRequest.getCategory());
        imports.setPieceWeight(addImportRequest.getPieceWeight());
        imports.setPieceQuantity(addImportRequest.getPieceQuantity());
        imports.setPieceBuyingPrice(addImportRequest.getPieceBuyingPrice());
        imports.setKgLtBuyingPrice(addImportRequest.getKgLtBuyingPrice());
        imports.setKgLtQuantity(imports.getKgLtQuantity());
        imports.setBrand(addImportRequest.getBrand());
        imports.setTax(addImportRequest.getTax());
        imports.setVat(addImportRequest.getVat());
        imports.setExpire(DateUtil.getZoneDateTime(addImportRequest.getExpire() + "T00:00:00"));
        imports.setReceiverName(receiver.get());
        imports.setWarehouse(Warehouse.fromName(addImportRequest.getWarehouse().toString()));
        imports.setCreatedBy(user.get());
        Import saveImport = importRepository.save(imports);
        getCuttingResponses = addCutting(cuttings, saveImport, user.get());
        return AddImportResponse.from("Import Added Succesfully", imports, getCuttingResponses);
    }

    @Override
    public GetAllImportResponse getSingleProduct(String productId) {
        List<Import> importList = importRepository.findAllByProductIdOrderByCreatedOnAsc(productId);
        List<GetImportResponse> importResponseList = new ArrayList<>();
        for(Import imports : importList){
            importResponseList.add(GetImportResponse.from(imports));
        }
        return GetAllImportResponse.from(importResponseList);
    }

    @Override
    public GetAllImportResponse getAllProduct() {
        List<Import> importList = importRepository.findAll();
        List<GetImportResponse> importResponseList = new ArrayList<>();
        for(Import imports : importList){
            importResponseList.add(GetImportResponse.from(imports));
        }
        return GetAllImportResponse.from(importResponseList);
    }

    @Override
    public List<GetCuttingResponse> getCuttingDetails(Long cuttingId) {
        Optional<Import> imports = importRepository.findById(cuttingId);
        List<GetCuttingResponse> cuttingResponses = new ArrayList<>();
        if (imports.isPresent()) {
            List<Cutting> cuttingList = cuttingRepository.findAllByImportIdOrderByCreatedOnDesc(imports.get());
            for (Cutting cutting : cuttingList) {
                cuttingResponses.add(GetCuttingResponse.from(cutting));
            }
        }
        return cuttingResponses;
    }

    private List<GetCuttingResponse> addCutting(List<AddCuttingRequest> cuttings, Import imports, User user) {
        List<GetCuttingResponse> cuttingResponses = new ArrayList<>();
        for(AddCuttingRequest addCuttingRequest : cuttings){
            Cutting cutting = new Cutting();
            cutting.setCartoonNo(addCuttingRequest.getCartonNo());
            cutting.setCartoonWeight(addCuttingRequest.getCartonWeight());
            cutting.setPieceInCarton(addCuttingRequest.getPieceInCarton());
            cutting.setCartoonBuyingPrice(addCuttingRequest.getCartonBuyingPrice());
            cutting.setImportId(imports);
            cutting.setCreatedBy(user);
            System.out.println(cutting.toString());
            cuttingRepository.save(cutting);
            cuttingResponses.add(GetCuttingResponse.from(cutting));
        }
        return cuttingResponses;
    }

    private Boolean checkProduct(String productId) {
        Optional<Product> product = productRepository.findByProductId(productId);
        return product.isPresent();
    }
}
