package text;

import java.awt.Image;

import javax.swing.ImageIcon;

public interface Gameconfig {
	// ���ڴ�С
	int length = 1200;
	int width = 800;

	// ���ر���ͼ
	Image bround= new ImageIcon("images/bround/Desert.jpg").getImage();
	
	Image k_zou_right= new ImageIcon("images/zouyou.gif").getImage();
	Image k_pao_right= new ImageIcon("images/paoyou.gif").getImage();
}
