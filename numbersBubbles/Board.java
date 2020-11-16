package numbersBubbles;

/**
 *
 * @author Islam
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;


public class Board extends JPanel implements Runnable,ActionListener  {
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 650;
    private final int DELAY = 14;
    private static final Font FONT = new Font(Font.MONOSPACED, Font.BOLD, 45);
    
    private ImageIcon ii;
    private List<Bubble>bubble=new ArrayList<Bubble>();
    
    private numGenerator randPosition=new numGenerator(IntStream.rangeClosed(25, 350).toArray());
    
    private float newBubbleRate=45;
    private short newBubbleCounter=0;
    private float movingRate=1.5f;
    
    private int score=0;
    private short trys=3;
    private boolean inGame=true;
    private Thread animator;
    public Board(){
        initBoard();
    }
    
    private void initBoard(){
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(B_WIDTH,B_HEIGHT));
        bubble.add(new Bubble(200,-100));
        loadImage(bubble.get(0));
       
        
    }
    private void loadImage(Bubble b){
        switch(b.numImage){
            case 0:    
             ii=new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/0.png")).getImage());
             b.image=ii.getImage();
            break;
            
            case 1:
            ii=new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/1.png")).getImage());
            b.image=ii.getImage();
            break;
            
            case 2:
            ii=new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/2.png")).getImage());
            b.image=ii.getImage();
            break;
            
            case 3:
            ii=new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/3.png")).getImage());
            b.image=ii.getImage();
            break;
            
            case 4:
            ii=new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/4.png")).getImage());
            b.image=ii.getImage();
            break;
           
            case 5:
            ii=new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/5.png")).getImage());
            b.image=ii.getImage();
            break;
            
            case 6:
            ii=new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/6.png")).getImage());
            b.image=ii.getImage();
            break;
            
            case 7:
            ii=new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/7.png")).getImage());
            b.image=ii.getImage();
            break;
            
            case 8:
            ii=new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/8.png")).getImage());
            b.image=ii.getImage();
            break;
            
            case 9:
            ii=new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/9.png")).getImage());
            b.image=ii.getImage();
            break;
            
            
        }
    }
    @Override
    public void addNotify(){
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawObject(g);
    }
    
    private void drawObject(Graphics g) {
        if(inGame)
        for(int i=0;i<bubble.size();i++)
        {
            g.setFont(FONT);
            g.drawImage(bubble.get(i).image, bubble.get(i).x, bubble.get(i).y, this);
            g.setColor(new java.awt.Color(133, 47,178,70));
            g.drawString(Integer.toString(score), 15,40);
            g.setColor(new java.awt.Color(109, 215,51,70));
            g.drawString(Integer.toString(trys), 360,40);
            Toolkit.getDefaultToolkit().sync();
        }
        else
            gameOver(g);
    }
    private void cycle() {
        if(inGame){
        for(int i=0;i<bubble.size();i++)
        bubble.get(i).y+=movingRate;
        
        checkFail();
        newBubbleCounter++;
        if(newBubbleCounter==(short)newBubbleRate){
            
        bubble.add(new Bubble(randPosition.getRandom(),-200));
        loadImage(bubble.get(bubble.size()-1));
        newBubbleCounter=0;
        newBubbleRate-=0.05f;
        movingRate+=0.01f;
        
        }}
        
        
    
    
    }
    
    private void checkFail(){
        if(bubble.get(0).y>=B_HEIGHT-50){
        bubble.remove(0);
        trys-=1;
        if(trys==0)
            inGame=false;
        }
        
    }
    
    private void gameOver(Graphics g){
        String msg1 = "Game Over.";
        String msg2 = "Press any key to reset...";
        String msg3="Score: "+score;
        Font small = new Font("Vrinda", Font.BOLD, 25);
        FontMetrics metr = getFontMetrics(small);
        
        g.setColor(new java.awt.Color(51, 0,51));
        g.setFont(small);
        g.drawString(msg1, (B_WIDTH - metr.stringWidth(msg1)) / 2, B_HEIGHT / 2-20);
        g.drawString(msg3, (B_WIDTH - metr.stringWidth(msg3)) / 2, B_HEIGHT / 2+5);
        g.drawString(msg2, (B_WIDTH - metr.stringWidth(msg2)) / 2, B_HEIGHT / 2+30);
    
    }
    private void resetGame(){
    newBubbleRate=50;
    newBubbleCounter=0;
    movingRate=1.4f;
    score=0;
    trys=3;
    bubble.clear();
    initBoard();
    inGame=true;
    
    
    }
     @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                if(inGame)
                    Thread.sleep(sleep);
                else
                    Thread.sleep(1500);
            } catch (InterruptedException e) {
                
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                
                JOptionPane.showMessageDialog(this, msg, "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
    }
    
     @Override
        public void actionPerformed(ActionEvent e) {

        }   
    
      private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

           int key = e.getKeyCode();
           if(bubble.get(0).y>0)
           if(key-96==bubble.get(0).numImage||key-48==bubble.get(0).numImage)
           {
               bubble.remove(0);
               score++;
               key=0;  //fix the bug that cause two bubbles or more that have the same value to disappear from one correct press
           }
           if(!inGame)
               
               resetGame();
        }
    }
        }
        
