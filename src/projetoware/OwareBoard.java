package projetoware;

public class OwareBoard {
	
	
	public Hole[] holes;
    public Game game;
    
    public OwareBoard() {
        holes = new Hole[16];
        initializeBoard();
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
        System.out.println();
    }
    
    /**Clone method to create a deep copy of the OwareBoard object*/
//    public OwareBoard clone() {
//        OwareBoard clonedBoard = new OwareBoard();
//        for (int i = 0; i < 16; i++) {
//            Hole originalHole = this.holes[i];
//            clonedBoard.holes[i] = new Hole(originalHole.number, originalHole.redSeeds, originalHole.blueSeeds, originalHole.transparentSeeds);
//        }
//        // If the Game object has state that should be cloned as well, you should implement and use a clone method in the Game class too.
//        // For now, I'm just copying the reference:
//        clonedBoard.game = this.game;
//        return clonedBoard;
//    }
//    
//    //calculate total seeds on board 
    public int totalSeedsOnBoard() {
        int total = 0;
        for (Hole hole : holes) {
            total += hole.getRedSeeds() + hole.getBlueSeeds() + hole.getTransparentSeeds();
        }
        return total;
    }
    /**
     * @param holeNumber
     * the number of holes
     * @param seedColor
     * the color of the seed
     * @param distributionBehavior
     * @return 
     */
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
            while (seedsToDistribute > 0) {
            	
            	
            	currentHole = (currentHole % 16) + 1;
            	
            	
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
    
//    public boolean isFinalPosition(OwareBoard board) {
//        // Check if the game has reached a terminal state If total seeds on the board are less than 10, it's a terminal state
//        if (board.totalSeedsOnBoard() < 10) {
//            return true;
//        }
//        // Add other conditions as necessary
//        return false;
//    }

//    public int evaluateBoard(OwareBoard board) {
//        // A simple evaluation can be the difference in seeds captured by each player.
//        // Assuming you have methods or properties that track seeds captured by each player.
//    	int score = 0;
//
//        // 1. Seeds Captured
//        score += board.seedsComputer - board.seedsPlayer;
//
//        for (int i = 0; i < 16; i++) {
//            Hole hole = board.holes[i];
//            if ((hole.redSeeds + hole.blueSeeds + hole.transparentSeeds) == 2 || 
//                (hole.redSeeds + hole.blueSeeds + hole.transparentSeeds) == 3) {
//                // 2. Potential Captures
//                if (i % 2 == 0) { // Computer's holes are even indexed
//                    score += hole.redSeeds + hole.blueSeeds + hole.transparentSeeds;
//                } else {
//                    score -= hole.redSeeds + hole.blueSeeds + hole.transparentSeeds;
//                }
//            }
//        }
//
//        // 3. Starvation
//        int playerSeeds = 0;
//        int computerSeeds = 0;
//        for (int i = 0; i < 8; i++) {
//            playerSeeds += board.holes[i].redSeeds + board.holes[i].blueSeeds + board.holes[i].transparentSeeds;
//        }
//        for (int i = 8; i < 16; i++) {
//            computerSeeds += board.holes[i].redSeeds + board.holes[i].blueSeeds + board.holes[i].transparentSeeds;
//        }
//        if (playerSeeds == 0) {
//            score -= 50;  // Heavy penalty if the computer has starved the player
//        }
//        if (computerSeeds == 0) {
//            score += 50;  // Heavy reward if the player has starved the computer
//        }
//
//        // 4. Game Termination
//        if (board.totalSeedsOnBoard() < 10) {
//            score += (board.seedsComputer - board.playEvenMove(scoreJ2);) * 10;  // Multiplier to accentuate the difference
//        }
//
//        return score;
//    }
    
    
//    public boolean isValidMove(OwareBoard board, boolean computerPlay, int i) {
//        if (computerPlay && i % 2 != 0) { // If computer is playing and hole is odd
//            return false;
//        }
//        if (!computerPlay && i % 2 == 0) { // If player is playing and hole is even
//            return false;
//        }
//        return board.holes[i].computeSum() > 0; // Check if the hole has seeds
//    }
//	
//	
//	public int minMaxValue(OwareBoard board, boolean computerPlay, int depth, int depthMax, int alpha, int beta) {
//		char[] possibleSeedColors = {'R', 'B', 'T'};
//        char[] possibleDistributions = {'R', 'B'}; // Assuming 'T' will decide its behavior as either 'R' or 'B'
//
////		if (isFinalPosition(board) || depth == depthMax) {
////	        return evaluateBoard(board);
////	    }
//	    
//	    if (computerPlay) {
//	        int maxEval = Integer.MIN_VALUE;
//	        for (int i = 1; i <= 16; i += 2) {
//	        	for (char seedColor : possibleSeedColors) {
//	                for (char distribution : possibleDistributions) {			       
//		                OwareBoard nextBoardState = board.clone();
//		                nextBoardState.makeMove( i, seedColor, distribution);
//		                int eval = minMaxValue(nextBoardState, false, depth + 1, depthMax, alpha, beta);
//		                maxEval = Math.max(maxEval, eval);
//		                alpha = Math.max(alpha, eval);
//		                if (beta <= alpha) {
//		                    break; // Beta cut-off			                
//			            }
//	                }
//	        	}
//	        }
//	        return maxEval;
//	    } else {
//	        int minEval = Integer.MAX_VALUE;
//	        for (int i = 1; i <= 16; i += 2) {
//	        	for (char seedColor : possibleSeedColors) {
//	                for (char distribution : possibleDistributions) {
//			            if (isValidMove(board, false, i)) {
//			                OwareBoard nextBoardState = board.clone();
//			                nextBoardState.makeMove( i, seedColor, distribution);
//			                int eval = minMaxValue(nextBoardState, true, depth + 1, depthMax, alpha, beta);
//			                minEval = Math.min(minEval, eval);
//			                beta = Math.min(beta, eval);
//			                if (beta <= alpha) {
//			                    break; // Alpha cut-off
//			                }
//			            }
//	                }
//	        	}
//	        }
//	        return minEval;
//	    }
//	}
    
	
	
} 
  
