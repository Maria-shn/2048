import java.util.Scanner;

public class Game2048 {

    public static void main(String[] args) {
        Board myGrid = new Board();
        myGrid.newTile();
        myGrid.newTile();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(myGrid.board[i][j] == null){
                    System.out.print(0 + " "); 
                }else{
                System.out.print(myGrid.board[i][j].getValue() + " ");
                }
            }
            System.out.println(" ");
        }
        myGrid.direction = 'R';
        myGrid.move();
         for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(myGrid.board[i][j] == null){
                    System.out.print(0 + " "); 
                }else{
                System.out.print(myGrid.board[i][j].getValue() + " ");
                }
            }
            System.out.println(" ");
        }


          
          
      
    }
    
}