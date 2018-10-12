package batchtwo.mostain.lict.com.kinectgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public int activePlayer = 0;

    //2 means untapped
    public int[] gameState = {2,2,2,2,2,2,2,2,2};

    //Game is active or not
    public  boolean gameIsActive = true;

    //two dimensional arrays for winning positions
    public int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void dropIn(View v) {

        ImageView counter = (ImageView) v;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
            for(int[] winningPositions :winningPositions){
                if(gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                        gameState[winningPositions[1]] == gameState[winningPositions[2]] &&
                        gameState[winningPositions[0]] != 2){
                    //Somebody won
                    gameIsActive = false;
                    String winner = "Red";
                    if(gameState[winningPositions[0]] == 0){
                        winner = "Yellow";
                    }
                    TextView textWinner = findViewById(R.id.textResult);
                    textWinner.setText(winner + " has won");

                    //method to set animation to the layout
                    customAnimation();

                }else {
                    boolean gameIsOver = true;
                    for(int counterState : gameState) {
                        if (counterState == 2) {
                            gameIsOver = false;
                        }
                    }
                    if(gameIsOver){

                        TextView textWinner = findViewById(R.id.textResult);
                        textWinner.setText("It's a draw");

                        //method to set animation to the layout
                        customAnimation();
                    }
                }
            }
        }
    }
    public void playAgain(View v){
        gameIsActive = true;
        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for(int i = 0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        //to reset all the steps of the game
        GridLayout gridLayout = findViewById(R.id.gridlayout);
        for(int j=0; j<gridLayout.getChildCount(); j++){
            ((ImageView) gridLayout.getChildAt(j)).setImageResource(0);
        }
    }
    public void customAnimation(){
        LinearLayout linearLayout = findViewById(R.id.playAgainLayout);
        linearLayout.setTranslationX(-1000f);
        linearLayout.setTranslationY(1000f);
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout.animate().rotation(360).translationYBy(-1000f).translationXBy(1000f).setDuration(1500);
    }
}
