package se.darkyon.adnroid_serverc.ssh;

import android.media.MediaCas;
import android.os.AsyncTask;
import android.util.Log;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import se.darkyon.adnroid_serverc.LoginActivity;

public class JSessionExecuteTask extends AsyncTask<String, Void, Boolean> {
    Session session;
    ByteArrayOutputStream outputStream;

    public JSessionExecuteTask(Session session) {
        this.session = session;
    }

    @Override
    protected Boolean doInBackground(String... commands) {
        for (String command: commands) {
            try {
                ChannelShell Shell = (ChannelShell)session.openChannel("shell");
                ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[1000]);
                outputStream = new ByteArrayOutputStream();

                Shell.setInputStream(inputStream);
                Shell.setOutputStream(outputStream);
                Shell.connect();

                inputStream.read(command.getBytes());

                Shell.disconnect();
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
            Log.d("Output", outputStream.toString());
        } else {
            Log.d("ExecuteSuccess", "False");
        }
    }
}
