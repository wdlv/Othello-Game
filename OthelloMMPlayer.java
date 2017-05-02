
import java.util.List;

public class OthelloMMPlayer extends OthelloPlayer {
	
	int depth;
	
	
	public OthelloMMPlayer() { }
	public OthelloMMPlayer (int depthDesired) {
		this.depth = depthDesired;
	}

	public OthelloMove getMove(OthelloState state) {
        
		List<OthelloMove> moves = state.generateMoves();
		
		if (moves.isEmpty())
		{return null;}
		
		OthelloMove bestMove = moves.get(0);
		
		// Find the best score depends on which player is playing. Max for player 1 and Min for player 2
		int bestScore = miniMax(state.applyMoveCloning(bestMove), depth -1);
		
		for (int i = 0; i < moves.size(); i++) {
			
			OthelloMove currentMove = moves.get(i);
			 int currentScore = miniMax(state.applyMoveCloning(currentMove), depth -1);
        	 // Current player is player 1, Max       	       	
        	if (state.otherPlayer(state.applyMoveCloning(moves.get(i)).nextPlayerToMove) == state.PLAYER1) {
                		        		
        		if (currentScore > bestScore) {
        			bestScore = currentScore;
          			
        			bestMove = currentMove;
        		}
        	} else {        		
        		
                // Current player is play 2, Min
        		if (currentScore < bestScore) {
        			bestScore = currentScore;
        			bestMove = currentMove;
        		}
        		
        	}									
		}
		
		return bestMove;  
	
}
	
				
	public int miniMax(OthelloState state, int depth) {
			
		  if (state.gameOver() || depth == 0) {			  	
	        	
			  return state.score();
		    		 
		    	}
		  		  		  		
		  // generate the list of moves:
        List<OthelloMove> moves = state.generateMoves(); 
        
        if (moves.isEmpty()) { return state.score(); }
         // 0 is player 1, the Max player. On this condition, best score will get the minimum value. Otherwise it is player 2 and best score get the maximum value.  
        int bestScore = (state.nextPlayerToMove == 0) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        
                      
        	for (int i = 0; i < moves.size(); i++) {
        		
        		int currentScore =	miniMax(state.applyMoveCloning(moves.get(i)), depth -1);
        	// applyMoveCloning function will change nextPlayerTo Move, so use otherPlayer to get back the real current player       	
        	if (state.otherPlayer(state.applyMoveCloning(moves.get(i)).nextPlayerToMove) == state.PLAYER1) {
        		       		
        		if (currentScore > bestScore) {
        			bestScore = currentScore;
          			
        		}
        	} else { 
        		            		       
        		if (currentScore < bestScore) {
        			bestScore = currentScore;
        			
        		}
        		
        	}
        	        	
        } 
			       
		return bestScore;
	}
		
		
}

