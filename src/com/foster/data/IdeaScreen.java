package com.foster.data;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class IdeaScreen extends Activity {

	
	//private ParseObject mPosts = HomeScreen.getObject();
	private boolean goodIdea;
	public String objectID;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_idea_screen);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		Intent intent = getIntent();
		TextView textview = (TextView) findViewById(R.id.textView1);
		textview.setText(intent.getStringExtra(ListScreen.EXTRA_INFO));
		TextView score = (TextView) findViewById(R.id.score);
		objectID = intent.getStringExtra(ListScreen.EXTRA_INFO2);
		TextView author = (TextView) findViewById(R.id.author);
		ParseUser currentUser = ParseUser.getCurrentUser();
		author.setText(intent.getStringExtra(ListScreen.EXTRA_AUTHOR));
		//Update this variable for score
		score.setText(objectID);
		Button goodButton = (Button) findViewById(R.id.goodIdea);
		Button badButton = (Button) findViewById(R.id.badIdea);
		//Log.i("TEXT ",""+author.getText().toString());
		//Log.i("USER ",""+currentUser.getUsername());
		if(currentUser != null)
		{
		if(!author.getText().toString().equals(currentUser.getUsername()) )
		{
			goodButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					goodIdea = true;
					updateScore();
				}
			});
			
			badButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					goodIdea = false;
					updateScore();
				}
			});
		}
		else
		{
			goodButton.setEnabled(false);
			badButton.setEnabled(false);
		}
		
		}
		else
		{
			goodButton.setEnabled(false);
			badButton.setEnabled(false);
		}
		}
			

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.idea_screen, menu);
		return true;
	}
	
	public void updateScore(){
		ParseQuery <ParseObject> query = ParseQuery.getQuery("Posts");
		Log.i("Fuck","Balls " + objectID);
		query.getInBackground(objectID, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if(e == null){
					if(goodIdea){
						Log.i("Tesss","hh" +object.getString("Title"));
						object.increment("Score");
						object.saveInBackground();
						toListScreen();
					}
					else{
						object.put("Score",object.getInt("Score")-1);
						object.saveInBackground();
						toListScreen();
					}
						
				}
				else
				Log.e("Problem","Big " + e.getMessage());
				
			}
		});
	}
	
	public void toListScreen()
	{
		Intent intent = new Intent(this,ListScreen.class);
		startActivity(intent);
	}

}
