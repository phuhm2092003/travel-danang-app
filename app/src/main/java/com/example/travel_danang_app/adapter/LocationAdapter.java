package com.example.travel_danang_app.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travel_danang_app.R;
import com.example.travel_danang_app.databinding.ItemLocationBinding;
import com.example.travel_danang_app.interfaces.ItemLocationClicked;
import com.example.travel_danang_app.model.Location;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
    private List<Location> list;
    private final ItemLocationClicked itemLocationClicked;

    public LocationAdapter(ItemLocationClicked itemLocationClicked) {
        this.itemLocationClicked = itemLocationClicked;
    }

    public void setListLocation(List<Location> locations) {
        this.list = locations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemLocationBinding itemLocationBinding = ItemLocationBinding.inflate(layoutInflater, parent, false);
        return new LocationViewHolder(itemLocationBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        Location location = list.get(position);
        if (location == null) {
            return;
        }

        Glide.with(holder.itemView)
                .load(location.getHinhAnh())
                .centerCrop()
                .placeholder(R.drawable.photo_default)
                .into(holder.locationBinding.photoLocation);

        holder.locationBinding.nameLocation.setText(location.getTenDiaDiem());
        holder.locationBinding.aboutLocation.setText(location.getGioiThieu());
        holder.locationBinding.addressLocation.setText(location.getViTri());

        if (location.getIsFavourite() == 1) {
            holder.locationBinding.favouriteCheckButton.setChecked(true);
        } else {
            holder.locationBinding.favouriteCheckButton.setChecked(false);
        }

        holder.locationBinding.favouriteCheckButton.setOnClickListener(view -> {
            if (holder.locationBinding.favouriteCheckButton.isChecked()) {
                // Yêu thích địa điểm
                itemLocationClicked.onFavouriteLocationClick(true, location.getId());
            } else {
                // Bỏ yêu thích
                itemLocationClicked.onFavouriteLocationClick(false, location.getId());
            }
        });

        holder.itemView.setOnClickListener(view -> itemLocationClicked.onDisplayDetailLocation(location));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class LocationViewHolder extends RecyclerView.ViewHolder {
        ItemLocationBinding locationBinding;

        public LocationViewHolder(@NonNull ItemLocationBinding locationBinding) {
            super(locationBinding.getRoot());
            this.locationBinding = locationBinding;
        }
    }
}
