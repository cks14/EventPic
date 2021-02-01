package com.event.utils;

import android.content.Context;

public class SharedPreferences {


    public static String getIsLogin(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("is_login", Context.MODE_PRIVATE);
        return sharedPreferences.getString("login","");
    }

    public static void setIsLogin(Context context, String examCode){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("is_login", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("login",examCode);
        editor.clear();
        editor.commit();
        editor.apply();
    }



    public static String getExamCode(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("exam_code", Context.MODE_PRIVATE);
        return sharedPreferences.getString("exam","");
    }

    public static void storeExamCode(Context context, String examCode){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("exam_code", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("exam",examCode);
        editor.apply();
    }




    public static String getDOB(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("user_dob", Context.MODE_PRIVATE);
        return sharedPreferences.getString("dob","");
    }

    public static void storeDOB(Context context, String video_id){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("user_dob", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("dob",video_id);
        editor.apply();
    }





    public static String getVideoId(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("video_id", Context.MODE_PRIVATE);
        return sharedPreferences.getString("video","");
    }

    public static void storeVideoId(Context context, String video_id){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("video_id", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("video",video_id);
        editor.apply();
    }


    public static String getQuestionId(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("question_id", Context.MODE_PRIVATE);
        return sharedPreferences.getString("question","");
    }

    public static void storeQuestionId(Context context, String welcome){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("question_id", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("question",welcome);
        editor.apply();
    }



    public static String getImage(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("user_image", Context.MODE_PRIVATE);
        return sharedPreferences.getString("image","");
    }

    public static void storeImage(Context context, String image){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("user_image", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("image",image);
        editor.apply();
    }





    public static String getWelcomScreen(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("welcome_screen", Context.MODE_PRIVATE);
        return sharedPreferences.getString("welcome","");
    }

    public static void storeWelcomeScreen(Context context, String welcome){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("welcome_screen", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("welcome",welcome);
        editor.apply();
    }



    public static String getVideoTitle(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("video_title", Context.MODE_PRIVATE);
        return sharedPreferences.getString("title","");
    }

    public static void storeVideoTitle(Context context, String title){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("video_title", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("title",title);
        editor.apply();
    }



    public static String getVideoDesc(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("video_desc", Context.MODE_PRIVATE);
        return sharedPreferences.getString("desc","");
    }

    public static void storeVideoDesc(Context context, String desc){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("video_desc", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("desc",desc);
        editor.apply();
    }




    public static String getVideoUrl(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("video_url", Context.MODE_PRIVATE);
        return sharedPreferences.getString("url","");
    }

    public static void storeVideoUrl(Context context, String url){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("video_url", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("url",url);
        editor.apply();
    }





    public static String getUsername(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("user_name", Context.MODE_PRIVATE);
        return sharedPreferences.getString("username","");
    }

    public static void storeUsername(Context context, String username){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("user_name", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("username",username);
        editor.apply();
    }

    public static String getUserEmail(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("user_email", Context.MODE_PRIVATE);
        return sharedPreferences.getString("email","");
    }

    public static void storeUserEmail(Context context, String email){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("user_email", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("email",email);
        editor.apply();
    }

    public static String getMobile(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("mobile_number", Context.MODE_PRIVATE);
        return sharedPreferences.getString("mobile","");
    }

    public static void storeMobile(Context context, String mobile){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("mobile_number", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("mobile",mobile);
        editor.apply();
    }



    public static String getUserPassword(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("user_password", Context.MODE_PRIVATE);
        return sharedPreferences.getString("pass","");
    }

    public static void storeUserPassword(Context context, String pass){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("user_password", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("pass",pass);
        editor.apply();
    }

    public static String getAddress(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("user_address", Context.MODE_PRIVATE);
        return sharedPreferences.getString("address","");
    }

    public static void storeAddress(Context context, String address){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("user_address", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("address",address);
        editor.apply();
    }

    public static String getuserId(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("user_id", Context.MODE_PRIVATE);
        return sharedPreferences.getString("userid","");
    }

    public static void storeUserId(Context context, String userid){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("user_id", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("userid",userid);
        editor.apply();
    }

    












    public static String getChapterNumbers(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("chapter_number", Context.MODE_PRIVATE);
        return sharedPreferences.getString("chapter","");
    }

    public static void storeChapterNumbers(Context context, String chapterNo){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("chapter_number", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("chapter",chapterNo);
        editor.apply();
    }
    public static String getTotalTests(Context context){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("total_test", Context.MODE_PRIVATE);
        return sharedPreferences.getString("total","");
    }

    public static void storeTotalTests(Context context, String totalTest){
        android.content.SharedPreferences sharedPreferences=context.getSharedPreferences("total_test", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("total",totalTest);
        editor.apply();
    }
}
