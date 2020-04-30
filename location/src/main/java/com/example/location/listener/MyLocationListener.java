package com.example.location.listener;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.Poi;
import com.example.location.enums.ELocationType;
import com.example.location.model.LocationInfo;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MyLocationListener implements BDLocationListener {

    /**
     * 消费者信息
     */
    Consumer<LocationInfo> consumer;
    LocationClient mLocationClient;
    public MyLocationListener(Consumer<LocationInfo> consumer, LocationClient mLocationClient) {
        this.consumer = consumer;
        this.mLocationClient = mLocationClient;
    }




    @Override
    public void onReceiveLocation(BDLocation location) {
        Log.e(getClass().getName(),this.toString() + " --1");
        // TODO Auto-generated method stub
        if (null != location && location.getLocType() != BDLocation.TypeServerError) {
            Observable.just(1).map(v -> {
                LocationInfo.AssistInfo assistInfo = LocationInfo.AssistInfo.builder().altitude(location.getAltitude()).city(location.getCity())
                        .country(location.getCountry())
                        .countryCode(location.getCountryCode()).district(location.getDistrict()).gpsAccuracyStatus(location.getGpsAccuracyStatus())
                        .operators(location.getOperators())
                        .satelliteNumber(location.getSatelliteNumber()).speed(location.getSpeed()).street(location.getStreet()).build();
                LocationInfo.LocationInfoBuilder locationInfoBuilder =  LocationInfo.builder().address(location.getAddrStr()).
                        latitude(location.getLatitude()).longitude(location.getLongitude()).assistInfo(assistInfo);
                switch (location.getLocType()) {
                    case BDLocation.TypeGpsLocation:{
                        locationInfoBuilder.eLocationType(ELocationType.Gps);
                    }
                    break;
                    case BDLocation.TypeNetWorkLocation:{
                        locationInfoBuilder.eLocationType(ELocationType.Net);
                    }
                    break;
                    case BDLocation.TypeOffLineLocation: {
                        locationInfoBuilder.eLocationType(ELocationType.Offline);
                    }
                    break;
                    case BDLocation.TypeServerError: {
                        locationInfoBuilder.eLocationType(ELocationType.ServerError);
                    }
                    break;
                    case BDLocation.TypeNetWorkException: {
                        locationInfoBuilder.eLocationType(ELocationType.NetError);
                    }
                    break;
                    case BDLocation.TypeCriteriaException: {
                        locationInfoBuilder.eLocationType(ELocationType.CriteriaException);
                    }
                    break;
                    default: {
                        locationInfoBuilder.eLocationType(ELocationType.Unknow);
                    }
                    break;
                }
                mLocationClient.unRegisterLocationListener(MyLocationListener.this);
                return locationInfoBuilder.build();
            }).subscribe(consumer);
        }

    }





}
