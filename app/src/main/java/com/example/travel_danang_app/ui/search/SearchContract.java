package com.example.travel_danang_app.ui.search;

import com.example.travel_danang_app.model.Location;

import java.util.List;

public interface SearchContract {
    interface View {
        void onSearchDisplayLocations(List<Location> locations);
    }

    interface Presenter {
        void getLocations(String searchInput);
        void addFavouriteLocation(int idLocation);

        void removeFavouriteLocation(int idLocation);
    }
}
