package fandradetecinfo.com.moviecollectionapp.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fandradetecinfo.com.moviecollectionapp.MainActivity;
import fandradetecinfo.com.moviecollectionapp.R;

public class FragDiretor extends _BaseFrag{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        InitData();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

	
	private void InitData()
    {
        mDados.put("a_nome", "Diretor");
        mDados.put("z_nome", "Filmes");
        mDados.put("url", getString(R.string.url_diretor));
        mDados.put("coluna_a_nome", getString(R.string.base_view_col_a));
        mDados.put("coluna_a_ordem", "1");
        mDados.put("coluna_z_nome", getString(R.string.base_view_col_z));
        mDados.put("coluna_z_ordem", "2");

    }

   public static FragDiretor newInstance(String param1) {
        FragDiretor f = new FragDiretor();
        Bundle args = new Bundle();
        args.putString("msg", param1);

        f.setArguments(args);
        return f;
    }

}