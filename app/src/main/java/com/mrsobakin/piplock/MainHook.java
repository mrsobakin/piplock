package com.mrsobakin.piplock;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import com.mrsobakin.piplock.hooks.PipHooks;

public class MainHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.android.systemui")) {
            return;
        }
        XposedBridge.log("PiPLock: Hooking SystemUI");
        PipHooks.init(lpparam.classLoader);
    }
}
