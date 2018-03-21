package com.example.phamh.mytestnosql;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.storage.StorageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_WRITE_STORAGE = 112;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    this.recreate();
                } else
                {
                    Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private MStore mStore;
    class MyDataTest {
        public String value_1;
        public int  value_2;
        public boolean value_3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean hasPermission = (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
        } else {
            MTable mTable = new MTable("my_test_table");
            for(int i = 0; i < 100; i++) {
                ArrayList<Object> listMyDataTest = new ArrayList<Object>();
                MyDataTest myDataTest = new MyDataTest();
                myDataTest.value_1 = "abcdef";
                myDataTest.value_2 = 100;
                myDataTest.value_3 = true;
                if(mTable.addData(myDataTest)) {
                    Log.i("MY_DEBUG_Main", "Finish added data to table");
                } else {
                    Log.i("MY_DEBUG_Main", "Failed added data to table");
                }
                myDataTest.value_1 = "fedcba";
                myDataTest.value_2 = 200;
                myDataTest.value_3 = false;
                if(mTable.addData(myDataTest)) {
                    Log.i("MY_DEBUG_Main", "Finish added data to table");
                } else {
                    Log.i("MY_DEBUG_Main", "Failed added data to table");
                }
            }


            String[] m_test_data = mTable.readAllData(1);

            /*
            mStore.writeStore("test_store",listMyDataTest);
            ArrayList<Object> listMyDataRead = mStore.readStore("test_store");
            */
            int i = 0;
            i++;

        }
    }
}
