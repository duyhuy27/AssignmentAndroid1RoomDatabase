package huyndph30375.fpoly.huyndph30375_assignment.constant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import huyndph30375.fpoly.huyndph30375_assignment.R;
import huyndph30375.fpoly.huyndph30375_assignment.databinding.ActivityUpdateInformationBinding;

public class CitySelectDialog extends Dialog implements android.view.View.OnClickListener {
    public Activity c;
    public ListView cityList;
    public List<String> cities;
    public ArrayAdapter<String> adapter;
    public ActivityUpdateInformationBinding binding;


    public CitySelectDialog(@NonNull Context context, ActivityUpdateInformationBinding binding) {
        super(context);
        this.c = (Activity) context;
        this.binding = binding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_city_select);
        cityList = findViewById(R.id.city_list);
        EditText searchEditText = findViewById(R.id.search_bar);
        cities = new ArrayList<>();
        cities.add("An Giang");
        cities.add("Bà Rịa - Vũng Tàu");
        cities.add("Bắc Giang");
        cities.add("Bắc Kạn");
        cities.add("Bạc Liêu");
        cities.add("Bắc Ninh");
        cities.add("Bến Tre");
        cities.add("Bình Định");
        cities.add("Bình Dương");
        cities.add("Bình Phước");
        cities.add("Bình Thuận");
        cities.add("Cà Mau");
        cities.add("Cần Thơ");
        cities.add("Cao Bằng");
        cities.add("Đà Nẵng");
        cities.add("Đắk Lắk");
        cities.add("Đắk Nông");
        cities.add("Điện Biên");
        cities.add("Đồng Nai");
        cities.add("Đồng Tháp");
        cities.add("Gia Lai");
        cities.add("Hà Giang");
        cities.add("Hà Nam");
        cities.add("Hà Nội");
        cities.add("Hà Tĩnh");
        cities.add("Hải Dương");
        cities.add("Hải Phòng");
        cities.add("Hậu Giang");
        cities.add("Hòa Bình");
        cities.add("Hưng Yên");
        cities.add("Khánh Hòa");
        cities.add("Kiên Giang");
        cities.add("Kon Tum");
        cities.add("Lai Châu");
        cities.add("Lâm Đồng");
        cities.add("Lạng Sơn");
        cities.add("Lào Cai");
        cities.add("Long An");
        cities.add("Nam Định");
        cities.add("Nghệ An");
        cities.add("Ninh Bình");
        cities.add("Ninh Thuận");
        cities.add("Phú Thọ");
        cities.add("Quảng Bình");
        cities.add("Quảng Nam");
        cities.add("Quảng Ngãi");
        cities.add("Quảng Ninh");
        cities.add("Quảng Trị");
        cities.add("Sóc Trăng");
        cities.add("Sơn La");
        cities.add("Tây Ninh");
        cities.add("Thái Bình");
        cities.add("Thái Nguyên");
        cities.add("Thanh Hóa");
        cities.add("Thừa Thiên Huế");
        cities.add("Tiền Giang");
        cities.add("TP Hồ Chí Minh");
        cities.add("Trà Vinh");
        cities.add("Tuyên Quang");
        cities.add("Vĩnh Long");
        cities.add("Vĩnh Phúc");
        cities.add("Yên Bái");

        adapter = new ArrayAdapter<>(c, android.R.layout.simple_list_item_1, cities);
        cityList.setAdapter(adapter);

        // Set a filter to the adapter
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        cityList.setOnItemClickListener((adapterView, view, i, l) -> {
            String selectedCity = (String) adapterView.getItemAtPosition(i);
            binding.inputAddress.setText(selectedCity);
            Toast.makeText(c, selectedCity, Toast.LENGTH_SHORT).show();
            dismiss();
        });
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
