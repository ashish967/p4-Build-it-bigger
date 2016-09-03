package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jokedisplaylibrary.JokeDisplayActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.samplebackend.android.myApi.MyApi;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {



    ProgressDialog mProgressDialog;

//    public static String serverIp="192.168.1.7:8080"; // use this if  running on physical device and replace string with computer ip address
    public static String serverIp="10.0.2.2:8080"; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mProgressDialog= new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.progress_message));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){

        EndpointsAsyncTask task= new EndpointsAsyncTask(new EndpointsAsyncTask.ResponseListener() {
            @Override
            public void onResponse(String response) {

                mProgressDialog.dismiss();
                Intent intent= JokeDisplayActivity.createJokeDisplayIntent(MainActivity.this,response);
                startActivity(intent);

            }
        });

        task.execute();
        mProgressDialog.show();

    }

    public  static class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
        private static MyApi myApiService = null;
        private Context context;

        ResponseListener mListener;
        public interface ResponseListener{

            public void onResponse(String response);
        }

        public EndpointsAsyncTask(ResponseListener listener){

            this.mListener= listener;
        }
        @Override
        protected String doInBackground(Void ...voids) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://"+serverIp+"/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }


            try {
                return myApiService.getJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if(result!=null) {

                mListener.onResponse(result);
            }
        }
    }
}
