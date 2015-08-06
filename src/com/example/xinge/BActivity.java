package com.example.xinge;

//import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class BActivity extends Activity{
private Button button1;
private Button button2;
private Button comcompleteButton;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.company);
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
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(BActivity.this, ComregisterActivity.class);
				startActivity(intent);
				
				overridePendingTransition(R.anim.zoomout, R.anim.zoomin); 
			}
		});
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(BActivity.this, CompostActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.zoomout, R.anim.zoomin); 
				
			}
		});
		comcompleteButton = (Button) findViewById(R.id.comcomplete);
		comcompleteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(BActivity.this, ComupdateActivity.class);
				startActivity(intent);
				
				overridePendingTransition(R.anim.zoomout, R.anim.zoomin); 
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
