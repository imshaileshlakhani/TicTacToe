package com.example.tictectgame;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.tictectgame.R.color.colorforO;
import static com.example.tictectgame.R.color.colorforX;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    TextView player1,player2;
    String pl1N = "",pl2N = "";
    int player_1 = 0,player_2 = 1,pl1 = 0,pl2 = 0;
    int activeplayer = player_1;
    int[] filledpos = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    boolean active = true;
    private MediaPlayer mp,mp1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Tic Tec Toe");

        pl1N = getIntent().getStringExtra("Py1");
        pl2N = getIntent().getStringExtra("Py2");
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        btn0 = findViewById(R.id.button_00);
        btn1 = findViewById(R.id.button_01);
        btn2 = findViewById(R.id.button_02);
        btn3 = findViewById(R.id.button_10);
        btn4 = findViewById(R.id.button_11);
        btn5 = findViewById(R.id.button_12);
        btn6 = findViewById(R.id.button_20);
        btn7 = findViewById(R.id.button_21);
        btn8 = findViewById(R.id.button_22);

        player1.setText(pl1N+" (O) : 0");
        player2.setText(pl2N+" (X) : 0");

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
    }
   // @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        if(!active)
            return;

        mp = MediaPlayer.create(this,R.raw.clickbtn);

        Button clickedbtn = findViewById(view.getId());
        int clickedtag = Integer.parseInt(view.getTag().toString());

        if(filledpos[clickedtag] != -1){
            return;
        }
        filledpos[clickedtag] = activeplayer;
        mp.start();
        if(activeplayer == player_1){
            clickedbtn.setText("O");
            clickedbtn.setBackgroundColor(Color.parseColor("#E81AD3"));
            player1.setBackgroundColor(Color.parseColor("#E81AD3"));
            player2.setBackgroundColor(Color.parseColor("#000000"));
            activeplayer = player_2;
        }else{
            clickedbtn.setText("X");
            clickedbtn.setBackgroundColor(Color.parseColor("#60DA62"));
            player2.setBackgroundColor(Color.parseColor("#60DA62"));
            player1.setBackgroundColor(Color.parseColor("#000000"));
            activeplayer = player_1;
        }
        checkWinner();
    }

    //Check Winner....
    private void checkWinner() {
        int[][] winingpos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for(int i=0;i<8;i++){
            int val0 = winingpos[i][0];
            int val1 = winingpos[i][1];
            int val2 = winingpos[i][2];
            if(filledpos[val0] == filledpos[val1] && filledpos[val1] == filledpos[val2]){
                if(filledpos[val0] != -1){
                    active = false;
                    mp1 = MediaPlayer.create(this,R.raw.winner);
                    mp1.start();
                    if(filledpos[val0] == player_1) {
                        pl1 = pl1 + 1;
                        player1.setText(pl1N+" (O) : "+pl1);
                        dialog(pl1N+" is winner");
                    }else{
                        pl2 = pl2 + 1;
                        player2.setText(pl2N+" (X) : "+pl2);
                        dialog(pl2N+" is winner");
                    }
                }
            }
        }
    }

    //Dialog box for winner....
    private void dialog(String winner) {
       AlertDialog.Builder builder= new AlertDialog.Builder(this)
                .setTitle("Winner")
                .setMessage(winner)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartgame();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
    }

    //Restart game.......
    private void restartgame() {
        filledpos = new int[] {-1,-1,-1,-1,-1,-1,-1,-1,-1};
        activeplayer = player_1;
        active = true;

        player1.setBackgroundColor(Color.parseColor("#000000"));
        player2.setBackgroundColor(Color.parseColor("#000000"));

        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");

        btn0.setBackgroundColor(Color.parseColor("#CFC2C1"));
        btn1.setBackgroundColor(Color.parseColor("#CFC2C1"));
        btn2.setBackgroundColor(Color.parseColor("#CFC2C1"));
        btn3.setBackgroundColor(Color.parseColor("#CFC2C1"));
        btn4.setBackgroundColor(Color.parseColor("#CFC2C1"));
        btn5.setBackgroundColor(Color.parseColor("#CFC2C1"));
        btn6.setBackgroundColor(Color.parseColor("#CFC2C1"));
        btn7.setBackgroundColor(Color.parseColor("#CFC2C1"));
        btn8.setBackgroundColor(Color.parseColor("#CFC2C1"));
    }

    //Option menu.....
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.reset :
                pl2 = 0;
                pl1 = 0;
                player1.setText(pl1N+" (O) : "+pl1);
                player2.setText(pl2N+" (X) : "+pl2);
                break;
            case R.id.resetgame :
                restartgame();
                break;
            case R.id.Change :
                Intent intent = new Intent(MainActivity.this,playername.class);
                startActivity(intent);
                finish();
                break;
            case R.id.about_us :
                Intent intent1 = new Intent(MainActivity.this,aboutus.class);
                startActivity(intent1);
                break;
        }
        return true;
    }
}