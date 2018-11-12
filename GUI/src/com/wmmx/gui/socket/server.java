package com.wmmx.gui.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        //服务器向客户端写数据
        os.write("百度一下，你就知道".getBytes());

        //服务端读数据
        byte[] arr = new byte[1024];
        int len = is.read(arr);
        System.out.println(new String(arr,0,len));

        socket.close();
    }
}
