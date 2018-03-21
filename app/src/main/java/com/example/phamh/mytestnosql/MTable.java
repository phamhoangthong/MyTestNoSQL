package com.example.phamh.mytestnosql;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by phamh on 3/20/2018.
 */

public class MTable {

    private ArrayList<String> list_file_store = new ArrayList<String>();    //  danh sach file cua table

    private static int LENGTH_NAME_FILE = 10;   //  chieu dai ten file

    private MStore mStore;  //  api giao tiep file

    private String name_table;  //  ten table

    public MTable(String name) {
        name_table = name;
        mStore = new MStore(name_table);
        String[] arr_file = mStore.getListFile();
        if((arr_file == null) ||(arr_file.length == 0)) {
            if(createNewStore()) {
                Log.i("MY_DEBUG_MTable", "Created Table `" + name_table + "`");
            } else {
                Log.i("MY_DEBUG_MTable", "Error with created Table `" + name_table + "`");
            }
        } else {
            for(String str_temp : arr_file) {
                list_file_store.add(str_temp);
            }
            Log.i("MY_DEBUG_MTable", "Created Table `" + name_table + "`");
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  Them khong gian luu tru
    private boolean createNewStore() {
        while(true) {
            String temp_str = generateRandomCode(LENGTH_NAME_FILE);
            if(list_file_store.size() > 0) {
                boolean t_flag = false;
                for(String t_str : list_file_store) {
                    if(t_str.equals(temp_str)) {
                        t_flag = true;
                        break;
                    }
                }
                if(t_flag == false) {
                    if(mStore.createNewFile(temp_str)) {
                        list_file_store.add(temp_str);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            else {
                if(mStore.createNewFile(temp_str)) {
                    list_file_store.add(temp_str);
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  Tao ten file
    private String generateRandomCode(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while(str.length() < length) {
            int index = (int)(rnd.nextFloat()*characters.length());
            str.append(characters.charAt(index));
        }
        return str.toString();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  Ghi them data
    public boolean addData(Object input) {
        String temp_str = convertObjectToString(input);
        temp_str += '\n';
        int pos = list_file_store.size() - 1;
        long size_store = mStore.getSizeStore(list_file_store.get(pos));
        if(size_store + temp_str.length() > MStore.LIMIT_SIZE) {
            if(!createNewStore()) {
                return false;
            }
            pos++;
            size_store = 0;
        }
        mStore.writeDataStore(list_file_store.get(pos), size_store, temp_str);
        return true;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  Doc tat ca data
    public String[] readAllData(int limit) {
        ArrayList<String> buf_data_return = new ArrayList<String>();
        for(String name_file : list_file_store) {
            buf_data_return.addAll(mStore.readDataStore(name_file,-1,-1));
        }
        return buf_data_return.toArray(new String[buf_data_return.size()]);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  Chuyen doi Data thanh Text
    private String convertObjectToString(Object input) {
        StringBuilder m_str = new StringBuilder();
        Class<?> m_class = input.getClass();
        if(m_class.isArray()) {
            if(m_class.equals(boolean[].class)) {
                m_str.append('[');
                boolean[] m_buf = (boolean[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(Boolean[].class)) {
                m_str.append('[');
                Boolean[] m_buf = (Boolean[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(byte[].class)) {
                m_str.append('[');
                byte[] m_buf = (byte[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    double temp_data = (double)m_buf[i];
                    m_str.append(temp_data);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(Byte[].class)) {
                m_str.append('[');
                Byte[] m_buf = (Byte[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    double temp_data = (double)m_buf[i];
                    m_str.append(temp_data);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(short[].class)) {
                m_str.append('[');
                short[] m_buf = (short[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    double temp_data = (double)m_buf[i];
                    m_str.append(temp_data);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(Short[].class)) {
                m_str.append('[');
                Short[] m_buf = (Short[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    double temp_data = (double)m_buf[i];
                    m_str.append(temp_data);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(int[].class)) {
                m_str.append('[');
                int[] m_buf = (int[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    double temp_data = (double)m_buf[i];
                    m_str.append(temp_data);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(Integer[].class)) {
                m_str.append('[');
                Integer[] m_buf = (Integer[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    double temp_data = (double)m_buf[i];
                    m_str.append(temp_data);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(long[].class)) {
                m_str.append('[');
                long[] m_buf = (long[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    double temp_data = (double)m_buf[i];
                    m_str.append(temp_data);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(Long[].class)) {
                m_str.append('[');
                Long[] m_buf = (Long[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    double temp_data = (double)m_buf[i];
                    m_str.append(temp_data);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(float[].class)) {
                m_str.append('[');
                float[] m_buf = (float[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    double temp_data = (double)m_buf[i];
                    m_str.append(temp_data);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(Float[].class)) {
                m_str.append('[');
                Float[] m_buf = (Float[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    double temp_data = (double)m_buf[i];
                    m_str.append(temp_data);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(double[].class)) {
                m_str.append('[');
                double[] m_buf = (double[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(Double[].class)) {
                m_str.append('[');
                Double[] m_buf = (Double[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(String[].class)) {
                m_str.append('[');
                String[] m_buf = (String[])input;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append('"');
                    m_str.append(m_buf[i]);
                    m_str.append('"');
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(MTag[].class)){
                m_str.append('[');
                MTag[] m_objs = (MTag[])input;
                for(MTag mObj : m_objs) {
                    m_str.append('"');
                    m_str.append(mObj.getName());
                    m_str.append('"');
                    m_str.append(':');
                    m_str.append(convertObjectToString(mObj.getData()));
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else {
                Object[] m_objs = (Object[])input;
                if(m_objs[0].getClass().equals(MTag.class)) {
                    m_str.append('{');
                    for(Object mObj : m_objs) {
                        m_str.append(convertObjectToString(mObj));
                        m_str.append(',');
                    }
                    m_str.deleteCharAt(m_str.length()-1);
                    m_str.append('}');
                } else {
                    m_str.append('[');
                    for(Object mObj : m_objs) {
                        m_str.append(convertObjectToString(mObj));
                        m_str.append(',');
                    }
                    m_str.deleteCharAt(m_str.length()-1);
                    m_str.append(']');
                }
            }
        } else {
            if(m_class.equals(boolean.class)) {
                boolean m_obj = (boolean)input;
                m_str.append(m_obj);
            } else if(m_class.equals(Boolean.class)) {
                Boolean m_obj = (Boolean)input;
                m_str.append(m_obj);
            } else if(m_class.equals(byte.class)) {
                byte m_obj = (byte)input;
                double temp_data = (double)m_obj;
                m_str.append(temp_data);
            } else if(m_class.equals(Byte.class)) {
                Byte m_obj = (Byte)input;
                double temp_data = (double)m_obj;
                m_str.append(temp_data);
            } else if(m_class.equals(short.class)) {
                short m_obj = (short)input;
                double temp_data = (double)m_obj;
                m_str.append(temp_data);
            } else if(m_class.equals(Short.class)) {
                Short m_obj = (Short)input;
                double temp_data = (double)m_obj;
                m_str.append(temp_data);
            } else if(m_class.equals(int.class)) {
                int m_obj = (int)input;
                double temp_data = (double)m_obj;
                m_str.append(temp_data);
            } else if(m_class.equals(Integer.class)) {
                int m_obj = (int)input;
                double temp_data = (double)m_obj;
                m_str.append(temp_data);
            } else if(m_class.equals(long.class)) {
                long m_obj = (long)input;
                double temp_data = (double)m_obj;
                m_str.append(temp_data);
            } else if(m_class.equals(Long.class)) {
                Long m_obj = (Long)input;
                double temp_data = (double)m_obj;
                m_str.append(temp_data);
            } else if(m_class.equals(float.class)) {
                float m_obj = (float)input;
                double temp_data = (double)m_obj;
                m_str.append(temp_data);
            } else if(m_class.equals(Float.class)) {
                Float m_obj = (Float)input;
                double temp_data = (double)m_obj;
                m_str.append(temp_data);
            } else if(m_class.equals(double.class)) {
                double m_obj = (double)input;
                m_str.append(m_obj);
            } else if(m_class.equals(Double.class)) {
                Double m_obj = (Double)input;
                m_str.append(m_obj);
            } else if(m_class.equals(String.class)) {
                String m_obj = (String)input;
                m_str.append('"');
                m_str.append(m_obj);
                m_str.append('"');
            } else if(m_class.equals(MTag.class)) {
                MTag m_obj = (MTag)input;
                //m_str.deleteCharAt(m_str.length()-1);
                m_str.append('"');
                m_str.append(m_obj.getName());
                m_str.append('"');
                m_str.append(':');
                m_str.append(convertObjectToString(m_obj.getData()));
            } else{
                m_str.append('{');
                //Field[] m_fields = m_class.getFields();
                Field[] m_fields = filterField(m_class.getFields());
                for(int i = 0; i < m_fields.length; i++) {
                    m_fields[i].setAccessible(true);
                    if(java.lang.reflect.Modifier.isFinal(m_fields[i].getModifiers())|java.lang.reflect.Modifier.isStatic(m_fields[i].getModifiers())) {
                    } else {
                        try {
                            Object m_obj = m_fields[i].get(input);
                            m_str.append('"');
                            m_str.append(m_fields[i].getName());
                            m_str.append('"');
                            m_str.append(':');
                            m_str.append(convertObjectToString(m_obj));
                            if(i < m_fields.length - 1)
                                m_str.append(',');
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                m_str.append('}');
            }
        }
        return m_str.toString();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  Loc doi tuong trong convertObjectToString
    private Field[] filterField(Field[] input){
        ArrayList<Field> m_temp = new ArrayList<Field>();
        for(int i = 0; i < input.length; i++){
            if(java.lang.reflect.Modifier.isFinal(input[i].getModifiers())|java.lang.reflect.Modifier.isStatic(input[i].getModifiers())) {

            } else {
                m_temp.add(input[i]);
            }
        }
        Field[] m_return = new Field[m_temp.size()];
        for(int i = 0; i < m_return.length; i++){
            m_return[i] = m_temp.get(i);
        }
        return m_return;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////////////////////////
}
