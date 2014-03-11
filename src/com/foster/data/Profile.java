package com.foster.data;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class Profile extends Activity {

	public static List<ParseObject> posts;
	public static List<String> titles;
	
	public final static String EXTRA_DECISION = "com.foster.data.TEMP";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		final String temp = "";
		TextView totalPosts = (TextView)findViewById(R.id.totalPosts);
		ParseUser currentUser = ParseUser.getCurrentUser();
		posts = currentUser.getList("userPosts");
		titles = currentUser.getList("Titles");
		final Button listScreen = (Button) findViewById(R.id.userPosts);
		if(posts != null)
		{
			totalPosts.setText("Total Posts: " + posts.size());	
			
			listScreen.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showToast();
					showPosts(temp);		
				}
			});				
		}
		//
		else
		{
			totalPosts.setText("No posts found");
			listScreen.setEnabled(false);
		}	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.home:
				Intent intent = new Intent(this,HomeScreen.class);
				startActivity(intent);
			default:
				return super.onOptionsItemSelected(item);
		}
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
	
	public static List<String> getTitles()
	{
		return titles;
	}
	
	public void showToast()
	{
		Toast.makeText(this, "Loading", Toast.LENGTH_LONG).show();
	}
}
