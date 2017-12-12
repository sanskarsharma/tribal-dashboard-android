package sanskar.dev.com.tribaldashboardcg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import sanskar.dev.com.tribaldashboardcg.Utility.Utils;

public class LandingPageActivity extends AppCompatActivity {

    ImageButton projects_Btn, schemes_Btn ,settings_BTN;

    TextView tv_welcome, tv_userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        projects_Btn = findViewById(R.id.projects_imageButton);
        schemes_Btn = findViewById(R.id.schemes_imageButton);
        settings_BTN = findViewById(R.id.settings);
        tv_welcome = findViewById(R.id.welcome_tv);
        tv_userid = findViewById(R.id.userid_tv);

        tv_welcome.setText("Welcome, Mr."+ Utils.getData(Utils.KEY_NAME,"Hari"));
        tv_userid.setText("User ID : "+ Utils.getData(Utils.KEY_USERID,"1212345612"));

        projects_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LandingPageActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        schemes_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(LandingPageActivity.this,SchemesActivity.class);
//                startActivity(i);
            }
        });
        settings_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.logoutUser();
                finish();
            }
        });




    }
}
