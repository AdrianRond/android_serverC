package se.darkyon.adnroid_serverc.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.Properties;

public class JSession {
    private static JSch jSch = new JSch();

    public static Session getSession(HostData data, String password) {
        Session session;
        try {
            session = jSch.getSession(data.user, data.ip, data.port);
            session.setPassword(password);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect(30000);
        } catch (JSchException e) {
            return null;
        }

        return session;
    }
}


