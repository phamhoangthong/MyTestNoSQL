package com.example.phamh.mytestnosql;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by phamh on 3/18/2018.
 */

public class EnDecode {

    public String toString(Object in) {
        StringBuilder m_str = new StringBuilder();
        Class<?> m_class = in.getClass();
        if(m_class.isArray()) {
            if(m_class.equals(boolean[].class)) {
                m_str.append('[');
                boolean[] m_buf = (boolean[])in;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(Boolean[].class)) {
                m_str.append('[');
                Boolean[] m_buf = (Boolean[])in;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(byte[].class)) {
                m_str.append('[');
                byte[] m_buf = (byte[])in;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(Byte[].class)) {
                m_str.append('[');
                Byte[] m_buf = (Byte[])in;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(short[].class)) {
                m_str.append('[');
                short[] m_buf = (short[])in;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(Short[].class)) {
                m_str.append('[');
                Short[] m_buf = (Short[])in;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(int[].class)) {
                m_str.append('[');
                int[] m_buf = (int[])in;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(Integer[].class)) {
                m_str.append('[');
                Integer[] m_buf = (Integer[])in;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(long[].class)) {
                m_str.append('[');
                long[] m_buf = (long[])in;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(Long[].class)) {
                m_str.append('[');
                Long[] m_buf = (Long[])in;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(float[].class)) {
                m_str.append('[');
                float[] m_buf = (float[])in;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(Float[].class)) {
                m_str.append('[');
                Float[] m_buf = (Float[])in;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(double[].class)) {
                m_str.append('[');
                double[] m_buf = (double[])in;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(Double[].class)) {
                m_str.append('[');
                Double[] m_buf = (Double[])in;
                for(int i = 0; i < m_buf.length; i++) {
                    m_str.append(m_buf[i]);
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else if(m_class.equals(String[].class)) {
                m_str.append('[');
                String[] m_buf = (String[])in;
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
                MTag[] m_objs = (MTag[])in;
                for(MTag mObj : m_objs) {
                    m_str.append('"');
                    m_str.append(mObj.getName());
                    m_str.append('"');
                    m_str.append(':');
                    m_str.append(toString(mObj.getData()));
                    m_str.append(',');
                }
                m_str.deleteCharAt(m_str.length()-1);
                m_str.append(']');
            } else {
                Object[] m_objs = (Object[])in;
                if(m_objs[0].getClass().equals(MTag.class)) {
                    m_str.append('{');
                    for(Object mObj : m_objs) {
                        m_str.append(toString(mObj));
                        m_str.append(',');
                    }
                    m_str.deleteCharAt(m_str.length()-1);
                    m_str.append('}');
                } else {
                    m_str.append('[');
                    for(Object mObj : m_objs) {
                        m_str.append(toString(mObj));
                        m_str.append(',');
                    }
                    m_str.deleteCharAt(m_str.length()-1);
                    m_str.append(']');
                }
            }
        } else {
            if(m_class.equals(boolean.class)) {
                boolean m_obj = (boolean)in;
                m_str.append(m_obj);
            } else if(m_class.equals(Boolean.class)) {
                Boolean m_obj = (Boolean)in;
                m_str.append(m_obj);
            } else if(m_class.equals(byte.class)) {
                byte m_obj = (byte)in;
                m_str.append(m_obj);
            } else if(m_class.equals(Byte.class)) {
                Byte m_obj = (Byte)in;
                m_str.append(m_obj);
            } else if(m_class.equals(short.class)) {
                short m_obj = (short)in;
                m_str.append(m_obj);
            } else if(m_class.equals(Short.class)) {
                Short m_obj = (Short)in;
                m_str.append(m_obj);
                m_str.append(',');
            } else if(m_class.equals(int.class)) {
                int m_obj = (int)in;
                m_str.append(m_obj);
            } else if(m_class.equals(Integer.class)) {
                int m_obj = (int)in;
                m_str.append(m_obj);
            } else if(m_class.equals(long.class)) {
                long m_obj = (long)in;
                m_str.append(m_obj);
            } else if(m_class.equals(Long.class)) {
                Long m_obj = (Long)in;
                m_str.append(m_obj);
            } else if(m_class.equals(float.class)) {
                float m_obj = (float)in;
                m_str.append(m_obj);
            } else if(m_class.equals(Float.class)) {
                Float m_obj = (Float)in;
                m_str.append(m_obj);
            } else if(m_class.equals(double.class)) {
                double m_obj = (double)in;
                m_str.append(m_obj);
            } else if(m_class.equals(Double.class)) {
                Double m_obj = (Double)in;
                m_str.append(m_obj);
            } else if(m_class.equals(String.class)) {
                String m_obj = (String)in;
                m_str.append('"');
                m_str.append(m_obj);
                m_str.append('"');
            } else if(m_class.equals(MTag.class)) {
                MTag m_obj = (MTag)in;
                //m_str.deleteCharAt(m_str.length()-1);
                m_str.append('"');
                m_str.append(m_obj.getName());
                m_str.append('"');
                m_str.append(':');
                m_str.append(toString(m_obj.getData()));
            } else{
                m_str.append('{');
                //Field[] m_fields = m_class.getFields();
                Field[] m_fields = filterField(m_class.getFields());
                for(int i = 0; i < m_fields.length; i++) {
                    m_fields[i].setAccessible(true);
                    if(java.lang.reflect.Modifier.isFinal(m_fields[i].getModifiers())|java.lang.reflect.Modifier.isStatic(m_fields[i].getModifiers())) {
                    } else {
                        try {
                            Object m_obj = m_fields[i].get(in);
                            m_str.append('"');
                            m_str.append(m_fields[i].getName());
                            m_str.append('"');
                            m_str.append(':');
                            m_str.append(toString(m_obj));
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

    private Object getTagObject(Vector<Byte> input) {
        if(input.isEmpty() == true) {
            return null;
        }
        MTag m_return = new MTag();
        if(input.get(0) != '"') {
            input.remove(0);
            return null;
        }
        input.remove(0);
        Vector<Byte> t_name = new Vector<Byte>();
        while(input.size() > 0) {
            Byte t_buf = input.get(0);
            input.remove(0);
            if(t_buf == '"') {
                m_return.name = convertVectorByteToString(t_name);
                t_name.clear();
                break;
            } else {
                t_name.add(t_buf);
            }
        }

        if(input.size() == 0) {
            return null;
        }

        if(input.get(0) != ':') {
            input.remove(0);
            return null;
        }
        input.remove(0);

        Object t_obj = getObject(input);
        if(t_obj.equals(null)) {
            return null;
        }

        m_return.data = t_obj;
        return m_return;
    }

    private Object getObject(Vector<Byte> input) {
        Object m_return;
        switch (input.get(0)) {
            case '{':
                input.remove(0);
                ArrayList<Object> m_buf_tag = new ArrayList<Object>();
                do {
                    Object t_obj = getTagObject(input);
                    if(t_obj == null) {
                        return null;
                    }
                    m_buf_tag.add(t_obj);
                    byte t_char_buf = input.get(0);
                    input.remove(0);
                    if(t_char_buf == '}') {
                        if(m_buf_tag.size() < 1) {
                            return null;
                        }
                        if(m_buf_tag.size() == 1) {
                            m_return = m_buf_tag.get(0);
                        } else {
                            m_return = m_buf_tag.toArray();
                        }
                        m_buf_tag.clear();
                        break;
                    } else if(t_char_buf != ',') {
                        return null;
                    }
                } while(true);
                break;
            case '[':
                input.remove(0);
                ArrayList<Object> m_buf_obj = new ArrayList<Object>();
                do {
                    m_buf_obj.add(getObject(input));
                    byte t_char_buf = input.get(0);
                    input.remove(0);
                    if(t_char_buf == ']') {
                        if(m_buf_obj.size() < 1) {
                            return null;
                        }
                        if(m_buf_obj.size() == 1) {
                            m_return = m_buf_obj.get(0);
                        } else {
                            m_return = m_buf_obj.toArray();
                        }
                        m_buf_obj.clear();
                        break;
                    } else if(t_char_buf != ',') {
                        return null;
                    }
                } while(true);
                break;
            case '"':
                input.remove(0);
                Vector<Byte> t_buf_obj = new Vector<Byte>();
                while(input.size() > 0) {
                    Byte t_buf = input.get(0);
                    input.remove(0);
                    if(t_buf == '"') {
                        break;
                    } else {
                        t_buf_obj.add(t_buf);
                    }
                }
                if(input.size() == 0) {
                    return null;
                }
                m_return = convertVectorByteToString(t_buf_obj);
                break;
            case 't':
                input.remove(0);
                input.remove(0);
                input.remove(0);
                input.remove(0);
                m_return = (boolean)(true);
                break;
            case 'f':
                input.remove(0);
                input.remove(0);
                input.remove(0);
                input.remove(0);
                input.remove(0);
                m_return = (boolean)(false);
                break;
            default:
                Vector<Byte> t_buf_double = new Vector<Byte>();
                while(input.size() > 0) {
                    Byte t_buf = input.get(0);
                    if((t_buf == ',') || (t_buf == ']') || (t_buf == '}')) {
                        break;
                    } else {
                        input.remove(0);
                        t_buf_double.add(t_buf);
                    }
                }
                if(input.size() == 0) {
                    return null;
                }
                m_return = (double)Double.parseDouble(convertVectorByteToString(t_buf_double));
        }
        return m_return;
    }

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

    public Object getFromString(String in) {
        Vector<Byte> buf = new Vector<Byte>();
        for(int i = 0; i < in.length(); i++) {
            char temp = in.charAt(i);
            buf.add(((byte)temp));
        }
        return getObject(buf);
    }

    private static String convertVectorByteToString(Vector<Byte> input) {
        StringBuilder m_return = new StringBuilder();
        for(int i = 0; i < input.size(); i++) {
            m_return.append((char)((byte)(input.get(i))));
        }
        return m_return.toString();
    }

    public static boolean isArray(Object in) {
        Class<?> m_class = in.getClass();
        if(m_class.isArray()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBoolean(Object in) {
        Class<?> m_class = in.getClass();
        return (m_class.equals(boolean.class)) || (m_class.equals(Boolean.class));
    }

    public static boolean isArrayBoolean(Object in) {
        Class<?> m_class = in.getClass();
        return (m_class.equals(boolean[].class)) || (m_class.equals(boolean[].class));
    }

    public static boolean isDouble(Object in) {
        Class<?> m_class = in.getClass();
        return (m_class.equals(double.class)) || (m_class.equals(Double.class));
    }

    public static boolean isArrayDouble(Object in) {
        Class<?> m_class = in.getClass();
        return (m_class.equals(double[].class)) || (m_class.equals(Double[].class));
    }

    public static boolean isString(Object in) {
        Class<?> m_class = in.getClass();
        return m_class.equals(String.class);
    }

    public static boolean isArrayString(Object in) {
        Class<?> m_class = in.getClass();
        return m_class.equals(String[].class);
    }

    public static boolean isMTag(Object in) {
        Class<?> m_class = in.getClass();
        return m_class.equals(MTag.class);
    }

    public static boolean isArrayMTag(Object in) {
        Class<?> m_class = in.getClass();
        return m_class.equals(MTag[].class);
    }

    public static boolean isObject(Object in) {
        Class<?> m_class = in.getClass();
        return m_class.equals(Object.class);
    }

    public boolean isArrayObject(Object in) {
        Class<?> m_class = in.getClass();
        return m_class.equals(Object[].class);
    }

    public static byte[] convertStringToByte(String input) {
        char[] temp_buffer = input.toCharArray();
        byte[] return_buffer = new byte[temp_buffer.length];
        for(int i = 0; i < return_buffer.length; i++) {
            return_buffer[i] = (byte)temp_buffer[i];
        }
        return return_buffer;
    }
}
