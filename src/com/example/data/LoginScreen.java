package com.example.data;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);
		loginUser();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_screen, menu);
		return true;
	}
	
	private void loginUser(){
		ParseUser.logInInBackground("test11", "test", new LogInCallback() {
			public void done(ParseUser user, ParseException e) {
				if(user != null){
					Log.i("Test","Loggedin");
				}
				else{
					Log.e("Error", "" + e.getMessage());
				}
				
			}
		});
	}

}
