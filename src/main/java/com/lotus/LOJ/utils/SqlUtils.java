package com.lotus.LOJ.utils;

import org.apache.commons.lang3.StringUtils;

public class SqlUtils {

    /**
     * 校验排序字段是否合法（防止 SQL 注入）
     *
     * @param sortField 待校验的排序字段
     * @return 如果合法返回 true，否则返回 false
     */
    public static boolean validSortField(String sortField) {
        if (StringUtils.isBlank(sortField)) {
            return false;
        }

        // 使用正则表达式检查排序字段
        String regex = "^[a-zA-Z0-9_.]+$"; // 只允许字母、数字、下划线和点
        if (!sortField.matches(regex)) {
            return false;
        }

        // 限制字段长度
        return sortField.length() <= 64; // 假设最大长度为64
    }
}

