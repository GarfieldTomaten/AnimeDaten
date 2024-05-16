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
        final UserDao userDao = database.userDao();

        bt_save.setOnClickListener(v -> {
            String fName = firstname.getText().toString();
            String lName = lastname.getText().toString();
            User user = new User(fName, lName);
            new InsertAsyncTask(userDao).execute(user);
        });

        bt_search.setOnClickListener(v -> {
            String fName = firstname.getText().toString();
            String lName = lastname.getText().toString();
            userDao.getUserByFullName(fName, lName).observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {
                    if (!users.isEmpty()) {
                        User user = users.get(users.size() - 1);
                        tv_ausgabe.setText(user.getFirstName() + " " + user.getLastName());
                    }
                }
            });
        });
        bt_delete.setOnClickListener(v -> {
            String fName = firstname.getText().toString();
            String lName = lastname.getText().toString();
            User user = new User(fName, lName);
            new DeleteAsyncTask(userDao).execute(user);
        });

    }

    private static class InsertAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao asyncUserDao;

        InsertAsyncTask(UserDao userDao) {
            asyncUserDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            asyncUserDao.insert(users[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao asyncUserDao;
        DeleteAsyncTask(UserDao userDao) {
            asyncUserDao = userDao;         }
        public Void doInBackground(User... users) {
            asyncUserDao.delete(users[0]);
            return null;
        }
    }
}
