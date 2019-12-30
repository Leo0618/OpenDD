package vip.okfood.opendd;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private TextView timeUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        timeUI = findViewById(R.id.time);
        int[] time = SPUtil.getTime();
        timeUI.setText(String.format(Locale.getDefault(), "%02d:%02d", time[0], time[1]));
        SwitchCompat btnSwitch = findViewById(R.id.btnSwitch);
        btnSwitch.setOnCheckedChangeListener(this);
    }

    public void auth(View view) {
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        boolean      hasIgnored   = false;
        if(powerManager != null) {
            hasIgnored = powerManager.isIgnoringBatteryOptimizations(getPackageName());
        }
        if(!hasIgnored) {
            try {
                @SuppressLint("BatteryLife") Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:"+getPackageName()));
                startActivityForResult(intent, 2000);
            } catch(Throwable e) {
                e.printStackTrace();
            }
        } else {
            showToast("已添加到电池优化白名单");
        }
    }

    public void chooseTime(View view) {
        int[] time = SPUtil.getTime();
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.e("leo", "hourOfDay="+hourOfDay+",minute="+minute);
                SPUtil.saveTime(hourOfDay, minute);
                timeUI.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
            }
        }, time[0], time[1], true).show();
    }

    private Intent mService;

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        if(checked) {
            startService(mService = new Intent(this, AliveService.class));
            RunTask.instance().start();
        } else {
            stopService(mService);
            RunTask.instance().stop();
            mService = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mService != null) {
            stopService(mService);
            showToast("服务已关闭");
            mService = null;
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
