package com.example.hammad13060.okhttpexample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hammad13060.okhttpexample.Constants;
import com.example.hammad13060.okhttpexample.GlobalApplication;
import com.example.hammad13060.okhttpexample.httpHelper.OkHTTPHelper;
import com.example.hammad13060.okhttpexample.R;
import com.example.hammad13060.okhttpexample.httpHelper.OkHTTPHelperResponseInterface;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserActivity extends AppCompatActivity implements OkHTTPHelperResponseInterface {

    private static final String TAG = UserActivity.class.getSimpleName();

    private GlobalApplication globalApplication = null;
    private TextView userTextView = null;
    private EditText idEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        globalApplication = (GlobalApplication)getApplicationContext();
        userTextView = (TextView)findViewById(R.id.user_text_view);
        idEditText = (EditText)findViewById(R.id.id_edit_text);
    }

    public void onGetUserClick(View view) {
        try {
            int id = Integer.parseInt(idEditText.getText().toString());
            globalApplication.getOkHTTPHelper().getObject(Constants.SERVER_URL, id, this);
        } catch (NumberFormatException e) {
            Log.d(TAG, "invalid id entered");
            Toast.makeText(getApplicationContext(), "enter an integer value as id", Toast.LENGTH_SHORT);
            e.printStackTrace();
        }
    }

    @Override
    public void getAllObjectsResponse(int status, JSONArray response) {}

    @Override
    public void getObjectResponse(int status, JSONObject response) {
        final int STATUS = status;
        final JSONObject RESPONSE = response;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (STATUS == OkHTTPHelper.STATUS_SUCCESS) {
                    userTextView.setText(RESPONSE.toString());
                } else if (STATUS == OkHTTPHelper.STATUS_FAILED) {
                    userTextView.setText("could not fetch proper response or server was down or no user with this id");
                }
            }
        });
    }

    @Override
    public void createObjectResponse(int status, JSONObject response) {

    }

    @Override
    public void deleteObjectResponse(int status) {

    }
}
