package numbersBubbles;

/**
 *
 * @author Islam
 */

   
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame {

    public Main() {

        initUI();
    }
    
    private void initUI() {
        add(new Board());

        setResizable(false);
        pack();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("resources/icon.png")).getImage());
        setTitle("Numbers Bubbles");    
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new Main();
            ex.setVisible(true);
        });
    }
} 
