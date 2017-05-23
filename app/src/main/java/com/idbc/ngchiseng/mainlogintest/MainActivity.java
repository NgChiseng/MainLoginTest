package com.idbc.ngchiseng.mainlogintest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /* --- Here, its will declare the private variables of the class
     */
    private Boolean visited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* --- This will declare TourSharedPreference and access to the preferences files named "Tour"
        and the MODE_PRIVATE by 0
         */
        final SharedPreferences initTour = getSharedPreferences("Tour", 0);
        /* --- This will get the TourSharedPreference to the preferences access files named visited,
        and if no exist the preference "visited" , the return the default value false.
         */
        visited = initTour.getBoolean("visited", false);

        if (visited){

        } else {

            /* --- This will create the intent to pass information of the context to the TourActivity,
            close the old activities on the Activities stack, put the TourActivity in top of the
            stack after the its start, and finally call onDestroy() the present activity.
             */
            Intent i = new Intent(MainActivity.this, TourActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
    }
}
