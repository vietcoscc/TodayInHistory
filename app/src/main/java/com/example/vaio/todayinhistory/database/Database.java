package com.example.vaio.todayinhistory.database;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by vaio on 25/02/2017.
 */

public class Database {
    public static SQLiteDatabase initDatabase(Activity activity, String databaseName) {
        try {
            String PATH = getPath(activity, databaseName);
            File file = new File(PATH);
            if (!file.exists()) {


                File parentFile = file.getParentFile();
                parentFile.mkdirs();

                InputStream inputStream = activity.getAssets().open(databaseName);

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte b[] = new byte[1024];
                int length = inputStream.read(b);
                while (length != -1) {
                    fileOutputStream.write(b, 0, length);
                    length = inputStream.read(b);
                }
                Toast.makeText(activity, "Copy successful !", Toast.LENGTH_SHORT).show();
                inputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activity.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
    }

    public static String getPath(Activity activity, String databaseName) {
        return activity.getApplicationInfo().dataDir + "/databases/" + databaseName;
    }
}
