package com.shabsudemy.connect3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //    yellow=white red=green
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    String resultMessage = "Already selected!!";
    boolean isGameActive=true;

    LinearLayout ln;
    TextView textViewPrompt;
    ImageView counter;
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ln = findViewById(R.id.gameEndPromptLayout);
        textViewPrompt = findViewById(R.id.gameEndPromptLayoutText);
        gridLayout = findViewById(R.id.mainGridLayout);
    }

    public void dropIn(View view) {
        counter = (ImageView) view;

        int tappedIndex = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedIndex] == 2 && isGameActive) {

            counter.setTranslationY(-1000f);

            gameState[tappedIndex] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.ic_launcher_foreground);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.ic_launcher_background);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(500);
            for (int[] winningPosition : winningPositions) {
                ln = findViewById(R.id.gameEndPromptLayout);
                textViewPrompt = findViewById(R.id.gameEndPromptLayoutText);

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]]
                        == gameState[winningPosition[2]] && gameState[winningPosition[1]] != 2) {
                    String player="";
                    if(gameState[winningPosition[0]]==0){
                        player="white android";
                    }else{
                        player="green grid";
                    }

                    textViewPrompt.setText(player + " WON!!!");
                    isGameActive=false;
                    resultMessage = "Game Over";
                    ln.setVisibility(View.VISIBLE);
                    ln.animate().alpha(1).setDuration(800);
                }else{
                    boolean gameIsOver = true;
                    for(int i:gameState){
                        if(i==2){
                            gameIsOver=false;
                        }
                    }
                    if(gameIsOver){
                        textViewPrompt.setText(" Draw game");
                        resultMessage = "Game Over";
                        ln.setVisibility(View.VISIBLE);
                        ln.animate().alpha(1).setDuration(800);
                    }
                }
            }

        } else {
            Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT).show();
        }


        System.out.println(Arrays.toString(gameState));

    }

    public void playAgain(View view) {
        textViewPrompt = findViewById(R.id.gameEndPromptLayoutText);
        Arrays.fill(gameState, 2);
        resultMessage = "Already selected!!";
        ln = findViewById(R.id.gameEndPromptLayout);
        ln.setVisibility(View.INVISIBLE);

        gridLayout = findViewById(R.id.mainGridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setBackgroundColor(Color.parseColor("#D5DEE6"));
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        isGameActive=true;
    }
}