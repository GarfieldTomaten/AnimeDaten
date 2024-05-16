package com.marcwerner.animedaten;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button bt_save;
    Button bt_search;
    Button bt_update;

    Button bt_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_ausgabe;
        tv_ausgabe = findViewById(R.id.tv_ausgabe);
        EditText firstname = findViewById(R.id.firstname);
        EditText lastname = findViewById(R.id.lastname);
        bt_save = findViewById(R.id.bt_save);
        bt_search = findViewById(R.id.bt_search);
        bt_update = findViewById(R.id.bt_update);
        bt_delete = findViewById(R.id.bt_delete);

        final AppDatabase database = AppDatabase.getDatabase(this);
        final NamenDao namenDao = database.namenDao();

        bt_save.setOnClickListener(v -> {
            String fName = firstname.getText().toString();
            String lName = lastname.getText().toString();
            AnimeSuche Anime = new AnimeSuche(fName, lName);
            new InsertAsyncTask(namenDao).execute(Anime);
        });

        bt_search.setOnClickListener(v -> {
            String fName = firstname.getText().toString();
            String lName = lastname.getText().toString();
            namenDao.getAnimeByFullName(fName, lName).observe(this, new Observer<List<AnimeSuche>>() {
                @Override
                public void onChanged(List<AnimeSuche> Animes) {
                    if (!Animes.isEmpty()) {
                        AnimeSuche Anime = Animes.get(Animes.size() - 1);
                        tv_ausgabe.setText(Anime.getFirstName() + " " + Anime.getLastName());
                    }
                }
            });
        });
        bt_delete.setOnClickListener(v -> {
            String fName = firstname.getText().toString();
            String lName = lastname.getText().toString();
            AnimeSuche Anime = new AnimeSuche(fName, lName);
            new DeleteAsyncTask(namenDao).execute(Anime);
        });

    }

    private static class InsertAsyncTask extends AsyncTask<AnimeSuche, Void, Void> {
        private NamenDao asyncNamenDao;

        InsertAsyncTask(NamenDao namenDao) {
            asyncNamenDao = namenDao;
        }

        @Override
        protected Void doInBackground(AnimeSuche... animes) {
            asyncNamenDao.insert(animes[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<AnimeSuche, Void, Void> {
        private NamenDao asyncUserDao;
        private AnimeSuche asyncNamenDao;

        DeleteAsyncTask(NamenDao namenDao) {
            asyncNamenDao = (AnimeSuche) namenDao;         }
        public Void doInBackground(AnimeSuche... animes) {
            asyncNamenDao.delete(animes[0]);
            return null;
        }
    }
}
