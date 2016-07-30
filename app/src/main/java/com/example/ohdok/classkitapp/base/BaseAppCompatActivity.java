package com.example.ohdok.classkitapp.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import static com.tsengvn.typekit.TypekitContextWrapper.*;

public class BaseAppCompatActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(wrap(newBase));
    }


}