package com.example.restaurantguide.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantguide.DatabaseHelper;
import com.example.restaurantguide.EditActivity;
import com.example.restaurantguide.MainActivity;
import com.example.restaurantguide.Model.Restaurant;
import com.example.restaurantguide.R;
import com.example.restaurantguide.ViewActivity;

import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder{
    public TextView name,address,phone,tag,rating;
    public ImageButton editBtn,removeBtn;
    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.name);
        address = (TextView)itemView.findViewById(R.id.address);
        phone = (TextView)itemView.findViewById(R.id.phone);
        tag = (TextView)itemView.findViewById(R.id.tag);
        rating = (TextView)itemView.findViewById(R.id.rating);
        editBtn = (ImageButton)itemView.findViewById(R.id.edit);
        removeBtn = (ImageButton)itemView.findViewById(R.id.remove);


    }

}
public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>{
    private Context context;
    private List<Restaurant> restaurantList;
    //Database object
    DatabaseHelper dbHelper;

    public SearchAdapter(Context context, List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
        dbHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_restaurant,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, final int position) {
        final int id = restaurantList.get(position).getId();
        final String name = restaurantList.get(position).getrName();
        final String address = restaurantList.get(position).getrAddress();
        final String phone = restaurantList.get(position).getrPhone();
        final String tag = restaurantList.get(position).getrTag();
        final String rating = restaurantList.get(position).getrRating();
        final String desc = restaurantList.get(position).getrDesc();

        holder.name.setText(name);
        holder.address.setText(address);
        holder.phone.setText(phone);
        holder.tag.setText(tag);
        holder.rating.setText(rating);


        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog(
                        ""+position,
                        ""+id,
                        ""+name,
                        ""+address,
                        ""+phone,
                        ""+tag,
                        ""+rating,
                        ""+desc
                );
            }
        });

        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog(""+id);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openView(""+id,
                        ""+name,
                        ""+address,
                        ""+phone,
                        ""+tag,
                        ""+rating,
                        ""+desc);
            }
        });

    }

    private void openView(final String id, final String name, final String address, final String phone, final String tag, final String rating, final String desc) {
        Intent intent = new Intent(context, ViewActivity.class);
        intent.putExtra("ID",id);
        intent.putExtra("NAME",name);
        intent.putExtra("ADDRESS",address);
        intent.putExtra("PHONE",phone);
        intent.putExtra("DESC",desc);
        intent.putExtra("TAG",tag);
        intent.putExtra("RATING",rating);
        context.startActivity(intent);
    }

    private void deleteDialog(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you want to delete?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_delete_restaurant);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.delete(id);
                ((MainActivity)context).finish();
                context.startActivity(((MainActivity)context).getIntent());
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private void editDialog(String position, final String id, final String name, final String address, final String phone, final String tag, final String rating, final String desc) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Are you want to update?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_edit_restaurant);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("ID",id);
                intent.putExtra("NAME",name);
                intent.putExtra("ADDRESS",address);
                intent.putExtra("PHONE",phone);
                intent.putExtra("DESC",desc);
                intent.putExtra("TAG",tag);
                intent.putExtra("RATING",rating);
                intent.putExtra("EditMode",true);

                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
}
