package com.deppon.nci.plugins.rsa;

import android.util.Log;

import android.util.Base64;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;



public class RSA extends CordovaPlugin {

  private CallbackContext _callbackContext;


    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        Log.e("RSA","action : init" );
    }

    @Override
    public boolean execute(String action, JSONArray args,CallbackContext callbackContext) throws JSONException {
        _callbackContext = callbackContext;
        String fname=args.getString(0);

        Log.e("RSA","action : " + action);
        Log.e("RSA","args : " + args);

        if("encrypt".equalsIgnoreCase(action)){
            try {

                Log.e("RSA","encrypt 开始!!!!!");

                fname = Base64.encodeToString(RSACoder.encryptByPrivateKey(RSACoder.encryptMD5WithSalt(fname.getBytes(),RSACoder.KEY_SALT),RSACoder.PUBLIC_KEY_DATA),Base64.DEFAULT);

                Log.e("RSA","encrypt 内容 : " + fname);

                Log.e("RSA","encrypt 结束!!!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

         _callbackContext.success(fname);

        return true;
    }


}