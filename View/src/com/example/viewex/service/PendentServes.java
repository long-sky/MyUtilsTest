package com.example.viewex.service;

import com.example.viewex.widget.PendentView;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PendentServes extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		//����serviceʱһ�� ʵ����һ��TableShowView�����ҵ�������fun()��������ע�ᵽwindowManager��  
        super.onCreate();  
        new PendentView(getApplicationContext()).fun();  
	}
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	};
}
