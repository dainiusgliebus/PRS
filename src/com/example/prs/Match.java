package com.example.prs;

import java.util.Random;

import com.example.prs.MainActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Match extends MainActivity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match);
        
        Intent intent = getIntent();
	    String msg = intent.getStringExtra(MainActivity.PLAYER_PICK);
        
        
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
    	int score = sharedPref.getInt("SCORE_LOST", 0);
    	TextView t = (TextView)findViewById(R.id.int_score_lost);
    	t.setText(String.valueOf(score));
    	
    	score = sharedPref.getInt("SCORE_WON", 0);
    	TextView t2 = (TextView)findViewById(R.id.int_score_won);
    	t2.setText(String.valueOf(score));
    	
    	match(msg);
    }
	
	/* increase score for wins */
	public void increaseScoreWon(){
    	SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
    	SharedPreferences.Editor prefsEditor = sharedPref.edit();
    	int score = sharedPref.getInt("SCORE_WON", 0);
    	score++;
    	prefsEditor.putInt("SCORE_WON", score);
    	prefsEditor.commit();

    	TextView t = (TextView)findViewById(R.id.int_score_won);
    	t.setText(String.valueOf(score));
    }
    
	/* increase score for loses */
    public void increaseScoreLost(){
    	SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
    	SharedPreferences.Editor prefsEditor = sharedPref.edit();
    	int score = sharedPref.getInt("SCORE_LOST", 0);
    	score++;
    	prefsEditor.putInt("SCORE_LOST", score);
    	prefsEditor.commit();

    	TextView t = (TextView)findViewById(R.id.int_score_lost);
    	t.setText(String.valueOf(score));
    }
    
    /* generate AI hand */
    public String AI_pick(){
    	String[] list = {"rock", "paper", "scissors"};
    	int idx = new Random().nextInt(list.length);
    	String random = (list[idx]);
    	return random;
    }
    
    /* generate match */
    public void match(String playerPick){
    	String aiPick = AI_pick();
    	TextView t 		 = (TextView)findViewById(R.id.txt_winner);
    	
    	ImageView imgPlayerPick	 = (ImageView)findViewById(R.id.img_player_pick);
    	
    	if(playerPick.equals("rock")){
    		imgPlayerPick.setImageResource(R.drawable.rock);
    	}else if(playerPick.equals("paper")){
    		imgPlayerPick.setImageResource(R.drawable.paper);
    	}else if(playerPick.equals("scissors")){
    		imgPlayerPick.setImageResource(R.drawable.scissors);
    	}
    	
    	ImageView imgAIPick	 = (ImageView)findViewById(R.id.img_ai_pick);
    	
    	if(aiPick.equals("rock")){
    		imgAIPick.setImageResource(R.drawable.rock);
    	}else if(aiPick.equals("paper")){
    		imgAIPick.setImageResource(R.drawable.paper);
    	}else if(aiPick.equals("scissors")){
    		imgAIPick.setImageResource(R.drawable.scissors);
    	}
    	
    	
    	// check for draw
    	if(playerPick.equals(aiPick)){
    		// To Do set draw
    		t.setText("Draw");
    		t.setTextColor(Color.MAGENTA);
    	}else{
    		
    		if(winnerCompare(playerPick, aiPick)){
        		increaseScoreWon();
        		t.setText("Won");
        		t.setTextColor(Color.GREEN);
        	}else{
        		increaseScoreLost();
        		t.setText("Lost");
        		t.setTextColor(Color.RED);
        	}
    	}
    	
    }
    
    /* compare hands*/
    public Boolean winnerCompare(String player, String ai){
    	String rock = "rock";
    	String paper = "paper";
    	String scissors = "scissors";
    	
    	if(player.equals(rock)){
    		
    		if(ai.equals(scissors)){
    			return true;
    		}else{
    			return false;
    		}
    		
    	}else if(player.equals(paper)){
    		
    		if(ai.equals(rock)){
    			return true;
    		}else{
    			return false;
    		}
    		
    	}else if(player.equals(scissors)){
    		
    		if(ai.equals(paper)){
    			return true;
    		}else{
    			return false;
    		}
    		
    	}
    	
    	return false;
    }
    
    
    public void sendRetry(View view) {
    	//super.onBackPressed();
    	
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }
    
}
