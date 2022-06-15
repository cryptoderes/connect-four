import java.util.LinkedList;
import java.util.List;

public class Connect4 {
  //create board state
    int[][] board = new int[6][7];
//create List to track moves
    List<String> moveHistory = new LinkedList<String>();
    
    int winner = 0; 
    boolean player1 = true;
    
    public Connect4() {
        resetGame();
    }
    
//method for placing a piece given player and column
    public boolean putPiece(int column) {
        int x = 5;
        int y = column;
        
        while (board[x][y] != 0){
                x--;
        } 
        
        if (x >= 0) {
            if (player1) {
                board[x][y] = 1; 
                String entry = Integer.toString(1) + " " + Integer.toString(x) + " " + Integer.toString(y);
                moveHistory.add(entry);
            } else {
                board[x][y] = 2; 
                String entry = Integer.toString(2) + " " + Integer.toString(x) + " " + Integer.toString(y);
                moveHistory.add(entry);
            } 
        }  
        player1 = !player1;
        return true;
    }

    public int getPiece(int j, int i) {
        return board[i][j];
    }
//method for reading history entries + extracting values from them
    public int getPlayer() {
        String entry = moveHistory.get(moveHistory.size()-1);
        String[] split = entry.split(" ");
        return Integer.parseInt(split[0]);
    }
    
    public int getX() {
        String entry = moveHistory.get(moveHistory.size()-1);
        String[] split = entry.split(" ");
        return Integer.parseInt(split[1]);
    }
    
    public int getY() {
        String entry = moveHistory.get(moveHistory.size()-1);
        String[] split = entry.split(" ");
        return Integer.parseInt(split[2]);
    }
   
//method for undoing a move (removes from history and clears location on board
    public void undo() {
        if (!checkwin()) {
            int x = getX();
            int y = getY();
            moveHistory.remove(moveHistory.size()-1);
            board[x][y] = 0;
            player1 = !player1;
        }
    }
 
//method for resetting board and history
    public void resetGame() {
        moveHistory.clear();
        winner = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = 0;
            }
        }
    }
    
//checks for a win
    public boolean checkwin() {
        int x = getX();
        int y = getY();
        int player = getPlayer();
        int count = 0;
        
        //horizontal check
        for (int i = 0; i < 6; i++) {
            if (board[x][i] == player) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 4) {
                winner = player;
                return true;
            }
        }
        
        //vertical check
        for (int i = 0; i < 6; i++) {
            if (board[i][y] == player) {
                count++;
            } else {
                count = 0; 
            }
            if (count >= 4) {
                winner = player;
                return true;
            }
        }
        
        //diagonal check like this: /
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == player && board[i-1][j+1] == player 
                        && board[i-2][j+2] == player && board[i-3][j+3] == player) {
                    return true;
                }
            }
        }
        
        //diagonal check like this: \
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 7; j++) {
                if (board[i][j] == player && board[i-1][j-1] == player 
                        && board[i-2][j-2] == player && board[i-3][j-3] == player) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //method for printing the board
    public void printGameState() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < 7) { 
                    System.out.print(" | "); 
                }
            }
            if (i < 6) {
                System.out.println("\n---------------------------"); 
            } 
        }
    }
    
    public int getWinner() {
        return winner;
    }
    
    public int getMoves () {
        return moveHistory.size();
    }
    
    /* public static void main(String[] args) {
        Board b = new Board();
        b.putPiece(1);
        b.printGameState();
        
        b.putPiece(2);
        b.printGameState();
        
        b.putPiece(1);
        b.printGameState();
        
        b.putPiece(2);
        b.printGameState();
        
        b.putPiece(1);
        b.printGameState();
        
        b.putPiece(2);
        b.printGameState();
        
        b.putPiece(1);
        b.printGameState();
        
        System.out.println();
        b.checkwin();
        System.out.println("The winner is player " + b.getWinner());
    } */
}
