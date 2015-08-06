package com.example.xinge;


import sun.geoffery.uploadpic.NetUtil;
import sun.geoffery.uploadpic.SelectPicPopupWindow;
import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.text.TextUtils;
import android.text.style.UpdateAppearance;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class NameidentityActivity extends Activity implements OnClickListener{
	private ImageButton returnButton;
	private Context mContext;
	private int num=0;
	private Button submit;
	private ImageView picImg;
	private ImageView picImg2;
	private SelectPicPopupWindow menuWindow; // �Զ����ͷ��༭������
	
	private Uri photoUri;
	/** ʹ����������ջ�ȡͼƬ */
	public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
	/** ʹ������е�ͼƬ */
	public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
	/** ��ȡ����ͼƬ·�� */
	private String picPath = "";
	private String picPath2 = "";
	private static ProgressDialog pd;
    private String resultStr = "";	// ����˷��ؽ����
	private String imgUrl = "http://120.24.219.49/726287_temphead.jpg";
	private EditText nameEditText;
	private EditText namenum;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_nameidentity);
		submit=(Button)findViewById(R.id.submit_name);
		submit.setOnClickListener(this);
		mContext = NameidentityActivity.this;
		returnButton=(ImageButton)findViewById(R.id.back);
		returnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				finish();
			}
		});
		
		initViews();
		
	}
	private void initViews() {

		nameEditText=(EditText)findViewById(R.id.name_foridentity);
		namenum=(EditText)findViewById(R.id.namenum);
		picImg = (ImageView) findViewById(R.id.picImg);
		picImg.setOnClickListener(this);
		picImg2 = (ImageView) findViewById(R.id.picImg2);
		picImg2.setOnClickListener(this);
	}
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.picImg:// ���ͼƬ����¼�
			// ��ҳ��ײ�����һ�����壬ѡ�����ջ��Ǵ����ѡ������ͼƬ
			num=1;
			menuWindow = new SelectPicPopupWindow(mContext, itemsOnClick);  
			menuWindow.showAtLocation(findViewById(R.id.uploadLayout), 
					Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); 
			break;
		case R.id.picImg2:// ���ͼƬ����¼�
			// ��ҳ��ײ�����һ�����壬ѡ�����ջ��Ǵ����ѡ������ͼƬ
			num=2;
			menuWindow = new SelectPicPopupWindow(mContext, itemsOnClick);  
			menuWindow.showAtLocation(findViewById(R.id.uploadLayout), 
					Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); 
			break;
		case R.id.submit_name:// ���ͼƬ����¼�
			// ��ҳ��ײ�����һ�����壬ѡ�����ջ��Ǵ����ѡ������ͼƬ
			updatepic();
			break;
		default:
			break;
		}
	}
		private OnClickListener itemsOnClick = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ���ص�������
				menuWindow.dismiss();
				
				switch (v.getId()) {
				case R.id.takePhotoBtn:// ����
					takePhoto();
					break;
				case R.id.pickPhotoBtn:// ���ѡ��ͼƬ
					pickPhoto();
					break;
				case R.id.cancelBtn:// ȡ��
					break;
				default:
					break;
				}
			}
		}; 
		private void takePhoto() {
			// ִ������ǰ��Ӧ�����ж�SD���Ƿ����
			String SDState = Environment.getExternalStorageState();
			if (SDState.equals(Environment.MEDIA_MOUNTED)) {

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				/***
				 * ��Ҫ˵��һ�£����²���ʹ����������գ����պ��ͼƬ����������е� 
				 * ����ʹ�õ����ַ�ʽ��һ���ô����ǻ�ȡ��ͼƬ�����պ��ԭͼ
				 * �����ʹ��ContentValues�����Ƭ·���Ļ������պ��ȡ��ͼƬΪ����ͼ������
				 */
				ContentValues values = new ContentValues();
				photoUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
				intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
				startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
			} else {
				Toast.makeText(this, "�ڴ濨������", Toast.LENGTH_LONG).show();
			}
		}

		/***
		 * �������ȡͼƬ
		 */
		private void pickPhoto() {
			Intent intent = new Intent();
			// ���Ҫ�����ϴ�����������ͼƬ����ʱ����ֱ��д�磺"image/jpeg �� image/png�ȵ�����"
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);
		}

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// ���ȡ����ť
			if(resultCode == RESULT_CANCELED){
				return;
			}
			
			// ����ʹ��ͬһ������������ֿ�дΪ�˷�ֹ�Ժ���չ��ͬ������
			switch (requestCode) {
			case SELECT_PIC_BY_PICK_PHOTO:// �����ֱ�Ӵ�����ȡ
				doPhoto(requestCode, data);
				break;
			case SELECT_PIC_BY_TACK_PHOTO:// ����ǵ����������ʱ
				doPhoto(requestCode, data);
				break;
			}
			super.onActivityResult(requestCode, resultCode, data);
		}

		/**
		 * ѡ��ͼƬ�󣬻�ȡͼƬ��·��
		 * 
		 * @param requestCode
		 * @param data
		 */
		private void doPhoto(int requestCode, Intent data) {
			
			// �����ȡͼƬ����Щ�ֻ����쳣�������ע��
			if (requestCode == SELECT_PIC_BY_PICK_PHOTO) {
				if (data == null) {
					Toast.makeText(this, "ѡ��ͼƬ�ļ�����", Toast.LENGTH_LONG).show();
					return;
				}
				photoUri = data.getData();
				if (photoUri == null) {
					Toast.makeText(this, "ѡ��ͼƬ�ļ�����", Toast.LENGTH_LONG).show();
					return;
				}
			}
			
			String[] pojo = { MediaColumns.DATA };
			// The method managedQuery() from the type Activity is deprecated
			//Cursor cursor = managedQuery(photoUri, pojo, null, null, null);
			Cursor cursor = mContext.getContentResolver().query(photoUri, pojo, null, null, null);
			if (cursor != null) {
				int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
				cursor.moveToFirst();
				if(num==1)
				picPath = cursor.getString(columnIndex);
				else if(num==2)
					picPath2 = cursor.getString(columnIndex);
				// 4.0���ϵİ汾���Զ��ر� (4.0--14;; 4.0.3--15)
				if (Integer.parseInt(Build.VERSION.SDK) < 14) {
					cursor.close();
				}
			}
			
			updatepic();

		}
		// ���ͼƬ����Ҫ�����ϴ���������
			
		private void updatepic(){
			int check=0;
			if (picPath != null && (	picPath.endsWith(".png") || 
										picPath.endsWith(".PNG") || 
										picPath.endsWith(".jpg") || 
										picPath.endsWith(".JPG"))) {

				
				BitmapFactory.Options option = new BitmapFactory.Options();
				// ѹ��ͼƬ:��ʾ����ͼ��СΪԭʼͼƬ��С�ļ���֮һ��1Ϊԭͼ
				option.inSampleSize = 1;
				// ����ͼƬ��SDCard·������Bitmap
				Bitmap bm = BitmapFactory.decodeFile(picPath, option);
				// ��ʾ��ͼƬ�ؼ���
				picImg.setImageBitmap(bm);
				
				//pd = ProgressDialog.show(mContext, null, "�����ϴ�ͼƬ�����Ժ�...");
			//	new Thread(uploadImageRunnable).start();
			} else {
				check=1;
				Toast.makeText(this, "ѡ������ͼƬ�ļ�����ȷ", Toast.LENGTH_LONG).show();
			}

			if (picPath2 != null && (	picPath2.endsWith(".png") || 
										picPath2.endsWith(".PNG") || 
										picPath2.endsWith(".jpg") || 
										picPath2.endsWith(".JPG"))) {

				
				BitmapFactory.Options option = new BitmapFactory.Options();
				// ѹ��ͼƬ:��ʾ����ͼ��СΪԭʼͼƬ��С�ļ���֮һ��1Ϊԭͼ
				option.inSampleSize = 1;
				// ����ͼƬ��SDCard·������Bitmap
				Bitmap bm = BitmapFactory.decodeFile(picPath2, option);
				// ��ʾ��ͼƬ�ؼ���
				picImg2.setImageBitmap(bm);
				
				//pd = ProgressDialog.show(mContext, null, "�����ϴ�ͼƬ�����Ժ�...");
			//	new Thread(uploadImageRunnable).start();
			} else {
				check=1;
				Toast.makeText(this, "ѡ����ͼƬ�ļ�����ȷ", Toast.LENGTH_LONG).show();
			}
//			if(check==0)
//				new Thread(uploadImageRunnable).start();
//			else Toast.makeText(this, "ѡ��ͼƬ�ļ�����ȷ��������ѡ��", Toast.LENGTH_LONG).show();
				
		}
		/**
		 * ʹ��HttpUrlConnectionģ��post�������ļ�
		 * �ϴ�ƽʱ����ʹ�ã��Ƚ��鷳
		 * ԭ���ǣ� �����ļ��ϴ������ݸ�ʽ��Ȼ����ݸ�ʽ������Ӧ�ķ��͸����������ַ�����
		 */
		Runnable uploadImageRunnable = new Runnable() {
			@Override
			public void run() {
				
				if(TextUtils.isEmpty(imgUrl)){
					Toast.makeText(mContext, "��û�������ϴ���������·����", Toast.LENGTH_SHORT).show();
					return;
				}
				
				Map<String, String> textParams = new HashMap<String, String>();
				Map<String, File> fileparams = new HashMap<String, File>();
				
				try {
					// ����һ��URL����
					URL url = new URL(imgUrl);
					textParams = new HashMap<String, String>();
					fileparams = new HashMap<String, File>();
					// Ҫ�ϴ���ͼƬ�ļ�
					File file = new File(picPath);
					fileparams.put("image", file);
					// ����HttpURLConnection����������л�ȡ��ҳ����
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					// �������ӳ�ʱ���ǵ��������ӳ�ʱ,������粻��,Androidϵͳ�ڳ���Ĭ��ʱ����ջ���Դ�жϲ�����
					conn.setConnectTimeout(5000);
					// �����������������POST��������������������
					conn.setDoOutput(true);
					// ����ʹ��POST�ķ�ʽ����
					conn.setRequestMethod("POST");
					// ���ò�ʹ�û��棨���׳������⣩
					conn.setUseCaches(false);
					// �ڿ�ʼ��HttpURLConnection�����setRequestProperty()����,��������HTML�ļ�ͷ
					conn.setRequestProperty("ser-Agent", "Fiddler");
					// ����contentType
					conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + NetUtil.BOUNDARY);
					OutputStream os = conn.getOutputStream();
					DataOutputStream ds = new DataOutputStream(os);
					NetUtil.writeStringParams(textParams, ds);
					NetUtil.writeFileParams(fileparams, ds);
					NetUtil.paramsEnd(ds);
					// ���ļ���������,Ҫ�ǵü�ʱ�ر�
					os.close();
					// ���������ص���Ӧ��
					int code = conn.getResponseCode(); // ��Internet��ȡ��ҳ,��������,����ҳ��������ʽ������
					// ����Ӧ������ж�
					if (code == 200) {// ���ص���Ӧ��200,�ǳɹ�
						// �õ����緵�ص�������
						InputStream is = conn.getInputStream();
						resultStr = NetUtil.readString(is);
					} else {
						Toast.makeText(mContext, "����URLʧ�ܣ�", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0);// ִ�к�ʱ�ķ���֮��������handler
			}
		};

		Handler handler = new Handler(new Handler.Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					pd.dismiss();
					
					try {
						JSONObject jsonObject = new JSONObject(resultStr);
						// ��������ַ�����1����Ϊ�����ɹ����
						if (jsonObject.optString("status").equals("1")) {
		
							// ����ƴ�ӷ���˵˵ʱ�õ���ͼƬ·��
							// ����˷��ص�JsonObject��������ȡ��ͼƬ������URL·��
							String imageUrl = jsonObject.optString("imageUrl");
							// ��ȡ�����е�ͼƬ·��
							Toast.makeText(mContext, imageUrl, Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(mContext, jsonObject.optString("statusMessage"), Toast.LENGTH_SHORT).show();
						}
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
				}
				return false;
			}
		});
}
