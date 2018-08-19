import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JFrame{
	/**
	 * 主界面  视觉界面
	 */
	private static final long serialVersionUID = -1877974685325498861L;
	private Font f = new Font("微软雅黑",Font.PLAIN,13);
	private Font f2 = new Font("微软雅黑",Font.PLAIN,12);
	private ImageIcon backgroundImage;
	private JLabel label;
	JPanel imagePanel;
	
	public MainWindow(){
		Image img = Toolkit.getDefaultToolkit().getImage("ico.png");//窗口图标
		setIconImage(img);
	    setTitle("Gift For 孔梦丹");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(1000,552);
	    setResizable(false);
	    setLocationRelativeTo(null);
	    
	    //添加背景图片
	    backgroundImage = new ImageIcon("background/black.jpg");
	    backgroundImage.setImage(backgroundImage.getImage().getScaledInstance(1000,552,Image.SCALE_SMOOTH));
        label = new JLabel(backgroundImage);
        label.setBounds(0,0, this.getWidth(), this.getHeight());
        imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        
        //菜单栏
        JMenuBar bar = new JMenuBar();
        bar.setBackground(Color.white);
  		setJMenuBar(bar);
  		JMenu Help = new JMenu("玩法");
  		Help.setFont(f);
  		JMenu About = new JMenu("作者");
  		About.setFont(f);
  		bar.add(Help);
  		bar.add(About);	

		JMenuItem help = new JMenuItem("游戏指导");
		help.setFont(f2);
		Help.add(help);

		JMenuItem about = new JMenuItem("关于此游戏");
		about.setFont(f2);
		About.add(about);

		JMenuItem gift = new JMenuItem("生日快乐");
		about.setFont(f2);
		About.add(gift);

	    SnakeDemo snake = new SnakeDemo();
		snake.Thread();
		snake.setOpaque(false);
		imagePanel.add(snake, BorderLayout.CENTER);

		about.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		new About();
        	}
        });
		help.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		new Help();
        	}
        });
		gift.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){ new Gift(); }
		});
	    setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainWindow();
	}

}
