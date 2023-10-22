package projetoware;

public class Move {
	public int holeNumber;
    public char seedColor;
    public char distributionBehavior;

    public Move(int holeNumber, char seedColor, char distributionBehavior) {
        this.holeNumber = holeNumber;
        this.seedColor = seedColor;
        this.distributionBehavior = distributionBehavior;
    }

}
