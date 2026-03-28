package com.mrsobakin.piplock.hooks;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class PipHooks {
    public static void init(ClassLoader classLoader) {
        try {
            hookUpdatePipPositionForKeepClearAreas(classLoader);
            XposedBridge.log("PipLock: Successfully hooked PiP position updates");
        } catch (Throwable t) {
            XposedBridge.log("PipLock: Failed to initialize hooks: " + t.getMessage());
        }
    }

    private static void hookUpdatePipPositionForKeepClearAreas(ClassLoader classLoader) {
        XposedHelpers.findAndHookMethod(
            "com.android.wm.shell.pip.phone.PipController",
            classLoader,
            "updatePipPositionForKeepClearAreas",
            new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    XposedBridge.log("PipLock: Blocking system-initiated PiP repositioning");
                    param.setResult(null);
                }
            }
        );
    }
}
