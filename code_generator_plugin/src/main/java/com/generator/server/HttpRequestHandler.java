package com.generator.server;

import com.generator.constants.ContentTypeConstants;
import com.generator.context.Application;
import com.generator.model.MvcInvokeMethod;
import com.generator.service.TestService;
import com.generator.util.HttpResponseBuilder;
import com.generator.util.UrlUtils;
import com.google.common.collect.Lists;
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
        //暂时不支持url取参数
//        QueryStringDecoder queryStrdecoder = new QueryStringDecoder(url);
//        Set<Map.Entry<String, List<String>>> entrySet = queryStrdecoder.parameters().entrySet();
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
        //参数json
        String requestContent = request.content().toString(Charset.forName("UTF-8"));
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
        Object[] requestParam = new Object[parametersLength];
        for (int i = 0; i < parametersLength; i++) {
            Parameter parameter = parameters[i];
            Class<?> parameterClassType = parameter.getType();
            boolean isPrimitive = parameterClassType.isPrimitive();

            System.out.println(isPrimitive);
        }
        FullHttpResponse fullHttpResponse = HttpResponseBuilder.buildOk(Lists.newArrayList(1, 2, 3, 4, 5));

        ctx.writeAndFlush(fullHttpResponse);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
