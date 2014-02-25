package com.foster.data;

import java.util.Calendar;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ListScreen extends Activity {

	//private int specialNumber = 11;
	//private boolean foundNumber = false;
	public final static String EXTRA_INFO2 = "com.foster.data.SCORE";
	public final static String EXTRA_INFO = "com.foster.data.INFO";
	public final static String EXTRA_AUTHOR = "com.foster.data.AUTHOR";
	public final static String EXTRA_TITLE = "com.foster.data.TITLE";
	public final static String EXTRA_ID = "com.foster.data.ID";
	private ListView mlistView;
	private ArrayAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_screen);
		/*
		Intent intent = getIntent();
		String temp = intent.getStringExtra(Profile.EXTRA_DECISION);
		if(temp != null)
			temp = intent.getStringExtra(Profile.EXTRA_DECISION);
		else
			temp = "";
		//Log.i("Passed Variable"," " + temp);
		if(temp.equals("user")){
			displayUserPosts();
		}
		else
			getPosts();	
			*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_screen, menu);
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
	@Override
	protected void onResume()
	{
		super.onResume();
		Intent intent = getIntent();
		String temp = intent.getStringExtra(Profile.EXTRA_DECISION);
		if(temp != null)
			temp = intent.getStringExtra(Profile.EXTRA_DECISION);
		else
			temp = "";
		//Log.i("Passed Variable"," " + temp);
		if(temp.equals("user")){
			displayUserPosts();
		}
		else
		{
			temp = intent.getStringExtra(ToList.EXTRA_DECISION);
			if(temp.equals("top10"))
				getPosts();	
			else if(temp.equals("newPosts"))
				getNewPosts();
			else if (temp.equals("randomPosts"))
				getRandomPosts();
		}
	}
	//Use this class for displaying lists

	/*
	public void getUserPosts()
	{
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> objects, ParseException e) {
				if(e == null){
					//Log.i("test","Retrieved "+objects.size());
					//Object[] temp = objects.toArray();
					//Log.i("date","Array 1: " + objects.get(1).getString("createdAt"));
					String[] postTitle = new String[objects.size()];
					String[] postContent = new String[objects.size()];
					String[] objectIds = new String[objects.size()];
					
					for(int i = 0;i <= postTitle.length-1;i++){
						postTitle[i]=objects.get(i).getString("Title") + "(" + objects.get(i).getInt("Score") + ")";
						postContent[i]=objects.get(i).getString("Content");
						objectIds[i]=objects.get(i).getObjectId();
					}
					setList(postTitle,postContent,objectIds);
				}
				else{
					Log.d("test","Error: " + e.getMessage());
				}
				
			}
		});
	}
	*/

	//WORKING ON THIS PART!!!!
	public void displayUserPosts()
	{
		final List<ParseObject> posts = Profile.getData();
		final List<String> titles = Profile.getTitles();
		mlistView = (ListView)findViewById(R.id.listView1);
		/*
		for(int i = 0; i < posts.size();i++)
		{
			titles[i] = posts.get(i).getString("Title");
		}
		*/
		mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,titles);
		mlistView.setAdapter(mAdapter);
		mlistView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				Log.i("Clicked", "Postion" + position);
				try {
					posts.get(position).fetchIfNeeded();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//Log.i("Object","Id " + objectIds[position]);
				displayContent(posts.get(position).getString("Content"),"" + posts.get(position).getInt("Score"),posts.get(position).getString("Author"),posts.get(position).getString("Title"),posts.get(position).getObjectId());				
				}
		});
		//Only works with object id!!! why...?


	}



	public void getPosts()
	{
		//score check!!!
		//Log.i("Score check", "Amount: " + scoreCheck());
		Calendar rightNow = Calendar.getInstance();
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Posts");
		query.orderByDescending("Score");
		query.setLimit(10);//CHANGED!
		query.whereEqualTo("Day",rightNow.get(Calendar.DAY_OF_YEAR));
		query.whereEqualTo("Year", rightNow.get(Calendar.YEAR));
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> objects, ParseException e) {
				if(e == null){
					Log.i("test","Retrieved "+objects.size());
					//Object[] temp = objects.toArray();
					//Log.i("date","Array 1: " + objects.get(1).getString("createdAt"));
					String[] postTitle = new String[objects.size()];
					String[] postContent = new String[objects.size()];
					String[] postScore = new String[objects.size()];
					//Authors added
					String[] authors = new String[objects.size()];
					String[] postID = new String[objects.size()];

					for(int i = 0;i <= postTitle.length-1;i++){
						postTitle[i]=objects.get(i).getString("Title") + "(" + objects.get(i).getInt("Score") + ")";
						postContent[i]=objects.get(i).getString("Content");
						postScore[i]= "" + objects.get(i).getInt("Score");
						//
						authors[i] =objects.get(i).getString("Author");
						postID[i] = objects.get(i).getObjectId();
					}

					setList(postTitle,postContent,postScore,authors,postID);
				}
				else{
					Log.d("test","Error: " + e.getMessage());
				}

			}
		});                                    


	}
	
	public void getNewPosts()
	{
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Posts");
		query.setLimit(10);
		query.orderByDescending("Day");
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> objects, ParseException e) {
				if(e == null){
					Log.i("test","Retrieved "+objects.size());
					String[] postTitle = new String[objects.size()];
					String[] postContent = new String[objects.size()];
					String[] postScore = new String[objects.size()];
					String[] authors = new String[objects.size()];
					String[] postID = new String[objects.size()];

					for(int i = 0;i <= postTitle.length-1;i++){
						postTitle[i]=objects.get(i).getString("Title") + "(" + objects.get(i).getInt("Score") + ")";
						postContent[i]=objects.get(i).getString("Content");
						postScore[i]= "" + objects.get(i).getInt("Score");
						authors[i] =objects.get(i).getString("Author");
						postID[i] = objects.get(i).getObjectId();
					}

					setList(postTitle,postContent,postScore,authors,postID);
				}
				else{
					Log.d("test","Error: " + e.getMessage());
				}

			}
		});
	}
	
	
	public void getRandomPosts()
	{
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Posts");
		query.setLimit(10);
		//query.orderByDescending("Day");
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> objects, ParseException e) {
				if(e == null){
					Log.i("test","Retrieved "+objects.size());
					String[] postTitle = new String[objects.size()];
					String[] postContent = new String[objects.size()];
					String[] postScore = new String[objects.size()];
					String[] authors = new String[objects.size()];
					String[] postID = new String[objects.size()];

					for(int i = 0;i <= postTitle.length-1;i++){
						postTitle[i]=objects.get(i).getString("Title") + "(" + objects.get(i).getInt("Score") + ")";
						postContent[i]=objects.get(i).getString("Content");
						postScore[i]= "" + objects.get(i).getInt("Score");
						authors[i] =objects.get(i).getString("Author");
						postID[i] = objects.get(i).getObjectId();
					}

					setList(postTitle,postContent,postScore,authors,postID);
				}
				else{
					Log.d("test","Error: " + e.getMessage());
				}

			}
		});
	}


	public void setList(final String[] postTitle, final String[] postContent, final String[] postScore,final String[] authors,final String[] postID){
		//Log.i("Test3","Content"+listPost[0]);
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listPost);
		//setListAdapter(adapter);
		mlistView = (ListView)findViewById(R.id.listView1);
		mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,postTitle);
		mlistView.setAdapter(mAdapter);
		mlistView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				Log.i("Clicked", "Postion" + position);
				Log.i("Object","Id " + postScore[position]);
				Log.i("Author"," "+authors[position]);
				//Removes score element
				String temp = postTitle[position];
				String temp2 = temp.substring(0, temp.length()-3);
				displayContent(postContent[position],postScore[position],authors[position],temp2,postID[position]);				
			}			
		});		
	}

	public void displayContent(String info,String score,String author,String title,String id){
		Intent intent = new Intent(this,IdeaScreen.class);
		intent.putExtra(EXTRA_INFO, info);
		intent.putExtra(EXTRA_INFO2,score);
		intent.putExtra(EXTRA_AUTHOR,author);
		intent.putExtra(EXTRA_TITLE,title);
		intent.putExtra(EXTRA_ID,id);
		Log.i("Start", "Activity");
		startActivity(intent);	
	}


	/*
	public int scoreCheck(){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Posts");
		specialNumber = 0;
		while(foundNumber == false){
			Log.i("Special ","Number " + specialNumber + " "+ specialNumber);
			//ParseQuery<ParseObject> query = ParseQuery.getQuery("Posts");
			query.whereGreaterThanOrEqualTo("Score",specialNumber);//greater than!!~!!!!!!!!!
			query.countInBackground(new CountCallback() {
				public void done(int count, ParseException e) {
					if(e == null){
						Log.i("Count", "Found: " + count);
						if(count <= 10)
							foundNumber = true;
					}
					else{
						Log.e("scoreCheck", "Error: " + e.getMessage());
					}
					
				}
			});
			specialNumber++;
		}
		return specialNumber;
	}
	*/

}