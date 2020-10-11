package co.edu.unimagdalena.apmoviles.unimagdalena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListadoActivity extends AppCompatActivity {
    ListView listado;
    EstudianteController estudianteController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listado = findViewById(R.id.lstListado);
        estudianteController = new EstudianteController(this);
        Cursor c = estudianteController.allEstudiantes2();
        EstudianteCursoAdapter ecursor = new EstudianteCursoAdapter(this,c,false);
        listado.setAdapter(ecursor);
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView cod = view.findViewById(R.id.txtcodigo);
                TextView nombre = view.findViewById(R.id.txtnombre);
                TextView programa = view.findViewById(R.id.txtprograma);
                Intent i = new Intent(getApplicationContext(),EditarActivity.class);
                i.putExtra("codigo",cod.getText().toString());
                i.putExtra("nombre",nombre.getText().toString());
                i.putExtra("programa",programa.getText().toString());
                startActivity(i);
            }
        });
    }
}