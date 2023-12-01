package com.flagship.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.StringUtils;

public enum UOM {
    KG_LT(1),
    PIECE(2),
    CARTOON(3),
    ;

    UOM(int type) {
    }

    public static UOM fromValue(int value) {
        switch (value) {
            case 1:
                return KG_LT;
            case 2:
                return PIECE;
            case 3:
                return CARTOON;
            default:
                return null;
        }
    }

    @JsonCreator
    public static UOM fromName(String name) {
        switch (StringUtils.upperCase(name)) {
            case "KG_LT":
                return KG_LT;
            case "PIECE":
                return PIECE;
            case "CARTOON":
                return CARTOON;
            default:
                return null;
        }
    }

    @JsonValue
    public String getName() {
        return toString();
    }
}
