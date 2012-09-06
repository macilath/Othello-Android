package team3.othello;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Alerts extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alerts);

		//Alerts for Errors
		String errorMsg = getIntent().getStringExtra("Error");
		Log.d("LOL:", errorMsg);
		TextView errorMessage = (TextView)findViewById(R.id.MSG);
		
		if(errorMsg.contentEquals("undo")){
			errorMessage.setText("Can\'t Undo That Move!");
		}
		else if(errorMsg.contentEquals("redo")){
			errorMessage.setText("No Move to Redo!");
		}
		else if(errorMsg.contentEquals("move")){
			errorMessage.setText("Invalid Move, Try Again!");
		}
		else{
			errorMessage.setText("An Unspecified Error Ocurred. WTF");
		}
	}
}
