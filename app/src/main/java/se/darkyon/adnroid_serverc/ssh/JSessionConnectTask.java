package se.darkyon.adnroid_serverc.ssh;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.net.ConnectException;
import java.util.Vector;

import se.darkyon.adnroid_serverc.LoginActivity;

public class JSessionConnectTask extends AsyncTask<HostData, Void, Session> {
    JSch jSch = new JSch();
    LoginActivity callback;

    public JSessionConnectTask(LoginActivity callback) {
        this.callback = callback;
    }

    @Override
    protected Session doInBackground(HostData... hostData) {
        Session session;
        try {
            session = jSch.getSession(hostData[0].user, hostData[0].ip, hostData[0].port);
            session.setPassword(hostData[0].pass);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp channelSftp = (ChannelSftp)channel;
            channelSftp.cd("/home/steam");
            Vector filelist = channelSftp.ls("/home/steam");
            for(int i=0; i<filelist.size();i++){
                Log.d("session", filelist.get(i).toString());
            }
        } catch (Exception e) {
            Log.e("Exeption", e.getMessage());
            return null;
        }

        return session;
    }

    @Override
    protected void onPostExecute(Session session) {
        callback.onLogin(session);
    }
}
