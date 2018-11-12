package com.wmmx.gui.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Receive {

    public static void main(String[] args) throws IOException {
        //创建socket 码头
        DatagramSocket socket = new DatagramSocket(6666);
        //创建packet 集装箱
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        //接受
        socket.receive(packet);
        socket.close();
        System.out.println("地址"+packet.getAddress()+ "有效字节数据"+packet.getLength()+"端口"+packet.getPort());
    }

}
