package fandradetecinfo.com.moviecollectionapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import android.text.TextUtils;

import static fandradetecinfo.com.moviecollectionapp.MainActivity.mTela;

/**
 * Created by Fernando on 12/05/2018.
 */

public class _BaseFrag extends Fragment {
    protected ListView lv;
    protected ArrayList<DadosFilme> list;
    protected DadosFilmeAdapter adapter;

    protected String _ordem;
    protected boolean _isAsc;
    protected String _tela;

    protected Map<String, String> mDados = new Hashtable<>();

    protected TextView tv;
    protected TextView tv15;
    protected TextView tv2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vw=inflater.inflate(R.layout.fragment_dados_filme, container, false);

        tv = (TextView) vw.findViewById(R.id.txtHeader1);
        tv.setText(mDados.get("a_nome"));

        tv15 = (TextView) vw.findViewById(R.id.txtHeader15);
        if (tv15 != null)
            tv15.setText(mDados.get("m_nome"));

        TextView footer = (TextView) vw.findViewById(R.id.txtFooter);
        if (_tela.equals("mov") || _tela.equals("movw"))
            footer.setText("Role a tela lateralmente para navegar no App");
        else
            footer.setText(getString(R.string.pressione_ver_detalhes) + " " + mDados.get("a_nome"));

        tv2 = (TextView) vw.findViewById(R.id.txtHeader2);
        tv2.setText(mDados.get("z_nome")+ " " + Character.toString("\u2191".toCharArray()[0]));
        _ordem = mDados.get("coluna_z_ordem");
        _isAsc = false;

        if (list == null) {
            UpdateList(vw);
            UpdateHeaders(2);
            UpdateListAll();
        }

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!_ordem.equals(mDados.get("coluna_a_ordem"))) {
                    _isAsc = true;
                }
                _ordem = mDados.get("coluna_a_ordem");
                UpdateList(getView());
                UpdateHeaders(1);
                UpdateListAll();
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!_ordem.equals(mDados.get("coluna_z_ordem"))) {
                    _isAsc = false;
                }
                _ordem = mDados.get("coluna_z_ordem");
                UpdateList(getView());
                UpdateHeaders(2);
                UpdateListAll();
            }
        });

        lv=(ListView) vw.findViewById(R.id.lstDadosFilme);
        lv.setAdapter(adapter);
        return  vw;

    }

    private void UpdateHeaders(int header)
    {
       String s1 = mDados.get("a_nome");
       String s2 = mDados.get("z_nome");

       if (header==1) {
           s1 += " " + Character.toString((_isAsc ? "\u2193" : "\u2191").toCharArray()[0]);
       }
       else {
           s2 += " " + Character.toString((_isAsc ? "\u2193" : "\u2191").toCharArray()[0]);
       }
       tv.setText(s1);
       tv2.setText(s2);
       _isAsc = !_isAsc;
    }

    private String[] BuildArrayData(String ordem, boolean isAsc)
    {
        String[] s = new String[7];

        s[0] = MainActivity.baseUrl + mDados.get("url");
        s[1] = ordem;
        s[2] = mDados.get("coluna_a_nome");
        s[3] = mDados.get("coluna_z_nome");
        s[4] = "";
        s[5] = String.valueOf(isAsc);
        s[6] = _tela;

        return s;
    }

    protected void UpdateList(View v) {
        if (TextUtils.isEmpty(MainActivity.baseUrl))
            return;

        String[] arrayData = BuildArrayData(_ordem, _isAsc);

        list = new ArrayList<DadosFilme>();
        adapter = new DadosFilmeAdapter(getActivity(), R.layout.row_dados_filme, list, _tela);
        new JSONAsynTask(getActivity(), list, adapter).execute(arrayData);

        lv = (ListView) v.findViewById(R.id.lstDadosFilme);
        lv.setAdapter(adapter);

        if (!mTela.get(_tela).isLoaded()) {
            // Carregar todas as ordenações
            String[] a2 = BuildArrayData(_ordem, !_isAsc);
            new JSONAsynTask(getActivity()).execute(a2);

            String[] a3 = BuildArrayData(mDados.get("coluna_a_ordem"), _isAsc);
            new JSONAsynTask(getActivity()).execute(a3);

            String[] a4 = BuildArrayData(mDados.get("coluna_a_ordem"), !_isAsc);
            new JSONAsynTask(getActivity()).execute(a4);


        }
    }

    protected void UpdateListAll()
    {
        if (TextUtils.isEmpty(MainActivity.baseUrl))
            return;

        if (!mTela.get(_tela).isLoaded())
        {
            // Carregar todas as ordenações
            String[] a2 = BuildArrayData(_ordem, !_isAsc);
            new JSONAsynTask(getActivity()).execute(a2);

            String[] a3 = BuildArrayData(mDados.get("coluna_a_ordem"), _isAsc);
            new JSONAsynTask(getActivity()).execute(a3);

            String[] a4 = BuildArrayData(mDados.get("coluna_a_ordem"), !_isAsc);
            new JSONAsynTask(getActivity()).execute(a4);

            mTela.get(_tela).setLoaded(true);
        }

        mTela.get(_tela).setLoaded(true);
    }

    protected void LoadTelaFilmePor(String item, String count)
    {
        Intent i = new Intent(getActivity(), ActFilmePor.class);
        i.putExtra("nome", item);
        i.putExtra("qtd", count);
        i.putExtra("tela", _tela);
        startActivity(i);
    }

    protected void setFooterInvisible(View v)
    {
        TextView footer = (TextView) v.findViewById(R.id.txtFooter);
        footer.setVisibility(View.INVISIBLE);
    }
}
