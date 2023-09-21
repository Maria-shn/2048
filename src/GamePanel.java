/*import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {

    Timer timer;
    boolean running = false;
    Board grid;


    public void startGame(){
        grid = new Board();
        grid.newTile();
        grid.newTile();
        running = true;
        timer = new Timer(75, this);
        timer.start();
    }

    /*public boolean gameState(){
        for(int i = 0; i < grid.SIZE ; i++){
            for(int j = 0; j < grid.SIZE ; j++){
                if(grid.board[i][j] == null){
                    return true;
                }
                if(i<3){
                    if(grid.board[i][j] == grid.board[i+1][j]){
                        return true;
                    }
                }
                if(j<3){
                     if(grid.board[i][j] == grid.board[i][j+1]){
                        return true;
                    }
                }
            }
        }
        running = false;
        gameOver();
        
    }

    public void gameOver(){

    }

   

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
}*/