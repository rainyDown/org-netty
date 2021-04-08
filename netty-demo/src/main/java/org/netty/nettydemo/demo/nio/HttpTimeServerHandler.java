package org.netty.nettydemo.demo.nio;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.HashMap;
import java.util.Map;

public class HttpTimeServerHandler extends SimpleChannelInboundHandler {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("=======> read msg");
        if (o instanceof FullHttpRequest) {
            handlerHttpMessage(channelHandlerContext, (FullHttpRequest) o);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private void handlerHttpMessage(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) {
        String uri = request.uri();
        HttpMethod method = request.method();
        Map<String, String> res = new HashMap<>();
        res.put("uri", uri);
        res.put("method", method.name());
        String msg = "<html><head><title>test</title></head><body>你请求uri为：" + uri + "</body></html>";
        //创建response
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        channelHandlerContext.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
