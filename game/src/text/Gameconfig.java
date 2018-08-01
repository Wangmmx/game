package text;

import java.awt.Image;

import javax.swing.ImageIcon;

public interface Gameconfig {
	// 窗口大小
	int length = 800;
	int width = 400;

	// 加载背景图
	Image bround= new ImageIcon("images/bround/Desert.jpg").getImage();
	
	Image k_zou_right= new ImageIcon("images/zouyou.gif").getImage();
	Image k_pao_right= new ImageIcon("images/paoyou.gif").getImage();
}
