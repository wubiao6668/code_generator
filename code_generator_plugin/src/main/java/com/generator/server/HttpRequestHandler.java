package com.generator.server;

import com.generator.constants.ContentTypeConstants;
import com.generator.context.Application;
import com.generator.model.MvcInvokeMethod;
import com.generator.service.TestService;
import com.generator.util.HttpResponseBuilder;
import com.generator.util.JsonUtil;
import com.generator.util.Reflect2Utils;
import com.generator.util.UrlUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author wubiao
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Autowired
    public TestService testService;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        System.out.println(request);
        String url = request.uri();
        String contentType = request.headers().get("Content-Type");
        if (contentType.contains(";")) {
            contentType = contentType.split(";")[0];
        }
        contentType = contentType.toLowerCase();
        if (BooleanUtils.isFalse(ContentTypeConstants.APPLICATION_JSON.equals(contentType))) {
            FullHttpResponse fullHttpResponse = HttpResponseBuilder.createHttpResponse(HttpResponseStatus.SERVICE_UNAVAILABLE, "服务器不支持请求");
            ctx.writeAndFlush(fullHttpResponse);
            return;
        }
        String fullUrl = UrlUtils.fullUrlPath(url);
        MvcInvokeMethod mvcInvokeMethod = Application.webMethodMap.get(fullUrl);
        if (null == mvcInvokeMethod) {
            FullHttpResponse fullHttpResponse = HttpResponseBuilder.createHttpResponse(HttpResponseStatus.METHOD_NOT_ALLOWED, "方法不存在");
            ctx.writeAndFlush(fullHttpResponse);
            return;
        }
        Method method = mvcInvokeMethod.getMethod();
        Object bean = mvcInvokeMethod.getBean();
        Parameter[] parameters = method.getParameters();
        //参数为空情况直接返回
        if (ArrayUtils.isEmpty(parameters)) {
            Object object = method.invoke(bean);
            FullHttpResponse fullHttpResponse = HttpResponseBuilder.buildOk(object);
            ctx.writeAndFlush(fullHttpResponse);
            return;
        }
        int parametersLength = parameters.length;
        Object[] invokeParam = new Object[parametersLength];
        //参数
        String requestContent = request.content().toString(Charset.forName("UTF-8"));
        Map requestParamMap = JsonUtil.parseJson(requestContent, Map.class);
        Parameter firstParameters = parameters[0];
        //只有一个参数，且不是primitive类型
        if (parameters.length == 1 && BooleanUtils.isFalse(Reflect2Utils.isPrimitive(firstParameters.getType()))) {
            invokeParam[0] = JsonUtil.parseJson(requestContent, firstParameters.getType());
            Object object = method.invoke(bean, invokeParam);
            FullHttpResponse fullHttpResponse = HttpResponseBuilder.buildOk(object);
            ctx.writeAndFlush(fullHttpResponse);
            return;
        }
        //一个primitive类型或者多个参数
        for (int i = 0; i < parametersLength; i++) {
            Parameter parameter = parameters[i];
            Class<?> parameterClassType = parameter.getType();
            boolean isPrimitive = Reflect2Utils.isPrimitive(parameterClassType);
            String name = parameter.getName();
            Object requestParamValue = requestParamMap.get(name);
            if (isPrimitive) {
                if (null == requestParamValue) {
                    requestParamValue = Reflect2Utils.getDefaultValue(parameterClassType);
                }
                invokeParam[i] = requestParamValue;
                continue;
            }
            invokeParam[i] = JsonUtil.parseJson(JsonUtil.toJsonString(requestParamValue), parameterClassType);
        }

        Object object = method.invoke(bean, invokeParam);
        FullHttpResponse fullHttpResponse = HttpResponseBuilder.buildOk(object);
        ctx.writeAndFlush(fullHttpResponse);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
