package com.code;

class Hole {
    private int number;
    private int redSeeds;
    private int blueSeeds;
    private int transparentSeeds;

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
        for (int i = 0; i < 8; i++) {
            holes[i] = new Hole(i + 1, 2, 2, 1); // 2 Red, 2 Blue, and 1 Transparent
            holes[i + 8] = new Hole(16 - i, 2, 2, 1); // 2 Red, 2 Blue, and 1 Transparent
        }
        holes[4]=new Hole(5,0,0,0);
        holes[5]=new Hole(6,0,0,1);
        holes[6]=new Hole(7,0,1,1);
        holes[7]=new Hole(8,1,0,0);
        holes[1]=new Hole(2,1,0,1);
        holes[2]=new Hole(3,1,1,0);
        holes[3]=new Hole(4,0,1,0);

    }

    public void displayBoard() {
        // Print the board using the Hole objects
        for (int i = 0; i < 16; i++) {
            System.out.print(holes[i]);
            if ((i + 1) % 8 == 0) {
                System.out.println(); // New line after every 8 holes
            } else {
                System.out.print("  ");
            }
        }
    }
	
	
}



class Game{
	
    
}

public class Main {
	public static void main(String[] args) {
		OwareBoard owareBoard = new OwareBoard();
        owareBoard.displayBoard();
        
    }
}



