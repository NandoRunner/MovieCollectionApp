package fandradetecinfo.com.moviecollectionapp.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fandradetecinfo.com.moviecollectionapp.MainActivity;

public class FragFilme extends _BaseFrag {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        InitData();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void InitData()
    {
        mDados.put("a_nome", "Filmes para ver");
        mDados.put("z_nome", "IMDB");
        mDados.put("url", "/mc_filmes_ver.php");
        mDados.put("coluna_a_nome", "titulo");
        mDados.put("coluna_a_ordem", "2");
        mDados.put("coluna_z_nome", "rating");
        mDados.put("coluna_z_ordem", "4");

    }

    public static FragFilme newInstance(String param1) {
        FragFilme f = new FragFilme();
        Bundle args = new Bundle();
        args.putString("msg", param1);

        f.setArguments(args);
        return f;
    }

}