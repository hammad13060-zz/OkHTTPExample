package com.example.hammad13060.okhttpexample.httpHelper;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by hammad13060 on 27/04/16.
 */
public interface OkHTTPHelperResponseInterface {
    public void getAllObjectsResponse(int status, JSONArray response);
    public void getObjectResponse(int status, JSONObject response);
    public void createObjectResponse(int status, JSONObject response);
    public void deleteObjectResponse(int status);
}
