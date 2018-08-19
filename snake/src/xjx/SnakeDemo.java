import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import javax.sound.sampled.*;
import javax.swing.*;

class Tile{
	int x;
	int y;
	
	public Tile(int x0,int y0){
		x = x0;
		y = y0;
	}
}

class Barrier{
	int length;
	Tile[] barrier;
	public Barrier(int len){
		length = len;
		barrier = new Tile[length];
		for(int i = 0;i < length;i++)
		{
			barrier[i] = new Tile(0,0);//每堵墙的每块砖块的坐标默认为(0,0)
		}
	}
}

public class SnakeDemo extends JComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3794762291171148906L;
	
	private JLabel label  = new JLabel("当前长度：");
	private JLabel label2 = new JLabel("所花时间：");
	private JLabel label4 = new JLabel("剩余时间：");
	private JLabel Length = new JLabel("1");
	private JLabel Time = new JLabel("");
	private JLabel Time2 = new JLabel("5");
	private Font f = new Font("微软雅黑",Font.PLAIN,15);
	private JPanel p = new JPanel();
	
	public final int MAX_SIZE = 200;//蛇身体最长为400节
	private Tile temp = new Tile(0,0);
	private Tile temp2 = new Tile(0,0);
	private Tile head = new Tile(0,0);
	private Tile[] body = new Tile[MAX_SIZE];
	
	private String direction = "R";//默认向右走
	private String current_direction = "R";//当前方向
	private boolean first_launch = false;
	private boolean iseaten = false;
	private boolean isrun = true;
	private int randomx,randomy;
	public int body_length = 0;//身体长度初始化为0
	private Thread run;
	private Thread run2;
	private Thread run3;
	
	private int hour =0;
	private int min =0;
	private int sec =0 ;
	
	private boolean pause = false;
	
	public static long normal_speed = 300;
//	public static long normal_speed = 600000; //debug speed
	private long millis = normal_speed;//每隔normal_speed毫秒刷新一次
	private boolean speed = true;
	private Calendar Cld;
	private int MI,MI2,MI3;
	private int SS,SS2,SS3;

	private int foodtag;
	private int[] point_list = new int[6];
	public ImageIcon snakehead;
	public ImageIcon snakebody;
	private ImageIcon food;
	public JLabel head_label;
	public JLabel[] body_label = new JLabel[MAX_SIZE];
	private JLabel food_label;//每次产生一种食物
	
	private int countsecond = 5;//每种食物产生5秒后消失或者换位置
	private boolean ifcount = false;
	
	public static boolean If_remove = true;//是否移除网格线
	
	private boolean hit_flag = false;

	private ImageIcon brickIcon;
	private int brick_amount = 0;
	private int brick_history_amount = 0;
	private boolean hit_barrier = false; 

	private JLabel fire_label;
	private Tile fire_position = new Tile(0,0);
	private Tile target;
	private boolean startfire = false;
	private String running;
	private int targetx,targety;
	private int target_ptr;



	public SnakeDemo(){
		
		String lookAndFeel =UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		//布局
		add(label);
	    label.setBounds(910, 10, 80, 20);
	    label.setFont(f);
	    add(Length);
	    Length.setBounds(910, 35, 80, 20);
	    Length.setFont(f);
	    add(label2);
	    label2.setBounds(910, 70, 80, 20);
	    label2.setFont(f);
	    add(Time);
	    Time.setBounds(910, 95, 80, 20);
	    Time.setFont(f);
	    add(p);
	    p.setBounds(908, 200, 93, 1);
	    p.setBorder(BorderFactory.createLineBorder(Color.white));
	    add(label4);
	    label4.setBounds(910, 210, 80, 20);
	    label4.setFont(f);
	    add(Time2);
	    Time2.setBounds(910, 235, 80, 20);
	    Time2.setFont(f);
	    
	    //字体颜色，为了便于分辨，设为白色
	    label.setForeground(Color.white);
	    label2.setForeground(Color.white);
	    label4.setForeground(Color.white);
	    Length.setForeground(Color.white);
		Time.setForeground(Color.white);
		Time2.setForeground(Color.white);

		
	    //初始化头部坐标
	    ProduceRandom();
	    head = new Tile(randomx,randomy);
	    snakehead = new ImageIcon("head/head.png");
	    snakehead.setImage(snakehead.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));//保持图片的清晰
        head_label = new JLabel(snakehead); 
        add(head_label);
        head_label.setBounds(head.x, head.y, 30, 30);
        head_label.setOpaque(false);
        
        //初始化身体所有节点
        snakebody = new ImageIcon("body/9.png");
		snakebody.setImage(snakebody.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));//保持图片的清晰
		for(int i = 0; i < MAX_SIZE;i++)
		{
			body[i] = new Tile(0,0);
			body_label[i] = new JLabel(snakebody); 
			body_label[i].setOpaque(false);
		}
		
		//初始化食物
		food = new ImageIcon("food/food.png");
	    food.setImage(food.getImage().getScaledInstance(29,29,Image.SCALE_SMOOTH));//保持图片的清晰


		
		//添加监听器
		this.addKeyListener(new KeyAdapter() {
	    	public void keyPressed(KeyEvent e) {
	    		super.keyPressed(e);
	    		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
	    		{
	    			if(isrun && current_direction != "L")
	    			{
	    				direction = "R";
	    			}
	    		}
	    		if(e.getKeyCode() == KeyEvent.VK_LEFT)
	    		{
	    			if(isrun && current_direction != "R")
	    			{
	    				direction = "L";
	    			}
	    		}
	    		if(e.getKeyCode() == KeyEvent.VK_UP)
	    		{
	    			if(isrun && current_direction != "D")
	    			{
	    				direction = "U";
	    			}
	    		}
	    		if(e.getKeyCode() == KeyEvent.VK_DOWN)
	    		{
	    			if(isrun && current_direction != "U")
	    			{
	    				direction = "D";
	    			}
	    		}
	    		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)//重置所有变量初始值
	    		{
	    			Reset(); 
	    		}
	    		
	    		if(e.getKeyCode() == KeyEvent.VK_SPACE)
	    		{
	    			if(!pause)//暂停
	    			{
	    				pause = true;
	    				isrun = false;
	    				ifcount = false;
	    			}
	    			else//开始
	    			{
	    				pause = false;
	    				isrun = true;
	    				ifcount = true;
	    			}
	    		}

	    	}
		});
		
		this.addKeyListener(new KeyAdapter() {
	    	public void keyPressed(KeyEvent e) {
	    		int key = e.getKeyCode();
	    		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN)
	    		{
	    			if(speed)
	    			{
	    				Cld = Calendar.getInstance();
		    			int YY = Cld.get(Calendar.YEAR) ;
		    	        int MM = Cld.get(Calendar.MONTH)+1;
		    	        int DD = Cld.get(Calendar.DATE);
		    	        int HH = Cld.get(Calendar.HOUR_OF_DAY);
		    	        int mm = Cld.get(Calendar.MINUTE);
		    	        SS = Cld.get(Calendar.SECOND);
		    	        MI = Cld.get(Calendar.MILLISECOND); 
		    	        System.out.println("Pressed time  " + YY + "/" + MM + "/" + DD + "-" + HH
		    	        		+ ":" + mm + ":" + SS + ":" + MI);
		    	        
		    	        speed = false;
	    			}
	    			
	    			Cld = Calendar.getInstance();
	    			SS3 = Cld.get(Calendar.SECOND);
	    	        MI3 = Cld.get(Calendar.MILLISECOND); 
	    	        int x = SS3 * 1000 + MI3 - ( SS * 1000 + MI);
	    	        if(x > 400)//按一个按钮的时长大于400毫秒识别为长按
	    	        {
	    	        	millis = 50;//加速时每隔50毫秒刷新一次
	    	        	System.out.println("Long Pressed");
	    	        }
	    		}
	    	}
		});
		
		this.addKeyListener(new KeyAdapter() {
	    	public void keyReleased(KeyEvent e) {
	    		int key = e.getKeyCode();
	    		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN)
	    		{
	    			Cld = Calendar.getInstance();
	    			int YY2 = Cld.get(Calendar.YEAR) ;
	    	        int MM2 = Cld.get(Calendar.MONTH)+1;
	    	        int DD2 = Cld.get(Calendar.DATE);
	    	        int HH2 = Cld.get(Calendar.HOUR_OF_DAY);
	    	        int mm2 = Cld.get(Calendar.MINUTE);
	    	        SS2 = Cld.get(Calendar.SECOND);
	    	        MI2 = Cld.get(Calendar.MILLISECOND); 
	    	        System.out.println("Released time " + YY2 + "/" + MM2 + "/" + DD2 + "-" + HH2 
	    	        		+ ":" + mm2 + ":" + SS2 + ":" + MI2 + "\n" );

	    	        speed = true;
	    	        millis = normal_speed;
	    		}
	    	}
		});
		
		new Timer();//开始计时
		new Countdown();//开始倒计时
		setFocusable(true);
	}


	
	public void paintComponent(Graphics g1){
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_NORMALIZE);
		
		//刷新头部坐标
		if(hit_flag)
		{
			head.x = temp.x;
			head.y = temp.y;
		}
		head_label.setBounds(head.x, head.y, 30, 30);
		
		if(!first_launch)
		{
			//初始化食物位置
			ProduceFood();
			ProduceRandom();
			add(food_label);
			food_label.setBounds(randomx, randomy, 29, 29);
			ifcount = true;

		}
		else
		{
			//每次刷新身体
			for(int i = 0;i < body_length;i++)
			{

				body_label[i].setBounds(body[i].x, body[i].y, 30, 30);
			}


			
			if(EatFood())//被吃了重新产生食物
			{
				remove(food_label);//去掉被吃掉的食物
				ProduceFood();
				ProduceRandom();
				add(food_label);
				food_label.setBounds(randomx, randomy, 29, 29);
				iseaten = false;
				
				ifcount = false;
				Time2.setText("5");
				countsecond = 5;
				ifcount = true;
			}
			else
			{
				food_label.setBounds(randomx, randomy, 29, 29);
			}
		}
		first_launch = true;
		
		//墙
		g.setPaint(new GradientPaint(115,135,Color.YELLOW,230,135,Color.YELLOW,true));

		g.setStroke( new BasicStroke(4,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL));
		g.drawRect(2, 7, 900, 483);//+400

		if(!If_remove)
		{
			//网格线
			for(int i = 1;i < 28;i++)
			{
				g.setStroke( new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL));//实线
				g.setColor(Color.white);
				g.drawLine(5+i*32,9,5+i*32,490);
				if(i < 15)
				{
					g.drawLine(4,10+i*32,900,10+i*32);//+400
				}
			}
		}



	}
	
	public void ProduceFood(){
		Random rand = new Random();
		double x;
		x = rand.nextDouble();
		if(x >= 0 && x <1)
		{
			food_label = new JLabel(food);
		}
	}


	public void ProduceRandom(){
		boolean flag = true;
		Random rand = new Random();
		randomx = (rand.nextInt(27) + 1) * 32 + 7 ;
		randomy = (rand.nextInt(14) + 1) *32 + 12;
		System.out.println("产生一个随机坐标成功");

	}
	

	
	@SuppressWarnings("deprecation")
 	public void HitWall(){//判断是否撞墙
		if(current_direction == "L")
		{
			if(head.x < 7)
			{
				ifcount = false;
				new AePlayWave("over.wav").start();
				isrun = false;
				int result=JOptionPane.showConfirmDialog(null, "Game over! Try again?", "Information", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_NO_OPTION)
				{
					Reset();
				}
				else
				{
					pause = true;
				}		
			}
		}
		if(current_direction == "R")
		{
			if(head.x > 885)
			{
				ifcount = false;
				new AePlayWave("over.wav").start();
				isrun = false;
				int result=JOptionPane.showConfirmDialog(null, "Game over! Try again?", "Information", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_NO_OPTION)
				{
					Reset();
				}
				else
				{
					pause = true;
				}
			}
		}
		if(current_direction == "U")
		{
			if(head.y < 12)
			{
				ifcount = false;
				new AePlayWave("over.wav").start();
				isrun = false;
				int result=JOptionPane.showConfirmDialog(null, "Game over! Try again?", "Information", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_NO_OPTION)
				{
					Reset();
				}
				else
				{
					pause = true;
				}
			}
		}
		if(current_direction == "D")
		{
			if(head.y > 472)
			{
				ifcount = false;
				new AePlayWave("over.wav").start();
				isrun = false;
				int result=JOptionPane.showConfirmDialog(null, "Game over! Try again?", "Information", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_NO_OPTION)
				{
					Reset();
				}
				else
				{
					pause = true;
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void HitSelf(){//判断是否撞到自己身上
		for(int i = 0;i < body_length; i++)
		{
			if(body[i].x == head.x && body[i].y == head.y)
			{
				ifcount = false;
				new AePlayWave("over.wav").start();
				isrun = false;
				int result=JOptionPane.showConfirmDialog(null, "Game over! Try again?", "Information", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_NO_OPTION)
				{
					Reset();
				}
				else
				{
//					run.stop();
					pause = true;
				}
				break;
			}
		}
	}
	

	
	public boolean  EatFood(){
		if(head.x == randomx && head.y == randomy)
		{
			iseaten = true;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void Thread(){
//		millis = normal_speed;//默认每隔normal_speed毫秒刷新一次
		run = new Thread() {
			public void run() {
				while (true) 
				{
					try {
						Thread.sleep(millis);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
					
					if(!pause)
					{	
						hit_flag = false;
						temp.x = head.x;
						temp.y = head.y;
						
						//头部移动
						if(direction == "L")
						{
							head.x -= 32;
							if(head.x < 7)
							{
								hit_flag = true;
							}
						}
						if(direction == "R")
						{
							head.x += 32;
							if(head.x > 885)
							{
								hit_flag = true;
							}
						}
						if(direction == "U")
						{
							head.y -= 32;
							if(head.y < 12)
							{
								hit_flag = true;
							}
						}
						if(direction == "D")
						{
							head.y += 32;
							if(head.y > 472)
							{
								hit_flag = true;
							}
						}
						current_direction = direction;//刷新当前前进方向
						
						if(hit_barrier)
						{
							hit_flag = true;
						}
						
						if(!hit_flag)
						{
							for(int i = 0;i < body_length;i++)
							{
								temp2.x = body[i].x;
								temp2.y = body[i].y;
								body[i].x = temp.x;
								body[i].y = temp.y;
								temp.x = temp2.x;
								temp.y = temp2.y;
							}
							
							if(EatFood())
							{
								body_length ++;
								body[body_length-1].x = temp.x;
								body[body_length-1].y = temp.y;
//								switch (body_length) {
//									case 1:
//										snakebody = new ImageIcon("body/1.png");
//										snakebody.setImage(snakebody.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));//保持图片的清晰
//										break;
//									case 2:
//										snakebody = new ImageIcon("body/2.png");
//										snakebody.setImage(snakebody.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));//保持图片的清晰
//										break;
//									case 3:
//										snakebody = new ImageIcon("body/3.png");
//										snakebody.setImage(snakebody.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));//保持图片的清晰
//										break;
//									case 4:
//										snakebody = new ImageIcon("body/4.png");
//										snakebody.setImage(snakebody.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));//保持图片的清晰
//										break;
//									case 5:
//										snakebody = new ImageIcon("body/5.png");
//										snakebody.setImage(snakebody.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));//保持图片的清晰
//										break;
//									case 6:
//										snakebody = new ImageIcon("body/6.png");
//										snakebody.setImage(snakebody.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));//保持图片的清晰
//										break;
//									case 7:
//										snakebody = new ImageIcon("body/7.png");
//										snakebody.setImage(snakebody.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));//保持图片的清晰
//										break;
//									case 8:
//										snakebody = new ImageIcon("body/8.png");
//										snakebody.setImage(snakebody.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));//保持图片的清晰
//										break;
//									default:
//										snakebody = new ImageIcon("body/9.png");
//										snakebody.setImage(snakebody.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));//保持图片的清晰
//										break;
//								}
								
								add(body_label[body_length-1]);
								
								Length.setText("" + (body_length+1) );//刷新长度


								new AePlayWave("eat.wav").start();
							}
						}
						
						repaint();
						
						//刷新完判断是否撞墙和撞自己的身体
						HitWall();
						HitSelf();
					}
				}
			}
		};
		
		run.start();
	}


	
	public void Reset(){
		startfire = false;
		hit_barrier = false;
		hit_flag = false;
		remove(food_label);//去掉被吃掉的食物
		//初始化头部坐标
	    ProduceRandom();
	    
	    
	    head = new Tile(randomx,randomy);
	    //初始化身体节点部坐标
	    for(int jj = 0; jj < MAX_SIZE;jj++)
		{
			body[jj].x = 0;
			body[jj].y = 0;
		}
	    
	    for(int i = 0;i < 30;i++)
	    {
	    	body_label[i].setVisible(true);
	    }
	    
	    for(int k = 0;k < body_length;k++)
	    {
	    	remove(body_label[k]);
	    }
	    

	    
		hour =0;
		min =0;
		sec =0 ;
		direction = "R";//默认向右走
		current_direction = "R";//当前方向
		first_launch = false;
		iseaten = false;
		isrun = true;
		pause = false;
		millis = normal_speed;//每隔normal_speed毫秒刷新一次
		speed = true;
		body_length = 0;
		Length.setText("1");
		
		countsecond = 5;
		Time2.setText("5");
		ifcount = true;

		
		System.out.println("Start again");
	}
	
	
	//倒计时类
	class Countdown extends Thread{
	    public Countdown(){
	        this.start();
	    }
	    
	    @SuppressWarnings("deprecation")
		public void run() {
	    	while(true){
	    		if(ifcount)
		    	{
		    		if(countsecond >= 1)//2太难玩了
			    	{
			    		countsecond --;
			    		Time2.setText("" + countsecond);
			    	}
			    	else
			    	{
			    		ifcount = false;
			    		countsecond = 5;
			    		Time2.setText("5");
			    		remove(food_label);
			    		ProduceRandom();
			    		add(food_label);
			    		ifcount = true;
			    	}
		    	}
	    		
	    		try {
	    			Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	    	}
	    }
	}
	
	//计时器类
	class Timer extends Thread{  
		    public Timer(){
		        this.start();
		    }
		    @Override
		    public void run() {
		        while(true){
		            if(isrun){
		                sec +=1 ;
		                if(sec >= 60){
		                    sec = 0;
		                    min +=1 ;
		                }
		                if(min>=60){
		                    min=0;
		                    hour+=1;
		                }
		                showTime();
		            }
		 
		            try {
		                Thread.sleep(1000);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		             
		        }
		    }

		    private void showTime(){
		        String strTime ="" ;
		        if(hour < 10)
		            strTime = "0"+hour+":";
		        else
		            strTime = ""+hour+":";
		         
		        if(min < 10)
		            strTime = strTime+"0"+min+":";
		        else
		            strTime =strTime+ ""+min+":";
		         
		        if(sec < 10)
		            strTime = strTime+"0"+sec;
		        else
		            strTime = strTime+""+sec;
		         
		        //在窗体上设置显示时间
		        Time.setText(strTime);
		    }
		}	
}

class AePlayWave extends Thread { 	 
    private String filename;
    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb 

    public AePlayWave(String wavfile) { 
        filename = wavfile;
    } 
    	    
    public void run() { 
        File soundFile = new File(filename); 
        AudioInputStream audioInputStream = null;
        try { 
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException e1) { 
            e1.printStackTrace();
            return;
        } catch (IOException e1) { 
            e1.printStackTrace();
            return;
        } 
 
        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
        try { 
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) { 
            e.printStackTrace();
            return;
        } catch (Exception e) { 
            e.printStackTrace();
            return;
        } 

        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
 
        try { 
            while (nBytesRead != -1) { 
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) 
                    auline.write(abData, 0, nBytesRead);
            } 
        } catch (IOException e) { 
            e.printStackTrace();
            return;
        } finally { 
            auline.drain();
            auline.close();
        } 
    } 
}
