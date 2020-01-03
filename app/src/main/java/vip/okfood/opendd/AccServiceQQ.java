package vip.okfood.opendd;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;


import java.util.List;


/**
 * function:AccServiceQQ
 *
 * <p></p>
 * Created by lzj on 2019/3/18.
 */
public class AccServiceQQ extends AccessibilityService {
    private static final String TAG = "AccServiceQQ";

    @Override
    public void onInterrupt() {
        LogUtil.i(TAG, "onInterrupt");
    }

    @Override
    protected void onServiceConnected() { LogUtil.i(TAG, "onServiceConnected");}

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        if(!SPUtil.isNotification()) {
            return;
        }
        if(eventType == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            LogUtil.v(event.toString());
            List<CharSequence> texts = event.getText();
            if(texts == null || texts.isEmpty()) return;
            for(CharSequence text : texts) {
                if(text.toString().contains("嘀嘀")) {
                    LogUtil.i("收到消息,执行动作");
                    RunTask.instance().launch();
                    break;
                }
            }
        }
    }
}
