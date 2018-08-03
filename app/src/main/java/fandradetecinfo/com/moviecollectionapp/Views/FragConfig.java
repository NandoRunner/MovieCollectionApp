package fandradetecinfo.com.moviecollectionapp.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import fandradetecinfo.com.moviecollectionapp.PrefsHandler;
import fandradetecinfo.com.moviecollectionapp.R;

public class FragConfig extends Fragment{

    PrefsHandler prefs;
    EditText edt;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vw=inflater.inflate(R.layout.fragment_config, container, false);

        Context ctx = getContext();
        prefs = new PrefsHandler(ctx);

        edt = vw.findViewById(R.id.edtBaseURL);
        edt.setText(prefs.getBaseURL());
        Button btSalvar = vw.findViewById(R.id.btnSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                prefs.salvar(edt.getText().toString());
                //Sair do Fragment
                //NavUtils.navigateUpFromSameTask(getActivity());
            }
        });
        return  vw;

    }

    public static FragConfig newInstance(String param1) {
        FragConfig f = new FragConfig();
        Bundle args = new Bundle();
        args.putString("msg", param1);

        f.setArguments(args);
        return f;
    }
}
