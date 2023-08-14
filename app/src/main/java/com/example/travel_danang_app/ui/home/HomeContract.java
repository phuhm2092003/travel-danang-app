package com.example.travel_danang_app.ui.home;


import com.example.travel_danang_app.model.Location;

import java.util.List;

public interface HomeContract {
    interface View {
        void displayLocations(List<Location> locations);
    }

    interface Presenter {
        void getLocations();

        void addFavouriteLocation(int idLocation);

        void removeFavouriteLocation(int idLocation);
    }
}
