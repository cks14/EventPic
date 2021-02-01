package com.event.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalConstant {

    private static LocalConstant localConstant;
    private Context context;

    private SharedPreferences preferences;

    public LocalConstant(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static LocalConstant getInstance(Context context) {
        if (localConstant == null) {
            localConstant = new LocalConstant(context);
        }
        return localConstant;
    }

    public Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }


    public void watchYoutubeVideo(Context context, String youTubeUrl) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + extractVideoIdFromUrl(youTubeUrl)));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + extractVideoIdFromUrl(youTubeUrl)));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    final String youTubeUrlRegEx = "^(https?)?(://)?(www.)?(m.)?((youtube.com)|(youtu.be))/";
    final String[] videoIdRegex = {"\\?vi?=([^&]*)", "watch\\?.*v=([^&]*)", "(?:embed|vi?)/([^/?]*)", "^([A-Za-z0-9\\-]*)"};

    private String extractYTId(String url) {
        Pattern compiledPattern = Pattern.compile(youTubeUrlRegEx);
        Matcher matcher = compiledPattern.matcher(url);

        if (matcher.find()) {
            return url.replace(matcher.group(), "");
        }
        return url;
    }

    public String extractVideoIdFromUrl(String url) {
        String youTubeLinkWithoutProtocolAndDomain = extractYTId(url);

        for (String regex : videoIdRegex) {
            Pattern compiledPattern = Pattern.compile(regex);
            Matcher matcher = compiledPattern.matcher(youTubeLinkWithoutProtocolAndDomain);

            if (matcher.find()) {
                return matcher.group(1);
            }
        }

        return null;
    }

    public boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void keyboardHide(Context context, EditText editText) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getBitmapUrl(String videoPath)
            throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            else
                mediaMetadataRetriever.setDataSource(videoPath);

            bitmap = mediaMetadataRetriever.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable(
                    "Exception in retriveVideoFrameFromVideo(String videoPath)"
                            + e.getMessage());

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }


    public Uri getLocalBitmapUri(Bitmap bmp, Activity activity) {
        Uri bmpUri = null;
        try {
            bmpUri = getCroppedUri(bmp, activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    private Uri getCroppedUri(Bitmap bmp, Activity activity) {
        Uri photoURI = null;
        File photoFile = null;
        try {
            photoFile = createImageFile(activity, bmp);
        } catch (IOException ex) {
            // Error occurred while creating the File
        }

        if (photoFile != null) {
            photoURI = FileProvider.getUriForFile(activity,
                    activity.getPackageName() + ".android.fileprovider",
                    photoFile);
        }
        return photoURI;
    }

    private File createImageFile(Activity activity, Bitmap bmp) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );

        FileOutputStream out = new FileOutputStream(image);
        bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
        out.close();

        // Save a file: path for use with ACTION_VIEW intents
        //mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    public void errorDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //builder.setTitle("Breeding");
        builder.setMessage(message);
        builder.setPositiveButton("ok", null);
        //builder.setNegativeButton("cancel", null);
        builder.show();
    }

    private String isLogin = "isLogin";
    private String userID = "userID";
    private String userName = "userName";
    private String userMobile = "userMobile";
    private String userEmail = "userEmail";
    private String userImage = "userImage";
    private String userWallet = "userWallet";
    private String userReferal = "userReferal";

    private String key = "key";
    private String delivery_type = "delivery_type";
    private String delivery_location = "delivery_location";
    private String deliveryID = "deliveryID";
    private String addressSelectionType = "addressSelectionType";
    private String welcome = "welcome";

    private String code = "code";

    private String isReferal = "isReferal";

    private String token = "token";

    public String getToken() {
        return preferences.getString(token,"");
    }

    public void setToken(String token) {
        preferences.edit().putString(this.token,token).commit();
    }


    public String getIsReferal() {
        return preferences.getString(isReferal,"");
    }

    public void setIsReferal(String isReferal) {
        preferences.edit().putString(this.isReferal,isReferal).commit();
    }

    public int getCode() {
        return preferences.getInt(code,0);
    }

    public void setCode(int code) {
        preferences.edit().putInt(this.code,code).commit();
    }


    public String getAddressSelectionType() {
        return preferences.getString(addressSelectionType,"");
    }

    public void setAddressSelectionType(String addressSelectionType) {
        preferences.edit().putString(this.addressSelectionType,addressSelectionType).commit();
    }

    public String getDeliveryID() {
        return preferences.getString(deliveryID,"");
    }

    public void setDeliveryID(String deliveryID) {
        preferences.edit().putString(this.deliveryID,deliveryID).commit();
    }


    public String getDelivery_location() {
        return preferences.getString(delivery_location,"");
    }

    public void setDelivery_location(String delivery_location) {
        preferences.edit().putString(this.delivery_location,delivery_location).commit();
    }

    public String getDelivery_type() {
        return preferences.getString(delivery_type,"");
    }

    public void setDelivery_type(String delivery_type) {
        preferences.edit().putString(this.delivery_type,delivery_type).commit();
    }


    public String getKey() {
        return preferences.getString(key,"");
    }

    public void setKey(String key) {
        preferences.edit().putString(this.key,key).commit();
    }

    public String getIsLogin() {
        return preferences.getString(isLogin,"");
    }

    public void setIsLogin(String isLogin) {
        preferences.edit().putString(this.isLogin,isLogin).commit();
    }
    public String getUserWallet() {
        return preferences.getString(userWallet,"");
    }

    public void setUserWallet(String userWallet) {
        preferences.edit().putString(this.userWallet,userWallet).commit();
    }
    public String getUserReferal() {
        return preferences.getString(userReferal,"");
    }

    public void setUserReferal(String userReferal) {
        preferences.edit().putString(this.userReferal,userReferal).commit();
    }

    public String getUserImage() {
        return preferences.getString(userImage,"");
    }

    public void setUserImage(String userImage) {
        preferences.edit().putString(this.userImage,userImage).commit();
    }

    public String getUserEmail() {
        return preferences.getString(userEmail,"");
    }

    public void setUserEmail(String userEmail) {
        preferences.edit().putString(this.userEmail,userEmail).commit();
    }

    public String getUserMobile() {
        return preferences.getString(userMobile,"");
    }

    public void setUserMobile(String userMobile) {
        preferences.edit().putString(this.userMobile,userMobile).commit();
    }

    public String getUserName() {
        return preferences.getString(userName,"");
    }

    public void setUserName(String userName) {
        preferences.edit().putString(this.userName,userName).commit();
    }

    public String getUserId() {
        return preferences.getString(userID,"");
    }

    public void setUserId(String userId) {
        preferences.edit().putString(this.userID,userId).commit();
    }

    public String getWelcome() {
        return preferences.getString(welcome,"");
    }

    public void setWelcome(String welcome) {
        preferences.edit().putString(this.welcome,welcome).commit();


    }
}
