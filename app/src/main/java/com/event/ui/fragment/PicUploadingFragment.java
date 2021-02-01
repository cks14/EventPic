package com.event.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.event.R;
import com.event.ui.CameraUtils;
import com.event.ui.ImagePickerActivity;
import com.event.utils.LocalConstant;
import com.event.utils.RealPathUtil;
import com.event.webservice.HttpCallback;
import com.event.webservice.RestClient;
import com.event.webservice.model.ImageUpload;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.HeaderMap;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static com.facebook.FacebookSdk.getApplicationContext;

public class PicUploadingFragment extends Fragment {
    private String Pic1 = "";
    private String selectedPath;

    public static final String GALLERY_DIRECTORY_NAME = "HelloCamera";
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static final String IMAGE_EXTENSION = "jpg";
    public static final String VIDEO_EXTENSION = "mp4";

    public static final int REQUEST_IMAGE = 100;
    LinearLayout llApplPhoto;
    TextView tv_app_photo;
    private static String imageType = "";
    public static String type = "";
    ImageView image;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    public static final int BITMAP_SAMPLE_SIZE = 8;
    private static String imageStoragePath;
    int CAMERA = 1;
    File file = null;
    File file1;
    int image_type;

    public PicUploadingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_picuploading, container, false);
        initView(v);

        return v;
    }

    private void initView(View v) {
        llApplPhoto = (LinearLayout)v.findViewById(R.id.ll_appl_photo);
        tv_app_photo = (TextView) v.findViewById(R.id.tv_app_photo);
        image = (ImageView) v.findViewById(R.id.image);

        llApplPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                imageType = "1";
//                type = "photo1";
//                showImagePickerOptions();
                CAMERA = 1;
                CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).start(getContext(), PicUploadingFragment.this);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (CAMERA == 1) {
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    file = new File(resultUri.getPath());
                    AddImage(file);
                    // image_data = "available";
                    System.out.println("path===" + file);
                    Glide.with(PicUploadingFragment.this).load(file).into(image); //BankDetailsFragment.this

                }
            }
        }    }


    public void AddImage(File file) {

        RestClient.getInst().ImageUpload(tv_app_photo.getText().toString(),file).enqueue(new HttpCallback<ImageUpload>() {
            @Override
            public void onSuccess(Call<ImageUpload> call, Response<ImageUpload> response) {

                if (response.body().getStatus().equalsIgnoreCase("200")) {
//                    if (imageType.equals("1")) {
////                        Pic1 = response.body().getImage();
//                        tvAppPhoto.setText(Pic1);
//                        Glide.with(PicUploadingFragment.this).load(file).into(image);
//                    }



//                    Bundle bundle=new Bundle();
//                    bundle.putString("message", "file");
//                    //set Fragmentclass Arguments
//                    UploadedPictureFragment fragobj=new UploadedPictureFragment();
//                    fragobj.setArguments(bundle);


//                    tv_app_photo.setText(response.body().get);
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
            @Override
            public void onError(Call<ImageUpload> call, Throwable t) {

                Toast.makeText(getActivity(), "Something went wrong !", Toast.LENGTH_SHORT).show();
                System.out.println("dddd"+t.getMessage());

            }
        });
    }


//    private void captureImage() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE);
//        if (file != null) {
//            imageStoragePath = file.getAbsolutePath();
//        }
//
//        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);
//
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//
//        // start the image capture Intent
//        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
//    }
//
//
//    private void loadProfile(String url, Bitmap bitmap) {
//
//
//        if (imageType.equals("1")) {
//           // File file = bitmapToFile(bitmap);
//            AddImage(file);
//            Pic1 = url;
//        }
//    }
//

//    public File bitmapToFile(Bitmap bmp) {
//        try {
//            int size;
//            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024 * 2);
//            bmp.compress(Bitmap.CompressFormat.PNG, 80, bos);
//            byte[] bArr = bos.toByteArray();
//            bos.flush();
//            bos.close();
//
////            FileOutputStream fos = openFileOutput("mdroid.png", Context.MODE_PRIVATE);
////            fos.write(bArr);
////            fos.flush();
////            fos.close();
//
//            File mFile = new File(getFilesDir().getAbsolutePath(), "mdroid.png");
//            return mFile;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }

//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // if the result is capturing Image
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                // Refreshing the gallery
//                try {
//                    Uri uri = data.getParcelableExtra("path");
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
//                    loadProfile(uri.toString(), bitmap);
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            } else if (resultCode == RESULT_CANCELED) {
////                // user cancelled Image capture
////                Toast.makeText(getApplicationContext(),
////                        R.string.capture_image, Toast.LENGTH_SHORT)
////                        .show();
//            } else {
//                // failed to capture image
////                Toast.makeText(getApplicationContext(),
////                        R.string.failed_messge, Toast.LENGTH_SHORT)
////                        .show();
//            }
//        } else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                // Refreshing the gallery
//                CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);
//                Uri selectedImageUri = data.getData();
//                selectedPath = RealPathUtil.getRealPath(getActivity(), selectedImageUri);
//                File file = new File(String.valueOf(selectedPath));
//                // System.out.println("SELECT_VIDEO: " + file);
//                //  System.out.println("imageStoragePath==>" + file);
//                long length = file.length();
//                imageStoragePath = selectedPath;
////                Cursor cursor = MediaStore.Video.query(getContentResolver(), data.getData(),
////                        new String[]{MediaStore.Video.VideoColumns.DURATION});
////
////                cursor.moveToFirst();
//
//              //  String duration = cursor.getString(cursor.getColumnIndex("duration"));
//               // Double durations = (Double.parseDouble(duration)) / 1000;
////                if (durations < 60) {
////                   // Toast.makeText(getActivity(), R.string.minimum_file_size, Toast.LENGTH_SHORT).show();
////                }
////
////                else {
////                    AddImage(file);
////                }
//
//            } else if (resultCode == RESULT_CANCELED) {
//                // user cancelled recording
////                Toast.makeText(getApplicationContext(),
////                        R.string.recording, Toast.LENGTH_SHORT)
////                        .show();
//            } else {
//                // failed to record video
////                Toast.makeText(getApplicationContext(),
////                        R.string.faied_recording, Toast.LENGTH_SHORT)
////                        .show();
//            }
//        }
//    }
//
//
//    private void showImagePickerOptions() {
//        launchCameraIntent();
//
//    }
//
//    private void launchCameraIntent() {
//
//        if (imageType.equals("1")) {
//            Intent intent = new Intent(getActivity(), ImagePickerActivity.class);
//            intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);
//
//            // setting aspect ratio
//            intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
//            intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
//            intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
//
//            // setting maximum bitmap width and height
//            intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
//            intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 560);
//            intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 560);
//
//            startActivityForResult(intent, REQUEST_IMAGE);
//        }
//
//    }


}