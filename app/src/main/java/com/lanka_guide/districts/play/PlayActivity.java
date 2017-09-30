package com.lanka_guide.districts.play;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lanka_guide.districts.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chanya on 2017/09/27.
 */

public class PlayActivity extends Activity {
    ImageView img1;
    ImageView img2;
    RelativeLayout linearLayout;
    LinearLayout availableDistricts;
    private ViewPager mViewPager;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_activity);

        img1 = (ImageView) findViewById(R.id.imageView1);
        img2 = (ImageView) findViewById(R.id.imageView2);

        linearLayout = (RelativeLayout) findViewById(R.id.map_layout);
        availableDistricts = (LinearLayout) findViewById(R.id.availableDistricts);

        List<Integer> districtCropImages = new ArrayList<>();
        districtCropImages.add(R.drawable.moneragala_district_crop);
        districtCropImages.add(R.drawable.batticalo_district_crop);
        districtCropImages.add(R.drawable.colombo_district_crop);
        districtCropImages.add(R.drawable.gampaha_district_crop);

        for (final int i : districtCropImages) {
            final ImageView img3 = new ImageView(this);
            img3.setImageResource(i);

            img3.setOnLongClickListener(

                    new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            ClipData.Item item = new ClipData.Item((CharSequence) Integer.toString(i));


                            ClipData dragData = new ClipData(Integer.toString(i),
                                    new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);

                            Bitmap fullMap = BitmapFactory.decodeResource(getResources(), R.drawable.colombo_district);
                            Bitmap bMap = BitmapFactory.decodeResource(getResources(), i);

                            int scaledWidth = (int) (((double) bMap.getWidth() / fullMap.getWidth()) * img1.getWidth());
                            int scaledHeight = (int) (((double) bMap.getHeight() / fullMap.getHeight()) * img1.getHeight());


                            Log.d(PlayActivity.class.getName(), "scaled height : " + scaledHeight);
                            Log.d(PlayActivity.class.getName(), "scales width : " + scaledWidth);

                            Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, scaledWidth, scaledHeight, true);

                            View.DragShadowBuilder myShadow = new MyDragShadowBuilder(img3, scaledWidth, scaledHeight);
                            v.startDrag(dragData, myShadow, v, 0);

                            img3.setImageBitmap(bMapScaled);

                            return false;
                        }
                    }

            );

            img3.setAdjustViewBounds(true);
            availableDistricts.addView(img3);


        }

        img2.setOnLongClickListener(

                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());


                        ClipData dragData = new ClipData((CharSequence) v.getTag(),
                                new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);

                        View.DragShadowBuilder myShadow = new View.DragShadowBuilder(img2);

                        v.startDrag(dragData, myShadow, v, 0);

                        return false;
                    }
                }

        );

        img2.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View view, DragEvent event) {

                final int action = event.getAction();

                View view1 = (View) event.getLocalState();

                // Handles each of the expected events
                switch (action) {

                    case DragEvent.ACTION_DRAG_STARTED:
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        break;
                    case DragEvent.ACTION_DROP:

                        break;
                    default:
                        break;
                }

                return true;
            }
        });

        img1.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View view, DragEvent event) {

                final int action = event.getAction();

                // Handles each of the expected events
                switch (action) {

                    case DragEvent.ACTION_DRAG_STARTED:
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:

                        break;
                    case DragEvent.ACTION_DROP:

                        ImageView dragged = (ImageView) event.getLocalState();

                        int x_cord = (int) event.getX();
                        int y_cord = (int) event.getY();
                        if (event.getClipData().getItemAt(0).getText().equals(Integer.toString(R.drawable
                                .galle_district_crop)) && x_cord > 350 && x_cord < 450 && y_cord > 1000 &&
                                y_cord < 1050) {


                            dragged.setX(x_cord - (dragged.getWidth() / 2));
                            dragged.setY(y_cord - (dragged.getHeight() / 2));

                            availableDistricts.removeView(dragged);
                            linearLayout.addView(dragged);

                        }

                        if (event.getClipData().getItemAt(0).getText().equals(Integer.toString(R.drawable
                                .moneragala_district_crop)) && x_cord > 600 && x_cord < 700 && y_cord > 800 && y_cord
                                < 900) {
                            if (dragged.getX() == 686 || dragged.getY() == 700) {
                                return false;
                            }
                            availableDistricts.removeView(dragged);
                            linearLayout.addView(dragged);


                            dragged.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                            Log.d(PlayActivity.class.getName(), "get X : " + dragged.getX());
                            Log.d(PlayActivity.class.getName(), "get Y : " + dragged.getY());

                            dragged.setX(685);
                            dragged.setY(700);
                        }
                        break;
                    default:
                        break;
                }

                return true;
            }
        });
    }

    private static class MyDragShadowBuilder extends View.DragShadowBuilder {

        int scaledWidth;
        int scaledHeight;
        private Point mScaleFactor;

        // Defines the constructor for myDragShadowBuilder
        public MyDragShadowBuilder(View v, int scaledWidth, int scaledHeight) {

            // Stores the View parameter passed to myDragShadowBuilder.
            super(v);

            this.scaledWidth = scaledWidth;
            this.scaledHeight = scaledHeight;

        }

        // Defines a callback that sends the drag shadow dimensions and touch point back to the
        // system.
        @Override
        public void onProvideShadowMetrics(Point size, Point touch) {
            // Defines local variables
            int width;
            int height;


            // Sets the width of the shadow to half the width of the original View
//            width = getView().getWidth() * 2;
            width = scaledWidth;
            // Sets the height of the shadow to half the height of the original View
//            height = getView().getHeight() * 2;
            height = scaledHeight;

            // Sets the size parameter's width and height values. These get back to the system
            // through the size parameter.
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