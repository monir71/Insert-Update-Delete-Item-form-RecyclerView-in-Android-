Adapter

```
package com.example.recyclertest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<ContactModel> contactList;

    public RecyclerViewAdapter(Context context, ArrayList<ContactModel> contactList)
    {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img.setImageResource(contactList.get(position).img);
        holder.name.setText(contactList.get(position).name);
        holder.number.setText(contactList.get(position).number);

        holder.llrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_update_layout);

                EditText addUpdateName = dialog.findViewById(R.id.add_update_name);
                EditText addUpdateNumber = dialog.findViewById(R.id.add_update_number);
                Button addUpdateButton = dialog.findViewById(R.id.add_update_button);
                TextView title = dialog.findViewById(R.id.title);

                title.setText("Edit Contact");
                addUpdateButton.setText("Update");

                addUpdateName.setText(contactList.get(position).name);
                addUpdateNumber.setText(contactList.get(position).number);

                addUpdateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = addUpdateName.getText().toString();
                        String number = addUpdateNumber.getText().toString();
                        int img = R.drawable.photo1;

                        contactList.set(position, new ContactModel(name, number, img));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure want to delete?")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                contactList.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, number;
        ImageView img;
        LinearLayout llrow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtViewName);
            number = itemView.findViewById(R.id.txtViewNumber);
            img = itemView.findViewById(R.id.img);
            llrow = itemView.findViewById(R.id.llrow);
        }
    }
}
```

Model

```
package com.example.recyclertest;

public class ContactModel {
    String name, number;
    int img;

    public ContactModel(String name, String number, int img)
    {
        this.name = name;
        this.number = number;
        this.img = img;
    }
}
```

MainActivity.java File:

```
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
```
