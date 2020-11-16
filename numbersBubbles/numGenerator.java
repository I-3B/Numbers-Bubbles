package numbersBubbles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class numGenerator {
     private final List<Integer> range = new ArrayList<>();

      numGenerator()
    {
        this.addRange(new int[]{0});
    }
    
     
    numGenerator(int[] arr)
    {
        this.addRange(arr);
    }

    final void addRange(int arr[])
    {
        for(int i :arr)
        {
            this.range.add(i);
        }
    }

    int getRandom()
    {
        return this.range.get(new Random().nextInt(this.range.size()));
    }
    
    void Clear(){
        range.clear();
    }
    
}

