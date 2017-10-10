package com.lanka_guide.districts.play;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.lanka_guide.districts.Districts;
import com.lanka_guide.districts.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chanya on 2017/09/27.
 */

public class PlayActivity extends Activity {
    ImageView fullMap;
    RelativeLayout fullMapLayout;
    LinearLayout availableDistricts;
    List<Districts.District> placedDistricts = new ArrayList<>();
    private ViewPager mViewPager;
    private android.widget.RelativeLayout.LayoutParams layoutParams;
    private AdView mAdView;
    private TextView districtLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Districts.getDistricts().isEmpty()) {
            new Districts(getApplicationContext());
        }

        setContentView(R.layout.play_activity);

        districtLabel = (TextView) findViewById(R.id.playLabel);

        mAdView = (AdView) findViewById(R.id.playAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        fullMap = (ImageView) findViewById(R.id.fullMap);

        fullMapLayout = (RelativeLayout) findViewById(R.id.map_layout);
        availableDistricts = (LinearLayout) findViewById(R.id.availableDistricts);

        List<Integer> districtCropImages = new ArrayList<>();
        List<Districts.District> districts = Districts.getDistricts();

        for (Districts.District district : districts) {
            if (district.getDistrictMapId() != 0) {
                districtCropImages.add(district.getDistrictMapId());
            }
        }

        for (final int mapId : districtCropImages) {
            final ImageView districtImage = new ImageView(this);
            districtImage.setImageResource(mapId);

            districtImage.setOnLongClickListener(

                    new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {

//                            Toast.makeText(PlayActivity.this, Districts.getNameByDistrictMapId(mapId), Toast.LENGTH_SHORT).show();

                            ClipData.Item item = new ClipData.Item(Integer.toString(mapId));

                            ClipData dragData = new ClipData(Integer.toString(mapId), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);

                            Bitmap fullMap = BitmapFactory.decodeResource(getResources(), R.drawable.map_srilanka);
                            Bitmap bMap = BitmapFactory.decodeResource(getResources(), mapId);

                            int scaledWidth = (int) (((double) bMap.getWidth() / fullMap.getWidth()) * PlayActivity.this.fullMap.getWidth());
                            int scaledHeight = (int) (((double) bMap.getHeight() / fullMap.getHeight()) * PlayActivity.this.fullMap.getHeight());

                            Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, scaledWidth, scaledHeight, true);

                            View.DragShadowBuilder myShadow = new MyDragShadowBuilder(districtImage, scaledWidth, scaledHeight);
                            v.startDrag(dragData, myShadow, v, 0);

                            districtImage.setImageBitmap(bMapScaled);

                            return false;
                        }
                    }

            );

            districtImage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    districtLabel.setText(Districts.getNameByDistrictMapId(mapId));
                    districtLabel.setVisibility(View.VISIBLE);

                    new CountDownTimer(2000, 1000) {

                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            districtLabel.setVisibility(View.GONE);
                        }
                    }.start();

                    return false;
                }
            });
            districtImage.setAdjustViewBounds(true);
            availableDistricts.addView(districtImage);

        }


        fullMap.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View view, DragEvent event) {

                final int action = event.getAction();

                switch (action) {

                    case DragEvent.ACTION_DRAG_STARTED:
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        break;

                    case DragEvent.ACTION_DROP:
                        return onDropDistrict(event);

                    default:
                        break;
                }

                return true;
            }
        });
    }

    private boolean onDropDistrict(DragEvent event) {
        Districts.District district = Districts.getDistrictByDistrictMapId(Integer.parseInt((String) event
                .getClipData().getItemAt(0).getText()));

        ImageView dragged = (ImageView) event.getLocalState();

        int x_cord = (int) event.getX();
        int y_cord = (int) event.getY();

        Log.d(PlayActivity.class.getName(), district.getName() + " district code X : " + x_cord);
        Log.d(PlayActivity.class.getName(), district.getName() + " district code Y : " + y_cord);

        if (district.isInsideRage(new Districts.Point(Math.round((x_cord - dragged.getWidth() / 2) * 10000.0) / fullMap.getWidth() / 10000.0,
                (double) Math.round((y_cord - dragged.getHeight() / 2) * 10000.0) / fullMap.getHeight() / 10000.0)) || district.isInsideRage(new
                Districts.Point(x_cord, y_cord))) {

            dragged.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            if (!placedDistricts.contains(district)) {

                availableDistricts.removeView(dragged);

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dragged.getLayoutParams());
                params.leftMargin = (int) Math.round(district.getLocation().getX() * fullMap.getWidth());
                params.topMargin = (int) Math.round(district.getLocation().getY() * fullMap.getHeight());

                fullMapLayout.addView(dragged, params);


                placedDistricts.add(district);

                if (placedDistricts.size() == 25) {
                    TextView congratzText = (TextView) findViewById(R.id.playCongratz);
                    congratzText.setVisibility(View.VISIBLE);
                    congratzText.bringToFront();
                }
            } else {

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dragged.getLayoutParams());

                params.leftMargin = x_cord - dragged.getWidth() / 2;
                params.topMargin = y_cord - dragged.getHeight() / 2;

                dragged.setLayoutParams(params);

                Log.d(PlayActivity.class.getName(), district.getName() + " district location X : " + Math.round(
                        params.leftMargin * 10000.0 / fullMap.getWidth()) / 10000.0);
                Log.d(PlayActivity.class.getName(), district.getName() + " district location Y : " + Math.round(
                        params.topMargin * 10000.0 / fullMap.getHeight()) / 10000.0);
                Log.d(PlayActivity.class.getName(), "Fullmap aspect ratio : " + (double) fullMap.getWidth() / fullMap.getHeight());
            }

            dragged.setLongClickable(false);
            dragged.setOnTouchListener(null);
        }
        return false;
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    private static class MyDragShadowBuilder extends View.DragShadowBuilder {

        int scaledWidth;
        int scaledHeight;
        private Point mScaleFactor;

        MyDragShadowBuilder(View v, int scaledWidth, int scaledHeight) {
            super(v);
            this.scaledWidth = scaledWidth;
            this.scaledHeight = scaledHeight;

        }

        @Override
        public void onProvideShadowMetrics(Point size, Point touch) {
            int width = scaledWidth;
            int height = scaledHeight;

            size.set(width, height);

            // Sets size parameter to member that will be used for scaling shadow image.
            mScaleFactor = size;

            // Sets the touch point's position to be in the middle of the drag shadow
            touch.set(width / 2, height / 2);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            // Draws the ColorDrawable in the Canvas passed in from the system.
            canvas.scale(mScaleFactor.x / (float) getView().getWidth(), mScaleFactor.y / (float) getView().getHeight());
            getView().draw(canvas);
        }

    }
}