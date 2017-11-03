package app.android.mmauri.laboratorio1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    // Global variables
    ImageButton imageButtonInfo;
    Button buttonShare;
    String textToShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initializeActionBar();
        initializeVariables();

        Bundle b = getIntent().getExtras();
        if (b != null && b.getString("name") != null && !b.getString("name").isEmpty() &&
                b.getString("subject") != null && !b.getString("subject").isEmpty()) {

            // Si el pase entre activities ha ido bien...
            final String name = b.getString("name");
            final String age = b.getString("age");
            final String subject = b.getString("subject");

            //String text;

            imageButtonInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (subject.equals("greeter")) {
                        textToShare = "Hola " + name + ", ¿Cómo llevas esos " + age + " años? #MyForm";
                    } else if (subject.equals("farewell")) {
                        int ageInFuture = Integer.parseInt(age) + 1;
                        textToShare = "Espero verte pronto " + name + ", antes que cumplas " + ageInFuture + ".. #MyForm";
                    } else {
                        Toast.makeText(ThirdActivity.this, "A RadioButton error has occurred", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Toast.makeText(ThirdActivity.this, textToShare, Toast.LENGTH_LONG).show();
                    imageButtonInfo.setVisibility(View.INVISIBLE);
                    buttonShare.setVisibility(View.VISIBLE);
                }
            });

            buttonShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
                    sendIntent.setType("text/plain");
                    startActivity(Intent.createChooser(sendIntent, "Choose an application"));
                }
            });
        } else
            Toast.makeText(ThirdActivity.this, "An error has occurred", Toast.LENGTH_SHORT).show();
    }

    private void initializeVariables() {
        imageButtonInfo = (ImageButton) findViewById(R.id.imageButtonInfo);
        buttonShare = (Button) findViewById(R.id.buttonShare);
    }

    private void initializeActionBar() {
        // Activar icono en el action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        // Activar flecha de retorno en el action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
