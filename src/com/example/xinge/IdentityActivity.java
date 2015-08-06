package com.example.xinge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class IdentityActivity extends Activity {
private DataForm dataForm;
private ImageButton returnButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_identity);
		returnButton=(ImageButton)findViewById(R.id.back);
		returnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		dataForm=(DataForm)getApplication();
		 ListView list2 = (ListView) findViewById(R.id.ListViewmy);
	       dataForm =(DataForm)getApplication();
	        //生成动态数组，加入数据
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map = new HashMap<String, Object>();
			map.put("ItemTitle","邮箱认证");
			map.put("ItemText","点击认证");
			//map.put("ItemImage", R.drawable.email);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("ItemTitle", "实名认证");
			map.put("ItemText", "点击认证");
			//map.put("ItemImage", R.drawable.name);
			list.add(map);

		
	
	        //生成适配器的Item和动态数组对应的元素
	        SimpleAdapter listItemAdapter = new SimpleAdapter(this,list,//数据源 
	            R.layout.vlist3,//ListItem的XML实现
	            //动态数组与ImageItem对应的子项        
	            new String[] {"ItemTitle", "ItemText"}, //"ItemImage",
	            //ImageItem的XML文件里面的一个ImageView,两个TextView ID
	            new int[] {R.id.ItemTitle,R.id.ItemText}//R.id.ItemImage,
	        );
	       
	        //添加并且显示
	        list2.setAdapter(listItemAdapter);
	        
	        //添加点击
	        list2.setOnItemClickListener(new OnItemClickListener() {    
	            @Override  
	            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {   
	               
	                Intent intent = null;
	               
	             
	                switch(arg2)  {
	                case 0:
	                	intent = new Intent(arg1.getContext(), EmailActivity.class) ;  
	                break;
	                case 1: 
	                	intent =new Intent(arg1.getContext(),NameidentityActivity.class)  ;  //SelfinformationActivity.class) ;
	                break; 
	                
	                       
	            }   
	                        startActivity(intent);
	                        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right); 
	            }   
	        });
	}
}
