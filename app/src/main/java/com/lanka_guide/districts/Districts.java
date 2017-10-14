package com.lanka_guide.districts;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

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
    private Context context;


    public Districts(Context context) {
        this.context = context;
        districts.add(new District(R.drawable.ampara_district, R.drawable.ampara_district_crop, getString(R.string.ampara) , 0.5625, 0.5222));
        districts.add(new District(R.drawable.anuradhapura_district, R.drawable.anuradhapura_district_crop, getString(R.string.anuradhapura), 0.2, 0.2738,
                0.2618, 442, 520));
        districts.add(new District(R.drawable.badulla_district, R.drawable.badulla_district_crop, getString(R.string.badulla), 0.5025, 0.5498));
        districts.add(new District(R.drawable.batticaloa_district, R.drawable.batticalo_district_crop, getString(R.string.batticaloa), 0.6315, 0.4098));
        districts.add(new District(R.drawable.colombo_district, R.drawable.colombo_district_crop, getString(R.string.colombo), 0.2425, 0.6855));
        districts.add(new District(R.drawable.galle_district, R.drawable.galle_district_crop, getString(R.string.galle), 0.2831, 0.8106));
        districts.add(new District(R.drawable.gampaha_district, R.drawable.gampaha_district_crop, getString(R.string.gampaha), 0.2375, 0.6126));
        districts.add(new District(R.drawable.hambantota_district, R.drawable.hambantota_district_crop, getString(R.string.hambantota), 0.4613, 0.7783));
        districts.add(new District(R.drawable.jaffna_district, R.drawable.jaffna_district_crop, getString(R.string.jaffna), 0.1869, 0.0628));
        districts.add(new District(R.drawable.kalutara_district, R.drawable.kalutara_district_crop, getString(R.string.kalutara), 0.2563, 0.7230));
        districts.add(new District(R.drawable.kandy_district, R.drawable.kandy_district_crop, getString(R.string.kandy), 0.4075, 0.5783));
        districts.add(new District(R.drawable.kegalle_district, R.drawable.kegalle_district_crop, getString(R.string.kegalle), 0.3288, 0.5961));
        districts.add(new District(R.drawable.kilinochchi_district, R.drawable.kilinochchi_district_crop, getString(R.string.kilinochchi), 0.2913, 0.099));
        districts.add(new District(R.drawable.kurunegala_district, R.drawable.kurunegala_district_crop, getString(R.string.kurunegala), 0.2, 0.2569,
                0.4242, 371, 713));
        districts.add(new District(R.drawable.mannar_district, R.drawable.mannar_district_crop, getString(R.string.mannar), 0.1988, 0.1957));
        districts.add(new District(R.drawable.matale_district, R.drawable.matale_district_crop, getString(R.string.matale), 0.4256, 0.4662));
        districts.add(new District(R.drawable.matara_district, R.drawable.matara_district_crop, getString(R.string.matara), 0.395, 0.815));
        districts.add(new District(R.drawable.moneragala_district, R.drawable.moneragala_district_crop, getString(R.string.monaragala), 0.5185, 0.5848));
        districts.add(new District(R.drawable.mullaitivu_district, R.drawable.mullaitivu_district_crop, getString(R.string.mullaitivu), 0.34, 0.144));
        districts.add(new District(R.drawable.nuwara_eliya_district, R.drawable.nuwara_eliya_district_crop, getString(R.string.nuwaraEliya), 0.4094, 0.6212));
        districts.add(new District(R.drawable.polonnaruwa_district, R.drawable.polonnaruwa_district_crop, getString(R.string.polonnaruwa), 0.4994, 0.3932));
        districts.add(new District(R.drawable.puttalam_district, R.drawable.puttalam_district_crop, getString(R.string.puttalam), 0.2, 0.1981, 0.344, 277,
                658));
        districts.add(new District(R.drawable.ratnapura_district, R.drawable.ratnapura_district_crop, getString(R.string.ratnapura), 0.3388, 0.7010));
        districts.add(new District(R.drawable.trincomalee_district, R.drawable.trincomalee_district_crop, getString(R.string.trincomalee), 0.5063, 0.2512));
        districts.add(new District(R.drawable.vavuniya_district, R.drawable.vavuniya_district_crop, getString(R.string.vavuniya), 0.3369, 0.2159));

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

    private String getString(int id) {
        Resources resources = context.getResources();
        return resources.getString(id);
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

        District(int fullMapId, int districtMapId, String name, double precentage, Point location, Point dropPoint) {
            this(fullMapId, districtMapId, name);
            this.location = location;
            this.range = new Range(dropPoint, precentage);
        }

        public District(int fullMapId, int districtMapId, String name, double precentage, double locationX, double locationY, int dropPointX, int
                dropPointY) {
            this(fullMapId, districtMapId, name, precentage, new Point(locationX, locationY), new Point(dropPointX, dropPointY));
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
        static final double DEFAULT_PERCENTAGE = 0.15;
        Point min;
        Point max;

        public Range(Point dropPoint) {
            this(dropPoint, DEFAULT_PERCENTAGE);
        }

        public Range(Point dropPoint, double precentage) {
            double xMin = dropPoint.getX() * (1 - precentage);
            double xMax = dropPoint.getX() * (1 + precentage);
            double yMin = dropPoint.getY() * (1 - precentage);
            double yMax = dropPoint.getY() * (1 + precentage);

            this.min = new Point(xMin, yMin);
            this.max = new Point(xMax, yMax);
        }

        public boolean isInsideRage(Point point) {
            Log.d(District.class.getName(), "xMin : " + min.getX());
            Log.d(District.class.getName(), "xMax : " + max.getX());
            Log.d(District.class.getName(), "pointX : " + point.getX());

            Log.d(District.class.getName(), "yMin : " + min.getY());
            Log.d(District.class.getName(), "yMax : " + max.getY());
            Log.d(District.class.getName(), "pointY : " + point.getY());

            return point.getX() > min.getX() && point.getX() < max.getX() && point.getY() > min.getY() && point.getY() < max.getY();
        }
    }

}




