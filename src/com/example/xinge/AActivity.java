package com.example.xinge;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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










//import android.R;
import android.content.Context;  
import android.util.AttributeSet;  
import android.widget.GridView;  
import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView.ScaleType;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.content.Intent;  
public class AActivity extends Activity{
	private ViewPager viewPager; // android-support-v4中的滑动组件
	private List<ImageView> imageViews; // 滑动的图片集合
	private GridView gview;
	private List<Map<String, Object>> data_list;
	private SimpleAdapter sim_adapter;
	// 图片封装为一个数组
	private int[] icon = { R.drawable.address_book, R.drawable.calendar,
			R.drawable.camera, R.drawable.clock,  };
	private String[] iconName = { "1", "2", "3", "4" };
	private String[] titles; // 图片标题
	private int[] imageResId; // 图片ID
	private List<View> dots; // 图片标题正文的那些点
	private String value;
	private TextView tv_title;
	private int currentItem = 0; // 当前图片的索引号
	Handler mainHandler;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button change;
	private DataForm dataForm;
	 private ProgressBar circleProgressBar;  
	// An ExecutorService that can schedule commands to run after a given delay,
	// or to execute periodically.
	private ScheduledExecutorService scheduledExecutorService;

	// 切换当前显示的图片
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
		};
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
		 circleProgressBar = (ProgressBar)findViewById(R.id.circleProgressBar);  
		 circleProgressBar.setIndeterminate(false);  
		dataForm=(DataForm)getApplication();
		gview = (GridView) findViewById(R.id.gview);
		//新建List
		data_list = new ArrayList<Map<String, Object>>();
		//获取数据
		circleProgressBar.setVisibility(View.VISIBLE); 
		Thread thread = new Thread(){
			public void run(){
				
				sendmessage();
				//circleProgressBar.setVisibility(View.GONE);
			}
			};
		thread.start();
	
		//分享功能
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
	        //滑屏功能
		imageResId = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
		titles = new String[imageResId.length];
		titles[0] = "测试paer文字1";
		titles[1] = "测试paer文字2";
		titles[2] = "测试paer文字3";
		titles[3] = "测试paer文字4";
		titles[4] = "测试paer文字5";

		imageViews = new ArrayList<ImageView>();

		// 初始化图片资源
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageViews.add(imageView);
		}

		
		dots = new ArrayList<View>();
		dots.add(findViewById(R.id.v_dot0));
		dots.add(findViewById(R.id.v_dot1));
		dots.add(findViewById(R.id.v_dot2));
		dots.add(findViewById(R.id.v_dot3));
		dots.add(findViewById(R.id.v_dot4));

		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(titles[0]);//

		viewPager = (ViewPager) findViewById(R.id.vp);
		viewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
		// 设置一个监听器，当ViewPager中的页面改变时调用
		viewPager.setOnPageChangeListener(new MyPageChangeListener());

	}
//
	
	
	
	

	
	@Override
	protected void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	protected void onStop() {
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
		super.onStop();
	}
	//取消滑动设置手动计算
	private static class Utility {  
		public static void setGirdViewHeightBasedOnChildren(GridView gview) {  
		//获取ListView对应的Adapter  
		ListAdapter listAdapter = gview.getAdapter();  
		if (listAdapter == null) {  
		// pre-condition  
		return;  
		}  
		  
		int totalHeight = 0;  
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { //listAdapter.getCount()返回数据项的数目  
		View listItem = listAdapter.getView(i, null, gview);  
		listItem.measure(0, 0); //计算子项View 的宽高  
		totalHeight = listItem.getMeasuredHeight(); //统计所有子项的总高度  
		}  
		  
		ViewGroup.LayoutParams params = gview.getLayoutParams();  
		params.height = totalHeight*2;//(totalHeight + (gview.getHeight() * (listAdapter.getCount() - 1)))/2;  
		//listView.getDividerHeight()获取子项间分隔符占用的高度  
		//params.height最后得到整个ListView完整显示需要的高度  
		gview.setLayoutParams(params);  
		}  
		}  
	/**
	 * 换行切换任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				System.out.println("currentItem: " + currentItem);
				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}

	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			currentItem = position;
			tv_title.setText(titles[position]);
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	/**
	 * 填充ViewPager页面的适配器
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageResId.length;
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(imageViews.get(arg1));
			return imageViews.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

		@Override
		public void finishUpdate(View arg0) {

		}
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
    void sendmessage(){
		
     HttpClient httpClient = new DefaultHttpClient();
   	 HttpPost httpPost = new HttpPost(dataForm.geturl2());//dataForm.geturl2());
   	 try{
   		
   	        try{
   			
   	        	JSONObject person = new JSONObject();
   	        	String request="recommand";
   	        	person.put("RequestType_", request);
   	        	
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
    void init()
    {
    	 mainHandler = new Handler(){
	        	
	            @Override
	             public void handleMessage(Message msg) {
	            	circleProgressBar.setVisibility(View.GONE);
	                 // TODO Auto-generated method stub
	                if(msg.obj.toString().equals("fail"))
	                {
	                	 Toast.makeText(getApplicationContext(), "刷新失败", Toast.LENGTH_SHORT).show();  
	               }
	                
	                else { 	
	                	// Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT).show();  
	                	parseJSON(msg.obj.toString());
					}
	             }
	         };
	         change=(Button)findViewById(R.id.change);
	         change.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					circleProgressBar.setVisibility(View.VISIBLE);
					Thread threadc = new Thread(){
						public void run(){
							
						sendmessage();	//sendData();
						//circleProgressBar.setVisibility(View.GONE);
						}
					};
						
					threadc.start();
					
				}
			});
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(AActivity.this, PostcomActivity.class);
				startActivity(intent);
				 overridePendingTransition(R.anim.out_from_left, R.anim.in_from_right); 
			}
		});
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
				JSONArray 	jsonObjs =  new JSONObject(value).getJSONArray("List_");				
				JSONObject jsonObj ;
				
				jsonObj= ((JSONObject)jsonObjs.opt(2)) ;
            	dataForm.setcompany(jsonObj.toString()); 
            	Intent intent = new Intent(AActivity.this, MerchantActivity.class);
				startActivity(intent);
				 overridePendingTransition(R.anim.out_from_left, R.anim.in_from_right);
				} catch (JSONException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
		});
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(AActivity.this, SearchActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.out_from_left, R.anim.in_from_right);
			}
		});
		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(AActivity.this, AdActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.out_from_left, R.anim.in_from_right); 
			}
		});
    }
private void parseJSON(String result) {
	value=result;
	data_list = new ArrayList<Map<String, Object>>();
	//JSONObject jsonObj;
	try { 
		JSONArray jsonObjs = new JSONObject(result).getJSONArray("List_"); 
        // 
	for(int i=0;i<4;i++){
		
		JSONObject jsonObj = ((JSONObject)jsonObjs.opt(i)) ;          
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("image", icon[i]);
		//jsonObj.getString("Name_");
		map.put("text", jsonObj.getString("Name_"));	
		//map.put("text", iconName[i]);
		data_list.add(map);
	}
	} catch (JSONException e) { 
	
        e.printStackTrace(); 
    } 
		String [] from ={"image","text"};
		int [] to = {R.id.image,R.id.text};
		sim_adapter =new SimpleAdapter(getApplicationContext(), data_list, R.layout.item, from, to);// new SimpleAdapter(this, data_list, R.layout.item, from, to);
		//配置适配器
		gview.setAdapter(sim_adapter);
		Utility.setGirdViewHeightBasedOnChildren(gview);
		gview.setOnItemClickListener(new OnItemClickListener() {
			 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {   
				  Intent intent=new Intent();  
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
	               
	                       
	            }   
	                        startActivity(intent);
	                        overridePendingTransition(R.anim.out_from_left, R.anim.in_from_right); 
	                        } catch (JSONException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
	               
	            }   
			
		});

// 		}
 	}
}
