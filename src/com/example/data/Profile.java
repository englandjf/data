package com.example.data;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.parse.ParseUser;

public class Profile extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		TextView totalPosts = (TextView)findViewById(R.id.totalPosts);
		ParseUser currentUser = ParseUser.getCurrentUser();
		List<Object> posts = currentUser.getList("userPosts");
		if(posts != null)
			totalPosts.setText("Total Posts: " + posts.size());
		else
			totalPosts.setText("No posts found");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
