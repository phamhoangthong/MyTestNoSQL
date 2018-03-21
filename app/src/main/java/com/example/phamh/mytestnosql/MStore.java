package com.example.phamh.mytestnosql;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

/**
 * Created by phamh on 3/18/2018.
 */

public class MStore {
    private String file_name_store;
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private static int LIMIT_SIZE = 4194304;
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private static String dir_name_store = "MyStore";
    private EnDecode m_encode_decode = new EnDecode();
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public MStore (String name) {
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.i("MY_DEBUG_ERROR", "Can't use storage");
            return;
        }
        dir_name_store = name;
        File m_dir = new File(Environment.getExternalStorageDirectory(), dir_name_store);
        if(!m_dir.exists()) {
            m_dir.mkdirs();
        }
        m_dir.exists();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public String[] getListFile() {
        File directory = new File(Environment.getExternalStorageDirectory(), dir_name_store);
        if(!directory.exists()) {
            return null;
        }
        //File[] files = directory.listFiles();
        String[] m_return = directory.list();
        return m_return;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public boolean writeStore(String file, ArrayList<Object> data) {
        File m_dir = new File(Environment.getExternalStorageDirectory(),"MyStore");
        if(!m_dir.exists()) {
            m_dir.mkdirs();
        }

        File dataFile = new File(m_dir,file + ".fhts");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ex) {
                Log.i("MY_DEBUG_ERROR", "Can't create file");
            }
        }

        FileOutputStream mOutput;
        FileChannel fileChannel;
        ByteBuffer byteBuffer = ByteBuffer.allocate(LIMIT_SIZE);
        try {
            mOutput = new FileOutputStream(dataFile, false);
            fileChannel = mOutput.getChannel();
        } catch (FileNotFoundException ex) {
            Log.i("MY_DEBUG_ERROR", "Cannot open file");
            return false;
        }

        for (Object m_obj : data) {
            byteBuffer.put(m_encode_decode.convertStringToByte(m_encode_decode.toString(m_obj)));
            byteBuffer.put((byte)('\n'));
        }

        byteBuffer.flip();

        while (byteBuffer.hasRemaining()) {
            try {
                fileChannel.write(byteBuffer);
            } catch (IOException ex) {
                Log.i("MY_DEBUG_ERROR", "Cannot write file");
                return false;
            }
        }

        try {
            fileChannel.close();
            mOutput.close();
        }catch (IOException ex) {
            Log.i("MY_DEBUG_ERROR", "Cannot close file");
            return false;
        }


        return true;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Object> readStore(String file) {
        File m_dir = new File(Environment.getExternalStorageDirectory(),"MyStore");
        if(!m_dir.exists()) {
            return null;
        }

        File dataFile = new File(m_dir,file + ".fhts");
        if (!dataFile.exists()) {
            return null;
        }

        FileInputStream mInput;
        FileChannel fileChannel;
        ByteBuffer byteBuffer = ByteBuffer.allocate(LIMIT_SIZE);
        try {
            mInput = new FileInputStream(dataFile);
            fileChannel = mInput.getChannel();
        } catch (FileNotFoundException ex) {
            Log.i("MY_DEBUG_ERROR", "Cannot open read file");
            return null;
        }

        ByteBuffer t_buffer = ByteBuffer.allocate(LIMIT_SIZE);
        try {
            int t_num_cout = fileChannel.read(t_buffer);
            t_buffer.flip();
            ArrayList<Object> return_data = new ArrayList<Object>();
            StringBuilder t_str_builder = new StringBuilder();
            char temp_byte;
            while(t_buffer.hasRemaining()) {
                temp_byte = (char)t_buffer.get();
                if(temp_byte == '\n') {
                    return_data.add(m_encode_decode.getFromString(t_str_builder.toString()));
                    t_str_builder.setLength(0);
                } else {
                    t_str_builder.append(temp_byte);
                }
            }
            if(t_str_builder.length() > 1) {
                return_data.add(m_encode_decode.getFromString(t_str_builder.toString()));
                t_str_builder.setLength(0);
            }
            return return_data;
        } catch (IOException ex) {
            Log.i("MY_DEBUG_ERROR", "Cannot read file");
            return null;
        }
    }
}
