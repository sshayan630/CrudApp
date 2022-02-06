package com.example.shayan.hotchTest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shayan.hotchTest.R;

public class NewPersonActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_PERSON_ID = "com.shayan.example.hotchTest.EXTRA_ID";
    public static final String EXTRA_REPLY_PERSON_NAME = "com.shayan.example.hotchTest.NAME";
    public static final String EXTRA_REPLY_PERSON_EMAIL = "com.shayan.example.hotchTest.EMAIL";
    public static final String EXTRA_REPLY_PERSON_NUMBER = "com.shayan.example.hotchTest.NUMBER";
    public static final String EXTRA_REPLY_PERSON_INTERST = "com.shayan.example.hotchTest.INTERST";

    private EditText mEditPersonNameView, mEditPersonInteestView, mEditPersonPhoneView, mEditPersonEmailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        mEditPersonNameView = findViewById(R.id.edit_person_name);
        mEditPersonInteestView = findViewById(R.id.edit_person_interest);
        mEditPersonPhoneView = findViewById(R.id.edit_person_number);
        mEditPersonEmailView = findViewById(R.id.edit_person_email);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_REPLY_PERSON_ID)) {
            setTitle("Edit Product");
            mEditPersonNameView.setText(intent.getStringExtra(EXTRA_REPLY_PERSON_NAME));
            mEditPersonEmailView.setText(intent.getStringExtra(EXTRA_REPLY_PERSON_EMAIL));
            mEditPersonPhoneView.setText(intent.getStringExtra(EXTRA_REPLY_PERSON_NUMBER));
            mEditPersonInteestView.setText(intent.getStringExtra(EXTRA_REPLY_PERSON_INTERST));

        } else {
            setTitle("Add Product");
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditPersonNameView.getText()) || TextUtils.isEmpty(mEditPersonEmailView.getText()) || TextUtils.isEmpty(mEditPersonPhoneView.getText()) || TextUtils.isEmpty(mEditPersonInteestView.getText())) {

                    setResult(RESULT_CANCELED, replyIntent);
                    finish();

                } else {
                    if (!isEmailValidCheck(mEditPersonEmailView.getText().toString())) {
                        Toast.makeText(NewPersonActivity.this, "Email not Valid", Toast.LENGTH_SHORT).show();
                    } else {
                        String personName = mEditPersonNameView.getText().toString();
                        String personEmail = mEditPersonEmailView.getText().toString();
                        String personPhone = mEditPersonPhoneView.getText().toString();
                        String personInterest = mEditPersonInteestView.getText().toString();

                        replyIntent.putExtra(EXTRA_REPLY_PERSON_NAME, personName);
                        replyIntent.putExtra(EXTRA_REPLY_PERSON_NUMBER, personPhone);
                        replyIntent.putExtra(EXTRA_REPLY_PERSON_INTERST, personInterest);
                        replyIntent.putExtra(EXTRA_REPLY_PERSON_EMAIL, personEmail);

                        int id = getIntent().getIntExtra(EXTRA_REPLY_PERSON_ID, -1);
                        if (id != -1) {
                            replyIntent.putExtra(EXTRA_REPLY_PERSON_ID, id);
                        }

                        setResult(RESULT_OK, replyIntent);
                        finish();

                    }
                }
            }
        });
        mEditPersonInteestView.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                        || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    button.performClick();
                }
                return false;
            }
        });


    }

    public static boolean isEmailValidCheck(String email) {
        return !(email == null || TextUtils.isEmpty(email)) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
