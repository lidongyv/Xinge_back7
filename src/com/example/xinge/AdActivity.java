package com.example.xinge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AdActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ad);
		 ListView list2 = (ListView) findViewById(R.id.ListViewmy);
	       
	        //生成动态数组，加入数据
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map = new HashMap<String, Object>();
			map.put("ItemTitle", "船歌鱼水饺8折");
			map.put("ItemText", "仅售39.5元！价值50元的船歌鱼水饺代金券1张，提供免费WiFi，"
					+ "尽情与好友享受美味");
			map.put("ItemImage", R.drawable.i1);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("ItemTitle", "商家简介");
			map.put("ItemText", "青岛船歌餐饮管理有限公司，"
					+ "旗下“船歌渔文化餐厅”是中国第一家以鱼水饺为特色、"
					+ "经营各类海鲜家常精致美食、体现传统渔家餐饮文化的休闲主题餐厅。");
			map.put("ItemImage", R.drawable.side);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("ItemTitle", "联系电话");
			map.put("ItemText", "12345678901");
			map.put("ItemImage", R.drawable.side);
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("ItemTitle", "商家地址");
			map.put("ItemText", "青岛市市北区延吉路128号");
			map.put("ItemImage", R.drawable.side);
			list.add(map);
			map = new HashMap<String, Object>();
			map.put("ItemTitle", "投放中的信息");
			map.put("ItemText", "青岛船歌餐饮管理有限公司，"
					+ "旗下“船歌渔文化餐厅”是中国第一家以鱼水饺为特色、"
					+ "经营各类海鲜家常精致美食、体现传统渔家餐饮文化的休闲主题餐厅。");
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
	            R.layout.vlist2,//ListItem的XML实现
	            //动态数组与ImageItem对应的子项        
	            new String[] {"ItemImage","ItemTitle", "ItemText"}, 
	            //ImageItem的XML文件里面的一个ImageView,两个TextView ID
	            new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText}
	        );
	       
	        //添加并且显示
	        list2.setAdapter(listItemAdapter);
	}
}
