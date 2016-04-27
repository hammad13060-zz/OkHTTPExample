package com.example.hammad13060.okhttpexample.httpHelper;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by hammad13060 on 26/04/16.
 */
public class OkHTTPHelper {

    private static final String TAG = OkHTTPHelper.class.getSimpleName();
    //constants
    public static final int STATUS_FAILED = 0;
    public static final int STATUS_SUCCESS = 1;

    //media type for json request response
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    //singleton instance of http client
    private static OkHttpClient mOkHttpClient = null;

    // singleton instance of helper class
    // instance created at runtime
    //thread safe by default
    private static OkHTTPHelper mOkHTTPHelper = new OkHTTPHelper();

    //default constructor made private
    private OkHTTPHelper() {
        Log.d(TAG, "creating singleton instance");
        mOkHttpClient = new OkHttpClient();
    }

    //returning singleton instance
    public static OkHTTPHelper getInstance() {
        return mOkHTTPHelper;
    }

    public void getAllObjects(String url, final OkHTTPHelperResponseInterface okHTTPHelperResponseInterface) {
        Log.d(TAG, "performing get at: \'" + url + "\'");
        final String SERVER_URL = url;
        Request request = new Request.Builder()
                .url(url)
                .build();

        mOkHttpClient.newCall(request).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "get failed at: \'" + SERVER_URL + "\'");
                        if (okHTTPHelperResponseInterface != null) {
                            okHTTPHelperResponseInterface.getAllObjectsResponse(STATUS_FAILED, new JSONArray());
                        }
                        call.cancel();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        if (okHTTPHelperResponseInterface != null) {
                            try {
                                JSONArray responseArray = new JSONArray(
                                        new String(response.body().bytes())
                                );
                                okHTTPHelperResponseInterface.getAllObjectsResponse(STATUS_SUCCESS, responseArray);
                                Log.d(TAG, "get succeeded at: \'" + SERVER_URL + "\'");
                            } catch (JSONException e) {
                                okHTTPHelperResponseInterface.getAllObjectsResponse(STATUS_FAILED, new JSONArray());
                                e.printStackTrace();
                                Log.d(TAG, "get succeeded at: \'" + SERVER_URL + "\'\n" +
                                    " but response not suitable"
                                );
                            }
                        }
                        call.cancel();
                    }
                }
        );
    }

    public void getObject(String url, int id, final OkHTTPHelperResponseInterface okHTTPHelperResponseInterface) {
        url = url + id;
        Log.d(TAG, "performing get at: \'" + url + "\'");
        final String SERVER_URL = url;
        Request request = new Request.Builder()
                .url(url)
                .build();

        mOkHttpClient.newCall(request).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "get failed at: \'" + SERVER_URL + "\'");
                        if (okHTTPHelperResponseInterface != null) {
                            okHTTPHelperResponseInterface.getObjectResponse(STATUS_FAILED, new JSONObject());
                        }
                        call.cancel();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (okHTTPHelperResponseInterface != null) {
                            try {
                                JSONObject responseObject = new JSONObject(
                                        new String(response.body().bytes())
                                );
                                okHTTPHelperResponseInterface.getObjectResponse(STATUS_SUCCESS, responseObject);
                                Log.d(TAG, "get succeeded at: \'" + SERVER_URL + "\'");
                            } catch (JSONException e) {
                                okHTTPHelperResponseInterface.getObjectResponse(STATUS_FAILED, new JSONObject());
                                e.printStackTrace();
                                Log.d(TAG, "get succeeded at: \'" + SERVER_URL + "\'\n" +
                                                " but response not in suitable format"
                                );
                            }
                        }
                        call.cancel();
                    }
                }
        );
    }

    public void createObject(String url, String name, final OkHTTPHelperResponseInterface okHTTPHelperResponseInterface) {
        Log.d(TAG, "creating a new resource at: " + url + " with name: " + name);
        JSONObject requestObject = new JSONObject();

        try {
            requestObject.put("name", name);
            RequestBody body = RequestBody.create(JSON, requestObject.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, "connection with server failed");
                    if (okHTTPHelperResponseInterface != null) {
                        okHTTPHelperResponseInterface.createObjectResponse(STATUS_FAILED, new JSONObject());
                    }
                    call.cancel();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d(TAG, "response from the server obtained");
                    if (okHTTPHelperResponseInterface != null) {
                        try {
                            JSONObject jsonResponse = new JSONObject(new String(response.body().bytes()));
                            okHTTPHelperResponseInterface.createObjectResponse(STATUS_SUCCESS, jsonResponse);
                            Log.d(TAG, "response in proper format");
                        } catch (JSONException e) {
                            okHTTPHelperResponseInterface.createObjectResponse(STATUS_FAILED, new JSONObject());
                            Log.d(TAG, "response not in proper format");
                            e.printStackTrace();
                        }
                    }
                    call.cancel();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void deleteObject(String url, int id, final OkHTTPHelperResponseInterface okHTTPHelperResponseInterface) {
        url = url + id;
        Log.d(TAG, "deleting resource at: " + url);

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "connection with server failed");
                if (okHTTPHelperResponseInterface != null) {
                    okHTTPHelperResponseInterface.deleteObjectResponse(STATUS_FAILED);
                }
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    Log.d(TAG, "DELETE action successful");
                    okHTTPHelperResponseInterface.deleteObjectResponse(STATUS_SUCCESS);
                } else {
                    Log.d(TAG, "DELETE action failed");
                    okHTTPHelperResponseInterface.deleteObjectResponse(STATUS_FAILED);
                }
                call.cancel();
            }
        });
    }
}
