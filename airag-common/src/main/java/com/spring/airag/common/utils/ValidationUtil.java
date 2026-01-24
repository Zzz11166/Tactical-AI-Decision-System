package com.spring.airag.common.utils;

import java.util.regex.Pattern;

/**
 * 校验工具类
 */
public class ValidationUtil {

    // 常用正则表达式
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PHONE_PATTERN = "^1[3-9]\\d{9}$";
    private static final String ID_CARD_PATTERN = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{3,20}$";
    private static final String PASSWORD_PATTERN = "^.{6,20}$";

    private static final Pattern emailRegex = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern phoneRegex = Pattern.compile(PHONE_PATTERN);
    private static final Pattern idCardRegex = Pattern.compile(ID_CARD_PATTERN);
    private static final Pattern usernameRegex = Pattern.compile(USERNAME_PATTERN);
    private static final Pattern passwordRegex = Pattern.compile(PASSWORD_PATTERN);

    /**
     * 验证邮箱
     */
    public static boolean isEmail(String email) {
        return email != null && emailRegex.matcher(email).matches();
    }

    /**
     * 验证手机号
     */
    public static boolean isPhone(String phone) {
        return phone != null && phoneRegex.matcher(phone).matches();
    }

    /**
     * 验证身份证号码
     */
    public static boolean isIdCard(String idCard) {
        return idCard != null && idCardRegex.matcher(idCard).matches();
    }

    /**
     * 验证用户名
     */
    public static boolean isUsername(String username) {
        return username != null && usernameRegex.matcher(username).matches();
    }

    /**
     * 验证密码
     */
    public static boolean isPassword(String password) {
        return password != null && passwordRegex.matcher(password).matches();
    }

    /**
     * 验证是否为数字
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * 验证是否为整数
     */
    public static boolean isInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("-?\\d+");
    }

    /**
     * 验证是否为正整数
     */
    public static boolean isPositiveInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("\\d*[1-9]\\d*$");
    }

    /**
     * 验证字符串长度
     */
    public static boolean isLengthInRange(String str, int minLength, int maxLength) {
        if (str == null) {
            return minLength <= 0;
        }
        int length = str.length();
        return length >= minLength && length <= maxLength;
    }
}