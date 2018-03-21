package com.example.phamh.mytestnosql;

import java.util.ArrayList;

/**
 * Created by phamh on 3/20/2018.
 */

public class MTable {
    private ArrayList<String> list_file_store = new ArrayList<String>();

    private MStore mStore;

    public MTable(String name) {
        mStore = new MStore(name);
        String[] arr_file = mStore.getListFile();
        if(arr_file == null) {
            list_file_store.add(name + String.valueOf(list_file_store.size()));
        } else {
            for(String str_temp : arr_file) {
                list_file_store.add(str_temp);
            }
        }
    }


}
