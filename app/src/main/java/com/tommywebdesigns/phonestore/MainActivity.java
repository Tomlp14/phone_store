package com.tommywebdesigns.phonestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.tommywebdesigns.phonestore.databinding.ActivityMainBinding;
import com.tommywebdesigns.phonestore.model.data.Phone;
import com.tommywebdesigns.phonestore.model.data.PhoneBrands;
import com.tommywebdesigns.phonestore.model.db.PhoneDBHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private PhoneBrands setPhoneBrand= PhoneBrands.Samsung;
    private PhoneAdaptor phoneAdaptor= new PhoneAdaptor();
    private Phone phone=null;




    private PhoneDBHelper phoneDBHelper;


    private String[] options= new String[] {
            PhoneBrands.Samsung.name(),
            PhoneBrands.Apple.name(),
            PhoneBrands.Amazon.name(),
            PhoneBrands.Asus.name(),
            PhoneBrands.Google.name(),
            PhoneBrands.LG.name(),
            PhoneBrands.Sony.name(),
            PhoneBrands.Nokia.name(),
            PhoneBrands.Levono.name(),
            PhoneBrands.Huawei.name()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //by reference
        binding.phoneListview.setAdapter(phoneAdaptor);
        //It calls the onCreate method automatically.
        phoneDBHelper= new PhoneDBHelper(this);
        readAllPhones();
        //I have to complete a new layout for my items in the list.
        //The arguments are the layout spinner_item, the text view in the layout called brand_name and the options array.
        binding.spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, R.id.brand_name_tv, options));
        binding.spinner.setSelection(0);
        //This method gets the value of the item that the user selects
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //setPhoneBrand is an instance of PhoneBrands enum.
                setPhoneBrand= PhoneBrands.valueOf(options[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                  //It's not doing something.
            }
        });



        binding.addBt.setOnClickListener(v -> {
            String strModel= binding.modelEt.getText().toString().trim();
            double doublePrice= Double.parseDouble(binding.priceEt.getText().toString().trim());
            //i have to check if PhoneBrands is null or I have to gift an default option.
            Phone myPhone= new Phone(setPhoneBrand, strModel, doublePrice);
            phoneDBHelper.insertPhone(myPhone);
            readAllPhones();

        });

        binding.phoneListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                phone= new Phone();
                phone.setPhoneId((int)phoneAdaptor.getItemId(position));
            }
        });

        binding.deleteBt.setOnClickListener(v -> {
             phoneDBHelper.deletePhone(phone);
             readAllPhones();
        });




    }// onCreate

    private void readAllPhones() {
        List<Phone> phones= phoneDBHelper.getAllPhones();
         phoneAdaptor.setPhoneList(phones);
    }//readAllPhones
}