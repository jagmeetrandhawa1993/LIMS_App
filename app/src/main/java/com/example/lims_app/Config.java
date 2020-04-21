package com.example.lims_app;


import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;



public class Config {

    private static final String ROOT_URL = "http://192.168.5.26/LIMS4/api/";


    public static final String URL_LOGIN = ROOT_URL+"userlogin.php";
    public static final String URL_REGISTER = ROOT_URL+"userregister.php";
    public static final String URL_ADDBATCH = ROOT_URL+"addbatch.php";

}
