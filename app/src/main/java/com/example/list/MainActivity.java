package com.example.list;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

import com.example.list.model.Constants;
import com.example.list.model.Livro;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int selected;
    ArrayList<Livro> listaLivros;
    ArrayAdapter adapter;
    ListView listViewLivros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selected = -1;

        listaLivros = new ArrayList<Livro>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaLivros );

        listViewLivros = ( ListView )findViewById( R.id.listViewLivros);
        listViewLivros.setAdapter( adapter );
        listViewLivros.setSelector( android.R.color.holo_green_light );

        listViewLivros.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                Toast.makeText(MainActivity.this, "" + listaLivros.get( position ).toString(), Toast.LENGTH_SHORT).show();
                selected = position;
            }
        } );

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_main_activity, menu );
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected( MenuItem item ) {

        switch(item.getItemId())
        {
            case R.id.add:
                clicarAdicionar();
                break;
            case R.id.edit:
                clicarEditar();
                break;
            case R.id.delete:
                apagarItemLista();
                break;
        }
        return true;
    }

    private void apagarItemLista(){

        if( listaLivros.size() > 0 ){
            listaLivros.remove( selected );
            adapter.notifyDataSetChanged();;
        }
        else {
            selected = -1;
        }

    }

    public void clicarAdicionar(){
        Intent intent = new Intent( this, MainActivity2.class );
        startActivityForResult( intent, Constants.REQUEST_ADD );
    }

    public void clicarEditar(){

        Intent intent = new Intent( this, MainActivity2.class );

        Livro livro = listaLivros.get( selected );

        intent.putExtra( "id", livro.getId() );
        intent.putExtra( "nome", livro.getNome() );
        intent.putExtra( "editora", livro.getEditora() );
        intent.putExtra( "volume", livro.getVolume() );

        startActivityForResult( intent, Constants.REQUEST_EDIT );
    }


    @Override
    protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == Constants.REQUEST_ADD && resultCode == Constants.RESULT_ADD ){

            String nome = ( String )data.getExtras().get( "nome" );
            String editora = ( String )data.getExtras().get( "editora" );
            String volume = ( String )data.getExtras().get( "volume" );

            Livro livro = new Livro( nome, editora, volume );

            listaLivros.add( livro );
            adapter.notifyDataSetChanged();

        } else if( requestCode == Constants.REQUEST_EDIT && resultCode == Constants.RESULT_ADD ){

            String nome = ( String )data.getExtras().get( "nome" );
            String editora = ( String )data.getExtras().get( "editora" );
            String volume = ( String )data.getExtras().get( "volume" );
            int idEditar = (int)data.getExtras().get( "id" );

            for( Livro livro: listaLivros ){

                if( livro.getId() == idEditar ){
                    livro.setNome( nome );
                    livro.setEditora( editora );
                    livro.setVolume( volume );
                }
            }

            adapter.notifyDataSetChanged();

        }
        else if( resultCode == Constants.RESULT_CANCEL ){
            Toast.makeText( this,"Cancelado",
                    Toast.LENGTH_SHORT).show();
        }

    }

}
