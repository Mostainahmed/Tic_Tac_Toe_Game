package batchtwo.mostain.lict.com.kinectgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void dropIn(View v){
        ImageView counter = (ImageView) v;
        counter.setTranslationY(-1000f);
        counter.setImageResource(R.drawable.yellow);
        counter.animate().translationYBy(1000f).setDuration(300);
    }
}
