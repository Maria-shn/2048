import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {


    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 150;
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
            this.grid.move();
            this.grid.newTile();
            if (!grid.gridHasSpace()) {
                //TODO Handle game over logic here (e.g., displaying a message)
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
                Tile tile = grid.board[row][col]; // Get the tile from your Board
                if (tile != null) {
                // Set color or image based on tile value
                    g2d.setColor(tile.getColor());
                // Fill the rectangle with the color or draw an image
                    g2d.fillRect(x, y, tileSize, tileSize);
                // Draw the tile's value (e.g., text) on the tile
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(String.valueOf(tile.getValue()), x + tileSize / 2, y + tileSize / 2);
            }
        }
    }

    // Draw the game board and tiles based on the Board's state
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
            // Pass the direction to your Board and trigger game actions
            grid.setDirection(direction);
        }
    
}
}