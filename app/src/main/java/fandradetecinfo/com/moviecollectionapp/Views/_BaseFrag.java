package fandradetecinfo.com.moviecollectionapp.Views;

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
/**
 * Created by Fernando on 12/05/2018.
 */

public class _BaseFrag extends Fragment {
    protected ListView lv;
    protected ArrayList<DadosFilme> list;
    protected DadosFilmeAdapter adapter;

    protected String ordem;

    protected Map<String, String> mDados = new Hashtable<>();;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vw=inflater.inflate(R.layout.fragment_dados_filme, container, false);

        TextView tv = (TextView) vw.findViewById(R.id.txtHeader1);
        tv.setText(mDados.get("a_nome"));

        TextView tv2 = (TextView) vw.findViewById(R.id.txtHeader2);
        tv2.setText(mDados.get("z_nome"));

        if (list == null) {
            UpdateList(vw);
        }

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ordem==null) ordem = mDados.get("coluna_z_ordem");

                if (!ordem.equals(mDados.get("coluna_a_ordem"))) {
                    ordem = mDados.get("coluna_a_ordem");
                    UpdateList(getView());
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
                    UpdateList(getView());
                }
            }
        });
        lv=(ListView) vw.findViewById(R.id.lstDadosFilme);
        lv.setAdapter(adapter);
        return  vw;

    }

    protected void UpdateList(View v)
    {
        String[] arrayData = new String[4];

        if (TextUtils.isEmpty(MainActivity.baseUrl))
            return;

        arrayData[0] = MainActivity.baseUrl + mDados.get("url");
        arrayData[1] = ordem;
        arrayData[2] = mDados.get("coluna_a_nome");
        arrayData[3] = mDados.get("coluna_z_nome");

        list = new ArrayList<DadosFilme>();
        adapter = new DadosFilmeAdapter(getActivity(), R.layout.row_dados_filme, list);
        new JSONAsynTask(getActivity(), list, adapter).execute(arrayData);

        lv=(ListView) v.findViewById(R.id.lstDadosFilme);
        lv.setAdapter(adapter);
    }
}
