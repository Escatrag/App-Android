package com.escatrag.bluetootharduino;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by escatrag on 25/03/18.
 */

public class ledFragment extends Fragment {

    sendDatas sendDatass = new sendDatas();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.led_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CompoundButton.OnCheckedChangeListener multiListener = new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                String state;
                Toast.makeText(MainActivity.cMainActivity, getResources().getResourceEntryName(v.getId()) + " " + (isChecked ? "on" : "off"), Toast.LENGTH_SHORT).show();
                if (isChecked) {
                    state="ON";
                    Log.i("Switch State", getResources().getResourceEntryName(v.getId()) + " ON");
                } else {
                    state="OFF";
                    Log.i("Switch State", getResources().getResourceEntryName(v.getId()) + " OFF");
                }
                switch (v.getId()){
                    case R.id.switch1:
                        sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin$1$"+state);
                        break;
                    case R.id.switch2:
                        sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin$2$"+state);
                        break;
                    case R.id.switch3:
                        sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin$3$"+state);
                        break;
                    case R.id.switch4:
                        sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin$4$"+state);
                        break;
                    case R.id.switch5:
                        sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin$5$"+state);
                        break;
                    case R.id.switch6:
                        sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin$6$"+state);
                        break;
                    case R.id.switch7:
                        sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin$7$"+state);
                        break;
                    case R.id.switch8:
                        sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin$8$"+state);
                        break;
                    case R.id.switch9:
                        sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin$9$"+state);
                        break;
                    case R.id.switch10:
                        sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin$10$"+state);
                        break;
                    case R.id.switch11:
                        sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin$11$"+state);
                        break;
                    case R.id.switch12:
                        sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin$12$"+state);
                        break;
                }
            }
        };

        ((Switch) getView().findViewById(R.id.switch1)).setOnCheckedChangeListener(multiListener);
        ((Switch) getView().findViewById(R.id.switch2)).setOnCheckedChangeListener(multiListener);
        ((Switch) getView().findViewById(R.id.switch3)).setOnCheckedChangeListener(multiListener);
        ((Switch) getView().findViewById(R.id.switch4)).setOnCheckedChangeListener(multiListener);
        ((Switch) getView().findViewById(R.id.switch5)).setOnCheckedChangeListener(multiListener);
        ((Switch) getView().findViewById(R.id.switch6)).setOnCheckedChangeListener(multiListener);
        ((Switch) getView().findViewById(R.id.switch7)).setOnCheckedChangeListener(multiListener);
        ((Switch) getView().findViewById(R.id.switch8)).setOnCheckedChangeListener(multiListener);
        ((Switch) getView().findViewById(R.id.switch9)).setOnCheckedChangeListener(multiListener);
        ((Switch) getView().findViewById(R.id.switch10)).setOnCheckedChangeListener(multiListener);
        ((Switch) getView().findViewById(R.id.switch11)).setOnCheckedChangeListener(multiListener);
        ((Switch) getView().findViewById(R.id.switch12)).setOnCheckedChangeListener(multiListener);
    }
}
