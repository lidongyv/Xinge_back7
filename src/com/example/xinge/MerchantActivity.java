package com.example.xinge;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;  
import android.net.Uri;  
import android.R.integer;
//import android.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MerchantActivity extends Activity{
	private DataForm dataForm;
	private Button getgold;
	Handler mainHandler2;
	private String urlString;
	 private ImageButton returnButton;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dataForm=(DataForm)getApplication();
		setContentView(R.layout.activity_merchant);
		returnButton=(ImageButton)findViewById(R.id.back);
		returnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		Intent intent = getIntent();  
        String value = dataForm.getcompany();  
       // Toast.makeText(getApplicationContext(), dataForm.getcompany(), Toast.LENGTH_SHORT).show(); 
        ListView list2 = (ListView) findViewById(R.id.ListViewmy);  
		  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//	        //生成动态数组，加入数据
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(value);
		
	        Map<String, Object> map = new HashMap<String, Object>();
			map.put("ItemTitle", jsonObj.getString("Name_") );
			map.put("ItemText", "");
			map.put("ItemImage", R.drawable.i1);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("ItemTitle", "商家简介");
			map.put("ItemText",jsonObj.getString("Brief_"));
			map.put("ItemImage", R.drawable.side);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("ItemTitle", "联系电话");
			map.put("ItemText", jsonObj.getString("ID_"));
			map.put("ItemImage", R.drawable.side);
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("ItemTitle", "商家地址");
			map.put("ItemText", jsonObj.getString("Position_"));
			map.put("ItemImage", R.drawable.side);
			list.add(map);
			map = new HashMap<String, Object>();
			map.put("ItemTitle", "查看更多诚信信息");
			map.put("ItemText", "点击跳转");
			map.put("ItemImage", R.drawable.side);
			list.add(map);
			urlString="http://s.11315.tv/appindexda.aspx?cid="+jsonObj.getString("cn11315ID_");
	        //生成适配器的Item和动态数组对应的元素
	        SimpleAdapter listItemAdapter = new SimpleAdapter(this,list,//数据源 
	            R.layout.vlist2,//ListItem的XML实现
	            //动态数组与ImageItem对应的子项        
	            new String[] {"ItemImage","ItemTitle", "ItemText"}, 
	            //ImageItem的XML文件里面的一个ImageView,两个TextView ID
	            new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText}
	        );
    //添加并且显示
	        list2.setAdapter(listItemAdapter);
	        } catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		  list2.setOnItemClickListener(new OnItemClickListener() {    
	            @Override  
	            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {   
	               
	               
	               if(arg2==4)
	               {   
	            	   Uri uri = Uri.parse(urlString);  
	               //通过Uri获得编辑框里的//地址，加上http://是为了用户输入时可以不要输入  
	            	   Intent intent = new Intent(Intent.ACTION_VIEW,uri);    
	                   //建立Intent对象，传入uri  
	                   startActivity(intent);    
	           // overridePendingTransition(R.anim.out_from_left, R.anim.in_from_right);
	            }
	               
	            }   
	        });
	        mainHandler2 = new Handler(){   	
	            @Override
	             public void handleMessage(Message msg) {
	                 // TODO Auto-generated method stub
	            	 if(msg.obj.toString().equals("success"))
		                { 	
	            		 
	            		 dataForm.setgold(Integer.toString((Integer.parseInt(dataForm.getgold())+10)));
	            		 Toast.makeText(getApplicationContext(), "恭喜获得金币",
								Toast.LENGTH_SHORT).show();
	            			Intent intent = new Intent(MerchantActivity.this,MainTabActivity.class);
		                	startActivity(intent);
		                	finish();
		                	overridePendingTransition(R.anim.zoomout, R.anim.zoomin); 
		                
		               }
		                
		                else {
		                	
		                	Toast.makeText(getApplicationContext(), "获得金币失败",
									Toast.LENGTH_SHORT).show();
							
						}
	             }
	         };
	        getgold=(Button)findViewById(R.id.getgold);
	        getgold.setOnClickListener(new OnClickListener() {
				
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
		 HttpClient httpClient = new DefaultHttpClient();
		 HttpPost httpPost = new HttpPost(dataForm.geturl());
		 try{			
		        try{
		        	String temp=dataForm.getphonenum();
		        	JSONObject person = new JSONObject();
		        	String request="moneychanged";
		        	person.put("RequestType_", request);		        	
		        	person.put("AccountID_", temp);	
		        	person.put("Number_", "10");
		        	person.put("ItemInfo_", "查询企业诚信信息");
		        	person.put("Mode_", "Gold");
		        	
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
				 Message msg = mainHandler2.obtainMessage(0, EntityUtils.toString(httpResponse.getEntity()));	
				 mainHandler2.sendMessage(msg);
			 }		
		 }catch (ClientProtocolException e)
		 {
			 e.printStackTrace();
		 }catch (IOException e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		
		
		
	}
	private void parseJSON(String value){
		dataForm=(DataForm)getApplication(); 
        ListView list2 = (ListView) findViewById(R.id.ListViewmy);  
 		try{
 			 
 			
 				JSONObject jsonObj = new JSONObject(value);			

 			        
 			        //添加点击
 			
 		}
 		catch(JSONException e){
 			e.printStackTrace();
 		}
 	}
}
