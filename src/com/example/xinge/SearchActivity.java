package com.example.xinge;




import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.view.ExpandTabView;
import com.example.view.ViewLeft;
import com.example.view.ViewMiddle;
import com.example.view.ViewRight;

public class SearchActivity extends Activity {

	private ExpandTabView expandTabView;
	private ArrayList<View> mViewArray = new ArrayList<View>();
	private ViewLeft viewLeft;
	private ViewMiddle viewMiddle;
	private ViewRight viewRight;
	private EditText searchtext;
	private Button submitsearch;
	private Button nextpage;
	private DataForm dataForm;
	Handler mainHandler;
	private ImageButton returnButton;
	private String value;
	 private ProgressBar circleProgressBar;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		 circleProgressBar = (ProgressBar)findViewById(R.id.circleProgressBar);  
		 circleProgressBar.setIndeterminate(false); 
		initView();
		initVaule();
		initListener();
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
	            	circleProgressBar.setVisibility(View.GONE);
	                 // TODO Auto-generated method stub
	                if(msg.obj.toString().equals("fail"))
	                {
	                	 Toast.makeText(getApplicationContext(), "获得失败", Toast.LENGTH_SHORT).show();  
	               }
	                
	                else {
	                	
	                	parseJSON(msg.obj.toString());
					}
	                /*
	                 parseJSON(msg.obj.toString);
	                 */
	             }
	         };
	       submitsearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				dataForm=(DataForm)getApplication();
				circleProgressBar.setVisibility(View.VISIBLE);
				Thread thread = new Thread(){
					public void run(){
						
						sendmessage();
						
					}
				};
				thread.start();
				
				
			}
		});
	       nextpage.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				dataForm=(DataForm)getApplication();
				circleProgressBar.setVisibility(View.VISIBLE);
				Thread thread = new Thread(){
					public void run(){
						
						sendmessage();
						//circleProgressBar.setVisibility(View.GONE);
					}
				};
				thread.start();
				
				
			}
		});
	       
	       
	}

	void sendmessage(){
		String search=searchtext.getText().toString();
		dataForm = (DataForm)getApplication();
        HttpClient httpClient = new DefaultHttpClient();
   	 HttpPost httpPost = new HttpPost(dataForm.geturl2());
   	 try{
   		
   	        try{
   	        	dataForm.setlocation(0, viewMiddle.place[0]);
   	        	dataForm.setlocation(1, viewMiddle.place[1]);
   	        	dataForm.setlocation(2, viewMiddle.place[2]);
   	        	JSONObject person = new JSONObject();
   	        	String request="search";
   	        	person.put("RequestType_", request);
   	        	person.put("Name_", search);
   	        	person.put("Position_", dataForm.getlocation());
   	        	
   	        StringEntity se =new StringEntity(person.toString(),"UTF-8");
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
	private void initView() {
		
		expandTabView = (ExpandTabView) findViewById(R.id.expandtab_view);
		viewLeft = new ViewLeft(this);
		viewMiddle = new ViewMiddle(this);
		viewRight = new ViewRight(this);
		searchtext=(EditText)findViewById(R.id.searchtext);
		submitsearch=(Button)findViewById(R.id.searchsubmit);
		nextpage=(Button)findViewById(R.id.nextpage);
	}

	private void initVaule() {
		
		mViewArray.add(viewLeft);
		mViewArray.add(viewMiddle);
		mViewArray.add(viewRight);
		ArrayList<String> mTextArray = new ArrayList<String>();
		mTextArray.add("距离");
		mTextArray.add("区域");
		mTextArray.add("选择");
		expandTabView.setValue(mTextArray, mViewArray);
		expandTabView.setTitle(viewLeft.getShowText(), 0);
		expandTabView.setTitle(viewMiddle.getShowText(), 1);
		expandTabView.setTitle(viewRight.getShowText(), 2);
		
	}

	private void initListener() {
		
		viewLeft.setOnSelectListener(new ViewLeft.OnSelectListener() {

			@Override
			public void getValue(String distance, String showText) {
				onRefresh(viewLeft, showText);
			}
		});
		
		viewMiddle.setOnSelectListener(new ViewMiddle.OnSelectListener() {
			
			@Override
			public void getValue(String showText) {
				
				onRefresh(viewMiddle,showText);
				
			}
		});
		
		viewRight.setOnSelectListener(new ViewRight.OnSelectListener() {

			@Override
			public void getValue(String distance, String showText) {
				onRefresh(viewRight, showText);
			}
		});
		
	}
	
	private void onRefresh(View view, String showText) {
		
		expandTabView.onPressBack();
		int position = getPositon(view);
		if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
			expandTabView.setTitle(showText, position);
		}
		Toast.makeText(SearchActivity.this, showText, Toast.LENGTH_SHORT).show();
		//Toast.makeText(SearchActivity.this, viewMiddle.place[0]+viewMiddle.place[1], Toast.LENGTH_SHORT).show();

	}
	
	private int getPositon(View tView) {
		for (int i = 0; i < mViewArray.size(); i++) {
			if (mViewArray.get(i) == tView) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public void onBackPressed() {
		
		if (!expandTabView.onPressBack()) {
			finish();
		}
		
	}
	private void parseJSON(String result) {
 			value=result;
 			ListView list2 = (ListView) findViewById(R.id.ListViewmy);
 	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
 	       
 			try { 		        
 			 JSONArray 	jsonObjs =  new JSONObject(result).getJSONArray("List_");       
 	            for(int i = 0; i < jsonObjs.length() ; i++){ 
 	                JSONObject jsonObj = ((JSONObject)jsonObjs.opt(i)) ;
 	                Map<String, Object> map = new HashMap<String, Object>();
 	        		map.put("ItemTitle", jsonObj.getString("Name_"));
 	        		map.put("ItemText", jsonObj.getString("Brief_"));
 	        		map.put("ItemImage", R.drawable.address_book);
 	        		list.add(map);
 	               
 	            } 
 	            
 	        } catch (JSONException e) { 
 	            System.out.println("Jsons parse error !"); 
 	            e.printStackTrace(); 
 	        } 
 	   
 	        //生成动态数组，加入数据
 			
 		 SimpleAdapter listItemAdapter = new SimpleAdapter(this,list,//数据源 
 		            R.layout.xlist3,//ListItem的XML实现
 		            //动态数组与ImageItem对应的子项        
 		            new String[] {"ItemImage","ItemTitle", "ItemText"}, 
 		            //ImageItem的XML文件里面的一个ImageView,两个TextView ID
 		            new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText}
 		        );
 		       
 		        //添加并且显示
 		        list2.setAdapter(listItemAdapter);
 		        
 		        //添加点击
 		        list2.setOnItemClickListener(new OnItemClickListener() {    
 		            @Override  
 		            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {   
 		                Intent intent = new Intent(); 
 		               try {
						JSONArray 	jsonObjs =  new JSONObject(value).getJSONArray("List_");
						JSONObject jsonObj ;
 			             
 		                switch(arg2)  {
 		                case 0:
 		                	jsonObj= ((JSONObject)jsonObjs.opt(0)) ;
 		                	dataForm.setcompany(jsonObj.toString());
 		                	 
 		                	intent = new Intent(arg1.getContext(), MerchantActivity.class) ;  
 		                break;
 		                case 1: 
 		                	jsonObj = ((JSONObject)jsonObjs.opt(1)) ;
		                	
 		                	dataForm.setcompany(jsonObj.toString());
 		                	intent = new Intent(arg1.getContext(), MerchantActivity.class) ;  //SelfinformationActivity.class) ;
 		                break;
 		                
 		                case 3: 
 		                	jsonObj = ((JSONObject)jsonObjs.opt(3)) ;
		                	
 		                	dataForm.setcompany(jsonObj.toString());
 		                	intent = new Intent(arg1.getContext(), MerchantActivity.class) ;
 	                    
 		                break;
 		                case 2: 
 		                	jsonObj = ((JSONObject)jsonObjs.opt(2)) ;
		                	
 		                	dataForm.setcompany(jsonObj.toString());
 		                	intent = new Intent(arg1.getContext(), MerchantActivity.class) ;
 		                	 break;
 		                case 4: 
 		                	jsonObj = ((JSONObject)jsonObjs.opt(4)) ;
		                  
 		                	dataForm.setcompany(jsonObj.toString());
 		                	intent = new Intent(arg1.getContext(), MerchantActivity.class) ;
 		                	break;
 		                case 5: 
 		                	jsonObj = ((JSONObject)jsonObjs.opt(5)) ;
		                	 
 		                	dataForm.setcompany(jsonObj.toString());
 		                	intent = new Intent(arg1.getContext(), MerchantActivity.class) ;
 		                break;
 		                       
 		            }   
 		                startActivity(intent);
 		                finish();
 		               overridePendingTransition(R.anim.out_from_left, R.anim.in_from_right); 
 		               } catch (JSONException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}   
 		            }   
 		        });
 	}
}
