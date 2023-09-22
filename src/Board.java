import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Board {
    
    Tile board[][];
    static final int SIZE = 4;
    char direction;
    Random random;
    

    public Board(){
        this.board = new Tile[SIZE][SIZE];
        random = new Random();
    }

    public void newTile(){
        int emptyX, emptyY;
        do {
            emptyX = random.nextInt(SIZE); 
            emptyY = random.nextInt(SIZE);
        } while (this.board[emptyX][emptyY] != null); 
        this.board[emptyX][emptyY] = new Tile((random.nextInt(2) + 1) * 2);
    }

    

    public boolean moveMax(char direction){
        boolean change = false;
        switch(direction){
            case 'U':
               for(int i = 0; i< SIZE; i++){
                 for(int j = 1; j< SIZE; j++){
                    if(board[j-1][i] == null && board[j][i] != null){
                        board[j-1][i] = board[j][i];
                        board[j][i] = null;
                        change = true;
                    }
                 }
               }
               break;
            case 'D':
                for(int i = 0; i< SIZE; i++){
                 for(int j = SIZE-1; j<= 0; j--){
                    if(board[j+1][i] == null && board[j][i] != null){
                        board[j+1][i] = board[j][i];
                        board[j][i] = null;
                        change = true;
                    }
                 }
               }
               break;
            case 'R':
                for(int i = 0; i< SIZE; i++){
                 for(int j = SIZE-2; j>= 0; j--){
                    if(board[i][j+1] == null && board[i][j] != null){
                        board[i][j+1] = board[i][j];
                        board[i][j] = null;
                        change = true;
                    }
                 }
               }
               break;
            case 'L':
                for(int i = 0; i< SIZE; i++){
                 for(int j = 1; j< SIZE; j++){
                    if(board[i][j-1] == null && board[i][j] != null){
                        board[i][j-1] = board[i][j];
                        board[i][j] = null;
                        change = true;
                    }
                 }
               }
               break;
        }
    return change;
    }

    public boolean checkDouble(char direction){

        boolean change = false;
        switch(direction){
            case 'U':
               for(int i = 0; i< SIZE; i++){
                 for(int j = 1; j< SIZE; j++){
                    if(board[j][i]!=null && board[j-1][i] != null && board[j-1][i].getValue() == board[j][i].getValue()){
                        System.out.println("theres a double");
                        System.out.println(board[j][i].getValue());
                        board[j-1][i] = new Tile(2*(board[j][i].getValue()));
                        board[j][i] = null;
                        change = true;
                    }
                 }
               }
               break;
            case 'D':
                for(int i = 0; i< SIZE; i++){
                 for(int j = SIZE-1; j<= 0; j--){
                    if(board[j][i]!=null && board[j+1][i] != null && board[j+1][i].getValue() == board[j][i].getValue()){
                        board[j+1][i] = new Tile(2*(board[j][i].getValue()));
                        board[j][i] = null;
                        change = true;
                    }
                 }
               }
               break;
            case 'R':
                for(int i = 0; i< SIZE; i++){
                 for(int j = SIZE-2; j>= 0; j--){
                    if(board[i][j]!=null && board[i][j+1] != null && board[i][j+1].getValue() == board[i][j].getValue()){
                        board[i][j+1] = new Tile(2*(board[i][j].getValue()));
                        board[i][j] = null;
                        change = true;
                    }
                 }
               }
               break;
            case 'L':
                for(int i = 0; i< SIZE; i++){
                 for(int j = 1; j< SIZE; j++){
                    if(board[i][j]!=null && board[i][j-1] != null && board[i][j-1].getValue() == board[i][j].getValue()){
                        board[i][j-1] = new Tile(2*(board[i][j].getValue()));
                        board[i][j] = null;
                        change = true;
                    }
                }
            }
            break;
        }
        return change;
    }
    
    public void move(){
        boolean change = false;

        do {
            change = moveMax(direction);
        } while (change);
    
        boolean doubleChecker = checkDouble(direction);
        while(doubleChecker){
            doubleChecker = moveMax(direction);
        }
    }

    public boolean canMove(){
        for(int i = 0; i < this.SIZE ; i++){
            for(int j = 0; j < this.SIZE ; j++){
                if(this.board[i][j] == null){
                    return true;
                }
                if(i<3){
                    if(this.board[i][j] == this.board[i+1][j]){
                        return true;
                    }
                }
                if(j<3){
                     if(this.board[i][j] == this.board[i][j+1]){
                        return true;
                    }
                }
            }
        }
        return false;
        
    }

    public void setDirection(char dir){
        this.direction = dir;
    }

    public boolean gridHasSpace(){
        for(int i = 0; i< this.SIZE; i++){
            for(int j = 0; j<this.SIZE; j++){
                if(this.board[i][j] == null){
                    return true;
                }
                if(i< SIZE-1){
                    if(this.board[i][j].getValue() == this.board[i+1][j].getValue());
                    return true;
                }
                if(j<SIZE-1){
                    if(this.board[i][j].getValue() == this.board[i][j+1].getValue());
                    return true;
                }
            }
        }

        return false;
    }

    
    //To play on the terminal
    public void displayBoard(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(this.board[i][j] == null){
                    System.out.print(0 + " "); 
                }else{
                System.out.print(this.board[i][j].getValue() + " ");
                }
            }
            System.out.println(" ");
        }
    }

    

}

