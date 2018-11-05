package com.wmmx;

import java.util.ArrayList;
import java.util.Collections;

public class main {
    /**
     * 模拟斗地主洗牌发牌
     * 1.集合对象 扑克牌
     * 2.洗牌
     * 3.发牌
     * 4.看牌
     */

    public static void main(String[] args) {
        //存储扑克
        String[] num = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] color = {"♥️","♠️","♦️","♣️"};
        ArrayList<String> poker = new ArrayList<>();

        //拼接花色数字
        for (String s1 : color) {
            for (String s2 : num) {
                poker.add(s1.concat(s2));
            }
        }
        poker.add("大王");
        poker.add("小王");
        //System.out.println(poker);

        //洗牌
        Collections.shuffle(poker);
        //System.out.println(poker);

        //发牌
        ArrayList<String> p1 = new ArrayList<>();
        ArrayList<String> p2 = new ArrayList<>();
        ArrayList<String> p3 = new ArrayList<>();
        ArrayList<String> buttom = new ArrayList<>();

        for (int i = 0; i< poker.size(); i++) {
            if (i >= poker.size()-3) {
                buttom.add(poker.get(i));
            } else {
                if (i%3 ==0) {
                    p1.add(poker.get(i));
                } else if (i%3 ==1) {
                    p2.add(poker.get(i));
                } else {
                    p3.add(poker.get(i));
                }
            }
        }
        System.out.println(buttom);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);


    }



}
