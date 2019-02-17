package com.example.roomdemo;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText nameupdate, name, mob, roll;
    Button add, update, view, delete, getone;
    Data data;
    DataDao dataDao;
    Database database;
    List<Data> dataList;
    List<Data> dataList1;
    long id;
    int res;
    int resupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameupdate = findViewById(R.id.nameupdate);
        name = findViewById(R.id.name);
        getone = findViewById(R.id.getone);
        mob = findViewById(R.id.mob);
        roll = findViewById(R.id.roll);
        add = findViewById(R.id.add);
        view = findViewById(R.id.view);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);

        database = Room.databaseBuilder(getApplicationContext(), Database.class, "Hello")
                .build();
        dataDao = database.dataDao();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iname = name.getText().toString();
                String iroll = roll.getText().toString();
                String imob = mob.getText().toString();

                new Insertion().execute(iname, iroll, imob);


            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Viewuser().execute();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = nameupdate.getText().toString();
                new deleteuser().execute(id);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = nameupdate.getText().toString();
                String iname = name.getText().toString();
                String iroll = roll.getText().toString();
                String imob = mob.getText().toString();

                new UpdateUser().execute(id, iname, iroll, imob);
            }
        });

        getone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = nameupdate.getText().toString();


                new Viewone().execute(id);

            }
        });


    }

    class Insertion extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... strings) {
            String iname = strings[0];
            String iroll = strings[1];
            String imob = strings[2];

            data = new Data(iname, iroll, imob);
            id = dataDao.insert(data);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (id == -1) {

                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            return null;
        }
    }

    class Viewuser extends AsyncTask<Void, Void, List<Data>> {


        @Override
        protected List<Data> doInBackground(Void... voids) {

            dataList = dataDao.getdata();

            return dataList;
        }

        @Override
        protected void onPostExecute(List<Data> idata) {
            super.onPostExecute(idata);

            StringBuffer stringBuffer = new StringBuffer();


            for (int i = 0; i < idata.size(); i++) {
                data = idata.get(i);
                String name = data.getName();
                String roll = data.getRoll();
                String mob = data.getMobile();
                int id = data.getId();
                stringBuffer.append(id + " " + name + " " + roll + " " + mob + "\n");


            }
            Toast.makeText(MainActivity.this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();

        }
    }

    class deleteuser extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... strings) {
            long id = Long.valueOf(strings[0]);

            res = dataDao.delete(id);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (res == 0) {

                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            return null;
        }
    }

    class UpdateUser extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... strings) {
            long id = Long.valueOf(strings[0]);
            String iname = strings[1];
            String iroll = strings[2];
            String imob = strings[3];

            resupdate = dataDao.update(id, iname, iroll, imob);


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (resupdate == 0) {

                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            return null;
        }
    }

    class Viewone extends AsyncTask<String, Void, List<Data>> {


        @Override
        protected List<Data> doInBackground(String... strings) {
            long id = Long.valueOf(strings[0]);
            dataList1 = dataDao.getonedata(id);

            return dataList1;
        }

        @Override
        protected void onPostExecute(List<Data> data1) {
            super.onPostExecute(data1);
            for (int i = 0; i < data1.size(); i++) {

                data = data1.get(i);
                String name = data.getName();
                String roll = data.getRoll();
                String mob = data.getMobile();
                int id = data.getId();
                Toast.makeText(MainActivity.this, id + " " + name + " " + roll + " " + mob, Toast.LENGTH_SHORT).show();


            }

        }
    }

}
