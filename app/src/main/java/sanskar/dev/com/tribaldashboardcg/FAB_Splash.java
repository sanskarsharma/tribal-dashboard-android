package sanskar.dev.com.tribaldashboardcg;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import io.saeid.fabloading.LoadingView;

public class FAB_Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){
            Toast.makeText(getApplicationContext(),"npe",Toast.LENGTH_SHORT).show();
        }
        setContentView(R.layout.activity_fab__splash);



        LoadingView yo = (LoadingView)findViewById(R.id.splash);
        yo.addAnimation(Color.parseColor("#ffd633"),R.drawable.ic_launcher,LoadingView.FROM_BOTTOM);
        yo.addAnimation(Color.parseColor("#ffd633"),R.drawable.ic_launcher,LoadingView.FROM_BOTTOM);
        yo.addAnimation(Color.parseColor("#ff9900"),R.drawable.ic_launcher,LoadingView.FROM_BOTTOM);
        yo.addAnimation(Color.parseColor("#ffffff"),R.drawable.ic_launcher,LoadingView.FROM_BOTTOM);
        yo.addAnimation(Color.parseColor("#00cc00"),R.drawable.ic_launcher,LoadingView.FROM_BOTTOM);
        yo.addAnimation(Color.parseColor("#ffd633"), R.drawable.ic_launcher, LoadingView.FROM_BOTTOM);

        yo.startAnimation();


        Thread soja = new Thread(){
            @Override
            public void run() {

                try{
                    Thread.sleep(3200);
                }catch(InterruptedException e){

                }
                finally {
                    Intent intentObject = new Intent(FAB_Splash.this,LoginActivity.class);
                    startActivity(intentObject);
                    finish();
                }

            }
        };soja.start();


    }
}
