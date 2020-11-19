package TypingBubbles;
 /*
 * @author Islam
 */

import java.awt.Image;

public class Bubble {
    public int x,y;
    public Image image; 
    public short character;
    public String imageName;
    Bubble(){
        this.x=200;
        this.y=-100;
        this.character=(short)Board.characterRand.getRandom();
        this.imageName=String.valueOf(character);
    }
    Bubble(int x,int y){
        
        this.x=x;
        this.y=y;
        
        do{
            
        this.character=(short)Board.characterRand.getRandom();
        
        }while(this.character==Main.board.bubble.get(Main.board.bubble.size()-1).character);
        
        this.imageName=String.valueOf(character);
    }

   
}
