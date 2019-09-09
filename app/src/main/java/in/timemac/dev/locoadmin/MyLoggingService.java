package in.timemac.dev.locoadmin;

 import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import com.jcraft.jsch.*;
/**
 * Created by ROCKSTAR on 01-07-2018.
 */

public class MyLoggingService extends IntentService{


    public MyLoggingService() {
        super("LoggingService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String tag = "MyLoggingService";
        JSch jsch=new JSch();
        try
        {
            Log.v(tag,"connecting");
            jsch.setConfig("StrictHostKeyChecking", "no");
            String password = "gudmorning";
            //enter your own EC2 instance IP here
            Session session = jsch.getSession("royatirek", "ec2-18-217-196-226.us-east-2.compute.amazonaws.com", 22);
            session.setPassword(password);
            session.connect();








        try {
            Runtime.getRuntime().exec("logcat -c");
            Log.v("MainActivity","07-07 22:03:59.942 14133 15881 D QuizSubscriptionActivity: {\"answer_revealed_after\":10998,\"answer_status_after\":21998,\"contest_status\":10,\"current_time\":1530981239932,\"options\":[{\"current_time\":0,\"is_active\":false,\"is_correct\":false,\"rank\":0,\"server_time\":0,\"text\":\"Ears\",\"uid\":\"AEKM3VZIH5\"},{\"current_time\":0,\"is_active\":false,\"is_correct\":false,\"rank\":1,\"server_time\":0,\"text\":\"Fingers\",\"uid\":\"5QWX5752AY\"},{\"current_time\":0,\"is_active\":false,\"is_correct\":false,\"rank\":2,\"server_time\":0,\"text\":\"Thumb\",\"uid\":\"1YER8J3BIY\"}],\"question_rank\":1,\"server_time\":1530981240002,\"showingStatus\":false,\"text\":\"A human hand does NOT consist of __.\",\"uid\":\"W0MYPFCHEC\",\"user_answer\":\"\",\"user_status\":10}");

            Process process = Runtime.getRuntime().exec("logcat");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));


            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.showtimeapp");
            if (launchIntent != null) {
                startActivity(launchIntent);//null pointer check in case package name was not found
            }


            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.indexOf("answer_revealed_after") != -1)
                {

                    //run stuff
                    int start = line.indexOf("{");
                    int stop = line.length();
                    String argumentString = line.substring(start,stop);
                    Channel channel = session.openChannel("exec");
                    String command = "python3 findAnswersLocoM.py "+"'"+argumentString+"'";
                    //Log.v(tag,command);
                    ((ChannelExec)channel).setCommand(command);
                    channel.connect();
                    //channel.disconnect();
                    //System.exit(0);
                    Log.d("FOUND", line.toString().replace("answer_revealed_after"," "));
                }
                else if(line.indexOf("total_winner") != -1) {

                    session.disconnect();

                }

            }
        }
        catch (IOException e) {
            Log.v(tag,e.toString());

        }
        finally {
            Log.v(tag,"disconnecting, reached finally block");
            session.disconnect();
        }

        }


        catch (JSchException e) {
            Log.v(tag,e.toString());
        }

    }

}
