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
    
    //Adds a new tile to the board;
    public void newTile(){
        int emptyX, emptyY;
        do {
            emptyX = random.nextInt(SIZE); 
            emptyY = random.nextInt(SIZE);
        } while (this.board[emptyX][emptyY] != null); 
        double randomNum = random.nextDouble(1);
        if(randomNum<0.85){
        this.board[emptyX][emptyY] = new Tile(2);
        }else{
            this.board[emptyX][emptyY] = new Tile(4);
        }
    }

    
    //helper function for move()
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
    

    //Also a helper function for move()
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

    public boolean legalMove(){
        switch(direction){
            case 'D':
               for(int i = 0; i< SIZE; i++){
                 for(int j = 0; j< SIZE-2; j++){
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
    
    
    //checks for legal move
    public boolean gridHasSpace(){
        for(int i = 0; i< Board.SIZE; i++){
            for(int j = 0; j<Board.SIZE; j++){
                if(this.board[i][j] == null){
                    return true;
                }else if(i>0){
                    if(this.board[i][j].getValue() == this.board[i-1][j].getValue()){
                    return true;
                    }
                }else if(j>0){
                    if(this.board[i][j].getValue() == this.board[i][j-1].getValue()){
                    return true;
                }
            }
        }
    }

        return false;
    }

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

    
    //To play on the terminal
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

