package sanskar.dev.com.tribaldashboardcg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import sanskar.dev.com.tribaldashboardcg.Models.Item;
import sanskar.dev.com.tribaldashboardcg.Utility.URLs;
import sanskar.dev.com.tribaldashboardcg.Utility.Utils;
import sanskar.dev.com.tribaldashboardcg.Utility.VolleySingleton;

public class AddLogActivity extends AppCompatActivity {

    TextView title_text;
    EditText log_title, detailed_comments;
    Button submit_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log);
        setTitle("New Project Log");

        title_text = findViewById(R.id.title_text);
        log_title = findViewById(R.id.log_title);
        detailed_comments = findViewById(R.id.detailed_comments);

        submit_Btn = findViewById(R.id.submit_btn);


        final String projectName = getIntent().getStringExtra("project_name");
        final String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        title_text.setText("Log for : " + projectName);

        submit_Btn.setOnClickListener(new View.OnClickListener() {
            String URL = URLs.URL_ADD_LOGS;
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST,URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();

                                if (response == null) {
                                    Toast.makeText(getApplicationContext(), "LOG ADDED. STATUS : -n.", Toast.LENGTH_LONG).show();
                                    return;
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error in getting json
                        Log.d("ADD_LOGS_ACTIVITY", "Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("project_name",projectName);
                        params.put("department",Utils.getData(Utils.KEY_DEPARTMENT,"TribalDEPT"));
                        params.put("comment",detailed_comments.getText().toString()+"..");
                        params.put("location","RPR");
                        params.put("phone",Utils.getData(Utils.KEY_USERID,"9876543211"));
                        params.put("timestamp",date);
                        return params;
                    }
                };

                VolleySingleton.getInstance(AddLogActivity.this).addToRequestQueue(request);
            }
        });


    }
}
