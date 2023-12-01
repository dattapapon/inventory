package com.flagship.constant.db;

public class DbConstant {
    private DbConstant() {

    }

    public static class DbCommon {
        public static final String CREATED_BY = "created_by";
        public static final String CREATED_ON = "created_on";
        public static final String LAST_UPDATED_BY = "last_updated_by";
        public static final String LAST_UPDATED_ON = "last_updated_on";

        public static final String ID = "id";

        DbCommon() {
        }
    }

    public static class DbUser extends DbCommon {
        public static final String TABLE_NAME = "users";
        public static final String NAME = "name";

        public static final String EMAIL = "email";
        public static final String DATE_OF_BIRTH = "date_of_birth";
        public static final String PASSWORD = "password";
        public static final String GENDER = "gender";
    }

    public static class DbProduct extends DbCommon {
        public static final String TABLE_NAME = "product";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
    }

    public static class DbCountry extends DbCommon {
        public static final String TABLE_NAME = "country";
        public static final String COUNTRY_ID = "country_id";
        public static final String COUNTRY_NAME = "country_name";
    }

    public static class DbBrand extends DbCommon {
        public static final String TABLE_NAME = "brand";
        public static final String BRAND_ID = "brand_id";
        public static final String BRAND_NAME = "brand_name";
    }

    public static class DbCategories extends DbCommon {
        public static final String TABLE_NAME = "categories";
        public static final String CATEGORY_ID = "category_id";
        public static final String CATEGORY_NAME = "category_name";
    }

    public static class DbWastage extends DbCommon {
        public static final String TABLE_NAME = "wastage";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String PRODUCT_SERIAL_NO = "product_serial_no";
        public static final String WAR_HOUSE = "war_house";
        public static final String IMPORT_DATE = "import_date";
        public static final String CAUSE = "cause";
    }

    public static class DbImportDetails extends DbCommon {
        public static final String TABLE_NAME = "import_details";
        public static final String SHIPMENT_NO = "shipment_no";
        public static final String PRODUCT = "product";
        public static final String CATEGORY = "category";
        public static final String BRAND = "brand";
        public static final String PRODUCTION = "production";
        public static final String COUNTRY = "country";
        public static final String WAREHOUSE = "warehouse";
        public static final String EXPIRE = "expire";
        public static final String CARTOON = "cartoon";
        public static final String PIECE = "piece";
        public static final String KG_LT = "kg_lt";
        public static final String UOM = "uom";
        public static final String UNIT_PRICE = "price";
        public static final String TOTAL_PRICE = "total";
    }

    public static class DbImportMaster extends DbCommon {
        public static final String TABLE_NAME = "import_master";
        public static final String SHIPMENT_NO = "shipment_no";
        public static final String COUNTRY = "country";
        public static final String DATE = "date";
    }

    public static class DbCustomer extends DbCommon {
        public static final String TABLE_NAME = "customer";
        public static final String CUSTOMER_ID = "customer_id";

        public static final String EMAIL = "email";
        public static final String CUSTOMER_NAME = "customer_name";
        public static final String COMPANY = "company";
        public static final String PHONE_NUMBER = "phone_number";
        public static final String CUSTOMER_TYPE = "customer_type";
    }

    public static class DbSalesPerson extends DbCommon {
        public static final String TABLE_NAME = "sales_person";
        public static final String SALES_PERSON_ID = "sales_person_id";
        public static final String SALES_PERSON_NAME = "sales_person_name";
        public static final String AREA = "area";
        public static final String PHONE_NUMBER = "phone_number";
    }

    public static class DbOrderMaster extends DbCommon {
        public static final String TABLE_NAME = "order_master";
        public static final String ORDER_ID = "order_id";
        public static final String SALES_PERSON_NAME = "sales_person_name";
        public static final String SUPPLIER_CODE = "supplier_code";
        public static final String CUSTOMER_NAME = "customer_name";
        public static final String PHONE_NUMBER = "phone_number";
        public static final String CUSTOMER_TYPE = "customer_type";
        public static final String CUSTOMER_STATUS = "customer_status";
        public static final String COMPANY_NAME = "company_name";
        public static final String ORDER_DATE = "order_date";
        public static final String CREDIT_TERM = "credit_term";
        public static final String BILL_STATUS = "bill_status";
        public static final String TOTAL_BILL = "total_bill";
        public static final String DUE_BILL = "bill_due";

    }

    public static class DbOrderDetails extends DbCommon {
        public static final String TABLE_NAME = "order_details";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String CARTON_QUANTITY = "carton_quantity";
        public static final String CARTOON_SELLING_PRICE = "carton_selling_quantity";
        public static final String CARTOON_WEIGHT = "carton_weight";
        public static final String PIECE_QUANTITY = "piece_quantity";
        public static final String PIECE_SELLING_PRICE = "piece_selling_quantity";
        public static final String PIECE_WEIGHT = "piece_weight";
        public static final String KG_LT_QUANTITY = "kg_lt_quantity";
        public static final String KG_LT_SELLING_PRICE = "kg_lt_selling_quantity";
        public static final String TAX = "tax";
        public static final String VAT = "vat";
        public static final String TOTAL_BILL = "bill";
        public static final String ORDER_ID = "order_id";
    }

    public static class DbOrderCutting extends DbCommon {
        public static final String TABLE_NAME = "order_cutting";
        public static final String CARTON_NO = "carton_no";
        public static final String CARTON_QUANTITY = "carton_quantity";
        public static final String PIECE_IN_CARTON = "piece_in_carton";
        public static final String PRICE = "price";
        public static final String ORDER_ID = "order_id";
    }

    public static class DbStock extends DbCommon {
        public static final String TABLE_NAME = "stock";
        public static final String PRODUCT = "product";
        public static final String UOM = "uom";
        public static final String TOTAL_BUY = "total";
        public static final String TOTAL_SELL = "sell";
        public static final String IN_STOCK = "inStock";
    }
}

