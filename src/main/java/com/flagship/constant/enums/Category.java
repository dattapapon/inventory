package com.flagship.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.StringUtils;

public enum Category {
    VEG(1),
    MEAT(2),
    FISH(3),
    FRUITS(4),
    PACKET(5),
            ;

    private final int type;

    Category(int type) {
        this.type = type;
    }

    public static Category fromValue(int value) {
        switch (value) {
            case 1:
                return VEG;
            case 2:
                return MEAT;
            case 3:
                return FISH;
            case 4:
                return FRUITS;
            case 5:
                return PACKET;
            default:
                return null;
        }
    }

    @JsonCreator
    public static Category fromName(String name) {
        switch (StringUtils.upperCase(name)) {
            case "VEG":
                return VEG;
            case "MEAT":
                return MEAT;
            case "FISH":
                return FISH;
            case "FRUITS":
                return FRUITS;
            case "PACKET":
                return PACKET;
            default:
                return null;
        }
    }

    public int getType() {
        return type;
    }

    @JsonValue
    public String getName() {
        return toString();
    }
}
