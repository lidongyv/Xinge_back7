package com.example.xinge;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import com.example.xinge.R;
import com.example.xinge.R.id;
import com.example.xinge.R.layout;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ComregisterActivity extends BaseActivity  implements OnWheelChangedListener{
	 private EditText comname;
//	 private EditText sheng;
//	 private EditText shi;
//	 private EditText qu;
	 private EditText combrief;
	 private Button comsubmit;
	 private DataForm dataForm;
	 private ImageButton returnButton;
	 private WheelView mViewProvince;
		private WheelView mViewCity;
		private WheelView mViewDistrict;
	 Handler mainHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comregister);
		init();
		setUpViews();
		setUpListener();
		setUpData();
		returnButton=(ImageButton)findViewById(R.id.back);
		returnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		comsubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Thread thread = new Thread(){
					public void run(){
						sendData();
					}
				};
				thread.start();
				
				
			}
		});
	}
	void sendData(){
		dataForm = (DataForm)getApplication();
		 HttpClient httpClient = new DefaultHttpClient();
		 HttpPost httpPost = new HttpPost(dataForm.geturl2());
		 try{			
		        try{
		        	String temp=dataForm.getphonenum();
		        	JSONObject person = new JSONObject();
		        	String request="register";
		        	person.put("RequestType_", request);		        	
		        	person.put("ID_", temp);	
		        	String name1=comname.getText().toString();
		        	person.put("Name_", name1);
//		        	String sheng1,shi1;
//		        	sheng1= sheng.getText().toString();
//		        	shi1=shi.getText().toString();
		        	dataForm.setlocation(0, mCurrentProviceName);
		        	dataForm.setlocation(1, mCurrentCityName);
		        	dataForm.setlocation(2, mCurrentDistrictName);
		        	person.put("Position_", dataForm.getlocation());
		        	String brief1=combrief.getText().toString();
		        	person.put("Brief_", brief1);
		        	person.put("Logo_", "");
		        StringEntity se =new StringEntity(person.toString(),"UTF-8");
		        se.setContentType("application/json;charset=UTF-8");  
	            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,  
	                    "application/json;charset=UTF-8"));  
	           // receive2.setText(person.toString());
	           
		        httpPost.setEntity(se);
				
				}catch(JSONException ex){
		        	throw new RuntimeException(ex);
		        } catch (UnsupportedEncodingException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			
			 HttpResponse httpResponse = httpClient.execute(httpPost);
			
			 if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
			 {
				 Message msg = mainHandler.obtainMessage(0, EntityUtils.toString(httpResponse.getEntity()));	
				 mainHandler.sendMessage(msg);
			 }		
		 }catch (ClientProtocolException e)
		 {
			 e.printStackTrace();
		 }catch (IOException e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		
		
		
	}
	void init(){
		comname = (EditText)findViewById(R.id.comname);
//		sheng = (EditText)findViewById(R.id.sheng);
//		shi = (EditText)findViewById(R.id.shi);
//		qu = (EditText)findViewById(R.id.qu);
		combrief = (EditText)findViewById(R.id.combrief);
		comsubmit = (Button)findViewById(R.id.comsubmit1);
		//receive2 = (TextView)findViewById(R.id.receive2);
		//textview1 =(TextView)findViewById(R.id.textView1);
		 mainHandler = new Handler(){
	        	
	            @Override
	             public void handleMessage(Message msg) {
	                 // TODO Auto-generated method stub
	            	 if(msg.obj.toString().equals("success"))
		                { 
	            		// receive2.setText(pString);
	            		 Toast.makeText(getApplicationContext(), "正在审核",
								Toast.LENGTH_SHORT).show();
		                	Intent intent = new Intent(ComregisterActivity.this,MainTabActivity.class);
		                	startActivity(intent);
		                	finish();
		                	overridePendingTransition(R.anim.zoomout, R.anim.zoomin); 
		               }
		                
		                else {
		       //         	receive2.setText(pString);
		                	Toast.makeText(getApplicationContext(), "注册失败请联系客服",
							Toast.LENGTH_SHORT).show();
							
						}
	             }
	         };
		
	}
	private void setUpViews() {
		mViewProvince = (WheelView) findViewById(R.id.id_province);
		mViewCity = (WheelView) findViewById(R.id.id_city);
		mViewDistrict = (WheelView) findViewById(R.id.id_district);
	}
	
	private void setUpListener() {
    	// 添加change事件
    	mViewProvince.addChangingListener(this);
    	// 添加change事件
    	mViewCity.addChangingListener(this);
    	// 添加change事件
    	mViewDistrict.addChangingListener(this);
    	// 添加onclick事件   	
    }
	
	private void setUpData() {
		initProvinceDatas();
		mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(ComregisterActivity.this, mProvinceDatas));
		// 设置可见条目数量
		mViewProvince.setVisibleItems(3);
		mViewCity.setVisibleItems(3);
		mViewDistrict.setVisibleItems(3);
		updateCities();
		updateAreas();
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		// TODO Auto-generated method stub
		if (wheel == mViewProvince) {
			updateCities();
		} else if (wheel == mViewCity) {
			updateAreas();
		} else if (wheel == mViewDistrict) {
			mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
			mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
		}
	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		int pCurrent = mViewCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		String[] areas = mDistrictDatasMap.get(mCurrentCityName);

		if (areas == null) {
			areas = new String[] { "" };
		}
		mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
		mViewDistrict.setCurrentItem(0);
	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mViewProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
		mViewCity.setCurrentItem(0);
		updateAreas();
	}

	private void showSelectedResult() {
		Toast.makeText(ComregisterActivity.this, "当前选中:"+mCurrentProviceName+","+mCurrentCityName+","
				+mCurrentDistrictName+","+mCurrentZipCode, Toast.LENGTH_SHORT).show();
	}
}
