package camera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.appstone.androidbatch8.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class CameraActivity extends AppCompatActivity {


    private FrameLayout cameraFrame;
    private Camera camera;
    private boolean isBackCamera = true;

    private ImageView mIvPreviewImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ImageView mIvCapture = findViewById(R.id.capture_img);
        ImageView mIvChangeCamera = findViewById(R.id.rotate_camera);
        mIvPreviewImg = findViewById(R.id.preview_img);

        cameraFrame = findViewById(R.id.cameraFrame);


        if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            startCamera(true);
        } else {
            ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 986);
        }


        mIvChangeCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBackCamera) {
                    startCamera(false);
                } else {
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
                        storeImageToCamera(capturedBitmap);
                    }
                });
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 986) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera(true);
            } else {
                Toast.makeText(CameraActivity.this, "User Denied Permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void startCamera(boolean isBackCamera) {
        int cameraID = isBackCamera ? Camera.CameraInfo.CAMERA_FACING_BACK : Camera.CameraInfo.CAMERA_FACING_FRONT;

        this.isBackCamera = isBackCamera;

        camera = Camera.open(cameraID);
        CameraSurfaceView surfaceView = new CameraSurfaceView(CameraActivity.this, camera);
        cameraFrame.addView(surfaceView);

    }

    private void storeImageToCamera(Bitmap bitmap) {
        try {
            File appDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Batch8");

            if (!appDirectory.exists()) {
                appDirectory.mkdir();
            }

            File imgFile = new File(appDirectory, "IMG_" + System.currentTimeMillis() + ".png");
            FileOutputStream fos = new FileOutputStream(imgFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

            mIvPreviewImg.setImageBitmap(bitmap);

            camera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readImagesFromDevice(){
        Uri imageURI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[]{MediaStore.Images.Media.DATA};

        Cursor cursor = getApplicationContext().getContentResolver().query(imageURI, projection, null, null, null);

        ArrayList<String> images = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                images.add(image);
            }while(cursor.moveToNext());
        }
    }
}