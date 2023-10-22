package projetoware;





public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		
		//int i=0;
		int scoreJ1 = 0;
		int scoreJ2 = 0;
		boolean humanTurn = true;
		
		

		while((scoreJ1!=40 &&scoreJ2 !=40)|| (scoreJ1<41 ||scoreJ2<41)) {
			
			if (humanTurn) {
                // Get input from user and play human's move
				game.playEvenMove();
				humanTurn = false;
            } else {
            	
                // Computer's turn:
            	game.playComputerMove();
                
                // Play the best move found
//                game.board.makeMove(bestMove.holeNumber,bestMove.seedColor,bestMove.distributionBehavior);
                humanTurn = true;
            }
//			scoreJ2 = game.playEvenMove(scoreJ2);
//			scoreJ1 = game.playComputerMove(scoreJ1,oddPlaying);
			
//			scoreJ2 = game.playComputerMove(scoreJ2,oddPlaying);
//			oddPlaying =true;
//			scoreJ1 = game.playComputerMove(scoreJ1,oddPlaying);
//			oddPlaying=false;
			
			
	        
	}

 }
}