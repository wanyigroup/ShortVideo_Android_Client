package com.wanyi.uiframe.upgrade.encryptor;

import com.xuexiang.xupdate.proxy.IFileEncryptor;

import java.io.File;

public class IGnoreEncryptor implements IFileEncryptor {
    @Override
    public String encryptFile(File file) {
        return null;
    }

    @Override
    public boolean isFileValid(String encrypt, File file) {
        return true;
    }
}
