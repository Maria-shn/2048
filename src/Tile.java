import java.awt.Color;

public class Tile {

    Color color;
    int value;
    
    public Tile(){
        Color color = new Color(208, 146, 139);;
        int value = 2;
    }

    public Tile(int x){
        switch(x){

        case 0:
            this.color = new Color(0, 0, 0);
        case 2:
            this.color = new Color(208, 146, 139);
        case 4:
            this.color = new Color(202, 132, 125);
        case 8:
            this.color = new Color(196, 119, 110);
        case 16:
            this.color = new Color(190, 105, 96);
        case 32:
            this.color = new Color(184, 91, 81);
        case 64:
            this.color = new Color(174, 81, 71);
        case 128:
            this.color = new Color(152, 71, 62);
        case 256:
            this.color = new Color(145, 68, 59);
        case 512:
            this.color = new Color(130, 61, 53);
        case 1024:
            this.color = new Color(116, 54, 47);
        case 2048:
            this.color = new Color(101, 47, 41);
        case 4096:
            this.color = new Color(87, 41, 35);
        case 8192:
            this.color = new Color(72, 34, 30);

        }
        this.value = x;
    }

    public int getValue(){
        return this.value;
    }
}
