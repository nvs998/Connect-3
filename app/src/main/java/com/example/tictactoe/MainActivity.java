package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // x = 0, o = 1, empty = 2;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winningstate = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer = 0;
    boolean gameActive = true,flag = true;
    String winner = "";
    public void put(View view)
    {
        ImageView image = (ImageView) view ;
        int t_counter = Integer.parseInt(image.getTag().toString());
        if(gamestate[t_counter] == 2 && gameActive)
        {
            gamestate[t_counter] = activePlayer;
            image.setTranslationY(-1500);
            if(activePlayer == 0){
            image.setImageResource(R.drawable.corona1);
            activePlayer = 1;
            }
            else{
                image.setImageResource(R.drawable.mask1);
                activePlayer = 0;
            }
            image.animate().rotation(3600).translationYBy(1500).setDuration(1000);
            for(int[] pos : winningstate) {
                if(gamestate[pos[0]] == gamestate[pos[1]] && gamestate[pos[1]] == gamestate[pos[2]] && gamestate[pos[0]] != 2){
                    gameActive = false;
                    flag = false;


                    if(activePlayer == 1)
                    {
                        winner = "More masks required!";
                    }
                    else{
                        winner = "We are safe!";
                    }
                    Button playAgain = (Button)findViewById(R.id.button);
                    TextView txt = (TextView)findViewById(R.id.textView2);
                    txt.setText(winner);
                    playAgain.setVisibility(View.VISIBLE);
                    txt.setVisibility(View.VISIBLE);
                }
            }
            boolean draw = true;
            for(int x: gamestate){
                if(x == 2){
                    draw = false;
                }
            }
            if(draw && flag){
                Button playAgain = (Button)findViewById(R.id.button);
                TextView txt = (TextView)findViewById(R.id.textView2);
                txt.setText("It's a Draw!");
                playAgain.setVisibility(View.VISIBLE);
                txt.setVisibility(View.VISIBLE);
                gameActive = false;
            }
        }
    }

        public void reset(View view)
        {
            Button playAgain = (Button)findViewById(R.id.button);
            TextView txt = (TextView)findViewById(R.id.textView2);
            playAgain.setVisibility(View.INVISIBLE);
            txt.setVisibility(View.INVISIBLE);
            GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++)
        {
            ImageView image1 = (ImageView) gridLayout.getChildAt(i);
            image1.setImageDrawable(null);
        }
        for(int j=0; j<gamestate.length; j++){
            gamestate[j] = 2;
        }
        activePlayer = 0;
        gameActive = true;
        flag = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}