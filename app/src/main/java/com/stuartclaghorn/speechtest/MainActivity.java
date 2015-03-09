package com.stuartclaghorn.speechtest;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {
    TextToSpeech ttsobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ttsobj = new TextToSpeech(getApplicationContext(),new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // Set Language
                // Enhancement - add Language to preferences
                if (status != TextToSpeech.ERROR) {
                    ttsobj.setLanguage(Locale.US);
                }
            }
        });
    }

    @Override
    public void onPause() {
        if (ttsobj != null) {
            ttsobj.stop();
            ttsobj.shutdown();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void speakText(View v) {
        // TTS label
        Button b = (Button)v;
        String lbl = b.getText().toString();
        Toast.makeText(getApplicationContext(), lbl,
                Toast.LENGTH_SHORT).show();
        ttsobj.speak(lbl, TextToSpeech.QUEUE_FLUSH, null);
    }
}
