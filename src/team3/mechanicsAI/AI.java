package team3.mechanicsAI;

/*
import android.app.Activity;
import android.os.Bundle;
*/
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.util.Random;
//import java.util.Scanner;


public class AI extends Mechanics{
	//Artificial Intelligence Class

	//private members
	private char difficulty;
	private char playerColor;
	private char state[][] = new char[COLUMNS][ROWS];
	private int depth;
	
	//private functions
	
	//private void copyCurrState(Mechanics o){
	//	
	//}
	
	
	private Move minMax(char[][] state){
		depth = 0;
		return maxMove(state);
	}
	
	private Move maxMove(char[][] state){
		depth++;
		if(depth >= 3 || (numMoves(state, playerColor) == 0 && numMoves(state, opposingPlayer(playerColor))== 0)){
			Move temp = new Move();
			temp.val = evaluate(state);
			temp.str = "";
			depth--;
			return temp;
		}
		else{
			Move temp = new Move();
			temp.val = -INFINITY;
			temp.str = "";
			for(int i=0; i<COLUMNS; i++){
				for(int j=0; j<ROWS; j++){
					if(playerColor == BLACK){
						if(state[i][j] == POSSIBLE_BLACK_MOVE || state[i][j] == POSSIBLE_BLACK_OR_WHITE_MOVE){
							char[][] minState = testMove(state, i, j, playerColor);
							Move moveVal = minMove(minState);
							if(moveVal.val > temp.val){
								temp.val = moveVal.val;
								String move = "";							//stringstream ss;
								move += (char)(i+97);						//ss << (char)(i+97) << (j+1);
								move += (j+1);
								temp.str = move;
								//System.out.println("here");
								//System.out.println(temp.str);
							}
						}
					}
					else{
						if(state[i][j] == POSSIBLE_WHITE_MOVE || state[i][j] == POSSIBLE_BLACK_OR_WHITE_MOVE){
							char[][] minState = testMove(state, i, j, playerColor);
							Move moveVal = minMove(minState);
							if(moveVal.val > temp.val){
								temp.val = moveVal.val;
								String move = "";							//stringstream ss;
								move += (char)(i+97);						//ss << (char)(i+97) << (j+1);
								move += (j+1);
								temp.str = move;
								//System.out.println("here");
								//System.out.println(temp.str);
							}
						}
					}
				}
			}
			depth--;
			return temp;
		}
	}
	
	private Move minMove(char[][] state){
		depth++;
		Move temp = new Move();
		temp.val = INFINITY;
		temp.str = "";
		for(int i=0; i<COLUMNS; i++){
			for(int j=0; j<ROWS; j++){
				if(playerColor == BLACK){
					if(state[i][j] == POSSIBLE_WHITE_MOVE || state[i][j] == POSSIBLE_BLACK_OR_WHITE_MOVE){
						char[][] maxState = testMove(state, i, j, opposingPlayer(playerColor));
						Move moveVal = maxMove(maxState);
						if(moveVal.val < temp.val){
							temp.val = moveVal.val;
							String move = "";							//stringstream ss;
							move += (char)(i+97);						//ss << (char)(i+97) << (j+1);
							move += (j+1);
							temp.str = move;
						}
					}
				}
				else{
					if(state[i][j] == POSSIBLE_BLACK_MOVE || state[i][j] == POSSIBLE_BLACK_OR_WHITE_MOVE){
						char[][] maxState = testMove(state, i, j, opposingPlayer(playerColor));
						Move moveVal = maxMove(maxState);
						if(moveVal.val < temp.val){
							temp.val = moveVal.val;
							String move = "";							//stringstream ss;
							move += (char)(i+97);						//ss << (char)(i+97) << (j+1);
							move += (j+1);
							temp.str = move;
						}
					}
				}
			}
		}
		depth--;
		return temp;
	}
	
	private int evaluate(char[][] state){
		if(difficulty == MEDIUM){
			int val = 0;
		
			//number of pieces
			for(int i=0; i<COLUMNS; i++){
				for(int j=0; j<ROWS; j++){
					if(state[i][j] == playerColor){
						val += 1;
					}
					if(state[i][j] == opposingPlayer(playerColor)){
						val -= 1;
					}
				}
			}
			return val;
		}
		else{
			int val = 0;
			
			//check corners
			if(state[0][0] == playerColor){
				val += 300;
			}
			if(state[0][7] == playerColor){
				val += 300;
			}
			if(state[7][0] == playerColor){
				val += 300;
			}
			if(state[7][7] == playerColor){
				val += 300;
			}
			
			//check opposing corners
			if(state[0][0] == opposingPlayer(playerColor)){
				val -= 300;
			}
			if(state[0][7] == opposingPlayer(playerColor)){
				val -= 300;
			}
			if(state[7][0] == opposingPlayer(playerColor)){
				val -= 300;
			}
			if(state[7][7] == opposingPlayer(playerColor)){
				val -= 300;
			}
			
			//number openent moves
			for(int i=0; i<COLUMNS; i++){
				for(int j=0; j<ROWS; j++){
					if(playerColor == WHITE){
						if(state[i][j] == POSSIBLE_BLACK_MOVE
						   || state[i][j] == POSSIBLE_BLACK_OR_WHITE_MOVE){
							val -= 10;
						}
					}
					else{
						if(state[i][j] == POSSIBLE_WHITE_MOVE
						   || state[i][j] == POSSIBLE_BLACK_OR_WHITE_MOVE){
							val -= 10;
						}
					}
				}
			}
			
			//end of game means worry about winning
			int numSpaces = 0;
			for(int i=0; i<COLUMNS; i++){
				for(int j=0; j<ROWS; j++){
					if(state[i][j] != BLACK && state[i][j] != WHITE){
						numSpaces++;
					}
				}
			}
			
			//number of pieces
			for(int i=0; i<COLUMNS; i++){
				for(int j=0; j<ROWS; j++){
					if(state[i][j] == playerColor){
						if(numSpaces < 10){
							val += 10;
						}
						else{
							val += 3;
						}
					}
					if(state[i][j] == opposingPlayer(playerColor)){
						if(numSpaces < 10){
							val += 10;
						}
						else{
							val += 3;
						}
					}
				}
			}
			return val;
		}
	}
	
	private char[][] testMove(char[][] state, int column, int row, char player){
		//create the next state
		char[][] newState = new char[COLUMNS][ROWS];
		for(int i=0; i<COLUMNS; i++){
			for(int j=0; j<ROWS; j++){
				newState[i][j] = state[i][j];
			}
		}	
		//check each direction and flip oposing pieces
		int tempColumn;										//the next column to look at
		int tempRow;										//the next row to look at
		//left
		if(player == left(newState, column, row)){
			//state[column][row] = player;
			tempColumn = column - 1;
			tempRow = row;
			while(newState[tempColumn][tempRow] != player){
				newState[tempColumn][tempRow] = player;
				tempColumn--;
				//tempRow does not change
			}
		}
		
		//left up
		if(player == leftUp(newState, column, row)){
			//state[column][row] = player;
			tempColumn = column - 1;
			tempRow = row - 1;
			while(newState[tempColumn][tempRow] != player){
				newState[tempColumn][tempRow] = player;
				tempColumn--;
				tempRow--;
			}
		}
		
		//up
		if(player == up(newState, column, row)){
			//state[column][row] = player;
			tempColumn = column;
			tempRow = row - 1;
			while(newState[tempColumn][tempRow] != player){
				newState[tempColumn][tempRow] = player;
				//tempColumn does not change
				tempRow--;
			}
		}
		
		//right up
		if(player == rightUp(newState, column, row)){
			//state[column][row] = player;
			tempColumn = column + 1;
			tempRow = row - 1;
			while(newState[tempColumn][tempRow] != player){
				newState[tempColumn][tempRow] = player;
				tempColumn++;
				tempRow--;
			}
		}
		
		//right
		if(player == right(newState, column, row)){
			//state[column][row] = player;
			tempColumn = column + 1;
			tempRow = row;
			while(newState[tempColumn][tempRow] != player){
				newState[tempColumn][tempRow] = player;
				tempColumn++;
				//tempRow does not change
			}
		}
		
		//right down
		if(player == rightDown(newState, column, row)){
			//state[column][row] = player;
			tempColumn = column + 1;
			tempRow = row + 1;
			while(newState[tempColumn][tempRow] != player){
				newState[tempColumn][tempRow] = player;
				tempColumn++;
				tempRow++;
			}
		}
		
		//down
		if(player == down(newState, column, row)){
			//state[column][row] = player;
			tempColumn = column;
			tempRow = row + 1;
			while(newState[tempColumn][tempRow] != player){
				newState[tempColumn][tempRow] = player;
				//tempColumn does not change
				tempRow++;
			}
		}
		
		//left down
		if(player == leftDown(newState, column, row)){
			//state[column][row] = player;
			tempColumn = column - 1;
			tempRow = row + 1;
			while(newState[tempColumn][tempRow] != player){
				newState[tempColumn][tempRow] = player;
				tempColumn--;
				tempRow++;
			}
		}
		
		//change actual spot
		newState[column][row] = player;
		
		/*
		Now we need to re eavluate all of the pieces on the board
		to determine if they have a piece, are a valid move for
		each player, or is simply empty.
		*/
		
		for(int i=0; i<COLUMNS; i++){
			for(int j=0; j<ROWS; j++){
				newState[i][j] = evalSpace(newState, i, j);
			}
		}
		return newState;
	}
	
	
	public char opposingPlayer(char player){
		if(player == WHITE){
			return BLACK;
		}
		else if(player == BLACK){
			return WHITE;
		}
		System.out.println("opposingPlayer: Invalid color.");
		return WHITE;
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
	
	
	//helper functions for testMove
	private char evalSpace(char[][] state, int column, int row){
		//check column and row bounds
		if(column >= COLUMNS || column < 0 ||  row >= ROWS || row < 0){
			System.out.println("evalSpace: Column or row range error.");
		}
		
		//check if there is already a piece in that spot
		if(state[column][row] == WHITE){
			return WHITE;
		}
		if(state[column][row] == BLACK){
			return BLACK;
		}
		
		//keep count of white and black move possibilities
		int whiteCount = 0;
		int blackCount = 0;
		
		//return code for direction
		char rc = EMPTY;
		
		//check each direction for each color
		//left
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
				column--;	//go to left space
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
				column--;	//go to left, upper space
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
				row--;	//go to the space above
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
				column++;	//go to right, upper space
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
				column++;	//go to right space
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
				column++;	//go to right, lower space
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
				row++;	//go to the space below
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
				column--;	//go to left, lower space
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
	
	//constructor
	public AI(){
		difficulty = EASY;
		playerColor = WHITE;
		//depth = 0;
	}

	public String go(Mechanics o){
		if(difficulty == EASY){
			//do a random move
			int moveNum = 0;
			Random rand = new Random( );		//int randomNum = rand()%(o.getNumMoves(playerColor));
			int randomNum = rand.nextInt(o.getNumMoves(playerColor));  
			state = o.getState();
			
			//check if it is a valid move
			//check if valid move
			for(int i=0; i<COLUMNS; i++){
				for(int j=0; j<ROWS; j++){
					if(playerColor == WHITE){
						if(state[i][j] == POSSIBLE_WHITE_MOVE
							 || state[i][j] == POSSIBLE_BLACK_OR_WHITE_MOVE){
							if(moveNum == randomNum){
								//do this move
								String move = "";							//stringstream ss;
								move += (char)(i+97);						//ss << (char)(i+97) << (j+1);
								move += (j+1);
								System.out.println(move);		//cout << ss.str() << endl;
								return move;								//return ss.str();
							}
							else{
								//don't do this move
								moveNum++;
							}
						}
					}
					if(playerColor == BLACK){
						if(state[i][j] == POSSIBLE_BLACK_MOVE
							 || state[i][j] == POSSIBLE_BLACK_OR_WHITE_MOVE){
							if(moveNum == randomNum){
								//do this move
								String move = "";							//stringstream ss;
								move += (char)(i+97);						//ss << (char)(i+97) << (j+1);
								move += (j+1);								//cout << ss.str() << endl;
								System.out.println(move);
								return move;
							}
							else{
								//don't do this move
								moveNum++;
							}
						}
					}	
				}
			}
		}
		else if(difficulty == MEDIUM || difficulty == HARD){
			//put currstate on the stack alone
			char[][] columns = new char[COLUMNS][ROWS];
			for(int i=0; i<COLUMNS; i++){
				for(int j=0; j<ROWS; j++){
					columns[i][j] = o.getState()[i][j];
				}
			}
			
			//columns = o.getState();
			//print(o.getState(), playerColor);
			//Scanner scan = new Scanner(System.in);
			//scan.nextLine();
			//String input;
			//BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			//input = bufferedReader.readLine();
			//mightymax
			Move temp = new Move();
			temp = minMax(columns);
			//cout << temp.str << endl;
			System.out.println(temp.str);
			return temp.str;	
		}
				
		String move = ";Not supported.\n";
		return move;
	}
		
	public char getColor(){
		return playerColor;
	}
	
	public void setDifficulty(char diff){
		if (diff == EASY || diff == MEDIUM || diff == HARD){
			difficulty = diff;
		}
		else{
			System.out.println("Not supported.");
		}
	}
	
	public void setPlayerColor(char color){
		if (color == BLACK || color == WHITE){
			playerColor = color;
		}
		else{
			System.out.println("Not supported.");
		}
	}
}
	