package com.example.shayan.hotchTest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.shayan.hotchTest.database.PersonRepository;
import com.example.shayan.hotchTest.entity.Person;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    private PersonRepository mRepository;

    private LiveData<List<Person>> mAllPerson;

    public PersonViewModel(Application application) {
        super(application);
        mRepository = new PersonRepository(application);
        mAllPerson = mRepository.getAllPersons();
    }

    public LiveData<List<Person>> getAllPersons() { return mAllPerson; }

    public void insert(Person product) { mRepository.insert(product); }
    public void update(Person product) { mRepository.update(product); }
    public void delete(Person product) { mRepository.delete(product); }
}