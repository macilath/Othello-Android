package team3.mechanicsAI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//import android.app.Activity;
//import android.os.Bundle;
//import team3.othello.AI;


public class Mechanics {//extends Activity {
    /** Called when the activity is first created. */
    /**
     * ***************************************************************
     *                     ANDROID FUNCTIONS
     * ***************************************************************
     */
	//@Override - Parts for Android application
    /*public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    */
	
	/**
	 * ***************************************************************
	 *                       GLOBAL CONSTANTS
	 * ***************************************************************
	 */
    public int COLUMNS = 8;
    public int ROWS = 8;
    public boolean OFF = false;
	public boolean ON = true;
	public char EMPTY = 'a';
	public static char BLACK = 'b';
	public static char WHITE = 'c';
	public char POSSIBLE_BLACK_MOVE = 'd';
	public char POSSIBLE_WHITE_MOVE = 'e';
	public char POSSIBLE_BLACK_OR_WHITE_MOVE = 'f'; //space where either color can go to
	//Game board definitions
	public int A = 0;
	public int B = 1;
	public int C = 2;
	public int D = 3;
	public int E = 4; 
	public int F = 5; 
	public int G = 6; 
	public int H = 7;
	public int MAX_STATES = 64;
	public static char EASY = 'A';
	public static char MEDIUM = 'B';
	public static char HARD = 'C';
	public int INFINITY = 999999;
	
	/**
	 * ****************************************************************
	 *                    OTHELLO GAME MECHANICS
	 * ****************************************************************
	 */
	//Private members
	private char states[][][] = new char[MAX_STATES][COLUMNS][ROWS];
	private char playerTurnArray[] = new char[MAX_STATES];
	private boolean display;
	private int numStates;
	private int currState;	
	
	//Private functions
	public char opposingPlayer(char player){
		if(player == WHITE){
			return BLACK;
		}
		else if(player == BLACK){
			return WHITE;
		}
		else{
			System.out.println("Error, invalid color");
			return 0;
		}
	}
	
	public int numPieces(char[][] state, char player){
		if(player == WHITE){
			int whitePieces = 0;
			for(int i=0; i < ROWS; i++){
				for(int j=0; j < COLUMNS; j++){
					if(state[i][j] == WHITE){
						whitePieces++;
					}
				}
			}
			return whitePieces;
		}
		else if(player == BLACK){
			int blackPieces = 0;
			for(int i=0; i < ROWS; i++){
				for(int j=0; j < COLUMNS; j++){
					if(state[i][j] == BLACK){
						blackPieces++;
					}
				}
			}
			return blackPieces;
		}
		else
			return 0;
	}
	
	public int score(char[][] state, char player){
		if(player == WHITE){
			return numPieces(state, WHITE) - numPieces(state, BLACK);
		}
		else if(player == BLACK){
			return numPieces(state, BLACK) - numPieces(state, WHITE);
		}
		else{
			System.out.println("score: Invalid player.");
			return 0;
		}
	}
	
	private int numMoves(char[][] state, char player){
		int count = 0;
		for(int i=0; i<COLUMNS; i++){
			for(int j=0; j<ROWS; j++){
				if(player == BLACK){
					if(state[i][j] == POSSIBLE_BLACK_MOVE || state[i][j] == POSSIBLE_BLACK_OR_WHITE_MOVE){
						count++;
					}
				}
				if(player == WHITE){
					if(state[i][j] == POSSIBLE_WHITE_MOVE || state[i][j] == POSSIBLE_BLACK_OR_WHITE_MOVE){
						count++;
					}
				}
			}
		}
		return count;
	}
/*	
	private void print(char[][] state){
	
		//We want to loop through the state arrays to check for pieces
		//first print top row
		System.out.println("\n");
		System.out.println(" |__a__|__b__|__c__|__d__|__e__|__f__|__g__|__h__| ");
	
		for(int i=0; i<ROWS; i++){
			System.out.print((i+1) + "|") ; //start with row number (ex: 0| )
			for(int j=0; j<COLUMNS; j++){
				char piece = '_';
				if(state[j][i] == BLACK){
					piece = '@';
				}
				else if(state[j][i] == WHITE){
					piece = 'O';
				}
				else
					piece = '_';
				System.out.print("__" + piece + "__|");
			}
			System.out.println();
		}
		System.out.println();
	}
*/	
	public void print(char[][] state, char player){
	
		System.out.println("\n");
		System.out.println(" |__a__|__b__|__c__|__d__|__e__|__f__|__g__|__h__| ");
		if(player == BLACK){
			for(int i=0; i<ROWS; i++){
				System.out.print((i+1) + "|") ; //start with row number (ex: 0| )
				for(int j=0; j<COLUMNS; j++){
					char piece = '_';
					if(state[j][i] == BLACK){
						piece = '@';
					}
					else if(state[j][i] == WHITE){
						piece = 'O';
					}
					else if(state[j][i] == POSSIBLE_BLACK_MOVE){
						piece = 'X';
					}
					else if(state[j][i] == POSSIBLE_BLACK_OR_WHITE_MOVE){
						piece = 'X';
					}
					else
						piece = '_';
					System.out.print("__" + piece + "__|");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		else if(player == WHITE){	
			for(int i=0; i<ROWS; i++){
				System.out.print((i+1) + "|") ; //start with row number (ex: 0| )
				for(int j=0; j<COLUMNS; j++){
					char piece = '_';
					if(state[j][i] == BLACK){
						piece = '@';
					}
					else if(state[j][i] == WHITE){
						piece = 'O';
					}
					else if(state[j][i] == POSSIBLE_WHITE_MOVE){
						piece = 'X';
					}
					else if(state[j][i] == POSSIBLE_BLACK_OR_WHITE_MOVE){
						piece = 'X';
					}
					else
						piece = '_';
					System.out.print("__" + piece + "__|");
				}
				System.out.println();
			}
			System.out.println();
		}
		else
			System.out.println("Error: invalid player");
	}
	
	private boolean undo(char player){
		if(currState == 0){
			return false;
		}
		int nextState = currState - 1;
		while(true){
			if(nextState < 0){
				return false;
			}
			if(playerTurnArray[nextState] == player){
				currState = nextState;
				return true;
			}
			if(nextState == 0){
				return false;
			}
			nextState--;
		}
	}
	
	private boolean redo(char player){
		if(currState == numStates-1){
			System.out.println("redo: Can't redo, no more future states.\n");
			return false;
		}
		int nextState = currState + 1;
		while(true){
			if(nextState > numStates-1){
				return false;
			}
			if(playerTurnArray[nextState] == player){
				currState = nextState;
				return true;
			}
			if(nextState == numStates-1){
				return false;
			}
			nextState++;
		}
	}
	
	private boolean move(int column, int row, char player){
		if(player != WHITE && player != BLACK){
			System.out.println("move: invalid player.\n");
			return false;
		}
		
		if(column >= COLUMNS || column < 0 || row >= ROWS || row < 0){
			System.out.println("EvalSpace: Column or row range error.");
			return false;
		}
		
		if(player == WHITE){
			if(states[currState][column][row] != POSSIBLE_WHITE_MOVE && states[currState][column][row] != POSSIBLE_BLACK_OR_WHITE_MOVE){
				//System.out.println("bad");
				return false;
			}
		}
		
		if(player == BLACK){
			if(states[currState][column][row] != POSSIBLE_BLACK_MOVE 
			&& states[currState][column][row] != POSSIBLE_BLACK_OR_WHITE_MOVE){
				System.out.println("bad");
				return false;
			}
		}
		
		//Move is valid
		//Go to the next state and update the states
		currState++;
		numStates = currState + 1;
		//System.arraycopy( states[currState-1], 0, states[currState], 0, states[currState].length);
		
		for(int i=0; i<COLUMNS; i++){
			for(int j=0; j<ROWS; j++){
				states[currState][i][j] = states[currState-1][i][j];
			}
		}
		
		//Check each direction, flip opposing pieces
		int tempColumn;
		int tempRow;
		
		//left from move
		if(player == left(states[currState], column, row)){
			//state[column][row] = player
			tempColumn = column - 1;
			tempRow = row;
			while(states[currState][tempColumn][tempRow] != player){
				states[currState][tempColumn][tempRow] = player;
				tempColumn--;
				//tempRow does not change
			}
		}
		
		//left up
		if(player == leftUp(states[currState], column, row)){
			//state[column][row] = player;
			tempColumn = column - 1;
			tempRow = row - 1;
			while(states[currState][tempColumn][tempRow] != player){
				states[currState][tempColumn][tempRow] = player;
				tempColumn--;
				tempRow--;
			}
		}
		
		//up
		if(player == up(states[currState], column, row)){
			//state[column][row] = player;
			tempColumn = column;
			tempRow = row - 1;
			while(states[currState][tempColumn][tempRow] != player){
				states[currState][tempColumn][tempRow] = player;
				//tempColumn does not change
				tempRow--;
			}
		}
		
		//right up
		if(player == rightUp(states[currState], column, row)){
			//state[column][row] = player;
			tempColumn = column + 1;
			tempRow = row - 1;
			while(states[currState][tempColumn][tempRow] != player){
				states[currState][tempColumn][tempRow] = player;
				tempColumn++;
				tempRow--;
			}
		}
		
		//right
		if(player == right(states[currState], column, row)){
			//state[column][row] = player;
			tempColumn = column + 1;
			tempRow = row;
			while(states[currState][tempColumn][tempRow] != player){
				states[currState][tempColumn][tempRow] = player;
				tempColumn++;
				//tempRow does not change
			}
		}
		
		//right down
		if(player == rightDown(states[currState], column, row)){
			//state[column][row] = player;
			tempColumn = column + 1;
			tempRow = row + 1;
			while(states[currState][tempColumn][tempRow] != player){
				states[currState][tempColumn][tempRow] = player;
				tempColumn++;
				tempRow++;
			}
		}
		
		//down
		if(player == down(states[currState], column, row)){
			//state[column][row] = player;
			tempColumn = column;
			tempRow = row + 1;
			while(states[currState][tempColumn][tempRow] != player){
				states[currState][tempColumn][tempRow] = player;
				//tempColumn does not change
				tempRow++;
			}
		}
		
		//left down
		if(player == leftDown(states[currState], column, row)){
			//state[column][row] = player;
			tempColumn = column - 1;
			tempRow = row + 1;
			while(states[currState][tempColumn][tempRow] != player){
				states[currState][tempColumn][tempRow] = player;
				tempColumn--;
				tempRow++;
			}
		}
		
		//change actual spot
		states[currState][column][row] = player;
		
		/*
		Now we need to reevaluate all of the pieces on the board
		to determine if they have a piece, is a valid move for each player,
		or is simply empty
		*/
		
		for(int i=0; i < COLUMNS; i++){
			for(int j=0; j < ROWS; j++){
				states[currState][i][j] = evalSpace(states[currState], i, j);
			}
		}
		
		//success
		return true;
	}
	
	private boolean isColumn(char test){
		//Check and see how to do type casting to make this function smaller
		if(test == 'a' || test == 'b' || test == 'c' || test == 'd' 
		|| test == 'e' || test == 'f' || test == 'g' || test == 'h'){
			return true;
		}
		
		else
			return false;
	}
	
	private boolean isRow(char test){
		if(test == '1' || test == '2' || test == '3' || test == '4' 
		|| test == '5' || test == '6' || test == '7' || test == '8'){
			return true;
		}
		
		else
			return false;	
	}
	
	/**
	*******************************************************
	*                  PRIVATE HELPER FUNCTIONS
	*******************************************************
	*/
	/*
	These functions check an empty space's direction. It returns EMPTY if
	no pieces will be flipped in that direction, BLACK if white pieces could
	be flipped in that direction, or WHITE if black pieces could be flipped
	in that direction.
	*/

	/*
	Each of these functions compares its next space with the current space
	while the next space is not off the board. If the next space is the
	opposite color of the current space, then it returns the next space.
	If the next space is not a black or white piece, then it returns EMPTY,
	meaning nothing would happen in that direction, regardless of which player
	made the move.
	*/
	
	private char left(char[][] state, int column, int row){
		char nextSpace, currSpace;
		while(column > A){
			currSpace = state[column][row];
			nextSpace = state[column-1][row];
			if(nextSpace != BLACK && nextSpace != WHITE){
				return EMPTY;
			}
			else if(opposingPlayer(nextSpace) == currSpace){
				return nextSpace;
			}
			else{
				column--;
			}
		}
		return EMPTY;
	}
	
	private char leftUp(char[][] state, int column, int row){
		char nextSpace, currSpace;
		while(column > A && row > 0){
			currSpace = state[column][row];
			nextSpace = state[column-1][row-1];
			if(nextSpace != BLACK && nextSpace != WHITE){
				return EMPTY;
			}
			else if(opposingPlayer(nextSpace) == currSpace){
				return nextSpace;
			}
			else{
				column--;
				row--;
			}
		}
		return EMPTY;
	}
	
	private char up(char[][] state, int column, int row){
		char nextSpace, currSpace;
		while(row > 0){
			currSpace = state[column][row];
			nextSpace = state[column][row-1];
			if(nextSpace != BLACK && nextSpace != WHITE){
				return EMPTY;
			}
			else if(opposingPlayer(nextSpace) == currSpace){
				return nextSpace;
			}
			else{
				row--;
			}
		}
		return EMPTY;
	}
	
	private char rightUp(char[][] state, int column, int row){
		char nextSpace, currSpace;
		while(column < H && row > 0){
			currSpace = state[column][row];
			nextSpace = state[column+1][row-1];
			if(nextSpace != BLACK && nextSpace != WHITE){
				return EMPTY;
			}
			else if(opposingPlayer(nextSpace) == currSpace){
				return nextSpace;
			}
			else{
				column++;
				row--;
			}
		}
		return EMPTY;
	}
	
	private char right(char[][] state, int column, int row){
		char nextSpace, currSpace;
		while(column < H){
			currSpace = state[column][row];
			nextSpace = state[column+1][row];
			if(nextSpace != BLACK && nextSpace != WHITE){
				return EMPTY;
			}
			else if(opposingPlayer(nextSpace) == currSpace){
				return nextSpace;
			}
			else{
				column++;
			}
		}
		return EMPTY;
	}
	
	private char rightDown(char[][] state, int column, int row){
		char nextSpace, currSpace;
		while(column < H && row < 7){
			currSpace = state[column][row];
			nextSpace = state[column+1][row+1];
			if(nextSpace != BLACK && nextSpace != WHITE){
				return EMPTY;
			}
			else if(opposingPlayer(nextSpace) == currSpace){
				return nextSpace;
			}
			else{
				column++;
				row++;
			}
		}
		return EMPTY;
	}
	
	private char down(char[][] state, int column, int row){
		char nextSpace, currSpace;
		while(row < 7){
			currSpace = state[column][row];
			nextSpace = state[column][row+1];
			if(nextSpace != BLACK && nextSpace != WHITE){
				return EMPTY;
			}
			else if(opposingPlayer(nextSpace) == currSpace){
				return nextSpace;
			}
			else{
				row++;
			}
		}
		return EMPTY;
	}
	
	private char leftDown(char[][] state, int column, int row){
		char nextSpace, currSpace;
		while(column > A && row < 7){
			currSpace = state[column][row];
			nextSpace = state[column-1][row+1];
			if(nextSpace != BLACK && nextSpace != WHITE){
				return EMPTY;
			}
			else if(opposingPlayer(nextSpace) == currSpace){
				return nextSpace;
			}
			else{
				column--;
				row++;
			}
		}
		return EMPTY;
	}
	
	/*
	This function calls the above functions (checking all of the directions)
	to determine if the spot should be EMPTY, BLACK, WHITE, POSSIBLE_BLACK_MOVE,
	POSSIBLE_WHITE_MOVE, or POSSIBLE_BLACK_OR_WHITE_MOVE.
	*/
	
	private char evalSpace(char[][] state, int column, int row){
		//Check bounds
		if(column >= COLUMNS || column < 0 || row >= ROWS || row < 0){
			System.out.println("evalSpace: column or row range error");
		}
		
		//check if a piece is in that spot
		if(state[column][row] == WHITE){
			return WHITE;
		}
		if(state[column][row] == BLACK){
			return BLACK;
		}
		
		//Keep track of number of move possibilities
		int whiteCount = 0;
		int blackCount = 0;
		
		//return code
		char rc = EMPTY;
		
		//check each direction for each color
			rc = left(state, column, row);
		if(rc == WHITE){
			whiteCount++;
		}
		else if(rc == BLACK){
			blackCount++;
		}
		
		//left up
		rc = leftUp(state, column, row);
		if(rc == WHITE){
			whiteCount++;
		}
		else if(rc == BLACK){
			blackCount++;
		}
		
		//up
		rc = up(state, column, row);
		if(rc == WHITE){
			whiteCount++;
		}
		else if(rc == BLACK){
			blackCount++;
		}
		
		//right up
		rc = rightUp(state, column, row);
		if(rc == WHITE){
			whiteCount++;
		}
		else if(rc == BLACK){
			blackCount++;
		}
		
		//right
		rc = right(state, column, row);
		if(rc == WHITE){
			whiteCount++;
		}
		else if(rc == BLACK){
			blackCount++;
		}
		
		//right down
		rc = rightDown(state, column, row);
		if(rc == WHITE){
			whiteCount++;
		}
		else if(rc == BLACK){
			blackCount++;
		}
		
		//down
		rc = down(state, column, row);
		if(rc == WHITE){
			whiteCount++;
		}
		else if(rc == BLACK){
			blackCount++;
		}
		
		//left down
		rc = leftDown(state, column, row);
		if(rc == WHITE){
			whiteCount++;
		}
		else if(rc == BLACK){
			blackCount++;
		}
		
		//return new space value
		if(whiteCount > 0 && blackCount > 0){
			return POSSIBLE_BLACK_OR_WHITE_MOVE;
		}
		else if(whiteCount > 0){
			return POSSIBLE_WHITE_MOVE;
		}
		else if(blackCount > 0){
			return POSSIBLE_BLACK_MOVE;
		}
		else{
			return EMPTY;
		}
	}
	
	/**
	*******************************************************
	*                  PUBLIC FUNCTIONS
	*******************************************************
	*/

	//Constructor
	public Mechanics() {
		//Set up the board
		currState = 0;
		for(int i=0; i < COLUMNS; i++){
			for(int j=0; j < ROWS; j++){
				states[currState][i][j] = EMPTY;
			}
		}
		states[currState][D][4] = BLACK;
		states[currState][E][3] = BLACK;
		states[currState][D][3] = WHITE;
		states[currState][E][4] = WHITE;
		
		playerTurnArray[currState] = BLACK;
		
		for(int i=0; i < COLUMNS; i++){
			for(int j=0; j < ROWS; j++){
				states[currState][i][j] = evalSpace(states[currState], i, j);
			}
		}
		
		display = ON;
		numStates = 1;
		
		//print(states[currState], BLACK);
	}
	
	public boolean endGame(){
		if(numMoves(states[currState], WHITE) == 0 && numMoves(states[currState], BLACK) == 0){
			return true;
		}
		else
			return false;
	}
	
	/*
	This function takes in a player's command, move, or comment
	and handles it accordingly. It returns 1 if it should be
	the next player's turn, 0 if it should stay the same player's
	turn, and returns 2 if the game should end.
	*/
	
	public int parse(String input, char player) throws IOException{
		
		//System.out.println(input);
		//System.out.println(player);
		
		//empty input
		if(input.length() == 0){
			System.out.println("No input found.\n");
			return 0;
		}
		
		//comment
		else if(input.charAt(0) == ';'){
			System.out.println(input + "\n");
			return 0;
		}
		
		//exit needs fixing
		else if(input.equals("EXIT")){
			String confirm;
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			//String line = bufferedReader.readLine();
			do{
				System.out.println("Are you sure you want to exit?\nGame data will be lost! <y/n>\n>");
				confirm = bufferedReader.readLine();
				if(confirm.equals("y")){
					return 2;
				}
				else if(confirm.equals("n")){
					return 0;
				}
				else{
					System.out.println("Invalid input\n");
				}
			}while(true);
		}
		
		//display on
		else if(input.equals("DISPLAY_ON")){
			display = ON;
			print(states[currState], player);
			return 0;
		}
		
		//display off
		else if(input.equals("DISPLAY_OFF")){
			display = OFF;
			return 0;
		}
		
		//easy
		else if(input.equals("EASY")){
			System.out.println("Cannot change difficulty once game has started.\n");
			return 0;
		}
		
		//medium
		else if(input.equals("MEDIUM")){
			System.out.println("Cannot change difficulty once game has started.\n");
			return 0;
		}
		
		//hard
		else if(input.equals("HARD")){
			System.out.println("Cannot change difficulty once game has started.\n");
			return 0;
		}
		
		//black
		else if(input.equals("BLACK")){
			System.out.println("Cannot change color once game has started.\n");
			return 0;
		}
		
		//white
		else if(input.equals("WHITE")){
			System.out.println("Cannot change color once game has started.\n");
			return 0;
		}
		
		//undo
		else if(input.equals("UNDO")){
			if(!undo(player)){		//undo didn't work
				return -1;
			}
			if(display){
				//System.out.println("inside display");
				print(states[currState], player);
			}
			return 0;
		}
		
		//redo
		else if(input.equals("REDO")){
			if(!redo(player)){		//redo didn't work
				return -1;
			}
			if(display){
				print(states[currState], player);
			}
			return 0;
		}
		
		//show next position
		else if(input.equals("SHOW_NEXT_POS")){
			System.out.println("numMoves: " + numMoves(states[currState], player));
			print(states[currState], player);
			return 0;
		}
		
		//move
		else if(input.length() == 2){
			if(isColumn(input.charAt(0))){														//valid column
				if(isRow(input.charAt(1))){														//valid row
					//convert column and row to ints
					int tempColumn = (int)(input.charAt(0)) - 97;								//ascii conversion
					int tempRow = (int)(input.charAt(1)) - 48;									//ascii conversion
					//System.out.println(tempColumn);
					//System.out.println(tempRow);
					if(move(tempColumn, tempRow-1, player)){
						//valid move. check if game is over
						if(endGame()){
							if(display){
								print(states[currState], player);
							}
							if(score(states[currState], WHITE) == score(states[currState], BLACK)){			//tie
								System.out.println("Tie game! Final scores\nBLACK pieces: " + numPieces(states[currState], BLACK) +  "\nWHITE pieces: "
									 + numPieces(states[currState], WHITE) + "\n");
								return 2;
							}
							else if(score(states[currState], WHITE) < score(states[currState], BLACK)){		//Black wins
								System.out.println("BLACK wins! Final scores\nBLACK pieces: "
									 + numPieces(states[currState], BLACK) + "\nWHITE pieces: "
									 + numPieces(states[currState], WHITE) + "\nBLACK score: "
									 + score(states[currState], BLACK) + "\n");
								return 2;
							}
							else if(score(states[currState], WHITE) > score(states[currState], BLACK)){		//White wins
								System.out.println("White wins! Final scores\nBLACK pieces: "
									 + numPieces(states[currState], BLACK) + "\nWHITE pieces: "
									 + numPieces(states[currState], WHITE) + "\nWHITE score: "
									 + score(states[currState], WHITE) + "\n");
								return 2;
							}
						}
						else{																//game is not over. Check number of opponent moves
							if(numMoves(states[currState], opposingPlayer(player)) == 0){
								System.out.println("Opposing player has no move to make. Go again!\n");
								if(display){
									print(states[currState], player);				//print(states[currState]);		change back after debugging
								}
								playerTurnArray[currState] = player;
								return 0;
							}
							else{
								if(display){
									print(states[currState], opposingPlayer(player));				//print(states[currState]);		change back after debugging
								}
								playerTurnArray[currState] = opposingPlayer(player);
								return 1;
							}
						}
					}
				}
			}
			//System.out.println(input);
			System.out.println("Invalid move. Type \"SHOW_NEXT_POS\" to see possible moves.\n");
			return 0;																		//invalid move
		}
		
		//invalid command
		else{
			System.out.println(input);
			System.out.println("Invalid input.\n");
			return 0;
		}
	}
	
	
	public int getNumMoves(char player){
		return numMoves(states[currState], player);
	}
	
	public void copyCurrState(char[][] state){
		System.arraycopy( states[currState], 0, state, 0, states[currState].length);
	}
	
	public char[][] getState(){
		return states[currState];
	}
	/**
	*******************************************************
	*                  MAIN FUNCTION
	*******************************************************
	*/
/*	public static void main(String [] args) throws IOException {
		Mechanics o = new Mechanics();
		
		AI ai = new AI();
		
		String input;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int rc = 0;
		
		System.out.println("Welcome to the best Othello game eva!\n" + "Enter a command:");
		
		//set difficulty
		boolean goAgain = false;
		do{
			System.out.println("\nEnter a difficulty: <EASY/MEDIUM/HARD>");
			input = bufferedReader.readLine();
			if (input.equals("EASY")){
				ai.setDifficulty(EASY);
				goAgain = false;
			}
			else if(input.equals("MEDIUM")){
				ai.setDifficulty(MEDIUM);
				goAgain = false;
			}
			else if(input.equals("HARD")){
				ai.setDifficulty(HARD);
				goAgain = false;
			}
			else{
				System.out.println("Invalid difficulty");
				goAgain = true;
			}
		}while(goAgain);
		
		//set color
		do{
			System.out.println("\nEnter your color <BLACK/WHITE>");
			input = bufferedReader.readLine();
			if (input.equals("BLACK")){
				ai.setPlayerColor(WHITE);
				goAgain = false;
			}
			else if(input.equals("WHITE")){
				ai.setPlayerColor(BLACK);
				goAgain = false;
			}
			else{
				System.out.println("Invalid color");
				goAgain = true;
			}
		}while(goAgain);
		
		//play
		o.print(o.getState(), BLACK);
		while(rc != 2){
			do{
				if (ai.getColor() == BLACK){
					System.out.println("\nBLACK>");
					rc = o.parse(ai.go(o), BLACK);
				}
				else{
					System.out.print("\nBLACK>");
					input = bufferedReader.readLine();
					rc = o.parse(input, BLACK);
				}
			}while(rc == 0);
						
			if(rc != 2){
				do{
					if (ai.getColor() == WHITE){
						System.out.println("\nWHITE>");
						rc = o.parse(ai.go(o), WHITE);
					}
					else{
						System.out.print("\nWHITE>");
						input = bufferedReader.readLine();
						rc = o.parse(input, WHITE);
					}
				}while(rc == 0);
			}
		}
		
	}
*/
}