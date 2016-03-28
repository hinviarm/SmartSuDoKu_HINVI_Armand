package com.example.hp.smartsudoku_hinvi_armand;

        import android.os.Bundle;
        import android.app.Activity;
        import android.content.Intent;
        import android.view.Menu;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;

public class activity_about extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_about);

        final Button Retour = (Button) findViewById(R.id.button1);
        Retour.setOnClickListener (new OnClickListener()     {
                                       public void onClick(View v) {
                                           Intent intent = new Intent(activity_about.this, activity_main.class);
                                           startActivity(intent);
                                       }
                                   }
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_about, menu);
        return true;
    }

}
