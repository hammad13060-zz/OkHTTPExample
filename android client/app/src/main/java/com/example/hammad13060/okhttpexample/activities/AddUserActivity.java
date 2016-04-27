package com.example.hammad13060.okhttpexample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hammad13060.okhttpexample.Constants;
import com.example.hammad13060.okhttpexample.GlobalApplication;
import com.example.hammad13060.okhttpexample.R;
import com.example.hammad13060.okhttpexample.httpHelper.OkHTTPHelper;
import com.example.hammad13060.okhttpexample.httpHelper.OkHTTPHelperResponseInterface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.StringTokenizer;

public class AddUserActivity extends AppCompatActivity implements OkHTTPHelperResponseInterface {

    GlobalApplication globalApplication = null;

    EditText nameEditText = null;
    TextView responseTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        nameEditText = (EditText)findViewById(R.id.name_edit_text);
        responseTextView = (TextView)findViewById(R.id.response_text_view);

        globalApplication = (GlobalApplication)getApplicationContext();
    }

    public void onSubmitDataClick(View view) {
        String name = nameEditText.getText().toString();
        globalApplication.getOkHTTPHelper().createObject(Constants.SERVER_URL, name, this);
    }

    @Override
    public void getAllObjectsResponse(int status, JSONArray response) {

    }

    @Override
    public void getObjectResponse(int status, JSONObject response) {

    }

    @Override
    public void createObjectResponse(int status, JSONObject response) {
        final int STATUS = status;
        final JSONObject RESPONSE = response;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (STATUS == OkHTTPHelper.STATUS_SUCCESS) {
                    responseTextView.setText(RESPONSE.toString());
                } else {
                    responseTextView.setText("not able to create resource on server");
                }
            }
        });
    }
}
