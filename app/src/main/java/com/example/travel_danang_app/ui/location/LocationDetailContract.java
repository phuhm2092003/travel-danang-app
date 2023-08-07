package com.example.travel_danang_app.ui.location;

public interface LocationDetailContract {
    interface Presenter{
        void addFavouriteLocation(int idLocation);

        void removeFavouriteLocation(int idLocation);
    }
}
