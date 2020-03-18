package com.example.hangancristian_tema2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<User> users;

    // Constructor (depends on the kind of dataset)
    MyAdapter(List<User> myDataset){
        users = myDataset;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_info, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.name.setText(users.get(position).getName());
        holder.mark.setText("Nota: " + users.get(position).getMark());
    }


    // Return the size of the dataset
    @Override
    public int getItemCount() {
        return users.size();
    }


    public void addUser (User user) {
        List<User> users = this.users;
        users.add(user);
        this.users = users;


    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView mark;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name);
            mark = itemView.findViewById(R.id.user_mark);
        }
    }
}
