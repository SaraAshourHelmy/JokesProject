package com.example.displayjokeslibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayJokeActivity extends AppCompatActivity {

    TextView jokeTextView;
    public static final String EXTRA_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);
        jokeTextView = (TextView) findViewById(R.id.tv_joke);
        getData();
    }

    private void getData() {

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_JOKE)) {
            String joke = intent.getStringExtra(EXTRA_JOKE);
            jokeTextView.setText(joke);
        }
    }
}
