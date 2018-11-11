package com.wmmx.gui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class gui {
    public static void main(String[] args) {
        Frame frame = new Frame("第一个窗口");
        frame.setSize(400,600);
        frame.setLocation(500,50);
        Button b1 = new Button("按一下");
        frame.add(b1);
        frame.setLayout(new FlowLayout());
        frame.addWindowListener(new MyWindowListener());
        frame.setVisible(true);
    }

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
