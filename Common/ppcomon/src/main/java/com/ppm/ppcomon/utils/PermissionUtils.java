package com.ppm.ppcomon.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import com.ppm.ppcomon.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理工具类
 *
 * @author lvpengcheng 2016/01/14
 */
public class PermissionUtils {

    /**
     * 权限识别码
     */
    public static final String EXTRA_PERMISSION_ACTION = "extra_permission_action";
    public static final int ACTION_REQUEST_CONTRACT = 1;
    public static final int ACTION_REQUEST_CAMERA = 2;
    public static final int ACTION_REQUEST_STORAGE = 3;
    public static final int ACTION_REQUEST_TOKE_PIC = 4;
    public static final int ACTION_REQUEST_CALL_PHONE = 5;

    public static boolean checkPermission(Context context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    /**
     * 基本权限是否已授权
     */
    public static boolean checkHasBasePermission(Context context) {
        List<String> basePermissionList = new ArrayList<>();
        String basePermissions[] = {Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        for (String permission : basePermissions) {
            if (!checkPermission(context, permission)) {
                basePermissionList.add(permission);
            }
        }
        if (basePermissionList.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 获取需要申请的权限
     *
     * @param action 需要什么功能的权限
     */
    public static String[] getPermissionNeeded(Context context, int action) {
        switch (action) {
            case ACTION_REQUEST_CONTRACT:
                return getDeniedPermissions(context, new String[]{Manifest.permission.READ_CONTACTS});
            case ACTION_REQUEST_CAMERA:
                return getDeniedPermissions(context, new String[]{Manifest.permission.CAMERA});
            case ACTION_REQUEST_STORAGE:
                return getDeniedPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
            case ACTION_REQUEST_TOKE_PIC:
                return getDeniedPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA});
            case ACTION_REQUEST_CALL_PHONE:
                return getDeniedPermissions(context, new String[]{Manifest.permission.CALL_PHONE});
        }
        return null;
    }

    /**
     * 获取需要但未授权的权限
     */
    private static String[] getDeniedPermissions(Context contexts, String[] permissions) {
        List<String> permissionNeed = new ArrayList<>();
        for (String permission : permissions) {
            if (!checkPermission(contexts, permission)) {
                permissionNeed.add(permission);
            }
        }
        return permissionNeed.toArray(new String[permissionNeed.size()]);
    }

    /**
     * 获取申请权限之前的提示
     */
    public static String getPermissionsTips(Context context, int action) {
        String tips;
        switch (action) {
            case ACTION_REQUEST_CONTRACT:
                tips = context.getString(R.string.request_permission_contact);
                break;
            case ACTION_REQUEST_CAMERA:
                tips = context.getString(R.string.permission_camera_tips);
                break;
            default:
                tips = context.getString(R.string.request_permission_tips);
                break;
        }
        return tips;
    }
}
