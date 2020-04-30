/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wanyi.uiframe.upgrade.custom;

import android.util.Log;

import com.wanyi.uiframe.upgrade.UpgradeConst;
import com.wanyi.uiframe.upgrade.enity.action.ItemUpdate;
import com.xuexiang.xupdate.entity.UpdateEntity;
import com.xuexiang.xupdate.proxy.IUpdateParser;


/**
 * @author xuexiang
 * @since 2018/7/30 下午12:00
 */
public class XUpdateServiceParser implements IUpdateParser {

    @Override
    public UpdateEntity parseJson(String json) throws Exception {
        Log.e(getClass().getName(),"json:" + json);
        ItemUpdate itemUpdate = UpgradeConst.getItemUpdate();
        UpdateEntity updateEntity = new UpdateEntity();
        updateEntity.setHasUpdate(itemUpdate.isForce()).setHasUpdate(true)
                .setUpdateContent(itemUpdate.getAppDesc().replaceAll("\\\\r\\\\n", "\r\n"))
                .setDownloadUrl(itemUpdate.getLatestApk())
                .setVersionName(itemUpdate.getVersionCode());
        return updateEntity;
    }







}
