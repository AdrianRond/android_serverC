package se.darkyon.adnroid_serverc.ssh;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.Properties;

import se.darkyon.adnroid_serverc.LoginActivity;

public class JSession {
    public static Session session;

    public static void getSession(HostData data, Activity callback) {
        new JSessionConnectTask((LoginActivity) callback).execute(data);
    }

    public static void execute(String... commands) {
        new JSessionExecuteTask(session).execute(commands);
    }
}


