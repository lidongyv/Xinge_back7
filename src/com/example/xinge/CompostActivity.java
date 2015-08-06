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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class CompostActivity extends Activity {
	//private EditText company;
	private EditText reason;
	private EditText title;
	private Button submit;
	private DataForm dataForm;
	private Spinner spinner2;
    private ArrayAdapter adapter2;
	 Handler mainHandler;
	 private ImageButton returnButton;
	 private String typesString ="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compost);
		//company=(EditText)findViewById(R.id.company);
		reason=(EditText)findViewById(R.id.morereason);
		title=(EditText)findViewById(R.id.reason);
		submit=(Button)findViewById(R.id.submit);
		returnButton=(ImageButton)findViewById(R.id.back);
		returnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		 spinner2 = (Spinner) findViewById(R.id.type);
		 adapter2 = ArrayAdapter.createFromResource(this, R.array.spingarr, android.R.layout.simple_spinner_item);
		 
	        //设置下拉列表的风格 
	 adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 
	        //将adapter2 添加到spinner中
	        spinner2.setAdapter(adapter2);
	 
	        //添加事件Spinner事件监听  
	        spinner2.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
	 
	        //设置默认值
	        spinner2.setVisibility(View.VISIBLE);
		 mainHandler = new Handler(){
	        	
	            @Override
	             public void handleMessage(Message msg) {
	                 // TODO Auto-generated method stub
	            	 if(msg.obj.toString().equals("success"))
		                { 
	            		
	            		 Toast.makeText(getApplicationContext(), "发布成功",
								Toast.LENGTH_SHORT).show();
		                	Intent intent = new Intent(CompostActivity.this,MainTabActivity.class);
		                	startActivity(intent);
		                	finish();
		                	overridePendingTransition(R.anim.zoomout, R.anim.zoomin); 
		               }
		                
		                else {
		                	
		                	Toast.makeText(getApplicationContext(), "发布失败",
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
		        	//String companyString=company.getText().toString();
		        	String reasonString=title.getText().toString();
		        	String reasonsString=reason.getText().toString();
		        	String temp=dataForm.getphonenum();
		        	JSONObject person = new JSONObject();
		        	String request="announce";
		        	person.put("RequestType_", request);
		        	person.put("AuthorID_", temp);	
		        	person.put("IType_", typesString);	
		        	//person.put("Name_", companyString);
		        	person.put("Title_", reasonString);
		        	person.put("Brief_", reasonsString);
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
	 class SpinnerXMLSelectedListener implements OnItemSelectedListener{
	        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
	                long arg3) {
	        	typesString=adapter2.getItem(arg2).toString();
	        }
	 
	        public void onNothingSelected(AdapterView<?> arg0) {
	        }
	         
	    }
	
	
	
}
