package se.darkyon.adnroid_serverc.ssh;

import android.media.MediaCas;
import android.os.AsyncTask;
import android.util.Log;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import se.darkyon.adnroid_serverc.LoginActivity;

public class JSessionExecuteTask extends AsyncTask<String, Void, Boolean> {
    ChannelShell Shell;

    public JSessionExecuteTask(ChannelShell Shell) {
        this.Shell = Shell;
    }

    @Override
    protected Boolean doInBackground(String... commands) {
        try {
            if (!Shell.isConnected())
                Shell.connect();

            for (String command: commands)
                Shell.getInputStream().read(command.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (success) {
            Log.d("ExecuteSuccess", "True");
            try {
                Log.d("Output", new ByteArrayOutputStream(Shell.getOutputStream()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.d("ExecuteSuccess", "False");
        }
    }
}
