import java.awt.*;
import javax.swing.*;

public class Help extends JDialog {
    private static final long serialVersionUID = 4693799019369193520L;
    private JPanel contentPane;
    private Font f = new Font("微软雅黑",Font.PLAIN,15);
    private JScrollPane scroll;
	
    public Help() {
        setTitle("游戏规则说明");//设置窗体标题
        Image img=Toolkit.getDefaultToolkit().getImage("ico.png");//窗口图标
        setIconImage(img);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);//设置为模态窗口
        setSize(410,380);
        setResizable(false);
        setLocationRelativeTo(null);
        contentPane = new JPanel();// 创建内容面板
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        ShadePanel shadePanel = new ShadePanel();// 创建渐变背景面板
        contentPane.add(shadePanel, BorderLayout.CENTER);// 添加面板到窗体内容面板
        shadePanel.setLayout(null);
        
        JTextArea J1 = new JTextArea("游戏说明如下：\n通过键盘上的方向键来控制蛇"
        		+ "前进的方向，长按可以加速。在游戏界面按ESC键可以直接重新开始游戏，按空格键可以实现暂停和开始。游戏界面右边会显示你的当前长度，"
        		+ "得分，当前所含有的子弹数，以及距离当前食物消失或移动剩余的时间。此版本相对上一版"
        		+ "本加入了障碍物，障碍物随机产生，每隔一段时间自动随机移动，障碍物的长度也随机，排列也随机。");
        J1.setFocusable(false);
    	J1.setFont(f);
    	J1.setEditable(false);
    	J1.setOpaque(false);//背景透明
    	J1.setLineWrap(true);
    	
    	scroll = new JScrollPane(J1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	scroll.setBorder(BorderFactory.createTitledBorder("How to play"));
    	scroll.setOpaque(false);
    	scroll.getViewport().setOpaque(false);//JScrollPane设置成透明需加上这一行
    	shadePanel.add(scroll);
    	scroll.setBounds(10, 10, 385, 330);
    	
    	setVisible(true);
    }
    
    public static void main(String[] args) {
		new Help();
	}
}
