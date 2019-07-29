package fandradetecinfo.com.moviecollectionapp.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fandradetecinfo.com.moviecollectionapp.MainActivity;
import fandradetecinfo.com.moviecollectionapp.R;

public class FragFilme extends _BaseFrag {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        InitData();

        View v = super.onCreateView(inflater, container, savedInstanceState);
        setFooterInvisible(v);
        return v;
    }

    private void InitData()
    {
        _tela = "mov";

        mDados.put("a_nome", "Filmes para ver");
        mDados.put("z_nome", "IMDB");
        mDados.put("url", getString(R.string.url_filme));
        mDados.put("coluna_a_nome", getString(R.string.base_view_movie_col_a));
        mDados.put("coluna_a_ordem", "1");
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