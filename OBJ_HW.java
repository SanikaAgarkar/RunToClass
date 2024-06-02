import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_HW extends SuperObject{
    public OBJ_HW(){
        name = "HW";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("homework.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
