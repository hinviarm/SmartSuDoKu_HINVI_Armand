package com.example.hp.smartsudoku_hinvi_armand;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class activity_main extends Activity {

    public Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_main);

        final Button APropos = (Button) findViewById(R.id.button2);
        APropos.setOnClickListener (new OnClickListener()     {
                                        public void onClick(View v) {
                                            intent = new Intent(activity_main.this, activity_about.class);
                                            startActivity(intent);
                                        }
                                    }
        );

        final Button j = (Button) findViewById(R.id.button1);
        j.setOnClickListener (new OnClickListener()     {
                                  public void onClick(View v) {
                                      intent = new Intent(activity_main.this, activity_jeu.class);
                                      startActivity(intent);
                                  }
                              }
        );



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
