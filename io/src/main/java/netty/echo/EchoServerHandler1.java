/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

/**
 * Handler implementation for the echo server.
 */
@Sharable
public class EchoServerHandler1 implements ChannelHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(msg);
        // 读取msg中的数据
        ByteBuf result = (ByteBuf) msg;
        byte[] bytes = new byte[result.readableBytes()];
        result.readBytes(bytes);
        String resultStr = new String(bytes);
        System.out.println("handle read 1: " + resultStr);
//        resultStr = "add head one " + resultStr;


        ByteBuf newResult = Unpooled.copiedBuffer("<read 1 head>" + resultStr + "</read 1 head>", CharsetUtil.UTF_8);

        ctx.fireChannelRead(newResult);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("handle complete 1: ");

        ctx.fireChannelReadComplete();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //非最后的handler 只是打印，统一由最后的handler关闭channel
        cause.printStackTrace();
//        ctx.close();
    }
}
