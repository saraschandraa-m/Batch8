package camera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.appstone.androidbatch8.R;

public class CameraActivity extends AppCompatActivity {


    private FrameLayout cameraFrame;
    private Camera camera;
    private boolean isBackCamera = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ImageView mIvCapture = findViewById(R.id.capture_img);
        ImageView mIvChangeCamera = findViewById(R.id.rotate_camera);
        ImageView mIvPreviewImg = findViewById(R.id.preview_img);

        cameraFrame = findViewById(R.id.cameraFrame);


        if(ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            startCamera(true);
        }else{
            ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA}, 986);
        }


        mIvChangeCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBackCamera){
                    startCamera(false);
                }else{
                    startCamera(true);
                }
            }
        });

        mIvCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] bytes, Camera camera) {
                        Bitmap capturedBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        mIvPreviewImg.setImageBitmap(capturedBitmap);

                        camera.startPreview();
                    }
                });
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 986){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startCamera(true);
            }else{
                Toast.makeText(CameraActivity.this, "User Denied Permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void startCamera(boolean isBackCamera){
        int cameraID = isBackCamera ? Camera.CameraInfo.CAMERA_FACING_BACK : Camera.CameraInfo.CAMERA_FACING_FRONT;

        this.isBackCamera = isBackCamera;

        camera = Camera.open(cameraID);
        CameraSurfaceView surfaceView = new CameraSurfaceView(CameraActivity.this, camera);
        cameraFrame.addView(surfaceView);

    }
}