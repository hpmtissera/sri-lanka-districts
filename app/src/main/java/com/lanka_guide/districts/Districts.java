package com.lanka_guide.districts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * District class
 */

public class Districts {

    private static List<District> districts = new ArrayList<>();
    private static List<String> districtNames = new ArrayList<>();
    private static Map<Integer, District> mapIdDistrictMap = new HashMap<>();

    static {
        districts.add(new District(R.drawable.ampara_district, R.drawable.ampara_district_crop, "Ampara", 0.5625, 0.5222));
        districts.add(new District(R.drawable.anuradhapura_district, R.drawable.anuradhapura_district_crop, "Anuradhapura", 0.2738, 0.2618));
        districts.add(new District(R.drawable.badulla_district, R.drawable.badulla_district_crop, "Badulla", 0.5025, 0.5498));
        districts.add(new District(R.drawable.batticaloa_district, R.drawable.batticalo_district_crop, "Batticaloa", 0.6175, 0.4155));
        districts.add(new District(R.drawable.colombo_district, R.drawable.colombo_district_crop, "Colombo", 0.2425, 0.6855));
        districts.add(new District(R.drawable.galle_district, R.drawable.galle_district_crop, "Galle", 0.2831, 0.8106));
        districts.add(new District(R.drawable.gampaha_district, R.drawable.gampaha_district_crop, "Gampaha", 0.2375, 0.6126));
        districts.add(new District(R.drawable.hambantota_district, R.drawable.hambantota_district_crop, "Hambantota", 0.4613, 0.7783));
        districts.add(new District(R.drawable.jaffna_district, R.drawable.jaffna_district_crop, "Jaffna", 0.1869, 0.0628));
        districts.add(new District(R.drawable.kalutara_district, R.drawable.kalutara_district_crop, "Kalutara", 0.2563, 0.7203));
        districts.add(new District(R.drawable.kandy_district, R.drawable.kandy_district_crop, "Kandy", 0.4075, 0.5783));
        districts.add(new District(R.drawable.kegalle_district, R.drawable.kegalle_district_crop, "Kegalle", 0.3288, 0.5961));
        districts.add(new District(R.drawable.kilinochchi_district, R.drawable.kilinochchi_district_crop, "Kilinochchi", 0.2913, 0.099));
        districts.add(new District(R.drawable.kurunegala_district, R.drawable.kurunegala_district_crop, "Kurunegala", 0.2569, 0.4242));
        districts.add(new District(R.drawable.mannar_district, R.drawable.mannar_district_crop, "Mannar", 0.1988, 0.1957));
        districts.add(new District(R.drawable.matale_district, R.drawable.matale_district_crop, "Matale", 0.4256, 0.4662));
        districts.add(new District(R.drawable.matara_district, R.drawable.matara_district_crop, "Matara", 0.395, 0.815));
        districts.add(new District(R.drawable.moneragala_district, R.drawable.moneragala_district_crop, "Monaragala", 0.5185, 0.5848));
        districts.add(new District(R.drawable.mullaitivu_district, R.drawable.mullaitivu_district_crop, "Mullaitivu", 0.34, 0.144));
        districts.add(new District(R.drawable.nuwara_eliya_district, R.drawable.nuwara_eliya_district_crop, "Nuwara Eliya", 0.4094, 0.6203));
        districts.add(new District(R.drawable.polonnaruwa_district, R.drawable.polonnaruwa_district_crop, "Polonnaruwa", 0.4994, 0.3932));
        districts.add(new District(R.drawable.puttalam_district, R.drawable.puttalam_district_crop, "Puttalam", 0.1981, 0.344));
        districts.add(new District(R.drawable.ratnapura_district, R.drawable.ratnapura_district_crop, "Ratnapura", 0.3388, 0.699));
        districts.add(new District(R.drawable.trincomalee_district, R.drawable.trincomalee_district_crop, "Trincomalee", 0.5063, 0.2512));
        districts.add(new District(R.drawable.vavuniya_district, R.drawable.vavuniya_district_crop, "Vavuniya", 0.3369, 0.2159));

        districtNames = new ArrayList<>();
        for (District d : districts) {
            districtNames.add(d.getName());
            if (d.getDistrictMapId() != 0) {
                mapIdDistrictMap.put(d.getDistrictMapId(), d);
            }
        }
    }

    public static int[] getDistrictImagesId() {
        Collections.shuffle(districts);
        districts = Collections.unmodifiableList(districts);
        int[] imageIds = new int[districts.size()];
        for (int i = 0; i < districts.size(); i++) {
            imageIds[i] = districts.get(i).getFullMapId();
        }
        return imageIds;
    }

    public static String getName(int position) {
        return districts.get(position).getName();
    }

    public static String getNameByDistrictMapId(int mapId) {
        return mapIdDistrictMap.get(mapId).getName();
    }

    public static District getDistrictByDistrictMapId(int mapId) {
        return mapIdDistrictMap.get(mapId);
    }

    public static List<District> getDistricts() {
        return districts;
    }

    public static List<String> getAnswers(String districtName) {
        List<String> allNames = new ArrayList<>();
        allNames.addAll(districtNames);
        Collections.shuffle(allNames);

        List<String> answers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            answers.add(allNames.get(i));
        }

        if (!answers.contains(districtName)) {
            answers.remove(0);
            answers.add(districtName);
            Collections.shuffle(answers);
        }
        return answers;
    }


    public static class District {
        int fullMapId;
        int districtMapId;
        private String name;
        private Point location;
        private Range range;
        private List<String> answerPool;

        District(int fullMapId, String name) {
            this.name = name;
            this.fullMapId = fullMapId;
        }

        District(int fullMapId, int districtMapId, String name) {
            this.name = name;
            this.fullMapId = fullMapId;
            this.districtMapId = districtMapId;
        }

        District(int fullMapId, int districtMapId, String name, double x, double y) {
            this(fullMapId, districtMapId, name, new Point(x, y));
        }

        District(int fullMapId, int districtMapId, String name, Point location) {
            this(fullMapId, districtMapId, name);
            this.location = location;
            this.range = new Range(location);
        }

        public String getName() {
            return name;
        }

        int getFullMapId() {
            return fullMapId;
        }

        public int getDistrictMapId() {
            return districtMapId;
        }

        public List<String> getAnswerPool() {
            return answerPool;
        }

        public boolean isInsideRage(Point point) {
            return range.isInsideRage(point);
        }

        public Point getLocation() {
            return location;
        }
    }

    public static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

    public static class Range {
        Point min;
        Point max;
        double percentage = 0.15;

        public Range(Point location) {
            double xMin = location.getX() * (1 - percentage);
            double xMax = location.getX() * (1 + percentage);
            double yMin = location.getY() * (1 - percentage);
            double yMax = location.getY() * (1 + percentage);

            this.min = new Point(xMin, yMin);
            this.max = new Point(xMax, yMax);

        }

        public boolean isInsideRage(Point point) {
//            Log.d(District.class.getName(), "xMin : " + min.getX());
//            Log.d(District.class.getName(), "xMax : " + max.getX());
//            Log.d(District.class.getName(), "pointX : " + point.getX());
//
//            Log.d(District.class.getName(), "yMin : " + min.getY());
//            Log.d(District.class.getName(), "yMax : " + max.getY());
//            Log.d(District.class.getName(), "pointY : " + point.getY());

            return point.getX() > min.getX() && point.getX() < max.getX() && point.getY() > min.getY() && point.getY() < max
                    .getY();
        }
    }

}




