package platformpbp.uajy.quickresto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


import platformpbp.uajy.quickresto.databinding.ItemMyreservationlistBinding;
import platformpbp.uajy.quickresto.model.Reservation;

public class MyResListRecyclerViewAdapter extends RecyclerView.Adapter<MyResListRecyclerViewAdapter.MyViewHolder>{
    private Context context;
    private List<Reservation> result;

    public MyResListRecyclerViewAdapter(){}

    public MyResListRecyclerViewAdapter(Context context, List<Reservation> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyResListRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMyreservationlistBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_myreservationlist, parent, false);
        return new MyResListRecyclerViewAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyResListRecyclerViewAdapter.MyViewHolder holder, final int position) {
        Reservation rst= result.get(position);
        holder.bind(rst);
//        if (!rst.getUrlfoto().equals("")){
//            Glide.with(context)
//                    .load(rst.getUrlfoto())
//                    .apply(new RequestOptions().override(80, 80))
//                    .into(holder.foto_profil);
//        }else{
//            holder.foto_profil.setImageResource(R.drawable.ic_baseline_broken_image_24);
//        }
        holder.parent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context,MapRestaurant.class);
                intent.putExtra("alamat",String.valueOf(rst.address));
                intent.putExtra("resto",String.valueOf(rst.nameRest));
//                intent.putExtra("gambar",String.valueOf(rst.urlfoto));
//                intent.putExtra("latitude",rst.latitude);
//                intent.putExtra("longitude",rst.longitude);
                context.startActivity(intent);
                Toast.makeText(context, "You touch me?" , Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ItemMyreservationlistBinding itemRowBinding;
//        public ImageView foto_profil;
        private CardView parent;

        public MyViewHolder(ItemMyreservationlistBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;

        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(platformpbp.uajy.quickresto.BR.resto, obj);
            itemRowBinding.executePendingBindings();
//            foto_profil = itemView.findViewById(R.id.image_resto);
            parent = itemView.findViewById(R.id.item_reservation);

        }

        public void onClick(View view) {
            Toast.makeText(context, "You touch me?", Toast.LENGTH_SHORT).show();
        }
    }
}
