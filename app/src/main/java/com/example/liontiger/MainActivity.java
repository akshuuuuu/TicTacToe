package com.example.liontiger;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    enum Player{

        ONE,TWO,No
    }

    Player currentPlayer = Player.ONE;

    Player[] playerChoices = new Player[9];

    int[][] winnerRowColumn= {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    private  boolean gameOver =false;
    private Button btn;
    private GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerChoices[0]=Player.No;
        playerChoices[1]=Player.No;
        playerChoices[2]=Player.No;
        playerChoices[3]=Player.No;
        playerChoices[4]=Player.No;
        playerChoices[5]=Player.No;
        playerChoices[6]=Player.No;
        playerChoices[7]=Player.No;
        playerChoices[8]=Player.No;

        btn = findViewById(R.id.reset);
        gridLayout = findViewById(R.id.gridLayout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();

            }
        });




    }

    public void imageViewisTapped(View imageView)
    {
        ImageView tappedImageView = (ImageView) imageView;
        int tappedImageTag = Integer.parseInt(tappedImageView.getTag().toString());

        if(playerChoices[tappedImageTag]== Player.No && gameOver == false) {
            tappedImageView.setTranslationX(-2000);
            playerChoices[tappedImageTag] = currentPlayer;



            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;

            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }
            tappedImageView.animate().translationXBy(2000).alpha(1).
                    rotation(3600).setDuration(1000);

            //Toast.makeText(this, tappedImageView.getTag().toString(),Toast.LENGTH_SHORT ).show();

            for (int[] winnerColumns : winnerRowColumn) {
                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]]
                        && playerChoices[winnerColumns[1]] ==
                        playerChoices[winnerColumns[2]] && playerChoices
                        [winnerColumns[0]] != Player.No) {
                    btn.setVisibility(View.VISIBLE);
                    gameOver = true;
                    String winnerOfGame = "";

                    if (currentPlayer == Player.ONE) {
                        winnerOfGame = "Player two";

                    } else if (currentPlayer == Player.TWO) {
                        winnerOfGame = "Player one";
                    }
                    Toast.makeText(this, winnerOfGame + "wins", Toast.LENGTH_SHORT).show();
                }
                else{
                    btn.setVisibility(View.VISIBLE);
                }


            }
        }
    }
    // Reset game function
    private void resetGame()
    {
        for (int index =0; index< gridLayout.getChildCount();index++)
        {
            ImageView imageView =(ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }
        currentPlayer = Player.ONE;
        playerChoices[0]=Player.No;
        playerChoices[1]=Player.No;
        playerChoices[2]=Player.No;
        playerChoices[3]=Player.No;
        playerChoices[4]=Player.No;
        playerChoices[5]=Player.No;
        playerChoices[6]=Player.No;
        playerChoices[7]=Player.No;
        playerChoices[8]=Player.No;
        gameOver = false;

        btn.setVisibility(View.INVISIBLE);


    }
}
