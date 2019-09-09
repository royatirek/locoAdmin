package in.timemac.dev.locoadmin;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this,MyLoggingService.class));
        //Log.v("MainActivity","07-07 22:03:59.942 14133 15881 D QuizSubscriptionActivity: {\"answer_revealed_after\":10998,\"answer_status_after\":21998,\"contest_status\":10,\"current_time\":1530981239932,\"options\":[{\"current_time\":0,\"is_active\":false,\"is_correct\":false,\"rank\":0,\"server_time\":0,\"text\":\"Ears\",\"uid\":\"AEKM3VZIH5\"},{\"current_time\":0,\"is_active\":false,\"is_correct\":false,\"rank\":1,\"server_time\":0,\"text\":\"Fingers\",\"uid\":\"5QWX5752AY\"},{\"current_time\":0,\"is_active\":false,\"is_correct\":false,\"rank\":2,\"server_time\":0,\"text\":\"Thumb\",\"uid\":\"1YER8J3BIY\"}],\"question_rank\":1,\"server_time\":1530981240002,\"showingStatus\":false,\"text\":\"A human hand does NOT consist of __.\",\"uid\":\"W0MYPFCHEC\",\"user_answer\":\"\",\"user_status\":10}");
    }

}


