package com.example.shayan.hotchTest.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.shayan.hotchTest.dao.PersonDao;
import com.example.shayan.hotchTest.entity.Person;

import java.util.List;

public class PersonRepository {
    private PersonDao personDao;
    private LiveData<List<Person>> mAllPerso;

    public PersonRepository(Application application) {
        PersonAppDatabase db = PersonAppDatabase.getDatabase(application);
        personDao = db.personDao();
        mAllPerso = personDao.getAllPersons();
    }

    public LiveData<List<Person>> getAllPersons() {
        return mAllPerso;
    }


    public void insert(Person person) {
        new insertAsyncTask(personDao).execute(person);
    }

    public void delete(Person person) {
        new DeletePersonAsyncTask(personDao).execute(person);
    }

    public void update(Person person) {
        new UpdatePersonAsyncTask(personDao).execute(person);
    }

    private static class insertAsyncTask extends AsyncTask<Person, Void, Void> {

        private PersonDao mAsyncTaskDao;

        insertAsyncTask(PersonDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Person... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class UpdatePersonAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao personDao;

        private UpdatePersonAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... Persons) {
            personDao.update(Persons[0]);
            return null;
        }
    }

    private static class DeletePersonAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao personDao;

        private DeletePersonAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... Persons) {
            personDao.delete(Persons[0]);
            return null;
        }
    }
}
