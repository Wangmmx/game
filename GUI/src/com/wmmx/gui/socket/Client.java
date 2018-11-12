package com.wmmx.gui.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 5555);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //客户端读服务器的数据
        byte[] arr = new byte[1024];
        int len = is.read(arr);
        System.out.println(new String(arr,0,len));
        //客户端向服务器写数据
        os.write("王梦大好人".getBytes());

        socket.close();
    }
}
