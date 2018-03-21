package com.example.phamh.mytestnosql;

/**
 * Created by phamh on 3/18/2018.
 */

public class MTag extends Object{
    public String name;
    public Object data;

    public MTag() {

    }

    public MTag(String m_name, Object m_data) {
        set(m_name, m_data);
    }

    public String getName() {
        return name;
    }

    public Object getData() {
        return data;
    }

    public void setName(String m_name) {
        name = m_name;
    }

    public void setData(Object m_data) {
        data = m_data;
    }

    public void set(String m_name, Object m_data) {
        name = m_name;
        data = m_data;
    }
}