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
