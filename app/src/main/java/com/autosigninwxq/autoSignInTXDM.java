package com.autosigninwxq;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;
import static com.autosigninwxq.SignInService.autoLock;
import static com.autosigninwxq.Utils.delay;

public class autoSignInTXDM {
    private boolean found = false;
    final String TXDM = "com.qq.ac.android";

    public void doTXDM(AccessibilityService service) {
        autoLock.lock();
        try {
            delay(4000);
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            Log.i("msg","执行了第一个返回");
            delay(4000);
            Utils.reLaunch(service, TXDM);
            delay(4000);
            iteratorTXDM(service);
            //autoCondition.await(10, TimeUnit.SECONDS); // 10s
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            Log.i("msg","签到完执行了第一个返回");
            delay(2000);
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            delay(2000);
            Log.i("msg","签到完执行了第二个返回");
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            delay(2000);
//            Log.i("msg","签到完执行了第三个返回");
//            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
//            delay(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }


        autoLock.unlock();
        MainActivity.condition.signal();
    }
    public static AccessibilityNodeInfo findNodeInfosById(AccessibilityNodeInfo nodeInfo, String resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId(resId);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }
    private void iteratorTXDM(AccessibilityService service) {
        Log.i("msg","正在点击第一个按钮");
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        targetNode = findNodeInfosById(nodeInfo, "com.qq.ac.android:id/tab_layout_center");
        Log.i("msg","目标结点输出");
        System.out.println(targetNode);
        while (targetNode!=null){
            Log.i("msg","targetNode");
            System.out.println(targetNode);
            if(targetNode.isClickable()){
                targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                break;
            }
            targetNode = targetNode.getParent();
        }
        delay(2000);
        iteratorTXDM1( service);
        found = true;
        return;
    }
    private void  iteratorTXDM1( AccessibilityService service) {
        Log.i("msg","正在点击第二个按钮");
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        targetNode = findNodeInfosById(nodeInfo, "com.qq.ac.android:id/sign_layout");
        Log.i("msg","目标结点输出");
        System.out.println(targetNode);
        while (targetNode!=null){
            Log.i("msg","targetNode");
            System.out.println(targetNode);
            if(targetNode.isClickable()){
                targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                break;
            }
            targetNode = targetNode.getParent();
        }
        return;
    }

}
