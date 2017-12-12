package sanskar.dev.com.tribaldashboardcg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import sanskar.dev.com.tribaldashboardcg.Models.UserModel;
import sanskar.dev.com.tribaldashboardcg.Utility.URLs;
import sanskar.dev.com.tribaldashboardcg.Utility.Utils;
import sanskar.dev.com.tribaldashboardcg.Utility.VolleySingleton;

public class LoginActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Utils.isLoggedIn()) {
              Toast.makeText(getApplicationContext(),"USER LOGGED IN",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this, LandingPageActivity.class));
        }

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);


        //if user presses on login
        //calling the method login
        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });


    }

    private void userLogin() {
        //first getting the values
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Please enter your username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (obj.getString("status").equals("success")) {
                                Toast.makeText(getApplicationContext(), "LOGIN SUCCESS", Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                //JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
                                UserModel user = new UserModel(
                                        obj.getString("phone"),
                                        obj.getString("password"),
                                        obj.getString("department"),
                                        obj.getString("role"),
                                        obj.getString("name")
                                );

                                //storing the user in shared preferences
                                if(Utils.loginUser(user)){

                                    finish();
                                    startActivity(new Intent(getApplicationContext(), LandingPageActivity.class));
                                }
                               // SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                //starting the profile activity

                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("Status Fail for login"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "ERROR= "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("phone", username);
                params.put("password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}