package text;
import javax.swing.JPanel;
/**
 * �߳�ѭ��
 * @author ��־��
 *
 */
public class UpdateThread extends Thread{  
    JPanel panel;  
    public UpdateThread(JPanel panel) {  
        this.panel = panel;  
    }  
      
    @Override  
    public void run() {  
        while(true){  
            panel.repaint();  
            try {  
                Thread.sleep(10);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }     
}  