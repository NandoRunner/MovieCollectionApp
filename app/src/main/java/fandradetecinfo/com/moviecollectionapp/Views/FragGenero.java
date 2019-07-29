package fandradetecinfo.com.moviecollectionapp.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import fandradetecinfo.com.moviecollectionapp.MainActivity;
import fandradetecinfo.com.moviecollectionapp.Models.DadosFilme;
import fandradetecinfo.com.moviecollectionapp.PrefsHandler;
import fandradetecinfo.com.moviecollectionapp.R;

public class FragGenero extends _BaseFrag{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        InitData();

        View vw = super.onCreateView(inflater, container, savedInstanceState);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DadosFilme df = adapter.getItem(position);
                //Toast.makeText(getActivity(), df.getNome(), Toast.LENGTH_SHORT).show();
                LoadTelaFilmePor(df.getNome(), df.getFilmes());
                return false;
            }
        });
//
//        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        return vw;
    }

	private void InitData()
    {
        _tela = "gen";

        mDados.put("a_nome", "Gênero");
        mDados.put("z_nome", "Filmes");
        mDados.put("url", getString(R.string.url_genero));
        mDados.put("coluna_a_nome", getString(R.string.base_view_col_a));
        mDados.put("coluna_a_ordem", "1");
        mDados.put("coluna_z_nome", getString(R.string.base_view_col_z));
        mDados.put("coluna_z_ordem", "2");
    }

   public static FragGenero newInstance(String param1) {
        FragGenero f = new FragGenero();
        Bundle args = new Bundle();
        args.putString("msg", param1);

        f.setArguments(args);
        return f;
    }
}