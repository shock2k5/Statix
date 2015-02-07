package com.example.kevin.poke;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    Button btnCalculator;
    Button btnPokedex;
    Button btnMoveset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculator = (Button) findViewById(R.id.btn_calculator);
        btnPokedex = (Button) findViewById(R.id.btnPokedex);
        btnMoveset = (Button) findViewById(R.id.btnMoveset);
    }

    public void onClickCalculator(View view){
        startActivity(new Intent(MainActivity.this, Calculator.class));
    }

    public void onClickPokedex(View view){
        Intent intent = new Intent(MainActivity.this, Chooser.class);
        intent.putExtra("dest", "Pokedex");
        startActivity(intent);
    }

    public void onClickMoveset(View view){
        Intent intent = new Intent(MainActivity.this, Chooser.class);
        intent.putExtra("dest", "Moveset");
        startActivity(intent);
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
