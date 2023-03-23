package com.example.sheet_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivity extends AppCompatActivity {

    private Button showSheet;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT>=21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        showSheet = findViewById(R.id.bottom_sheet_display);

        LinearLayout bottomSheetLayout = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        // visible part of bottom sheet dafault
        bottomSheetBehavior.setPeekHeight(0);
        // set your sheet hideable or not
        bottomSheetBehavior.setHideable(false);

        // set clicklistener on content of bottom sheet
        bottomSheetLayout.findViewById(R.id.getLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Getting Link..", Toast.LENGTH_SHORT).show();
                bottomSheetBehavior.setHideable(true);
                bottomSheetBehavior.setState(bottomSheetBehavior.STATE_COLLAPSED);
                showSheet.setVisibility(View.VISIBLE);
            }
        });

        bottomSheetLayout.findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sharing..", Toast.LENGTH_SHORT).show();
                bottomSheetBehavior.setHideable(true);
                bottomSheetBehavior.setState(bottomSheetBehavior.STATE_COLLAPSED);
                showSheet.setVisibility(View.VISIBLE);
            }
        });

        showSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View  v) {
                bottomSheetBehavior.setPeekHeight(600);
                bottomSheetBehavior.setHideable(false);
            }
        });

        // set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState==BottomSheetBehavior.STATE_COLLAPSED)
                {
                    bottomSheetBehavior.setPeekHeight(0);
                    showSheet.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //hide view on slide
                showSheet.setVisibility(View.INVISIBLE);
            }
        });
    }
}