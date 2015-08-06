package com.example.xinge;

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
public class CompleteselfActivity extends Activity {
	 private EditText name;
	 private DatePicker birthday;
	 private EditText sex;
	 private EditText email;
	 private EditText qq;
	 private EditText school;
	 private EditText biye;
	 private EditText diploma;
	 private ImageButton returnButton;
	// private TextView receive2;
	// private TextView textview1;
	 private Button poston;
	 private DataForm dataForm;
	 Handler mainHandler;
	 String pString;
	 String dayString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_completeself);
		init();
		
		birthday.init(2015, 3, 15, new OnDateChangedListener() {
			  
            @Override
            public void onDateChanged(DatePicker view, int year,
                    int monthOfYear, int dayOfMonth) {
                // 获取一个日历对象，并初始化为当前选中的时间
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat(
                        "yyyy年MM月dd日");
                dayString=format.format(calendar.getTime());     
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
		        	
		        //	temp = name.getText().toString();
		        	String name2=name.getText().toString();
		        	person.put("Name_", name2);
		        	
		        //	temp = birthday.getText().toString();
		        	
		        	String birthday2;
		        	birthday2=dayString;
		        	person.put("Birthday_", birthday2);
		        	//temp = sex.getText().toString();
		        	String sex2= sex.getText().toString();
		        	person.put("Sex_", sex2);
		        	
		        	//temp = email.getText().toString();
		        	String email2=email.getText().toString();
		        	person.put("Email_", email2);
		        	//temp = qq.getText().toString();
		        	String qq2=qq.getText().toString();
		        	person.put("QQ_", qq2);
		        	
		        	//temp = school.getText().toString();
		        	String school2= school.getText().toString();
		        	person.put("GraduateSchool_", school2);
		        	//temp = biye.getText().toString();
		        	
		        	String biye2=biye.getText().toString();
		        	person.put("GraduateYear_", biye2);
		        	//temp= diploma.getText().toString();
		        	
		        	String diploma2=diploma.getText().toString();
		        	person.put("Diploma_", diploma2);
		        	
		        	person.put("Gold_", dataForm.getgold());
		        	
		        StringEntity se =new StringEntity(person.toString(),"UTF-8");
		        se.setContentType("application/json;charset=UTF-8");  
	            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,  
	                    "application/json;charset=UTF-8"));  
	           // receive2.setText(person.toString());
		        httpPost.setEntity(se);
		        
		        pString=person.toString();
				
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
		name = (EditText)findViewById(R.id.name);
		birthday = (DatePicker)findViewById(R.id.dpPicker);
		sex = (EditText)findViewById(R.id.sex);
		email = (EditText)findViewById(R.id.email);
		qq = (EditText)findViewById(R.id.qq);
		school =(EditText)findViewById(R.id.school);
		biye = (EditText)findViewById(R.id.biye);
		diploma = (EditText)findViewById(R.id.diploma);
		poston = (Button)findViewById(R.id.poston);
		//receive2 = (TextView)findViewById(R.id.receive2);
		//textview1 =(TextView)findViewById(R.id.textView1);
		 mainHandler = new Handler(){
	        	
	            @Override
	             public void handleMessage(Message msg) {
	                 // TODO Auto-generated method stub
	            	 if(msg.obj.toString().equals("success"))
		                { 
	            		// receive2.setText(pString);
	            		 try {
							dataForm.setperson(pString);
						} catch (JSONException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		                	Intent intent = new Intent(CompleteselfActivity.this,MainTabActivity.class);
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
}
