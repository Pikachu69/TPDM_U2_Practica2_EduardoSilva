package mx.edu.ittepic.tpdm_u2_practica2_eduardosilva;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    Dueno[] duenos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.lista);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                duenos = new Dueno(MainActivity.this).consulta();
                if(duenos == null){
                    Toast.makeText(MainActivity.this,"NO HAY DUEÑOS, CAPTURE PRIMERO",Toast.LENGTH_LONG).show();
                } else {
                    Intent v = new Intent(MainActivity.this, nueva_poliza.class);
                    startActivity(v);
                }
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                Poliza poli = new Poliza(MainActivity.this);
                final Poliza polizas[] = poli.consulta();
                final int pos = i;


                alerta.setTitle("Detalle de "+polizas[pos].iddueno)
                        .setMessage("Tipo de poliza: "+polizas[pos].tipopoliza+"\nID: "+polizas[pos].idcoche
                                +"\n¿Deseas modificar/Eliminar registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int ift) {

                                Intent dpr = new Intent(MainActivity.this, editar_poliza.class);
                                dpr.putExtra("idcoche",polizas[pos].idcoche);
                                dpr.putExtra("modelo",polizas[pos].modelo);
                                dpr.putExtra("marca",polizas[pos].marca);
                                dpr.putExtra("year",polizas[pos].year);
                                dpr.putExtra("fechainicio",polizas[pos].fechaInicio);
                                dpr.putExtra("precio",polizas[pos].precio);
                                dpr.putExtra("tipopoliza",polizas[pos].tipopoliza);
                                dpr.putExtra("idDueno",polizas[pos].iddueno);

                                startActivity(dpr);
                                dialogInterface.dismiss();
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.adddueno:
                Intent v = new Intent(MainActivity.this, nuevo_dueno.class);
                startActivity(v);
                break;
            case R.id.editdueno:
                Intent v2 = new Intent(MainActivity.this, detalles_dueno.class);
                startActivity(v2);
                break;
        }
        return true;
    }

    protected void onStart(){
        super.onStart();
        Poliza poli = new Poliza(this);
        Poliza polizas[] = poli.consulta();
        if(polizas==null){
            Toast.makeText(this,"NO HAY POLIZAS",Toast.LENGTH_LONG).show();
        } else {
            String NoExp[] = new String[polizas.length];
            for (int i = 0; i < NoExp.length; i++) {
                Dueno duenos = new Dueno(this).consultar(polizas[i].iddueno);
                NoExp[i] = polizas[i].modelo + "\n" + duenos.nombre;
            }
            ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, NoExp);
            lista.setAdapter(adap);
        }
    }
}
