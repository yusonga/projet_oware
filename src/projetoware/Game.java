package projetoware;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	    private OwareBoard board;
	public Game() {
	    this.board = new OwareBoard();
	}
	
	public int playEvenMove(int score) {
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
	    
	   
	    int finalHole = board.makeMoveEvenplayer(holeChoice, seedColor, distributionBehavior);
	    score += capture(finalHole);
	    System.out.println("The sowing leads to  ");
	    board.displayBoard();
	    return score;
	    
	    
	}
	/**Choose a random odd hole and a random seed color for the computer's move*/
	public int playComputerMove(int score) { 
		ArrayList<Character> seedColors = new ArrayList<>();
		Random random = new Random();
		int chosenHole;
		do {
		//random = new Random();
	
		// Choose a random odd hole (1, 3, 5, ..., 15)
		chosenHole = random.nextInt(8) * 2 + 1;
	
	  
		//there is at less 1 red seed in the hole
		if (board.holes[chosenHole-1].getRedSeeds()>0) {
		  seedColors.add('R');
	  }
		//there is at less 1 blue seed in the hole
		if (board.holes[chosenHole-1].getBlueSeeds()>0) {
		  seedColors.add('B');
		}
		//there is at less 1 tranparent seed in the hole
		if (board.holes[chosenHole-1].getTransparentSeeds()>0) {
		  seedColors.add('T');
		}
		}while(seedColors.size()==0);
		// Choose a random seed color avalable
		int randomIndex = random.nextInt(seedColors.size());
	
		// get random color
		char seedColor = seedColors.get(randomIndex);
	
		char distributionBehavior;
		// If chosen seed is transparent, choose distribution behavior randoml
		if (seedColor == 'T') {
			distributionBehavior = (seedColor == 'T' && random.nextBoolean()) ? 'R' : 'B';
		}
		else {
			distributionBehavior =seedColor;
		}
		if((seedColor=='B'&&board.holes[chosenHole-1].getBlueSeeds()==0)
				||(seedColor=='R'&&board.holes[chosenHole-1].getRedSeeds()==0)
				||(seedColor=='T'&&board.holes[chosenHole-1].getTransparentSeeds()==0)) {
	  	
	  		 throw new IllegalArgumentException("no seeds of this color!");
	  	}
	  
		int finalHole = board.makeMoveComputer(chosenHole, seedColor, distributionBehavior);
		System.out.println("Computer plays " + chosenHole + " " + seedColor + (seedColor == 'T' ? distributionBehavior : ""));
		score += capture(finalHole);
		board.displayBoard(); 
		return score;
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
		
		//boucle provisoir
		for (int i=0;i<10;i++) {
		
		
		
			if (sum ==3 || sum ==2) {
				score += sum;		
				board.holes[realFinalHole].emptyTheHole();
				sum = board.holes[realFinalHole-i].computeSum();
			}
			//sum!= 3 or !=2
			else {
				return score;
			}
		}
		
		return score;
		
	}


}