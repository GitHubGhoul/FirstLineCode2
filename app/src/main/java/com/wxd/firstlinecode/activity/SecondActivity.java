package com.wxd.firstlinecode.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wxd.firstlinecode.R;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    private Button button1;
    private PersonParcelable personParcelable;
    private PersonSerializable personSerializable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.e(TAG, "Task id is"+getTaskId());
        Intent intent = getIntent();
        personParcelable = intent.getParcelableExtra("extra_data");
        personSerializable = (PersonSerializable) intent.getSerializableExtra("extra_data");
        Log.e(TAG, personSerializable.getName());
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result_data","Hello First");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    public static void actionStart(Context context,String data1,String data2){
        Intent intent = new Intent(context,SecondActivity.class);
        intent.putExtra("param1",data1);
        intent.putExtra("param2",data2);
        context.startActivity(intent);
    }
}