package ca.iptvbooster.testhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by michelarcato on 2018-03-16.
 */

public class ViewInfos extends AppCompatActivity {

    String json_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        json_string = getIntent().getExtras().getString("json_data");
    }
}