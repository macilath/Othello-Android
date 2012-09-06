package team3.othello;

import java.io.IOException;

import team3.mechanicsAI.AI;
import team3.mechanicsAI.Mechanics;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


public class Draw2d extends SurfaceView implements SurfaceHolder.Callback{
	//private static final String TAG = DrawBoard.class.getSimpleName();
	private DrawBoard thread;
	private char player = 'a';	//color of player whose turn it is
	boolean showNextPos = false;
	public static AI ai = new AI();
	Mechanics m = new Mechanics();
	boolean endOfGame = false;
	
	
	public Draw2d(Context context) throws IOException{
		super(context);
		getHolder().addCallback(this);
		thread = new DrawBoard(getHolder(), this);
		setFocusable(true);
		player = m.BLACK;
		
		//set AI color and difficulty here!!!!!!!!!***********************
		//ai.setPlayerColor(m.WHITE);
		//ai.setDifficulty(m.HARD);
		//****************************************************************
		
		//if ai is black, have him go first now
		if(ai.getColor() == player){
			for(int i=0; i<9999; i++){
				;//sleep
			}
			if(m.parse(ai.go(m), player) == 1){
				player = m.opposingPlayer(player);
			}
		}	
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// delegating event handling to the piece
			try {
				showNextPos = false;
				int rc = 0;
				
				if(ai.getColor() != player){	//human's turn
					if((int)event.getX() >= 5 && (int)event.getX() <= 60){	//column a
						if((int)event.getY() >= 10 && (int)event.getY() <= 65){	//row 1
							rc = m.parse("a1", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 70 && (int)event.getY() <= 125){	//row 2
							rc = m.parse("a2", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 130 && (int)event.getY() <= 185){	//row 3
							rc = m.parse("a3", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 190 && (int)event.getY() <= 245){	//row 4
							rc = m.parse("a4", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 250 && (int)event.getY() <= 305){	//row 5
							rc = m.parse("a5", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 310 && (int)event.getY() <= 365){	//row 6
							rc = m.parse("a6", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 370 && (int)event.getY() <= 425){	//row 7
							rc = m.parse("a7", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 430 && (int)event.getY() <= 485){	//row 8
							rc = m.parse("a8", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
					}
					
					if((int)event.getX() >= 65 && (int)event.getX() <= 120){	//column b
						if((int)event.getY() >= 10 && (int)event.getY() <= 65){	//row 1
							rc = m.parse("b1", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 70 && (int)event.getY() <= 125){	//row 2
							rc = m.parse("b2", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 130 && (int)event.getY() <= 185){	//row 3
							rc = m.parse("b3", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 190 && (int)event.getY() <= 245){	//row 4
							rc = m.parse("b4", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 250 && (int)event.getY() <= 305){	//row 5
							rc = m.parse("b5", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 310 && (int)event.getY() <= 365){	//row 6
							rc = m.parse("b6", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 370 && (int)event.getY() <= 425){	//row 7
							rc = m.parse("b7", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 430 && (int)event.getY() <= 485){	//row 8
							rc = m.parse("b8", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
					}
					
					if((int)event.getX() >= 125 && (int)event.getX() <= 180){	//column c
						if((int)event.getY() >= 10 && (int)event.getY() <= 65){	//row 1
							rc = m.parse("c1", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 70 && (int)event.getY() <= 125){	//row 2
							rc = m.parse("c2", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 130 && (int)event.getY() <= 185){	//row 3
							rc = m.parse("c3", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 190 && (int)event.getY() <= 245){	//row 4
							rc = m.parse("c4", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 250 && (int)event.getY() <= 305){	//row 5
							rc = m.parse("c5", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 310 && (int)event.getY() <= 365){	//row 6
							rc = m.parse("c6", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 370 && (int)event.getY() <= 425){	//row 7
							rc = m.parse("c7", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 430 && (int)event.getY() <= 485){	//row 8
							rc = m.parse("c8", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
					}
					
					if((int)event.getX() >= 185 && (int)event.getX() <= 240){	//column d
						if((int)event.getY() >= 10 && (int)event.getY() <= 65){	//row 1
							rc = m.parse("d1", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 70 && (int)event.getY() <= 125){	//row 2
							rc = m.parse("d2", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 130 && (int)event.getY() <= 185){	//row 3
							rc = m.parse("d3", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 190 && (int)event.getY() <= 245){	//row 4
							rc = m.parse("d4", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 250 && (int)event.getY() <= 305){	//row 5
							rc = m.parse("d5", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 310 && (int)event.getY() <= 365){	//row 6
							rc = m.parse("d6", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 370 && (int)event.getY() <= 425){	//row 7
							rc = m.parse("d7", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 430 && (int)event.getY() <= 485){	//row 8
							rc = m.parse("d8", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
					}
					
					if((int)event.getX() >= 245 && (int)event.getX() <= 300){	//column e
						if((int)event.getY() >= 10 && (int)event.getY() <= 65){	//row 1
							rc = m.parse("e1", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 70 && (int)event.getY() <= 125){	//row 2
							rc = m.parse("e2", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 130 && (int)event.getY() <= 185){	//row 3
							rc = m.parse("e3", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 190 && (int)event.getY() <= 245){	//row 4
							rc = m.parse("e4", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 250 && (int)event.getY() <= 305){	//row 5
							rc = m.parse("e5", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 310 && (int)event.getY() <= 365){	//row 6
							rc = m.parse("e6", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 370 && (int)event.getY() <= 425){	//row 7
							rc = m.parse("e7", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 430 && (int)event.getY() <= 485){	//row 8
							rc = m.parse("e8", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
					}
					
					if((int)event.getX() >= 305 && (int)event.getX() <= 360){	//column f
						if((int)event.getY() >= 10 && (int)event.getY() <= 65){	//row 1
							rc = m.parse("f1", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 70 && (int)event.getY() <= 125){	//row 2
							rc = m.parse("f2", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 130 && (int)event.getY() <= 185){	//row 3
							rc = m.parse("f3", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 190 && (int)event.getY() <= 245){	//row 4
							rc = m.parse("f4", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 250 && (int)event.getY() <= 305){	//row 5
							rc = m.parse("f5", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 310 && (int)event.getY() <= 365){	//row 6
							rc = m.parse("f6", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 370 && (int)event.getY() <= 425){	//row 7
							rc = m.parse("f7", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 430 && (int)event.getY() <= 485){	//row 8
							rc = m.parse("f8", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
					}
					
					if((int)event.getX() >= 365 && (int)event.getX() <= 420){	//column g
						if((int)event.getY() >= 10 && (int)event.getY() <= 65){	//row 1
							rc = m.parse("g1", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 70 && (int)event.getY() <= 125){	//row 2
							rc = m.parse("g2", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 130 && (int)event.getY() <= 185){	//row 3
							rc = m.parse("g3", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 190 && (int)event.getY() <= 245){	//row 4
							rc = m.parse("g4", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 250 && (int)event.getY() <= 305){	//row 5
							rc = m.parse("g5", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 310 && (int)event.getY() <= 365){	//row 6
							rc = m.parse("g6", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 370 && (int)event.getY() <= 425){	//row 7
							rc = m.parse("g7", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 430 && (int)event.getY() <= 485){	//row 8
							rc = m.parse("g8", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
					}
					
					if((int)event.getX() >= 425 && (int)event.getX() <= 480){	//column h
						if((int)event.getY() >= 10 && (int)event.getY() <= 65){	//row 1
							rc = m.parse("h1", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 70 && (int)event.getY() <= 125){	//row 2
							rc = m.parse("h2", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 130 && (int)event.getY() <= 185){	//row 3
							rc = m.parse("h3", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 190 && (int)event.getY() <= 245){	//row 4
							rc = m.parse("h4", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 250 && (int)event.getY() <= 305){	//row 5
							rc = m.parse("h5", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 310 && (int)event.getY() <= 365){	//row 6
							rc = m.parse("h6", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 370 && (int)event.getY() <= 425){	//row 7
							rc = m.parse("h7", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
						if((int)event.getY() >= 430 && (int)event.getY() <= 485){	//row 8
							rc = m.parse("h8", player);
							if(rc == 1){
								player = m.opposingPlayer(player);
							}
							else if(rc == 2){
								endGame();
							}
						}
					}
				}
				
				//sleep
				/*
				for(int i=0; i<9999999; i++){
					;//nop
				}
				*/
				
				if(ai.getColor() == player){	//ai should go
					do{
						rc = m.parse(ai.go(m), player);
						if(rc == 1){
							player = m.opposingPlayer(player);
						}
						else if(rc == 2){
							endGame();
						}
					}while(rc == 0 && !endOfGame);
				}	
						
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	@Override
	protected void onDraw(Canvas c){
		super.onDraw(c);
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		
		paint.setColor(Color.BLACK);
		c.drawPaint(paint);
		
		
		paint.setColor(Color.GREEN);
		//c.drawRect(20, 5, 50, 100, paint);
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				c.drawRect(60*i+5, 60*j+10 ,60*i+60 ,60*j+65, paint);
			}
		}
		
		
		paint.setAntiAlias(true);
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(m.getState()[i][j] == m.BLACK){
					paint.setColor(Color.BLACK);
					c.drawCircle((float)32.5+i*60, (float)37.5+j*60, 25, paint);
				}
				if(m.getState()[i][j] == m.WHITE){
					paint.setColor(Color.WHITE);
					c.drawCircle((float)32.5+i*60, (float)37.5+j*60, 25, paint);
				}
				
				if(showNextPos){
					if((m.getState()[i][j] == m.POSSIBLE_BLACK_MOVE ||
							m.getState()[i][j] == m.POSSIBLE_BLACK_OR_WHITE_MOVE)&& player == m.BLACK){
						paint.setARGB(150, 150, 150, 150);
						c.drawCircle((float)32.5+i*60, (float)37.5+j*60, 25, paint);
					}
					if((m.getState()[i][j] == m.POSSIBLE_WHITE_MOVE ||
							m.getState()[i][j] == m.POSSIBLE_BLACK_OR_WHITE_MOVE)&& player == m.WHITE){
						paint.setARGB(150, 150, 150, 150);
						c.drawCircle((float)32.5+i*60, (float)37.5+j*60, 25, paint);
					}	
				}	
			}
		}
		
		if(endOfGame){
			paint.setARGB(190, 255, 255, 255);
			c.drawRect(10, 160, 230, 325, paint);
			paint.setColor(Color.RED);
			paint.setTextSize(25);
			String out = "";
			int blackPieces = m.numPieces(m.getState(), m.BLACK);
			int whitePieces = m.numPieces(m.getState(), m.WHITE);
			int blackScore = m.score(m.getState(), m.BLACK);
			int whiteScore = m.score(m.getState(), m.WHITE);
			
			if(blackScore == 0){
				//tie
				out = "Tie game!";
				c.drawText(out, 20, 200, paint);
				out = "Black Pieces: " + blackPieces;
				c.drawText(out, 20, 230, paint);
				out = "White Pieces: " + whitePieces;
				c.drawText(out, 20, 260, paint);
			}
			else if(blackScore > 0){
				//black wins
				out = "Black wins!";
				c.drawText(out, 20, 200, paint);
				out = "Black Pieces: " + blackPieces;
				c.drawText(out, 20, 230, paint);
				out = "White Pieces: " + whitePieces;
				c.drawText(out, 20, 260, paint);
				out = "Score: " + blackScore;
				c.drawText(out, 20, 290, paint);
			}
			else{
				//white wins
				out = "White wins!";
				c.drawText(out, 20, 200, paint);
				out = "Black Pieces: " + blackPieces;
				c.drawText(out, 20, 230, paint);
				out = "White Pieces: " + whitePieces;
				c.drawText(out, 20, 260, paint);
				out = "Score: " + whiteScore;
				c.drawText(out, 20, 290, paint);
			}
		}
		
		
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		getHolder().addCallback(this);
		thread = new DrawBoard(getHolder(), this);
		thread.setRunning(true);
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if(!endOfGame){
			boolean retry = true;
			while (retry) {
				try {
					thread.join();
					retry = false;
				} catch (InterruptedException e) {
					// try again shutting down the thread
				}
			}
			//Log.d(TAG, "Thread was shut down cleanly");
		}
	}
	
	private void endGame(){
		endOfGame = true;
		thread.setRunning(false);
	}
	
	public void surfaceDestroyed() {
		// TODO Auto-generated method stub
		boolean retry = true;
		while (retry) {
			try {
				endGame();
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
		//Log.d(TAG, "Thread was shut down cleanly");
	}
	
	public boolean undo() throws IOException{
		if(endOfGame){
			return false;
		}
		if(m.parse("UNDO", player) == 0){
			return true;
		}
		return false;
	}
	
	public boolean redo() throws IOException{
		if(endOfGame){
			return false;
		}
		if(m.parse("REDO", player) == 0){
			return true;
		}
		return false;
	}
	
	public void showHint(){
		showNextPos = true;
	}
}
