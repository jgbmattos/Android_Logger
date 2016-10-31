package br.com.gpslogger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StartServiceOnBoot extends BroadcastReceiver {
    public StartServiceOnBoot() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "Iniciando GPSLogger", Toast.LENGTH_SHORT ).show();
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent serviceIntent = new Intent(context, GPSLogger.class);
            context.startService(serviceIntent);
        }
    }
}
