
public class Test {
    
    
    public static void main(String args[]) {
    	
    	 int win = 0;
         int lose = 0;
         int draw = 0;
         double prob = 0;
         for (int i = 0; i < 100; i++) {
    	
        // Create the game state with the initial position for an 8x8 board:
        OthelloState state = new OthelloState(8);
        
        int searchDepth = 4;
        int iterations = 1000;
       
       
        OthelloPlayer players[] = {new OthelloMCPlayer(iterations),
                                   new OthelloMMPlayer(searchDepth)};
     
        
        do{
            // Display the current state in the console:
            System.out.println("\nCurrent state, " + OthelloState.PLAYER_NAMES[state.nextPlayerToMove] + " to move:");
            System.out.print(state);
            
            
            OthelloMove move = players[state.nextPlayerToMove].getMove(state);            
            System.out.println(move);
            state = state.applyMoveCloning(move);            
        }while(!state.gameOver());

        // Show the result of the game:
        System.out.println("\nFinal state with score: " + state.score());
        System.out.println(state);
        
        if (state.score() > 0) win++;
        if (state.score() < 0) lose++;
        if (state.score() == 0) draw ++;
        }
         
        prob = win/100.0;
        		
        System.out.println("Player 1 Win " + win + ", " + "Lose " + lose + ", " + "Draw " + draw + ", " + "Win Probablity " + prob );
    }    
           
    
}
