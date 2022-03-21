package com.example.pet_groomer;

import android.provider.BaseColumns;

public final class ClientList {
    private ClientList(){}


    public static class Client implements BaseColumns{
        /*
        pet name
        pet breed
        pet weight
        special instructions
         */
        public static final String TABLE_NAME = "pet_clients";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_BREED = "breed";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_SI = "instructions";
    }

}
