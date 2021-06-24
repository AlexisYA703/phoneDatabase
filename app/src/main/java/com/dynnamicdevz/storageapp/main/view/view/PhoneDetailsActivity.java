package com.dynnamicdevz.storageapp.main.view.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dynnamicdevz.storageapp.databinding.ActivityPhoneDetailsBinding;
import com.dynnamicdevz.storageapp.main.view.model.data.Phone;
import com.dynnamicdevz.storageapp.main.view.model.db.PhoneDBHelper;

public class PhoneDetailsActivity extends AppCompatActivity {

    private ActivityPhoneDetailsBinding binding;
    private PhoneDBHelper phoneDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneDetailsBinding.inflate(
                getLayoutInflater()
        );
        setContentView(binding.getRoot());

        Phone phone = getIntent().getParcelableExtra("PHONE_DATA");
        phoneDBHelper = new PhoneDBHelper(this);

        if(phone != null){
            binding.phoneModelTextview.setText(phone.getPhoneModel());
            binding.phonePriceTextview.setText("$" + phone.getPrice());
            binding.phoneIdTextview.setText("SERIAL#: AA00"+phone.getPhoneID());
        }

        binding.sellPhoneButton.setOnClickListener(view -> {
            int phoneID = phone.getPhoneID();
            phoneDBHelper.deletePhone(phoneID);
            Intent intent = new Intent(this, PhoneSQLiteDatabase.class);
            startActivity(intent);
        });
    }
}