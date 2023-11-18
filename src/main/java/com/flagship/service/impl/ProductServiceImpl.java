package com.flagship.service.impl;

import com.flagship.constant.enums.Warehouse;
import com.flagship.dto.request.AddCuttingRequest;
import com.flagship.dto.request.AddImportRequest;
import com.flagship.dto.request.MoveProductRequest;
import com.flagship.dto.request.ProductRequest;
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
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CuttingRepository cuttingRepository;
    private final ImportRepository importRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CuttingRepository cuttingRepository,
                              ImportRepository importRepository, OrderDetailsRepository orderDetailsRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cuttingRepository = cuttingRepository;
        this.importRepository = importRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public CreateProductResponse createProduct(ProductRequest productRequest) {
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
        return CreateProductResponse.from("Product Added Successful", product);
    }

    @Override
    public GetAllProductResponse getProduct() {
        List<Product> allProduct = productRepository.findAll();
        List<GetProductResponse> getProductRespons = new ArrayList<>();
        for (Product product : allProduct) {
            getProductRespons.add(GetProductResponse.from(product));
        }
        return GetAllProductResponse.from(getProductRespons);
    }

    @Override
    public AddImportResponse addImport(AddImportRequest addImportRequest) {
        List<AddCuttingRequest> cuttings = addImportRequest.getCartons();
        Optional<User> user = userRepository.findByEmail(addImportRequest.getUserEmail());
        if (user.isEmpty()) {
            throw new RequestValidationException("User Email did not exist");
        }
        Optional<User> receiver = userRepository.findById(addImportRequest.getReceiverName());
        if (receiver.isEmpty()) {
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
        if (cuttings != null && !cuttings.isEmpty()) {
            List<GetCuttingResponse> getCuttingResponses = addCutting(cuttings, saveImport, user.get());
            return AddImportResponse.from("Import Added Succesfully", imports, getCuttingResponses);
        } else {
            return AddImportResponse.from("Import Added Succesfully", imports, null);
        }
    }

    @Override
    public GetAllImportResponse getSingleProduct(String productId) {
        List<Import> importList = importRepository.findAllByProductIdOrderByCreatedOnAsc(productId);
        List<GetImportResponse> importResponseList = new ArrayList<>();
        for (Import imports : importList) {
            importResponseList.add(GetImportResponse.from(imports));
        }
        return GetAllImportResponse.from(importResponseList);
    }

    @Override
    public GetAllImportResponse getAllProduct() {
        List<Import> importList;
        importList = importRepository.findAll();
        List<GetImportResponse> importResponseList = new ArrayList<>();
        for (Import imports : importList) {
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

    @Override
    public List<GetAllCuttingResponse> getAllCuttingDetails() {
        List<Cutting> cuttingList = cuttingRepository.findAll();
        List<GetAllCuttingResponse> getAllCuttingResponses = new ArrayList<>();
        for (Cutting cutting : cuttingList) {
            Optional<Import> imports = importRepository.findById(cutting.getImportId().getId());
            GetAllCuttingResponse getCuttingResponse = GetAllCuttingResponse.from(cutting, imports.get());
            getAllCuttingResponses.add(getCuttingResponse);
        }
        return getAllCuttingResponses;
    }

    @Override
    public List<RevenueResponse> getRevenueDetails() {
        List<Product> products = productRepository.findAll();
        List<RevenueResponse> revenueResponses = new ArrayList<>();
        for (Product product : products) {
            List<Import> importsList = importRepository.findAllByProductIdOrderByCreatedOnAsc(product.getProductId());
            double buyingPrice = 0.0;
            long quantity = 0;
            for (Import imports : importsList) {
                if (imports.getCartoonBuyingPrice() != null && buyingPrice < imports.getCartoonBuyingPrice()) {
                    buyingPrice = imports.getCartoonBuyingPrice();
                }
                quantity = quantity + imports.getCartoonQuantity();
            }
            double totalBuyingPrice = (double) quantity * buyingPrice;

            List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllByProductIdOrderByCreatedOnAsc(product.getProductId());
            double sellingPrice = 0.0;
            quantity = 0;
            for (OrderDetails orderDetails : orderDetailsList) {
                if (orderDetails.getCartonSellingPrice() != null && buyingPrice > orderDetails.getCartonSellingPrice()) {
                    sellingPrice = orderDetails.getCartonSellingPrice();
                }
                quantity = quantity + orderDetails.getCartonQuantity();
            }
            double totalSellingPrice = (double) quantity * sellingPrice;
            double revenue = totalSellingPrice - totalBuyingPrice;
            RevenueResponse revenueResponse = RevenueResponse.from(product, buyingPrice, sellingPrice, totalBuyingPrice, totalSellingPrice, revenue);
            revenueResponses.add(revenueResponse);
        }
        return revenueResponses;
    }

    @Override
    public GetAllImportResponse getAllProductByTime(String productId, String warhouse, String start, String end) {
        System.out.println(start);
        if (start != null && end != null && start.compareTo(end) > 0) {
            throw new RequestValidationException("Start time should be less than end time");
        }
        List<Import> importList;
        if (productId == null && warhouse == null) {
            importList = importRepository.findAllByCreatedOnBetween(DateUtil.getZoneDateTime(start + "T00:00:00"),
                    DateUtil.getZoneDateTime(end + "T00:00:00"));
        } else if (productId == null) {
            importList = importRepository.findAllByWarehouseAndCreatedOnBetween(Warehouse.fromName(warhouse),
                    DateUtil.getZoneDateTime(start + "T00:00:00"), DateUtil.getZoneDateTime(end + "T00:00:00"));
        } else if (warhouse == null) {
            importList = importRepository.findAllByProductIdAndCreatedOnBetween(productId, DateUtil.getZoneDateTime(start + "T00:00:00"),
                    DateUtil.getZoneDateTime(end + "T00:00:00"));
        } else {
            importList = importRepository.findAllByProductIdAndWarehouseAndCreatedOnBetween(productId, Warehouse.fromName(warhouse),
                    DateUtil.getZoneDateTime(start + "T00:00:00"), DateUtil.getZoneDateTime(end + "T00:00:00"));
        }
        List<GetImportResponse> importResponseList = new ArrayList<>();
        assert importList != null;
        for (Import imports : importList) {
            importResponseList.add(GetImportResponse.from(imports));
        }
        return GetAllImportResponse.from(importResponseList);
    }

    @Override
    public List<GetAllCuttingResponse> getAllCuttingDetailsByTime(String productId, String warhouse, String start, String end) {
        System.out.println(start);
        if (start != null && end != null && start.compareTo(end) > 0) {
            throw new RequestValidationException("Start time should be less than end time");
        }
        List<Cutting> cuttingList;
        cuttingList = cuttingRepository.findAllByCreatedOnBetween(DateUtil.getZoneDateTime(start + "T00:00:00"),
                DateUtil.getZoneDateTime(end + "T00:00:00"));
        List<GetAllCuttingResponse> getAllCuttingResponses = new ArrayList<>();
        for (Cutting cutting : cuttingList) {
            Optional<Import> imports = importRepository.findById(cutting.getImportId().getId());
            GetAllCuttingResponse getCuttingResponse = GetAllCuttingResponse.from(cutting, imports.get());
            getAllCuttingResponses.add(getCuttingResponse);
        }
        return getAllCuttingResponses;
    }

    @Override
    public List<RevenueResponse> getRevenueDetailsByTime(String start, String end) {
        if (start != null && end != null && start.compareTo(end) > 0) {
            throw new RequestValidationException("Start time should be less than end time");
        }
        List<Product> products = productRepository.findAll();
        List<RevenueResponse> revenueResponses = new ArrayList<>();
        for (Product product : products) {
            List<Import> importsList = importRepository.findAllByProductIdAndCreatedOnBetween(product.getProductId(),
                    DateUtil.getZoneDateTime(start + "T00:00:00"), DateUtil.getZoneDateTime(end + "T00:00:00"));
            double buyingPrice = 0.0;
            long quantity = 0;
            for (Import imports : importsList) {
                if (imports.getCartoonBuyingPrice() != null && buyingPrice < imports.getCartoonBuyingPrice()) {
                    buyingPrice = imports.getCartoonBuyingPrice();
                }
                quantity = quantity + imports.getCartoonQuantity();
            }
            double totalBuyingPrice = quantity * buyingPrice;

            List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllByProductIdAndCreatedOnBetween(product.getProductId(),
                    DateUtil.getZoneDateTime(start + "T00:00:00"), DateUtil.getZoneDateTime(end + "T00:00:00"));
            double sellingPrice = 0.0;
            quantity = 0;
            for (OrderDetails orderDetails : orderDetailsList) {
                if (orderDetails.getCartonSellingPrice() != null && buyingPrice > orderDetails.getCartonSellingPrice()) {
                    sellingPrice = orderDetails.getCartonSellingPrice();
                }
                quantity = quantity + orderDetails.getCartonQuantity();
            }
            double totalSellingPrice = quantity * sellingPrice;
            double revenue = totalSellingPrice - totalBuyingPrice;
            RevenueResponse revenueResponse = RevenueResponse.from(product, buyingPrice, sellingPrice, totalBuyingPrice, totalSellingPrice, revenue);
            revenueResponses.add(revenueResponse);
        }
        return revenueResponses;
    }

    @Override
    public SuccessResponse moveProduct(MoveProductRequest moveProductRequest) {
        moveProductRequest.validate();
        return SuccessResponse.from("Product Move Successfully");
    }

    private List<GetCuttingResponse> addCutting(List<AddCuttingRequest> cuttings, Import imports, User user) {
        List<GetCuttingResponse> cuttingResponses = new ArrayList<>();
        for (AddCuttingRequest addCuttingRequest : cuttings) {
            Cutting cutting = new Cutting();
            cutting.setCartoonNo(addCuttingRequest.getCartonNo());
            cutting.setCartoonWeight(addCuttingRequest.getCartonWeight());
            cutting.setPieceInCarton(addCuttingRequest.getPieceInCarton());
            cutting.setCartoonBuyingPrice(addCuttingRequest.getCartonBuyingPrice());
            cutting.setImportId(imports);
            cutting.setCreatedBy(user);
            System.out.println(cutting);
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
