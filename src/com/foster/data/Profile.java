package com.foster.data;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.foster.data.R;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class Profile extends Activity {

	public static List<ParseObject> posts;
	
	public final static String EXTRA_DECISION = "com.foster.data.TEMP";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		final String temp = "";
		TextView totalPosts = (TextView)findViewById(R.id.totalPosts);
		ParseUser currentUser = ParseUser.getCurrentUser();
		posts = currentUser.getList("userPosts");
		if(posts != null)
			totalPosts.setText("Total Posts: " + posts.size());
		else
			totalPosts.setText("No posts found");
		
		final Button listScreen = (Button) findViewById(R.id.userPosts);
		listScreen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showPosts(temp);		
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	public void showPosts(String temp){
		Intent intent = new Intent(this,ListScreen.class);
		temp = "user";
		intent.putExtra(EXTRA_DECISION,temp);
		startActivity(intent);	
	}
	
	
	public static List<ParseObject> getData()
	{
		return posts;
	}
	
}
