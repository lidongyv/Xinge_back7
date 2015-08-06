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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Toast;

public class StartActivity extends Activity {
	private SharedPreferences sp; 
	private DataForm dataform;
	private String pString;
	private ProgressDialog progressDialog = null;
	Handler mainHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	        final View view = View.inflate(this, R.layout.activity_start, null);
	        setContentView(view);
	           dataform=(DataForm)getApplication();                                                           
	        //渐变展示启动屏
	           mainHandler = new Handler(){
		        	
		            @Override
		             public void handleMessage(Message msg) {
		                 // TODO Auto-generated method stub
		            	// progressDialog.dismiss();
		                if(msg.obj.toString().equals("fail"))
		                {Toast.makeText(getApplicationContext(), "验证失败，请重新登录或者注册", Toast.LENGTH_LONG).show();  
		                	Intent intent = new Intent(StartActivity.this, MainActivity.class);
		         	        startActivity(intent);
		         	        overridePendingTransition(R.anim.alphaout, R.anim.alphain); 
		         	        finish();  
		               }
		                
		                else {
		                	
		                	parseJSON(msg.obj.toString());
		                	Intent intent = new Intent(StartActivity.this, MainTabActivity.class);
		 					Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_LONG).show();  
		 					startActivity(intent);
		 					finish();
		 					overridePendingTransition(R.anim.zoomout, R.anim.zoomin); 
						}
		                /*
		                 parseJSON(msg.obj.toString);
		                 */
		             }
		         };
	        AlphaAnimation aa = new AlphaAnimation(0.1f,1.0f);
	        aa.setDuration(3500);
	        view.startAnimation(aa);
	        aa.setAnimationListener(new AnimationListener()
	        {
	            @Override
	            public void onAnimationEnd(Animation arg0) {
	                redirectTo();
	            }
	            @Override
	            public void onAnimationRepeat(Animation animation) {}
	            @Override
	            public void onAnimationStart(Animation animation) {}
	                                                                          
	        });
	                                                                      
	                                                          
	    }
	void sendmessage(){

		String nameString,passwordString;
		nameString=sp.getString("name","");
		passwordString=sp.getString("password","");
		dataform.setphonenum(nameString);
		dataform.setkey(passwordString);
		String phonenum = dataform.getphonenum();
        String key = dataform.getkey();
        sp=getSharedPreferences("data", MODE_PRIVATE);
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
   		 { //	progressDialog.dismiss();
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
	    /**
	     * 跳转到...
	     */
	    private void redirectTo(){     
	    	sp=getSharedPreferences("data", MODE_PRIVATE);
	    	sp=getSharedPreferences("data", MODE_PRIVATE);
			String nameString,passwordString;
			nameString=sp.getString("name","");
			passwordString=sp.getString("password","");
			progressDialog = ProgressDialog.show(StartActivity.this, "请稍等...", "获取数据中...", true);	
			Thread thread = new Thread(){
				public void run(){ 
					
					sendmessage();
					progressDialog.dismiss();
				}
			};
			thread.start();	
			
	       
	    }
}
