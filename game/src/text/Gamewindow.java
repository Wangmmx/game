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
 * 游戏窗口
 * 
 * @author 莫言情难忘 QQ:1179307527
 *
 */
public class Gamewindow extends JFrame implements Gameconfig {
	/**
	 * 主方法，构造
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Gamewindow();
	}

	// 游戏面板
	JPanel panel;

	public Gamewindow() {
		setTitle("2D格斗游戏");// 设置标题
		setSize(length, width);// 设置大小
		setLayout(new FlowLayout());// 设置布局
		setDefaultCloseOperation(3);// 设置错误关闭操作
		setLocationRelativeTo(null);// 设置窗体居中
		setResizable(false);// 不可最大化

		// 创建游戏面板
		panel = setpanel();

		add(panel);
		setVisible(true);// 显示窗口

		// 安装键盘监听器
		PanelListenner plis = new PanelListenner();
		addKeyListener(plis);

		// 启动人物移动线程
		Player player = new Player();
		player.start();

		// 启动刷新面板线程
		UpdateThread ut = new UpdateThread(panel);
		ut.start();
	}

	/**
	 * 设置游戏面板
	 */
	public JPanel setpanel() {
		JPanel panel = new MyPanel();
		// 设置游戏面板大小
		panel.setPreferredSize(new Dimension(length, width));
		panel.setLayout(null);
		return panel;
	}

	/**
	 * 内部游戏按键监听类
	 */
	class PanelListenner extends KeyAdapter {
		// 当按键按下
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
				// A键
				Player.left = true;
				break;
			case 68:
				// D键
				Player.right = true;
				break;
			// 74=J 75=K 76=L
			default:
				break;
			}
		}

		// 当按键释放
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
				// A键
				Player.left = false;
				break;
			case 68:
				// D键
				Player.right = false;
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 自定义内部游戏面板类
	 * 
	 * @author 莫言情难忘 QQ:1179307527
	 */
	class MyPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			// 画背景图，背景图在images文件夹下的bround文件夹内，因为打斗界面以后不是1个
			g.drawImage(bround, 0, 0, length, width, null);
			Player.draw(g);		
		}
	}
}
