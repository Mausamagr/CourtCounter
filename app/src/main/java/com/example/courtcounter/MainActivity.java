package com.example.courtcounter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ScoreViewModel mScoreViewModel;
    TextView teamAScore;
    Button teamAStart;

    private Observer<Integer> scoreObserver = new Observer<Integer>() {
        @Override
        public void onChanged(@Nullable Integer integer) {
            displayForTeamA(integer);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teamAScore = findViewById(R.id.teamAScore);
        teamAStart = findViewById(R.id.teamAStart);

        mScoreViewModel = ViewModelProviders.of(this).get(ScoreViewModel.class);

        teamAStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ExampleAsyncTask().execute(10);
            }
        });

        SubscribeScoreForTeamA();
    }



    private void SubscribeScoreForTeamA() {
        mScoreViewModel.getScoreTeamA().observe(this, scoreObserver);
    }

    private void displayForTeamA(int x) {

        teamAScore.setText(String.valueOf(x));

    }

    private class ExampleAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... integers) {
            for (int i = 0 ;i <= integers[0]; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                  e.printStackTrace();
                }
                publishProgress(i);

            }
            return "finished";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mScoreViewModel.setScoreTeamA(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, "Done for Team A", Toast.LENGTH_SHORT).show();
        }
    }
}
