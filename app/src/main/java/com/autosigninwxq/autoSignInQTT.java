package com.autosigninwxq;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static com.autosigninwxq.SignInService.autoCondition;
import static com.autosigninwxq.SignInService.autoLock;
import static com.autosigninwxq.Utils.delay;


public class autoSignInQTT {
    private boolean found = false;
    final String QTT = "com.jifen.qukan";

    public void doQTT(AccessibilityService service) {
        autoLock.lock();
        try {
            delay(4000);
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            delay(4000);
            Utils.reLaunch(service, QTT);
            delay(4000);
            iteratorQTT(service);
            autoCondition.await(10, TimeUnit.SECONDS); // 10s
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            delay(2000);
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            delay(2000);
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
    private void iteratorQTT(AccessibilityService service) {
        Log.i("msg","正在点击第一个按钮");
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        targetNode = findNodeInfosById(nodeInfo, "com.jifen.qukan:id/k5");
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
        delay(5000);
        iteratorQTT1( service);
        found = true;
        return;
    }
    private void iteratorQTT1(AccessibilityService service) {
        Log.i("msg","正在点击第二个按钮");
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        targetNode = findNodeInfosById(nodeInfo, "com.jifen.qukan:id/a0_");
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
        delay(5000);
        iteratorQTT2( service);
        found = true;
        return;
    }
    private void  iteratorQTT2( AccessibilityService service) {
        Log.i("msg","正在点击第三个按钮");
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        targetNode = findNodeInfosById(nodeInfo, "com.jifen.qukan:id/a9v");
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
