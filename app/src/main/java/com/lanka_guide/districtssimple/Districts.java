package com.lanka_guide.districtssimple;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chanya on 2017/09/24.
 */

public class Districts {

    static List<District> districts = new ArrayList<>();

    static {
        districts.add(new District(R.drawable.ampara_district, "Ampara"));
        districts.add(new District(R.drawable.anuradhapura_district, "Anuradhapura"));
        districts.add(new District(R.drawable.badulla_district, "Badulla"));
        districts.add(new District(R.drawable.batticaloa_district, "Batticaloa"));
        districts.add(new District(R.drawable.colombo_district, "Colombo"));
        districts.add(new District(R.drawable.galle_district, "Galle"));
        districts.add(new District(R.drawable.gampaha_district, "Gampaha"));
        districts.add(new District(R.drawable.hambantota_district, "Hambantota"));
        districts.add(new District(R.drawable.jaffna_district, "Jaffna"));
        districts.add(new District(R.drawable.jaffna_district, "Kalutara"));
        districts.add(new District(R.drawable.kandy_district, "Kandy"));
        districts.add(new District(R.drawable.kegalle_district, "Kegalle"));
        districts.add(new District(R.drawable.kilinochchi_district, "Kilinochchi"));
        districts.add(new District(R.drawable.kurunegala_district, "Kurunegala"));
        districts.add(new District(R.drawable.mannar_district, "Mannar"));
        districts.add(new District(R.drawable.matale_district, "Matale"));
        districts.add(new District(R.drawable.matara_district, "Matara"));
        districts.add(new District(R.drawable.moneragala_district, "Monaragala"));
        districts.add(new District(R.drawable.mullaitivu_district, "Mullaitivu"));
        districts.add(new District(R.drawable.nuwara_eliya_district, "Nuwara Eliya"));
        districts.add(new District(R.drawable.polonnaruwa_district, "Polonnaruwa"));
        districts.add(new District(R.drawable.puttalam_district, "Puttalam"));
        districts.add(new District(R.drawable.ratnapura_district, "Ratnapura"));
        districts.add(new District(R.drawable.trincomalee_district, "Trincomalee"));
        districts.add(new District(R.drawable.vavuniya_district, "Vavuniya"));
    }

    public static int[] getSliderImagesId() {
        Collections.shuffle(districts);
        int[] imageIds = new int[districts.size()];
        for (int i = 0; i < districts.size(); i++) {
            imageIds[i] = districts.get(i).getImageId();
        }
        return imageIds;
    }

    public static String getName(int position) {
        return districts.get(position).getName();
    }

    static class District {
        int imageId;
        private String name;

        public District(int imageId, String name) {
            this.name = name;
            this.imageId = imageId;
        }

        public String getName() {
            return name;
        }

        public int getImageId() {
            return imageId;
        }

    }
}
