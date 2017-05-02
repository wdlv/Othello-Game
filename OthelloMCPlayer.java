import  java.util.Random;
import  java.util.List;



public class OthelloMCPlayer extends OthelloPlayer {
	
	int iterations;
	Random r = new Random();
	
	public OthelloMCPlayer(int inputIteration)
	{
		iterations = inputIteration;
	}
	
	// Create a game tree node from the given state
	public MCNode createNode(OthelloState state) {
		MCNode rootNode = new MCNode(state);
		return rootNode;
	}
	
	public OthelloMove getMove(OthelloState state) {
		
		MCNode root = createNode(state);
		
		// Determine if the node have any child or moves
		if (root.moves.isEmpty()) {
			return null;
		}
		
		for (int i = 0; i < iterations; i++) {
			
			MCNode node = treePolicy(root);
			
			if (node != null) {
				OthelloState node2state = defaultPolicy(node);
				
				int node2Score = node2state.score();
				node.backup(node, node2Score);
			}
		}
		
		return root.bestChild(root).step;
	}
	
	
	
	public MCNode treePolicy(MCNode node) {
		
		// Determine if the node have any child or moves
		if (node.moves.isEmpty()) {
			return node;
		}
		
		if (node.state.gameOver()) {
			
			return node;
		}
		// Determine if any of the node's children are not in the tree
		if (node.children.size() < node.moves.size()) {
			MCNode newnode = new MCNode(node, node.moves.get(node.children.size()));
			node.children.add(newnode);
			
			return newnode;
		}
		
		// Epsilon greedy strategy
		MCNode nodetmp;
		
		if (r.nextInt(10) <= 9) {
					
			nodetmp = node.bestChild(node);		
			
		} else {
			nodetmp = node.children.get(r.nextInt(node.children.size()));

		}
		
		return treePolicy(nodetmp);
	}
	
	// Use random agents to do random actions and return the final state of the game.
	public OthelloState defaultPolicy(MCNode node) {
			
		OthelloState newState = node.state.clone();
		
		while (!newState.gameOver()) {
			List<OthelloMove> someMoves = newState.generateMoves();
			
			if (someMoves.size() > 0) {
				newState.applyMove(someMoves.get(r.nextInt(someMoves.size())));
			} else {
				newState.applyMove(null);
			}
		}
		
		return newState;
	}
	
}
