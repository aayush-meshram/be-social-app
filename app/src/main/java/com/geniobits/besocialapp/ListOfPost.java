package com.geniobits.besocialapp;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.geniobits.besocialapp.adapter.ExampleAdapter;
import com.geniobits.besocialapp.customVariable.PostInfo;
import com.geniobits.besocialapp.customVariable.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListOfPost extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RequestQueue mQueue;
    private ArrayList<PostInfo> postInfoList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserInfo info = getIntent().getParcelableExtra("userInfo");
        int id = info.getUserID();
        postInfoList = new ArrayList<>();

        mQueue = VolleySingleton.getInstance(this).getRequestQueue();
        getPosts(id);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(postInfoList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    public void getPosts(int id)    {
        String URL = "https://gorest.co.in/public/v2/users/"+id+"/posts";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                int id = object.getInt("id");
                                int user_id = object.getInt("user_id");
                                String title = object.getString("title");
                                String body = object.getString("body");
                                PostInfo info = new PostInfo(id, user_id, title, body);
                                postInfoList.add(info);
                                Log.i(TAG, "onResponse: "+title);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

}
