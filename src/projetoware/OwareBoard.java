package projetoware;

public class OwareBoard {
	public Hole[] holes;

    public OwareBoard() {
        holes = new Hole[16];
        initializeBoard();
    }
    /**Initialize holes with numbers and seed counts for each color*/
    public void initializeBoard() {
		/*
		 * for (int i = 0; i < 8; i++) { holes[i] = new Hole(i + 1, 2, 2, 1); // 2 Red,
		 * 2 Blue, and 1 Transparent holes[i + 8] = new Hole(16 - i, 2, 2, 1); // 2 Red,
		 * 2 Blue, and 1 Transparent }
		 */
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
        System.out.println();
    }
    /**
     * @param holeNumber
     * the number of holes
     * @param seedColor
     * the color of the seed
     * @param distrubutionBehavior
     * @return 
     */
    public int makeMoveEvenplayer(int holeNumber, char seedColor,char distributionBehavior) {
    	
    	if (holeNumber % 2 == 0) {
    		Hole chosenHole = holes[holeNumber - 1];
            int seedsToDistribute = -500;
            
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
                	System.out.println("Invalid seed color!");
                    //throw new IllegalArgumentException("Invalid seed color!");
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
            return currentHole;
	    } else {
	    	//System.out.println("non");
	        throw new IllegalArgumentException("Second player can only choose even holes!");
	    }
    }
    /**
     * @param holeNumber
     * the number of holes
     * @param seedColor
     * the color of the seed
     * @param distributionBehavior
     * @return 
     */
    public int makeMoveComputer(int holeNumber, char seedColor,char distributionBehavior) {
    	
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
            	
            	
            	currentHole = (currentHole % 16) + 1;
            	
            	
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
            return currentHole;
	    } 
    	else {
	        throw new IllegalArgumentException(" player can only choose odd holes!");
	    }
    	
    }
	
	
}

