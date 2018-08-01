package text;
import javax.swing.JPanel;
/**
 * 线程循环
 * @author 赵志辉
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