package com.escatrag.bluetootharduino;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by escatrag on 26/03/18.
 */

public class pinFragment extends Fragment{

    DiscreteSeekBar pin3, pin5, pin6, pin9,pin10, pin11;
    sendDatas sendDatass = new sendDatas();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pin_pwm_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pin3 = (DiscreteSeekBar) getView().findViewById(R.id.discreteSeekBar2);
        pin5 = (DiscreteSeekBar) getView().findViewById(R.id.discreteSeekBar3);
        pin6 = (DiscreteSeekBar) getView().findViewById(R.id.discreteSeekBar4);
        pin9 = (DiscreteSeekBar) getView().findViewById(R.id.discreteSeekBar5);
        pin10 = (DiscreteSeekBar) getView().findViewById(R.id.discreteSeekBar6);
        pin11 = (DiscreteSeekBar) getView().findViewById(R.id.discreteSeekBar7);

        pin3.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                Log.i("Test On progress Pin 3",String.valueOf(pin3.getProgress()));
                sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin_pwm$3$"+String.valueOf(pin3.getProgress()));
            }
            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {}
        });
        pin5.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                Log.i("Test On progress Pin 5 ",String.valueOf(pin5.getProgress()));
                sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin_pwm$5$"+String.valueOf(pin5.getProgress()));
            }
            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {}
        });
        pin6.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                Log.i("Test On progress Pin 6",String.valueOf(pin6.getProgress()));
                sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin_pwm$6$"+String.valueOf(pin6.getProgress()));
            }
            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {}
        });
        pin9.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                Log.i("Test On progress Pin 9",String.valueOf(pin9.getProgress()));
                sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin_pwm$9$"+String.valueOf(pin9.getProgress()));
            }
            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {}
        });
        pin10.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                Log.i("Test On progress Pin 10",String.valueOf(pin10.getProgress()));
                sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin_pwm$10$"+String.valueOf(pin10.getProgress()));
            }
            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {}
        });
        pin11.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                Log.i("Test On progress Pin 11",String.valueOf(pin11.getProgress()));
                sendDatass.sendDataConn(bluetooth_connection.btSocket,"pin_pwm$11$"+String.valueOf(pin11.getProgress()));
            }
            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {}
        });
    }
}
