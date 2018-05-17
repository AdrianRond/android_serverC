package se.darkyon.adnroid_serverc.ssh;

import android.os.AsyncTask;
import android.util.Log;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.Properties;

import se.darkyon.adnroid_serverc.LoginActivity;

public class JSession {
    private JSch jSch = new JSch();
    private LoginActivity callback;
    public Session session;

    public JSession(LoginActivity callback) {
        this.callback = callback;
    }

    public void getSession(HostData data) {
        new JSessionConnectTask(callback).execute(data);
    }

    public void execute(String... commands) {
        new JSessionExecuteTask(session).execute(commands);
    }
}


