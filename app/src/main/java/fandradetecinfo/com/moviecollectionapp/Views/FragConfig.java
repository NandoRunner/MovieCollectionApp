package fandradetecinfo.com.moviecollectionapp.Views;

import fandradetecinfo.com.moviecollectionapp.BuildConfig;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import fandradetecinfo.com.moviecollectionapp.PrefsHandler;
import fandradetecinfo.com.moviecollectionapp.R;

public class FragConfig extends Fragment{

    PrefsHandler prefs;
    EditText edt;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vw=inflater.inflate(R.layout.fragment_config, container, false);

        Context ctx = getContext();
        prefs = new PrefsHandler(ctx);

        TextView tv = (TextView) vw.findViewById(R.id.txtVersion);
        tv.setText(String.valueOf("Version: " + BuildConfig.VERSION_NAME));

        final String oriBaseURL = prefs.getBaseURL();

        edt = vw.findViewById(R.id.edtBaseURL);
        edt.setText(oriBaseURL);
        Button btSalvar = vw.findViewById(R.id.btnSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                prefs.salvar(edt.getText().toString());

                Toast toast=Toast.makeText(getActivity().getBaseContext(),"URL salva!",Toast.LENGTH_LONG);
                //toast.setMargin(50,50);
                toast.show();

                Button btSalvar = v.getRootView().findViewById(R.id.btnSalvar);
                btSalvar.setEnabled(false);

                //Sair do Fragment
                //NavUtils.navigateUpFromSameTask(getActivity());
            }
        });

        btSalvar.setEnabled(false);

        edt.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                Button btSalvar = view.getRootView().findViewById(R.id.btnSalvar);
                EditText et = view.getRootView().findViewById(R.id.edtBaseURL);
                if (!et.getText().toString().equals(oriBaseURL)) {
                    //...
                    // Perform your action on key press here
                    // ...
                    btSalvar.setEnabled(true);

                    return true;
                }
                else
                {
                    btSalvar.setEnabled(false);

                }
                return false;
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
