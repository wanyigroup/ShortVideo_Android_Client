package com.wanyi.uiframe.installed.action;



import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.api.ApiFactory;
import com.wanyi.uiframe.api.RequestJsonBodyFactory;
import com.wanyi.uiframe.api.service.IInstallService;
import com.wanyi.uiframe.installed.IInstallFactory;
import com.wanyi.uiframe.installed.IInstallQuery;
import com.wanyi.uiframe.installed.query.InstalledQuery;

import io.reactivex.schedulers.Schedulers;

public class InstallAction {

    IInstallFactory installFactory;
    IInstallQuery iInstallQuery;

    public InstallAction setInstallFactory(IInstallFactory installFactory) {
        this.installFactory = installFactory;
        return this;
    }

    public InstallAction setiInstallQuery(IInstallQuery iInstallQuery) {
        this.iInstallQuery = iInstallQuery;
        return this;
    }

    public final void execute() {

        if(iInstallQuery.isEmpty()){
            installFactory.subscribe(query -> {
               iInstallQuery.saveInstalledQuery(query);
               upStreamToNet(query);
            });
        } else {
            InstalledQuery query = iInstallQuery.getInstalledQuery();
            upStreamToNet(query);
        }
    }

    private void upStreamToNet(InstalledQuery query) {
       IInstallService hdService = ApiFacade.createInstall();
       hdService.postTrackInfo(RequestJsonBodyFactory.create(query)).subscribeOn(Schedulers.io()).subscribe(responseBody -> {});
    }


}
