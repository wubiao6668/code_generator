package com.generator.server;

import com.generator.service.TestService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        QueryStringDecoder queryStrdecoder = new QueryStringDecoder(request.uri());
        Set<Map.Entry<String, List<String>>> entrySet = queryStrdecoder.parameters().entrySet();
        Set<String> headerNames = request.headers().names();
        request.content().toString(Charset.forName("UTF-8"));


    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
