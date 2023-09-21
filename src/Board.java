import java.util.Random;

public class Board {
    
    Tile board[][];
    static final int SIZE = 4;
    Random random;
    char direction;
    boolean running = false;

    public Board(){
        Tile board[][] = new Tile[SIZE][SIZE];
     }

    public void newTile(){
        int emptyX, emptyY;
        do {
            emptyX = random.nextInt(SIZE); 
            emptyY = random.nextInt(SIZE);
        } while (board[emptyX][emptyY] != null); 
        board[emptyX][emptyY] = new Tile((random.nextInt(2) + 1) * 2);
    }

    public boolean moveMax(char direction){
        boolean change = false;
        switch(direction){
            case 'U':
               for(int i = 0; i< SIZE; i++){
                 for(int j = 1; j< SIZE; j++){
                    if(board[j-1][i] == null){
                        board[j-1][i] = board[j][i];
                        change = true;
                    }
                 }
               }
            case 'D':
                for(int i = 0; i< SIZE; i++){
                 for(int j = SIZE-1; j<= 0; j--){
                    if(board[j+1][i] == null){
                        board[j+1][i] = board[j][i];
                        change = true;
                    }
                 }
               }
            case 'R':
                for(int i = 0; i< SIZE; i++){
                 for(int j = SIZE-1; j<= 0; j--){
                    if(board[i][j+1] == null){
                        board[i][j] = board[j][i];
                        change = true;
                    }
                 }
               }
            case 'L':
                for(int i = 0; i< SIZE; i++){
                 for(int j = 1; j< SIZE; j++){
                    if(board[i][j-1] == null){
                        board[i][j-1] = board[j][i];
                        change = true;
                    }
                 }
               }
        }
    return change;
    }

    public boolean checkDouble(char direction){

        boolean change = false;
        switch(direction){
            case 'U':
               for(int i = 0; i< SIZE; i++){
                 for(int j = 1; j< SIZE; j++){
                    if(board[j-1][i] == board[j][i]){
                        board[j-1][i] = new Tile(2*board[j-1][i].getValue());
                        board[j][i] = null;
                        change = true;
                    }
                 }
               }
            case 'D':
                for(int i = 0; i< SIZE; i++){
                 for(int j = SIZE-1; j<= 0; j--){
                    if(board[j+1][i] == board[j][i]){
                        board[j+1][i] = new Tile(2*board[j+1][i].getValue());
                        board[j][i] = null;
                        change = true;
                    }
                 }
               }
            case 'R':
                for(int i = 0; i< SIZE; i++){
                 for(int j = SIZE-1; j<= 0; j--){
                    if(board[i][j+1] == board[i][j]){
                        board[i][j+1] = new Tile(2*board[i][j+1].getValue());
                        board[i][j] = null;
                        change = true;
                    }
                 }
               }
            case 'L':
                for(int i = 0; i< SIZE; i++){
                 for(int j = 1; j< SIZE; j++){
                    if(board[i][j-1] == board[i][j]){
                        board[i][j-1] = new Tile(2*board[i][j+1].getValue());
                        board[i][j] = null;
                        change = true;
                    }
                 }
               }
    }
    return change;

    }
    
    public void move(){
        boolean change = moveMax(direction);
        while(change){
            change = moveMax(direction);
        }
        boolean doubleChecker = checkDouble(direction);
        while(doubleChecker){
            doubleChecker = moveMax(direction);
        }    
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

        return false;
        
    }

}

