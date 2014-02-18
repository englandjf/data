package com.foster.data;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.foster.data.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class NewAccount extends Activity {

	//ISSUE WITH MAKING ACCOUNT!!!!
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_account);
		final EditText userName = (EditText) findViewById(R.id.newUsername);
		final EditText password1 = (EditText) findViewById(R.id.newPassword1);
	
		Button newAccount = (Button) findViewById(R.id.newAccountButton);
		newAccount.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				signUp(userName,password1);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_account, menu);
		return true;
	}

	
	public void signUp(EditText userName,EditText password1){
		String newUserName = userName.getText().toString();
		String newPword1 =  password1.getText().toString();
		ParseUser user = new ParseUser();
		user.setUsername(newUserName);
		user.setPassword(newPword1);
		user.signUpInBackground(new SignUpCallback() {
			public void done(ParseException e) {
				if(e == null){
					accountMade();
					onBackPressed();
				}
				else{
					Log.e("Error","" + e.getMessage());
					onBackPressed();
				}
			}
		});
		
	}
	
	private void accountMade(){
		Toast.makeText(this, "Account created", Toast.LENGTH_LONG).show();
	}
}
