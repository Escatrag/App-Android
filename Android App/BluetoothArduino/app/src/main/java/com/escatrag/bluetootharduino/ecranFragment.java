package com.escatrag.bluetootharduino;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by escatrag on 26/03/18.
 */

public class ecranFragment extends Fragment {

    Button send;
    EditText text;
    LinearLayout linearLayout;
    sendDatas sendDatass = new sendDatas();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ecran_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        text = (EditText) getView().findViewById(R.id.TextToSend);
        send = (Button) getView().findViewById(R.id.sendButtonScreen);
        linearLayout = (LinearLayout) getView().findViewById(R.id.linearLayoutScreen);

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //sendDatass.sendDataConn(bluetooth_connection.btSocket,"ecran$null$"+text.getText());
                TextView txt1 = new TextView(MainActivity.cMainActivity);
                txt1.setText("Envoyé: "+text.getText());
                linearLayout.setBackgroundColor(Color.TRANSPARENT);
                linearLayout.addView(txt1);
                Log.i("Called: ", "Appelé: send");
            }
        });
    }
}
