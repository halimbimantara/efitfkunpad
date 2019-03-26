package org.efit.mobile.utility.pushnotif;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;
/**
 * Created by ThinkPad T430 on 16/11/2017.
 */

public class FirebaseIDService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseIDService";
    @Override
    public void onNewToken(String refreshedToken) {
        super.onNewToken(refreshedToken);
        Log.e("NEW_TOKEN",refreshedToken);
        CheckToken(refreshedToken);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }

    private void storeRegIdInPref(String token) {
        Sharepref.saveString(Constant.FIREBASEID, token);
    }
    private void CheckToken(String token){
        boolean update_token=true;
        String token_old=Sharepref.getString(Constant.FIREBASEID) != null ? Sharepref.getString(Constant.FIREBASEID):"";
        if (token_old.isEmpty()){
            storeRegIdInPref(token);
        }else{
            if (token.equals(Sharepref.getString(Constant.FIREBASEID))){
                /**
                 * Update Token Di Server
                 */
                Log.d(TAG,"reNewTokenOnServer");
            }
        }
    }
}