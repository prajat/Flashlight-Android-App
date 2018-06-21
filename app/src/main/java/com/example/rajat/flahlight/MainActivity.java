package com.example.rajat.flahlight;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    android.hardware.Camera camera;
    android.hardware.Camera.Parameters parameters;
    boolean isflash = false;
    boolean ison = false;

    ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.imageButton);




        if (getApplication().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {


            camera= android.hardware.Camera.open();
            parameters=camera.getParameters();
            isflash=true;

        }





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isflash) {
                    if (!ison) {
                        btn.setImageResource(R.mipmap.on_btn);
                        parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameters);
                        camera.startPreview();
                        ison = true;
                    } else {

                        btn.setImageResource(R.mipmap.off_btn);
                        parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
                        camera.stopPreview();
                        ison = false;
                    }
                }


                else
                {

                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("flash apllication");
                    builder.setMessage("sorry your device doesn't support this feature");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();

                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        camera =null;
    }
}
