package com.example.xinge;

import sun.geoffery.uploadpic.SelectPicPopupWindow;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class GanenActivity extends Activity implements OnClickListener {
	private Button jieshaoButton;
	private Button ganenButton;
	private Button fenxiang;
	private ImageButton returnButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ganen);
		jieshaoButton=(Button)findViewById(R.id.jieshao);
		ganenButton=(Button)findViewById(R.id.ganen);
		fenxiang=(Button)findViewById(R.id.fenxiang);
		jieshaoButton.setOnClickListener(this);
		ganenButton.setOnClickListener(this);
		fenxiang.setOnClickListener(this);
		returnButton=(ImageButton)findViewById(R.id.back);
		returnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		
	}
	public void onClick(View v) {
		Intent intent=null;
		switch (v.getId()) {
		case R.id.jieshao:// 添加图片点击事件
			// 从页面底部弹出一个窗体，选择拍照还是从相册选择已有图片
			intent=new Intent(GanenActivity.this, GanenjieshaoActivity.class);
			startActivity(intent);
			break;
		case R.id.ganen:// 添加图片点击事件
			// 从页面底部弹出一个窗体，选择拍照还是从相册选择已有图片
			intent=new Intent(GanenActivity.this, GanenmoneyActivity.class);
			startActivity(intent);
			break;
		case R.id.fenxiang:// 添加图片点击事件
		                intent=new Intent(Intent.ACTION_SEND);    
		                intent.setType("image/*");    
		                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");    
		                intent.putExtra(Intent.EXTRA_TEXT, "信鸽感恩功能上线啦！！");       
		                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    
		                startActivity(Intent.createChooser(intent, getTitle()));    
		                  
		        
		default:
			break;
		}
	}
}
