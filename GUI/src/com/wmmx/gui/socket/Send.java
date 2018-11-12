package com.wmmx.gui.socket;

import java.io.IOException;
import java.net.*;

public class Send {

    public static void main(String[] args) throws IOException {
        String str = "giving a test";
        //创建socket 码头
        DatagramSocket socket = new DatagramSocket();
        //创建packet 集装箱
        DatagramPacket packet = new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName("127.0.0.1"), 6666);
        //发货
        socket.send(packet);
        socket.close();
    }

}
