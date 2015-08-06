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

public class SetkeyActivity extends Activity {
	private Button loginr2;
	private DataForm dataform;
	private EditText key1;
	private EditText key2;
	Handler mainHandler;
	private ProgressDialog progressDialog = null;
	//private TextView text1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setkey);
		loginr2 = (Button) findViewById(R.id.loginr2);
		key1= (EditText)findViewById(R.id.key1);
		key2 = (EditText)findViewById(R.id.key2);
		//text1=(TextView)findViewById(R.id.textView1);
		  mainHandler = new Handler(){
	        	
	            @Override
	             public void handleMessage(Message msg) {
	                 // TODO Auto-generated method stub
	                if(msg.obj.toString().equals("success"))
	                { 
	                	Intent intent = new Intent(SetkeyActivity.this,MainTabActivity.class);
	                	startActivity(intent);
	                	finish();
	                	overridePendingTransition(R.anim.zoomin, R.anim.zoomout); 
	               }
	                
	                else {
	                	Toast.makeText(getApplicationContext(), "注册失败",
								Toast.LENGTH_SHORT).show();
						key1.setText("failed to register");
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
				String keyString;
				keyString= key1.getText().toString();
				String keyString2;
				keyString2 = key2.getText().toString();
				if(keyString.equals(keyString2))
				{
					progressDialog = ProgressDialog.show(SetkeyActivity.this, "请稍等正在登陆...", "获取数据中...", true);	
					
				Thread thread = new Thread(){
					public void run(){
						sendmessage();
						progressDialog.dismiss();
					}
				};
				thread.start();
				
				
				}
				else 
					{key1.setText("");
					key2.setText("");
					Toast.makeText(getApplicationContext(), "两次密码不相同请重新输入",
							Toast.LENGTH_SHORT).show();
					}
			}
		});
	}
	void sendmessage(){
		String keyString;
		keyString= key1.getText().toString();
		dataform = (DataForm)getApplication();
		dataform.setkey(keyString);
		String phonenum = dataform.getphonenum();
        String key = dataform.getkey();
        HttpClient httpClient = new DefaultHttpClient();
   	 HttpPost httpPost = new HttpPost(dataform.geturl());
   	 try{
   		
   	        try{
   			
   	        	JSONObject person = new JSONObject();
   	        	String request="register";
   	        	person.put("RequestType_", request);
   	        	person.put("ID_", phonenum);
   	        	person.put("Password_", key);
   	        	
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
}
