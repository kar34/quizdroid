package kar34.washington.edu.quizdroid;


import android.app.DownloadManager;
import android.content.*;
import android.os.ParcelFileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DMReceiver extends BroadcastReceiver {

    public DMReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        try {
            FileInputStream input = new ParcelFileDescriptor.AutoCloseInputStream(dm.openDownloadedFile(intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)));
            FileOutputStream output = context.openFileOutput("questions.json", Context.MODE_PRIVATE);

            while (input.available() >= 1)
                output.write(input.read());
            output.close();

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}