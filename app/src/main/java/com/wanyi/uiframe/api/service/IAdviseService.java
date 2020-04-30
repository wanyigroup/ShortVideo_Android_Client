package com.wanyi.uiframe.api.service;


import com.wanyi.uiframe.api.model.dto.advise.AdImgDTO;
import com.wanyi.uiframe.api.model.dto.advise.AdvTxtDTO;
import com.wanyi.uiframe.api.model.dto.advise.AppBootDTO;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IAdviseService {

    @GET("/api/appbootadv")
    Observable<AppBootDTO> appboot();

    @GET("/api/adimg")
    Observable<AdImgDTO> adImg();

    @GET("/api/adtxt")
    Observable<AdvTxtDTO> adTxt();


}
