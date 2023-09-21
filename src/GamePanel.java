import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {

    Timer timer;
    boolean running = false;
    Board board[][];


    public void startGame(){
        board = new Board();
        board.newTile();
        board.newTile();
        running = true;
        timer = new Timer(75, this);
        timer.start();
    }

    public boolean gameState(){
        for(int i = 0; i < SIZE ; i++){
            for(int j = 0; j < SIZE ; j++){
                if(board[i][j] == null){
                    return true;
                }
                if(i<3){
                    if(board[i][j] == board[i+1][j]){
                        return true;
                    }
                }
                if(j<3){
                     if(board[i][j] == board[i][j+1]){
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
    
}