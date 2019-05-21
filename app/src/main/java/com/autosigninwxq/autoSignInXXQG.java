
package com.autosigninwxq;

        import android.accessibilityservice.AccessibilityService;
        import android.util.Log;
        import android.os.Build;
        import android.view.accessibility.AccessibilityNodeInfo;

        import java.util.List;
        import java.util.concurrent.TimeUnit;
        import static com.autosigninwxq.SignInService.autoCondition;
        import static com.autosigninwxq.SignInService.autoLock;
        import static com.autosigninwxq.Utils.delay;


public class autoSignInXXQG {
    private boolean found = false;
    final String NK = "cn.xuexi.android";

    public void doXXQG(AccessibilityService service) {
        autoLock.lock();
        try {
            delay(4000);
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            Log.i("msg","执行了第一个返回");
            delay(4000);
            Utils.reLaunch(service, NK);
            delay(4000);
            iteratorXXQG(service);
           // autoCondition.await(10, TimeUnit.SECONDS); // 10s
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            Log.i("msg","签到完执行了第1个返回");
            delay(2000);
           service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            Log.i("msg","签到完执行了第2个返回");
//            delay(2000);
//            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
//            Log.i("msg","签到完执行了第3个返回");

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
    private void iteratorXXQG(AccessibilityService service) {
        Log.i("msg","正在点击第一个按钮");
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        targetNode = findNodeInfosById(nodeInfo, "cn.xuexi.android:id/home_bottom_tab_button_mine");
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
        //iteratorXXQG1( service);
        found = true;
        return;
    }
  /*  private void  iteratorXXQG1( AccessibilityService service) {
        Log.i("msg","正在点击第二个按钮");
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        targetNode = findNodeInfosById(nodeInfo, "com.smzdm.client.android:id/tv_login_sign");
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
    }*/

}

