
package TypingBubbles;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author ISlam
 */
public class ChoicesMenu implements ActionListener{
        JMenuBar bMenu=new JMenuBar();
        static String selectedChoice="Just letters";
        JMenu menu=new JMenu("Style");
        JMenuItem m1=new JMenuItem("Just numbers");
        JMenuItem m2=new JMenuItem("Just letters");
        JMenuItem m3=new JMenuItem("Letters and numbers");
        
        
    public ChoicesMenu() {
        set();
    }
    private void set(){
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
        bMenu.add(menu);
        m1.addActionListener(this); 
        m2.addActionListener(this); 
        m3.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){ 
         selectedChoice= e.getActionCommand();
    } 
}

    
    

