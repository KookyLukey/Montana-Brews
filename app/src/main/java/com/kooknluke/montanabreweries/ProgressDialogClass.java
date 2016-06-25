package com.kooknluke.montanabreweries;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Dan Poss on 6/21/2016.
 * @ProgressDialogClass
 *      This class is to be used to instantiate the Progress Dialog as well as
 *      set the message to be displayed and then dismiss it using destroyPD().
 */

public class ProgressDialogClass {

    private ProgressDialog progress;

    public void setPD(Context context) {
        progress = new ProgressDialog(context);
    }

    public void setMessagePD() {
        progress.setTitle("Loading");
        progress.setMessage("Fetching...");
    }

    public void showPD() {
        progress.show();
    }

    public ProgressDialog getPD() {
        return progress;
    }

    public void destroyPD() {
        progress.dismiss();
    }
}
