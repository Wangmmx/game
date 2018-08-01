package text;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * ����� 
 * @author Ī��������
 */
public class Player extends Thread implements Gameconfig {


	// �жϽ�ɫ�ƶ�
	static boolean left = false;
	static boolean right = false;
	//��ɫ����
	static int py_x = 50;
	static int py_y = 346;
	static boolean firstda = true;
	// ��ɫ�ĳ��� 1,2�ֱ��������(���������ɫ���ƶ�ʱ�ĳ�������)
	static int towards = 2;//Ĭ�ϳ�����
/**
 * �߳�
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
	 * ��ɫ�����ƶ��ķ���
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
		// �����ɫ�����ƶ���
		if (!left && !right) {
			if (towards == 1) {// ����ƶ�������
				g.drawImage(k_zou_right,py_x,py_y,244,424,null);
			} else if (towards == 2) {// ����ƶ�������
				g.drawImage(k_zou_right,py_x,py_y,244,424,null);
			}
		} else {// �����ɫ���ƶ���
			if (left) {
				g.drawImage(k_pao_right,py_x,py_y,300,424,null);
			} else if (right) {
				g.drawImage(k_pao_right,py_x,py_y,300,424,null);
			}
		}
	}
}