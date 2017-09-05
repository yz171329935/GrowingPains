package com.marcoyao.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * Created by marcoyao on 2017/8/4.
 */
public class Test {

    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");

        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        System.out.println((char)buf.readByte());

        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();

        buf.writeByte((byte)'?');

        assert readerIndex == buf.readerIndex();

        assert writerIndex != buf.writerIndex();
    }
}
