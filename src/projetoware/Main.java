package projetoware;





public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		//int i=0;
		int scoreJ1 = 0;
		int scoreJ2 = 0;
		while((scoreJ1!=40 &&scoreJ2 !=40)|| (scoreJ1>=41 ||scoreJ2>=41)) {
			scoreJ1 = game.playEvenMove(scoreJ1);
	        scoreJ2 = game.playComputerMove(scoreJ2);
	        System.out.println("scoreJ1: "+scoreJ1);
	        System.out.println("scoreJ2: "+scoreJ2);
		}
	}

}
