package mx.edu.ittepic.tpdm_u2_practica2_eduardosilva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

public class editar_poliza extends AppCompatActivity {
    EditText modelo2, marca2, year2, fechainicio2, precio2, tipopoliza2;
    Spinner iddueno2;
    Button pmodificar, peliminar, pregresar2;
    Dueno[] duenos;
    Poliza poliza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_poliza);

        modelo2 = findViewById(R.id.modelo2);
        marca2 = findViewById(R.id.marca2);
        year2 = findViewById(R.id.año2);
        fechainicio2 = findViewById(R.id.fechainicio2);
        precio2 = findViewById(R.id.precio2);
        tipopoliza2 = findViewById(R.id.tipopoliza2);
        iddueno2 = findViewById(R.id.piddueño2);

        pmodificar = findViewById(R.id.pmodificar);
        peliminar = findViewById(R.id.peliminar);
        pregresar2 = findViewById(R.id.pregresar2);

        final int id = getIntent().getExtras().getInt("idcoche");
        String mx= getIntent().getExtras().getString("modelo");
        String max = getIntent().getExtras().getString("marca");
        String ax = getIntent().getExtras().getInt("year")+"";
        String fx = getIntent().getExtras().getString("fechainicio");
        String px = getIntent().getExtras().getFloat("precio")+"";
        String tx = getIntent().getExtras().getString("tipopoliza");
        int iddx = getIntent().getExtras().getInt("idDueno");

        modelo2.setText(mx);
        marca2.setText(max);
        year2.setText(ax);
        fechainicio2.setText(fx);
        precio2.setText(px);
        tipopoliza2.setText(tx);
        iddueno2.setPrompt(""+ iddueno2);


        duenos = new Dueno(this).consulta();
        if(duenos.length==0){
            Toast.makeText(this,"NO HAY DUEÑOS, CAPTURE PRIMERO",Toast.LENGTH_LONG).show();
            pmodificar.setEnabled(false);peliminar.setEnabled(false);
            iddueno2.setEnabled(false);
            return;
        }
        final String[] nombres = new String[duenos.length];
        for(int i=0; i<nombres.length; i++){
            nombres[i] = duenos[i].nombre;
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        iddueno2.setAdapter(adaptador);
        poliza = new Poliza(this);

        pregresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        peliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(poliza.eliminar(new Poliza(id,modelo2.getText().toString(),marca2.getText().toString(),
                        Integer.parseInt(year2.getText().toString()),fechainicio2.getText().toString(),
                        Float.parseFloat(precio2.getText().toString()),tipopoliza2.getText().toString(),
                        duenos[iddueno2.getSelectedItemPosition()].id))){
                    Toast.makeText(editar_poliza.this,"SE BORRO POLIZA",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(editar_poliza.this,"NO SE BORRO POLIZA",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        pmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(poliza.actualizar(new Poliza(id,modelo2.getText().toString(),marca2.getText().toString(),
                        Integer.parseInt(year2.getText().toString()),fechainicio2.getText().toString(),
                        Float.parseFloat(precio2.getText().toString()),tipopoliza2.getText().toString(),
                        duenos[iddueno2.getSelectedItemPosition()].id))){
                    Toast.makeText(editar_poliza.this,"SE MODIFICO POLIZA",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(editar_poliza.this,"NO SE MODIFICO POLIZA",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
