package com.wmmx.gui;

import java.awt.*;
import java.awt.event.*;

public class gui {
    public static void main(String[] args) {
        Frame frame = new Frame("第一个窗口");
        frame.setSize(400,600);
        frame.setLocation(500,50);
        Button b1 = new Button("点一下或者按空格也可以退出");
        frame.add(b1);
        frame.setLayout(new FlowLayout());
        frame.addWindowListener(new MyWindowListener());
        //加匿名内部类
        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.exit(0);
            }
        });
        b1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                //空格退出 32 or  KeyEvent.VK_SPACE
                if (e.getKeyCode() == 32) {
                    System.exit(0);
                }
            }
        });
        Button b2 = new Button("动作");
        frame.add(b2);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }

    //重写方法
    static class MyWindowListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);

        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }
}
