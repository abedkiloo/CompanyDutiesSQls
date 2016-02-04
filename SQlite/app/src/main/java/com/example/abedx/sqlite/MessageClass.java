package com.example.abedx.sqlite;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by abedx on 1/11/2016.
 */
public class MessageClass {
    public static void message(Context context, String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
