package com.example.travel_danang_app.ui.user.home;


import com.example.travel_danang_app.model.Location;

import java.util.List;

public interface HomeContract {
    interface View {
        void onGetLocationsResult(List<Location> locations);
    }

    interface Presenter {
        void onCallGetLocationsApi();
    }
}
