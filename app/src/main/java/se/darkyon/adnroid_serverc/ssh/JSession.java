package se.darkyon.adnroid_serverc.ssh;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

import se.darkyon.adnroid_serverc.LoginActivity;

public class JSession {
    public static Session session;
    private static JSessionExecuteTask executeTask = null;
    private static ChannelShell Shell = null;

    public static void getSession(HostData data, Activity callback) {
        new JSessionConnectTask((LoginActivity) callback).execute(data);
    }

    public static void execute(String... commands) {
        if (Shell == null) {
            try {
                initShell();
            } catch (JSchException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        if (executeTask == null)
           executeTask = new JSessionExecuteTask(Shell);

        executeTask.execute(commands);
    }

    private static void initShell() throws JSchException, IOException {
        Shell = (ChannelShell)session.openChannel("shell");
        Shell.setOutputStream(new ByteArrayOutputStream());
        Shell.setInputStream(new ByteArrayInputStream(new byte[10000]));
    }
}


