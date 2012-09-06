package team3.othello;

import java.io.IOException;

import team3.mechanicsAI.Mechanics;
import team3.othello.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.graphics.*;
import android.content.Intent;
import android.widget.TextView;

public class Game extends Activity implements OnClickListener {
	
	LinearLayout ll;
	//Draw2d c;
	Draw2d pcc;
	Mechanics m = new Mechanics();
	
	boolean isError = true;
	
	//Create this stuff when Activity is started	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		
		String tag = "IntentDiff: ";
		String tag2 = "IntentColor: ";
		//Get Difficulty and player color
		String aiLevel = getIntent().getStringExtra("Difficulty");
		String colorChoice = getIntent().getStringExtra("Color");
		Log.d(tag, aiLevel);
		Log.d(tag2, colorChoice);
		TextView diffy = (TextView)findViewById(R.id.difficulty);
		diffy.setText(aiLevel);
		
		View hintButton = findViewById(R.id.hint);
		hintButton.setOnClickListener(this);
		View undoButton = findViewById(R.id.undo);
		undoButton.setOnClickListener(this);
		View redoButton = findViewById(R.id.redo);
		redoButton.setOnClickListener(this);
		View quitButton = findViewById(R.id.quit);
		quitButton.setOnClickListener(this);
		
		//Set AI difficulty
		if(aiLevel.equals("Easy")){
			Draw2d.ai.setDifficulty(m.EASY);
		}
		if(aiLevel.equals("Medium")){
			Draw2d.ai.setDifficulty(m.MEDIUM);
		}
		if(aiLevel.equals("Hard")){
			Draw2d.ai.setDifficulty(m.HARD);
		}
		//Set AI color
		if(colorChoice.equals("White")){
			Draw2d.ai.setPlayerColor(m.BLACK);
		}
		if(colorChoice.equals("Black")){
			Draw2d.ai.setPlayerColor(m.WHITE);
		}
		
		
		ll = (LinearLayout) findViewById(R.id.gameBoard);
		m = new Mechanics();
		//c = new Draw2d(this, m);
		
		try {
			pcc = new Draw2d(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//Bitmap result = Bitmap.createBitmap(25, 25, Bitmap.Config.ARGB_8888);
		//Canvas canvas = new Canvas();
		//canvas.setBitmap(result);
		//pcc.draw(canvas);
		//ll.draw(canvas);
		pcc.setLayoutParams(new LayoutParams(500, 500));
		ll.addView(pcc);
	}
	
	
	//When a button is clicked, pass instructions
	public void onClick(View v){
		String buttonHit = "Action: ";
		//String moveMade = "Move: ";
		switch(v.getId()){
		case R.id.hint:{
			String needAHint = "SHOW_NEXT_POS";
			Log.d(buttonHit, needAHint);
			//Place Xs or something on possible moves
			pcc.showHint();
			break;
			}
		case R.id.undo:{
			String oops = "UNDO";
			Log.d(buttonHit, oops);
			try {
				if(pcc.undo() == false){
					Intent i = new Intent(this, Alerts.class);
					i.putExtra("Error", "undo");
					startActivity(i);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Undo move
			break;
			}
		case R.id.redo:{
			String ctrlY = "REDO";
			Log.d(buttonHit, ctrlY);
			try {
				if(pcc.redo() == false){
					Intent i = new Intent(this, Alerts.class);
					i.putExtra("Error", "redo");
					startActivity(i);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Redo
			break;
			}
		case R.id.quit:{
			String quitter = "Quit Game";
			Log.d(buttonHit, quitter);
			pcc.surfaceDestroyed();
			finish();
			break;
			}	
		}
	}

}
