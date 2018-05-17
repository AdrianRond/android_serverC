package se.darkyon.adnroid_serverc.ssh;

import android.media.MediaCas;
import android.os.AsyncTask;
import android.util.Log;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import se.darkyon.adnroid_serverc.LoginActivity;

public class JSessionExecuteTask extends AsyncTask<String, Void, Boolean> {
    Session session;

    public JSessionExecuteTask(Session session) {
        this.session = session;
    }

    @Override
    protected Boolean doInBackground(String... commands) {
        for (String command: commands) {
            try {
                ChannelExec exec = (ChannelExec)session.openChannel("exec");
                InputStream inputStream = exec.getInputStream();

                exec.setCommand(command);
                exec.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                int index = 0;

                while ((line = reader.readLine()) != null)
                {
                    Log.d("Execute", ++index + " : " + line);
                }

                exec.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (success) {
            Log.d("ExecuteSuccess", "True");
        } else {
            Log.d("ExecuteSuccess", "False");
        }
    }
}
