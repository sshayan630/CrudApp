package com.example.shayan.hotchTest.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shayan.hotchTest.R;
import com.example.shayan.hotchTest.adapter.PersonListAdapter;
import com.example.shayan.hotchTest.adapter.RecyclerItemClickListener;
import com.example.shayan.hotchTest.entity.Person;
import com.example.shayan.hotchTest.utils.ApplicationStrings;
import com.example.shayan.hotchTest.viewmodel.PersonViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_WORD_ACTIVITY_REQUEST_CODE = 2;

    private PersonViewModel mPersonViewModel;
    private Button editButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PersonListAdapter adapter = new PersonListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Get a new or existing ViewModel from the ViewModelProvider.
        mPersonViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mPersonViewModel.getAllPersons().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable final List<Person> persons) {
                // Update the cached copy of the words in the adapter.
                adapter.setmPersons(persons);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewPersonActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, final int position) {
                        editButton = v.findViewById(R.id.button_edit);
                        //Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
                        deleteButton = v.findViewById(R.id.button_delete);

                        deleteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Delete button is called " + adapter.getProductAt(position).getPersonName(), Toast.LENGTH_SHORT).show();

                                mPersonViewModel.delete(adapter.getProductAt(position));
                            }
                        });

                        editButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Edit button is called " + adapter.getProductAt(position).getPersonName(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, NewPersonActivity.class);
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_PERSON_ID, adapter.getProductAt(position).getPersonId());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_PERSON_NAME, adapter.getProductAt(position).getPersonName());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_PERSON_EMAIL, adapter.getProductAt(position).getPersonEmail());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_PERSON_INTERST, adapter.getProductAt(position).getPersonInterest());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_PERSON_NUMBER, adapter.getProductAt(position).getPersonNumber());
                                startActivityForResult(intent, EDIT_WORD_ACTIVITY_REQUEST_CODE);
                            }
                        });
                    }
                })
        );


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String personName = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PERSON_NAME);
            String personEmail = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PERSON_EMAIL);
            String personNumber = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PERSON_NUMBER);
            String personInterst = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PERSON_INTERST);

            Person person = new Person(personName, personEmail, personInterst, System.currentTimeMillis(), personNumber, true);
            mPersonViewModel.insert(person);
        } else if (requestCode == EDIT_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            int id = data.getIntExtra(ApplicationStrings.EXTRA_REPLY_PERSON_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Person can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String personName = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PERSON_NAME);
            String personEmail = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PERSON_EMAIL);
            String personNumber = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PERSON_NUMBER);
            String personInterst = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PERSON_INTERST);

            Person person = new Person(personName, personEmail, personInterst, System.currentTimeMillis(), personNumber, true);
            person.setPersonId(id);
            mPersonViewModel.update(person);

            Toast.makeText(this, "Person updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Person not saved", Toast.LENGTH_SHORT).show();
        }
    }



}
