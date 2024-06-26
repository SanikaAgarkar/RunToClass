import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixles
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 58;
    public final int maxWorldRow = 95;
    public final int maxMap = 10;
    public final int currentMap = 0;
    //public final int worldWidth = tileSize * maxScreenCol;
    //public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    CollisionChecker cChecker = new CollisionChecker(this);
    AssetSetter aSetter = new AssetSetter(this);
    UI ui = new UI(this);
    Thread gameThread;

    // ENTITY AND OBJECT 
    Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[20];

    // SET PLAYER'S DEFAULT POSITION
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){

        aSetter.setObject();

        playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    /* public void run() {
        
        double drawInterval = 1000000000/FPS; // 0.016666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){

            update();

            repaint();
            
            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/100000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            }
            catch(InterruptedException e){
                e.printStackTrace();
            }

        }
    } */

    public void run(){
        
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // TILE
        tileM.draw(g2);

        // OBJECT
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        // PLAYER
        player.draw(g2);

        //UI
        ui.draw(g2);
        
        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();

    }


}
