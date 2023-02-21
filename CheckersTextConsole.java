package ui;

import java.util.Scanner;
import core.CheckersComputerPlayer;
import core.CheckersLogic;

/**
 * This class runs the ui for a simple checkers game.
 * 
 * @author Cheng Zhang
 * @version 01/30/2023
 */
public class CheckersTextConsole 
{	
	/**
	 * Queries and returns user input as a String
	 * 
	 * @param in a Scanner reading user input
	 * @return a String containing the current and new position of a game piece, separated by a dash
	 */
	public static String getMove(Scanner in)
	{
		String move = "";
		System.out.println("Choose a cell position of piece to be moved and the new position. e.g. 3a-4b:");
		move = in.nextLine();
		return move;
	}
	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		CheckersLogic game = new CheckersLogic();
		String move = "";
		String choice = "";
		
		game.displayBoard();
		System.out.println("Begin Game. Enter 'P' if you want to play against another player; enter 'C' to play against computer.");
		choice = in.nextLine(); //read choice
		choice = choice.toUpperCase(); //convert to upper case
		
		while (!choice.equals("P") && !choice.equals("C")) //exception handling
		{
			System.out.println("Wrong input - please enter 'P' or 'C': ");
			choice = in.nextLine();
			choice = choice.toUpperCase(); //convert to upper case
		}
		
		if (choice.equals("P")) // PVP
		{
			while (!game.gameOver()) //check if game is over each loop
			{
				if (game.getxTurn()) System.out.println("Player X - your turn.");
				else System.out.println("Player O - your turn.");
				move = getMove(in);
				if (game.checkMove(move)) game.setxTurn(!game.getxTurn()); //flip xTurn if move is valid
				else System.out.println("Invalid move - please try again");
				game.displayBoard();
			}
		}
		
		if (choice.equals("C")) //PVC
		{
			CheckersComputerPlayer computer = new CheckersComputerPlayer();
			while (!game.gameOver()) //check if game is over each loop
			{
				if (game.getxTurn())
				{
					System.out.println("Player X - your turn.");
					move = getMove(in);
					if (game.checkMove(move)) game.setxTurn(!game.getxTurn()); //flip xTurn if move is valid
					else System.out.println("Invalid move - please try again");
				}
				
				else
				{
					System.out.println("Player O's turn.");
					computer.move(game); //computer generates move based on board
					game.setxTurn(!game.getxTurn()); //flip xTurn
				}
				game.displayBoard();
			}
		}
		if (game.getWinner() == 'N') System.out.println("The game resulted in a tie.");
		else System.out.println("Player " + game.getWinner() + " won the game");
	}
}