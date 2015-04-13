package com.coder80.timer;

import com.coder80.timer.utils.NotificationServiceUtil;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * Created by coder80 on 2014/10/31.
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener{
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch(id){
		case R.id.button1:
			NotificationServiceUtil.invokeTimerNotification(mContext,10*1000,"消息1,恭喜您,中奖啦!",1001);
			break;
		case R.id.button2:
			NotificationServiceUtil.cancleNotification(mContext, 1001);
			break;
		case R.id.button3:
			NotificationServiceUtil.invokeTimerNotification(mContext,15*1000,"消息2,恭喜您,中奖啦!",1002);
			break;
		case R.id.button4:
			NotificationServiceUtil.cancleNotification(mContext, 1002);
			break;
		}
		
	}
}
