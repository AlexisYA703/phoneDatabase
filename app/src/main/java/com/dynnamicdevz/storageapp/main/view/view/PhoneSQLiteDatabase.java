package com.dynnamicdevz.storageapp.main.view.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dynnamicdevz.storageapp.databinding.ActivityPhoneSqliteDatabaseBinding;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.dynnamicdevz.storageapp.R;
import com.dynnamicdevz.storageapp.main.view.model.data.Phone;
import com.dynnamicdevz.storageapp.main.view.model.data.Phone.Manufacturer;
import com.dynnamicdevz.storageapp.main.view.model.db.PhoneDBHelper;
import com.dynnamicdevz.storageapp.main.view.presenter.PhonePresenter;
import com.dynnamicdevz.storageapp.main.view.presenter.PresenterContract;
import com.dynnamicdevz.storageapp.main.view.view.adapter.PhoneAdapter;

import java.util.List;

public class PhoneSQLiteDatabase extends AppCompatActivity implements PhoneAdapter.PhoneDelegate, PresenterContract.PhoneView{

    private ActivityPhoneSqliteDatabaseBinding binding;
    private Manufacturer setManufacturer = Manufacturer.iPhone;
    private PhoneAdapter phoneAdapter = new PhoneAdapter(this);
    private PresenterContract.Presenter phonePresenter;
    PhoneDBHelper phoneDBHelper = new PhoneDBHelper(this);

    private String[] options = {
            Manufacturer.iPhone.name(),
            Manufacturer.Samsung.name(),
            Manufacturer.Motorola.name(),
            Manufacturer.Google.name()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneSqliteDatabaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.phoneListview.setAdapter(phoneAdapter);
        phonePresenter = new PhonePresenter(this);
        phonePresenter.getPhones();

        binding.manufacturerSpinner.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item,R.id.manufacturer_name, options ));
        binding.manufacturerSpinner.setSelection(0);

        binding.manufacturerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setManufacturer = Manufacturer.valueOf(options[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        binding.insertPhoneButton.setOnClickListener(view -> {
            String phoneModel = binding.modelEdittext.getText().toString().trim();
            double price = Double.parseDouble(binding.priceEdittext.getText().toString());

            Phone newPhone = new Phone(phoneModel,setManufacturer,price);
            phonePresenter.insertNewPhone(newPhone);
        });

    }

    @Override
    public void selectPhone(Phone phone) {
        Toast.makeText(this, phone.getPhoneModel(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PhoneDetailsActivity.class);
        intent.putExtra("PHONE_DATA", phone);
        startActivity(intent);
    }

    @Override
    public void displayPhones(List<Phone> phones) {
        phoneAdapter.setPhoneList(phones);
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}