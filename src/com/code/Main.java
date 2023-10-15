package com.code;

import java.util.Random;

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
	private Hole[] holes;

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
            holes[i] = new Hole(i + 1, 2, 2, 1); // 2 Red, 2 Blue, and 1 Transparent
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
    
    public void makeMove(int holeNumber, char seedColor) {
    	
    	if (holeNumber % 2 == 0) {
    		Hole chosenHole = holes[holeNumber - 1];
            int seedsToDistribute;
            char distributionBehavior = seedColor; // Default to seedColor

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
                    Random rand = new Random();
                    distributionBehavior = rand.nextBoolean() ? 'R' : 'B'; // Randomly choose to distribute like red or blue
                    break;
                default:
                    throw new IllegalArgumentException("Invalid seed color!");
            }

            int currentHole = holeNumber;
            while (seedsToDistribute > 0) {
            	// Move clockwise
            	
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
	
	
}



class Game {
    private OwareBoard board;

    public Game() {
        this.board = new OwareBoard();
    }

    public void playMove() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Player even plays ");
        String input = scanner.next();

        int holeChoice = Character.getNumericValue(input.charAt(0)); // Extract the hole number
        char seedColor = input.charAt(1); // Extract the seed color

        board.makeMove(holeChoice, seedColor);

		/*
		 * String colorPlayed; switch (seedColor) { case 'R': colorPlayed = "Red";
		 * break; case 'B': colorPlayed = "Blue"; break; case 'T': colorPlayed =
		 * "Transparent"; break; default: colorPlayed = ""; }
		 */

        System.out.println("The sowing leads to  ");
        board.displayBoard();
        scanner.close();
    }
}

public class Main {
	public static void main(String[] args) {
		//OwareBoard owareBoard = new OwareBoard();
		//System.out.print("The result is: \n");
        //owareBoard.displayBoard();
        Game game = new Game();
        //System.out.println("Initial Board:");
        game.playMove();
        
    }
}



