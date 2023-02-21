package core;

/**
 * This class runs the logic behind a simple checkers game.
 * 
 * @author Cheng Zhang
 * @version 01/23/2023
 */
public class CheckersLogic 
{
	private char[][] board; //gameboard
	private int x; //x pieces
	private int o; //o pieces
	private boolean xTurn; //player x's turn
	private char winner;
	
	/**
	 * Constructor sets up original state of board
	 */
	public CheckersLogic()
	{
		board = new char[8][8]; //8x8 board
		x = 12; //12 pieces
		o = 12; //12 pieces
		xTurn = true; //player x starts the game
		winner = ' ';
		
		for (int i = 0; i < 8; i++) //board setup
			for (int j = 0; j < 8; j++)
			{
				board[i][j] = '_';
			}
		
		for (int i = 1; i < 8; i += 2) //populate odd columns
		{
			board[0][i] = 'o'; //row 8
			board[2][i] = 'o'; //row 6
			board[6][i] = 'x'; //row 2
		}
		
		for (int i = 0; i < 8; i += 2) //populate even columns
		{
			board[1][i] = 'o'; //row 7
			board[5][i] = 'x'; //row 3
			board[7][i] = 'x'; //row 1
		}
	}
	
	/**
	 * Displays current state of board
	 */
	public void displayBoard()
	{
		System.out.print("8|");
		for (int i = 0; i < 8; i++) System.out.print(board[0][i] + "|");
		System.out.print("\n7|");
		for (int i = 0; i < 8; i++) System.out.print(board[1][i] + "|");
		System.out.print("\n6|");
		for (int i = 0; i < 8; i++) System.out.print(board[2][i] + "|");
		System.out.print("\n5|");
		for (int i = 0; i < 8; i++) System.out.print(board[3][i] + "|");
		System.out.print("\n4|");
		for (int i = 0; i < 8; i++) System.out.print(board[4][i] + "|");
		System.out.print("\n3|");
		for (int i = 0; i < 8; i++) System.out.print(board[5][i] + "|");
		System.out.print("\n2|");
		for (int i = 0; i < 8; i++) System.out.print(board[6][i] + "|");
		System.out.print("\n1|");
		for (int i = 0; i < 8; i++) System.out.print(board[7][i] + "|");
		System.out.println("\n  a b c d e f g h");
	}
	
	/**
	 * Gets the char stored at row i column j of board
	 * 
	 * @param i an int indicating row
	 * @param j an int indicating column
	 * @return the char stored at row i column j
	 */
	public char getBoard(int i, int j)
	{
		return board[i][j];
	}
	
	/**
	 * Sets the char stored at row i column j of board
	 * 
	 * @param i an int indicating row
	 * @param j an int indicating column
	 * @param piece the char to be stored
	 */
	public void setBoard(int i, int j, char piece)
	{
		board[i][j] = piece;
	}
	
	/**
	 * Returns if its player X's turn
	 * 
	 * @return a boolean indicating whether its player X's turn
	 */
	public boolean getxTurn()
	{
		return xTurn;
	}
	
	/**
	 * Sets if its player X's turn
	 * 
	 * @param xTurn a boolean indicating whether its player X's turn
	 */
	public void setxTurn(boolean xTurn)
	{
		this.xTurn = xTurn;
	}
	
	/**
	 * Simulates capturing a 'x' piece, used by computer player
	 */
	public void captureX()
	{
		x = x - 1;
	}
	
	/**
	 * Returns the winner of the game
	 * 
	 * @return a char representing the winner of the game
	 */
	public char getWinner()
	{
		return winner;
	}
	
	/**
	 * Checks if a move is valid
	 * 
	 * @param move a String containing the current and new position of a game piece, separated by a dash
	 * @return a boolean indicating whether the move was valid
	 */
	public boolean checkMove(String move)
	{
		move = move.toLowerCase();
		int fromRow = -1;
		int fromColumn = -1;
		int toRow = -1;
		int toColumn = -1;
		
		try //try parsing input
		{
			fromRow = Integer.parseInt(move.substring(0,1)); 
			fromColumn = move.charAt(1);
			toRow = Integer.parseInt(move.substring(3,4));
			toColumn = move.charAt(4);
		}
		
		catch (IllegalArgumentException ex) //input not in correct format
		{
			return false;
		}
		
		fromRow = 8 - fromRow; //flip row
		fromColumn = fromColumn - 97; //ascii arithmetic
		toRow = 8 - toRow; //flip row
		toColumn = toColumn - 97; //ascii arithmetic
		
		if (fromRow > 7 || fromRow < 0 || fromColumn > 7 || fromColumn < 0 || toRow > 7 || toRow < 0 || toColumn > 7 || toColumn < 0) return false; //check bounds
		if (board[toRow][toColumn] != '_') return false; //new position is occupied
		
		if (getxTurn()) //player X's turn
		{
			if (board[fromRow][fromColumn] != 'x') return false; //no piece to move
			else if (toRow == (fromRow - 1) && (toColumn == (fromColumn + 1) || toColumn == (fromColumn - 1)))
			{
				board[fromRow][fromColumn] = '_'; 
				board[toRow][toColumn] = 'x'; //new position
				return true; //valid move
			}
			else if (toRow == (fromRow - 2) && toColumn == (fromColumn + 2))
			{
				if(board[fromRow-1][fromColumn+1] == 'o') //piece to capture
				{
					board[fromRow][fromColumn] = '_';
					board[fromRow-1][fromColumn+1] = '_'; //captured
					board[toRow][toColumn] = 'x'; //new position
					o = o - 1;
					return true; //valid jump
				}
			}
			else if (toRow == (fromRow - 2) && toColumn == (fromColumn - 2))
			{
				if(board[fromRow-1][fromColumn-1] == 'o') //piece to capture
				{
					board[fromRow][fromColumn] = '_';
					board[fromRow-1][fromColumn-1] = '_'; //captured
					board[toRow][toColumn] = 'x'; //new position
					o = o - 1;
					return true; //valid jump
				}
			}	
		}
		
		else if (!getxTurn()) //player O's turn
		{
			if (board[fromRow][fromColumn] != 'o') return false; //no piece to move
			else if (toRow == (fromRow + 1) && (toColumn == (fromColumn + 1) || toColumn == (fromColumn - 1)))
			{
				board[fromRow][fromColumn] = '_'; 
				board[toRow][toColumn] = 'o'; //new position
				return true; //valid move
			}
			else if (toRow == (fromRow + 2) && toColumn == (fromColumn + 2))
			{
				if(board[fromRow+1][fromColumn+1] == 'x')
				{
					board[fromRow][fromColumn] = '_';
					board[fromRow+1][fromColumn+1] = '_'; //captured
					board[toRow][toColumn] = 'o'; //new position
					x = x - 1;
					return true; //valid jump
				}
			}
			else if (toRow == (fromRow + 2) && toColumn == (fromColumn - 2))
			{
				if(board[fromRow+1][fromColumn-1] == 'x')
				{
					board[fromRow][fromColumn] = '_';
					board[fromRow+1][fromColumn-1] = '_'; //captured
					board[toRow][toColumn] = 'o'; //new position
					x = x - 1;
					return true; //valid jump
				}
			}	
		}
		
		return false;
	}
	
	/**
	 * Returns if the game is over because a player is out of (movable) pieces
	 * 
	 * @return a boolean indicating if the game is over
	 */
	public boolean gameOver()
	{
		int movablex = 0;
		int movableo = 0;
		
		for (int i = 1; i < 8; i++) //rows excluding o end
		{
			for (int j = 1; j < 7; j++) //columns excluding edges
			{
				if (board[i][j] == 'x') //check for available x moves
				{
					if (board[i-1][j-1] == '_' || board[i-1][j+1] == '_') movablex += 1; //valid moves available
				}
			}
			if (board[i][0] == 'x' && board[i-1][1] == '_') movablex += 1; //check for available x moves in leftmost column
			if (board[i][7] == 'x' && board[i-1][6] == '_') movablex += 1; //check for available x moves in rightmost column
		}
		
		for (int i = 2; i < 8; i++) //rows excluding 2 end rows of o
		{
			for (int j = 2; j < 6; j++) //columns excluding 4 edge columns
			{
				if (board[i][j] == 'x') //check for available x jumps
				{
					if ((board[i-1][j-1] == 'o' && board[i-2][j-2] == '_') || (board[i-1][j+1] == 'o' && board[i-2][j+2] == '_')) movablex += 1; //valid jumps available
				}
			}
			if (board[i][0] == 'x' && board[i-1][1] == 'o' && board[i-2][2] == '_') movablex += 1; //check for available x jumps in leftmost column
			if (board[i][1] == 'x' && board[i-1][2] == 'o' && board[i-2][3] == '_') movablex += 1; //check for available x jumps in 2nd to leftmost column
			if (board[i][6] == 'x' && board[i-1][5] == 'o' && board[i-2][4] == '_') movablex += 1; //check for available x jumps in 2nd to rightmost column	
			if (board[i][7] == 'x' && board[i-1][6] == 'o' && board[i-2][5] == '_') movablex += 1; //check for available x jumps in rightmost column
		}
		
		for (int i = 0; i < 7; i++) //rows excluding x end
		{
			for (int j = 1; j < 7; j++) //columns excluding edges
			{
				if (board[i][j] == 'o') //check for available o moves
				{
					if (board[i+1][j-1] == '_' || board[i+1][j+1] == '_') movableo += 1; //valid moves available
				}
			}
			if (board[i][0] == 'o' && board[i+1][1] == '_') movableo += 1; //check for available o moves in leftmost column
			if (board[i][7] == 'o' && board[i+1][6] == '_') movableo += 1; //check for available o moves in rightmost column
		}
		
		for (int i = 0; i < 6; i++) //rows excluding 2 end rows of x
		{
			for (int j = 2; j < 6; j++) //columns excluding 4 edge columns
			{
				if (board[i][j] == 'o') //check for available o jumps
				{
					if ((board[i+1][j-1] == 'x' && board[i+2][j-2] == '_') || (board[i+1][j+1] == 'x' && board[i+2][j+2] == '_')) movableo += 1; //valid jumps available
				}
			}
			if (board[i][0] == 'o' && board[i+1][1] == 'x' && board[i+2][2] == '_') movableo += 1; //check for available o jumps in leftmost column	
			if (board[i][1] == 'o' && board[i+1][2] == 'x' && board[i+2][3] == '_') movableo += 1; //check for available o jumps in 2nd to leftmost column
			if (board[i][6] == 'o' && board[i+1][5] == 'x' && board[i+2][4] == '_') movableo += 1; //check for available o jumps in 2nd to rightmost column
			if (board[i][7] == 'o' && board[i+1][6] == 'x' && board[i+2][5] == '_') movableo += 1; //check for available o jumps in rightmost column
		}
		
		if ((x == 0 || movablex == 0) && (o == 0 || movableo == 0)) //check for tie
		{
			winner = 'N'; //neither player wins
			return true; //game over
		}
		
		if (getxTurn()) //if it's player X's turn
		{
			if (x == 0 || movablex == 0) //if no more (movable) x pieces remaining
			{
				winner = 'O'; //Player O wins
				return true; //game over
			}
			
			else if (o == 0 || movableo == 0) //if no more (movable) o pieces remaining
			{
				winner = 'X'; //Player X wins
				return true; //game over
			}
		}
	
		else //if it's player O's turn
		{
			if (x == 0 || movablex == 0) //if no more (movable) x pieces remaining
			{
				winner = 'O'; //Player O wins
				return true; //game over
			}
			
			else if (o == 0 || movableo == 0) //if no more (movable) o pieces remaining
			{
				winner = 'X'; //Player X wins
				return true; //game over
			}
		}
		
		return false; //continue game
	}
}