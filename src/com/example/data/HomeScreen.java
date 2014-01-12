//Test

package com.example.data;

import com.parse.Parse;
import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class HomeScreen extends Activity {

	//public static ParseObject mPosts = new ParseObject("Posts");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		Parse.initialize(this, "lJOuXGXbg66r8PQNE6O4dxovHocUCBvUqd8qJedz","3oVwEWUbMXsk08soxwfNssNXs0wPJFxbgFzYeUev" );
		final Button postScreen = (Button) findViewById(R.id.postScreen);
		postScreen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toPostScreen();		
			}
		});
		
		final Button listScreen = (Button) findViewById(R.id.listScreen);
		listScreen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toListScreen();
				
			}
		});
	}
	
	public void toPostScreen()
	{
		Intent intent = new Intent(this,PostScreen.class);
		startActivity(intent);
	}

	public void toListScreen()
	{
		Intent intent = new Intent(this,ListScreen.class);
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}
	
	/*
	public static ParseObject getObject(){
		return mPosts;
	}
	*/

}
