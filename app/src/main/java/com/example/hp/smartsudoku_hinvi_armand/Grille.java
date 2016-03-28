package com.example.hp.smartsudoku_hinvi_armand;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Grille extends View {

    private int screenWidth;
    private int screenHeight;
    private int n;
    private boolean result=false;
    private boolean activated=false;

    private  Paint paint1;
    private Paint paint2;
    private Paint paint3;

    private int[][] matrix = new int[9][9];
    private boolean[][] fixIdx = new boolean[9][9];



    public Grille(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public Grille(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Grille(Context context) {
        super(context);
        init();
    }

    private void init() {
        set("000105000140000670080002400063070010900000003010090520007200080026000035000409000");
        // set("672145398145983672389762451263574819958621743714398526597236184426817935831459267");
        //set("123456789912345678891234567789123456678912345567891234456789123345678912234567891");
        // set("000400870", 0);
        // set("047092050", 1);
        // set("200600030", 2);
        // set("970500203", 3);
        // set("508024706", 4);
        // set("604007085", 5);
        // set("090308007", 6);
        // set("003240160", 7);
        // set("012000090", 8);

        paint1 = new Paint();
        paint1.setAntiAlias(true);
        paint1.setColor(Color.BLACK);
        paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(Color.RED);
        paint3 = new Paint();
        paint3.setAntiAlias(true);
        paint3.setColor(Color.BLACK);// trait "normal"
        //Stroke s = paint3.getStroke();
        //paint3.drawLine(10, 10, 50, 10);


    }


    @Override
    protected void onDraw(Canvas canvas) {

        screenWidth = getWidth();
        screenHeight = getHeight();
        int x = Math.min(screenWidth, screenHeight);
        int r;
        n = (x / 9) - (1 - (x % 2));

        // Dessiner les lignes noires Largeur et hauteur


        for(int i=0;i< screenWidth ;i+=n) {
            canvas.drawLine(i, 0, i, screenHeight, paint1);
        }


        for(int j=0;j< screenHeight ;j+=n) {
            canvas.drawLine(0, j,screenWidth,j, paint1);
        }


        // Dessiner les lignes rouges Plus d'eppaiseur donc 3 ligne concentr�
        r= screenHeight/3;
        for(int j=0;j< screenHeight ;j+=r) {
            canvas.drawLine(0, j-1,screenWidth,j-1, paint2);
            canvas.drawLine(0, j+1,screenWidth,j, paint2);
            canvas.drawLine(0, j+1,screenWidth,j, paint2);
        }

        for(int j=0;j< screenWidth ;j+=r) {
            canvas.drawLine(j-1, 0,j-1,screenHeight, paint2);
            canvas.drawLine(j, 0,j,screenHeight, paint2);
            canvas.drawLine(j+1, 0,j+1,screenHeight, paint2);
        }

        // Le contenu d'une case
        String s;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s = "" + (matrix[j][i] == 0 ? "" : matrix[j][i]);
                if (fixIdx[j][i])
                    canvas.drawText(s, i * n + (n / 2) - (n / 10), j * n
                            + (n / 2) + (n / 10), paint2);
                else
                    canvas.drawText(s, i * n + (n / 2) - (n / 10), j * n
                            + (n / 2) + (n / 10), paint1);
            }
        }

        if(this.activated) {

            if(this.result) {
                int x1=100;
                int y1=10;
                for(int i=0;i<10;i++) {

                    canvas.drawLine(y1, y1, y1, x1, paint3);
                    canvas.drawLine(y1, y1, x1, x1, paint3);
                    canvas.drawLine(x1, x1, y1, x1, paint3);
                    x1=x1+10;
                    y1=y1+10;
                }
                System.out.println("Je dessine les triangles ::::::::::: You WINNNN" );

            }
            else {
                int x1=100;
                int y1=10;
                for(int i=0;i<10;i++) {
                    canvas.drawLine(y1, y1, y1, x1, paint2);
                    canvas.drawLine(y1, y1, x1, x1, paint2);
                    canvas.drawLine(x1, x1, y1, x1, paint2);
                    x1=x1+10;
                    y1=y1+10;
                }


                System.out.println("Je dessine les triangles ::::::::::: Perduuuuu" );

            }
        }

    }

    public int getXFromMatrix(int x) {
        // Renvoie l'indice d'une case �  partir du pixel x de sa position h
        return (x / n);
    }

    public void activate(boolean a,boolean b) {

        this.activated=a;

        this.result=b;
        invalidate();

    }




    public int getYFromMatrix(int y) {
        // Renvoie l'indice d'une case �  partir du pixel y de sa position v
        return (y / n);
    }

    public void set(String s, int i) {
        // Remplir la ième ligne de la matrice matrix avec un vecteur String s
        int v;
        for (int j = 0; j < 9; j++) {
            v = s.charAt(j) - '0';
            matrix[i][j] = v;
            if (v == 0)
                fixIdx[i][j] = false;
            else
                fixIdx[i][j] = true;
        }
    }

    public void set(String s) {
        // Remplir la matrice matrix �  partir d'un vecteur String s
        for (int i = 0; i < 9; i++) {
            set(s.substring(i * 9, i * 9 + 9), i);
        }
    }

    public void set(int x, int y, int v) {
        matrix[y][x] = v;
    }

    public boolean isNotFix(int x, int y) {
        // Renvoie si la case (y,x) n'est pas fixe
        if(matrix[y][x]==0)
            return true;
        return false;

    }

    public boolean isValid() {
        // 1. Vérifier l'existence de chaque numéro (de 1 �  9) dans chaque
        // ligne et chaque colonne


        boolean[] rl = { true, true, true, true, true, true, true, true, true };
        boolean[] rc = { true, true, true, true, true, true, true, true, true };
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (matrix[i][j] == 0)
                    return false;

                if (rl[j] && rc[j])
                    rl[j] = rc[j] = false;
                else
                    return false;
            }
            for (int j = 0; j < 9; j++) {
                rl[matrix[i][j] - 1] = true;
                rc[matrix[i][j] - 1] = true;
            }
        }
        // ------
        // 2. Vérifier l'existence de chaque numéro (de 1 �  9) dans chacun
        // des 9 carrés
        boolean[] r = { true, true, true, true, true, true, true, true, true };
        int w;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                w = 0;
                for (int k = i * 3; k < i * 3 + 3; k++) {
                    for (int l = j * 3; l < j * 3 + 3; l++) {
                        if (matrix[k][l] == 0)

                            return false;
                        if (r[w])
                            r[w++] = false;
                        else

                            return false;
                    }
                }
                for (int k = i * 3; k < i * 3 + 3; k++) {
                    for (int l = j * 3; l < j * 3 + 3; l++) {
                        r[matrix[k][l] - 1] = true;
                    }
                }
            }
        }
        // ------
        // Gagné



        return true;
    }
}
