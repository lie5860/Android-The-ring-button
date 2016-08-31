package com.example.opendor;
import com.example222.opendor.R;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {
	private ImageView bg;
	int screen_w;
	int screen_h;
	AnimatorSet animatorSet;
	private DisplayMetrics metrics;
	int halfWidth;
	int height;
	int clearTranslateY,billTranslateY,orderTranslateY,tel1TranslateY,tel2TranslateY,
	tel3TranslateY,tel4TranslateY,tel5TranslateY,tel6TranslateY,bgTranslateY;
	ImageView telID1,telID2,telID3,telID4,telID5,telID6;
	Animation animation, animation1;
	TranslateAnimation translateAnimation,translateAnimationbill,translateAnimationclear,translateAnimationorder;
	Button clear,bill,order;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		screen_w = getWindowManager().getDefaultDisplay().getWidth();
		screen_h = getWindowManager().getDefaultDisplay().getHeight();
		bg = (ImageView) findViewById(R.id.split_ll);
		
		clear=(Button) findViewById(R.id.clear);
		order=(Button) findViewById(R.id.order);
		bill=(Button) findViewById(R.id.bill);
		//加载3张图片或更少动画很顺畅，但是加载4张之后动画卡顿很明显，所以下面采用硬件缓存解决
		 telID1=(ImageView) findViewById(R.id.telId1);
		 telID2=(ImageView) findViewById(R.id.telId2);
		 telID3=(ImageView) findViewById(R.id.telId3);
		 telID4=(ImageView) findViewById(R.id.telId4);
		 telID5=(ImageView) findViewById(R.id.telId5);
		 telID6=(ImageView) findViewById(R.id.telId6);
		telID1.setOnClickListener(this);
		telID2.setOnClickListener(this);
		telID3.setOnClickListener(this);
		telID4.setOnClickListener(this);
		telID5.setOnClickListener(this);
		telID6.setOnClickListener(this);
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		Log.e("byf","------------metrics.widthPixels ="+metrics.widthPixels);
		Log.e("byf","------------metrics.heightPixels ="+metrics.heightPixels);
		halfWidth = metrics.widthPixels/2;
		height = metrics.heightPixels;
	}
	
	public void clear (View view){
	}
	
	public void order (View view){
		clear.setVisibility(View.VISIBLE);
		 bill.setVisibility(View.VISIBLE);
		 //http://blog.oneapm.com/apm-tech/288.html
		 //动画放映过程中每帧画面可能都要重绘。如果使用视图层，渲染过的视图可以存入离屏缓存以待将来重用，而无需每帧重绘。
		 clear.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		 order.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		 bill.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		 telID1.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		 telID2.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		 telID3.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		 telID4.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		 telID5.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		 telID6.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		 bg.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		//坑1：：：ofFloat方法后两个参数第一个：控件当前位置，第二个，控件要移动到的位置，负数为左移，整数为右移（和坐标系相同）
		 animatorSet=new AnimatorSet();
		 //计算动画偏移量
		 measureMinationResult(height);
		//坑2：：：控件的这些get方法必须要在oncreate执行完成后再去获取
		ObjectAnimator animatorclear = ObjectAnimator.ofFloat(clear, "translationY",0f,-clearTranslateY);  
		ObjectAnimator animatorbill  = ObjectAnimator.ofFloat(bill, "translationY",0f,-billTranslateY);  
		ObjectAnimator animatororder = ObjectAnimator.ofFloat(order, "translationY",0f,-orderTranslateY); 
		
		ObjectAnimator animatortelID1 = ObjectAnimator.ofFloat(telID1, "translationY",0f,-tel1TranslateY);  
		ObjectAnimator animatortelID2 = ObjectAnimator.ofFloat(telID2, "translationY",0f,-tel2TranslateY);  
		ObjectAnimator animatortelID3 = ObjectAnimator.ofFloat(telID3, "translationY",0f,-tel3TranslateY);  
		ObjectAnimator animatortelID4 = ObjectAnimator.ofFloat(telID4, "translationY",0f,-tel4TranslateY);  
		ObjectAnimator animatortelID5 = ObjectAnimator.ofFloat(telID5, "translationY",0f,-tel5TranslateY);  
		ObjectAnimator animatortelID6 = ObjectAnimator.ofFloat(telID6, "translationY",0f,-tel6TranslateY);  
		
		ObjectAnimator animatorbg = ObjectAnimator.ofFloat(bg, "translationY",0f,-bgTranslateY);  
		
		animatorSet.play(animatorclear).with(animatororder).with(animatorbill).with(animatorbg).with(animatortelID1).
		with(animatortelID2).with(animatortelID3).with(animatortelID4).with(animatortelID5).with(animatortelID6);
		animatorSet.setDuration(1500); 
		animatorSet.start(); 
		animatorSet.addListener(new AnimatorListenerAdapter() {  
			 @Override  
			 public void onAnimationEnd(Animator animation) {
//				 int orderSizeW=order.getWidth();
//				 ObjectAnimator animatororder = ObjectAnimator.ofFloat(order, "translationX",0f,0f);  
//				 animatororder.setDuration(0);
//				 animatororder.start();
				 clear.setLayerType(View.LAYER_TYPE_NONE, null);
				 order.setLayerType(View.LAYER_TYPE_NONE, null);
				 bill.setLayerType(View.LAYER_TYPE_NONE, null);
				 telID1.setLayerType(View.LAYER_TYPE_NONE, null);
				 telID2.setLayerType(View.LAYER_TYPE_NONE, null);
				 telID3.setLayerType(View.LAYER_TYPE_NONE, null);
				 telID4.setLayerType(View.LAYER_TYPE_NONE, null);
				 telID5.setLayerType(View.LAYER_TYPE_NONE, null);
				 telID6.setLayerType(View.LAYER_TYPE_NONE, null);
				 bg.setLayerType(View.LAYER_TYPE_NONE, null);
				 clear.setVisibility(View.GONE);
				 bill.setVisibility(View.GONE);
			 }  
			}); 
	}
	 
	private void measureMinationResult(int height) {
		// TODO Auto-generated method stub
		//计算每个控件应该上移的长度
		int clearSizeTop=clear.getTop();
		int clearSizeH=clear.getHeight();
		 clearTranslateY=clearSizeTop+clearSizeH;
		
		int billSizeTop=bill.getTop();
		int billSizeH=bill.getHeight();
		 billTranslateY=billSizeTop+billSizeH;
		
		int orderSizeTop=order.getTop();
		int orderSizeH=order.getHeight();
		 orderTranslateY=orderSizeTop+orderSizeH;
		
		
		
		 tel1TranslateY=height;
		 tel2TranslateY=height;
		 tel3TranslateY=height;
		 tel4TranslateY=height;
		 tel5TranslateY=height;
		 tel6TranslateY=height;
		
		 bgTranslateY=height;
	}

	public void bill (View view){
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 Toast.makeText(MainActivity.this, v.getTag().toString(), 1*1000).show(); 
	}

}
