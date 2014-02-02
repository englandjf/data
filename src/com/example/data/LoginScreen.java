package com.example.data;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);
		final EditText username = (EditText) findViewById(R.id.loginText);
		final EditText password = (EditText) findViewById(R.id.passwordText);
		Button button = (Button)findViewById(R.id.loginButton);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				loginUser(username,password);		
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_screen, menu);
		return true;
	}
	
	private void loginUser(EditText username,EditText password){
		String u = username.getText().toString();
		String p = password.getText().toString();
		ParseUser.logInInBackground(u, p, new LogInCallback() {
			public void done(ParseUser user, ParseException e) {
				if(user != null){
					Log.i("Test","Loggedin");
					showToast("Logged In");
					finish();
				}
				else{
					Log.e("Error", "" + e.getMessage());
					showToast("Could not login");
					
				}
				
			}
		});
	}
	
	private void showToast(String message){
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

}
