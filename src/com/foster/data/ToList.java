package com.foster.data;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ToList extends Activity {

	public final static String EXTRA_DECISION = "com.foster.data.TEMP";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_list);
		//ActionBar actionBar = getActionBar();
		//actionBar.hide();
		final Button listScreen = (Button) findViewById(R.id.top10);
		listScreen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toTop10();
				
			}
		});
		final Button newPosts = (Button) findViewById(R.id.newPosts);
		newPosts.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toNewPosts();
				
			}
		});
		final Button randomPosts = (Button) findViewById(R.id.randomPosts);
		//randomPosts.setEnabled(false);
		randomPosts.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toRandomPosts();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.to_list, menu);
		return true;
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
	public void toTop10()
	{
		Intent intent = new Intent(this,ListScreen.class);
		String temp = "top10";
		intent.putExtra(EXTRA_DECISION, temp);
		startActivity(intent);
	}
	
	public void toNewPosts()
	{
		Intent intent = new Intent(this,ListScreen.class);
		String temp = "newPosts";
		intent.putExtra(EXTRA_DECISION, temp);
		startActivity(intent);
	}
	
	public void toRandomPosts()
	{
		Intent intent = new Intent(this,ListScreen.class);
		String temp = "randomPosts";
		intent.putExtra(EXTRA_DECISION, temp);
		startActivity(intent);
	}

}
