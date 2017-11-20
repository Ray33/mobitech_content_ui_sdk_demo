package io.mobitech.content_ui_demo.util;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsSession;

import io.mobitech.content_ui_demo.R;

public class CustomTabHelper extends Fragment {
//    public static String getPackageNameToUse(Context context, String urlToLoad) {
//        PackageManager pm = context.getPackageManager();
//        Intent activityIntent = new Intent("android.intent.action.VIEW", Uri.parse(urlToLoad));
//        ResolveInfo defaultViewHandlerInfo = pm.resolveActivity(activityIntent, 0);
//        String defaultViewHandlerPackageName = null;
//        if (defaultViewHandlerInfo != null) {
//            defaultViewHandlerPackageName = defaultViewHandlerInfo.activityInfo.packageName;
//        }
//        List<ResolveInfo> resolvedActivityList = pm.queryIntentActivities(activityIntent, 0);
//        List<String> packagesSupportingCustomTabs = new ArrayList();
//        for (ResolveInfo info : resolvedActivityList) {
//            Intent serviceIntent = new Intent();
//            serviceIntent.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
//            serviceIntent.setPackage(info.activityInfo.packageName);
//            if (pm.resolveService(serviceIntent, 0) != null) {
//                packagesSupportingCustomTabs.add(info.activityInfo.packageName);
//            }
//        }
//        if (packagesSupportingCustomTabs.size() <= 0) {
//            return null;
//        }
//        if (TextUtils.isEmpty(defaultViewHandlerPackageName) || !packagesSupportingCustomTabs.contains(defaultViewHandlerPackageName)) {
//            return (String) packagesSupportingCustomTabs.get(0);
//        }
//        return defaultViewHandlerPackageName;
//    }

    //    private static CustomTabsServiceConnection mConnection;
//    private static CustomTabsClient mClient;
    private static CustomTabManager mcustomTabManager;

//    static{
//        mConnection = new CustomTabsServiceConnection() {
//            @Override
//            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
//                mClient = customTabsClient;
//                mClient.warmup(0);
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName componentName) {
//                mClient = null;
//            }
//        };
//    }

    private static String packageName = "com.android.chrome";

    public static void bindCustomTab(Context context) {
        mcustomTabManager = new CustomTabManager(context);
        mcustomTabManager.bind(packageName);
    }

    private static boolean dialogShown = false;

    @WorkerThread
    @NonNull
    public static CustomTabsIntent.Builder createTabBuilder(final Context context, @Nullable Uri... possibleUris) {
            CustomTabsCallback customTabsCallback = new CustomTabsCallback();
            CustomTabsSession session = mcustomTabManager.createSession(customTabsCallback, possibleUris);
        return new CustomTabsIntent.Builder(session);
    }

    public static void openCustomTab(Context context, CustomTabsIntent.Builder intentBuilder, Resources resources, String url) {
        intentBuilder.setToolbarColor(resources.getColor(R.color.colorPrimary));
        intentBuilder.setShowTitle(true);
        CustomTabsIntent customTabsIntent = intentBuilder.build();
        customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        customTabsIntent.launchUrl(context, Uri.parse(url));

    }
}
