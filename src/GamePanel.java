import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {


    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int DELAY = 75;
    Timer timer;
    boolean running = false;
    Random random;
    JButton replayButton;
    Board grid;
    boolean gameOver = false;
    boolean reach2048 = false;
    boolean show2048 = true;
    boolean reach8192 = false;



    GamePanel(){

        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();

        replayButton = new JButton("Replay");
        replayButton.setFont(new Font("Ink Free", Font.BOLD, 24));
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(); 
                gameOver = false;
                replayButton.setVisible(false);
                repaint();
            }
        });
        this.add(replayButton);
        replayButton.setVisible(false);

    }


    public void startGame(){
        grid = new Board();
        grid.newTile();
        grid.newTile();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int tileSize = SCREEN_WIDTH / Board.SIZE; 

        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                int x = col * tileSize;
                int y = row * tileSize;
                g2d.drawRect(x, y, tileSize, tileSize);
            }
         }

        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                int x = col * tileSize;
                int y = row * tileSize;
                Tile tile = grid.board[row][col];
                if (tile != null) {
                    g2d.setColor(tile.getColor());
                    g2d.fillRect(x, y, tileSize, tileSize);
                    
                    Font newFont = new Font("SansSerif", Font.BOLD, 36);
                    
                    g2d.setFont(newFont);
                    g2d.setColor(Color.WHITE);

                    int value = tile.getValue();
                    String valueStr = String.valueOf(value);
    
                    // Calculate the position for centered text
                    int textX = x + (tileSize - g2d.getFontMetrics().stringWidth(valueStr)) / 2;
                    int textY = y + (tileSize + g2d.getFontMetrics().getAscent()) / 2;
    
                    g2d.drawString(valueStr, textX, textY);
            }
        }
    }
    //GAME OVER logic
    if (gameOver) {
         g.setColor(Color.BLACK);
         g.setFont(new Font("Ink Free", Font.BOLD, 75));
         FontMetrics metrics1 = getFontMetrics(g.getFont());
         g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
         
         replayButton.setBounds(SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT / 2 + 50, 200, 50);
         replayButton.setVisible(true);
        }
    
    if(reach8192){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("That's all, folks!", (SCREEN_WIDTH - metrics1.stringWidth("That's all, folks!"))/2, SCREEN_HEIGHT/2);
         
        replayButton.setBounds(SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT / 2 + 50, 200, 50);
        replayButton.setVisible(true);
    }

    if(reach2048 && show2048){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Ink Free", Font.BOLD, 30));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("You reached 2048! Keep Going!", (SCREEN_WIDTH - metrics1.stringWidth("You reached 2048! Keep Going!"))/2, SCREEN_HEIGHT/2);
        Timer hideMessageTimer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show2048 = false;
                repaint(); // Repaint to hide the message
            }
        });
        hideMessageTimer.setRepeats(false); // Only execute once
        hideMessageTimer.start();
         
    }


}

    public class MyKeyAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    grid.setDirection('L');
                    break;
                case KeyEvent.VK_RIGHT:
                    grid.setDirection('R');
                    break;
                case KeyEvent.VK_UP:
                    grid.setDirection('U');
                    break;
                case KeyEvent.VK_DOWN:
                    grid.setDirection('D');
                    break;
            }
            if(grid.legalMove()){
            grid.move(); 
            grid.newTile(); 
            }
            repaint();
             if (!grid.gridHasSpace()) {
                gameOver = true;
             }
             int endCase = grid.numReach();
             if(endCase == 1){
                reach2048 = true;
             }else if(endCase == 2){
                reach8192 = true;
             }
    
        }
    
}

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
