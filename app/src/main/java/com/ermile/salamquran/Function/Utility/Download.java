package com.ermile.salamquran.Function.Utility;


import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.ermile.salamquran.Actitvty.View.About;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;

public class Download {

    private static long downloadID;

    public static void File(Context context,
                    String URL,
                    String Folder, String FileName, String Format,
                    String Title, String Discretion)
    {
        if (Title == null){
            Title = "Salam Quran";
        }
        if (Discretion == null){
            Discretion = "Downloading...";
        }

        if (URL != null && FileName != null){
            File file = new File(context.getExternalFilesDir(null),Folder + FileName + Format);
            android.app.DownloadManager.Request request=new android.app.DownloadManager.Request(Uri.parse(URL))
                    .setTitle(Title)// Title of the Download Notification
                    .setDescription(Discretion)// Description of the Download Notification
                    .setNotificationVisibility(android.app.DownloadManager.Request.VISIBILITY_VISIBLE)// Visibility of the download Notification
                    .setDestinationUri(Uri.fromFile(file))// Uri of the destination file
//                .setRequiresCharging(false)// Set if charging is required to begin the download
                    .setAllowedOverMetered(true)// Set if download is allowed on Mobile network
                    .setAllowedOverRoaming(true);// Set if download is allowed on roaming network

            android.app.DownloadManager downloadManager= (android.app.DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
            if (downloadManager != null) {
                downloadID = downloadManager.enqueue(request);
            }
        }

    }

    public static BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent ) {

            //Fetching the download id received with the broadcast
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            //Checking if the received broadcast is for our enqueued download by matching download id
            if (downloadID == id) {
                Toast.makeText(context, "Download Completed", Toast.LENGTH_SHORT).show();
            }
        }
    };
}