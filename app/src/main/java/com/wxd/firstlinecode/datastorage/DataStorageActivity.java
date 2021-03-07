package com.wxd.firstlinecode.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wxd.firstlinecode.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DataStorageActivity extends AppCompatActivity {

    private static final String TAG = "DataStorageActivity";
    private EditText editText;
    private String inputText;
    private Button btnSavePerference;
    private Button btnGetPerference;
    private Button btnCreate;
    private Button btnAdd;
    private Button btnQuery;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnProviderAdd;
    private Button btnProviderQuery;
    private Button btnProviderUpdate;
    private Button btnProviderDelete;

    private MyDatabaseHelper databaseHelper;
    private static final String CONTENT_URI = "content://com.wxd.firstlinecode.provider/";
    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);
        editText = findViewById(R.id.editText);
        btnSavePerference = findViewById(R.id.btnSavePreference);
        btnGetPerference = findViewById(R.id.btnGetPreference);
        btnCreate = findViewById(R.id.btnCreate);
        btnAdd = findViewById(R.id.btnAdd);
        btnQuery = findViewById(R.id.btnQuery);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnProviderAdd = findViewById(R.id.btnProviderAdd);
        btnProviderQuery = findViewById(R.id.btnProviderQuery);
        btnProviderUpdate = findViewById(R.id.btnProviderUpdate);
        btnProviderDelete = findViewById(R.id.btnProviderDelete);
        inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
            editText.setText(inputText);
            editText.setSelection(inputText.length());
        }
        btnSavePerference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 28);
                editor.putBoolean("married", false);
                editor.apply();
            }
        });
        btnGetPerference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
                btnGetPerference.setText("name:" + preferences.getString("name", "") + "\n"
                        + "age:" + preferences.getInt("age", 0) + "\n"
                        + "married:" + preferences.getBoolean("married", false));
            }
        });
        databaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.getWritableDatabase();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                //db.insert("Book", null, values);
                values.clear();
                values.put("name", "The Last Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                //db.insert("Book", null, values);
                db.execSQL("insert into Book (name,author,pages,price) values (?,?,?,?)",
                        new String[]{"first", "one", "100", "10.1"});
                db.execSQL("insert into Book (name,author,pages,price) values (?,?,?,?)",
                        new String[]{"second", "two", "1000", "100.11"});
            }
        });
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                //Cursor cursor = db.query("Book", null, null, null, null, null, null);
                Cursor cursor = db.rawQuery("select * from Book", null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.e(TAG, "name: " + name);
                        Log.e(TAG, "author: " + author);
                        Log.e(TAG, "pages: " + pages);
                        Log.e(TAG, "price: " + price);
                        btnQuery.setText("name:" + name + "\n"
                                + "author:" + author + "\n"
                                + "pages:" + pages + "\n"
                                + "price:" + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
                //db.update("Book", values, "name = ?", new String[]{"The Da Vinci Code"});
                db.execSQL("update Book set price = ? where name = ?",new String[]{"20.00","first"});
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                //db.delete("Book", "pages > ?", new String[]{"500"});
                db.execSQL("delete from Book where pages > ?", new String[]{"500"});
            }
        });
        btnProviderAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(CONTENT_URI+"book");
                ContentValues values = new ContentValues();
                values.put("name","A Clash of Kings");
                values.put("author","George Martin");
                values.put("pages",1040);
                values.put("price",22.85);
                Uri newUri = getContentResolver().insert(uri,values);
                newId = newUri.getPathSegments().get(1);
            }
        });
        btnProviderQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(CONTENT_URI+"book");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if(cursor!=null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.e(TAG, "name: "+name);
                        Log.e(TAG, "author: "+author);
                        Log.e(TAG, "pages: "+pages);
                        Log.e(TAG, "price: "+price);
                        btnProviderQuery.setText("name:" + name + "\n"
                                + "author:" + author + "\n"
                                + "pages:" + pages + "\n"
                                + "price:" + price);
                    }
                    cursor.close();
                }
            }
        });
        btnProviderUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(CONTENT_URI+"book/"+newId);
                ContentValues values = new ContentValues();
                values.put("name","A Storm of Swords");
                values.put("pages",1216);
                values.put("price",24.05);
                getContentResolver().update(uri,values,null,null);
            }
        });
        btnProviderDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(CONTENT_URI+"book/"+newId);
                getContentResolver().delete(uri,null,null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save(editText.getText().toString().trim());
    }

    private void save(String data) {
        FileOutputStream fos = null;
        BufferedWriter writer = null;
        try {
            fos = openFileOutput("data", MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String load() {
        FileInputStream fis = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            fis = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(fis));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}