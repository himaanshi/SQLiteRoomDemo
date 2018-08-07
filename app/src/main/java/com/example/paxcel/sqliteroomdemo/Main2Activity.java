package com.example.paxcel.sqliteroomdemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    EditText et_first, et_last, et_age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et_first = findViewById(R.id.et_first);
        et_last = findViewById(R.id.et_last);
        et_age = findViewById(R.id.et_age);


        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(MainActivity.this));

                User user = new User();
                user.setFirstName(et_first.getText().toString());
                user.setLastName(et_last.getText().toString());
                user.setAge(Integer.parseInt(et_age.getText().toString()));
                new Main2Activity.PopulateDbAsync(Main2Activity.this,AppDatabase.getAppDatabase(Main2Activity.this), user).execute();
            }
        });


    }



    public void naviagte(User user) {
        setResult(1, new Intent().putExtra("user", user.getFirstName()));
        finish();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        Main2Activity main2Activity;
        User user;

        PopulateDbAsync(Main2Activity main2Activity, AppDatabase db, User user) {
            mDb = db;
            this.main2Activity=main2Activity;
            this.user = user;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("DatabaseInitializer", "onPostExecute");
            main2Activity.naviagte(user);

        }

        @Override
        protected Void doInBackground(final Void... params) {

            mDb.userDao().insertAll(user);
            List<User> userList = mDb.userDao().getAll();
            Log.d("DatabaseInitializer", "RowsCount: " + userList.size());

            return null;
        }

    }


}
