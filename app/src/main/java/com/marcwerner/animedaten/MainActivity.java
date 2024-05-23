package com.marcwerner.animedaten;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button bt_save;
    Button bt_search;
    Button bt_update;
    Button bt_delete;
    ImageView animeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_ausgabe = findViewById(R.id.tv_ausgabe);
        EditText firstname = findViewById(R.id.firstname);
        EditText lastname = findViewById(R.id.lastname);
        bt_save = findViewById(R.id.bt_save);
        bt_search = findViewById(R.id.bt_search);
        bt_update = findViewById(R.id.bt_update);
        bt_delete = findViewById(R.id.bt_delete);
        animeImage = findViewById(R.id.anime_image);

        final AppDatabase database = AppDatabase.getDatabase(this);
        final NamenDao namenDao = database.namenDao();

        bt_save.setOnClickListener(v -> {
            String fName = firstname.getText().toString();
            String lName = lastname.getText().toString();
            AnimeSuche anime = new AnimeSuche(fName, lName);
            new InsertAsyncTask(namenDao).execute(anime);
        });

        bt_search.setOnClickListener(v -> {
            String fName = firstname.getText().toString();
            String lName = lastname.getText().toString();
            namenDao.getAnimeByFullName(fName, lName).observe(this, new Observer<List<AnimeSuche>>() {
                @Override
                public void onChanged(List<AnimeSuche> animes) {
                    if (!animes.isEmpty()) {
                        AnimeSuche anime = animes.get(animes.size() - 1);
                        tv_ausgabe.setText(anime.getFirstName() + " " + anime.getLastName());
                        showAnimeImage(anime.getFirstName(), anime.getLastName());
                    } else {
                        tv_ausgabe.setText("No record found");
                        animeImage.setVisibility(ImageView.GONE);
                    }
                }
            });
        });

        bt_delete.setOnClickListener(v -> {
            String fName = firstname.getText().toString();
            String lName = lastname.getText().toString();
            AnimeSuche anime = new AnimeSuche(fName, lName);
            new DeleteAsyncTask(namenDao, tv_ausgabe, animeImage).execute(anime);
        });
    }

    private void showAnimeImage(String firstName, String lastName) {
        // Mapping logic for firstName and lastName to image resources
        int imageResId = getImageResource(firstName, lastName);
        if (imageResId != 0) {
            animeImage.setImageResource(imageResId);
            animeImage.setVisibility(ImageView.VISIBLE);
        } else {
            animeImage.setVisibility(ImageView.GONE);
            Toast.makeText(this, "No image found for " + firstName + " " + lastName, Toast.LENGTH_SHORT).show();
        }
    }

    private int getImageResource(String firstName, String lastName) {
        // This is just an example, you should replace this with your actual logic
        if (firstName.equalsIgnoreCase("Naruto") && lastName.equalsIgnoreCase("Uzumaki")) {
            return R.drawable.eins;
        } else if (firstName.equalsIgnoreCase("Sasuke") && lastName.equalsIgnoreCase("Uchiha")) {
            return R.drawable.eins;
        } else if (firstName.equalsIgnoreCase("Sakura") && lastName.equalsIgnoreCase("Haruno")) {
            return R.drawable.eins;
        } else if (firstName.equalsIgnoreCase("Kakashi") && lastName.equalsIgnoreCase("Hatake")) {
            return R.drawable.eins;
        } else if (firstName.equalsIgnoreCase("William James") && lastName.equalsIgnoreCase("Moriaty")) {
            return R.drawable.zwei;
        } else if (firstName.equalsIgnoreCase("Sherlock") && lastName.equalsIgnoreCase("Holmes")) {
            return R.drawable.zwei;
        } else if (firstName.equalsIgnoreCase("Luffy") && lastName.equalsIgnoreCase("Monkey D")) {
            return R.drawable.drei;
        } else if (firstName.equalsIgnoreCase("Zoro") && lastName.equalsIgnoreCase("Roronoa")) {
            return R.drawable.drei;
        } else if (firstName.equalsIgnoreCase("Sanji") && lastName.equalsIgnoreCase("Vinsmoke")) {
            return R.drawable.drei;
        } else if (firstName.equalsIgnoreCase("Eren") && lastName.equalsIgnoreCase("Jaeger")) {
            return R.drawable.vier;
        } else if (firstName.equalsIgnoreCase("Mikasa") && lastName.equalsIgnoreCase("Ackerman")) {
            return R.drawable.vier;
        } else if (firstName.equalsIgnoreCase("Armin") && lastName.equalsIgnoreCase("Alert")) {
            return R.drawable.vier;
        } else if (firstName.equalsIgnoreCase("Ichigo") && lastName.equalsIgnoreCase("Kurosaki")) {
            return R.drawable.fuenf;
        } else if (firstName.equalsIgnoreCase("Rukia") && lastName.equalsIgnoreCase("Kuchiki")) {
            return R.drawable.fuenf;
        } else if (firstName.equalsIgnoreCase("Aizen") && lastName.equalsIgnoreCase("Sosuke")) {
            return R.drawable.fuenf;
        } else if (firstName.equalsIgnoreCase("Tyson") && lastName.equalsIgnoreCase("Granger")) {
            return R.drawable.sechs;
        } else if (firstName.equalsIgnoreCase("Goku") && lastName.equalsIgnoreCase("Son")) {
            return R.drawable.sieben;
        } else if (firstName.equalsIgnoreCase("Vegeta") && lastName.equalsIgnoreCase("Brief")) {
            return R.drawable.sieben;
        } else if (firstName.equalsIgnoreCase("Bulma") && lastName.equalsIgnoreCase("Breif")) {
            return R.drawable.sieben;
        } else if (firstName.equalsIgnoreCase("Jotaro") && lastName.equalsIgnoreCase("Kujo")) {
            return R.drawable.acht;
        } else if (firstName.equalsIgnoreCase("Joseph") && lastName.equalsIgnoreCase("Joestar")) {
            return R.drawable.acht;
        } else if (firstName.equalsIgnoreCase("Dio") && lastName.equalsIgnoreCase("Brando")) {
            return R.drawable.acht;
        } else if (firstName.equalsIgnoreCase("Gon") && lastName.equalsIgnoreCase("Freecs")) {
            return R.drawable.neun;
        } else if (firstName.equalsIgnoreCase("Killua") && lastName.equalsIgnoreCase("Zoldyck")) {
            return R.drawable.neun;
        } else if (firstName.equalsIgnoreCase("Hisoka") && lastName.equalsIgnoreCase("Morow")) {
            return R.drawable.neun;
        } else if (firstName.equalsIgnoreCase("Thorfinn") && lastName.equalsIgnoreCase("Karlsefni")) {
            return R.drawable.zehn;
        } else if (firstName.equalsIgnoreCase("Askeladd") && lastName.equalsIgnoreCase("")) {
            return R.drawable.zehn;
        } else if (firstName.equalsIgnoreCase("Thors") && lastName.equalsIgnoreCase("")) {
            return R.drawable.zehn;
        }
        return 0;
    }

    private static class InsertAsyncTask extends AsyncTask<AnimeSuche, Void, Void> {
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

    private static class DeleteAsyncTask extends AsyncTask<AnimeSuche, Void, Void> {
        private NamenDao asyncNamenDao;
        private TextView tv_ausgabe;
        private ImageView animeImage;

        DeleteAsyncTask(NamenDao namenDao, TextView tv_ausgabe, ImageView animeImage) {
            asyncNamenDao = namenDao;
            this.tv_ausgabe = tv_ausgabe;
            this.animeImage = animeImage;
        }

        @Override
        protected Void doInBackground(AnimeSuche... animes) {
            try {
                asyncNamenDao.delete(animes[0]);
                Log.d("DeleteAsyncTask", "Delete successful: " + animes[0].getFirstName() + " " + animes[0].getLastName());
            } catch (Exception e) {
                Log.e("DeleteAsyncTask", "Delete failed", e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            tv_ausgabe.setText("No record found");
            animeImage.setVisibility(ImageView.GONE);
        }
    }
}
