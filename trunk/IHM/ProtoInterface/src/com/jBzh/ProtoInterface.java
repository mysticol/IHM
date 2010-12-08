package com.jBzh;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ProtoInterface extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void choixMJ(View view) {
        System.out.println("Choix MJ!Wouhouuuuuuuu!!!!");
    }
}