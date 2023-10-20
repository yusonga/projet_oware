package com.code;

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

    public int getNumber() {
        return number;
    }

    public int getRedSeeds() {
        return redSeeds;
    }

    public int getBlueSeeds() {
        return blueSeeds;
    }

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

    public OwareBoard() {
        holes = new Hole[16];
        initializeBoard();
    }
    public void initializeBoard() {
        // Initialize holes with numbers and seed counts for each color
		/*
		 * for (int i = 0; i < 8; i++) { holes[i] = new Hole(i + 1, 2, 2, 1); // 2 Red,
		 * 2 Blue, and 1 Transparent holes[i + 8] = new Hole(16 - i, 2, 2, 1); // 2 Red,
		 * 2 Blue, and 1 Transparent }
		 */
        for (int i = 0; i < 16; i++) {
            holes[i] = new Hole(i + 1, 4, 4, 1); // 2 Red, 2 Blue, and 1 Transparent
             }
        

    }

    public void displayBoard() {
        // Print the board using the Hole objects
        for (int i = 0; i<8; i++) {
           
            	System.out.print(holes[i]);
            	System.out.print("  ");
        }
        System.out.println();
        for (int i = 15; i>=8; i--) {
            
        	System.out.print(holes[i]);
        	System.out.print("  ");
        }
        System.out.println();
    }
    
    public void makeMoveEvenplayer(int holeNumber, char seedColor,char distributionBehavior) {
    	
    	if (holeNumber % 2 == 0) {
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
            while (seedsToDistribute > 0) {
            	
            	
            	currentHole = (currentHole % 16) + 1;
            	
            	
            	Hole hole = holes[currentHole - 1];
                


                if (distributionBehavior == 'B' && (currentHole % 2 == 0)) {
                    continue; // Skip player's own holes when distributing like blue seeds
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
	    } else {
	        throw new IllegalArgumentException("Second player can only choose even holes!");
	    }
    }
    public void makeMoveComputer(int holeNumber, char seedColor,char distributionBehavior) {
    	
    	if (holeNumber % 2 != 0) {
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
            while (seedsToDistribute > 0) {
            	
            	
            	currentHole = (currentHole % 16);
            	
            	
            	Hole hole = holes[currentHole - 1];
                


                if (distributionBehavior == 'B' && (currentHole % 2 != 0)) {
                    continue; // Skip player's own holes when distributing like blue seeds
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
	    } else {
	        throw new IllegalArgumentException(" player can only choose odd holes!");
	    }
    }
	
	
}



class Game {
    private OwareBoard board;

    public Game() {
        this.board = new OwareBoard();
    }

    public void playEvenMove() {
        Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Player even plays ");
        String input = scanner.next();

        int holeChoice;
        char seedColor;
        char distributionBehavior;
        if (input.length() == 2) {
            holeChoice = Integer.parseInt(input.substring(0, 1)); // Extract the hole number
            seedColor = input.charAt(1); // Extract the seed color
			/*
			 * System.out.println(seedColor);
			 * 
			 * System.out.print(board.holes[holeChoice-1]);
			 */
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
        	
        		 throw new IllegalArgumentException("no seeds of this color!");
        	}
        
        board.makeMoveEvenplayer(holeChoice, seedColor, distributionBehavior);


	

        System.out.println("The sowing leads to  ");
        board.displayBoard();
        
    }
    
    
    
    
    
    public void playComputerMove() { 
	// Choose a random odd hole and a random seed color for the computer's move
	 
	  
	  Random rand = new Random();
	  char distributionBehavior ;
	
		// Choose a random odd hole (1, 3, 5, ..., 15)
	  int chosenHole = rand.nextInt(8) * 2 + 1;
	
	  //char[] seedColors = {'R', 'B', 'T'};
	// Choose a random seed color
	  //char seedColor = seedColors[rand.nextInt(3)];
	  char seedColor='B';
	
	// If chosen seed is transparent, choose distribution behavior randoml
	  
	  if(seedColor!='T')
	  {
		  distributionBehavior=seedColor;
		  
	  }else {
		  distributionBehavior = (seedColor == 'T'&& rand.nextBoolean()) ? 'R':'B';
		  
	  }
	  //char distributionBehavior = (seedColor == 'T'&& rand.nextBoolean()) ? 'R':'B';
	  
	  
	  if((seedColor=='B'&&board.holes[chosenHole-1].getBlueSeeds()==0)
      	||(seedColor=='R'&&board.holes[chosenHole-1].getRedSeeds()==0)
      	||(seedColor=='T'&&board.holes[chosenHole-1].getTransparentSeeds()==0)) {
      	
      		 throw new IllegalArgumentException("no seeds of this color!");     	}
	  
	  
	  board.makeMoveComputer(chosenHole, seedColor, distributionBehavior);
	  
	  System.out.println(" Computer plays " + chosenHole + " " + seedColor + (seedColor == 'T' ? distributionBehavior : ""));

	  
	  board.displayBoard(); 
	}
}



public class Main {
	
	public static void main(String[] args) {
		
		//OwareBoard owareBoard = new OwareBoard();
		//System.out.print("The result is: \n");
        //owareBoard.displayBoard();
		Game game = new Game();
		int i=0;
		while(i<20) {
			game.playEvenMove();
	        game.playComputerMove();
	        i++;
		}
		
		
        //System.out.println("Initial Board:");
       
        
    }
}



