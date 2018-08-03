package fandradetecinfo.com.moviecollectionapp.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fandradetecinfo.com.moviecollectionapp.MainActivity;
import fandradetecinfo.com.moviecollectionapp.PrefsHandler;

public class FragAtor extends _BaseFrag{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        InitData();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

	private void InitData()
    {
        mDados.put("a_nome", "Ator");
        mDados.put("z_nome", "Filmes");
        mDados.put("url", "/mc_atores.php");
        mDados.put("coluna_a_nome", "ator");
        mDados.put("coluna_a_ordem", "1");
        mDados.put("coluna_z_nome", "filmes");
        mDados.put("coluna_z_ordem", "2");
    }

    public static FragAtor newInstance(String param1) {
        FragAtor f = new FragAtor();
        Bundle args = new Bundle();
        args.putString("msg", param1);

        f.setArguments(args);
        return f;
    }
}