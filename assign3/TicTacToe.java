// class Box
// Author:  Bob Myers
//
// For COP3252, Java Programming
//
//
// Modified 9/29/14 by Taylor Brockhoeft for Java Homework 3
//Provides UI Layer for Tic Tac Toe Game

import java.util.*;

public class TicTacToe{
   //Constants
   public static final int EMPTY = 0, CROSS = 1,CIRCLE = 2;
   public static final int ROWS = 3, COLS = 3; // number of rows and columns
   public static final int PLAYING = 10, PONE_WIN = 11, PTWO_WIN = 22, DRAW = 13; // number of rows and columns
      public static int[][] gameBoard = new int[ROWS][COLS]; // game board in 2D array
      //Variables
   public static int gameType; // Game Type
                  // 0 = Null = 2 Human
                  // 3 = -c = 2 computer
                  // 1 = -c 1 = computer is player 1, human player 2
                  // 2 = -c 2 = human player 1, computer player 2
   public static int gameState; // 0 for running, 1 for player 1 win, 2 for player 2 win, 3 for draw
   public static int currentGameToken;
   public static int turns;//turn counters
   public static int last;


   
   

     public static void main(String[] args){
      boolean run = true;

      argGameTypeHandler(args);
       initilizeGame();
       while(run){
       updateBoard();
       //Initilize cumputer or human players, set gametype
      //Player
       play();
       //CheckWin
       if(checkWin()>0){
            if(checkWin()==CIRCLE){
            System.out.println("Player 2 wins! (O's)"); 
         run = false;              
            }
            if(checkWin()==CROSS){
            System.out.println("Player 1 wins! (X's)"); 
         run = false;              
            }
          if(checkWin()==DRAW){
            System.out.println("DRAW"); 
         run = false;              
            }
            updateBoard();


       }
     }
}
//function to handle parameters passed by user when starting
   public static void argGameTypeHandler(String[] args) {
      if(args.length >= 1){
         if(args[0].equals("-c")){ 
            if(args.length >= 2){
               if(args[1].equals("1")){//computer is player 1, human player 2
               System.out.println("Player 1(X's) is computer, player 2(O's) is human\n");  
                  gameType = 1;
               }
               if(args[1].equals("2")){//human player 1, computer player 2
               System.out.println("Player 1(X's) is human, player 2(O's) is computer\n"); 
                  gameType = 2;
               }
            }   
            else{
            gameType = 3;//Two Computer Players
            System.out.println("Player 1(X's) is computer, player 2(O's) is computer\n"); 
            }
         }      
      }
      else{
         gameType  = 0;//Two Human Players
         System.out.println("Player 1(X's) is human, player 2(O's) is human\n"); 
      }
      //System.out.println(gameType); 
   }

      public static void play(){
         int input;
         switch(gameType){
            case 0://Two Human Players  
               input = human();
               setToken(input);
               turns++;
               break;
            case 1://computer player 1, human player 2
               input = computer();
               setToken(input);
               swapPlayer();
               turns++;
               updateBoard();
               input = human();
               setToken(input);
               turns++;
               break;
            case 2://human player 1, computer player 2
               input = human();
               setToken(input);
               swapPlayer();
               turns++;
               updateBoard();
               input = computer();
               setToken(input);
               turns++;
               break;
            case 3://Two computer Players  
               input = computer();
               setToken(input);
               turns++;
               break;
         }
       //SwapPlayers
       swapPlayer();
    
     }

   public static int computer(){
      int input = childai();
   return input;
  }

  //childai = random int
   public static int childai(){
      int max = 9;
      int min = 1;
      Random rand = new Random();
      int input = rand.nextInt((max-min)+1);   
      while(tokenIsSet(input)){
         int counter = 0;
         counter++;
         int old = input;
         System.out.println("tks " + input);
      input = rand.nextInt((max-min)+1); 
      if(input == old){
         input = (input +3)%9;
       }
       if(turns > 8){
         System.out.println("DRAW");
         updateBoard();
         System.exit(0);
       } 
         if(counter>3){
            input = isolateTarget();
         }   
      }
      int maybe = findAndTakeWiningMove(currentGameToken); //Attempt to take whening move where available
      if( maybe > 0){
         input = maybe;
      }
      return input;
      }    

      public static int isolateTarget(){
         for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
               if (gameBoard[row][col] == EMPTY) {
                  return decodeTable(row,col);
               }
            }
         }
         return 0;
      }

      public static int decodeTable(int r, int c){
         int row = 0;
         int col = 0;
         switch(r){
            case 0: row = 7;
            break;
            case 1: row = 4;
            break;
            case 2: row = 1;
            break;
         }
         switch(c){
            case 0: col = 0;
            break;
            case 1: col = 1;
            break;
            case 2: col = 2;
            break;
         }

         return row+col;
      }

      public static int findAndTakeWiningMove(int player){
         int other;
         if(player == CROSS){
            other = CIRCLE;
         }
         else{
            other = CROSS;
         }
         int ret=0;
         //row1
         if(gameBoard[0][0]==player && gameBoard[0][1]==player && (gameBoard[0][2]==EMPTY || gameBoard[0][2]==other) ){
            if(gameBoard[0][2]==EMPTY){
            ret = 9;
            }
         }
         if(gameBoard[0][0]==player && (gameBoard[0][1]==EMPTY || gameBoard[0][1]==other) && gameBoard[0][2]==player ){
            if(gameBoard[0][1]==EMPTY){
               ret = 8;
            }
         }
         if((gameBoard[0][0]==EMPTY || gameBoard[0][0]==other) && gameBoard[0][1]==player && gameBoard[0][2]==player ){
            if(gameBoard[0][0]==EMPTY){
               ret = 7;
            }
         }
         //row2
         if(gameBoard[1][0]==player && gameBoard[1][1]==player && (gameBoard[1][2]==EMPTY || gameBoard[1][2]==other) ){
            if(gameBoard[1][2]==EMPTY){ret = 6;}
         }
         if(gameBoard[1][0]==player && (gameBoard[1][1]==EMPTY || gameBoard[1][1]==other) && gameBoard[1][2]==player ){
            if(gameBoard[1][1]==EMPTY){ret = 5;}
         }
         if((gameBoard[1][0]==EMPTY || gameBoard[1][0]==other) && gameBoard[1][1]==player && gameBoard[1][2]==player ){
            if(gameBoard[1][0]==EMPTY){ret = 4;}
         }
         //row3
         if(gameBoard[2][0]==player && gameBoard[2][1]==player && (gameBoard[2][2]==EMPTY || gameBoard[2][2]==other) ){
            if(gameBoard[2][2]==EMPTY){ret = 3;}
         }
         if(gameBoard[2][0]==player && (gameBoard[2][1]==EMPTY || gameBoard[2][1]==other) && gameBoard[2][2]==player ){
            if(gameBoard[2][1]==EMPTY){ret = 2;}
         }
         if((gameBoard[2][0]==EMPTY || gameBoard[2][0]==other) && gameBoard[2][1]==player && gameBoard[2][2]==player ){
            if(gameBoard[2][0]==EMPTY){ret = 1;}
         }

         //col1
         if(gameBoard[0][0]==player && gameBoard[1][0]==player && (gameBoard[2][0]==EMPTY || gameBoard[2][0]==other) ){
            if(gameBoard[2][0]==EMPTY){
            ret = 1;
            }
         }
         if(gameBoard[0][0]==player && (gameBoard[1][0]==EMPTY || gameBoard[1][0]==other) && gameBoard[2][0]==player ){
            if(gameBoard[1][0]==EMPTY){
               ret = 4;
            }
         }
         if((gameBoard[0][0]==EMPTY || gameBoard[0][0]==other) && gameBoard[1][0]==player && gameBoard[2][0]==player ){
            if(gameBoard[0][0]==EMPTY){
               ret = 7;
            }
         }
         //col2
         if(gameBoard[0][1]==player && gameBoard[1][1]==player && (gameBoard[2][1]==EMPTY || gameBoard[2][1]==other) ){
            if(gameBoard[1][2]==EMPTY){ret = 2;}
         }
         if(gameBoard[0][1]==player && (gameBoard[1][1]==EMPTY || gameBoard[1][1]==other) && gameBoard[2][1]==player ){
            if(gameBoard[1][1]==EMPTY){ret = 5;}
         }
         if((gameBoard[0][1]==EMPTY || gameBoard[0][1]==other) && gameBoard[1][1]==player && gameBoard[2][1]==player ){
            if(gameBoard[1][0]==EMPTY){ret = 8;}
         }
         //col3
         if(gameBoard[0][2]==player && gameBoard[1][2]==player && (gameBoard[2][2]==EMPTY || gameBoard[2][2]==other) ){
            if(gameBoard[2][2]==EMPTY){ret = 3;}
         }
         if(gameBoard[0][2]==player && (gameBoard[1][2]==EMPTY || gameBoard[1][2]==other) && gameBoard[2][2]==player ){
            if(gameBoard[1][2]==EMPTY){ret = 6;}
         }
         if((gameBoard[0][2]==EMPTY || gameBoard[0][2]==other) && gameBoard[1][2]==player && gameBoard[2][2]==player ){
            if(gameBoard[0][2]==EMPTY){ret = 9;}
         }
         //diagnol1
         if(gameBoard[0][0]==player && gameBoard[1][1]==player && (gameBoard[2][2]==EMPTY || gameBoard[2][2]==other) ){
            if(gameBoard[2][2]==EMPTY){ret = 3;}
         }
         if(gameBoard[0][0]==player && (gameBoard[1][1]==EMPTY || gameBoard[1][1]==other) && gameBoard[2][2]==player ){
            if(gameBoard[1][1]==EMPTY){ret = 5;}
         }
         if((gameBoard[0][0]==EMPTY || gameBoard[0][0]==other) && gameBoard[1][1]==player && gameBoard[2][2]==player ){
            if(gameBoard[0][0]==EMPTY){ret = 7;}
         }
         //diagnol2
         if(gameBoard[2][0]==player && gameBoard[1][1]==player && (gameBoard[2][2]==EMPTY || gameBoard[0][2]==other) ){
            if(gameBoard[2][2]==EMPTY){ret = 1;}
         }
         if(gameBoard[2][0]==player && (gameBoard[1][1]==EMPTY || gameBoard[1][1]==other) && gameBoard[0][2]==player ){
            if(gameBoard[1][1]==EMPTY){ret = 5;}
         }
         if((gameBoard[2][0]==EMPTY || gameBoard[2][0]==other) && gameBoard[1][1]==player && gameBoard[0][2]==player ){
            if(gameBoard[0][0]==EMPTY){ret = 9;}
         }
         return ret;

      }
   // public static int daddyai(){
   //    int max = 9, min = 0;
   //    int input = 0; 

   //    if(gameType==1 && turns==0){//first move
   //       return last = 7;
   //    }

   //    if(gameType==1 && turns==2){//player one rules, second move
   //       if(tokenIsSet(3)){
   //       return last = 9;
   //       }
   //       if(tokenIsSet(9)){
   //       return last = 3;
   //       }
   //       if(tokenIsSet(1)){
   //       return last = 3;
   //       }
   //       if(tokenIsSet(5)){
   //       return last = 3;
   //       }
   //       return last = 3;

   //    }

   //    if(gameType==1 && turns==4){//player one rules, second move
   //       if(last = 9){
   //          if(tokenIsSet(8)){
   //             if(tokenIsSet(1)){
   //             return last = 8;
   //             }
   //             return last = 1;
   //          }
   //          return last = 1;
   //       }
   //    }

   //    return 0;
   // }

   public static int randomInteger(int min, int max){      
      Random rand = new Random();
      return rand.nextInt((max-min)); 
   }

  public static int human(){
      Scanner s = new Scanner(System.in);
      int input = s.nextInt();
      while(input < 1 || input > 9 ){     
         System.out.println("enter 1-9 to select correct tile please and thank you");
       input = s.nextInt();
       }
       while(tokenIsSet(input)){
         System.out.println("Token "+Integer.toString(input)+" is already set");
         input = s.nextInt();
       }
   return input;
  }

//Sets Current Player Token Util function
  public static void setToken(int input){
      int row = 0, col = 0;
      switch(input){
         case 7: row = 0; col = 0;
         break;
         case 8: row = 0; col = 1;
         break;
         case 9: row = 0; col = 2;
         break;
         case 4: row = 1; col = 0;
         break;
         case 5: row = 1; col = 1;
         break;
         case 6: row = 1; col = 2;
         break;
         case 1: row = 2; col = 0;
         break;
         case 2: row = 2; col = 1;
         break;
         case 3: row = 2; col = 2;
         break;
      }
      gameBoard[row][col] = currentGameToken;

     }

    public static boolean tokenIsSet(int input){
         int row = 0, col = 0;
         switch(input){
            case 7: row = 0; col = 0;
            break;
            case 8: row = 0; col = 1;
            break;
            case 9: row = 0; col = 2;
            break;
            case 4: row = 1; col = 0;
            break;
            case 5: row = 1; col = 1;
            break;
            case 6: row = 1; col = 2;
            break;
            case 1: row = 2; col = 0;
            break;
            case 2: row = 2; col = 1;
            break;
            case 3: row = 2; col = 2;
            break;
         }

         if(turns > 8){
            return false;
         } 
         if(gameBoard[row][col] == EMPTY){
            return false;
         }
         return true;

  }

// ->8
  //Returns an int gametsate value, which could be playing, win, or draw.
  public static int checkWin(){
      //Diagnols
         for(int i = 1; i < 3; i++){
            if(gameBoard[0][0] == i && gameBoard[1][1] == i && gameBoard[2][2] == i){
               return i;
            }
               if(gameBoard[0][2] == i && gameBoard[1][1] == i && gameBoard[2][0] == i){
               return i;
            }
            //Columns
            if(gameBoard[0][0] == i && gameBoard[0][1] == i && gameBoard[0][2] == i){
               return i;
            }
            if(gameBoard[1][0] == i && gameBoard[1][1] == i && gameBoard[1][2] == i){
               return i;
            }
            if(gameBoard[2][0] == i && gameBoard[2][1] == i && gameBoard[2][2] == i){
               return i;
            }
            //Rows
            if(gameBoard[0][0] == i && gameBoard[1][0] == i && gameBoard[2][0] == i){
               return i;
            }
            if(gameBoard[0][1] == i && gameBoard[1][1] == i && gameBoard[2][1] == i){
               return i;
            }
            if(gameBoard[0][2] == i && gameBoard[1][2] == i && gameBoard[2][2] == i){
               return i;
            }
         }   
         if(turns > 8){
            return DRAW;
         }
         return PLAYING;
        }

         //Swaps Player Value
      public static void swapPlayer(){
         if(currentGameToken==CROSS){
            currentGameToken = CIRCLE;
         }
         else if(currentGameToken==CIRCLE){
            currentGameToken = CROSS;
         }
      }

      /** Initialize the game-board contents and the current states */
      public static void initilizeGame() {
         turns = 0;
         for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
               gameBoard[row][col] = EMPTY;  // all cells empty
            }
         }
         gameState = PLAYING; // ready to play
         currentGameToken = CROSS;  // cross plays first
      }

      //updates ascii interface board
      public static void updateBoard(){
         System.out.println("Turn: " + turns);   // print vertical partition
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