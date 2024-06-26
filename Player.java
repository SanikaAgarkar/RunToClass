import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int hasCoin = 0;
    int hasHW = 0;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 31;
        worldY = gp.tileSize * 66 - 3;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){

        try{

            up1 = ImageIO.read(getClass().getResourceAsStream("B_UP1_backpack.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("B_UP2_backpack.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("B_DOWN1_backpack.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("B_DOWN2_backpack.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("B_Left1_backpack.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("B_Left2_backpack.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("B_Right1_backpack.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("B_Right2_backpack.png"));

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true || 
            keyH.leftPressed == true || keyH.rightPressed == true){

                if(keyH.upPressed == true){
                    direction = "up";
                }
                else if (keyH.downPressed == true){
                    direction = "down";
                }
                else if(keyH.leftPressed == true){
                    direction = "left";
                }
                else if(keyH.rightPressed == true){
                    direction = "right";
                }

                // CHECK TILE COLLISION
                collisionOn = false;
                gp.cChecker.checkTile(this);

                // CHECK OBJECT COLLISION 
                int objIndex = gp.cChecker.checkObject(this, true);
                pickUpObject(objIndex);


                // IF COLLSION IS FALSE, PLAYER CAN MOVE
                if(collisionOn == false){
                    switch (direction) {
                        case "up": worldY -= speed; break;
                        case "down": worldY += speed; break;
                        case "left": worldX -= speed; break;
                        case "right": worldX += speed; break;
                    }
                }
        
                spriteCounter++;
                if(spriteCounter > 13){
                    if(spriteNum == 1){
                        spriteNum = 2;
                    }
                    else if (spriteNum == 2){
                        spriteNum = 1;
                    }
        
                    spriteCounter = 0;
                }

        }
    }

    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;

            switch(objectName){
                case "coin":
                    gp.playSE(1);
                    hasCoin++;
                    gp.obj[i] = null;
                    break;
                case "school":
                    if(hasCoin > 0){
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasCoin--;
                        gp.ui.showMessage("You unlocked the next level!");
                        speed = 4;
                    }
                    else{
                        gp.ui.showMessage("You need a coin!");
                    }
                    break;
                case "boots":
                    gp.playSE(2);
                    speed += 2;
                    gp.ui.showMessage("Speed boost!");
                    gp.obj[i] = null;
                    break;
                case "door":
                    if(hasHW > 0){
                        gp.playSE(3);
                        hasHW--;
                        gp.ui.showMessage("Door unlocked");
                    }   
                    else{
                        gp.ui.showMessage("You need at least one homework completed to enter");
                    }
                case "HW":
                    gp.playSE(1);
                    hasHW++;
                    gp.ui.showMessage("Homeowrk completed!");
                    gp.obj[i] = null;
                    break;
                        
                    
            }
        }
    }

    public void draw(Graphics2D g2){
     /* g2.setColor(Color.WHITE);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize); */

        BufferedImage image = null;

        switch(direction){
            case "up":
                if (spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
