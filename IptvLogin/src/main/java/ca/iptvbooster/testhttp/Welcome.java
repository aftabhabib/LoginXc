package ca.iptvbooster.testhttp;

/**
 * Created by Michel Arcato (Techker).
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.app.Service.START_STICKY;
import static android.content.ContentValues.TAG;

public class Welcome extends Activity {

    Button btnLogout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        Intent intent = getIntent();

        String Welcome=("Welcome ," +getIntent().getStringExtra("Username"));
        String Username= (getIntent().getStringExtra("Username"));
        String Password =(getIntent().getStringExtra("Password"));
        TextView textView = (TextView) findViewById(R.id.usertext);
        textView.setTextSize(45);
        textView.setText(Welcome);

          System.out.println("reseived  "+ Welcome);
          System.out.println("reseived Pass  "+ Password);



        btnLogout = (Button) findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent login = new Intent(getApplicationContext(), MainActivity.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login);
                finish();

                System.exit(0);

            }
        });

    }



}







