package mx.edu.ittepic.tpdm_u2_practica2_eduardosilva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class nuevo_dueno extends AppCompatActivity {
    EditText idDueno, nombre, telefono, domicilio;
    Button guardar;
    Dueno d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_dueno);

        idDueno = findViewById(R.id.iddueño);
        nombre = findViewById(R.id.nombre);
        domicilio = findViewById(R.id.domicilio);
        telefono = findViewById(R.id.telefono);

        guardar = findViewById(R.id.guardarabogado);

        guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                d = new Dueno(nuevo_dueno.this);
                boolean res = d.insertar(new Dueno(idDueno.getText().toString(), nombre.getText().toString()
                        , domicilio.getText().toString(), telefono.getText().toString()));

                if(res) {
                    Toast.makeText(nuevo_dueno.this,"Se inserto correctamente",
                            Toast.LENGTH_LONG).show();
                    idDueno.setText("");
                    nombre.setText("");
                    domicilio.setText("");
                    telefono.setText("");
                } else {
                    Toast.makeText(nuevo_dueno.this,"ERROR AL INSERTAR",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
