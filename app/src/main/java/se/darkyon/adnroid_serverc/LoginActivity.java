package se.darkyon.adnroid_serverc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;

import java.util.Vector;
import java.util.concurrent.ExecutionException;

import se.darkyon.adnroid_serverc.ssh.HostData;
import se.darkyon.adnroid_serverc.ssh.JSession;
import se.darkyon.adnroid_serverc.ssh.JSessionConnectTask;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginClick(View view) {
        EditText usernameText = findViewById(R.id.userEditText);
        EditText passwordText = findViewById(R.id.passEditText);
        EditText hostText = findViewById(R.id.hostEditText);

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        String host = hostText.getText().toString();

        JSession.getSession(new HostData(host, username, password, 22), this);
    }

    public void onLogin(Session session) {
        if (session.isConnected()) {
            Log.d("isConntected", "true");
            Log.d("something", session.getHost());

            JSession.session = session;

            Intent intent = new Intent(this, testActivity.class);
            startActivity(intent);
        } else {
            Log.d("isConnected", "false");
        }
    }
}
