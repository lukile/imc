package com.example.lukile.imc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

// implements View.OnTouchListener for onTouch function
public class MainActivity extends AppCompatActivity {
    private final String defaut = "Vous devez cliquer sur 'Calculer l\'IMC' pour obtenir un résultat";
    private final String megastring = "Félicitations vous faites le poids idéal ! Ne changez rien, vous pouvez continuer les sushis";

    Button compute = null;
    Button raz = null;

    EditText weight = null;
    EditText height = null;

    RadioGroup group = null;

    CheckBox checkBox = null;

    TextView result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compute = (Button)findViewById(R.id.button_compute);

        raz = (Button)findViewById(R.id.button_raz);

        weight = (EditText)findViewById(R.id.weight);
        height = (EditText)findViewById(R.id.height);

        checkBox = (CheckBox)findViewById(R.id.checkbox);

        group = (RadioGroup)findViewById(R.id.group);

        result = (TextView)findViewById(R.id.result);
        result.setText(defaut);

        compute.setOnClickListener(onCompute);
        raz.setOnClickListener(onRaz);
        checkBox.setOnClickListener(onChecked);
        height.addTextChangedListener(textWatcher);
        weight.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            result.setText(defaut);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private OnClickListener onChecked= new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!((CheckBox)v).isChecked() && result.getText().equals(megastring)){
                result.setText(defaut);
            }else{
                result.setText(megastring);
            }
        }
    };

    private OnClickListener onCompute = new OnClickListener() {
        @Override
        public void onClick(View v) {
            String w = weight.getText().toString();
            String h = height.getText().toString();

            float castHeight = Float.valueOf(h);

            if (castHeight != 0f) {
                float castWeight = Float.valueOf(w);
                if (group.getCheckedRadioButtonId() == R.id.centimeter) {
                    castHeight = castHeight / 100;
                }
                float heightSquare = (float)Math.pow(castHeight, 2);
                float computed = castWeight/heightSquare;

                result.setText(String.valueOf(computed));
            }else {
                Toast.makeText(getApplicationContext(), "La taille ne peut pas être égale à 0", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private OnClickListener onRaz = new OnClickListener() {
        @Override
        public void onClick(View v) {
            weight.getText().clear();
            height.getText().clear();
            result.setText(defaut);
        }
    };

}
