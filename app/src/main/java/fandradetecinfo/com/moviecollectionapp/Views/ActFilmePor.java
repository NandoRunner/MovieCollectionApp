package fandradetecinfo.com.moviecollectionapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import fandradetecinfo.com.moviecollectionapp.DadosFilmeAdapter;
import fandradetecinfo.com.moviecollectionapp.JSONAsynTask;
import fandradetecinfo.com.moviecollectionapp.MainActivity;
import fandradetecinfo.com.moviecollectionapp.Models.DadosFilme;
import fandradetecinfo.com.moviecollectionapp.R;

public class ActFilmePor extends AppCompatActivity {

    protected ListView lv;
    protected ArrayList<DadosFilme> list;
    protected DadosFilmeAdapter adapter;

    protected String ordem;
    protected String filtroValor;

    protected String _tela;

    protected Map<String, String> mDados = new Hashtable<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dados_filme);

        Bundle b = getIntent().getExtras();
        if(b!=null) {
            filtroValor =(String) b.get("nome");
        }

        _tela = (String) b.get("tela");
        InitData();

        TextView tv = (TextView) findViewById(R.id.txtHeader1);
        tv.setText(mDados.get("a_nome"));

        TextView tv2 = (TextView) findViewById(R.id.txtHeader2);
        tv2.setText(mDados.get("z_nome"));

        if (list == null) {
            UpdateList();
        }

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ordem==null) ordem = mDados.get("coluna_z_ordem");

                if (!ordem.equals(mDados.get("coluna_a_ordem"))) {
                    ordem = mDados.get("coluna_a_ordem");
                    UpdateList();
                }
                //Toast.makeText(getActivity(), arrayData[4], Toast.LENGTH_SHORT).show();
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ordem==null) ordem = mDados.get("coluna_z_ordem");

                if (!ordem.equals(mDados.get("coluna_z_ordem"))) {
                    ordem = mDados.get("coluna_z_ordem");
                    UpdateList();
                }
            }
        });
        lv=(ListView) findViewById(R.id.lstDadosFilme);
        lv.setAdapter(adapter);
    }

    private void InitData()
    {
        mDados.put("a_nome", filtroValor);
        mDados.put("z_nome", "IMDB");
        mDados.put("url", MainActivity.mTela.get(_tela).getUrl_second());
        //mDados.put("url", getString(R.string.url_filmes_ator));
        mDados.put("coluna_a_nome", getString(R.string.base_view_movie_col_a));
        mDados.put("coluna_a_ordem", "3");
        mDados.put("coluna_z_nome", "rating");
        mDados.put("coluna_z_ordem", "5");
        mDados.put("filtro", "nome");
    }

    protected void UpdateList()
    {
        String[] arrayData = new String[5];

        if (TextUtils.isEmpty(MainActivity.baseUrl))
            return;

        arrayData[0] = MainActivity.baseUrl + mDados.get("url");
        arrayData[1] = ordem;
        arrayData[2] = mDados.get("coluna_a_nome");
        arrayData[3] = mDados.get("coluna_z_nome");
        arrayData[4] = filtroValor.replace(" ", "+");

        list = new ArrayList<DadosFilme>();
        adapter = new DadosFilmeAdapter(this, R.layout.row_dados_filme, list, _tela);
        new JSONAsynTask(this, list, adapter).execute(arrayData);

        lv=(ListView) findViewById(R.id.lstDadosFilme);
        lv.setAdapter(adapter);
    }

}
