package com.generator.util;

import com.generator.constants.ContentTypeConstants;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;


public class HttpResponseBuilder {


    public static FullHttpResponse buildText(Object _content) {
        return createHttpResponse(HttpResponseStatus.OK, _content, ContentTypeConstants.TEXT_HTML);
    }

    public static FullHttpResponse builNotFound(Object _content) {
        return createHttpResponse(HttpResponseStatus.NOT_FOUND, _content, ContentTypeConstants.TEXT_HTML);
    }

    public static FullHttpResponse buildText(HttpResponseStatus _status, Object _content) {
        return createHttpResponse(HttpResponseStatus.OK, _content, ContentTypeConstants.TEXT_HTML);
    }

    public static FullHttpResponse buildOk(Object _content) {
        return createHttpResponse(HttpResponseStatus.OK, _content, ContentTypeConstants.APPLICATION_JSON_UTF8);
    }

    public static FullHttpResponse buildOk(Object _content, String contentType) {
        return createHttpResponse(HttpResponseStatus.OK, _content, contentType);
    }

    public static FullHttpResponse buildOkMessage(String _msg) {
        return createHttpResponse(HttpResponseStatus.OK, _msg, ContentTypeConstants.APPLICATION_JSON_UTF8);
    }

    public static FullHttpResponse buildOkMessage(String _msg, String contentType) {
        return createHttpResponse(HttpResponseStatus.OK, _msg, contentType);
    }

    public static FullHttpResponse buildOkJson(Map<String, Object> data) {
        return createHttpResponse(HttpResponseStatus.OK, data, ContentTypeConstants.APPLICATION_JSON_UTF8);
    }

    public static FullHttpResponse buildOkJson(Map<String, Object> data, String contentType) {
        return createHttpResponse(HttpResponseStatus.OK, data, contentType);
    }

    public static FullHttpResponse buildServerError(String _msg) {
        return createHttpResponse(HttpResponseStatus.INTERNAL_SERVER_ERROR, _msg, ContentTypeConstants.APPLICATION_JSON_UTF8);
    }

    public static FullHttpResponse buildServerError(String _msg, String contentType) {
        return createHttpResponse(HttpResponseStatus.INTERNAL_SERVER_ERROR, _msg, contentType);
    }

    public static FullHttpResponse buildBadRequest(String _msg) {
        return createHttpResponse(HttpResponseStatus.BAD_REQUEST, _msg, ContentTypeConstants.APPLICATION_JSON_UTF8);
    }

    public static FullHttpResponse buildBadRequest(String _msg, String contentType) {
        return createHttpResponse(HttpResponseStatus.BAD_REQUEST, _msg, contentType);
    }

    public static FullHttpResponse buildNotImplemented(String _msg) {
        return createHttpResponse(HttpResponseStatus.NOT_IMPLEMENTED, _msg, ContentTypeConstants.APPLICATION_JSON_UTF8);
    }

    public static FullHttpResponse buildNotImplemented(String _msg, String contentType) {
        return createHttpResponse(HttpResponseStatus.NOT_IMPLEMENTED, _msg, contentType);
    }

    public static FullHttpResponse buildUnauthorized(String _msg) {
        return createHttpResponse(HttpResponseStatus.UNAUTHORIZED, _msg, ContentTypeConstants.APPLICATION_JSON_UTF8);
    }

    public static FullHttpResponse buildUnauthorized(String _msg, String contentType) {
        return createHttpResponse(HttpResponseStatus.UNAUTHORIZED, _msg, contentType);
    }

    public static FullHttpResponse createHttpResponse(HttpResponseStatus _status, Object _content) {
        return createHttpResponse(_status, _content, ContentTypeConstants.APPLICATION_JSON_UTF8);
    }

    private static FullHttpResponse createHttpResponse(HttpResponseStatus _status, Object _content, String contentType) {
        String contentStr = null;
        if (_content instanceof String) {
            contentStr = (String) _content;
        } else if (_content != null) {
            StringWriter strWriter = new StringWriter();
            try {
                JsonUtil.jsonMapper.writeValue(strWriter, _content);
                contentStr = strWriter.toString();
            } catch (Exception e) {
                e.printStackTrace(new PrintWriter(strWriter));
                contentStr = strWriter.toString();
            }
        }
        FullHttpResponse response;
        if (_content == null) {
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, _status);
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, 0);
        } else {
            byte[] contentBuf = contentStr.getBytes();

            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, _status, Unpooled.copiedBuffer(contentBuf));
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, contentBuf.length);
        }
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, contentType);
//		FullHttpRequest request = RequestContext.get(RequestContext.Key.ORIGINAL_REQUEST);
//		if (HttpUtil.isKeepAlive(request)) {
//			response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
//		}
        return response;
    }
}
