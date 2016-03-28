package com.example.hp.smartsudoku_hinvi_armand;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class activity_choix extends Activity implements OnItemClickListener {


    ListView listeView = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_choix);
        String [] liste = {"","1","2","3","4","5","6","7","8","9"};
        // Complétez

        listeView = (ListView) findViewById(R.id.listView1);

        // Create an empty adapter we will use to display the loaded data.
        // We pass null for the cursor, then update it in onLoadFinished()
        listeView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, liste));

        listeView.setOnItemClickListener(this) ;

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_choix, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {

        // Reccuper� le numero choisie dans choixactivit� et l'envoyer � JeuActivit�

        Intent intent = new Intent(activity_choix.this,activity_jeu.class);
        // position = listeView.getCheckedItemPosition();
        // intent.putExtra("position",position);
        setResult(position,intent);
        finish();
        // startActivity(intent);
        // startActivityForResult(intent, 1);


    }

}
