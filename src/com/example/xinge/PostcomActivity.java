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

public class PostcomActivity extends Activity {
	private EditText company;
	private EditText reason;
	private EditText title;
	private EditText connect;
	private Button submit;
	private DataForm dataForm;
	private ImageButton returnButton;
	 Handler mainHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_postcom);
		company=(EditText)findViewById(R.id.company);
		reason=(EditText)findViewById(R.id.morereason);
		submit=(Button)findViewById(R.id.submit);
		title=(EditText)findViewById(R.id.title);
		connect=(EditText)findViewById(R.id.connect);
		returnButton=(ImageButton)findViewById(R.id.back);
		returnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				finish();
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
		                	Intent intent = new Intent(PostcomActivity.this,MainTabActivity.class);
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
		
		submit.setOnClickListener(new OnClickListener() {
			
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
		 HttpPost httpPost = new HttpPost(dataForm.geturl3());
		 try{			
		        try{
		        	String companyString=company.getText().toString();
		        	String reasString=reason.getText().toString();
		        	String temp=dataForm.getphonenum();
		        	String tString=title.getText().toString();
		        	String cString=connect.getText().toString();
		        	JSONObject person = new JSONObject();
		        	String request="report";
		        	person.put("RequestType_", request);
		        	person.put("ReportID_", temp);	
		        	person.put("ReportedName_", companyString);
		        	person.put("Reason_", reasString);
		        	person.put("Title_", tString);
		        	person.put("ReportedPhone_", cString);
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
