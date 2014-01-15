

package com.example.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseUser;


public class HomeScreen extends Activity {

	public boolean loggedIn = false;
	//public static ParseObject mPosts = new ParseObject("Posts");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		Parse.initialize(this, "lJOuXGXbg66r8PQNE6O4dxovHocUCBvUqd8qJedz","3oVwEWUbMXsk08soxwfNssNXs0wPJFxbgFzYeUev" );
		final Button postScreen = (Button) findViewById(R.id.postScreen);
		if(isLoggedIn()){
			loggedIn = true;
			Log.i("Logged","In!");
		}
		else
			Log.i("Not Logged","In");
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
		final Button loginScreen = (Button) findViewById(R.id.loginScreen);
		loginScreen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toLoginScreen();
				
			}
		});
		final Button newAccount = (Button) findViewById(R.id.newAccount);
		newAccount.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toAccountScreen();
				
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
	
	public void toLoginScreen(){
		Intent intent = new Intent(this,LoginScreen.class);
		startActivity(intent);
	}
	
	public void toAccountScreen(){
		Intent intent = new Intent(this,NewAccount.class);
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}
	
	
	public boolean isLoggedIn()
	{
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null){
			loggedIn = true;
		}
		else{
			loggedIn = false;
		}
		return loggedIn;
	}
	
	/*
	public static ParseObject getObject(){
		return mPosts;
	}
	*/

}
