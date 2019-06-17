package fandradetecinfo.com.moviecollectionapp.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fandradetecinfo.com.moviecollectionapp.MainActivity;
import fandradetecinfo.com.moviecollectionapp.R;


public class FragFilmeVisto extends _BaseFrag {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        InitData();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void InitData()
    {
        _tela = "movw";

        mDados.put("a_nome", "Filmes vistos");
        mDados.put("z_nome", "Período");
        mDados.put("url", getString(R.string.url_filme_visto));
        mDados.put("coluna_a_nome", getString(R.string.base_view_movie_col_a));
        mDados.put("coluna_a_ordem", "1");
        mDados.put("coluna_z_nome", "periodo_exibe");
        mDados.put("coluna_z_ordem", "5");

    }

    public static FragFilmeVisto newInstance(String param1) {
        FragFilmeVisto f = new FragFilmeVisto();
        Bundle args = new Bundle();
        args.putString("msg", param1);

        f.setArguments(args);
        return f;
    }

}