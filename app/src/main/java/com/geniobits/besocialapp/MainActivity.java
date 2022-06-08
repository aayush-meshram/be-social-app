package com.geniobits.besocialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.geniobits.besocialapp.customVariable.UserInfo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialTextInputPicker;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private MaterialButton loginButton;
    private MaterialButton newUserButton;
    private RequestQueue mQueue;
    protected List<UserInfo> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        editText = findViewById(R.id.editText);
        loginButton = findViewById(R.id.materialButton);
        newUserButton = findViewById(R.id.textButton);

        mQueue = VolleySingleton.getInstance(this).getRequestQueue();

        getListOfUsers();

        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPage();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmailID = editText.getText().toString();
                for (int i = 0; i < usersList.size(); i++)  {
                    if(userEmailID.equals(usersList.get(i).emailID))
                        Snackbar.make(findViewById(R.id.loginPage),
                                "Email logged in!", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void openLoginPage() {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    private void getListOfUsers()   {

        String url = "https://gorest.co.in/public/v2/users";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject array = response.getJSONObject(i);

                                int id = array.getInt("id");
                                String name = array.getString("name");
                                String email = array.getString("email");
                                String gender = array.getString("gender");
                                String status = array.getString("status");

                                usersList.add(new UserInfo(id, name, email, gender, status));
                                if(i == 19)   {
                                    Snackbar.make(findViewById(R.id.loginPage),
                                            "data acquired", Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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