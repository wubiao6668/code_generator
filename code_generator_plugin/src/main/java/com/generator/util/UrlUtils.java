package com.generator.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

/**
 * @author wubiao
 */
public class UrlUtils {


    public static String fullUrlPath(String... urls) {
        if (urls == null || urls.length <= 0) {
            return null;
        }
        List<String> list = Lists.newArrayList();
        for (String url : urls) {
            for (String urlTemp : url.split("/")) {
                if (StringUtils.isBlank(urlTemp)) {
                    continue;
                }
                list.add(urlTemp);
            }
        }
        return "/" + Joiner.on("/").join(list);
    }

    public static String fullFilePath(String... urls) {
        if (urls == null || urls.length <= 0) {
            return null;
        }
        List<String> list = Lists.newArrayList();
        for (String url : urls) {
            for (String urlTemp : url.split("/")) {
                if (StringUtils.isBlank(urlTemp)) {
                    continue;
                }
                list.add(urlTemp);
            }
        }
        return File.separator + Joiner.on(File.separator).join(list);
    }

//    public static String fullFilePath(String[]... urls) {
//
//    }

    public boolean isStaticResource(String url) {
        if (StringUtils.isBlank(url)) {
            return false;
        }
        //静态文件处理
        final String STATIC_RESOURCE_STARTS_WITH = "static";
        return url.startsWith(STATIC_RESOURCE_STARTS_WITH) || url.startsWith("/" + STATIC_RESOURCE_STARTS_WITH);
    }


    public static void main(String[] args) {
//        System.out.println(File.separator);
//        System.out.println(File.pathSeparator);
        String[] dd = {"aaa","bbb"};
        String[] cc = {"cc","dd"};
//        System.out.println(fullFilePath(dd,cc));
    }
}
