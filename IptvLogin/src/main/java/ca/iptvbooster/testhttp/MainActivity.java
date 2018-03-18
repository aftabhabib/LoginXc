package ca.iptvbooster.testhttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {

    EditText etResponse;
    TextView tvIsConnected;
    EditText uname, password;
    Button submit;

    public static final String USER = "User";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etResponse = (EditText) findViewById(R.id.etResponse);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);

        uname = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPass);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                new HttpAsyncTask().execute("http://stb.spades-tv.xyz:25461/player_api.php?username=" + uname.getText() + "&password=" + password.getText());

            }
        });
    }
    public static String GET(String url) {

        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            inputStream = httpResponse.getEntity().getContent();
            ////Checks if URL is empty
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);

            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        //check status print all the json result
        if (result != null) {
            System.out.println(" You are In! = " + result);

        } else { System.out.println(" 404 "); }
        return result;
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            ///check url is empty
            if (result.equals ("")){
                Toast.makeText(getBaseContext(), "Login not correct!", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(getBaseContext(), "Credentials are Good!", Toast.LENGTH_LONG).show();

                try {
                    //gets Json object
                    JSONObject json = new JSONObject(result);
                    json = json.getJSONObject("user_info");

                    String User = json.getString("username");
                    String Pass = json.getString("password");
                    Integer Auth = json.getInt("auth");

                    System.out.println(" Username = " + User);
                    System.out.println(" Password = " + Pass);
                    System.out.println(" Password = " + Auth);

                   ///Object in Json String
                    if (Auth == 1 ) {


                        sendmessage(User,Pass);

                        System.out.println(" Pass! "+ Pass);


                } else {
                        //Sends to No Access
                        Intent login = new Intent(getApplicationContext(), MainActivity.class);
                        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(login);
                        finish();
                        Toast.makeText(getBaseContext(), "Credentials are Wrong!", Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
   }
//Sends to Welcome Screen User And Pass
    public void sendmessage (String  User,String Pass)
    {

    Intent login = new Intent(getApplicationContext(), Welcome.class);
    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


    login.putExtra("Username", User);
    login.putExtra("Password", Pass);

    startActivity(login);

    System.out.println("Sending"+User);


    }
    private <T> Iterable<T> iterate(final Iterator<T> i){
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return i;
            }
        };
    }
}