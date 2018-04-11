package com.escatrag.bluetootharduino;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by luca on 31/03/18.
 */

public class sendDatas {

    private BluetoothAdapter btAdapter = null;
    private OutputStream outStream = null;
    private BluetoothAdapter  mBtAdapter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothSocket oldBtSocket = null;

    // On envoie des donnees
    public void sendDataConn(BluetoothSocket btSocket, String message) {
        byte[] msgBuffer = message.getBytes();
        // On verifie que l'appareil a le bluetooth
        mBtAdapter= BluetoothAdapter.getDefaultAdapter();
        if(mBtAdapter==null) {
            Toast.makeText(MainActivity.cMainActivity, "Votre appareil ne supporte pas le Bluetooth", Toast.LENGTH_SHORT).show();
        }else{
            Log.i("Log Jsp", "BtSocket = "+String.valueOf(btSocket));
            Log.i("Log Jsp", "BtSocket = "+String.valueOf(oldBtSocket));

            if (btSocket != null){
                //On cree un flux de donnees

                try {
                    outStream = btSocket.getOutputStream();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.cMainActivity, "ERROR - Impossible de créer un flux Bluetooth", Toast.LENGTH_SHORT).show();
                }

                try {
                    outStream.write(msgBuffer);
                } catch (IOException e) {
                    Toast.makeText(MainActivity.cMainActivity, "ERROR - Appareil non trouvé. Merci de bien vouloir vous (re)connecter", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.cMainActivity,"Impossible, aucun device connecté",Toast.LENGTH_LONG).show();
            }
        }
    }
}
