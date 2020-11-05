package fandradetecinfo.com.moviecollectionapp.Views;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
    protected boolean isAsc;
    protected String filtroValor;
    protected String filtroValor2;


    protected String _tela;

    protected Map<String, String> mDados = new Hashtable<>();;

    protected TextView tv;
    protected TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);

        tv = (TextView) findViewById(R.id.txtHeader1);
        tv.setText("Filmes");

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle b = getIntent().getExtras();
        if(b!=null) {
            filtroValor =(String) b.get("nome");
            filtroValor2 =(String) b.get("qtd");
        }
        mToolbar.setTitle(filtroValor + " (" + filtroValor2 + ")");

        _tela = (String) b.get("tela");

        InitData();

        tv2 = (TextView) findViewById(R.id.txtHeader2);
        tv2.setText(mDados.get("z_nome"));
        ordem = mDados.get("coluna_z_ordem");
        isAsc = false;

        if (list == null) {
            UpdateList();
            UpdateHeaders(2);
        }

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ordem.equals(mDados.get("coluna_a_ordem"))) {
                    isAsc = true;
                }
                ordem = mDados.get("coluna_a_ordem");
                UpdateList();
                UpdateHeaders(1);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ordem.equals(mDados.get("coluna_z_ordem"))) {
                    isAsc = false;
                }
                ordem = mDados.get("coluna_z_ordem");
                UpdateList();
                UpdateHeaders(2);
            }
        });

        lv=(ListView) findViewById(R.id.lstDadosFilme);
        lv.setAdapter(adapter);
    }


    private void UpdateHeaders(int header)
    {
        String s1 = "Filmes";
        String s2 = mDados.get("z_nome");

        if (header==1) {
            s1 += " " + Character.toString((isAsc ? "\u2193" : "\u2191").toCharArray()[0]);
        }
        else {
            s2 += " " + Character.toString((isAsc ? "\u2193" : "\u2191").toCharArray()[0]);
        }
        tv.setText(s1);
        tv2.setText(s2);
        isAsc = !isAsc;
    }

    protected void UpdateList()
    {
        String[] arrayData = new String[8];

        if (TextUtils.isEmpty(MainActivity.baseUrl))
            return;

        arrayData[0] = MainActivity.baseUrl + mDados.get("url");
        arrayData[1] = ordem;
        arrayData[2] = mDados.get("coluna_a_nome");
        arrayData[3] = mDados.get("coluna_z_nome");
        arrayData[4] = filtroValor.replace(" ", "+");
        arrayData[5] = String.valueOf(isAsc);
        arrayData[6] = _tela;
        arrayData[7] = "";

        list = new ArrayList<DadosFilme>();
        adapter = new DadosFilmeAdapter(this, R.layout.row_dados_filme, list, _tela);
        new JSONAsynTask(this, list, adapter).execute(arrayData);

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

}
