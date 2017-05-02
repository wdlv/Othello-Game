import java.util.List;
import java.util.Random;


public class OthelloRandomPlayer extends OthelloPlayer {
    Random r = new Random();
   
    public OthelloMove getMove(OthelloState state) {
        // generate the list of moves:
        List<OthelloMove> moves = state.generateMoves();           
              
        // return one at random:
        if (!moves.isEmpty()) return moves.get(r.nextInt(moves.size()));
        
        // If there are no possible moves, just return "pass":
        return null;
    }
    
}
