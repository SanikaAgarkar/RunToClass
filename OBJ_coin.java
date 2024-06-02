import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_coin extends SuperObject{
    public OBJ_coin(){
        name = "coin";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("coin.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
