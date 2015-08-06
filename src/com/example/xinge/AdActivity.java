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
	       
	        //���ɶ�̬���飬��������
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map = new HashMap<String, Object>();
			map.put("ItemTitle", "������ˮ��8��");
			map.put("ItemText", "����39.5Ԫ����ֵ50Ԫ�Ĵ�����ˮ�ȴ���ȯ1�ţ��ṩ���WiFi��"
					+ "���������������ζ");
			map.put("ItemImage", R.drawable.i1);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("ItemTitle", "�̼Ҽ��");
			map.put("ItemText", "�ൺ��������������޹�˾��"
					+ "���¡��������Ļ����������й���һ������ˮ��Ϊ��ɫ��"
					+ "��Ӫ���ຣ�ʼҳ�������ʳ�����ִ�ͳ��Ҳ����Ļ����������������");
			map.put("ItemImage", R.drawable.side);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("ItemTitle", "��ϵ�绰");
			map.put("ItemText", "12345678901");
			map.put("ItemImage", R.drawable.side);
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("ItemTitle", "�̼ҵ�ַ");
			map.put("ItemText", "�ൺ���б����Ӽ�·128��");
			map.put("ItemImage", R.drawable.side);
			list.add(map);
			map = new HashMap<String, Object>();
			map.put("ItemTitle", "Ͷ���е���Ϣ");
			map.put("ItemText", "�ൺ��������������޹�˾��"
					+ "���¡��������Ļ����������й���һ������ˮ��Ϊ��ɫ��"
					+ "��Ӫ���ຣ�ʼҳ�������ʳ�����ִ�ͳ��Ҳ����Ļ����������������");
			map.put("ItemImage", R.drawable.i5);
			list.add(map);
			
		
			/*for(int i=0;i<10;i++)
	        {
	        	HashMap<String, Object> map = new HashMap<String, Object>();
	        	map.put("ItemImage", R.drawable.checked);//ͼ����Դ��ID
	        	map.put("ItemTitle", "Level "+i);
	        	map.put("ItemText", "Finished in 1 Min 54 Secs, 70 Moves! ");
	        	list.add(map);
	        }*/
	        //������������Item�Ͷ�̬�����Ӧ��Ԫ��
	        SimpleAdapter listItemAdapter = new SimpleAdapter(this,list,//����Դ 
	            R.layout.vlist2,//ListItem��XMLʵ��
	            //��̬������ImageItem��Ӧ������        
	            new String[] {"ItemImage","ItemTitle", "ItemText"}, 
	            //ImageItem��XML�ļ������һ��ImageView,����TextView ID
	            new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText}
	        );
	       
	        //��Ӳ�����ʾ
	        list2.setAdapter(listItemAdapter);
	}
}
