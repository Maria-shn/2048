import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class GamePanel extends JPanel implements ActionListener {


    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int DELAY = 75;
    Timer timer;
    boolean running = false;
    Random random;
    Board grid;



    GamePanel(){

        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();

    }


    public void startGame(){
        grid = new Board();
        grid.newTile();
        grid.newTile();
        running = true;
        timer = new Timer(75, this);
        timer.start();
    }

   

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            grid.move();
            grid.newTile();
            if (!grid.gridHasSpace()) {
                //TODO Handle game over logic here 
                running = false;
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int tileSize = SCREEN_WIDTH / this.grid.SIZE; // Calculate tile size based on panel size and board size

        for (int row = 0; row < this.grid.SIZE; row++) {
            for (int col = 0; col < this.grid.SIZE; col++) {
                int x = col * tileSize;
                int y = row * tileSize;
                g2d.drawRect(x, y, tileSize, tileSize);
            }
}

        for (int row = 0; row < this.grid.SIZE; row++) {
            for (int col = 0; col < this.grid.SIZE; col++) {
                int x = col * tileSize;
                int y = row * tileSize;
                Tile tile = grid.board[row][col];
                if (tile != null) {
                    g2d.setColor(tile.getColor());
                    g2d.fillRect(x, y, tileSize, tileSize);
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(String.valueOf(tile.getValue()), x + tileSize / 2, y + tileSize / 2);
            }
        }
    }
}


    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            char direction = '\0'; // Initialize with a default value
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    direction = 'R';
                    break;
                case KeyEvent.VK_UP:
                    direction = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                    direction = 'D';
                    break;
            }
        }
    
}
}