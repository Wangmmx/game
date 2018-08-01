package text;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ��Ϸ����
 * 
 * @author Ī�������� QQ:1179307527
 *
 */
public class Gamewindow extends JFrame implements Gameconfig {
	/**
	 * ������������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Gamewindow();
	}

	// ��Ϸ���
	JPanel panel;

	public Gamewindow() {
		setTitle("2D����Ϸ");// ���ñ���
		setSize(length, width);// ���ô�С
		setLayout(new FlowLayout());// ���ò���
		setDefaultCloseOperation(3);// ���ô���رղ���
		setLocationRelativeTo(null);// ���ô������
		setResizable(false);// �������

		// ������Ϸ���
		panel = setpanel();

		add(panel);
		setVisible(true);// ��ʾ����

		// ��װ���̼�����
		PanelListenner plis = new PanelListenner();
		addKeyListener(plis);

		// ���������ƶ��߳�
		Player player = new Player();
		player.start();

		// ����ˢ������߳�
		UpdateThread ut = new UpdateThread(panel);
		ut.start();
	}

	/**
	 * ������Ϸ���
	 */
	public JPanel setpanel() {
		JPanel panel = new MyPanel();
		// ������Ϸ����С
		panel.setPreferredSize(new Dimension(length, width));
		panel.setLayout(null);
		return panel;
	}

	/**
	 * �ڲ���Ϸ����������
	 */
	class PanelListenner extends KeyAdapter {
		// ����������
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			switch (code) {
			case KeyEvent.VK_LEFT:
				Player.left = true;
				break;
			case KeyEvent.VK_RIGHT:
				Player.right = true;
				break;
			case 65:
				// A��
				Player.left = true;
				break;
			case 68:
				// D��
				Player.right = true;
				break;
			// 74=J 75=K 76=L
			default:
				break;
			}
		}

		// �������ͷ�
		public void keyReleased(KeyEvent e) {
			int code = e.getKeyCode();
			switch (code) {
			case KeyEvent.VK_LEFT:
				Player.left = false;
				break;
			case KeyEvent.VK_RIGHT:
				Player.right = false;
				break;
			case 65:
				// A��
				Player.left = false;
				break;
			case 68:
				// D��
				Player.right = false;
				break;
			default:
				break;
			}
		}
	}

	/**
	 * �Զ����ڲ���Ϸ�����
	 * 
	 * @author Ī�������� QQ:1179307527
	 */
	class MyPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			// ������ͼ������ͼ��images�ļ����µ�bround�ļ����ڣ���Ϊ�򶷽����Ժ���1��
			g.drawImage(bround, 0, 0, length, width, null);
			Player.draw(g);		
		}
	}
}
