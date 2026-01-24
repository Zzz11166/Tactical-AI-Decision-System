package com.spring.airag.common.constants;

/**
 * 国际化相关常量类
 */
public class I18nConstants {

    // 语言代码
    public static final String LANG_ZH_CN = "zh-CN"; // 中文简体
    public static final String LANG_ZH_TW = "zh-TW"; // 中文繁体
    public static final String LANG_EN_US = "en-US"; // 英文美国
    public static final String LANG_EN_GB = "en-GB"; // 英文英国
    public static final String LANG_JA_JP = "ja-JP"; // 日文
    public static final String LANG_KO_KR = "ko-KR"; // 韩文
    public static final String LANG_FR_FR = "fr-FR"; // 法文
    public static final String LANG_DE_DE = "de-DE"; // 德文
    public static final String LANG_ES_ES = "es-ES"; // 西班牙文

    // 语言描述
    public static final String LANG_DESC_ZH_CN = "中文(简体)";
    public static final String LANG_DESC_EN_US = "English(US)";
    public static final String LANG_DESC_JA_JP = "日本語";
    public static final String LANG_DESC_KO_KR = "한국어";

    // 时区
    public static final String TIMEZONE_CHINA = "Asia/Shanghai";
    public static final String TIMEZONE_USA = "America/New_York";
    public static final String TIMEZONE_UK = "Europe/London";
    public static final String TIMEZONE_JAPAN = "Asia/Tokyo";
    public static final String TIMEZONE_KOREA = "Asia/Seoul";

    // 日期格式
    public static final String DATE_FORMAT_ZH_CN = "yyyy年MM月dd日";
    public static final String DATE_FORMAT_EN_US = "MM/dd/yyyy";
    public static final String DATE_FORMAT_ISO = "yyyy-MM-dd";
    public static final String DATE_FORMAT_ISO_FULL = "yyyy-MM-dd HH:mm:ss";

    // 数字格式
    public static final String NUMBER_FORMAT_ZH_CN = "#,##0.00";
    public static final String NUMBER_FORMAT_EN_US = "#,##0.00";
    public static final String NUMBER_FORMAT_PERCENT = "0.00%";

    // 货币代码
    public static final String CURRENCY_CNY = "CNY"; // 人民币
    public static final String CURRENCY_USD = "USD"; // 美元
    public static final String CURRENCY_JPY = "JPY"; // 日元
    public static final String CURRENCY_KRW = "KRW"; // 韩元
    public static final String CURRENCY_EUR = "EUR"; // 欧元
    public static final String CURRENCY_GBP = "GBP"; // 英镑

    // 货币符号
    public static final String CURRENCY_SYMBOL_CNY = "¥";
    public static final String CURRENCY_SYMBOL_USD = "$";
    public static final String CURRENCY_SYMBOL_JPY = "¥";
    public static final String CURRENCY_SYMBOL_KRW = "₩";
    public static final String CURRENCY_SYMBOL_EUR = "€";
    public static final String CURRENCY_SYMBOL_GBP = "£";

    // 国家代码
    public static final String COUNTRY_CN = "CN";
    public static final String COUNTRY_US = "US";
    public static final String COUNTRY_JP = "JP";
    public static final String COUNTRY_KR = "KR";
    public static final String COUNTRY_FR = "FR";
    public static final String COUNTRY_DE = "DE";
    public static final String COUNTRY_ES = "ES";

    // 国家名称
    public static final String COUNTRY_NAME_CN = "中国";
    public static final String COUNTRY_NAME_US = "美国";
    public static final String COUNTRY_NAME_JP = "日本";
    public static final String COUNTRY_NAME_KR = "韩国";
    public static final String COUNTRY_NAME_FR = "法国";
    public static final String COUNTRY_NAME_DE = "德国";
    public static final String COUNTRY_NAME_ES = "西班牙";

    // 默认配置
    public static final String DEFAULT_LANGUAGE = LANG_ZH_CN;
    public static final String DEFAULT_COUNTRY = COUNTRY_CN;
    public static final String DEFAULT_TIMEZONE = TIMEZONE_CHINA;
    public static final String DEFAULT_CURRENCY = CURRENCY_CNY;

    // 国际化配置相关
    public static final String I18N_CONFIG_LOCALE_RESOLVER = "localeResolver";
    public static final String I18N_CONFIG_MESSAGE_SOURCE = "messageSource";
    public static final String I18N_CONFIG_BASENAME = "i18n/messages";
    public static final String I18N_CONFIG_ENCODING = "UTF-8";
    public static final String I18N_CONFIG_CACHE_SECONDS = "3600";

    // 国际化资源文件路径
    public static final String I18N_RESOURCE_PATH = "i18n/";
    public static final String I18N_RESOURCE_BASENAME = "messages";
    public static final String I18N_RESOURCE_SUFFIX = ".properties";

    // 语言切换相关
    public static final String I18N_PARAM_LANG = "lang";
    public static final String I18N_PARAM_LOCALE = "locale";
    public static final String I18N_HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    public static final String I18N_COOKIE_LOCALE = "locale";

    // 资源文件名
    public static final String I18N_FILE_MESSAGES = "messages";
    public static final String I18N_FILE_MESSAGES_ZH_CN = "messages_zh_CN";
    public static final String I18N_FILE_MESSAGES_EN_US = "messages_en_US";
    public static final String I18N_FILE_VALIDATION = "validation";
    public static final String I18N_FILE_ERROR = "errors";
    public static final String I18N_FILE_LABEL = "labels";

    // 国际化键值前缀
    public static final String I18N_KEY_PREFIX_MESSAGE = "msg.";
    public static final String I18N_KEY_PREFIX_BUTTON = "btn.";
    public static final String I18N_KEY_PREFIX_LABEL = "label.";
    public static final String I18N_KEY_PREFIX_TITLE = "title.";
    public static final String I18N_KEY_PREFIX_ERROR = "error.";
    public static final String I18N_KEY_PREFIX_VALIDATION = "validation.";
    public static final String I18N_KEY_PREFIX_PLACEHOLDER = "placeholder.";

    // 语言切换选项
    public static final String[] SUPPORTED_LANGUAGES = {
        LANG_ZH_CN, LANG_EN_US, LANG_JA_JP, LANG_KO_KR
    };

    // 语言方向
    public static final String LANG_DIRECTION_LTR = "ltr"; // 从左到右
    public static final String LANG_DIRECTION_RTL = "rtl"; // 从右到左

    // 国际化上下文相关
    public static final String I18N_CONTEXT_LOCALE = "locale";
    public static final String I18N_CONTEXT_COUNTRY = "country";
    public static final String I18N_CONTEXT_TIMEZONE = "timezone";
    public static final String I18N_CONTEXT_CURRENCY = "currency";

    // 国际化错误码
    public static final String I18N_ERROR_CODE_LANGUAGE_NOT_SUPPORTED = "LANG_NOT_SUPPORTED";
    public static final String I18N_ERROR_CODE_TRANSLATION_NOT_FOUND = "TRANS_NOT_FOUND";
    public static final String I18N_ERROR_CODE_INVALID_LOCALE = "INVALID_LOCALE";
}