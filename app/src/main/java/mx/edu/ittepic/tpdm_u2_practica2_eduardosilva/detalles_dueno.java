package mx.edu.ittepic.tpdm_u2_practica2_eduardosilva;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class detalles_dueno extends AppCompatActivity {
    ListView listaDuenos;
    Dueno du;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_dueno);

        listaDuenos = findViewById(R.id.listaDueños);
        du = new Dueno(this);

        listaDuenos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int fi, long l) {
                final Dueno[] s = du.consulta();
                final int i = fi;

                AlertDialog.Builder alerta = new AlertDialog.Builder(detalles_dueno.this);
                alerta.setTitle("Detalle de "+s[i].nombre)
                        .setMessage("id: "+s[i].id+"\nDomicilio: "+s[i].domicilio+"\nTelefono: "
                                +s[i].telefono+"\n¿Deseas modificar/Eliminar registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int ift) {

                                Toast.makeText(detalles_dueno.this,""+s[i].id+" "+s[i].nombre+" "+s[i].domicilio+" "+s[i].telefono,
                                        Toast.LENGTH_LONG).show();
                                Intent i44 = new Intent(detalles_dueno.this, editar_dueno.class);
                                i44.putExtra("id",s[i].id);
                                i44.putExtra("nombre",s[i].nombre);
                                i44.putExtra("domicilio",s[i].domicilio);
                                i44.putExtra("telefono",s[i].telefono);
                                startActivity(i44);
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
    }

    protected void onStart(){
        super.onStart();
        try {
            String nombres[] = {"No hay polizas capturadas aun"};
            Dueno[] s = du.consulta();
            if (s!=null) {
                nombres = new String[s.length];
                for (int i = 0; i < nombres.length; i++) {
                    nombres[i] = s[i].nombre;
                }
            }
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    this, android.R.layout.simple_list_item_1, nombres);
            listaDuenos.setAdapter(adaptador);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
