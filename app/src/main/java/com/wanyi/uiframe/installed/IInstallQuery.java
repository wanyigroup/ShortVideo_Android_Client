package com.wanyi.uiframe.installed;


import com.wanyi.uiframe.installed.query.InstalledQuery;

public interface IInstallQuery {

    boolean isEmpty();

    void saveInstalledQuery(InstalledQuery query);

    InstalledQuery getInstalledQuery();


}
