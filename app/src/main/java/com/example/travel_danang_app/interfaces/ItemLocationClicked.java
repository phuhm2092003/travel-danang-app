package com.example.travel_danang_app.interfaces;

import com.example.travel_danang_app.model.Location;

public interface ItemLocationClicked {
    void onFavouriteLocationClicked(boolean isFavourite, int idLocation);
    void onLaunchDetailLocationActivity(Location location);
}
