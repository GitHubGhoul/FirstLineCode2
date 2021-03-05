package com.wxd.firstlinecode.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wxd.firstlinecode.R;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        //btnLeft = findViewById(R.id.btnLeft);
        //btnLeft.setOnClickListener(this);
        //LeftFragment fragment = (LeftFragment) getSupportFragmentManager().findFragmentById(R.id.leftFragment);
        //replaceFragment(new RightFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLeft:
                replaceFragment(new AnotherRightFragment());
                break;
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout,fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
}