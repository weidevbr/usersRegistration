package com.example.usersregistration;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    private List<User> users;
    private UserAccess access;

    public MyAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String userName = users.get(position).getUserName();
        access = new UserAccess(context);

        holder.textView.setText(users.get(position).getNome());
        holder.imageview.setImageResource(R.drawable.data__4_);
        holder.editView.setImageResource(R.drawable.baseline_edit_24);
        holder.deleteView.setImageResource(R.drawable.delete_base);

        holder.editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), EditUser.class);
                i.putExtra("user",users.get(position));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(i);
            }
        });

        holder.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertMsg = new AlertDialog.Builder(v.getRootView().getContext())
                        .setTitle("Atenção!")
                        .setMessage("Deseja excluir esse usuário?")
                        .setNegativeButton("NÃO",null)
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                users.remove(users.get(position));
                                access.deleteUser(userName);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Usuário: " + userName +
                                        " Deletado com Sucesso", Toast.LENGTH_SHORT).show();
                            }
                        }).create();

                alertMsg.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
