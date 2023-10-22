package projetoware;

public class Hole {
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

