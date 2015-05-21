package kar34.washington.edu.quizdroid;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;


public class Service extends IntentService {
    public Service() {
        super("Service");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(workIntent.getStringExtra("url")));
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
    }
}
