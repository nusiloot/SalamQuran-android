package com.ermile.salamquran.Function.FileManager;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZipFile {
    Context context;

    public UnZipFile(Context context,String FileName,String Foramt) {
        this.context = context;
        String File_Name = FileName+Foramt;
        AssetManager manager = context.getAssets();
        final String OUTPUT_FOLDER = context.getExternalFilesDir(null).getAbsolutePath();
        // create output directory if not exists
        String[] fileNameSplit = File_Name.split("/");
        File folder = new File(OUTPUT_FOLDER + "/" + fileNameSplit[0]);
        if (!folder.exists()) {
            folder.mkdir();
        }
        try {
            // get the zip file content
            BufferedInputStream bis = new BufferedInputStream(manager.open(File_Name));
            ZipInputStream zis = new ZipInputStream(bis);
            // get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileEntry = ze.getName();
                File newFile = new File(OUTPUT_FOLDER + File.separator + fileEntry);
                // create all non existent folders from zip archive
                if (fileEntry.endsWith("/")) {
                    // new File(newFile.getParent()).mkdirs();
                    newFile.mkdirs();
                }
                File parent = newFile.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }
                // FileOutputStream fos = new FileOutputStream(newFile);
                FileOutputStream fos = context.openFileOutput(newFile.getName(),
                        0);
                byte[] buffer = new byte[4096];
                int length = 0;
                while ((length = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                    fos.flush();
                }
                fos.close();
                ze = zis.getNextEntry();
            }
            // close open resources
            bis.close();
            zis.closeEntry();
            zis.close();
        } catch (IOException ex) {
        }
    }

}
