package com.example.data;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewAccount extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_account);
		Button newAccount = (Button) findViewById(R.id.newAccount);
		newAccount.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showCreated();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_account, menu);
		return true;
	}

	public void showCreated(){
		Toast.makeText(this, "Account Created", Toast.LENGTH_LONG).show();
	}
}
