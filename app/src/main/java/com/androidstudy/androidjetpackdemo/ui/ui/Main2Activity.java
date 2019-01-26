package com.androidstudy.androidjetpackdemo.ui.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.androidstudy.androidjetpackdemo.R;

import io.flutter.facade.Flutter;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterView;


public class Main2Activity extends AppCompatActivity {
    private static final String CHANNEL = "samples.flutter.io/battery";
    MethodChannel channel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //GeneratedPluginRegistrant.registerWith(this);

        View flutterView = Flutter.createView(
                Main2Activity.this,
                getLifecycle(),
                "route1"
        );
        FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(600, 800);
//        layout.leftMargin = 100;
//        layout.topMargin = 200;
        //addContentView(flutterView, layout);
        setContentView(flutterView);
        channel = new MethodChannel((FlutterView)flutterView, CHANNEL);

        channel.setMethodCallHandler(
                new MethodChannel.MethodCallHandler() {
                    @Override
                    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
//                通过methodCall可以获取参数和方法名  执行对应的平台业务逻辑即可
                        if (methodCall.method.equals("getBatteryLevel")) {
                            System.out.println("");

                            result.success("flutter 调用原生 getBatteryLevel");
//                            int batteryLevel = getBatteryLevel();
//                            if (batteryLevel != -1) {
//                                result.success(batteryLevel);
//                            } else {
//                                result.error("UNAVAILABLE", "Battery level not available.", null);
//                            }
                        } else {
                            result.notImplemented();
                        }
                    }
                }
        );

        new EventChannel((FlutterView)flutterView, "samples.flutter.io/charging").setStreamHandler(
                new EventChannel.StreamHandler() {
                    // 接收电池广播的BroadcastReceiver。
                    private BroadcastReceiver chargingStateChangeReceiver;
                    @Override
                    // 这个onListen是Flutter端开始监听这个channel时的回调，第二个参数 EventSink是用来传数据的载体。
                    public void onListen(Object arguments, EventChannel.EventSink events) {
                        chargingStateChangeReceiver = createChargingStateChangeReceiver(events);
                        registerReceiver(
                                chargingStateChangeReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
                        Handler h = new Handler(Looper.getMainLooper());
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                events.success("123321");

                            }
                        },1000);
                    }

                    @Override
                    public void onCancel(Object arguments) {
                        // 对面不再接收
                        unregisterReceiver(chargingStateChangeReceiver);
                        chargingStateChangeReceiver = null;
                    }
                }
        );


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                channel.invokeMethod("getName", null, new MethodChannel.Result() {
                @Override
                public void success(Object o) {
                    // 这里就会输出 "Hello from Flutter"
                    //Log.i("debug", o.toString());
                }
                @Override
                public void error(String s, String s1, Object o) {
                }
                @Override
                public void notImplemented() {
                }
            });
        }
        },2000);
    }

    private BroadcastReceiver createChargingStateChangeReceiver(final EventChannel.EventSink events) {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

                if (status == BatteryManager.BATTERY_STATUS_UNKNOWN) {
                    events.error("UNAVAILABLE", "Charging status unavailable", null);
                } else {
                    boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                            status == BatteryManager.BATTERY_STATUS_FULL;
                    // 把电池状态发给Flutter
                    events.success(isCharging ? "charging" : "discharging");
                }
            }
        };
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        channel.invokeMethod("backPressed", null, new MethodChannel.Result() {
            @Override
            public void success(Object o) {
                // 这里就会输出 "Hello from Flutter"
                //Log.i("debug", o.toString());
                System.out.println(o);
            }
            @Override
            public void error(String s, String s1, Object o) {
            }
            @Override
            public void notImplemented() {
            }
        });
    }
}
