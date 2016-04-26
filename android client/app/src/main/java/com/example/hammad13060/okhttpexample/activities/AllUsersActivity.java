package com.example.hammad13060.okhttpexample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hammad13060.okhttpexample.Constants;
import com.example.hammad13060.okhttpexample.GlobalApplication;
import com.example.hammad13060.okhttpexample.httpHelper.OkHTTPHelper;
import com.example.hammad13060.okhttpexample.R;
import com.example.hammad13060.okhttpexample.httpHelper.OkHTTPHelperResponseInterface;

import org.json.JSONArray;
import org.json.JSONObject;

public class AllUsersActivity extends AppCompatActivity implements OkHTTPHelperResponseInterface {

    private static final String TAG = AllUsersActivity.class.getSimpleName();

    private GlobalApplication globalApplication = null;
    private TextView allUsersTextView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        globalApplication = (GlobalApplication)getApplicationContext();
        allUsersTextView = (TextView) findViewById(R.id.all_users_text_view);

        //fetching contacts
        globalApplication
                .getOkHTTPHelper()
                .getAllObjects(
                        Constants.SERVER_URL,
                        this
                );
    }

    @Override
    public void getAllObjectsResponse(int status, JSONArray response) {
        final int STATUS = status;
        final JSONArray RESPONSE = response;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (STATUS == OkHTTPHelper.STATUS_SUCCESS) {
                    allUsersTextView.setText(RESPONSE.toString());
                } else if (STATUS == OkHTTPHelper.STATUS_FAILED) {
                    allUsersTextView.setText("could not fetch proper response");
                }
            }
        });

    }

    @Override
    public void getObjectResponse(int status, JSONObject response) {
    }
}
