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
        districts.add(new District(R.drawable.ampara_district, "Ampara"));
        districts.add(new District(R.drawable.anuradhapura_district, "Anuradhapura"));
        districts.add(new District(R.drawable.badulla_district, "Badulla"));
        districts.add(new District(R.drawable.batticaloa_district, "Batticaloa"));
        districts.add(new District(R.drawable.colombo_district, R.drawable.colombo_district_crop, "Colombo", 263, 944));
        districts.add(new District(R.drawable.galle_district, "Galle"));
        districts.add(new District(R.drawable.gampaha_district, R.drawable.gampaha_district_crop, "Gampaha", 259, 844));
        districts.add(new District(R.drawable.hambantota_district, "Hambantota"));
        districts.add(new District(R.drawable.jaffna_district, "Jaffna"));
        districts.add(new District(R.drawable.kalutara_district, "Kalutara"));
        districts.add(new District(R.drawable.kandy_district, "Kandy"));
        districts.add(new District(R.drawable.kegalle_district, "Kegalle"));
        districts.add(new District(R.drawable.kilinochchi_district, "Kilinochchi"));
        districts.add(new District(R.drawable.kurunegala_district, "Kurunegala"));
        districts.add(new District(R.drawable.mannar_district, "Mannar"));
        districts.add(new District(R.drawable.matale_district, "Matale"));
        districts.add(new District(R.drawable.matara_district, "Matara"));
        districts.add(new District(R.drawable.moneragala_district, R.drawable.moneragala_district_crop, "Monaragala",
                560, 807));
        districts.add(new District(R.drawable.mullaitivu_district, "Mullaitivu"));
        districts.add(new District(R.drawable.nuwara_eliya_district, "Nuwara Eliya"));
        districts.add(new District(R.drawable.polonnaruwa_district, "Polonnaruwa"));
        districts.add(new District(R.drawable.puttalam_district, "Puttalam"));
        districts.add(new District(R.drawable.ratnapura_district, "Ratnapura"));
        districts.add(new District(R.drawable.trincomalee_district, "Trincomalee"));
        districts.add(new District(R.drawable.vavuniya_district, "Vavuniya"));

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

        District(int fullMapId, int districtMapId, String name, int x, int y) {
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
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static class Range {
        Point min;
        Point max;
        double percentage = 0.1;

        public Range(Point location) {
            int xMin = (int) (location.getX() * (1 - percentage));
            int xMax = (int) (location.getX() * (1 + percentage));
            int yMin = (int) (location.getY() * (1 - percentage));
            int yMax = (int) (location.getY() * (1 + percentage));

            this.min = new Point(xMin, yMin);
            this.max = new Point(xMax, yMax);

        }

        public boolean isInsideRage(Point point) {
            return point.getX() > min.getX() && point.getX() < max.getX() && point.getY() > min.getY() && point.getY() < max
                    .getY();
        }
    }

}




