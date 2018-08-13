import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1877974685325498861L;
	private Font f = new Font("微软雅黑",Font.PLAIN,15);
	private Font f2 = new Font("微软雅黑",Font.PLAIN,12);
	private ImageIcon backgroundImage;
	private JLabel label;
	JPanel imagePanel;
	
	public MainWindow(){
		Image img = Toolkit.getDefaultToolkit().getImage("title.png");//窗口图标
		setIconImage(img);
	    setTitle("Gift for KMD By WM");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    setSize(602, 507);
	    setSize(1000,540);
	    setResizable(false);
	    setLocationRelativeTo(null);
	    
	    //添加背景图片
	    backgroundImage = new ImageIcon("background/black.jpg");
	    backgroundImage.setImage(backgroundImage.getImage().getScaledInstance(1000,540,Image.SCALE_SMOOTH));
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
		snake.Thread2();
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
		
	    setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainWindow();
	}
	
	class Alter_Bacground extends JDialog{
		/**
		 * 
		 */
		private static final long serialVersionUID = -990903376750998765L;
		private final int back_kind = 6;
		private Font f = new Font("微软雅黑",Font.PLAIN,15);
		private JPanel p = new JPanel();
		
		public Alter_Bacground(){
			 setTitle("更换游戏背景");//设置窗体标题
			 Image img=Toolkit.getDefaultToolkit().getImage("title.png");//窗口图标
			 setIconImage(img);
		     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		     setModal(true);//设置为模态窗口
		     setSize(650,390);
		     setResizable(false);
		     setLocationRelativeTo(null);
		     
		     //添加背景图片
		     ImageIcon background[] = new ImageIcon[back_kind];
		     background[0] = new ImageIcon("background//desert.jpg");
		     background[1] = new ImageIcon("background//grass.jpg");
		     background[2] = new ImageIcon("background//ocean.jpg");
		     background[3] = new ImageIcon("background//ocean2.jpg");
		     background[4] = new ImageIcon("background//sky.jpg");
		     background[5] = new ImageIcon("background//sky2.jpg");

		     background[0].setImage(background[0].getImage().getScaledInstance(200,110,Image.SCALE_FAST));//快速
		     background[1].setImage(background[1].getImage().getScaledInstance(200,110,Image.SCALE_FAST));
		     background[2].setImage(background[2].getImage().getScaledInstance(200,110,Image.SCALE_FAST));
		     background[3].setImage(background[3].getImage().getScaledInstance(200,110,Image.SCALE_FAST));
		     background[4].setImage(background[4].getImage().getScaledInstance(200,110,Image.SCALE_FAST));
		     background[5].setImage(background[5].getImage().getScaledInstance(200,110,Image.SCALE_FAST));
		     
		     JLabel Back_label[] = new JLabel[back_kind];
		     JButton choose[] = new JButton[back_kind];
		     for(int i = 0;i < back_kind;i++)
		     {
		    	 Back_label[i] = new JLabel(background[i],SwingConstants.LEFT);
		    	 Back_label[i].setFont(f);
		    	 Back_label[i].setHorizontalTextPosition(SwingConstants.CENTER);
		    	 Back_label[i].setVerticalTextPosition(SwingConstants.BOTTOM);
		    	 
		    	 choose[i] = new JButton("选择");
		    	 choose[i].setFont(f);
		    	 p.add(choose[i]);
		    	 p.add(Back_label[i]);
		     }
		     
		     add(p,BorderLayout.CENTER);
		     p.setBackground(Color.white);
		     p.setLayout(null);
		     
		     Back_label[0].setBounds(10, 0, 200, 120);
		     choose[0].setBounds(70, 140, 80, 25);
		     Back_label[1].setBounds(220, 0, 200, 120);
		     choose[1].setBounds(280, 140, 80, 25);
		     Back_label[2].setBounds(430, 0, 200, 120);
		     choose[2].setBounds(490, 140, 80, 25);
		     Back_label[3].setBounds(10, 180, 200, 120);
		     choose[3].setBounds(70, 320, 80, 25);
		     Back_label[4].setBounds(220, 180, 200, 120);
		     choose[4].setBounds(280, 320, 80, 25);
		     Back_label[5].setBounds(430, 180, 200, 120);
		     choose[5].setBounds(490, 320, 80, 25);
		     
		     for(int i = 0;i < back_kind;i++)
		     {
		    	 choose[i].addActionListener(new ActionListener(){
		         	public void actionPerformed(ActionEvent e){
		        		if(e.getSource() == choose[0])
		        		{
		        			ImageIcon temp = new ImageIcon(background[0].toString());
		        			backgroundImage.setImage(temp.getImage().getScaledInstance(1000,540,Image.SCALE_SMOOTH));
		        			repaint();
		        			JOptionPane.showMessageDialog(null,"更改成功！回到游戏界面生效");
		        		}
		        		else if(e.getSource() == choose[1])
		        		{
		        			ImageIcon temp = new ImageIcon(background[1].toString());
		        			backgroundImage.setImage(temp.getImage().getScaledInstance(1000,540,Image.SCALE_SMOOTH));
		        			repaint();
		        			JOptionPane.showMessageDialog(null,"更改成功！回到游戏界面生效");
		        		}
		        		else if(e.getSource() == choose[2])
		        		{
		        			ImageIcon temp = new ImageIcon(background[2].toString());
		        			backgroundImage.setImage(temp.getImage().getScaledInstance(1000,540,Image.SCALE_SMOOTH));
		        			repaint();
		        			JOptionPane.showMessageDialog(null,"更改成功！回到游戏界面生效");
		        		}
		        		else if(e.getSource() == choose[3])
		        		{
		        			ImageIcon temp = new ImageIcon(background[3].toString());
		        			backgroundImage.setImage(temp.getImage().getScaledInstance(1000,540,Image.SCALE_SMOOTH));
		        			repaint();
		        			JOptionPane.showMessageDialog(null,"更改成功！回到游戏界面生效");
		        		}
		        		else if(e.getSource() == choose[4])
		        		{
		        			ImageIcon temp = new ImageIcon(background[4].toString());
		        			backgroundImage.setImage(temp.getImage().getScaledInstance(1000,540,Image.SCALE_SMOOTH));
		        			repaint();
		        			JOptionPane.showMessageDialog(null,"更改成功！回到游戏界面生效");
		        		}
		        		else if(e.getSource() == choose[5])
		        		{
		        			ImageIcon temp = new ImageIcon(background[5].toString());
		        			backgroundImage.setImage(temp.getImage().getScaledInstance(1000,540,Image.SCALE_SMOOTH));
		        			repaint();
		        			JOptionPane.showMessageDialog(null,"更改成功！回到游戏界面生效");
		        		}
		        	}
		        });
		     }
		     setVisible(true);
		}
	}
}
