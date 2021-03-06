package com.example.viewex.activity;

import com.example.viewanimator.R;
import com.example.viewex.widget.viewanimator.WSCircleArc;
import com.example.viewex.widget.viewanimator.WSCircleBar;
import com.example.viewex.widget.viewanimator.WSCircleCD;
import com.example.viewex.widget.viewanimator.WSCircleFace;
import com.example.viewex.widget.viewanimator.WSCircleJump;
import com.example.viewex.widget.viewanimator.WSCircleRing;
import com.example.viewex.widget.viewanimator.WSCircleRipple;
import com.example.viewex.widget.viewanimator.WSCircleRise;
import com.example.viewex.widget.viewanimator.WSCircleRotate;
import com.example.viewex.widget.viewanimator.WSCircleSun;
import com.example.viewex.widget.viewanimator.WSCubes;
import com.example.viewex.widget.viewanimator.WSEatBeans;
import com.example.viewex.widget.viewanimator.WSFiveStar;
import com.example.viewex.widget.viewanimator.WSGearLoading;
import com.example.viewex.widget.viewanimator.WSGears;
import com.example.viewex.widget.viewanimator.WSJump;
import com.example.viewex.widget.viewanimator.WSLineProgress;
import com.example.viewex.widget.viewanimator.WSMaterialLoading;
import com.example.viewex.widget.viewanimator.WSSwapLoading;

import android.app.Activity;
import android.os.Bundle;

public class AnimatorLoadingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animator_loading);
		
		WSCircleCD mWSCircleCD = (WSCircleCD) findViewById(R.id.load_cd);
        mWSCircleCD.startAnimator();
        
        WSCircleSun mWSCircleSun = (WSCircleSun) findViewById(R.id.load_sun);
        mWSCircleSun.startAnimator();

        WSCircleRing mWSCircleRing= (WSCircleRing) findViewById(R.id.load_ring);
        mWSCircleRing.startAnimator();

        WSCircleFace mWSCircleFace= (WSCircleFace) findViewById(R.id.load_face);
        mWSCircleFace.startAnimator();

        WSCircleJump mWSCircleJump= (WSCircleJump) findViewById(R.id.load_jump);
        mWSCircleJump.startAnimator();

        WSGears mWSGears= (WSGears) findViewById(R.id.load_gear);
        mWSGears.startAnimator();

        WSJump mWSJump= (WSJump) findViewById(R.id.load_mjump);
        mWSJump.startAnimator();

        WSLineProgress mWSLineProgress= (WSLineProgress) findViewById(R.id.load_line_progress);
        mWSLineProgress.startAnimator();

        WSEatBeans mWSEatBeans= (WSEatBeans) findViewById(R.id.load_eat);
        mWSEatBeans.startAnimator();

        WSCubes mWSCubes= (WSCubes) findViewById(R.id.load_cube);
        mWSCubes.startAnimator();

        WSFiveStar mWSFiveStarView= (WSFiveStar) findViewById(R.id.load_mfive);
        mWSFiveStarView.setRegularPolygon(5);
        mWSFiveStarView.startAnimator();

        WSCircleRise mWSCircleRise= (WSCircleRise) findViewById(R.id.load_rise);
        mWSCircleRise.startAnimator();

        WSCircleBar mWSCircleBar= (WSCircleBar) findViewById(R.id.load_bar);
        mWSCircleBar.startAnimator();

        WSCircleArc mWSCircleArc= (WSCircleArc) findViewById(R.id.load_arc);
        mWSCircleArc.startAnimator();

        WSMaterialLoading mWSMaterialLoading= (WSMaterialLoading) findViewById(R.id.load_material);
        mWSMaterialLoading.startAnimator();

        WSGearLoading mWSGearLoading= (WSGearLoading) findViewById(R.id.load_gear_loading);
        mWSGearLoading.startAnimator();

        WSSwapLoading mWSSwapLoading= (WSSwapLoading) findViewById(R.id.load_swap);
        mWSSwapLoading.startAnimator();

        WSCircleRotate mWSCircleRotate= (WSCircleRotate) findViewById(R.id.load_rotate);
        mWSCircleRotate.startAnimator();

        WSCircleRipple mWSCircleRipple= (WSCircleRipple) findViewById(R.id.load_ripple);
        mWSCircleRipple.startAnimator();
	}
}
