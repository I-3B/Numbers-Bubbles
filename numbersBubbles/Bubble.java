package numbersBubbles;
 /*
 * @author Islam
 */

import java.awt.Image;

public class Bubble {
    public int x,y;
    public Image image; 
    public short numImage;
    private numGenerator randNum=new numGenerator(new int[]{0,1,2,3,4,5,6,7,8,9});
    Bubble(int x,int y){
        this.x=x;
        this.y=y;
        this.numImage=(short)randNum.getRandom();
    
    }

   
}
