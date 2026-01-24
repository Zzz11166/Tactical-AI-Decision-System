package com.spring.airag.common.constants;

/**
 * 文件相关常量类
 */
public class FileConstants {

    // 文件大小单位
    public static final long KB = 1024L;
    public static final long MB = 1024 * KB;
    public static final long GB = 1024 * MB;
    public static final long TB = 1024 * GB;

    // 文件类型分类
    public static final String FILE_TYPE_IMAGE = "image";
    public static final String FILE_TYPE_DOCUMENT = "document";
    public static final String FILE_TYPE_VIDEO = "video";
    public static final String FILE_TYPE_AUDIO = "audio";
    public static final String FILE_TYPE_ARCHIVE = "archive";
    public static final String FILE_TYPE_OTHER = "other";

    // 图片文件扩展名
    public static final String[] IMAGE_EXTENSIONS = {
        "jpg", "jpeg", "png", "gif", "bmp", "webp", "svg", "ico", "tiff", "raw"
    };

    // 文档文件扩展名
    public static final String[] DOCUMENT_EXTENSIONS = {
        "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "rtf", "odt", "ods", "odp"
    };

    // 视频文件扩展名
    public static final String[] VIDEO_EXTENSIONS = {
        "mp4", "avi", "mov", "wmv", "flv", "mkv", "webm", "m4v", "3gp", "mpeg"
    };

    // 音频文件扩展名
    public static final String[] AUDIO_EXTENSIONS = {
        "mp3", "wav", "flac", "aac", "ogg", "wma", "m4a", "opus"
    };

    // 压缩文件扩展名
    public static final String[] ARCHIVE_EXTENSIONS = {
        "zip", "rar", "7z", "tar", "gz", "bz2", "xz", "iso", "jar", "war"
    };

    // 可执行文件扩展名
    public static final String[] EXECUTABLE_EXTENSIONS = {
        "exe", "bat", "sh", "msi", "app", "bin", "com", "scr"
    };

    // 危险文件扩展名
    public static final String[] DANGEROUS_EXTENSIONS = {
        "exe", "bat", "sh", "msi", "scr", "vbs", "js", "ps1", "cmd", "com"
    };

    // 文本文件扩展名
    public static final String[] TEXT_EXTENSIONS = {
        "txt", "log", "csv", "xml", "json", "html", "htm", "md", "yaml", "yml", "ini", "cfg", "conf"
    };

    // 文件大小限制
    public static final long FILE_SIZE_1KB = 1 * KB;
    public static final long FILE_SIZE_10KB = 10 * KB;
    public static final long FILE_SIZE_100KB = 100 * KB;
    public static final long FILE_SIZE_1MB = 1 * MB;
    public static final long FILE_SIZE_10MB = 10 * MB;
    public static final long FILE_SIZE_50MB = 50 * MB;
    public static final long FILE_SIZE_100MB = 100 * MB;
    public static final long FILE_SIZE_500MB = 500 * MB;
    public static final long FILE_SIZE_1GB = 1 * GB;

    // 默认文件大小限制
    public static final long DEFAULT_FILE_SIZE_LIMIT = FILE_SIZE_10MB;
//    public static final long DEFAULT_IMAGE_SIZE_LIMIT = FILE_SIZE_5MB;
    public static final long DEFAULT_DOCUMENT_SIZE_LIMIT = FILE_SIZE_10MB;
    public static final long DEFAULT_VIDEO_SIZE_LIMIT = FILE_SIZE_100MB;

    // 文件路径分隔符
    public static final String PATH_SEPARATOR_UNIX = "/";
    public static final String PATH_SEPARATOR_WINDOWS = "\\";
    public static final String PATH_SEPARATOR = System.getProperty("file.separator");

    // 临时文件相关
    public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
    public static final String TEMP_FILE_PREFIX = "temp_";
    public static final String TEMP_FILE_SUFFIX = ".tmp";

    // 文件编码
    public static final String ENCODING_UTF8 = "UTF-8";
    public static final String ENCODING_GBK = "GBK";
    public static final String ENCODING_GB2312 = "GB2312";
    public static final String ENCODING_ISO8859_1 = "ISO-8859-1";

    // MIME类型
    public static final String MIME_TYPE_IMAGE_JPEG = "image/jpeg";
    public static final String MIME_TYPE_IMAGE_PNG = "image/png";
    public static final String MIME_TYPE_IMAGE_GIF = "image/gif";
    public static final String MIME_TYPE_IMAGE_BMP = "image/bmp";
    public static final String MIME_TYPE_IMAGE_SVG = "image/svg+xml";
    public static final String MIME_TYPE_APPLICATION_PDF = "application/pdf";
    public static final String MIME_TYPE_APPLICATION_MSWORD = "application/msword";
    public static final String MIME_TYPE_APPLICATION_MSEXCEL = "application/vnd.ms-excel";
    public static final String MIME_TYPE_APPLICATION_MSPOWERPOINT = "application/vnd.ms-powerpoint";
    public static final String MIME_TYPE_TEXT_PLAIN = "text/plain";
    public static final String MIME_TYPE_TEXT_HTML = "text/html";
    public static final String MIME_TYPE_APPLICATION_JSON = "application/json";
    public static final String MIME_TYPE_APPLICATION_XML = "application/xml";
    public static final String MIME_TYPE_APPLICATION_ZIP = "application/zip";
    public static final String MIME_TYPE_APPLICATION_RAR = "application/x-rar-compressed";
    public static final String MIME_TYPE_APPLICATION_JAVA_ARCHIVE = "application/java-archive";

    // 文件上传相关
    public static final String UPLOAD_DIR_DEFAULT = "uploads";
    public static final String UPLOAD_DIR_TEMP = "temp";
    public static final String UPLOAD_DIR_AVATAR = "avatar";
    public static final String UPLOAD_DIR_DOCUMENT = "document";
    public static final String UPLOAD_DIR_IMAGE = "image";

    // 文件下载相关
    public static final String DOWNLOAD_HEADER_ATTACHMENT = "attachment";
    public static final String DOWNLOAD_HEADER_INLINE = "inline";
    public static final String DOWNLOAD_PARAM_FILENAME = "filename";

    // 文件操作相关
    public static final String FILE_OPERATION_READ = "read";
    public static final String FILE_OPERATION_WRITE = "write";
    public static final String FILE_OPERATION_DELETE = "delete";
    public static final String FILE_OPERATION_COPY = "copy";
    public static final String FILE_OPERATION_MOVE = "move";

    // 文件权限相关
    public static final String FILE_PERMISSION_READ = "r";
    public static final String FILE_PERMISSION_WRITE = "w";
    public static final String FILE_PERMISSION_EXECUTE = "x";

    // 文件状态
    public static final String FILE_STATUS_NORMAL = "NORMAL";
    public static final String FILE_STATUS_ENCRYPTED = "ENCRYPTED";
    public static final String FILE_STATUS_COMPRESSED = "COMPRESSED";
    public static final String FILE_STATUS_CORRUPTED = "CORRUPTED";
    public static final String FILE_STATUS_VIRUS_INFECTED = "VIRUS_INFECTED";

    // 文件处理状态
    public static final String FILE_PROCESS_STATUS_PENDING = "PENDING";
    public static final String FILE_PROCESS_STATUS_PROCESSING = "PROCESSING";
    public static final String FILE_PROCESS_STATUS_COMPLETED = "COMPLETED";
    public static final String FILE_PROCESS_STATUS_FAILED = "FAILED";
    public static final String FILE_PROCESS_STATUS_CANCELLED = "CANCELLED";

    // 文件存储类型
    public static final String STORAGE_TYPE_LOCAL = "LOCAL";
    public static final String STORAGE_TYPE_REMOTE = "REMOTE";
    public static final String STORAGE_TYPE_CLOUD = "CLOUD";
    public static final String STORAGE_TYPE_FTP = "FTP";
    public static final String STORAGE_TYPE_S3 = "S3";
    public static final String STORAGE_TYPE_MINIO = "MINIO";

    // 文件压缩相关
    public static final String COMPRESS_TYPE_ZIP = "ZIP";
    public static final String COMPRESS_TYPE_GZIP = "GZIP";
    public static final String COMPRESS_TYPE_TAR = "TAR";
    public static final String COMPRESS_TYPE_SEVEN_ZIP = "7Z";

    // 文件加密相关
    public static final String ENCRYPT_ALGORITHM_AES = "AES";
    public static final String ENCRYPT_ALGORITHM_DES = "DES";
    public static final String ENCRYPT_ALGORITHM_RSA = "RSA";

    // 文件哈希算法
    public static final String HASH_ALGORITHM_MD5 = "MD5";
    public static final String HASH_ALGORITHM_SHA1 = "SHA-1";
    public static final String HASH_ALGORITHM_SHA256 = "SHA-256";
}