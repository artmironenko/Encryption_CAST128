package ru.fenderil.encryption_cast128;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;

public class MainActivity extends Activity implements View.OnClickListener {

    Button encoding;
    Button decoding;
    int[] cyphertext;
    EditText originText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        encoding = (Button) findViewById(R.id.encoding);
        decoding = (Button) findViewById(R.id.decoding);
        encoding.setOnClickListener(this);
        decoding.setOnClickListener(this);
        originText = (EditText) findViewById(R.id.originText);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.encoding:
                try {
                    cyphertext = CAST128.encoding(getResources().getString(R.string.originText), getResources().getString(R.string.player1), getResources().getString(R.string.player2));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.decoding:
                try {
                    String answer = CAST128.decoding(cyphertext,getResources().getString(R.string.player1),getResources().getString(R.string.player2));
                    originText.setText(answer);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
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
}
