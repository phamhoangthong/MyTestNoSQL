package com.example.phamh.mytestnosql;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.storage.StorageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

    private MTable mTable;

    class MyChildDataTest {
        public String value_1;
        public int  value_2;
        public boolean value_3;
    }

    class MyDataTest {
        public String value_1;
        public int  value_2;
        public boolean value_3;
        //public MyChildDataTest value_4;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean hasPermission = (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
        } else {
            mTable = new MTable("my_test_table");
            Button m_button;
            m_button = (Button)findViewById(R.id.button_test_write_data);
            m_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    long start = System.nanoTime();
                    int count_loop = 100000;
                    MyDataTest myDataTest = new MyDataTest();
                    myDataTest.value_1 = MTable.generateRandomCode(1);
                    myDataTest.value_2 = 123456789;
                    myDataTest.value_3 = true;
                    //myDataTest.value_4 = new MyChildDataTest();
                    //myDataTest.value_4.value_1 = "abcdefghkij";
                    //myDataTest.value_4.value_2 = 987654321;
                    //myDataTest.value_4.value_3 = false;
                    for(int i = 0; i < count_loop; i++) {
                        myDataTest.value_1 = MTable.generateRandomCode(2);
                        if(mTable.addData(myDataTest)) {
                            //Log.i("MY_DEBUG_Main", "Finish added data to table");
                        } else {
                            //Log.i("MY_DEBUG_Main", "Failed added data to table");
                        }
                    }
                    long end = System.nanoTime();
                    long time_ns = end - start;
                    TextView textView = (TextView) findViewById(R.id.textView_info);
                    textView.setText("Write " + String.valueOf(count_loop) + " data for " + String.valueOf(((double)time_ns)/1000000.0) + " ms");
                }
            });

            m_button = (Button)findViewById(R.id.button_test_read_all_data);
            m_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    long start = System.nanoTime();
                    String[] t_data = mTable.readAllData(1);
                    long end = System.nanoTime();
                    long time_ns = end - start;
                    TextView textView = (TextView) findViewById(R.id.textView_info);
                    textView.setText("Read " + String.valueOf(t_data.length) + " data for " + String.valueOf(((double)time_ns)/1000000.0) + " ms");
                }
            });

            m_button = (Button)findViewById(R.id.button_test_optimal_data);
            m_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    long pre_size = mTable.getSize();
                    long start = System.nanoTime();
                    mTable.optimalData();
                    long end = System.nanoTime();
                    long real_size = mTable.getSize();
                    long time_ns = end - start;
                    TextView textView = (TextView) findViewById(R.id.textView_info);
                    textView.setText("Reduce size from " + String.valueOf(((double)pre_size)/1024.0/1024.0) + "MB to " + String.valueOf(((double)real_size)/1024.0/1024.0) +" MB for " + String.valueOf(((double)time_ns)/1000000.0) + " ms");
                }
            });
        }


    }
}
