package com.example.tictectgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class playername extends AppCompatActivity {

    EditText player1,player2;
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playername);

        getSupportActionBar().hide();

        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Player1,Player2;
                Player1 = player1.getText().toString();
                Player2 = player2.getText().toString();
                if(Player1.equals("")){
                    player1.requestFocus();
                    player1.setError("Please Enter Name");
                    return;
                }
                if(Player2.equals("")){
                    player2.requestFocus();
                    player2.setError("Please Enter Name");
                    return;
                }
                Intent intent = new Intent(playername.this,MainActivity.class);
                intent.putExtra("Py1",Player1);
                intent.putExtra("Py2",Player2);
                startActivity(intent);
                finish();
            }
        });
    }
}