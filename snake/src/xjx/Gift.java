import javax.swing.*;
import java.awt.*;

public class Gift extends JDialog {
    private static final long serialVersionUID = 4693799019369193520L;
    private JPanel contentPane;
    private Font f = new Font("微软雅黑",Font.PLAIN,15);
    private JScrollPane scroll;

    public Gift() {
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
        
        JTextArea J1 = new JTextArea("送给孔梦丹，生日快乐！");
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
		new Gift();
	}
}
