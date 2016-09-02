package com.example.jokedisplaylibrary;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    private static final String JOKE = "joke";

    String mJoke;


    public void getExtras(){

        mJoke= getIntent().getStringExtra(JOKE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.joke_activity_title));

        getExtras();;

        TextView tvJokeText= (TextView) findViewById(R.id.tv_joke);
        tvJokeText.setText(mJoke);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                 finish();
        }
        return true;
    }

    public static Intent createJokeDisplayIntent(Activity activity, String joke){

        Intent intent= new Intent(activity,JokeDisplayActivity.class);
        intent.putExtra(JOKE,joke);
        return intent;

    }
}
