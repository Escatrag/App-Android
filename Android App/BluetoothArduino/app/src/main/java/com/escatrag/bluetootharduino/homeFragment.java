package com.escatrag.bluetootharduino;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by escatrag on 25/03/18.
 */

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class homeFragment extends Fragment {

    public FragmentTransaction fragmentTransaction;
    private final static int REQUEST_CODE_ENABLE_BLUETOOTH = 0;
    public Fragment FragmentToDisplay = null;

    TextView statusBlutooth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        statusBlutooth = (TextView) getView().findViewById(R.id.textView21);
        Button BtnConnect = (Button) getView().findViewById(R.id.connect_button);
        Button decoButton = (Button) getView().findViewById(R.id.deconnect_button);


        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        MainActivity.cMainActivity.registerReceiver(mReceiver, filter);

        BtnConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Savoir si on a le bluetooth
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (bluetoothAdapter == null) {
                    Toast.makeText(MainActivity.cMainActivity, "Vous n'avez pas le Bluetooth",
                            Toast.LENGTH_SHORT).show();}
                else {
                    //demander de l'activer si il ne l'est pas
                    if (!bluetoothAdapter.isEnabled()) {
                        Intent enableBlueTooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBlueTooth, REQUEST_CODE_ENABLE_BLUETOOTH);
                    }
                    FragmentToDisplay = new bluetooth_connection();
                    if (FragmentToDisplay != null && getActivity() != null) {
                        fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentLayout, FragmentToDisplay);
                        fragmentTransaction.commit();
                    }
                }
            }
        });

    }
        private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                    //device connecte
                    Log.i("Oh", BluetoothDevice.ACTION_ACL_CONNECTED);
                    statusBlutooth.setText("Statut du bluetooth: connecté");
                    statusBlutooth.setTextColor(Color.GREEN);

                } else if (BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED.equals(action)) {

                } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                    statusBlutooth.setText("Statut du bluetooth: deconnecté");
                    statusBlutooth.setTextColor(Color.RED);
                }
            }
        };


}
