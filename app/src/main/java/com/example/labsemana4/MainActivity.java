package com.example.labsemana4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button pingIt;
    private Button host;

    //>>>>>
    private EditText num1;
    private EditText num2;
    private EditText num3;
    private EditText num4;

    private SharedPreferences hostId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);

        host = findViewById(R.id.host );
        pingIt = findViewById(R.id.ping);
        pingIt.setOnClickListener(
                (view)->{
                    Intent i = new Intent(this, PingScreen.class);

                    startActivity(i);
                }
        );
        host.setOnClickListener(
                (view)->{
                    Intent i = new Intent(this, HostIshere.class);

                 num1.getText().toString();
                    String j = blender( num1.getText().toString(), num2.getText().toString(), num3.getText().toString(), num4.getText().toString() );
                    Log.e("hahahahahahahahahhaha", j);
                    hostId = getSharedPreferences("host", MODE_PRIVATE);
                    hostId.edit().putString("pointedHost",j).apply();
                    startActivity(i);
                }
        );





    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    public String blender(String a, String b, String c, String d){

        String converted = (""+a+"."+b+"."+c+"."+d);
        return converted;
    }
}


/*


*/