package com.example.travel_danang_app.ui.home;


import com.example.travel_danang_app.model.Location;

import java.util.List;

public interface HomeContract {
    interface View {
        void onDisplayLocations(List<Location> locations);
    }

    interface Presenter {
        void onCallGetLocationsApi();

        void addFavouriteLocation(String idCurrentUser, int idLocation);
        void removeFavouriteLocation(String idCurrentUser, int idLocation);
    }
}
