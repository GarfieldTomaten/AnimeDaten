package com.marcwerner.animedaten;

import android.os.AsyncTask;
import android.util.Log;

public class InsertAsyncTask extends AsyncTask<AnimeSuche, Void, Void> {
    private NamenDao asyncNamenDao;

    InsertAsyncTask(NamenDao namenDao) {
        asyncNamenDao = namenDao;
    }

    @Override
    protected Void doInBackground(AnimeSuche... animes) {
        try {
            asyncNamenDao.insert(animes[0]);
            Log.d("InsertAsyncTask", "Insert successful: " + animes[0].getFirstName() + " " + animes[0].getLastName());
        } catch (Exception e) {
            Log.e("InsertAsyncTask", "Insert failed", e);
        }
        return null;
    }

}
