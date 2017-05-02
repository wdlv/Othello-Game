import  java.util.List;
import  java.util.ArrayList;


public class MCNode {
	
	// State means board
	OthelloState state;
	
	// The move to reach this node
	OthelloMove step;
	
	// All the moves that node can take 
	List<OthelloMove> moves;
	MCNode parent;
	
	// Node's sub nodes
	List<MCNode> children;
	
	// Number of the time the node has been visited
	int numVisited;
	
	// Average score found so far for this node
	double averageScore;
	int totalScore;
	
	// Create a game root node with a given state
	public MCNode (OthelloState inputState) {
		state = inputState;
		moves = state.generateMoves();	
		children = new ArrayList<MCNode>();
		step = null;
		parent = null;
		numVisited = 0;
		averageScore = 0;
		totalScore = 0;
		
	}
	
	// Create a child node with a node and a move
	public MCNode (MCNode node, OthelloMove move) {
		
		state = node.state.applyMoveCloning(move);
		parent = node;
		step = move;
		children = new ArrayList<MCNode>();
		moves = state.generateMoves();
	}
	
	// Propogate scores to all the related nodes
	public void backup(MCNode node, int score) {
		numVisited ++;
		totalScore = totalScore + score;
		averageScore = totalScore/numVisited;
		
		if (node.parent != null) {
			backup(node.parent, score);
		}
		
	}
	
	// Find the child node with the highest score when given a parent node
	public MCNode bestChild(MCNode node) {
		int index = 0;
		if (node.state.nextPlayerToMove == state.PLAYER1) {
			
			double maxAverageScore = Integer.MIN_VALUE;
												
			
			for (int i = 0; i < node.children.size(); i++) {
				
				double temp = node.children.get(i).averageScore;
				
				if (temp > maxAverageScore) {
					maxAverageScore = temp;
					index = i;
				}
				
			}
		} else {
			double minAverageScore = Integer.MAX_VALUE;
			
			for (int i = 0; i < node.children.size(); i++) {
				
				double temp = node.children.get(i).averageScore;
				
				if (temp < minAverageScore) {
					minAverageScore = temp;
					index = i;
				}
				
			}
		}
		
		return node.children.get(index);
	}
	

}
