package com.example.xinge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class VipActivity extends Activity {
	 private ImageButton returnButton;
	 private Button toexplain;
	 private Button buyvipButton;
	 private TextView accountnameTextView;
	 private DataForm dataForm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vip);
		accountnameTextView=(TextView)findViewById(R.id.accountname_in_vip);
		dataForm=(DataForm)getApplication();
		accountnameTextView.setText(dataForm.getname());
		toexplain=(Button)findViewById(R.id.VIP);
		toexplain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(VipActivity.this, VipexplainActivity.class);
				startActivity(intent);
			}
		});
		buyvipButton=(Button)findViewById(R.id.buyvip);
		buyvipButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(VipActivity.this, VipexplainActivity.class);
				startActivity(intent);
			}
		});
		
		
		returnButton=(ImageButton)findViewById(R.id.back);
		returnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
	}
}
