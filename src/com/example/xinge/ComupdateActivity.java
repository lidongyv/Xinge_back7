package com.example.xinge;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import  android.widget.CalendarView;

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

import android.R.string;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class ComupdateActivity extends Activity {
	 private EditText typeEditText;
	 private EditText faxEditText;
	 private EditText phoneEditText;
	 private Button poston;
	 private DataForm dataForm;
	 Handler mainHandler;
	 private ImageButton returnButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comupdate);
		typeEditText=(EditText)findViewById(R.id.ctype);
		faxEditText=(EditText)findViewById(R.id.fax);
		phoneEditText=(EditText)findViewById(R.id.phone);
		poston=(Button)findViewById(R.id.cposton);
		returnButton=(ImageButton)findViewById(R.id.back);
		returnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		poston.setOnClickListener(new OnClickListener() {
			
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
		 mainHandler = new Handler(){
	        	
	            @Override
	             public void handleMessage(Message msg) {
	                 // TODO Auto-generated method stub
	            	 if(msg.obj.toString().equals("success"))
		                { 
	            		// receive2.setText(pString);
	            		
		                	Intent intent = new Intent(ComupdateActivity.this,MainTabActivity.class);
		                	startActivity(intent);
		                	finish();
		                	overridePendingTransition(R.anim.zoomout, R.anim.zoomin); 
		               }
		                
		                else {
		       //         	receive2.setText(pString);
		                	Toast.makeText(getApplicationContext(), "更新失败",
									Toast.LENGTH_SHORT).show();
							
						}
	             }
	         };
	}
	void sendData(){
		dataForm = (DataForm)getApplication();
		 HttpClient httpClient = new DefaultHttpClient();
		 HttpPost httpPost = new HttpPost(dataForm.geturl2());
		 try{			
		        try{
		        	String temp=dataForm.getphonenum();
		        	JSONObject person = new JSONObject();
		        	String request="update";
		        	person.put("RequestType_", request);
		        	
		        	person.put("ID_", temp);	
		        	
		        //	temp = name.getText().toString();
		        	String name2=typeEditText.getText().toString();
		        	person.put("Sort_", name2);
		        	
		        //	temp = birthday.getText().toString();
		        	
		        	String birthday2=faxEditText.getText().toString();

		        	person.put("Fax_", birthday2);
		        	//temp = sex.getText().toString();
		        	String sex2= phoneEditText.getText().toString();
		        	person.put("phone_", sex2);
		        	
		        	
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
}
