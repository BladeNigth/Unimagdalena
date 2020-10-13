package co.edu.unimagdalena.apmoviles.unimagdalena;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.text.TextUtils;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Estudiante e;
    EstudianteController ec;
    EditText codigo,nombre,programa;
    Button agregar,cancelar,mostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agregar = findViewById(R.id.btnguardar);
        cancelar = findViewById(R.id.btncancelar);
        mostrar = findViewById(R.id.btnlistado);
        codigo = findViewById(R.id.editTextTextPersonName);
        nombre = findViewById(R.id.editTextTextPersonName2);
        programa = findViewById(R.id.editTextTextPersonName3);

        ec = new EstudianteController(this);
        agregar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        mostrar.setOnClickListener(this);

        codigo.setText("");
        nombre.setText("");
        programa.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnguardar:
                if(TextUtils.isEmpty(codigo.getText().toString()) || TextUtils.isEmpty(nombre.getText().toString()) ||
                        TextUtils.isEmpty(programa.getText().toString())){
                    Toast.makeText(this, "Los datos no pueden Estar Vacios", Toast.LENGTH_SHORT).show();
                }else {

                    e = new Estudiante(codigo.getText().toString(), nombre.getText().toString(), programa.getText().toString());
                    if(ec.buscarEstudiante(e.getCodigo())){
                        Toast.makeText(this,"El estudiante ya esta registrado",Toast.LENGTH_LONG).show();
                    }else{
                        ec.agregarEstudiante(e);
                    }

                }
                break;
            case R.id.btnlistado:
             /*   Cursor c = ec.allEstudiantes();
                String cadena = "";
                while(c.moveToNext()){
                    cadena = cadena + c.getString(1) + ", ";
                }
                Toast.makeText(this,cadena,Toast.LENGTH_LONG).show();*/
                Intent i = new Intent(getApplicationContext(),ListadoActivity.class);
                startActivity(i);
                break;
        }
    }
}