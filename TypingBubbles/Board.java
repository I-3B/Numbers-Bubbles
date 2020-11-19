package TypingBubbles;

/**
 *
 * @author Islam
 */

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;


public class Board extends JPanel implements Runnable  {
    private final int B_WIDTH = 450;
    private final int B_HEIGHT = 650;
    private static final Font FONT = new Font(Font.MONOSPACED, Font.BOLD,35);
    
    private ImageIcon ii;
    public List<Bubble>bubble=new ArrayList<Bubble>();
    
    private numGenerator randPosition=new numGenerator(IntStream.rangeClosed(25, B_WIDTH-75).toArray());
    
    private int DELAY = 11;
    private float newBubbleRate=44;
    private short newBubbleCounter=0;
    private short incrementCounter=0;
    private final short incrementEnd=50;
    private float movingRate=1.6f;
    
    private int Hscore=0;
    private int score=0;
    private int trys=3;
    
    private boolean inGame=true;
    //private boolean gamePaused=false;

    
    private Thread animator;
    public static String selectedChoice=ChoicesMenu.selectedChoice;
    public static numGenerator characterRand=new numGenerator();
    
    public Board(){
        initBoard();
    }
    
    private void initBoard(){
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(new java.awt.Color(22, 22,22,98));
        setPreferredSize(new Dimension(B_WIDTH,B_HEIGHT));
        characterRand.Clear();
        switch(Board.selectedChoice){
            case "Just numbers":
                characterRand.addRange(IntStream.rangeClosed(48, 57).toArray());
                break;
            case "Just letters":
                characterRand.addRange(IntStream.rangeClosed(65, 90).toArray());
                break;
            case "Letters and numbers":
                characterRand.addRange(IntStream.rangeClosed(48, 57).toArray());
                characterRand.addRange(IntStream.rangeClosed(65, 90).toArray());
                break;
        
        }
        
        bubble.add(new Bubble());
        loadImage(bubble.get(0));
       
        
    }
    private void loadImage(Bubble b){
       
             ii=new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/"+b.imageName+".png")).getImage());
             b.image=ii.getImage();
            
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
        g.setFont(FONT);
         
        if(inGame)
        for(int i=0;i<bubble.size();i++)
        {
            g.drawImage(bubble.get(i).image, bubble.get(i).x, bubble.get(i).y, this);
            g.setColor(new java.awt.Color(133, 47,178,40));
            g.drawString(Integer.toString(Hscore), 5,30);
            g.drawString(Integer.toString(score), 5,60);
            g.setColor(new java.awt.Color(109, 215,51,40));
            g.drawString(Integer.toString(trys), B_WIDTH-25,30);
            Toolkit.getDefaultToolkit().sync();
        }
        
        else
            gameOver(g);
    }
    private void cycle() {
        
        if(Board.selectedChoice.equals(ChoicesMenu.selectedChoice)){
            if(inGame){
                for(int i=0;i<bubble.size();i++)
                bubble.get(i).y+=movingRate;
        
                checkFail();
                incrementCounter++;
                newBubbleCounter++;
                if(newBubbleCounter==(short)newBubbleRate)
                {
                    bubble.add(new Bubble(randPosition.getRandom(),-100));
                    loadImage(bubble.get(bubble.size()-1));
                    newBubbleCounter=0;
                }
                if(incrementCounter==incrementEnd&&movingRate<2){
                    incrementCounter=0;
                    newBubbleRate-=0.05;
                    movingRate+=0.01;
                }
                else if(incrementCounter==incrementEnd&&movingRate<3){
                    incrementCounter=0;
                    newBubbleRate-=0.05;
                    movingRate+=0.01;
                }
                else if(incrementCounter==incrementEnd&&movingRate>3){
                    incrementCounter=0;
                    newBubbleRate-=0.02;
                    movingRate+=0.01;
                }         if((int)(Math.round(movingRate * 100))/100.0==2.00f)
                {

                    DELAY=10;
                }

                if((int)(Math.round(movingRate * 100))/100.0==2.00f)
                {

                    DELAY=10;
                }
            
                if((int)(Math.round(movingRate * 100))/100.0==3.00f)
                { 
                    DELAY=9;
                }
                
                if((int)(Math.round(movingRate * 100))/100.0==4.00f)
                { 
                    DELAY=8;
                }
        
                }
                
        }
        
        else{
            Board.selectedChoice=ChoicesMenu.selectedChoice;
            Hscore=0;
            resetGame();
            
        }
       
}
    
    private void checkFail(){
        if(bubble.get(0).y>=B_HEIGHT-20){
            bubble.remove(0);
            trys-=1;
        }
        if(trys==0)
            inGame=false;
     
        }
    
        private void gameOver(Graphics g){
            String msg1 = "Game Over.";
            String msg2 = "Press any key to reset...";
            String msg3="Score: "+score;
            String msg4="Highest score: "+Hscore;
            Font small = new Font("Vrinda", Font.BOLD, 25);
            FontMetrics metr = getFontMetrics(small);

            g.setColor(new java.awt.Color(220, 220,220));
            g.setFont(small);
            g.drawString(msg1, (B_WIDTH - metr.stringWidth(msg1)) / 2, B_HEIGHT / 2-40);
            g.drawString(msg3, (B_WIDTH - metr.stringWidth(msg3)) / 2, B_HEIGHT / 2+5);
            g.drawString(msg4, (B_WIDTH - metr.stringWidth(msg4)) / 2, B_HEIGHT / 2+30);
            g.drawString(msg2, (B_WIDTH - metr.stringWidth(msg2)) / 2, B_HEIGHT / 2+55);

        }
        
        
        private void resetGame(){
            if(score>Hscore)
                Hscore=score;
            DELAY=11;
            newBubbleRate=44;
            newBubbleCounter=0;
            incrementCounter=0;
            movingRate=1.6f;
            score=0;
            trys=3;
            bubble.clear();
            inGame=true;
            initBoard();
           

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
                            Thread.sleep(1000);
                    } catch (InterruptedException e) {

                        String msg = String.format("Thread interrupted: %s", e.getMessage());

                        JOptionPane.showMessageDialog(this, msg, "Error", 
                            JOptionPane.ERROR_MESSAGE);
                        }

                    beforeTime = System.currentTimeMillis();
                }
        }
    
    
        private class TAdapter extends KeyAdapter {
                int key;
                @Override
                public void keyPressed(KeyEvent e) {
                    
                    key = e.getKeyCode();
                 
                    if(key>=96&&key<=105){
                        key-=48;
                   }

                    if(bubble.get(0).y>0){
                        if(key==bubble.get(0).character){  
                            bubble.remove(0);
                            score++;
                        }
                   }
                   
                    if(!inGame)
                       resetGame();
                }
        }


}

