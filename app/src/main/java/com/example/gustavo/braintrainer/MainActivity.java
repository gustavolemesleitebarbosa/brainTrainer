package com.example.gustavo.braintrainer;

import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button op1;
    Button op2;
    Button op3;
    Button op4;
    Button again;
    Button go;
    TextView timeLeft;
    TextView mathCount;
    TextView userMessage;
    TextView currentScore;
    int firstTerm;
    int secondTerm;
    int rightButton;
    int points;
    int rounds;
    int []numbers={0,0,0,0};
    Random random;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;




public void round(){

    rounds++;
    Integer array[];
    random = new Random();
    firstTerm= random.nextInt(20)+1;
    secondTerm= random.nextInt(20)+1;
    mathCount.setText(Integer.toString(firstTerm)+"+ "+Integer.toString(secondTerm));

    Set<Integer> set = new LinkedHashSet<Integer>();

    while(set.size() != 3) {
        int possible;
        possible=random.nextInt(40) + 1;
        if(possible!=(firstTerm+secondTerm)) {
            set.add(possible);
        }
    }

    array=(Integer[])set.toArray(new Integer[0]);

    rightButton= random.nextInt(4)+1;
    switch (rightButton) {
        case 1:
            op1.setText(Integer.toString(firstTerm+secondTerm));
            op1.setTag("write");
            op2.setText(array[0].toString());
            op2.setTag("wrong");
            op3.setText(array[1].toString());
            op3.setTag("wrong");
            op4.setText(array[2].toString());
            op4.setTag("wrong");
            break;
        case 2:
            op2.setText(Integer.toString(firstTerm+secondTerm));
            op2.setTag("write");
            op1.setText(array[0].toString());
            op1.setTag("wrong");
            op3.setText(array[1].toString());
            op3.setTag("wrong");
            op4.setText(array[2].toString());
            op4.setTag("wrong");
            break;
        case 3:
            op3.setText(Integer.toString(firstTerm+secondTerm));
            op3.setTag("write");
            op1.setText(array[0].toString());
            op1.setTag("wrong");
            op2.setText(array[1].toString());
            op2.setTag("wrong");
            op4.setText(array[2].toString());
            op4.setTag("wrong");
            break;


        case 4:

            op4.setText(Integer.toString(firstTerm+secondTerm));
            op4.setTag("write");
            op1.setText(array[0].toString());
            op1.setTag("wrong");
            op2.setText(array[1].toString());
            op2.setTag("wrong");
            op3.setText(array[2].toString());
            op3.setTag("wrong");
            break;
    }


}



    public void testWrite(View view){
        userMessage.setVisibility(View.VISIBLE);

        String tag =(String)view.getTag();
        if(tag=="write") {
            points++;
           userMessage.setText("right!");
        }
        else{
            userMessage.setText("Wrong!");
        }

        currentScore.setText(Integer.toString(points)+"/"+Integer.toString(rounds));
    }


    public void startBrain(View view) {

        points=0;
        rounds=0;
        currentScore.setText("0/0");
        op1.setEnabled(true);
        op2.setEnabled(true);
        op3.setEnabled(true);
        op4.setEnabled(true);

        view.setVisibility(View.INVISIBLE);
        userMessage.setVisibility(View.INVISIBLE);
        op1.setVisibility(View.VISIBLE);
        op2.setVisibility(View.VISIBLE);
        op3.setVisibility(View.VISIBLE);
        op4.setVisibility(View.VISIBLE);
        timeLeft.setVisibility(View.VISIBLE);
        mathCount.setVisibility(View.VISIBLE);
        currentScore.setVisibility(View.VISIBLE);
        round();
        CountDownTimer timer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds= (int)millisUntilFinished/1000;
                if (seconds<10)
                timeLeft.setText("0:"+"0"+Integer.toString(seconds)+"s");
                else
                    timeLeft.setText("0:"+Integer.toString(seconds)+"s");
            }

            @Override
            public void onFinish() {
                again.setVisibility(View.VISIBLE);
                timeLeft.setText("0:00s");
                op1.setEnabled(false);
                op2.setEnabled(false);
                op3.setEnabled(false);
                op4.setEnabled(false);
            }
        }.start();

    }


    @Override

    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        op1 = (Button) findViewById(R.id.op1);
        op2 = (Button) findViewById(R.id.op2);
        op3 = (Button) findViewById(R.id.op3);
        op4 = (Button) findViewById(R.id.op4);
        again = (Button) findViewById(R.id.again);
        timeLeft = (TextView) findViewById(R.id.timeLeft);
        mathCount = (TextView) findViewById(R.id.mathCount);
        userMessage = (TextView) findViewById(R.id.userMessage);
        currentScore = (TextView) findViewById(R.id.currentScore);
        op1.setVisibility(View.INVISIBLE);
        op2.setVisibility(View.INVISIBLE);
        op3.setVisibility(View.INVISIBLE);
        op4.setVisibility(View.INVISIBLE);
        again.setVisibility(View.INVISIBLE);
        timeLeft.setVisibility(View.INVISIBLE);
        mathCount.setVisibility(View.INVISIBLE);
        userMessage.setVisibility(View.INVISIBLE);
        currentScore.setVisibility(View.INVISIBLE);

        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testWrite(op1);
                round();

            }
        });

        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testWrite(op2);
                round();

            }
        });

        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testWrite(op3);
                round();

            }
        });


        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                testWrite(op4);
                round();
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }







    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.gustavo.braintrainer/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.gustavo.braintrainer/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
