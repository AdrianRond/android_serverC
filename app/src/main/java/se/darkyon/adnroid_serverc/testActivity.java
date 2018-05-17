package se.darkyon.adnroid_serverc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import se.darkyon.adnroid_serverc.ssh.JSession;

public class testActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void serverOnClick(View view) {
        EditText dirText = findViewById(R.id.dirEditText);
        EditText execText = findViewById(R.id.execEditText);

        String dir = dirText.getText().toString();
        String exec = execText.getText().toString();

        String screenCommand = "screen -S Ark3";
        String screemCommand2 = "screen -ls";
        String cdCommand = "cd " + dir + "/ShooterGame/Binaries/Linux";
        String startServerCommand = "./" + exec;

        JSession.execute(screemCommand2);
    }
}
