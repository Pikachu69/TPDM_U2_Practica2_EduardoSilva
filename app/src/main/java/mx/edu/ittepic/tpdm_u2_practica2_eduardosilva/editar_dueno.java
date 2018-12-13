package mx.edu.ittepic.tpdm_u2_practica2_eduardosilva;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editar_dueno extends AppCompatActivity {
    EditText idDueno,dnombre, ddomicilio, dtelefono;
    Button dmodificar, dborrar;
    Dueno dueno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_dueno);

        dueno = new Dueno(this);

        String id= getIntent().getExtras().getString("id");
        String nombre = getIntent().getExtras().getString("nombre");
        final String domicilio = getIntent().getExtras().getString("domicilio");
        String telefono = getIntent().getExtras().getString("telefono");

        idDueno = findViewById(R.id.diddueño);
        dnombre = findViewById(R.id.dnombre);
        ddomicilio = findViewById(R.id.ddomicilio);
        dtelefono = findViewById(R.id.dtelefono);

        dmodificar = findViewById(R.id.dmodificar);
        dborrar = findViewById(R.id.dborrar);

        idDueno.setText(id);
        dnombre.setText(nombre);
        ddomicilio.setText(domicilio);
        dtelefono.setText(telefono);

        dmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean respuesta = dueno.actualizar(new Dueno(idDueno.getText().toString(),dnombre.getText().toString()
                        ,ddomicilio.getText().toString(),dtelefono.getText().toString()));
                if(respuesta){
                    AlertDialog.Builder a = new AlertDialog.Builder(editar_dueno.this);
                    a.setTitle("Exito").setMessage("se actualizó").show();
                } else {
                    AlertDialog.Builder a = new AlertDialog.Builder(editar_dueno.this);
                    a.setTitle("ERROR").setMessage("no se pudo actualizar").show();
                }
            }
        });

        dborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean respuesta = dueno.eliminar(new Dueno(idDueno.getText().toString(),dnombre.getText().toString()
                        ,ddomicilio.getText().toString(),dtelefono.getText().toString()));
                if(respuesta){
                    AlertDialog.Builder a = new AlertDialog.Builder(editar_dueno.this);
                    a.setTitle("Exito").setMessage("se eliminó").show();
                } else {
                    AlertDialog.Builder a = new AlertDialog.Builder(editar_dueno.this);
                    a.setTitle("ERROR").setMessage("no se pudo eliminar").show();
                }

            }
        });
    }
}
