package com.lz.manage.model.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 商品上下架状态 枚举
 */
@Getter
public enum MallGoodsStatusEnum {
    GOODS_STATUS_OFF("0", "下架"),
    GOODS_STATUS_ON("1", "上架");

    private static final Map<String, MallGoodsStatusEnum> VALUE_MAP = new HashMap<>();

    static {
        for (MallGoodsStatusEnum item : values()) {
            VALUE_MAP.put(item.value, item);
        }
    }

    private final String value;
    private final String label;

    MallGoodsStatusEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static Optional<MallGoodsStatusEnum> getEnumByValue(String value) {
        return Optional.ofNullable(VALUE_MAP.get(value));
    }
}
