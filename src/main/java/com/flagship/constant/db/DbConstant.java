package com.flagship.constant.db;

public class DbConstant {
    private DbConstant() {

    }

    public static class DbCommon {
        public static final String CREATED_ON = "created_on";
        public static final String CREATED_BY = "created_by";
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

    public static class DbWastage extends DbCommon {
        public static final String TABLE_NAME = "wastage";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String PRODUCT_SERIAL_NO = "product_serial_no";
        public static final String WAR_HOUSE = "war_house";
        public static final String IMPORT_DATE = "import_date";
        public static final String CAUSE = "cause";
    }

    public static class DbImport extends DbCommon {
        public static final String TABLE_NAME = "import";
        public static final String IMPORT_ID = "import_id";
        public static final String SHIPMENT_NO = "shipment_no";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String CARTOON_QUANTITY = "cartoon_quantity";
        public static final String CARTOON_BUYING_PRICE = "cartoon_buying_price";
        public static final String CARTOON_WEIGHT = "cartoon_weight";
        public static final String PIECE_QUANTITY = "piece_quantity";
        public static final String PIECE_BUYING_PRICE = "piece_buying_price";
        public static final String PIECE_WEIGHT = "piece_weight";
        public static final String KG_LT_QUANTITY = "kg_lt_quantity";
        public static final String KG_LT_BUYING_PRICE = "kg_lt_buying_price";
        public static final String BRAND = "brand";
        public static final String PRODUCTION = "production";
        public static final String COUNTRY = "country";
        public static final String TAX = "tax";
        public static final String VAT = "vat";
        public static final String WAREHOUSE = "warehouse";
        public static final String EXPIRE = "expire";
        public static final String USER_ID = "receiver_name";
        public static final String CATEGORY = "category";
    }

    public static class DbCutting extends DbCommon {
        public static final String TABLE_NAME = "cutting";
        public static final String CARTOON_NO = "cartoon_no";
        public static final String CARTOON_WEIGHT = "cartoon_weight";
        public static final String PIECE_IN_CARTOON = "piece_in_cartoon";
        public static final String CARTOON_BUYING_PRICE = "cartoon_buying_price";
        public static final String IMPORT_ID = "import_id";
    }

    public static class DbCustomer extends DbCommon {
        public static final String TABLE_NAME = "customer";
        public static final String CUSTOMER_ID = "customer_id";

        public static final String EMAIL = "email";
        public static final String CUSTOMER_NAME = "customer_name";
        public static final String COMPANY = "company";
        public static final String PHONE_NUMBER = "phone_number";
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

    public static class DbProductRecords extends DbCommon {
        public static final String TABLE_NAME = "product_records";
        public static final String PRODUCT_ID = "product_id";
        public static final String CARTON_NO = "carton_no";
        public static final String TOTAL_CARTON = "total_carton";
        public static final String TOTAL_PIECE = "total_piece";
        public static final String TOTAL_KG_LT = "total_kg_lt";
        public static final String PRICE = "price";
    }
}

