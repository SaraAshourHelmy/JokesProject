package com.example.sara.jokesproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.displayjokeslibrary.DisplayJokeActivity;
import com.example.sara.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by sara on 9/25/2017.
 */

public class JokeAsyncTask extends AsyncTask<Void, Void, String> {
    private MyApi myApiService = null;
    private Context context;
    private ProgressDialog dialog;

    public JokeAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    //.setRootUrl("http://10.0.2.2:8080/_ah/api/") // 10.0.2.2 is localhost's IP address in Android emulator
                    .setRootUrl("https://default-demo-app-85a97.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        try {
            return myApiService.getJokes().execute().getJokeText();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {

        dialog.dismiss();
        if (result == null) {
            Toast.makeText(context, "An error Occur", Toast.LENGTH_LONG).show();
            return;
        }
        if (!result.isEmpty()) {
            Intent intent = new Intent(context, DisplayJokeActivity.class);
            intent.putExtra(DisplayJokeActivity.EXTRA_JOKE, result);
            context.startActivity(intent);
        }
    }
}
