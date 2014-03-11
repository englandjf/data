package com.foster.data;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class PostScreen extends Activity  {

	public ParseObject mPosts = new ParseObject("Posts");
	//public int currentIndex;
	//private ParseObject mPosts = HomeScreen.getObject();
	//private String ID = mPosts.getObjectId();
	private String mtitle = "";
	private String mcontent = "";
	public int index;
	//final EditText mtitleText = (EditText) findViewById(R.id.title);
	//final EditText mcontentText = (EditText) findViewById(R.id.idea);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		final EditText mtitleText = (EditText) findViewById(R.id.title);
		final EditText mcontentText = (EditText) findViewById(R.id.idea);
	
		//ADD CONSTRAINTS FOR POSTING EMPTY!!!!
		mtitle = "";
		mcontent = "";
		getIndex();
		//final EditText mtitleText = (EditText) findViewById(R.id.title);
		//final EditText mcontentText = (EditText) findViewById(R.id.idea);
		final Toast posted = Toast.makeText(this, "Posted", Toast.LENGTH_LONG);
		final Button button = (Button) findViewById(R.id.postButton);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				posted.show();
				mtitle = mtitleText.getText().toString();
				mcontent = mcontentText.getText().toString();
				Log.i("Title: ",mtitle);
				Log.i("Content: ",mcontent);
				mPosts.put("Title",mtitle);
				mPosts.put("Content",mcontent);
				mPosts.put("Score", 0);
				mPosts.put("Index",index);
				//pull current index
				//check index
				//increase master index by 1
				//set post to index
				ParseUser currentUser = ParseUser.getCurrentUser();
				mPosts.put("Author",currentUser.getUsername());
				Calendar rightNow = Calendar.getInstance();
				mPosts.put("Day",rightNow.get(Calendar.DAY_OF_YEAR));
				mPosts.put("Year",rightNow.get(Calendar.YEAR));
				try {
					mPosts.save();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				currentUser.add("userPosts", mPosts);
				currentUser.add("Titles", mtitle);
				currentUser.saveInBackground();
				mtitleText.setText("");
				mcontentText.setText("");
				toHomeScreen();
			}
		});
		
	}
	//

	
	public void toHomeScreen()
	{
		Intent intent = new Intent(this,HomeScreen.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void getIndex()
	{
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Posts");
		query.getInBackground("DLTQz4O4nr", new GetCallback<ParseObject>() {
		  public void done(ParseObject object, ParseException e) {
		    if (e == null) {
		      index = object.getInt("Index");
		      object.increment("Index");
		      object.saveInBackground();
		      index++;
		    } else {
		      // something went wrong
		    }
		  }
		});
	}

}
