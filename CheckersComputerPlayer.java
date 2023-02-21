package core;

/**
 * This class generates moves to simulate a naive computer player, which the user can play against in a simple checkers game.
 * 
 * @author Cheng Zhang
 * @version 1/30/2023
 */
public class CheckersComputerPlayer 
{
	/**
	 * Generates moves for player O by first searching through possible moves, then searching through possible jumps.
	 * 
	 * @param game the instance of CheckersLogic
	 */
	public void move(CheckersLogic game) //Computer Player O
	{
		int fromRow = -1;
		int fromColumn = -1;
		int toRow = -1;
		int toColumn = -1;
		int clearRow = -1;
		int clearColumn = -1;
		
		for (int i = 0; i < 7; i++) //rows excluding x end
		{
			for (int j = 1; j < 7; j++) //columns excluding edges
			{
				if (game.getBoard(i, j) == 'o' && game.getBoard(i+1, j-1) == '_') //check for available o move
				{
					fromRow = i; 
					fromColumn = j;
					toRow = i + 1;
					toColumn = j - 1;
				}
				
				if (game.getBoard(i, j) == 'o' && game.getBoard(i+1, j+1) == '_') //check for available o move
				{
					fromRow = i; 
					fromColumn = j;
					toRow = i + 1;
					toColumn = j + 1;
				}
			}
			
			if (game.getBoard(i, 0) == 'o' && game.getBoard(i+1, 1) == '_') //check for available o move in leftmost column
			{
				fromRow = i; 
				fromColumn = 0;
				toRow = i + 1;
				toColumn = 1;
			}
			
			if (game.getBoard(i, 7) == 'o' && game.getBoard(i+1, 6) == '_') //check for available o move in rightmost column
			{
				fromRow = i; 
				fromColumn = 7;
				toRow = i + 1;
				toColumn = 6;
			}
		}
		
		for (int i = 0; i < 6; i++) //rows excluding 2 end rows of x
		{
			for (int j = 2; j < 6; j++) //columns excluding 4 edge columns
			{
				if (game.getBoard(i, j) == 'o' && game.getBoard(i+1, j-1) == 'x' && game.getBoard(i+2, j-2) == '_') //check for available o jump
				{
					fromRow = i; //move from current row
					fromColumn = j; //move from current column
					toRow = i + 2; //move to blank row
					toColumn = j - 2; //move to blank column
					clearRow = i + 1;
					clearColumn = j - 1;
				}
				
				if (game.getBoard(i, j) == 'o' && game.getBoard(i+1, j+1) == 'x' && game.getBoard(i+2, j+2) == '_') //check for available o jump
				{
					fromRow = i; //move from current row
					fromColumn = j; //move from current column
					toRow = i + 2; //move to blank row
					toColumn = j + 2; //move to blank column
					clearRow = i + 1;
					clearColumn = j + 1;
				}
			}
			
			if (game.getBoard(i, 0) == 'o' && game.getBoard(i+1, 1) == 'x' && game.getBoard(i+2, 2) == '_') //check for available o jump in leftmost column
			{
				fromRow = i; //move from current row
				fromColumn = 0; //move from current column
				toRow = i + 2; //move to blank row
				toColumn = 2; //move to blank column
				clearRow = i + 1;
				clearColumn = 1;
			}
			
			if (game.getBoard(i, 1) == 'o' && game.getBoard(i+1, 2) == 'x' && game.getBoard(i+2, 3) == '_') //check for available o jump in 2nd to leftmost column
			{
				fromRow = i; //move from current row
				fromColumn = 1; //move from current column
				toRow = i + 2; //move to blank row
				toColumn = 3; //move to blank column
				clearRow = i + 1;
				clearColumn = 2;
			}
			
			if (game.getBoard(i, 6) == 'o' && game.getBoard(i+1, 5) == 'x' && game.getBoard(i+2, 4) == '_') //check for available o jump in leftmost column
			{
				fromRow = i; //move from current row
				fromColumn = 6; //move from current column
				toRow = i + 2; //move to blank row
				toColumn = 4; //move to blank column
				clearRow = i + 1;
				clearColumn = 5;
			}
			
			if (game.getBoard(i, 7) == 'o' && game.getBoard(i+1, 6) == 'x' && game.getBoard(i+2, 5) == '_') //check for available o jump in leftmost column
			{
				fromRow = i; //move from current row
				fromColumn = 7; //move from current column
				toRow = i + 2; //move to blank row
				toColumn = 5; //move to blank column
				clearRow = i + 1;
				clearColumn = 6;
			}
		}
		
		game.setBoard(fromRow, fromColumn, '_'); //from is cleared
		if (clearRow != -1 && clearColumn != -1)
		{
			game.setBoard(clearRow, clearColumn, '_');
			game.captureX();
		}
		game.setBoard(toRow, toColumn, 'o'); //new position
	}
}
