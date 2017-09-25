package com.example.sara.jokesproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnJokes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnJokes = (Button) findViewById(R.id.btn_jokes);

        btnJokes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showJokes();
            }
        });


    }

    private void showJokes() {
        new JokeAsyncTask(this).execute();
    }

}
