package projetoware;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	
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
	/**Choose a random odd hole and a random seed color for the computer's move*/
	
//	public Move computeBestMove() {
//        int bestValue = Integer.MIN_VALUE;
//        Move bestMove = null;
//
//        char[] possibleSeedColors = {'R', 'B', 'T'};
//        char[] possibleDistributions = {'R', 'B'}; // Assuming 'T' will decide its behavior as either 'R' or 'B'
//
//        // Loop over all possible moves (holes)
//        for (int i = 1; i <= 16; i += 2) { // Assuming computer always plays odd holes
//            for (char seedColor : possibleSeedColors) {
//                for (char distribution : possibleDistributions) {
//                    OwareBoard simulatedBoard = board.clone(); // Clone the current state
//                    simulatedBoard.makeMove(i, seedColor, distribution);
//                    int moveValue = simulatedBoard.minMaxValue(simulatedBoard, false, 1, 3, Integer.MIN_VALUE, Integer.MAX_VALUE); // Example depth of 5
//                    if (moveValue > bestValue) {
//                        bestValue = moveValue;
//                        bestMove = new Move(i, seedColor, distribution);
//                    }
//                }
//            }
//        }
//
//        return bestMove;
//    }
	public void playComputerMove() { 
		Scanner scanner = new java.util.Scanner(System.in);
	    System.out.print("computer plays ");
	    
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
	    scoreOdd += capture(finalHole);
	    System.out.println("The sowing leads to  ");
	    board.displayBoard();
	    System.out.println("Computer Player Score: " + scoreOdd);
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