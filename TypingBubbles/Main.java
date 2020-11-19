package TypingBubbles;

/**
 *
 * @author Islam
 */

   
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame {
    public static Board board;
    public Main() {
          try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initUI();
    }
    
    private void initUI() {
      
     
        ChoicesMenu choicesMenu=new ChoicesMenu();
        board=new Board();
        setJMenuBar(choicesMenu.bMenu);
        add(board);
        
        setResizable(false);
        pack();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("resources/icon.png")).getImage());
        setTitle("Typing Bubbles");    
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

