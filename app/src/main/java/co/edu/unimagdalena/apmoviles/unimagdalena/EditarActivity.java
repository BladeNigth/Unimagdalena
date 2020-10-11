package co.edu.unimagdalena.apmoviles.unimagdalena;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditarActivity extends AppCompatActivity implements View.OnClickListener {
    Bundle datos;
    EstudianteController ec;
    EditText nombre,programa;
    Button modificar,eliminar;
    String c,p,n;
    TextView cod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        cod = findViewById(R.id.cod);
        nombre = findViewById(R.id.edtmnombre);
        programa = findViewById(R.id.edtmprograma);
        modificar = findViewById(R.id.btnmodi);
        eliminar = findViewById(R.id.btnelim);
        datos = getIntent().getExtras();
         c = datos.getString("codigo");
         n = datos.getString("nombre");
         p = datos.getString("programa");
        cod.setText(c);
        nombre.setText(n);
        programa.setText(p);
        ec = new EstudianteController(this);
        modificar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnmodi:
                alertM();
                break;

            case R.id.btnelim:
                alertE();
                break;
        }
    }

    public void alertE(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Confirmacion");
        alerta.setMessage("Desea Eliminar los Datos del Estudiante? ")
                .setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ec.Eliminar(c);
                        pause();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

    }

    public void alertM(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Confirmacion");
        alerta.setMessage("Desea Modificar los Datos del Estudiante? ")
                .setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ec.Modificar(cod.getText().toString(),nombre.getText().toString(),programa.getText().toString());
                        pause();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

    }

    public void pause(){
        Handler handler =  new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent i = new Intent(getApplicationContext(),ListadoActivity.class);
                startActivity(i);
            }
        }, 1000);
    }


}