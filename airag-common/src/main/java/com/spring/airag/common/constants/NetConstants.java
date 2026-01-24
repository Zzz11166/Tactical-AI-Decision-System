package com.spring.airag.common.constants;

/**
 * 网络相关常量类
 */
public class NetConstants {

    // 协议类型
    public static final String PROTOCOL_HTTP = "HTTP";
    public static final String PROTOCOL_HTTPS = "HTTPS";
    public static final String PROTOCOL_TCP = "TCP";
    public static final String PROTOCOL_UDP = "UDP";
    public static final String PROTOCOL_FTP = "FTP";
    public static final String PROTOCOL_SFTP = "SFTP";
    public static final String PROTOCOL_SMTP = "SMTP";
    public static final String PROTOCOL_POP3 = "POP3";
    public static final String PROTOCOL_IMAP = "IMAP";

    // HTTP方法
    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";
    public static final String HTTP_PUT = "PUT";
    public static final String HTTP_DELETE = "DELETE";
    public static final String HTTP_PATCH = "PATCH";
    public static final String HTTP_HEAD = "HEAD";
    public static final String HTTP_OPTIONS = "OPTIONS";
    public static final String HTTP_TRACE = "TRACE";

    // HTTP状态码
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_CREATED = 201;
    public static final int HTTP_STATUS_ACCEPTED = 202;
    public static final int HTTP_STATUS_NO_CONTENT = 204;
    public static final int HTTP_STATUS_MOVED_PERMANENTLY = 301;
    public static final int HTTP_STATUS_FOUND = 302;
    public static final int HTTP_STATUS_NOT_MODIFIED = 304;
    public static final int HTTP_STATUS_BAD_REQUEST = 400;
    public static final int HTTP_STATUS_UNAUTHORIZED = 401;
    public static final int HTTP_STATUS_FORBIDDEN = 403;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    public static final int HTTP_STATUS_METHOD_NOT_ALLOWED = 405;
    public static final int HTTP_STATUS_REQUEST_TIMEOUT = 408;
    public static final int HTTP_STATUS_TOO_MANY_REQUESTS = 429;
    public static final int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;
    public static final int HTTP_STATUS_BAD_GATEWAY = 502;
    public static final int HTTP_STATUS_SERVICE_UNAVAILABLE = 503;
    public static final int HTTP_STATUS_GATEWAY_TIMEOUT = 504;

    // 端口号
    public static final int PORT_HTTP = 80;
    public static final int PORT_HTTPS = 443;
    public static final int PORT_FTP = 21;
    public static final int PORT_SSH = 22;
    public static final int PORT_TELNET = 23;
    public static final int PORT_SMTP = 25;
    public static final int PORT_DNS = 53;
    public static final int PORT_DHCP = 67;
    public static final int PORT_POP3 = 110;
    public static final int PORT_IMAP = 143;
    public static final int PORT_HTTP_PROXY = 8080;
    public static final int PORT_HTTPS_PROXY = 8443;

    // IP地址类型
    public static final String IP_TYPE_IPV4 = "IPv4";
    public static final String IP_TYPE_IPV6 = "IPv6";
    public static final String IP_TYPE_PRIVATE = "PRIVATE";
    public static final String IP_TYPE_PUBLIC = "PUBLIC";
    public static final String IP_TYPE_LOOPBACK = "LOOPBACK";

    // IP地址范围
    public static final String IP_RANGE_CLASS_A = "10.0.0.0/8";
    public static final String IP_RANGE_CLASS_B = "172.16.0.0/12";
    public static final String IP_RANGE_CLASS_C = "192.168.0.0/16";
    public static final String IP_RANGE_LOOPBACK = "127.0.0.0/8";
    public static final String IP_RANGE_LINK_LOCAL = "169.254.0.0/16";

    // 网络接口类型
    public static final String INTERFACE_LOOPBACK = "lo";
    public static final String INTERFACE_ETHERNET = "eth";
    public static final String INTERFACE_WIFI = "wlan";
    public static final String INTERFACE_USB = "usb";

    // 网络状态
    public static final String NET_STATUS_UP = "UP";
    public static final String NET_STATUS_DOWN = "DOWN";
    public static final String NET_STATUS_UNKNOWN = "UNKNOWN";

    // 网络配置相关
    public static final String NET_CONFIG_DHCP = "DHCP";
    public static final String NET_CONFIG_STATIC = "STATIC";
    public static final String NET_CONFIG_AUTO = "AUTO";

    // 网络协议族
    public static final String PROTOCOL_FAMILY_INET = "INET";
    public static final String PROTOCOL_FAMILY_INET6 = "INET6";
    public static final String PROTOCOL_FAMILY_UNSPEC = "UNSPEC";

    // Socket相关
    public static final int SOCKET_TIMEOUT_DEFAULT = 30000; // 30秒
    public static final int SOCKET_TIMEOUT_MIN = 1000;     // 1秒
    public static final int SOCKET_TIMEOUT_MAX = 300000;   // 5分钟
    public static final int SOCKET_BUFFER_SIZE_DEFAULT = 8192; // 8KB
    public static final int SOCKET_BACKLOG_DEFAULT = 50;

    // 网络连接相关
    public static final int CONNECTION_TIMEOUT_DEFAULT = 10000; // 10秒
    public static final int CONNECTION_TIMEOUT_MIN = 1000;     // 1秒
    public static final int CONNECTION_TIMEOUT_MAX = 60000;    // 1分钟
    public static final int CONNECTION_POOL_SIZE_DEFAULT = 20;
    public static final int CONNECTION_POOL_SIZE_MAX = 100;

    // DNS相关
    public static final String DNS_SERVER_GOOGLE = "8.8.8.8";
    public static final String DNS_SERVER_GOOGLE_V6 = "2001:4860:4860::8888";
    public static final String DNS_SERVER_CLOUDFLARE = "1.1.1.1";
    public static final String DNS_SERVER_ALIDNS = "223.5.5.5";
    public static final String DNS_SERVER_TENCENTDNS = "119.29.29.29";

    // 代理相关
    public static final String PROXY_TYPE_HTTP = "HTTP";
    public static final String PROXY_TYPE_SOCKS = "SOCKS";
    public static final String PROXY_TYPE_SOCKS4 = "SOCKS4";
    public static final String PROXY_TYPE_SOCKS5 = "SOCKS5";

    // 网络安全相关
    public static final String SSL_PROTOCOL_TLS = "TLS";
    public static final String SSL_PROTOCOL_TLS1_2 = "TLSv1.2";
    public static final String SSL_PROTOCOL_TLS1_3 = "TLSv1.3";
    public static final String SSL_PROTOCOL_SSL = "SSL";
    public static final String SSL_PROTOCOL_SSL3 = "SSLv3";

    // 网络缓冲区大小
    public static final int BUFFER_SIZE_SMALL = 1024;      // 1KB
    public static final int BUFFER_SIZE_MEDIUM = 8192;    // 8KB
    public static final int BUFFER_SIZE_LARGE = 65536;    // 64KB
    public static final int BUFFER_SIZE_X_LARGE = 262144; // 256KB

    // 网络速率单位
    public static final long SPEED_BPS = 1L;              // bps
    public static final long SPEED_KBPS = 1000L * SPEED_BPS; // Kbps
    public static final long SPEED_MBPS = 1000L * SPEED_KBPS; // Mbps
    public static final long SPEED_GBPS = 1000L * SPEED_MBPS; // Gbps

    // 网络延迟相关
    public static final int LATENCY_EXCELLENT = 10;     // 优秀 < 10ms
    public static final int LATENCY_GOOD = 50;         // 良好 < 50ms
    public static final int LATENCY_FAIR = 100;        // 一般 < 100ms
    public static final int LATENCY_POOR = 200;        // 较差 < 200ms
    public static final int LATENCY_VERY_POOR = 500;   // 很差 >= 500ms

    // 网络包大小
    public static final int MTU_ETH_DEFAULT = 1500;     // 以太网MTU
    public static final int MTU_WIFI_DEFAULT = 1500;    // WiFi MTU
    public static final int MTU_PPPOE_DEFAULT = 1492;   // PPPoE MTU
    public static final int MTU_TUNNEL_DEFAULT = 1472;  // 隧道MTU

    // 网络重试相关
    public static final int RETRY_COUNT_DEFAULT = 3;
    public static final int RETRY_COUNT_MAX = 10;
    public static final long RETRY_INTERVAL_DEFAULT = 1000L; // 1秒
    public static final long RETRY_INTERVAL_MAX = 30000L;    // 30秒

    // 网络错误类型
    public static final String ERROR_TYPE_TIMEOUT = "TIMEOUT";
    public static final String ERROR_TYPE_CONNECTION_REFUSED = "CONNECTION_REFUSED";
    public static final String ERROR_TYPE_HOST_UNREACHABLE = "HOST_UNREACHABLE";
    public static final String ERROR_TYPE_NETWORK_UNREACHABLE = "NETWORK_UNREACHABLE";
    public static final String ERROR_TYPE_SOCKET_EXCEPTION = "SOCKET_EXCEPTION";
    public static final String ERROR_TYPE_IO_EXCEPTION = "IO_EXCEPTION";

    // 网络请求头相关
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_CONTENT_LENGTH = "Content-Length";
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_COOKIE = "Cookie";
    public static final String HEADER_REFERER = "Referer";
    public static final String HEADER_ORIGIN = "Origin";
    public static final String HEADER_HOST = "Host";
    public static final String HEADER_CONNECTION = "Connection";
    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";

    // 网络内容类型
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_XML = "application/xml";
    public static final String CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded";
    public static final String CONTENT_TYPE_MULTIPART = "multipart/form-data";
    public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";
    public static final String CONTENT_TYPE_TEXT_HTML = "text/html";
    public static final String CONTENT_TYPE_IMAGE_JPEG = "image/jpeg";
    public static final String CONTENT_TYPE_IMAGE_PNG = "image/png";
}