package com.torchapp.andrew.torchapp;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.Policy;

public class MainActivity extends AppCompatActivity {

    private Button torchButton;
    private boolean torchState = false;
    Camera.Parameters params;
    Camera cam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean hasFlash = this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        setContentView(R.layout.activity_main);
        this.cam = Camera.open();
        this.params = this.cam.getParameters();
        this.torchButton = (Button) findViewById(R.id.torchButton);
        this.torchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cam = Camera.open();
                //params = cam.getParameters();
                if(!torchState){
                    turnOnFlash();
                }
                else{
                    turnOffFlash();

                }

            }
        });

    }

    private void turnOnFlash(){
        //this.params = cam.getParameters();
        this.params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        this.cam.setParameters(params);
        this.cam.startPreview();
        this.torchState = true;
        torchButton.setText("Turn Off");
    }

    private void turnOffFlash(){
        this.params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        this.cam.setParameters(this.params);
        cam.stopPreview();
        //cam.release();
        this.torchState = false;
        torchButton.setText("Turn On");
    }
}
