package com.example.data;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;


public class PostScreen extends Activity  {

	public ParseObject mPosts = new ParseObject("Posts");
	//private ParseObject mPosts = HomeScreen.getObject();
	private String mtitle = "";
	private String mcontent = "";
	
	//final EditText mtitleText = (EditText) findViewById(R.id.title);
	//final EditText mcontentText = (EditText) findViewById(R.id.idea);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText mtitleText = (EditText) findViewById(R.id.title);
		final EditText mcontentText = (EditText) findViewById(R.id.idea);
	
		//ADD CONSTRAINTS FOR POSTING EMPTY!!!!
		mtitle = "";
		mcontent = "";
		//final EditText mtitleText = (EditText) findViewById(R.id.title);
		//final EditText mcontentText = (EditText) findViewById(R.id.idea);
		final Toast posted = Toast.makeText(this, "Posted", Toast.LENGTH_LONG);
		final Button button = (Button) findViewById(R.id.postButton);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mtitle = mtitleText.getText().toString();
				mcontent = mcontentText.getText().toString();
				Log.i("Title: ",mtitle);
				Log.i("Content: ",mcontent);
				posted.show();
				mPosts.put("Title",mtitle);
				mPosts.put("Content",mcontent);
				mPosts.put("Score", 0);
				Calendar rightNow = Calendar.getInstance();
				mPosts.put("Day",rightNow.get(Calendar.DAY_OF_YEAR));
				mPosts.put("Year",rightNow.get(Calendar.YEAR));
				mPosts.saveInBackground();
				mtitleText.setText("");
				onBackPressed();
			}
		});
		
	}
	
	public void toHomeScreen()
	{
		Intent intent = new Intent(this,HomeScreen.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
