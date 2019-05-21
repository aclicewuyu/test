package com.autosigninwxq;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

import static com.autosigninwxq.SignInService.autoLock;
import static com.autosigninwxq.Utils.delay;

public class autoSignInGH {
    //private boolean found = false;
    final String GH = "com.rytong.airchina";

    public void doGH(AccessibilityService service) {
        autoLock.lock();
        // found = false;
        try {
            Log.i("AA","GH");
            delay(4000);
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            delay(4000);
            Utils.reLaunch(service, GH);
            delay(2000);
            iteratorGH(service);
            delay(2000);////
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            Log.i("msg","返回1");
            delay(2000);
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            Log.i("msg","返回2");
            delay(2000);
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            delay(2000);
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            Log.i("msg","返回3");
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
    private void iteratorGH( AccessibilityService service){
      Log.i("msg","正在点击第一个按钮");
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        targetNode = findNodeInfosById(nodeInfo, "com.rytong.airchina:id/ll_home_me");
        Log.i("msg","目标结点输出");
        System.out.println(targetNode);
        while (targetNode!=null){
            if(targetNode.isClickable()){

                targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                break;
            }
            targetNode = targetNode.getParent();
        }

      /*  Path path = new Path();
        //System.out.println("11111");
        path.moveTo(950,2150);
       // System.out.println("22222");
        GestureDescription.Builder builder = new GestureDescription.Builder();
       // System.out.println("333");
        GestureDescription gestureDescription = builder.addStroke(new GestureDescription.StrokeDescription(path, 1000, 1000)).build();

        AccessibilityService.GestureResultCallback callback=null;
       // System.out.println("44444");
        Handler handler=null;
       // System.out.println("55555");
        boolean temp=service.dispatchGesture(gestureDescription,callback,handler);
       // System.out.println("6666666666666");
        if(temp){
            System.out.println("True");
        }
        else{
            System.out.println("False");
       }*/
        delay(3000);
        delay(2000);
        iteratorGH1( service);
       // found = true;
        return;
    }
    private void iteratorGH1( AccessibilityService service) {
        Log.i("msg","正在点击第二个按钮");
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        targetNode = findNodeInfosById(nodeInfo, "com.rytong.airchina:id/tv_right_drawable_left");
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
        delay(3000);
        iteratorGH2( service);
       // found = true;
        return;
    }
    private void iteratorGH2( AccessibilityService service) {
        Log.i("msg","正在点击第三个按钮");
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        targetNode = findNodeInfosById(nodeInfo, "com.rytong.airchina:id/tv_sign_up_sign");
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
        delay(3000);
        iteratorGH3( service);
      //  found = true;
        return;
    }
    private void iteratorGH3( AccessibilityService service) {
        Log.i("msg","正在点击第四个按钮");
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        targetNode = findNodeInfosById(nodeInfo, "com.rytong.airchina:id/tv_confirm");
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
        return;
    }

}
