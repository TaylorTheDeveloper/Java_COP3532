// class Box
// Author:  Bob Myers
//
// For COP3252, Java Programming
//
//
// Modified 9/29/14 by Taylor Brockhoeft for Java Homework 3
//Provides UI Layer for Tic Tac Toe Game
public class TicTacToe{
	//Constants
	public static final int EMPTY = 0, CROSS = 1,CIRCLE = 2;
	public static final int ROWS = 3, COLS = 3; // number of rows and columns
	public static final int PLAYING = 0, PONE_WIN = 1, PTWO_WIN = 2, DRAW = 3; // number of rows and columns
   	public static int[][] gameBoard = new int[ROWS][COLS]; // game board in 2D array
   	//Variables
	public static int gameType; // Game Type
						// 0 = Null = 2 Human
						// 3 = -c = 2 computer
						// 1 = -c 1 = computer is player 1, human player 2
						// 2 = -c 2 = human player 1, computer player 2
	public static int gameState; // 0 for running, 1 for player 1 win, 2 for player 2 win, 3 for draw

	
	

	  public static void main(String[] args){
	  	argHandler(args);
	    initilizeGame();
	    updateBoard();
	  }

//function to handle parameters passed by user when starting
	public static void argHandler(String[] args) {
		if(args.length >= 1){
			if(args.length == 2){
				if(args[2] == "1"){//computer is player 1, human player 2
               gameType = 1;
				}
				if(args[2] == "2"){//human player 1, computer player 2
					gameType = 2;
				}
			}
			else{
				gameType = 3;//Two Computer Players
			}

		}
		else{
			gameType  = 0;//Two Human Players
		}
   }

	   /** Initialize the game-board contents and the current states */
   	public static void initilizeGame() {
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            gameBoard[row][col] = EMPTY;  // all cells empty
         }
      }
      gameState = PLAYING; // ready to play
      //currentPlayer = CROSS;  // cross plays first
   	}

   	//updates ascii interface board
   	public static void updateBoard(){
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            printCell(gameBoard[row][col]); // print each of the cells
            if (col != COLS - 1) {
               System.out.print("|");   // print vertical partition
            }
         }
         System.out.println();
         if (row != ROWS - 1) {
            System.out.println("-----------"); // print horizontal partition
         }
      }
      System.out.println();
   }

   //Handles printing individual cells
   	public static void printCell(int val) {
      switch (val) {
         case EMPTY:  System.out.print("   "); break;
         case CIRCLE: System.out.print(" O "); break;
         case CROSS:  System.out.print(" X "); break;
      }
   }



}