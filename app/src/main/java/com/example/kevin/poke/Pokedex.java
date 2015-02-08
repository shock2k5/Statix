package com.example.kevin.poke;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class Pokedex extends ActionBarActivity {
    Intent intent;
    String poke;
    ListView list;
    Pokemon current;
    ImageView profile;
    TextView name, types, abilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex2);
        poke = getIntent().getStringExtra("Pokemon");
        current = Pokemon.getPokemon(poke);
        populateListView();

        profile = (ImageView) findViewById(R.id.imageView4);
        profile.setImageResource(Pokemon.getPokemon(poke).iconID);

        name = (TextView) findViewById(R.id.lblDexName);
        name.setText(poke);

        abilities = (TextView) findViewById(R.id.lblAbilities);
        String str = current.getAbilities()[0];
        if (current.getAbilities().length > 1 && !current.getAbilities()[1].isEmpty()){
            str += " / " + current.getAbilities()[1];
        }
        abilities.setText(str);

        types = (TextView) findViewById(R.id.lblDexTyping);
        String s = current.getType1();
        if (!current.getType2().isEmpty()){
            s += " / " + current.getType2();
        }
        types.setText(s);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pokedex, menu);
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

    private void populateListView(){

        list = (ListView) findViewById(R.id.lstAttacks);
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
                current.attacks));
    }
}
