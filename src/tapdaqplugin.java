package com.creativeads.tapdaq;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import com.tapdaq.sdk.*;
import com.tapdaq.sdk.common.*;
import com.tapdaq.sdk.listeners.*;

public class tapdaqplugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals("init")) {    
           // super.onCreate(savedInstanceState);
            Log.d("oliver", "init 1");
            TapdaqConfig config = new TapdaqConfig();
            config.setUserSubjectToGDPR(STATUS.TRUE); //GDPR declare if user is in EU
            config.setConsentGiven(true); //GDPR consent must be obtained from the user
            config.setIsAgeRestrictedUser(false); //Is user subject to COPPA or GDPR age restrictions
            Log.d("oliver", "init 2");
            Tapdaq.getInstance().initialize(this, "5d4317fcf998189c96860b11", "fc2b45a9-7052-4d91-bd3a-2d7b8757bce4", config, new TapdaqInitListener());
            Log.d("oliver", "init 3");
        }
        
        if (action.equals("performAdd")) {
            int arg1 = args.getInt(0);
            int arg2 = args.getInt(1);
            /* Indicating success is failure is done by calling the appropriate method on the 
            callbackContext.*/
            int result = arg1 + arg2;
            callbackContext.success("result calculated in Java: " + result);
            return true;
        }
        return false;
    }
    
    public void didInitialise() {
        super.didInitialise();
        // Ads may now be requested
        Log.d("oliver", "initialisiert");
        
    }
}
