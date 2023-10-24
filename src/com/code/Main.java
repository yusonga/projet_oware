package com.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



class Hole {
	public int number;
    public int redSeeds;
    public int blueSeeds;
    public int transparentSeeds;

    public Hole(int number, int redSeeds, int blueSeeds, int transparentSeeds) {
        this.number = number;
        this.redSeeds = redSeeds;
        this.blueSeeds = blueSeeds;
        this.transparentSeeds = transparentSeeds;
    }
    
    /**Compute the sum in the hole
     * 
     * @return 
     * the number of seed in the hole
     * */
    public int computeSum() {
    	int res = getBlueSeeds() + getRedSeeds() + getTransparentSeeds();
    	return res;
    }
    /**all the seeds are 0*/
    public void emptyTheHole() {
    	redSeeds = 0;
    	blueSeeds = 0;
    	transparentSeeds = 0;
    }
    
    /**the number of the hole*/
    public int getNumber() {
        return number;
    }
    /**the number of red seeds*/
    public int getRedSeeds() {
        return redSeeds;
    }
    /**the number of blue seeds*/
    public int getBlueSeeds() {
        return blueSeeds;
    }
    /**the number of transparent seeds*/
    public int getTransparentSeeds() {
        return transparentSeeds;
    }
    

    @Override
    public String toString() {
    	if(transparentSeeds!=0&&blueSeeds!=0&&redSeeds!=0) {
    		return number + " (" + redSeeds + "R" + blueSeeds + "B" + transparentSeeds + "T)";
		}
    	if(redSeeds==0&&blueSeeds!=0&&transparentSeeds!=0)
    		{
    		return number + " ("+ blueSeeds + "B" + transparentSeeds + "T)";
    		}
    	if(blueSeeds==0&&redSeeds!=0&&transparentSeeds!=0) {
			return number + " (" + redSeeds + "R" + transparentSeeds + "T)";
    	}
		if(transparentSeeds==0&&blueSeeds!=0&&redSeeds!=0) {
			return number + " (" + redSeeds + "R" + blueSeeds + "B)";
		}
		if(redSeeds==0&&blueSeeds==0&&transparentSeeds!=0)
		{
		return number + " ("+  transparentSeeds + "T)";
		}
		if(blueSeeds==0&&redSeeds!=0&&transparentSeeds==0) {
			return number + " (" +  redSeeds + "R)";
		}
		if(transparentSeeds==0&&blueSeeds!=0&&redSeeds==0) {
			return number + " (" + blueSeeds + "B)";
		}
		
		else {
			return number + " ()";
		}
    }
}


class OwareBoard {
	public Hole[] holes;
    public Game game;
    
    public OwareBoard() {
        holes = new Hole[16];
        initializeBoard();
    }
    /**The clone method in the OwareBoard class should create a new OwareBoard object and copy all of its state from the current object to the new one. Each Hole object should also be a new object with the same state as in the current OwareBoard*/
    public OwareBoard clone() {
        OwareBoard newBoard = new OwareBoard();
        newBoard.holes = new Hole[16];
        for (int i = 0; i < 16; i++) {
            Hole oldHole = this.holes[i];
            newBoard.holes[i] = new Hole(oldHole.getNumber(), oldHole.getRedSeeds(),
                    oldHole.getBlueSeeds(), oldHole.getTransparentSeeds());
        }
        return newBoard;
    }
    
    /**Initialize holes with numbers and seed counts for each color*/
    public void initializeBoard() {
		
        for (int i = 0; i < 16; i++) {
            holes[i] = new Hole(i + 1, 2, 2, 1); // 2 Red, 2 Blue, and 1 Transparent
             }
        

    }
    /**Print the board using the Hole objects*/
    public void displayBoard() {
        for (int i = 0; i<8; i++) {
           
            	System.out.print(holes[i]);
            	System.out.print("  ");
        }
        System.out.println();
        for (int i = 15; i>=8; i--) {
            
        	System.out.print(holes[i]);
        	System.out.print("  ");
        }
       
    }
    
    public int totalSeedsOnBoard() {
        int total = 0;
        for (Hole hole : holes) {
            total += hole.computeSum();
        }
        return total;
    }
    
    public int makeMove(int holeNumber, char seedColor,char distributionBehavior) {
    	
		Hole chosenHole = holes[holeNumber - 1];
        int seedsToDistribute;
        
        switch (seedColor) {
            case 'R':
                seedsToDistribute = chosenHole.getRedSeeds();
                chosenHole.redSeeds = 0;
                break;
            case 'B':
                seedsToDistribute = chosenHole.getBlueSeeds();
                chosenHole.blueSeeds = 0;
                break;
            case 'T':
                seedsToDistribute = chosenHole.getTransparentSeeds();
                chosenHole.transparentSeeds = 0;
                break;
            default:
                throw new IllegalArgumentException("Invalid seed color!");
        }
        int currentHole = holeNumber;
       // System.out.println(currentHole);
       
        while (seedsToDistribute > 0) {
        	
        	
        	currentHole = (currentHole % 16) + 1;
        	//System.out.print(currentHole);
        	
        	
        	Hole hole = holes[currentHole - 1];
            
        	//player odd 
        	if (holeNumber % 2 != 0) {
        		if (distributionBehavior == 'B' && (currentHole % 2 != 0)) {
                    continue; // Skip player's own holes when distributing like blue seeds
                }
        	} else { // even player 
        		if (distributionBehavior == 'B' && (currentHole % 2 == 0)) {
                    continue; // Skip player's own holes when distributing like blue seeds
        		
        	}
        	}

            // Place the actual seed (which is seedColor) in the hole
            switch (seedColor) {
                case 'R':
                    hole.redSeeds++;
                    break;
                case 'B':
                    hole.blueSeeds++;
                    break;
                case 'T':
                    hole.transparentSeeds++;
                    break;
            }
            
            seedsToDistribute--;
        }
        
        return currentHole;
    
	
}
	
	
}


class Game {
	public OwareBoard board;
    public int scoreEven;   // Score for even player
    public int scoreOdd;    // Score for odd player (You can use this in a similar method for the odd player)

    
	public Game() {
	    this.board = new OwareBoard();
	    this.scoreEven = 0;
        this.scoreOdd = 0;
	   
	}
	
	
	public void playEvenMove() {
	    Scanner scanner = new java.util.Scanner(System.in);
	    System.out.print("Player even plays ");
	    
	    String input;
	    
	
	    int holeChoice;
	    char seedColor;
	    char distributionBehavior;
	    boolean validInput = false;
	    do {
	    // Convert input in upperCase
	    input = scanner.next().toUpperCase();	
	    
	    if (input.length() == 2) {
	        holeChoice = Integer.parseInt(input.substring(0, 1)); // Extract the hole number
	        seedColor = input.charAt(1); // Extract the seed color
	        distributionBehavior = seedColor; // Use the same behavior as seed color
	    } else if(input.length() == 4){ // 4-character input 16TB
	        holeChoice = Integer.parseInt(input.substring(0, 2)); // Extract the hole number
	        seedColor = input.charAt(2); // Extract the main seed color
	        distributionBehavior = input.charAt(3); // Extract the distribution behavior for transparent seeds
	    }else {
	    	seedColor = input.charAt(1);
	    	if(seedColor=='T') {
	    		holeChoice = Integer.parseInt(input.substring(0, 1)); // Extract the hole number
	            distributionBehavior = input.charAt(2);
	    	}else {
	    		holeChoice = Integer.parseInt(input.substring(0, 2)); // Extract the hole number
	            seedColor = input.charAt(2);
	            distributionBehavior = seedColor;
	    	}
	    	
	    }
	    
	    
	    if((seedColor=='B'&&board.holes[holeChoice-1].getBlueSeeds()==0)
	    		||(seedColor=='R'&&board.holes[holeChoice-1].getRedSeeds()==0)
	    		||(seedColor=='T'&&board.holes[holeChoice-1].getTransparentSeeds()==0)) {
	    		 System.out.println("no seeds of this color in this hole");
	    		
	    	}
	    else if((seedColor=='B'&&board.holes[holeChoice-1].getBlueSeeds()>0)
	    		||(seedColor=='R'&&board.holes[holeChoice-1].getRedSeeds()>0)
	    		||(seedColor=='T'&&board.holes[holeChoice-1].getTransparentSeeds()>0)) {
	    	validInput = true;
	    }
	    else {
	    		System.out.println("Not a valid color. You can play R:Red, B:Blue, T:Transparent");
	    	}
	}while(validInput==false);
	    
	   
	    int finalHole = board.makeMove(holeChoice, seedColor, distributionBehavior);
	    scoreEven += capture(finalHole);
	    System.out.println("The sowing leads to  ");
	    board.displayBoard();
	    System.out.println("Even Player Score: " + scoreEven);
	    
	    
	}
	
	public void playComputerMove() { 
		int depth = 3; //  adjust the depth as needed
	    Move bestMove = minimax(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
	    int finalHole =board.makeMove(bestMove.hole, bestMove.seedColor, bestMove.distributionBehavior);
	    scoreOdd += capture(finalHole);
	    System.out.println("Computer played " + bestMove);
	    board.displayBoard();
	    System.out.println("Computer Player Score: " + scoreOdd);
	}
	
	private int evaluateBoard() {
		int score = 0;

	    // Evaluate the current number of captured seeds
	    score += scoreOdd * 20; // Each captured seed adds 20 points for the computer
	    score -= scoreEven * 20; // Each captured seed subtracts 20 points for the computer

	    // Evaluate the potential for future captures
	    for (Hole hole : board.holes) {
	        int holeSum = hole.computeSum();
	        // Assign points if a hole has the potential to be captured in the next move
	        if (holeSum == 1 || holeSum == 2) {
	            if (hole.getNumber() % 2 != 0) { // Odd hole, belongs to the computer
	                score += 5; // Potential for the computer to capture in the next move
	            } else { // Even hole, belongs to the human
	                score -= 5; // Potential for the human to capture in the next move
	            }
	        }
	    }

	    // Evaluate based on the total number of seeds remaining on the board
	    int totalSeedsOnBoard = board.totalSeedsOnBoard();
	    if (totalSeedsOnBoard < 10) {
	        // If less than 10 seeds are remaining, the game is about to end
	        // Adjust the score to prioritize capturing the remaining seeds
	        score += (10 - totalSeedsOnBoard) * 5; 
	    }

	    return score;
	}
// The minimax method evaluates the moves and returns the best one for the current player. In this method, the computer is considered as the maximizing player, and the human is the minimizing player.
	private Move minimax(int depth, int alpha, int beta, boolean maximizingPlayer) {
	    if (depth == 0 || gameIsOver()) {
	        return new Move(evaluateBoard());
	    }

	    Move bestMove = new Move(maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE);

	    for (Move move : getAllPossibleMoves()) {
	        OwareBoard tempBoard = board.clone(); // a clone method to simulate the movement of computer and even player without changing the original board 
	        tempBoard.makeMove(move.hole, move.seedColor, move.distributionBehavior);
	        move.score = minimax(depth - 1, alpha, beta, !maximizingPlayer).score;
	        //
	        if (maximizingPlayer && move.score > bestMove.score) {
	            bestMove = move;
	            alpha = Math.max(alpha, bestMove.score);
	        } else if (!maximizingPlayer && move.score < bestMove.score) {
	            bestMove = move;
	            beta = Math.min(beta, bestMove.score);
	        }

	        if (beta <= alpha) {
	            break;
	        }
	    }
	    // bestmove is a class contain 3 value , nb holes, seed color and distributionBehavior
	    return bestMove;
	}

	//The game stops when there is strictly less than 10 seeds on the board. In this case, the remaining seeds are not take into account.
	private boolean gameIsOver() {
	     return board.totalSeedsOnBoard() < 10;
	}
	
	//generate all possible moves for the current board state 
	
	private List<Move> getAllPossibleMoves() {
		List<Move> moves = new ArrayList<>();
	    for (Hole hole : board.holes) {
	        if (hole.getNumber() % 2 != 0) { // Check if the hole number is odd for the computer
	            if (hole.getRedSeeds() > 0) {
	                moves.add(new Move(hole.getNumber(), 'R', 'R')); // Red seed with red distribution
	            }
	            if (hole.getBlueSeeds() > 0) {
	                moves.add(new Move(hole.getNumber(), 'B', 'B')); // Blue seed with blue distribution
	            }
	            if (hole.getTransparentSeeds() > 0) {
	                moves.add(new Move(hole.getNumber(), 'T', 'R')); // Transparent seed with red distribution
	                moves.add(new Move(hole.getNumber(), 'T', 'B')); // Transparent seed with blue distribution
	            }
	        }
	    }
	    return moves;
	}
	
	/**the capture fonction
	 * @param finalHole 
	 * the hole were we arrived
	 * @return 
	 * 
	 * */
	public int capture(int finalHole) {
		int score = 0;
		int realFinalHole = finalHole-1;
		int sum = board.holes[realFinalHole].computeSum();
		
		
		
		while (sum ==3 || sum ==2) {
			System.out.println(board.totalSeedsOnBoard());
			score += sum;		
			board.holes[realFinalHole].emptyTheHole();
			sum = board.holes[realFinalHole].computeSum();
			
			//caputer the seeds in reverse clockwise 
			if (realFinalHole == 1) {
				realFinalHole = 16;
	        } else {
	        	realFinalHole--;
	        }
			
			
		}
		return score;
	}
	
}

class Move {
	int hole;
    char seedColor;
    char distributionBehavior;
    int score;

    public Move(int score) {
        this.score = score;
    }

    public Move(int hole, char seedColor, char distributionBehavior) {
        this.hole = hole;
        this.seedColor = seedColor;
        this.distributionBehavior = distributionBehavior;
    }


    @Override
    public String toString() {
        return "Move " + hole + seedColor + distributionBehavior
                + ", score=" + score;
    }

}

public class Main {
	
	public static void main(String[] args) {
		
		
		Game game = new Game();
		
		
		int scoreJ1 = 0;
		int scoreJ2 = 0;
		boolean humanTurn = true;
		
		

		while((scoreJ1!=40 &&scoreJ2 !=40)|| (scoreJ1<41 ||scoreJ2<41)) {
			
			if (humanTurn) {
                // Get input from user and play human's move
				game.playEvenMove();
				humanTurn = false;
            } else {
            	
                // Computer's turn:
            	game.playComputerMove();
                
                // Play the best move found
                humanTurn = true;
            }
		
	        
	}

		
		
       
        
    }
}



