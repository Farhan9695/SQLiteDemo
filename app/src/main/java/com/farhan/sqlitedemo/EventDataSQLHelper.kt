package com.farhan.sqlitedemo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class EventDataSQLHelper (context: Context?): SQLiteOpenHelper
    (context, DATABASE_NAME, null, DATABASE_VERSION){

        companion object {
            private val DATABASE_NAME = "events.db"
            private val DATABASE_VERSION = 1
            val TABLE_NAME = "users"
            val COL_NAME = "name"
            val COL_EMAIL = "email"
            val COL_PHONE = "phone"

        }
    //When the DB is created, this method will be called
    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME (${BaseColumns._ID} integer" + "PRIMARY KEY AUTOINCREMENT" +
        "$COL_NAME text, $COL_EMAIL text" + "$COL_PHONE text)"
    }
    //When the DB is upgraded, this method will be called
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        return
    }
}