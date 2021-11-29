package com.example.list;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.list.model.Constants;


public class MainActivity2 extends AppCompatActivity {

    EditText edtNome;
    EditText edtEdit;
    EditText edtVol;

    boolean edit;
    int idLivroEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtNome = findViewById( R.id.editTextNome );
        edtEdit = findViewById( R.id.editTextEdit );
        edtVol = findViewById( R.id.editTextVol );

        edit = false;

        if( getIntent().getExtras() != null ){

            String nome = ( String )getIntent().getExtras().get( "nome" );
            String editora = ( String )getIntent().getExtras().get( "editora" );
            String volume = ( String )getIntent().getExtras().get( "volume" );
            idLivroEditar = (int)getIntent().getExtras().get( "id" );

            edtNome.setText( nome );
            edtEdit.setText( editora );
            edtVol.setText( volume );

            edit = true;

        }

    }

    public void adicionar( View view ){

        Intent intent = new Intent();

        String nome = edtNome.getText().toString();
        String editora = edtEdit.getText().toString();
        String volume = edtVol.getText().toString();

        intent.putExtra( "nome", nome );
        intent.putExtra( "editora", editora );
        intent.putExtra( "volume", volume );

        if( edit ) intent.putExtra( "id", idLivroEditar );

        setResult( Constants.RESULT_ADD, intent );
        finish();
    }

    public void cancelar( View view ){
        setResult( Constants.RESULT_CANCEL );
        finish();
    }
}
