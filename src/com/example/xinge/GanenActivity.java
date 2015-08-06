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
				// TODO �Զ����ɵķ������
				finish();
			}
		});
		
	}
	public void onClick(View v) {
		Intent intent=null;
		switch (v.getId()) {
		case R.id.jieshao:// ���ͼƬ����¼�
			// ��ҳ��ײ�����һ�����壬ѡ�����ջ��Ǵ����ѡ������ͼƬ
			intent=new Intent(GanenActivity.this, GanenjieshaoActivity.class);
			startActivity(intent);
			break;
		case R.id.ganen:// ���ͼƬ����¼�
			// ��ҳ��ײ�����һ�����壬ѡ�����ջ��Ǵ����ѡ������ͼƬ
			intent=new Intent(GanenActivity.this, GanenmoneyActivity.class);
			startActivity(intent);
			break;
		case R.id.fenxiang:// ���ͼƬ����¼�
		                intent=new Intent(Intent.ACTION_SEND);    
		                intent.setType("image/*");    
		                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");    
		                intent.putExtra(Intent.EXTRA_TEXT, "�Ÿ�ж���������������");       
		                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    
		                startActivity(Intent.createChooser(intent, getTitle()));    
		                  
		        
		default:
			break;
		}
	}
}
