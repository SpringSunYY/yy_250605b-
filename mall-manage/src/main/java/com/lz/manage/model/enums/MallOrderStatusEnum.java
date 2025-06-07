package com.lz.manage.model.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 订单审核状态 枚举
 */
@Getter
public enum MallOrderStatusEnum {
    ORDER_STATUS_PENDING("0", "待审核"),
    ORDER_STATUS_APPROVED("1", "同意"),
    ORDER_STATUS_REJECTED("2", "拒绝");

    private static final Map<String, MallOrderStatusEnum> VALUE_MAP = new HashMap<>();

    static {
        for (MallOrderStatusEnum item : values()) {
            VALUE_MAP.put(item.value, item);
        }
    }

    private final String value;
    private final String label;

    MallOrderStatusEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static Optional<MallOrderStatusEnum> getEnumByValue(String value) {
        return Optional.ofNullable(VALUE_MAP.get(value));
    }
}
