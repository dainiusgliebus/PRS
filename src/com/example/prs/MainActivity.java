package com.example.prs;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	public final static String PLAYER_PICK = "com.example.prs.PLAYER_PICK";
	//Context context = getActivity();
	//SharedPreferences sharedPref = this.getSharedPreferences("com.example.prs", Context.MODE_PRIVATE);
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
    	int score = sharedPref.getInt("SCORE_LOST", 0);
    	TextView t = (TextView)findViewById(R.id.int_score_lost);
    	t.setText(String.valueOf(score));
    	
    	score = sharedPref.getInt("SCORE_WON", 0);
    	TextView t2 = (TextView)findViewById(R.id.int_score_won);
    	t2.setText(String.valueOf(score));
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    
    public void sendRock(View view) {
    	
    	Intent intent = new Intent(this, Match.class);
    	String msg = "rock";
    	intent.putExtra(PLAYER_PICK, msg);
    	startActivity(intent);
    }
    
    public void sendPaper(View view) {
    	Intent intent = new Intent(this, Match.class);
    	String msg = "paper";
    	intent.putExtra(PLAYER_PICK, msg);
    	startActivity(intent);
    }
    
    public void sendScissors(View view) {
    	Intent intent = new Intent(this, Match.class);
    	String msg = "scissors";
    	intent.putExtra(PLAYER_PICK, msg);
    	startActivity(intent);
    }
    
}
