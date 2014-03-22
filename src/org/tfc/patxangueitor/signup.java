package org.tfc.patxangueitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Oscar on 9/02/14.
 */
public class signup extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        View signupButton = findViewById(R.id.button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Implementar funció crear nou usuari

                //Implementar creació de l'Intent

                //Crear la informació a passar entre Activities

                //Afegir info. a l'Intent

                //Iniciar la nova activitat

            }
        });
        View cancelButton = findViewById(R.id.button2);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }});
    }
}