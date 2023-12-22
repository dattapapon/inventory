package com.flagship.service.impl;

import com.flagship.constant.enums.Cause;
import com.flagship.constant.enums.CustomerType;
import com.flagship.constant.enums.UOM;
import com.flagship.dto.request.*;
import com.flagship.dto.response.*;
import com.flagship.exception.RequestValidationException;
import com.flagship.model.db.*;
import com.flagship.repository.*;
import com.flagship.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommonServiceImpl implements CommonService {
  private final BrandRepository brandRepository;
  private final CountryRepository countryRepository;
  private final CategoriesRepository categoriesRepository;
  private final UserRepository userRepository;
  private final BranchRepository branchRepository;
  private final SaleRepository saleRepository;
  private final SupplierRepository supplierRepository;
  private final ProductRepository productRepository;
  private final StockRepository stockRepository;
  private final CustomerRepository customerRepository;
  private final SalesPersonRepository salesPersonRepository;
  private final WastageRepository wastageRepository;
  private final ImportMasterRepository importMasterRepository;
  private final OrderMasterRepository orderMasterRepository;
  private final ReturnsRepository returnsRepository;
  private final ImportDetailsRepository importDetailsRepository;
  private final OrderDetailsRepository orderDetailsRepository;

  @Autowired
  public CommonServiceImpl(BrandRepository brandRepository, CountryRepository countryRepository,
                           CategoriesRepository categoriesRepository, UserRepository userRepository,
                           BranchRepository branchRepository, SaleRepository saleRepository,
                           SupplierRepository supplierRepository, ProductRepository productRepository,
                           StockRepository stockRepository, CustomerRepository customerRepository,
                           SalesPersonRepository salesPersonRepository, WastageRepository wastageRepository,
                           ImportMasterRepository importMasterRepository, OrderMasterRepository orderMasterRepository,
                           ReturnsRepository returnsRepository, ImportDetailsRepository importDetailsRepository,
                           OrderDetailsRepository orderDetailsRepository) {
    this.brandRepository = brandRepository;
    this.countryRepository = countryRepository;
    this.categoriesRepository = categoriesRepository;
    this.userRepository = userRepository;
    this.branchRepository = branchRepository;
    this.saleRepository = saleRepository;
    this.supplierRepository = supplierRepository;
    this.productRepository = productRepository;
    this.stockRepository = stockRepository;
    this.customerRepository = customerRepository;
    this.salesPersonRepository = salesPersonRepository;
    this.wastageRepository = wastageRepository;
    this.importMasterRepository = importMasterRepository;
    this.orderMasterRepository = orderMasterRepository;
    this.returnsRepository = returnsRepository;
    this.importDetailsRepository = importDetailsRepository;
    this.orderDetailsRepository = orderDetailsRepository;
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
  public BrandResponse addBrand(BrandRequest request) {
    Optional<Brand> optionalBrand = brandRepository.findByBrandId(request.getBrandId());
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    if (optionalBrand.isPresent()) {
      throw new RequestValidationException("Brand is exist");
    } else {
      Brand brand = new Brand();
      brand.setBrandId(request.getBrandId());
      brand.setBrandName(request.getBrandName());
      brand.setCreatedBy(user.get());
      brandRepository.save(brand);
      return BrandResponse.from("Brand Added Successfully", brand);
    }
  }

  @Override
  public CountryResponse addCountry(CountryRequest request) {
    Optional<Country> optionalCountry = countryRepository.findByCountryId(request.getCountryId());
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    if (optionalCountry.isPresent()) {
      throw new RequestValidationException("Country is exist");
    } else {
      Country country = new Country();
      country.setCountryId(request.getCountryId());
      country.setCountryName(request.getCountryName());
      country.setCreatedBy(user.get());
      countryRepository.save(country);
      return CountryResponse.from("Country Added Successfully", country);
    }
  }

  @Override
  public CategoriesResponse addCategory(CategoriesRequest request) {
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    Optional<Categories> optionalCategories = categoriesRepository.findByCategoryId(request.getCategoryId());
    if (optionalCategories.isPresent()) {
      throw new RequestValidationException("Category is exist");
    } else {
      Categories categories = new Categories();
      categories.setCategoryId(request.getCategoryId());
      categories.setCategoryName(request.getCategoryName());
      categories.setCreatedBy(user.get());
      categoriesRepository.save(categories);
      return CategoriesResponse.from("Category Added Successfully", categories);
    }
  }

  @Override
  public SaleResponse addSale(SaleRequest request) {
    //Optional<Sale> optionalSale = saleRepository.findBySaleCode(request.getSaleCode());
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }

    Sale sale = new Sale();
    sale.setSupplier(getSupplier(request.getSupplier().getValue()));
    sale.setProduct(getProduct(request.getProduct().getValue()));
    sale.setArticle(request.getArticle());
    sale.setSaleCode(request.getSaleCode());
    sale.setSaleName(request.getSaleName());
    sale.setCreatedBy(user.get());
    saleRepository.save(sale);
    return SaleResponse.from("Sale And Article code Added Successfully", sale);

  }

  private Product getProduct(String product) {
    Optional<Product> optionalProduct = productRepository.findByProductId(product);
    if (optionalProduct.isPresent()) {
      return optionalProduct.get();
    } else {
      throw new RequestValidationException("Product is not exist");
    }
  }

  private Supplier getSupplier(String supplier) {
    Optional<Supplier> optionalSupplier = supplierRepository.findBySupplierId(supplier);
    if (optionalSupplier.isPresent()) {
      return optionalSupplier.get();
    } else {
      throw new RequestValidationException("Supplier is not present");
    }
  }

  @Override
  public BranchResponse addBranch(BranchRequest request) {
    Optional<Branch> optionalBranch = branchRepository.findByBranchCodeAndSupplier(request.getBranchCode(),
            getSupplier(request.getSupplier().getValue()));
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    if (optionalBranch.isPresent()) {
      throw new RequestValidationException("Branch is exist");
    } else {
      Branch branch = new Branch();
      branch.setSupplier(getSupplier(request.getSupplier().getValue()));
      branch.setBranchCode(request.getBranchCode());
      branch.setBranchName(request.getBranchName());
      branch.setCreatedBy(user.get());
      branchRepository.save(branch);
      return BranchResponse.from("Branch Added Successfully", branch);
    }
  }

  @Override
  public SupplierResponse addSupplier(SupplierRequest request) {
    Optional<Supplier> optionalSupplier = supplierRepository.findBySupplierId(request.getSupplierId());
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    if (optionalSupplier.isPresent()) {
      throw new RequestValidationException("Supplier is exist");
    } else {
      Supplier supplier = new Supplier();
      supplier.setSupplierId(request.getSupplierId());
      supplier.setSupplierName(request.getSupplierName());
      supplier.setCreatedBy(user.get());
      supplierRepository.save(supplier);
      return SupplierResponse.from("Supplier Added Successfully", supplier);
    }
  }

  @Override
  public AllProductResponse getProduct() {
    List<Product> allProduct = (List<Product>) productRepository.findAll();
    List<SingleProductResponse> getProductResponse = new ArrayList<>();
    for (Product product : allProduct) {
      getProductResponse.add(SingleProductResponse.from(product));
    }
    return AllProductResponse.from(getProductResponse);
  }

  @Override
  public AllStockResponse getStock() {
    List<Stock> stockList = (List<Stock>) stockRepository.findAll();
    List<StockResponse> stockResponseList = new ArrayList<>();
    for (Stock stock : stockList) {
      stockResponseList.add(StockResponse.from(stock));
    }
    return AllStockResponse.from(stockResponseList);
  }

  @Override
  public AllBrandResponse getBrand() {
    List<Brand> brandList = (List<Brand>) brandRepository.findAll();
    List<SingleBrandResponse> singleBrandResponses = new ArrayList<>();
    for (Brand brand : brandList) {
      singleBrandResponses.add(SingleBrandResponse.from(brand));
    }
    return AllBrandResponse.from(singleBrandResponses);
  }

  @Override
  public AllCountryResponse getCountry() {
    List<Country> countryList = (List<Country>) countryRepository.findAll();
    List<SingleCountryResponse> singleCountryResponseList = new ArrayList<>();
    for (Country country : countryList) {
      singleCountryResponseList.add(SingleCountryResponse.from(country));
    }
    return AllCountryResponse.from(singleCountryResponseList);
  }

  @Override
  public AllCategoryResponse getCategory() {
    List<Categories> categoriesList = (List<Categories>) categoriesRepository.findAll();
    List<SingleCategoryResponse> singleCategoryResponseList = new ArrayList<>();
    for (Categories categories : categoriesList) {
      singleCategoryResponseList.add(SingleCategoryResponse.from(categories));
    }
    return AllCategoryResponse.from(singleCategoryResponseList);
  }

  @Override
  public SingleSale getSale(String product, String supplier) {
    Optional<Sale> optionalSale = saleRepository.findByProductAndSupplier(getProduct(product), getSupplier(supplier));
    return optionalSale.map(sale -> SingleSale.from(sale.getSaleName(), sale.getSaleCode(), sale.getArticle()))
            .orElse(null);
  }

  @Override
  public GetAllSuppliers getSupplier() {
    List<Supplier> supplierList = (List<Supplier>) supplierRepository.findAll();
    List<SingleSupplier> singleSuppliers = new ArrayList<>();
    for (Supplier supplier : supplierList) {
      singleSuppliers.add(SingleSupplier.from(supplier));
    }
    return GetAllSuppliers.from(singleSuppliers);
  }

  private Boolean checkProduct(String productId) {
    Optional<Product> product = productRepository.findByProductId(productId);
    return product.isPresent();
  }

  @Override
  public CustomerResponse addCustomer(CustomerRequest customerRequest) {
    customerRequest.validate();
    Boolean checkCustomerExist = customerExist(customerRequest.getCustomerId());
    if (checkCustomerExist) {
      throw new RequestValidationException("Customer id is exist");
    }
    Optional<User> user = userRepository.findByEmail(customerRequest.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    Customer customer = new Customer();
    customer.setCustomerId(customerRequest.getCustomerId());
    customer.setCustomerName(customerRequest.getCustomerName());
    customer.setCompany(customerRequest.getCompany());
    customer.setPhoneNumber(customerRequest.getPhoneNumber());
    customer.setCustomerType(CustomerType.fromName(customerRequest.getCustomerType().getName()));
    if (customerRequest.getSupplier() != null && !customerRequest.getSupplier().isEmpty()) {
      customer.setSupplier(getSupplier(customerRequest.getSupplier()));
    }
    if (customerRequest.getBranch() != null && customerRequest.getBranch().getValue() != null
            && !customerRequest.getBranch().getValue().isEmpty()) {
      customer.setBranch(getBranch(customerRequest.getBranch().getValue(), customerRequest.getSupplier()));
    }
    customer.setAddress(customerRequest.getAddress());
    customer.setBinNo(customerRequest.getBinNo());
    customer.setCreatedBy(user.get());
    customerRepository.save(customer);
    return CustomerResponse.from("Customer added Successfully", customer);
  }

  private Boolean customerExist(String customerId) {
    Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
    return customer.isPresent();
  }

  private Branch getBranch(String branch, String supplier) {
    Optional<Branch> optionalBranch = branchRepository.findByBranchCodeAndSupplier(branch, getSupplier(supplier));
    if (optionalBranch.isPresent()) {
      return optionalBranch.get();
    } else {
      throw new RequestValidationException("Branch is not present");
    }
  }

  @Override
  public GetAllCustomer getCustomer() {
    List<Customer> customerList = (List<Customer>) customerRepository.findAll();
    List<SingleCustomer> singleCustomers = new ArrayList<>();
    for (Customer customer : customerList) {
      singleCustomers.add(SingleCustomer.from(customer));
    }
    return GetAllCustomer.from(singleCustomers);
  }

  @Override
  public AllBranchResponse getBranch(String supplier) {
    List<Branch> branchList = branchRepository.findBySupplier(getSupplier(supplier));
    List<SingleBranchResponse> singleBranchResponses = new ArrayList<>();
    for (Branch branch : branchList) {
      singleBranchResponses.add(SingleBranchResponse.from(branch));
    }
    return AllBranchResponse.from(singleBranchResponses);
  }

  @Override
  public SalesPersonResponse addSalesPerson(SalesPersonRequest salesPersonRequest) {
    salesPersonRequest.validate();
    Boolean checkSalesPersonExist = salesPersonExist(salesPersonRequest.getSalesPersonId());
    if (checkSalesPersonExist) {
      throw new RequestValidationException("Sales Person exist");
    }
    Optional<User> user = userRepository.findByEmail(salesPersonRequest.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not found");
    }
    SalesPerson salesPerson = new SalesPerson();
    salesPerson.setSalesPersonId(salesPersonRequest.getSalesPersonId());
    salesPerson.setSalesPersonName(salesPersonRequest.getSalesPersonName());
    salesPerson.setPhoneNumber(salesPersonRequest.getPhoneNumber());
    salesPerson.setArea(salesPersonRequest.getArea());
    salesPerson.setCreatedBy(user.get());
    salesPersonRepository.save(salesPerson);
    return SalesPersonResponse.from("Sales Person added Successfully", salesPerson);
  }

  @Override
  public AllSalesPersonResponse getSalesPerson() {
    List<SalesPerson> salesPersonList = (List<SalesPerson>) salesPersonRepository.findAll();
    List<SingleSalesPersonResponse> singleSalesPersonResponseList = new ArrayList<>();
    for (SalesPerson salesPerson : salesPersonList) {
      singleSalesPersonResponseList.add(SingleSalesPersonResponse.from(salesPerson));
    }
    return AllSalesPersonResponse.from(singleSalesPersonResponseList);
  }

  private Boolean salesPersonExist(String salesPersonId) {
    Optional<SalesPerson> salesPerson = salesPersonRepository.findBySalesPersonId(salesPersonId);
    return salesPerson.isPresent();
  }

  @Override
  public WastageResponse addWastage(WastageAddRequest request) {
    Wastage wastage = new Wastage();
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    wastage.setProduct(getProduct(request.getProduct().getValue()));
    wastage.setShipment(getShipment(request.getShipment().getValue()));
    wastage.setCartoon(request.getCartoon());
    wastage.setPiece(request.getPiece());
    wastage.setKgLt(request.getKgLt());
    wastage.setCause(Cause.fromName(request.getCause().toString()));
    wastage.setCreatedBy(user.get());
    updateStock(wastage);
    wastageRepository.save(wastage);
    return WastageResponse.from("Wastage added successfully", wastage);
  }

  private void updateStock(Wastage wastage) {
    Optional<ImportDetails> importDetails = importDetailsRepository.findByProductAndImportMaster(wastage.getProduct(),
            wastage.getShipment());
    if (importDetails.isPresent()) {
      importDetails.get().setCartoon(importDetails.get().getCartoon() - wastage.getCartoon());
      importDetails.get().setPiece(importDetails.get().getPiece() - wastage.getPiece());
      importDetails.get().setKgLt(importDetails.get().getKgLt() - wastage.getKgLt());
      if (importDetails.get().getUom().equals(UOM.KG_LT)) {
        updateStockData(wastage.getProduct(), wastage.getKgLt());
      }
    }
  }

  private void updateStockData(Product product, Double quantity) {
    Optional<Stock> optionalStock = stockRepository.findByProduct(product);
    if (optionalStock.isPresent()) {
      optionalStock.get().setTotalBuy(optionalStock.get().getTotalBuy());
      optionalStock.get().setTotalSell(optionalStock.get().getTotalSell() + quantity);
      optionalStock.get().setInStock(optionalStock.get().getInStock() - quantity);
    }
  }

  @Override
  public ReturnResponse addReturn(ReturnRequest request) {
    Returns returns = new Returns();
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    returns.setProduct(getProduct(request.getProduct().getValue()));
    returns.setOrder(getOrderId(request.getOrder()));
    returns.setCartoon(request.getCartoon());
    returns.setPiece(request.getPiece());
    returns.setKgLt(request.getKgLt());
    returns.setCreatedBy(user.get());
    returns.setDeliveryMan(request.getDeliveryMan());
    returns.setCause(Cause.fromName(request.getCause().toString()));
    returnsRepository.save(returns);
    return ReturnResponse.from("Returns added successfully", returns);
  }

  @Override
  public AllProductRevenueResponse calculateRevenue() {
    DecimalFormat decimalFormat = new DecimalFormat("#.###");
    List<Product> products = (List<Product>) productRepository.findAll();
    List<SingleProductRevenueResponse> singleProductRevenueResponses = new ArrayList<>();
    for (Product product : products) {
      Double totalBuy = 0.0;
      Double totalSell = 0.0;
      Double inStock = 0.0;
      double averageBuyingPrice;
      double averageSellingPrice;
      Double totalBuyingPrice = 0.0;
      Double totalSellingPrice = 0.0;
      double revenue;
      List<ImportDetails> importDetailsList = importDetailsRepository.findByProduct(product);
      List<OrderDetails> orderDetailsList = orderDetailsRepository.findByProduct(product);
      Optional<Stock> optionalStock = stockRepository.findByProduct(product);
      if (optionalStock.isPresent()) {
        totalBuy = optionalStock.get().getTotalBuy();
        totalSell = optionalStock.get().getTotalSell();
        inStock = optionalStock.get().getInStock();
      }
      for (ImportDetails importDetails : importDetailsList) {
        totalBuyingPrice = totalBuyingPrice + importDetails.getTotal();
      }
      for (OrderDetails orderDetails : orderDetailsList) {
        totalSellingPrice = totalSellingPrice + orderDetails.getTotalPrice();
      }
      averageBuyingPrice = totalBuy > 0 ? Double.parseDouble(decimalFormat.format(totalBuyingPrice / totalBuy)) : 0.0;
      averageSellingPrice = totalSell > 0 ? Double.parseDouble(decimalFormat.format(totalSellingPrice / totalSell)) : 0.0;
      revenue = Double.parseDouble(decimalFormat.format(totalSellingPrice - totalBuyingPrice));
      singleProductRevenueResponses.add(SingleProductRevenueResponse.from(product.getProductName(), totalBuy, totalSell,
              inStock, averageBuyingPrice, averageSellingPrice, totalBuyingPrice, totalSellingPrice, revenue));
    }
    return AllProductRevenueResponse.from(singleProductRevenueResponses);
  }

  private OrderMaster getOrderId(Long order) {
    Optional<OrderMaster> optionalOrderMaster = orderMasterRepository.findByOrderId(order);
    if (optionalOrderMaster.isPresent()) {
      return optionalOrderMaster.get();
    } else {
      throw new RequestValidationException("Order not exist");
    }
  }

  private ImportMaster getShipment(String shipment) {
    Optional<ImportMaster> optionalImportMaster = importMasterRepository.findByShipmentNo(shipment);
    if (optionalImportMaster.isPresent()) {
      return optionalImportMaster.get();
    } else {
      throw new RequestValidationException("Shipment not exist");
    }
  }
}