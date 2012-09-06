
/*
package team3.othello;

import android.view.*;
import android.content.Context;
import android.graphics.*;

public class DrawBoard extends View {

	private static int COLUMNS = 8;
	private static int ROWS = 8;
	
	private int width;
	private int height;
	private int spaceHeight;
	private int spaceWidth;
	
	private int seleX;
	private int seleY;
	private final Rect selRect = new Rect();
	private final Game game;
	
	public DrawBoard(Context context){
		super(context);
		this.game = (Game) context;
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		width = w/8;
		height = h/8;
		getRect(seleX, seleY, selRect);
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	private void getRect(int x, int y, Rect rect){
		//Stuff
	}
	
	protected void onDraw(Canvas canvas){
		Paint bkg = new Paint();
		bkg.setColor(getResources().getColor(R.color.background));
		canvas.drawRect(0,0, getWidth(), getHeight(), bkg);	
		
		Paint line = new Paint();
		line.setColor(getResources().getColor(R.color.gridLine));
	}
	
}
*/

package team3.othello;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class DrawBoard extends Thread {
	
	private static final String TAG = DrawBoard.class.getSimpleName();
	// Surface holder that can access the physical surface
	private SurfaceHolder surfaceHolder;
	// The actual view that handles inputs
	// and draws to the surface
	private Draw2d gamePanel;
	
	// flag to hold game state 
	private boolean running;
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public DrawBoard(SurfaceHolder surfaceHolder, Draw2d gamePanel) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void run() {
		Canvas canvas;
		//Log.d(TAG, "Starting game loop");
		while (running) {
			canvas = null;
			// try locking the canvas for exclusive pixel editing
			// in the surface
			try {
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					// update game state 
					// render state to the screen
					// draws the canvas on the panel
					this.gamePanel.onDraw(canvas);				
				}
			} finally {
				// in case of an exception the surface is not left in 
				// an inconsistent state
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}	// end finally
		}
	}
}

