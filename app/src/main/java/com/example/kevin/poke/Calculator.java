package com.example.kevin.poke;

import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;


public class Calculator extends ActionBarActivity {
    static AutoCompleteTextView txtPoke1, txtPoke2, txtPoke1Atk1, txtPoke1Atk2, txtPoke1Atk3,
    txtPoke1Atk4, txtPoke2Atk1, txtPoke2Atk2, txtPoke2Atk3, txtPoke2Atk4;
    static TextView lblDamageAlert, lblPoke1Damage, lblPoke2Damage;
    Button btnPoke1Stats, btnPoke2Stats;
    Pokemon pokeLeft, pokeRight;
    ImageView imageLeft, imageRight;
    Double maxDamageLeft, getMaxDamageRight;
    Double[] dmg;
    Weather weather;
    Spinner spnWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_pokedex);
        weather = Weather.CLEAR;
        Double[] dmg = new Double[2];


        lblPoke1Damage = (TextView) findViewById(R.id.txtPoke1Damage);
        lblPoke2Damage = (TextView) findViewById(R.id.txtPoke2Damage);
        txtPoke1 = (AutoCompleteTextView) findViewById(R.id.txtCalcPoke1);
        txtPoke2 = (AutoCompleteTextView) findViewById(R.id.txtCalcPoke2);
        txtPoke1Atk1 = (AutoCompleteTextView) findViewById(R.id.txtCalcPoke1Move1);
        txtPoke1Atk2 = (AutoCompleteTextView) findViewById(R.id.txtCalcPoke1Move2);
        txtPoke1Atk3 = (AutoCompleteTextView) findViewById(R.id.txtCalcPoke1Move3);
        txtPoke1Atk4 = (AutoCompleteTextView) findViewById(R.id.txtCalcPoke1Move4);

        txtPoke2Atk1 = (AutoCompleteTextView) findViewById(R.id.txtCalcPoke2Move1);
        txtPoke2Atk2 = (AutoCompleteTextView) findViewById(R.id.txtCalcPoke2Move2);
        txtPoke2Atk3 = (AutoCompleteTextView) findViewById(R.id.txtCalcPoke2Move3);
        txtPoke2Atk4 = (AutoCompleteTextView) findViewById(R.id.txtCalcPoke2Move4);


        lblDamageAlert = (TextView) findViewById(R.id.txtResult);

        imageLeft = (ImageView) findViewById(R.id.imgPoke1Pic);
        imageRight = (ImageView) findViewById(R.id.imgPoke2Pic);

        setAdapters();

        txtPoke1.addTextChangedListener(new TextWatcher() {
            int index = -1;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                for(String poke : Pokemon.names) {
                    if (poke.equalsIgnoreCase(s.toString())) {
                        index = Pokemon.names.indexOf(poke);
                        pokeLeft = Pokemon.allPokes.get(index);
                        imageLeft.setImageResource(pokeLeft.iconID);
                        imageLeft.invalidate();
                        break;
                    } else {
                        pokeLeft = null;
                        imageLeft.setImageResource(R.drawable.pokeball);
                        imageLeft.invalidate();
                    }
                }
            }
        });
        txtPoke2.addTextChangedListener(new TextWatcher() {
            int index = -1;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                for(String poke : Pokemon.names) {
                    if (poke.equalsIgnoreCase(s.toString())) {
                        index = Pokemon.names.indexOf(poke);
                        pokeRight = Pokemon.allPokes.get(index);
                        imageRight.setImageResource(pokeRight.iconID);
                        imageRight.invalidate();
                        break;
                    } else {
                        pokeRight = null;
                        imageRight.setImageResource(R.drawable.pokeball);
                        imageRight.invalidate();
                    }
                }
            }
        });
    }

    public void btnCalcItOnClick(View view){
        Double damage = calcDamage(new Double[2], pokeLeft, pokeRight, weather);
        // Short damg = damage.shortValue();
        // lblDamageAlert.setText(damg.toString());
    }
    private void setAdapters(){
        ArrayAdapter<String> namesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, Pokemon.names);
        ArrayAdapter<String> attacksAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, Attack.atkNames);


        txtPoke1.setThreshold(2);
        txtPoke2.setThreshold(2);
        txtPoke1.setAdapter(namesAdapter);
        txtPoke2.setAdapter(namesAdapter);

        txtPoke1Atk1.setAdapter(attacksAdapter);
        txtPoke1Atk2.setAdapter(attacksAdapter);
        txtPoke1Atk3.setAdapter(attacksAdapter);
        txtPoke1Atk4.setAdapter(attacksAdapter);

        txtPoke2Atk1.setAdapter(attacksAdapter);
        txtPoke2Atk2.setAdapter(attacksAdapter);
        txtPoke2Atk3.setAdapter(attacksAdapter);
        txtPoke2Atk4.setAdapter(attacksAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
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

    public static Double calcDamage(Double[] damage, Pokemon pokeLeft, Pokemon pokeRight,
                             Weather weather){
        String attack;
        Attack move;
        Double dmg;
        String[] output = new String[2];
        damage[0] = 0.0;
        damage[1] = 0.0;

        if(pokeLeft == null || pokeRight == null){
            return 0.0;
        }
        for(int i = 0; i < Attack.atkNames.length; i++) {
            if (!txtPoke1Atk1.getText().toString().isEmpty() && Attack.atkNames[i].equalsIgnoreCase
                    (txtPoke1Atk1.getText().toString())) {
                damage[0] = PokeMath.calculateMaxDamage(pokeLeft.getBaseAtk(), pokeRight.getBaseDef(),
                        pokeLeft, pokeRight, Attack.getAttack(txtPoke1Atk1.getText().toString()));
            }
        }
        for(int i = 0; i < Attack.atkNames.length; i++) {
            if (!txtPoke1Atk2.getText().toString().isEmpty() && Attack.atkNames[i]
                    .equalsIgnoreCase(txtPoke1Atk1.getText().toString())) {
                dmg = PokeMath.calculateMaxDamage(pokeLeft.getBaseAtk(), pokeRight.getBaseDef(),
                        pokeLeft, pokeRight, Attack.getAttack(txtPoke1Atk2.getText().toString()));
                damage[0] = damage[0] > dmg ? damage[0] : dmg;
            }
        }
        for(int i = 0; i < Attack.atkNames.length; i++) {
            if (!txtPoke1Atk3.getText().toString().isEmpty() && Attack.atkNames[i]
                    .equalsIgnoreCase(txtPoke1Atk1.getText().toString())) {
                dmg = PokeMath.calculateMaxDamage(pokeLeft.getBaseAtk(), pokeRight.getBaseDef(),
                        pokeLeft, pokeRight, Attack.getAttack(txtPoke1Atk3.getText().toString()));
                damage[0] = damage[0] > dmg ? damage[0] : dmg;
            }
        }
        for(int i = 0; i < Attack.atkNames.length; i++) {
            if (!txtPoke1Atk4.getText().toString().isEmpty() && Attack.atkNames[i]
                    .equalsIgnoreCase(txtPoke1Atk1.getText().toString())) {
                dmg = PokeMath.calculateMaxDamage(pokeLeft.getBaseAtk(), pokeRight.getBaseDef(),
                        pokeLeft, pokeRight, Attack.getAttack(txtPoke1Atk4.getText().toString()));
                damage[0] = damage[0] > dmg ? damage[0] : dmg;
            }
        }
        for(int i = 0; i < Attack.atkNames.length; i++) {
            if (!txtPoke2Atk1.getText().toString().isEmpty()  && Attack.atkNames[i]
                    .equalsIgnoreCase(txtPoke1Atk1.getText
                            ().toString())) {
                damage[1] = PokeMath.calculateMaxDamage(pokeRight.getBaseAtk(), pokeLeft.getBaseDef(),
                        pokeRight, pokeLeft, Attack.getAttack(txtPoke2Atk1.getText().toString()));
            }
        }
        for(int i = 0; i < Attack.atkNames.length; i++) {
            if (!txtPoke2Atk2.getText().toString().isEmpty()  && Attack.atkNames[i]
                    .equalsIgnoreCase(txtPoke1Atk1.getText().toString())) {
                dmg = PokeMath.calculateMaxDamage(pokeRight.getBaseAtk(), pokeLeft.getBaseDef(),
                        pokeRight, pokeLeft, Attack.getAttack(txtPoke2Atk2.getText().toString()));
                damage[1] = damage[1] > dmg ? damage[1] : dmg;
            }
        }
        for(int i = 0; i < Attack.atkNames.length; i++) {
            if (!txtPoke2Atk3.getText().toString().isEmpty()  && Attack.atkNames[i]
                    .equalsIgnoreCase(txtPoke1Atk1.getText().toString())) {
                dmg = PokeMath.calculateMaxDamage(pokeRight.getBaseAtk(), pokeLeft.getBaseDef(),
                        pokeRight, pokeLeft, Attack.getAttack(txtPoke2Atk3.getText().toString()));
                damage[1] = damage[1] > dmg ? damage[1] : dmg;
            }
        }
        for(int i = 0; i < Attack.atkNames.length; i++) {
            if (!txtPoke2Atk4.getText().toString().isEmpty() && Attack.atkNames[i]
                    .equalsIgnoreCase(txtPoke1Atk1.getText().toString())) {
                dmg = PokeMath.calculateMaxDamage(pokeRight.getBaseAtk(), pokeLeft.getBaseDef(),
                        pokeRight, pokeLeft, Attack.getAttack(txtPoke2Atk4.getText().toString()));
                damage[1] = damage[1] > dmg ? damage[1] : dmg;
            }
        }
        output[0] = new DecimalFormat("#.##").format(damage[0]);
        lblPoke1Damage.setText(output[0]);

        output[1] = new DecimalFormat("#.##").format(damage[1]);
        lblPoke2Damage.setText(output[1]);

        if(damage[0] > damage[1]){
            lblDamageAlert.setText(pokeLeft.name + " wins!");
        } else if(damage[0] < damage[1]){
            lblDamageAlert.setText(pokeRight.name + " wins!");
        } else if(pokeLeft.getBaseSpe() > pokeRight.getBaseSpe()){
            lblDamageAlert.setText(pokeLeft.getName() + " wins by speed!");
        } else if(pokeRight.getBaseSpe() < pokeLeft.getBaseSpe()){
            lblDamageAlert.setText(pokeRight.getName() + " wins by speed");
        } else {
            lblDamageAlert.setText("Power Tie and Speed Tie!");
        }
        return damage[0] > damage[1] ? damage[0] : damage[1];
    }
}
