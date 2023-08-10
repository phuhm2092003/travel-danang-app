package com.example.travel_danang_app.ui.favourite;

import com.example.travel_danang_app.model.Location;

import java.util.ArrayList;

public interface FavourtieContract {
    interface View {
        void onDisplayFavouriteLocations(ArrayList<Location> locations);
    }

    interface Presenter {
        void getFavouriteLocations();

        void addFavouriteLocation(int idLocation);

        void removeFavouriteLocation(int idLocation);
    }
}
