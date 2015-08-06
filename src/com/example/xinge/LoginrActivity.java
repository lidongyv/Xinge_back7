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
import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;
public class LoginrActivity extends Activity {
	private Button loginr2;
	private EditText phoneEditText;
	private EditText key;
	private DataForm dataform;
	private SharedPreferences sp;  
	private ProgressDialog progressDialog = null;
	//private TextView testTextView;
	Handler mainHandler;
	private String pString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginr);
		loginr2 = (Button) findViewById(R.id.loginr3);
		phoneEditText = (EditText)findViewById(R.id.phone);
		key = (EditText)findViewById(R.id.key);
		//testTextView = (TextView)findViewById(R.id.test);
	
		  mainHandler = new Handler(){
	        	
	            @Override
	             public void handleMessage(Message msg) {
	                 // TODO Auto-generated method stub
	            	// progressDialog.dismiss();
	                if(msg.obj.toString().equals("fail"))
	                {
	                	Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();  
	               }
	                
	                else {
	                	Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();  
	                	parseJSON(msg.obj.toString());
	                	Intent intent = new Intent(LoginrActivity.this, MainTabActivity.class);
	 					startActivity(intent);
	 					finish();
	 					overridePendingTransition(R.anim.zoomin, R.anim.zoomout); 
					}
	                /*
	                 parseJSON(msg.obj.toString);
	                 */
	             }
	         };
		loginr2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根 
				progressDialog = ProgressDialog.show(LoginrActivity.this, "请稍等正在登陆...", "获取数据中...", true);
				Thread thread = new Thread(){
					public void run(){
						
						sendmessage();
							
						 progressDialog.dismiss();
					}
				};
				thread.start();
				
				
			}
		});
	}
	void sendmessage(){
		String keyString;
		String phoneString;
		phoneString = phoneEditText.getText().toString();
		keyString = key.getText().toString();
		dataform = (DataForm)getApplication();
		dataform.setphonenum(phoneString);
		dataform.setkey(keyString);
		String phonenum = dataform.getphonenum();
        String key = dataform.getkey();
        sp=getSharedPreferences("data", MODE_PRIVATE);
		SharedPreferences.Editor editor= sp.edit();
		editor.putString("name", phonenum);
		editor.putString("password", key);
		editor.commit();
        HttpClient httpClient = new DefaultHttpClient();
   	 HttpPost httpPost = new HttpPost(dataform.geturl());
   	 try{
   		
   	        try{
   			
   	        	JSONObject person = new JSONObject();
   	        	String request="login";
   	        	person.put("RequestType_", request);
   	        	person.put("ID_", phonenum);
   	        	person.put("Password_", key);
   	        	pString=person.toString();
   	        StringEntity se =new StringEntity(person.toString());
   	        se.setContentType("application/json;charset=UTF-8");  
               se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,  
                       "application/json;charset=UTF-8"));  
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
	private void parseJSON(String result) {
 		JSONObject jsonObj;
 		
 		try{
 			dataform.setperson(result);
 			jsonObj = dataform.getperson();			
 			String id = jsonObj.getString("ID_");
 			String name = jsonObj.getString("Name_");
 			String gold = jsonObj.getString("Gold_");
 			String cash = jsonObj.getString("Cash_");
 			dataform.setmoney(cash);
 			dataform.setgold(gold);
 			dataform.setname(name);
 			
 		}
 		catch(JSONException e){
 			e.printStackTrace();
 		}
 	}
}
