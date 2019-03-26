package org.efit.mobile.helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import org.efit.mobile.R;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


/**
 * Created by TheMac on 10/8/17.
 */

public class DialogHelper {
    private Activity mActivity;
    public DialogHelper(){}
    public DialogHelper(Activity mActivity){
        this.mActivity=mActivity;
    }
    public void ShowErrorDialog(Context mcContext, String title, String msg) {
        new LovelyInfoDialog(mcContext)
                .setTopColorRes(R.color.color_type_wrong)
                .setIcon(R.drawable.warning)
                .setTitle(title)
                .setMessage(msg)
                .show();
    }

    public void ShowSuccessDialog(Context mcContext, String title, String msg) {
        new LovelyInfoDialog(mcContext)
                .setTopColorRes(R.color.selector_item_color)
                .setIcon(R.drawable.ic_checked_succes)
                .setTitle(title)
                .setMessage(msg)
                .show();
    }

    private String getindonesiaFormat(double angka) {
        DecimalFormat mataUangIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp.");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        mataUangIndonesia.setDecimalFormatSymbols(formatRp);
        return String.valueOf( mataUangIndonesia.format(angka));
    }
    public void ShowErrorDialogv2(Context mcContext, String title, String msg){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mActivity);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setPositiveButton("Tutup",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
