import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10]; // number of types of tiles we can create
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("map1.txt");
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("sky.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("land.png"));
            tile[1].collsion = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("grass.png"));
            tile[2].collsion = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("cloud.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("greenCar.png"));
            tile[4].collsion = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("redCar.png"));
            tile[5].collsion = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("wall.png"));
            tile[6].collsion = true;

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){

        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();

                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }
        catch(Exception e){}


    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;
        int x = 0;
        int y = 0;

        while(worldCol < gp.maxScreenCol && worldRow < gp.maxScreenRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            //int worldX = worldCol * gp.tileSize;
            //int worldY = worldRow * gp.tileSize;
            //int screenX = worldX - gp.player.x + gp.player.screenX;
            //int screenY = worldY - gp.player.x + gp.player.screenY;

            /*if(worldX > gp.player.worldX - gp.player.screenX &&
                    worldX < gp.player.worldX + gp.player.screenX &&
                    worldY > gp.player.worldY - gp.player.screenY &&
                    worldY < gp.player.worldY + gp.player.screenY){
                        g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    }*/
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            worldCol++;
            
            x += gp.tileSize;

            if(worldCol == gp.maxScreenCol){
                worldCol = 0;
                x = 0;
                worldRow++;
                y += gp.tileSize;
            }
        }

    }

}
