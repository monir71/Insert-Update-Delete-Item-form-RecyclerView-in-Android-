package com.example.recyclertest;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnOpenDialog;
    ArrayList<ContactModel> data;
    RecyclerView rView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        rView = findViewById(R.id.rView);
        rView.setLayoutManager(new LinearLayoutManager(this));

        data = new ArrayList<>();
        data.add(new ContactModel("Md Moniruzzaman", "01710389321", R.drawable.photo1));
        data.add(new ContactModel("Md Rokonuzzaman", "01710389322", R.drawable.photo2));
        data.add(new ContactModel("Md Moniruzzaman", "01710389323", R.drawable.photo3));
        data.add(new ContactModel("Md Moniruzzaman", "01710389324", R.drawable.photo4));
        data.add(new ContactModel("Md Moniruzzaman", "01710389325", R.drawable.photo5));
        data.add(new ContactModel("Md Moniruzzaman", "01710389326", R.drawable.photo6));
        data.add(new ContactModel("Md Moniruzzaman", "01710389327", R.drawable.photo7));
        data.add(new ContactModel("Md Moniruzzaman", "01710389328", R.drawable.photo1));
        data.add(new ContactModel("Md Moniruzzaman", "01710389329", R.drawable.photo2));
        data.add(new ContactModel("Md Moniruzzaman", "01710389310", R.drawable.photo3));
        data.add(new ContactModel("Md Moniruzzaman", "01710389311", R.drawable.photo4));
        data.add(new ContactModel("Md Moniruzzaman", "01710389312", R.drawable.photo5));
        data.add(new ContactModel("Md Moniruzzaman", "01710389313", R.drawable.photo6));
        data.add(new ContactModel("Md Moniruzzaman", "01710389314", R.drawable.photo7));
        data.add(new ContactModel("Md Moniruzzaman", "01710389315", R.drawable.photo1));
        data.add(new ContactModel("Md Moniruzzaman", "01710389316", R.drawable.photo2));
        data.add(new ContactModel("Md Moniruzzaman", "01710389317", R.drawable.photo3));
        data.add(new ContactModel("Md Moniruzzaman", "01710389318", R.drawable.photo4));
        data.add(new ContactModel("Md Moniruzzaman", "01710389319", R.drawable.photo5));
        data.add(new ContactModel("Md Moniruzzaman", "01710389320", R.drawable.photo6));
        data.add(new ContactModel("Md Moniruzzaman", "01710389321", R.drawable.photo7));
        data.add(new ContactModel("Md Moniruzzaman", "01710389322", R.drawable.photo1));
        data.add(new ContactModel("Md Moniruzzaman", "01710389323", R.drawable.photo2));
        data.add(new ContactModel("Md Moniruzzaman", "01710389324", R.drawable.photo3));
        data.add(new ContactModel("Md Moniruzzaman", "01710389325", R.drawable.photo4));

        adapter = new RecyclerViewAdapter(this, data);
        rView.setAdapter(adapter);

        btnOpenDialog = findViewById(R.id.btnOpenDialog);

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_layout);

                EditText addUpdateName = dialog.findViewById(R.id.add_update_name);
                EditText addUpdateNumber = dialog.findViewById(R.id.add_update_number);
                Button addUpdateButton = dialog.findViewById(R.id.add_update_button);

                addUpdateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = addUpdateName.getText().toString();
                        String number = addUpdateNumber.getText().toString();
                        int img = R.drawable.photo1;

                        data.add(new ContactModel(name, number, img));

                        adapter.notifyItemInserted(data.size()-1);

                        rView.scrollToPosition(data.size()-1);

                        dialog.dismiss();

                    }
                });

                dialog.show();
            }

        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}