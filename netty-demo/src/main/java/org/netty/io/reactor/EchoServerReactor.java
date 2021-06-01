package org.netty.io.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoServerReactor implements Runnable {

    Selector selector;

    ServerSocketChannel socketChannel;

    public EchoServerReactor() throws IOException {
        selector = Selector.open();
        InetSocketAddress address = new InetSocketAddress(8088);
        socketChannel.bind(address);
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            key.attach(new AcceptorHandler());
        }
    }


    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey selectionKey = keyIterator.next();
                    dispatch(selectionKey);
                }
                selectionKeys.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatch(SelectionKey selectionKey) {

        Runnable runnable = (Runnable) selectionKey.attachment();
        runnable.run();
    }


    class AcceptorHandler implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel channel = socketChannel.accept();
                if (channel != null){
                    new EchoServerHandler(selector,channel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
