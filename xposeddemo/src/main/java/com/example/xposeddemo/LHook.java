package com.example.xposeddemo;

import android.app.Activity;

import com.example.xposeddemo.handlesAcs.LoginAcHandler;
import com.example.xposeddemo.handlesAcs.SecondAcHandler;
import com.zhenxi.Superappium.PageManager;
import com.zhenxi.Superappium.ViewImage;
import com.zhenxi.Superappium.utils.CLogUtils;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * @author Zhenxi on 2020-07-03
 */
public class LHook implements IXposedHookLoadPackage {


    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        String pkg = "com.example.xposedappium";
        if (lpparam.processName.equals(pkg)) {
            CLogUtils.e("发现匹配的App " + pkg);
            //添加需要处理的Activity
            AddHandleActivity();
        }
        String pkg1 = "com.leiting.wf";
        if (lpparam.processName.equals(pkg1)) {
            CLogUtils.e("发现匹配的App " + pkg1);
            //添加需要处理的Activity
            AddHandleActivity1();
        }
    }

    private void AddHandleActivity() {
        PageManager.addHandler("com.example.xposedappium.ui.login.LoginActivity",
                new LoginAcHandler());
        PageManager.addHandler("com.example.xposedappium.ui.login.SecondActivity",
                new SecondAcHandler());

        CLogUtils.e("AddHandleActivity 注册完毕");
    }

    private void AddHandleActivity1() {

        PageManager.addHandler("com.leiting.sdk.activity.PrivacyActivity",
                new PageManager.ActivityFocusHandler() {
                    @Override
                    public boolean handleActivity(Activity activity, ViewImage root) {
                        CLogUtils.e("开始执行第一个页面 " + activity.getClass().getName());
                        root.clickByXpath("//android.widget.Button[@text='同意']");
                        return true;
                    }
                });

        PageManager.addHandler("air.com.leiting.wf.AppEntry",
                new PageManager.ActivityFocusHandler() {
                    @Override
                    public boolean handleActivity(Activity activity, ViewImage root) {
                        CLogUtils.e("开始执行第一个页面 " + activity.getClass().getName());
                        root.clickByXpath("//*[@resoure-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View");
//                        root.clickByXpath("//*[@clickable='true']");

                        return true;
                    }
                }
        );
//        PageManager.addHandler("com.example.xposedappium.ui.login.SecondActivity",
//                new SecondAcHandler());

        CLogUtils.e("AddHandleActivity 注册完毕");
    }
}
