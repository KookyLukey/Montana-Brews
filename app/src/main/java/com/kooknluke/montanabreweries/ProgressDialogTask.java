package com.kooknluke.montanabreweries;

import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * Created by Dan Poss on 6/22/2016.
 */

class ProgressDialogTask extends AsyncTask<Void, Void, Void> {
    private ProgressDialog dialog;

    public ProgressDialogTask(Beer activity) {
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Doing something, please wait.");
        dialog.show();
    }

    @Override
    protected void onPostExecute(Void result) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

}