package team3.othello;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException; 
import java.io.Reader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HighScores extends Activity {
	
	public String[] strArray = new String[12];
	int index = 0;
	//strArray = new String[12];
	String str = ""; 
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscores);
		
		TextView myXML = (TextView)findViewById(R.id.xml_scores);
		
		parse();
		
		myXML.setText(str);
		/*
		//From Lab 3 - Read in the XML file and display it in TextView
		try{
		File myFile = new File("/sdcard/example.xml");
		FileInputStream fStream = new FileInputStream(myFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fStream));
		String aDataRow = "";
		String aBuffer = "";
		while((aDataRow = reader.readLine()) != null){
			aBuffer += aDataRow + "\n";
		}
		
		myXML.setText(aBuffer);
		reader.close();
		//Toast.makeText(getBaseContext(), "Done reading SDCard", Toast.LENGTH_SHORT).show();
		}
		catch(Exception e){
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}	*/
	}
	
	public void parse(){
		try{
			//Create a new parser
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handle = new DefaultHandler() {
				boolean isID = false;
				boolean isName = false;
				boolean isScore = false;
				
				public void startElement(String uri, String localName, String elName, Attributes attributes) throws SAXException {
					//Follows tutorial from: 
					//http://www.mkyong.com/java/how-to-read-utf-8-xml-file-in-java-sax-parser/
					
					Log.d("Start element:", elName);
					
					if(elName.equalsIgnoreCase("ID")){
						isID = true;
					}
					if(elName.equalsIgnoreCase("NAME")){
						isName = true;
					}
					if(elName.equalsIgnoreCase("SCORE")){
						isScore = true;
					}
					
				}
				
				public void endElement(String uri, String localName, String elName) throws SAXException {
					Log.d("End element:", elName);
				}
				
				public void characters(char ch[], int begin, int length){
					//String input = new String(ch, begin, length);
					//Log.d("entries:", input);
					
					if(isID){
						String out = "Place: " + new String(ch, begin, length) + " ";
						strArray[index] = out;
						index++;
						Log.d("isID", out);
						isID = false;
					}
					if(isName){
						String out = "Name: " + new String(ch, begin, length) + " ";
						strArray[index] = out;
						index++;
						Log.d("isName", out);
						isName = false;						
					}
					if(isScore){
						String out = "Score: " + new String(ch, begin, length) + " ";
						strArray[index] = out;
						index++;
						strArray[index] = "\n";
						index++;
						Log.d("isScore", out);
						isScore = false;						
					}
				}
			};
			
			//Load file
			File myFile = new File("/sdcard/example.xml");
			FileInputStream fStream = new FileInputStream(myFile);
			Reader read = new InputStreamReader(fStream,"UTF-8");
			
			InputSource is = new InputSource(read);
			is.setEncoding("UTF-8");
			
			saxParser.parse(is, handle);
			
			//set up str
			for(int i=0; i<12; i++){
				str += strArray[i];
			}
			
			
		} // End of try
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
