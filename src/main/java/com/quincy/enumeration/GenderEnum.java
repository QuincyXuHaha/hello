package com.quincy.enumeration;

/**
 * @author quincy
 * @date 2018/3/5 星期一
 */
public enum GenderEnum {
    MALE("0"),
    FEMALE("1");

    private final String value;

    GenderEnum(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }

    public GenderEnum get(int v) {
        String value = String.valueOf(v);
        return get(value);
    }

    public GenderEnum get(String value) {
        if (value != null && !"".equals(value.trim())) {
            for (GenderEnum e : GenderEnum.values()) {
                if (e.getValue().equals(value)) {
                    return e;
                }
            }
        }
        return null;
    }

}
