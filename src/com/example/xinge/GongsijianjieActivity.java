package com.example.xinge;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class GongsijianjieActivity extends Activity {
	private ImageButton returnButton;
	private TextView text2;
	private TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gongsijianjie);
		returnButton=(ImageButton)findViewById(R.id.back);
		returnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		text2=(TextView)findViewById(R.id.call);
		text2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_CALL, Uri .parse("tel:400-090-7618"));    
				startActivity(intent); 
			}
		});
		text=(TextView)findViewById(R.id.web);
		text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				 Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.11315.tv"));    
                 //建立Intent对象，传入uri  
                 startActivity(intent);   
				
			}
		});
	}
}
