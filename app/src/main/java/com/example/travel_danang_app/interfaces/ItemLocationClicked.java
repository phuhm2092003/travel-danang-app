package com.example.travel_danang_app.interfaces;

import com.example.travel_danang_app.model.Location;

public interface ItemLocationClicked {
    void onFavouriteLocationClick(boolean isFavourite, int idLocation);
    void onDisplayDetailLocation(Location location);
}
