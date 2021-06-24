package com.dynnamicdevz.storageapp.main.view.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dynnamicdevz.storageapp.databinding.PhoneItemLayoutBinding;
import com.dynnamicdevz.storageapp.main.view.model.data.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneAdapter extends BaseAdapter {

    public interface PhoneDelegate {
        void selectPhone(Phone phone);
    }
    private List<Phone> phoneList = new ArrayList<>();

    private PhoneDelegate phoneDelegate;

    public PhoneAdapter(PhoneDelegate phoneDelegate) {
        this.phoneDelegate = phoneDelegate;
    }

    public PhoneAdapter(List<Phone> phoneList, PhoneDelegate phoneDelegate) {
        this.phoneList = phoneList;
        this.phoneDelegate = phoneDelegate;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    @Override
    public int getCount() {
        return phoneList.size();
    }

    @Override
    public Phone getItem(int i) {
        return phoneList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long)i;
    }

    private PhoneItemLayoutBinding binding;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        binding = PhoneItemLayoutBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);

        Phone phone = phoneList.get(i);
        binding.manufacturerNameTextview.setText(phone.getManufacturer().toString());
        binding.modelTextview.setText(phone.getPhoneModel());
        binding.priceTextview.setText("$"+phone.getPrice());
        binding.serialIdTextview.setText("SERIAL#: AA00"+phone.getPhoneID());

        binding.getRoot().setOnClickListener(v -> {
            phoneDelegate.selectPhone(phone);
        });
        return binding.getRoot();
    }
}
