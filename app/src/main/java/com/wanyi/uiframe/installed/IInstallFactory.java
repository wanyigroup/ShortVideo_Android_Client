package com.wanyi.uiframe.installed;


import com.wanyi.uiframe.installed.query.InstalledQuery;

import io.reactivex.functions.Consumer;

public interface IInstallFactory {

    /**
     * 订阅安装数据回调
     * @param consumer 消费者
     */
    void subscribe(Consumer<InstalledQuery> consumer);


}
