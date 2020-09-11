package com.example.labsemana4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class PingScreen extends AppCompatActivity {

    private TextView ejemplo;
    ArrayList<String> datos = new ArrayList<String>();
    private boolean finale;
    private Button back;
    private TextView total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_screen);
//>>>>>>>>>>>>>>>>>>>>>
        total = findViewById(R.id.total);
        finale = true;
        back = findViewById(R.id.back);
        back.setOnClickListener(
                (view)->{
                    finish();
                }
        );

        ejemplo = findViewById(R.id.ejemplo);
        callingMama();
    }

    void callingMama (){

        ejemplo.setText("");
        new Thread(
                ()->{
                    while (finale){

                        try {
                            Thread.sleep(500);
                            for (int j = 1; j < 255; j++) {
                                try {
                                    InetAddress inet = InetAddress.getByName("192.168.0."+j);
                                    String samsung = inet.getHostAddress();
                                    boolean conection = inet.isReachable(1000);
                                    if(conection) {
                                        Log.e(">>>>jiji>>>>",samsung+"is conection "+ conection);
                                        if (j < 253) {
                                            datos.add(samsung + "is conection " + conection);
                                        }

                                        runOnUiThread(

                                                ()->{

                                                    ejemplo.append(samsung+conection+"\n");
                                                    total.setText(""+datos.size());

                                                }
                                        );


                                    }
                                } catch (UnknownHostException e) {

                                    e.printStackTrace();
                                } catch (IOException e) {

                                    e.printStackTrace();
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                }
        ).start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finale = false;
    }
}