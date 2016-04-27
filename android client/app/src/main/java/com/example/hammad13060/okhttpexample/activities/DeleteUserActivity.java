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
import com.example.hammad13060.okhttpexample.R;
import com.example.hammad13060.okhttpexample.httpHelper.OkHTTPHelper;
import com.example.hammad13060.okhttpexample.httpHelper.OkHTTPHelperResponseInterface;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class DeleteUserActivity extends AppCompatActivity implements OkHTTPHelperResponseInterface {

    private static final String TAG = DeleteUserActivity.class.getSimpleName();

    private GlobalApplication globalApplication = null;

    private EditText idEditText = null;
    private TextView responseTextView = null;
    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        idEditText = (EditText)findViewById(R.id.id_edit_text);
        responseTextView = (TextView)findViewById(R.id.response_text_view);

        globalApplication = (GlobalApplication)getApplicationContext();

    }

    public void onDeleteUserClick(View view) {
        try {
            id = Integer.parseInt(idEditText.getText().toString());
            globalApplication.getOkHTTPHelper().deleteObject(Constants.SERVER_URL, id, this);
        } catch (NumberFormatException e) {
            Log.d(TAG, "invalid id entered");
            Toast.makeText(getApplicationContext(), "enter an non negative integer value as id ", Toast.LENGTH_SHORT);
            e.printStackTrace();
        }
    }

    @Override
    public void getAllObjectsResponse(int status, JSONArray response) {

    }

    @Override
    public void getObjectResponse(int status, JSONObject response) {

    }

    @Override
    public void createObjectResponse(int status, JSONObject response) {

    }

    @Override
    public void deleteObjectResponse(final int status) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (status == OkHTTPHelper.STATUS_SUCCESS) {
                    responseTextView.setText("DELETE on /users/" + id + " succeeded");
                } else {
                    responseTextView.setText("DELETE on /users/" + id + " failed");
                }
            }
        });
    }
}
