package mx.edu.ittepic.tpdm_u2_practica2_eduardosilva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class nueva_poliza extends AppCompatActivity {
    EditText modelo, marca, year, fechainicio, precio, tipopoliza;
    Spinner piddueno;
    Button pguardar, pregresar;
    Dueno[] duenos;
    Poliza poliza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_poliza);

        modelo = findViewById(R.id.modelo);
        marca = findViewById(R.id.marca);
        year = findViewById(R.id.año);
        fechainicio = findViewById(R.id.fechainicio);
        precio = findViewById(R.id.precio);
        tipopoliza = findViewById(R.id.tipopoliza);
        piddueno = findViewById(R.id.piddueño);
        poliza = new Poliza(this);

        pguardar = findViewById(R.id.pguardar);
        pregresar = findViewById(R.id.pregresar);

        duenos = new Dueno(this).consulta();

        String[] nombres = new String[duenos.length];
        for(int i=0; i<nombres.length; i++){
            nombres[i] = duenos[i].nombre;
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        piddueno.setAdapter(adaptador);

        pguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = piddueno.getSelectedItemPosition();
                Poliza pl = new Poliza(0,modelo.getText().toString(),marca.getText().toString(),Integer.parseInt(year.getText().toString()),
                        fechainicio.getText().toString(),Integer.parseInt(precio.getText().toString()),tipopoliza.getText().toString(),
                        duenos[pos].id);

                boolean respuesta = poliza.insertar(pl);
                if(respuesta) {
                    Toast.makeText(nueva_poliza.this,"Guardada Correctamente",
                            Toast.LENGTH_LONG).show();
                    modelo.setText("");marca.setText("");
                    year.setText("");fechainicio.setText("");
                    precio.setText("");tipopoliza.setText("");
                } else {
                    Toast.makeText(nueva_poliza.this,"ERROR AL INSERTAR",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        pregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
