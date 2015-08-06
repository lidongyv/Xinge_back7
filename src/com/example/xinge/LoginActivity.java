package com.example.xinge;
import java.sql.Date;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.CountDownTimer;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.FrameLayout.LayoutParams;
public class LoginActivity extends Activity implements OnClickListener{
	//private TimeCount timeCount;
	private Button loginr2; 
	private Button sendcheck;
	// �ֻ��������
		private EditText inputPhoneEt;

		// ��֤�������
		private EditText inputCodeEt;

		// ��ȡ��֤�밴ť
		private Button requestCodeBtn;

		// ע�ᰴť
		private Button commitBtn;

		//
		int i = 30;
		private DataForm dataform;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
		
		
	}
	private void init() {
		inputPhoneEt = (EditText) findViewById(R.id.phonenum);
		inputCodeEt = (EditText) findViewById(R.id.checknum);
		requestCodeBtn = (Button) findViewById(R.id.sendcheck);
		commitBtn = (Button) findViewById(R.id.loginr2);
		requestCodeBtn.setOnClickListener(this);
		commitBtn.setOnClickListener(this);

		// ����������֤sdk
		SMSSDK.initSDK(this, "6b58be011ebc", "35ae24dc530dc2f2216c5162fc564e7f");
		EventHandler eventHandler = new EventHandler(){
			@Override
			public void afterEvent(int event, int result, Object data) {
				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				handler.sendMessage(msg);
			}
		};
		//ע��ص������ӿ�
		SMSSDK.registerEventHandler(eventHandler);
	}
	public void onClick(View v) {
		String phoneNums = inputPhoneEt.getText().toString();
		switch (v.getId()) {
		case R.id.sendcheck:
			// 1. ͨ�������ж��ֻ���
			if (!judgePhoneNums(phoneNums)) {
				return;
			} // 2. ͨ��sdk���Ͷ�����֤
			SMSSDK.getVerificationCode("86", phoneNums);

			// 3. �Ѱ�ť��ɲ��ɵ����������ʾ����ʱ�����ڻ�ȡ��
			requestCodeBtn.setClickable(false);
			requestCodeBtn.setText("���·���(" + i + ")");
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (; i > 0; i--) {
						handler.sendEmptyMessage(-9);
						if (i <= 0) {
							break;
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					handler.sendEmptyMessage(-8);
				}
			}).start();
			break;

		case R.id.loginr2:
			String phoneNums2 = inputPhoneEt.getText().toString();
			dataform = (DataForm)getApplication();
			dataform.setphonenum(phoneNums2);
			SMSSDK.submitVerificationCode("86", phoneNums, inputCodeEt
					.getText().toString());
			createProgressBar();
			
			break;
		}
	}

	/**
	 * ���ô�������
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == -9) {
				requestCodeBtn.setText("���·���(" + i + ")");
			} else if (msg.what == -8) {
				requestCodeBtn.setText("��ȡ��֤��");
				requestCodeBtn.setClickable(true);
				i = 30;
			} else {
				int event = msg.arg1;
				int result = msg.arg2;
				Object data = msg.obj;
				Log.e("event", "event=" + event);
				if (result == SMSSDK.RESULT_COMPLETE) {
					// ����ע��ɹ��󣬷���MainActivity,Ȼ����ʾ
					if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// �ύ��֤��ɹ�
						Toast.makeText(getApplicationContext(), "�ύ��֤��ɹ�",
								Toast.LENGTH_SHORT).show();
						
						Intent intent = new Intent(LoginActivity.this,
								SetkeyActivity.class);
						startActivity(intent);
						finish();
						overridePendingTransition(R.anim.zoomin, R.anim.zoomout); 
					} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
						Toast.makeText(getApplicationContext(), "��֤���Ѿ�����",
								Toast.LENGTH_SHORT).show();
					} else {
						((Throwable) data).printStackTrace();
					}
				}
			}
		}
	};
	
	
	/**
	 * �ж��ֻ������Ƿ����
	 * 
	 * @param phoneNums
	 */
	private boolean judgePhoneNums(String phoneNums) {
		if (isMatchLength(phoneNums, 11)
				&& isMobileNO(phoneNums)) {
			return true;
		}
		Toast.makeText(this, "�ֻ�������������",Toast.LENGTH_SHORT).show();
		return false;
	}

	/**
	 * �ж�һ���ַ�����λ��
	 * @param str
	 * @param length
	 * @return
	 */
	public static boolean isMatchLength(String str, int length) {
		if (str.isEmpty()) {
			return false;
		} else {
			return str.length() == length ? true : false;
		}
	}
	
	/**
	 * ��֤�ֻ���ʽ
	 */
	public static boolean isMobileNO(String mobileNums) {
		/*
		 * �ƶ���134��135��136��137��138��139��150��151��157(TD)��158��159��187��188
		 * ��ͨ��130��131��132��152��155��156��185��186 ���ţ�133��153��180��189����1349��ͨ��
		 * �ܽ��������ǵ�һλ�ض�Ϊ1���ڶ�λ�ض�Ϊ3��5��8������λ�õĿ���Ϊ0-9
		 */
		String telRegex = "[1][358]\\d{9}";// "[1]"�����1λΪ����1��"[358]"����ڶ�λ����Ϊ3��5��8�е�һ����"\\d{9}"��������ǿ�����0��9�����֣���9λ��
		if (TextUtils.isEmpty(mobileNums))
			return false;
		else
			return mobileNums.matches(telRegex);
	}

	/**
	 * progressbar
	 */
	private void createProgressBar() {
		FrameLayout layout = (FrameLayout) findViewById(android.R.id.content);
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.CENTER;
		ProgressBar mProBar = new ProgressBar(this);
		mProBar.setLayoutParams(layoutParams);
		mProBar.setVisibility(View.VISIBLE);
		layout.addView(mProBar);
	}
	
	@Override
	protected void onDestroy() {
		SMSSDK.unregisterAllEventHandler();
		super.onDestroy();
	}
}
