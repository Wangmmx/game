package text;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 玩家类 
 * @author 莫言情难忘
 */
public class Player extends Thread implements Gameconfig {


	// 判断角色移动
	static boolean left = false;
	static boolean right = false;
	//角色坐标
	static int py_x = 50;
	static int py_y = 346;
	static boolean firstda = true;
	// 角色的朝向 1,2分别代表左右(用来处理角色不移动时的朝向问题)
	static int towards = 2;//默认朝向右
/**
 * 线程
 */
	@Override
	public void run() {
		while (true) {
			moveLR();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 角色左右移动的方法
	 */
	public void moveLR() {
		if (left) {
			towards = 1;
			if (py_x > 0 ) {
				py_x -= 10;
			}
		} else if (right) {
			towards = 2;
			if (py_x < 900) {
				py_x += 10;
			}
		}
	}

	public static void draw(Graphics g) {
		// 如果角色不在移动中
		if (!left && !right) {
			if (towards == 1) {// 最后移动朝向左
				g.drawImage(k_zou_right,py_x,py_y,244,424,null);
			} else if (towards == 2) {// 最后移动朝向右
				g.drawImage(k_zou_right,py_x,py_y,244,424,null);
			}
		} else {// 如果角色在移动中
			if (left) {
				g.drawImage(k_pao_right,py_x,py_y,300,424,null);
			} else if (right) {
				g.drawImage(k_pao_right,py_x,py_y,300,424,null);
			}
		}
	}
}