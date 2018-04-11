package com.escatrag.bluetootharduino;

import android.app.Activity;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

/**
 * Created by escatrag on 26/03/18.
 */

public class bluetooth_connection extends Fragment {


    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;

    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private BluetoothAdapter btAdapter = null;
    public static BluetoothSocket btSocket = null;
    private OutputStream outStream = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bluetooth_connection_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textConnectionStatus;
        final ListView pairedListView;
        textConnectionStatus = (TextView) getView().findViewById(R.id.connecting);
        textConnectionStatus.setTextSize(40);

        Intent intent = ((Activity) MainActivity.cMainActivity).getIntent();
        btAdapter = BluetoothAdapter.getDefaultAdapter();

        // On check les devices enregistrés
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(MainActivity.cMainActivity, R.layout.device_name);

        //Met a jour la listView
        pairedListView = (ListView) getView().findViewById(R.id.paired_devices);
        pairedListView.setOnItemClickListener(mDeviceClickListener);
        pairedListView.setAdapter(mPairedDevicesArrayAdapter);

        //Check le bluetooth
        checkBTState();

        mPairedDevicesArrayAdapter.clear();// clears the array so items aren't duplicated when resuming from onPause

        textConnectionStatus.setText(" "); //makes the textview blank

        // Get the local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        // Get a set of currently paired devices and append to pairedDevices list
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();

        // Add previously paired devices to the array
        if (pairedDevices.size() > 0) {
            getView().findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        } else {
            mPairedDevicesArrayAdapter.add("Pas d'appareil appairé");
        }
    }
    //On met un OnClickListener
    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            Log.i("Connection BT", "Connection..");
            Toast.makeText(MainActivity.cMainActivity,"Connection...",Toast.LENGTH_SHORT).show();
            //On recupere l'adresse MAC du device
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);
            //Toast.makeText(MainActivity.cMainActivity,address,Toast.LENGTH_SHORT).show();

            //On recupere l'adresse du device auquel se connecter
            BluetoothDevice device = btAdapter.getRemoteDevice(address);

            //On cree un socket
            try {
                btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e1) {
                Toast.makeText(MainActivity.cMainActivity, "ERROR - Impossible de créer un socket Bluetooth", Toast.LENGTH_SHORT).show();
            }

            //On fait la connection
            try {
                btSocket.connect();
            } catch (IOException e) {
                try {
                    btSocket.close();        //Si une erreur apparait
                } catch (IOException e2) {
                    Toast.makeText(MainActivity.cMainActivity, "ERROR - Impossible de créer un socket Bluetooth", Toast.LENGTH_SHORT).show();
                }
            }

            //On cree uun flux de donnees
            try {
                outStream = btSocket.getOutputStream();
            } catch (IOException e) {
                Toast.makeText(MainActivity.cMainActivity, "ERROR - Impossible de créer un flux Bluetooth", Toast.LENGTH_SHORT).show();
            }
            //On envoie un caractere de test
            sendDatas sendDatass = new sendDatas();
            sendDatass.sendDataConn(btSocket, "x");
        }
    };

    private void checkBTState() {
        // On vérifie que le bluetooth est actif
        mBtAdapter= BluetoothAdapter.getDefaultAdapter();
        if(mBtAdapter==null) {
            Toast.makeText(MainActivity.cMainActivity, "Votre appareil ne supporte pas le Bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            if (!mBtAdapter.isEnabled()) {
                //Demander a allumer le bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }
}
