import java.util.Random;

public class Board {
    
    Tile board[][];
    static final int SIZE = 4;
    private static final double TILE_2_PROBABILITY = 0.85;
    char direction;
    Random random;
    

    public Board(){
        this.board = new Tile[SIZE][SIZE];
        random = new Random();
    }
    
    //Adds a new tile to the board;
    public void newTile(){
        int emptyX, emptyY;

        //searches for an empty cell on the board
        do {
            emptyX = random.nextInt(SIZE); 
            emptyY = random.nextInt(SIZE);
        } while (this.board[emptyX][emptyY] != null); 

        //assigns randomly a tile of value 2 or 4
        double randomNum = random.nextDouble(1);
        int tileValue = (randomNum < TILE_2_PROBABILITY) ? 2 : 4;
        this.board[emptyX][emptyY] = new Tile(tileValue);
        
    }

    
    //helper function for move() moves tile to the required direction
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
                 for(int j = SIZE-2; j>= 0; j--){
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
    

    //After all tiles have been moved we need to check if there are two adajcent tile with the same value, double one and remove the other
    public boolean checkDouble(char direction){

        boolean change = false;
        switch(direction){
            case 'U':
               for(int i = 0; i< SIZE; i++){
                 for(int j = 1; j< SIZE; j++){
                    if(board[j][i]!=null && board[j-1][i] != null && board[j-1][i].getValue() == board[j][i].getValue()){
                        board[j-1][i] = new Tile(2*(board[j][i].getValue()));
                        board[j][i] = null;
                        change = true;
                    }
                 }
               }
               break;
            case 'D':
                for(int i = 0; i< SIZE; i++){
                 for(int j = SIZE-2; j>= 0; j--){
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
    
    //moves tiles to the appropiate side;
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
 
    
    //sets the direction
    public void setDirection(char dir){
        this.direction = dir;
    }

    //Checks if the move the user wants to make can be done, ie if after pressing a key the board has changed in any way.
    //The move is legal if there is an empty space in the desired direction, or if there are two tile with the same value 
    //in the desire direction (eg if UP then twin tiles should be vertically arranged)
    public boolean legalMove(){
        switch(direction){
            case 'D':
               for(int i = 0; i< SIZE; i++){
                 for(int j = 0; j< SIZE-1; j++){
                    if(board[j][i]!= null && (board[j+1][i] == null || board[j+1][i].getValue() == board[j][i].getValue())){
                    return true;
                 }
               }
            }
               break;
            case 'U':
                for(int i = 0; i< SIZE; i++){
                 for(int j = SIZE-1; j>0; j--){
                    if(board[j][i]!= null && (board[j-1][i] == null || board[j-1][i].getValue() == board[j][i].getValue())){
                       return true;
                 }
                 }
            }  
               break;
            case 'L':
                for(int i = 0; i< SIZE; i++){
                 for(int j = SIZE-1; j>0; j--){
                    if(board[i][j]!= null && (board[i][j-1] == null || board[i][j-1].getValue() == board[i][j].getValue())){
                    return true;
                 }
                 }
                }
               break;
            case 'R':
                for(int i = 0; i< SIZE; i++){
                 for(int j = 0; j< SIZE-1; j++){
                   if(board[i][j]!= null && (board[i][j+1] == null || board[i][j+1].getValue() == board[i][j].getValue())){
                    return true;
                 }
                }
            }
        }
        
        return false;
        }
    
    
    //checks if grid has space or if it is game over
    public boolean gridHasSpace(){
        for(int i = 0; i< Board.SIZE; i++){
            for(int j = 0; j<Board.SIZE; j++){
                if(this.board[i][j] == null){
                    return true;
                }
                if(i>0){
                    if(this.board[i][j].getValue() == this.board[i-1][j].getValue()){
                    return true;
                    }
                }
                if(j>0){
                    if(this.board[i][j].getValue() == this.board[i][j-1].getValue()){
                    return true;
                }
            }
        }
    }

        return false;
    }


    //User wins once 2048 is reached, adn game terminates if 8192 reached
    public int numReach(){
        for(int i = 0; i< Board.SIZE; i++){
            for(int j = 0; j< Board.SIZE; j++){
                if(this.board[i][j].getValue() == 2048){
                    return 1;
                }else if(this.board[i][j].getValue() == 8192){
                    return 2;
                }
            }
        }
        return 0;
    }

    
    //Displays board if playing on the terminal
    public void displayBoard(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(this.board[i][j] == null){
                    System.out.print(0 + "  "); 
                }else{
                System.out.print(this.board[i][j].getValue() + "  ");
                }
            }
            System.out.println(" ");
        }
    }

    

}

