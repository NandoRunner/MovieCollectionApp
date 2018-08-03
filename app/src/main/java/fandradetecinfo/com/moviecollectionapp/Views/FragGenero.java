package fandradetecinfo.com.moviecollectionapp.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fandradetecinfo.com.moviecollectionapp.MainActivity;

public class FragGenero extends _BaseFrag{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        InitData();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void InitData()
    {
        mDados.put("a_nome", "Gênero");
        mDados.put("z_nome", "Filmes");
        mDados.put("url", "/mc_generos.php");
        mDados.put("coluna_a_nome", "genero");
        mDados.put("coluna_a_ordem", "1");
        mDados.put("coluna_z_nome", "filmes");
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