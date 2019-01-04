package com.generator.constants;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author wubiao
 */
public class ContentTypeConstants {
    public final static String APPLICATION_JSON = "application/json";
    public final static String APPLICATION_JSON_UTF8 = "application/json;charset=utf-8";
    public final static String TEXT_HTML = "text/html;charset=utf-8";
    public final static String TEXT_XML = "text/xml";

    //文件类型返回值
    public final static String JAVASCRIPT = "application/x-javascript";
    public final static String TEXT_CSS = "text/css";
    public final static String IMAGE_BMP = "image/bmp";
    public final static String IMAGE_WEBP = "image/webp";
    public final static String IMAGE_JPG = "image/jpg";
    public final static String IMAGE_PNG = "image/png";
    public final static String IMAGE_TIF = "image/tif";
    public final static String IMAGE_GIF = "image/gif";
    public final static String IMAGE_IOC = "image/ioc";


    static Map<String, String> contentTypeMap = Maps.newHashMap();

    static {
        contentTypeMap.put("html", TEXT_HTML);
        contentTypeMap.put("js", JAVASCRIPT);
        contentTypeMap.put("css", TEXT_CSS);
        contentTypeMap.put("bmp", IMAGE_BMP);
        contentTypeMap.put("webp", IMAGE_WEBP);
        contentTypeMap.put("jpg", IMAGE_JPG);
        contentTypeMap.put("png", IMAGE_PNG);
        contentTypeMap.put("tif", IMAGE_TIF);
        contentTypeMap.put("gif", IMAGE_GIF);
        contentTypeMap.put("ioc", IMAGE_IOC);
    }

    public static String getContentTypeByFileExtend(String fileType) {
        String contentType = contentTypeMap.get(fileType);
        if (StringUtils.isNotBlank(contentType)){
            return contentType;
        }
        return TEXT_HTML;
    }
}
