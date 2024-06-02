public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        // LEVEL 1

        gp.obj[0] = new OBJ_coin();
        gp.obj[0].worldX = 37 * gp.tileSize;
        gp.obj[0].worldY = 72 * gp.tileSize;

        gp.obj[1] = new OBJ_coin();
        gp.obj[1].worldX = 54 * gp.tileSize;
        gp.obj[1].worldY = 48 * gp.tileSize;

        gp.obj[2] = new OBJ_coin();
        gp.obj[2].worldX = 2 * gp.tileSize;
        gp.obj[2].worldY = 89 * gp.tileSize;

        gp.obj[3] = new OBJ_boots();
        gp.obj[3].worldX = 39 * gp.tileSize;
        gp.obj[3].worldY = 73 * gp.tileSize;

        gp.obj[4] = new OBJ_boots();
        gp.obj[4].worldX = 53 * gp.tileSize;
        gp.obj[4].worldY = 85 * gp.tileSize;

        gp.obj[5] = new OBJ_boots();
        gp.obj[5].worldX = 21 * gp.tileSize;
        gp.obj[5].worldY = 41 * gp.tileSize;

        gp.obj[6] = new OBJ_school();
        gp.obj[6].worldX = 30 * gp.tileSize;
        gp.obj[6].worldY = 44 * gp.tileSize;


        // LEVEL 2

        gp.obj[7] = new OBJ_door();
        gp.obj[7].worldX = 9 * gp.tileSize;
        gp.obj[7].worldY = 40 * gp.tileSize;

        gp.obj[8] = new OBJ_door();
        gp.obj[8].worldX = 30 * gp.tileSize;
        gp.obj[8].worldY = 36 * gp.tileSize;

        gp.obj[9] = new OBJ_door();
        gp.obj[9].worldX = 38 * gp.tileSize;
        gp.obj[9].worldY = 26 * gp.tileSize;

        gp.obj[10] = new OBJ_door();
        gp.obj[10].worldX = 32 * gp.tileSize;
        gp.obj[10].worldY = 7 * gp.tileSize;

        gp.obj[11] = new OBJ_HW();
        gp.obj[11].worldX = 37 * gp.tileSize;
        gp.obj[11].worldY = 10 * gp.tileSize;

    }
}
