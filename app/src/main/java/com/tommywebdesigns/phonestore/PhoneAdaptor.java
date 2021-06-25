package com.tommywebdesigns.phonestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tommywebdesigns.phonestore.R;
import com.tommywebdesigns.phonestore.databinding.PhoneItemLayoutBinding;
import com.tommywebdesigns.phonestore.model.data.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneAdaptor extends BaseAdapter {

    private List<Phone> phones= new ArrayList<>();

     public PhoneAdaptor(List<Phone> phones){
         this.phones=phones;
     }
     public PhoneAdaptor(){}// empty constructors

    public void setPhoneList(List<Phone> phones) {
        this.phones = phones;
        notifyDataSetChanged(); // it refreshes or updates the list
    }

    @Override
    public int getCount() {
        return this.phones.size();
    }

    @Override
    public Object getItem(int position) {
        return this.phones.get(position);
    }

    @Override //It returns the phone id.
    public long getItemId(int position) {
        return phones.get(position).getPhoneId();
    }

    PhoneItemLayoutBinding binding;
    //This one inflates the view and returning it to the main activity.
   @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // the viewgroup is the parent
        View layout= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phone_item_layout, parent, false);
        binding= PhoneItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        Phone phone= phones.get(position);

        binding.phoneBrandTv.setText(phone.getPhoneBrand().name());
        binding.phoneModelTv.setText(phone.getModel());
        binding.phonePriceTv.setText(phone.getPrice()+"");

         return binding.getRoot();
    }// getView


}
