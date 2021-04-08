package org.netty.nettydemo.demo.nio;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author rainy
 */
public class TimeServerHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("http-codec", new HttpServerCodec());
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        pipeline.addLast(new HttpTimeServerHandler());
    }
}
