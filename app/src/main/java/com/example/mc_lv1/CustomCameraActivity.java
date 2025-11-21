package com.example.mc_lv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomCameraActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CAMERA_PERMISSION = 200;
    private static final String[] PERMISSIONS = {Manifest.permission.CAMERA};

    private int currentLensFacing = CameraSelector.LENS_FACING_BACK;

    private static final String TAG = "CustomCamera";
    private static final String FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";

    private Button cameraCaptureButton;
    private PreviewView viewFinder;
    private ExecutorService executor;
    private ImageCapture imageCapture;

    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_camera);


        cameraCaptureButton = findViewById(R.id.image_capture_button);
        viewFinder = findViewById(R.id.viewFinder);

        if (checkPermission()) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CODE_CAMERA_PERMISSION);
        }

        // Okretanje kamere, prati koja kamera je trenutno aktivna
        Button switchCameraButton = findViewById(R.id.btnSwitchCamera);
        switchCameraButton.setOnClickListener(v -> {
            if (currentLensFacing == CameraSelector.LENS_FACING_BACK) {
                currentLensFacing = CameraSelector.LENS_FACING_FRONT;
            } else {
                currentLensFacing = CameraSelector.LENS_FACING_BACK;
            }
            startCamera();
        });

        cameraCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
            }
        });
    }

    private boolean checkPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "You need to grant camera permission to use this feature", Toast.LENGTH_LONG).show();
                finish();
            } else {
                startCamera();
            }
        }
    }

    private void startCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindPreview(cameraProvider);
                } catch (ExecutionException | InterruptedException e) {
                    Log.e(TAG, "Error in camera startup", e);
                }
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void bindPreview(ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder()
                .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                .build();

        // Selektor kamere
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(currentLensFacing)
                .build();

        executor = Executors.newSingleThreadExecutor();
        imageCapture = new ImageCapture.Builder().build();

        cameraProvider.unbindAll();
        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview, imageCapture);

        preview.setSurfaceProvider(viewFinder.getSurfaceProvider());
    }

    private void captureImage() {
        File photoFile = new File(getBaseContext().getExternalCacheDir() + File.separator + System.currentTimeMillis() + ".png");
        String name = new SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis());

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image");
        }

        ImageCapture.OutputFileOptions outputOptions = new ImageCapture.OutputFileOptions.Builder(getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues).build();
        imageCapture.takePicture(outputOptions, executor,
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
                        if (outputFileResults != null) {

                            Uri savedUri = outputFileResults.getSavedUri();

                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("photo_path", savedUri.toString());
                            setResult(RESULT_OK, resultIntent);
                            finish();
                        } else {
                            Log.e(TAG, "Failed to save image.");
                        }
                    }

                    @Override
                    public void onError(ImageCaptureException exception) {
                        Log.e(TAG, "Error capturing image: " + exception.getMessage());
                    }
                });
    }
}