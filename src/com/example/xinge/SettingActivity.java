package com.example.xinge;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class SettingActivity extends Activity {
	private EditText key;
	private Button poston;
	 private DataForm dataForm;
	 private ImageButton returnButton;
	 private Button tuichu;
	 Handler mainHandler;
	 private SharedPreferences sp;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		key =(EditText)findViewById(R.id.changekey);
		poston= (Button)findViewById(R.id.poston);
		returnButton=(ImageButton)findViewById(R.id.back);
		returnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		tuichu=(Button)findViewById(R.id.tuichu);
		tuichu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				sp=getSharedPreferences("data", MODE_PRIVATE);
				SharedPreferences.Editor editor= sp.edit();
				editor.clear();  
	            editor.commit();  
	            Intent intent = new Intent(Intent.ACTION_MAIN);  

	            intent.addCategory(Intent.CATEGORY_HOME);  

	            startActivity(intent);  

	            System.exit(0);  
				
			}
		});
		 mainHandler = new Handler(){
	        	
	            @Override
	             public void handleMessage(Message msg) {
	                 // TODO Auto-generated method stub
	            	 if(msg.obj.toString().equals("success"))
		                { 
	            		
	            		 Toast.makeText(getApplicationContext(), "更新成功",
								Toast.LENGTH_SHORT).show();
		                	Intent intent = new Intent(SettingActivity.this,MainTabActivity.class);
		                	startActivity(intent);
		                	finish();
		                	overridePendingTransition(R.anim.zoomout, R.anim.zoomin); 
		               }
		                
		                else {
		                	
		                	Toast.makeText(getApplicationContext(), "更新失败",
									Toast.LENGTH_SHORT).show();
							
						}
	             }
	         };
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
	}
	void sendData(){
		dataForm = (DataForm)getApplication();
		 HttpClient httpClient = new DefaultHttpClient();
		 HttpPost httpPost = new HttpPost(dataForm.geturl());
		 try{			
		        try{
		        	String temp=dataForm.getphonenum();
		        	JSONObject person = new JSONObject();
		        	String request="update";
		        	person.put("RequestType_", request);
		        	person.put("ID_", temp);	
		        	person.put("Password_", key.getText());
		       
		        	
		        StringEntity se =new StringEntity(person.toString());
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
