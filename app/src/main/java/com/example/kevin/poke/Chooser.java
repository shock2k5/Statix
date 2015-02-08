package com.example.kevin.poke;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Chooser extends ActionBarActivity {

    Button btnChooserSelect;
    ImageView image;
    String[] imageString, pokeNames;
    Adapter myCustomAdapter;
    ListView list;
    boolean Dex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        Dex = getIntent().getStringExtra("dest").equals("Pokedex");

        pokeNames = getResources().getStringArray(R.array.names);

        populateListView();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    intent = new Intent(Chooser.this, Pokedex.class);

                intent.putExtra("Pokemon", Pokemon.allPokes.get(position).getName());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chooser, menu);
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
        ArrayAdapter<Pokemon> adapter = new MyCustomAdapter();
        list = (ListView) findViewById(R.id.lstChooserList);
        list.setAdapter(adapter);
    }

    private class MyCustomAdapter extends ArrayAdapter<Pokemon>{

        public MyCustomAdapter() {
            super(Chooser.this, R.layout.single_row, Pokemon.allPokes);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.single_row, parent, false);
            }
            Pokemon current = Pokemon.allPokes.get(position);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imgPokePic);
            imageView.setImageResource(current.iconID);

            TextView txtName = (TextView) itemView.findViewById(R.id.txtPokeDescription);
            txtName.setText(current.name);

            TextView txtType = (TextView) itemView.findViewById(R.id.txtChooserTyping);
            String str = current.getType1();
            if(current.getType2() != "none"){
                str += " / " + current.getType2();
            }
            txtType.setText(str);

            return itemView;
        }
    }

}
