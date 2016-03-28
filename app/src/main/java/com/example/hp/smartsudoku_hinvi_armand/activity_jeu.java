package com.example.hp.smartsudoku_hinvi_armand;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.View.OnTouchListener;
        import android.widget.Button;

public class activity_jeu extends Activity {

    private final int CHOIX_NUM_FENETRE = 1;
    // DÃ©clarer la grille et une intent
    public Grille grille;
    public Intent intent;
    private int x = 0;
    private int y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_jeu);



        // Intent Ã  initialiser ici

        intent = new Intent(activity_jeu.this,activity_choix.class);


        // Associer la grille de l'interface graphique ici

        grille= (Grille) findViewById(R.id.grille1);

        grille.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    x = grille.getXFromMatrix((int) event.getX());
                    y = grille.getYFromMatrix((int) event.getY());
                    if (x < 9 && y < 9 && grille.isNotFix(x, y)) {
                        startActivityForResult(intent, CHOIX_NUM_FENETRE);

                    }
                }
                return true;
            }
        });

        Button valider = (Button) findViewById(R.id.button1);
        valider.setOnClickListener (new OnClickListener()     {
                                        public void onClick(View v) {
                                            boolean resultat = grille.isValid() ;
                                            if(resultat) System.out.println("resulta vrai :::::::::::" );
                                            else System.out.println("resulta Faux :::::::::::" );
                                            grille.activate(true, resultat);



                                        }
                                    }
        );



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_jeu, menu);
        return true;
    }

    @Override
    public void onActivityResult(int request, int result, Intent intent) {
        // vÃ©rifier si la case n'est pas fixe, on lui affecte le numÃ©ro result
        if(request==1) {
            System.out.println("La Numéro inserer est :::::::::::" );
            System.out.println(result );

            if(grille.isNotFix(x ,y))
                grille.set(x,y,result);
            grille.invalidate();

        }



    }


}
