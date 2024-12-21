package com.farhan.sqlitedemo

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.farhan.sqlitedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var mUSERDATA: EventDataSQLHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUSERDATA = EventDataSQLHelper(this)

        binding.saveButton.setOnClickListener {
            enterData(binding.nameEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.phoneEditText.text.toString())
            binding.retrieveButton.isEnabled = true

        }
        binding.retrieveButton.setOnClickListener {
            val cursor = getEvents()
            if (cursor!=null){
               binding.addressTextView.text = showData(cursor)
            }
            else{
                binding.addressTextView.text = "No Data Found"
            }
        }
    }

    private fun enterData(name: String, email: String, phone: String) {
        //get the database on mode Write
        val db = mUSERDATA?.writableDatabase
        val values = ContentValues()
        values.put(EventDataSQLHelper.COL_NAME, name)
        values.put(EventDataSQLHelper.COL_EMAIL, email)
        values.put(EventDataSQLHelper.COL_PHONE, phone)
        db?.insert(EventDataSQLHelper.TABLE_NAME, null, values)
    }

    private fun getEvents(): Cursor? {
        val db = mUSERDATA?.readableDatabase
        //Select * From users
        //columns = which columns to return
        //selection = which rows to return
        //selectionArgs = arguments for the selection
        val cursor = db?.query(EventDataSQLHelper.TABLE_NAME, null, null, null, null, null, null)
        return cursor
    }

    private fun showData(cursor: Cursor?):String{
        val ret = StringBuilder("")
        //cursor - hasil query dari getEvents()
        //selagi ada data, kita akan dapatkan nama, email, phone
        while (cursor!!.moveToNext()){
            val id = cursor.getLong(0)
            val name = cursor.getString(1)
            val email = cursor.getString(2)
            val phone = cursor.getString(3)
            ret.append("ID: $id, Name: $name, Email: $email, Phone: $phone")
            ret.append("\n")
        }
        return ret.toString()
    }
}