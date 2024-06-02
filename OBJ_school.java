import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_school extends SuperObject{
    public OBJ_school(){
        name = "school";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("school.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
