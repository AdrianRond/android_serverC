package se.darkyon.adnroid_serverc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.jcraft.jsch.Session;

import se.darkyon.adnroid_serverc.ssh.HostData;
import se.darkyon.adnroid_serverc.ssh.JSession;

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

        Session session = JSession.getSession(new HostData(host, username, 22), password);
        if (session.isConnected()) {
            Log.d("isConntected", "true");
        } else {
            Log.d("isConntected", "false");
        }
    }
}
