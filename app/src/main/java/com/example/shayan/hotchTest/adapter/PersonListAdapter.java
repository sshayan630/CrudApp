package com.example.shayan.hotchTest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shayan.hotchTest.R;
import com.example.shayan.hotchTest.entity.Person;
import com.example.shayan.hotchTest.viewmodel.PersonViewModel;

import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.ProductViewHolder> {
    private PersonViewModel personViewModel;

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView personNameView, productemailView, productCreationDateView, personNumberView, personAvailableView, mPersonInterestView;
        private final Button editButton, deleteButton;

        private ProductViewHolder(final View itemView) {
            super(itemView);
            personNameView = itemView.findViewById(R.id.textView_person_name);
            productemailView = itemView.findViewById(R.id.textView_person_email);
            productCreationDateView = itemView.findViewById(R.id.textView_creation_date);
            personAvailableView = itemView.findViewById(R.id.textView_available);
            personNumberView = itemView.findViewById(R.id.textView_person_number);
            mPersonInterestView = itemView.findViewById(R.id.textView_person_interest);

            editButton = itemView.findViewById(R.id.button_edit);
            deleteButton = itemView.findViewById(R.id.button_delete);
            //productViewModel = ViewModelProviders.of().get(ProductViewModel.class);


        }
    }

    private final LayoutInflater mInflater;
    private List<Person> mPersons; // Cached copy of words

    public PersonListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        if (mPersons != null) {
            Person current = mPersons.get(position);
            holder.personNameView.setText("FullName: " + current.getPersonName());
            holder.productemailView.setText("Email: " + current.getPersonEmail());
            holder.productCreationDateView.setText("Date Created: " + current.getDateTimeFormatted(holder.productCreationDateView.getContext()));
            holder.personNumberView.setText("PhoneNumber: " + current.getPersonNumber());
            holder.mPersonInterestView.setText("Interest: " + current.getPersonInterest());
            holder.personAvailableView.setVisibility(View.GONE);

        } else {
            // Covers the case of data not being ready yet.
            holder.personNameView.setText("No Product");
        }

    }

    public void setmPersons(List<Person> products) {
        mPersons = products;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPersons != null)
            return mPersons.size();
        else return 0;
    }

    public Person getProductAt(int position) {
        return mPersons.get(position);
    }
}
