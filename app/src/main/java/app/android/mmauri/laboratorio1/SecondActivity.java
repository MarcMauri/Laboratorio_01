package app.android.mmauri.laboratorio1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    RadioButton radioButtonGreeter;
    RadioButton radioButtonFarewell;
    SeekBar seekBarAge;
    TextView textViewAge;
    Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Configurar action bar
        initializeActionBar();

        // Se recogen los valores de la vista
        initializeVariables();

        textViewAge.setText(String.valueOf(seekBarAge.getProgress()));
        Bundle b = getIntent().getExtras();
        if (b != null && b.getString("name") != null && !b.getString("name").isEmpty()) {

            // Si el pase entre activities ha ido bien...
            final String name = b.getString("name");

            seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int currentAge = seekBarAge.getProgress();

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    currentAge = progress;
                    textViewAge.setText(String.valueOf(currentAge));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // No code
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (currentAge < 16) {
                        Toast.makeText(SecondActivity.this, "The min age allowed is: 16 years old", Toast.LENGTH_SHORT).show();
                        buttonNext.setVisibility(View.INVISIBLE);
                    } else if (currentAge > 60) {
                        Toast.makeText(SecondActivity.this, "The max age allowed is: 60 years old", Toast.LENGTH_SHORT).show();
                        buttonNext.setVisibility(View.INVISIBLE);
                    } else {
                        buttonNext.setVisibility(View.VISIBLE);
                    }
                }
            });

            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("age", textViewAge.getText().toString());

                    if (radioButtonGreeter.isChecked()) {
                        intent.putExtra("subject", "greeter");
                    } else if (radioButtonFarewell.isChecked()) {
                        intent.putExtra("subject", "farewell");
                    } else {
                        Toast.makeText(SecondActivity.this, "A RadioButton error has occurred", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(SecondActivity.this, "An error has occurred", Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeVariables() {
        radioButtonGreeter = (RadioButton) findViewById(R.id.radioButtonGreeter);
        radioButtonFarewell = (RadioButton) findViewById(R.id.radioButtonFarewell);
        seekBarAge = (SeekBar) findViewById(R.id.seekBarAge);
        textViewAge = (TextView) findViewById(R.id.textViewAge);
        buttonNext = (Button) findViewById(R.id.buttonNext);
    }

    private void initializeActionBar() {
        // Activar icono en el action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        // Activar flecha de retorno en el action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
