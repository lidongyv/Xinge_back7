package com.example.xinge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






//import android.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CActivity extends Activity{
		private DataForm dataForm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.self);
		 ImageButton share=(ImageButton)this.findViewById(R.id.share);  
	        share.setOnClickListener(new View.OnClickListener() {  
	              
	            @Override  
	            public void onClick(View v) {  
	                Intent intent=new Intent(Intent.ACTION_SEND);    
	                intent.setType("image/*");    
	                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");    
	                intent.putExtra(Intent.EXTRA_TEXT, "快来使用信鸽吧！");       
	                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    
	                startActivity(Intent.createChooser(intent, getTitle()));    
	                  
	            }  
	        });  
		  ListView list2 = (ListView) findViewById(R.id.ListViewmy);
	       dataForm =(DataForm)getApplication();
	        //生成动态数组，加入数据
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map = new HashMap<String, Object>();
			map.put("ItemTitle", dataForm.getname());
			map.put("ItemText","账号"+dataForm.getphonenum());
			map.put("ItemImage", R.drawable.i1);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("ItemTitle", "个人认证");
			map.put("ItemText", "");
			map.put("ItemImage", R.drawable.i2);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("ItemTitle", "感恩机制");
			map.put("ItemText", "");
			map.put("ItemImage", R.drawable.i3);
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("ItemTitle", "我的现金");
			map.put("ItemText", "");
			map.put("ItemImage", R.drawable.i4);
			list.add(map);
			map = new HashMap<String, Object>();
			map.put("ItemTitle", "我的金币");
			map.put("ItemText", "");
			map.put("ItemImage", R.drawable.i5);
			list.add(map);
			map = new HashMap<String, Object>();
			map.put("ItemTitle", "通用设置");
			map.put("ItemText", "");
			map.put("ItemImage", R.drawable.i5);
			list.add(map);
			map = new HashMap<String, Object>();
			map.put("ItemTitle", "关于信鸽");
			map.put("ItemText", "");
			map.put("ItemImage", R.drawable.i5);
			list.add(map);
			/*for(int i=0;i<10;i++)
	        {
	        	HashMap<String, Object> map = new HashMap<String, Object>();
	        	map.put("ItemImage", R.drawable.checked);//图像资源的ID
	        	map.put("ItemTitle", "Level "+i);
	        	map.put("ItemText", "Finished in 1 Min 54 Secs, 70 Moves! ");
	        	list.add(map);
	        }*/
	        //生成适配器的Item和动态数组对应的元素
	        SimpleAdapter listItemAdapter = new SimpleAdapter(this,list,//数据源 
	            R.layout.vlist,//ListItem的XML实现
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
	               
	                Intent intent = null;
	               
	             
	                switch(arg2)  {
	                case 0:
	                	intent = new Intent(arg1.getContext(), SelfinformationActivity.class) ;  
	                break;
	                case 1: 
	                	intent =new Intent(arg1.getContext(), IdentityActivity.class)  ;  //SelfinformationActivity.class) ;
	                break; 
	                case 2: 
	                	intent =new Intent(arg1.getContext(), GanenActivity.class)  ;  
	                	 break;
	                case 3:
	                	intent =new Intent(arg1.getContext(), MoneyActivity.class)  ;             
	                break;
	               
	                case 4: 
	                	intent =new Intent(arg1.getContext(), GoldActivity.class)  ;  
	                break;
	                case 5: 
	                	intent = new Intent(arg1.getContext(), SettingActivity.class) ;
	                break;
	                case 6: 
	                	intent = new Intent(arg1.getContext(), AboutActivity.class) ;
	                break;
	                       
	            }   
	                        startActivity(intent);
	                        overridePendingTransition(R.anim.out_from_left, R.anim.in_from_right); 
	            }   
	        });
	        
	      
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {  

        if (keyCode == KeyEvent.KEYCODE_BACK) {  

            exit();  

            return false;  

        } else {  

            return super.onKeyDown(keyCode, event);  

        }  

    }  
    boolean isExit;  
    public void exit(){  

        if (!isExit) {  

            isExit = true;  

            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();  

            mHandler.sendEmptyMessageDelayed(0, 2000);  

        } else {  

            Intent intent = new Intent(Intent.ACTION_MAIN);  

            intent.addCategory(Intent.CATEGORY_HOME);  

            startActivity(intent);  

            System.exit(0);  

        }  

    }  
    Handler mHandler = new Handler() {  

  	  

        @Override  

        public void handleMessage(Message msg) {  

            // TODO Auto-generated method stub   

            super.handleMessage(msg);  

            isExit = false;  

        }  

  

    };  
}
