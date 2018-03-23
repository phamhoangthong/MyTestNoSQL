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
    public static long LIMIT_SIZE = 4194304;
    //public static long LIMIT_SIZE = 4096;
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private static String dir_root_name_store = "MyStore";
    private static String external_file_store = "txt";//"fhts"
    private String dir_name_store;
    private EnDecode m_encode_decode = new EnDecode();
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public MStore (String name) {
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.i("MY_DEBUG_MStore", "Can't use storage");
            return;
        }
        dir_name_store = name;
        File m_dir = new File(Environment.getExternalStorageDirectory().getPath(), dir_root_name_store);
        if(!m_dir.exists()) {
            m_dir.mkdirs();
        }
        m_dir.exists();

        m_dir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + dir_root_name_store, dir_name_store);
        if(!m_dir.exists()) {
            m_dir.mkdirs();
        }
        m_dir.exists();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public String[] getListFile() {
        File directory = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + dir_root_name_store, dir_name_store);
        if(!directory.exists()) {
            return null;
        }
        String[] temp_list = directory.list();

        if(temp_list.length > 0) {
            ArrayList<String> m_return = new ArrayList<String>();
            for(int i = 0; i < temp_list.length; i++) {
                String[] temp_str = temp_list[i].split("\\.");
                if(temp_str.length == 2)
                    if(temp_str[1].equals(external_file_store)) {
                        m_return.add(temp_str[0]);
                    }
            }
            String [] buffer = m_return.toArray(new String[m_return.size()]);
            return buffer;
        } else {
            return null;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public long getSizeStore(String file) {
        File m_dir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + dir_root_name_store, dir_name_store);
        if(!m_dir.exists()) {
            return 0;
        }
        File dataFile = new File(m_dir,file + "." + external_file_store);
        if (!dataFile.exists()) {
            return 0;
        }
        return dataFile.length();
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
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)LIMIT_SIZE);
        try {
            mOutput = new FileOutputStream(dataFile, false);
            fileChannel = mOutput.getChannel();
        } catch (FileNotFoundException ex) {
            Log.i("MY_DEBUG_MStore", "Cannot open file");
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
                Log.i("MY_DEBUG_MStore", "Cannot write file");
                return false;
            }
        }

        try {
            fileChannel.close();
            mOutput.close();
        }catch (IOException ex) {
            Log.i("MY_DEBUG_MStore", "Cannot close file");
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
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)LIMIT_SIZE);
        try {
            mInput = new FileInputStream(dataFile);
            fileChannel = mInput.getChannel();
        } catch (FileNotFoundException ex) {
            Log.i("MY_DEBUG_MStore", "Cannot open read file");
            return null;
        }

        ByteBuffer t_buffer = ByteBuffer.allocate((int)LIMIT_SIZE);
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
            Log.i("MY_DEBUG_MStore", "Cannot read file");
            return null;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public boolean createNewFile(String file) {
        File m_dir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + dir_root_name_store,dir_name_store);
        if(!m_dir.exists()) {
            m_dir.mkdirs();
        }

        File dataFile = new File(m_dir,file + "." + external_file_store);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ex) {
                Log.i("MY_DEBUG_MStore", "Can't create file");
                return false;
            }
        }

        return true;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public boolean writeDataStore(String file, long pos, String data) {
        File m_dir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + dir_root_name_store, dir_name_store);
        if(!m_dir.exists()) {
            m_dir.mkdirs();
        }

        File dataFile = new File(m_dir,file + "." + external_file_store);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ex) {
                Log.i("MY_DEBUG_ERROR", "Can't create file");
                m_dir.exists();
                return false;
            }
        }

        FileOutputStream mOutput;
        FileChannel fileChannel;
        try {
            mOutput = new FileOutputStream(dataFile, true);
            fileChannel = mOutput.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(data.length()).put(convertStringToByte(data));
            byteBuffer.flip();
            try {
                //fileChannel.position(pos);
                int bytes_writed = fileChannel.write(byteBuffer);
                if(byteBuffer.hasRemaining()) {
                    byteBuffer.compact();
                } else {
                    byteBuffer.clear();
                }
            } catch (IOException ex) {
                Log.i("MY_DEBUG_MStore", "Cannot finish writing file");
                return false;
            }
            try {
                fileChannel.close();
                mOutput.close();
            }catch (IOException ex) {
                Log.i("MY_DEBUG_MStore", "Cannot close file");
                dataFile.exists();
                m_dir.exists();
                return false;
            }
            dataFile.exists();
            m_dir.exists();
            return true;
        } catch (FileNotFoundException ex) {
            Log.i("MY_DEBUG_MStore", "Cannot open file");
            dataFile.exists();
            m_dir.exists();
            return false;
        } catch (IOException ex) {
            Log.i("MY_DEBUG_MStore", "Cannot write file at " + String.valueOf(pos));
            dataFile.exists();
            m_dir.exists();
            return false;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<String> readDataStore(String file, long pos, long count) {
        File m_dir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + dir_root_name_store, dir_name_store);
        if(!m_dir.exists()) {
            Log.i("MY_DEBUG_ERROR", "Can't open file: " + file + "." + external_file_store);
            return null;
        }

        File dataFile = new File(m_dir,file + "." + external_file_store);
        if (!dataFile.exists()) {
            Log.i("MY_DEBUG_ERROR", "Can't open file: " + file + "." + external_file_store);
            m_dir.exists();
            return null;
        }

        FileInputStream mInput;
        FileChannel fileChannel;
        try {
            mInput = new FileInputStream(dataFile);
            fileChannel = mInput.getChannel();
            long size_file = dataFile.length();
            if(pos < 0) {
                pos = 0;
            } else if(pos >= size_file) {
                Log.i("MY_DEBUG_MStore", "Error read file: " + file + "." + external_file_store);
                try {
                    fileChannel.close();
                    mInput.close();
                }catch (IOException ex) {
                    Log.i("MY_DEBUG_MStore", "Cannot close file");
                    dataFile.exists();
                    m_dir.exists();
                    return null;
                }
            }
            if(count+pos > size_file) {
                Log.i("MY_DEBUG_MStore", "Error read file: " + file + "." + external_file_store);
                try {
                    fileChannel.close();
                    mInput.close();
                }catch (IOException ex) {
                    Log.i("MY_DEBUG_MStore", "Cannot close file");
                    dataFile.exists();
                    m_dir.exists();
                    return null;
                }
            } else if(count < 0) {
                count = size_file - pos;
            }
            ByteBuffer buffer = ByteBuffer.allocate((int)count);
            try {
                int bytesRead  = fileChannel.read(buffer, (int) pos);
                buffer.flip();
                String temp_str = "";
                ArrayList<String> m_return = new ArrayList<String>();
                while (buffer.hasRemaining()) {
                    char temp_char = (char)buffer.get();
                    if(temp_char == '\n') {
                        m_return.add(temp_str);
                        temp_str = "";
                    } else {
                        temp_str += temp_char;
                    }
                }
                if(temp_str.length() > 0) {
                    m_return.add(temp_str);
                }
                return m_return;
            } catch (IOException ex) {
                Log.i("MY_DEBUG_MStore", "Error read file: " + file + "." + external_file_store);
                try {
                    fileChannel.close();
                    mInput.close();
                }catch (IOException ex1) {
                    Log.i("MY_DEBUG_MStore", "Cannot close file");
                    dataFile.exists();
                    m_dir.exists();
                    return null;
                }
                dataFile.exists();
                m_dir.exists();
                return null;
            }

        } catch (FileNotFoundException ex) {
            Log.i("MY_DEBUG_MStore", "Cannot find file: " + file + "." + external_file_store);
            dataFile.exists();
            m_dir.exists();
            return null;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public boolean deleteFile(String file) {
        File m_dir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + dir_root_name_store,dir_name_store);
        if(!m_dir.exists()) {
            return false;
        }

        File dataFile = new File(m_dir,file + "." + external_file_store);
        if (!dataFile.exists()) {
            return true;
        }
        return dataFile.delete();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public byte[] convertStringToByte(String input) {
        char[] temp_buffer = input.toCharArray();
        byte[] return_buffer = new byte[temp_buffer.length];
        for(int i = 0; i < return_buffer.length; i++) {
            return_buffer[i] = (byte)temp_buffer[i];
        }
        return return_buffer;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
}
