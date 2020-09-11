package com.example.labsemana4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class HostIshere extends AppCompatActivity {


    private TextView hostReach;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_ishere);



        hostReach= findViewById(R.id.hostReach);
        back= findViewById(R.id.back);
        back.setOnClickListener(
                (view)->{
                    finish();
                }
        );
        String thisHost = getSharedPreferences("host",MODE_PRIVATE).getString("pointedHost","192.168.0.13");
        hostReach.setText("");
        new Thread(
                ()->{
                    try {
                        InetAddress inet = InetAddress.getByName(thisHost);
                        String samsung = inet.getHostAddress();
                        Log.e(">_>_>_>_>_>_>_>_>>_>_>_",samsung+"      "+thisHost);
                        boolean conection = inet.isReachable(1000);
                        for(int i = 0; i<5; i++){
                            if(conection){

                                runOnUiThread(
                                        ()->{
                                            hostReach.append("conectandome a "+samsung+" exitosamente\n");
                                        }
                                );

                            }else{
                                runOnUiThread(
                                        ()->{
                                            hostReach.append("no pude conectarme a "+samsung+" \n");
                                        }
                                );

                            }

                            Thread.sleep(2000);

                        }

                        Log.e(">_>_>_>_>_>_>_>_>>_>_>_","is conection "+ conection);
                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        ).start();


    }
}