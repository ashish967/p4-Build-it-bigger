package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


/**
 * Created by ashish-til on 28/8/16.
 */
public class GCETest extends AndroidTestCase {

     String apiResponse;

    public void testGCEWithAsyncTask(){

        apiResponse=null;
        final CountDownLatch signal = new CountDownLatch(1);
        MainActivity.EndpointsAsyncTask task= new MainActivity.EndpointsAsyncTask(new MainActivity.EndpointsAsyncTask.ResponseListener() {
            @Override
            public void onResponse(String response) {

                Log.d("GCETest",response);
                assertEquals(true,response!=null);
                apiResponse= response;
                signal.countDown();// notif
            }
        });

        task.execute();

        try {
            signal.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            assertEquals(true,false);
        }
        Log.d("GCETest",apiResponse+"");
        assertTrue(apiResponse!=null&&!apiResponse.isEmpty());
    }
}
