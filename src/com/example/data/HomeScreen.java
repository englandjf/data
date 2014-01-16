

package com.example.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseUser;


public class HomeScreen extends Activity {

	//public static ParseObject mPosts = new ParseObject("Posts");

	public String currentUser;
	
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
	
	@Override
	protected void onResume(){
		super.onResume();
		ParseUser currentUser = ParseUser.getCurrentUser();
		if(isLoggedIn()){
			Toast.makeText(this, "Hello "+ currentUser.getUsername(), Toast.LENGTH_LONG).show();
			Log.i("Logged In! ",""+ currentUser.getUsername());
		}
		else{
			Log.i("Not Logged","In");
		}
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
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	public static ParseObject getObject(){
		return mPosts;
	}
	*/

}
