package com.example.valerastores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.ViewHolder> {
    private OnItemClickListener listener;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position, @NonNull MainModel model) {

        holder.prdctname.setText(model.getName());
        holder.prdctprice.setText(model.getPrice());
        holder.prdctweight.setText(model.getWeight());
        Picasso.get().load(model.getImage()).placeholder(R.drawable.ic_launcher_foreground).into(holder.prdctimage);
        holder.itemView.setOnClickListener(v -> {
            if(listener!=null)
                listener.onItemClick(model);
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.products,parent,false));
    }

    public interface OnItemClickListener {
        void onItemClick(MainModel model);
    }
    // Method to set the click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView prdctimage;
        TextView prdctname,prdctweight,prdctprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prdctname=itemView.findViewById(R.id.prdctname);
            prdctimage=itemView.findViewById(R.id.prdctimage);
            prdctweight=itemView.findViewById(R.id.prdctweight);
            prdctprice=itemView.findViewById(R.id.prdctprice);
        }
    }
}
