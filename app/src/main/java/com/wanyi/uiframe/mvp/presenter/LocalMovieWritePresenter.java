package com.wanyi.uiframe.mvp.presenter;

import android.util.Log;

import com.hdl.m3u8.M3U8DownloadTask;
import com.hdl.m3u8.bean.OnDownloadListener;
import com.hdl.m3u8.utils.NetSpeedUtils;
import com.wanyi.uiframe.MyApp;
import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import com.wanyi.uiframe.mvp.presenter.callback.ILocalMovieWriteCallback;
import com.wanyi.uiframe.persistence.LocalMovieDataDoDao;
import com.wanyi.uiframe.persistence.entity.LocalMovieDataDo;
import com.wanyi.uiframe.usercenter.realize.BasePresenter;
import java.io.File;
import java.util.HashSet;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class LocalMovieWritePresenter extends BasePresenter<ILocalMovieWriteCallback> {

   LocalMovieCheckPresenter checkPresenter;
   HashSet<String> taskCenter = new HashSet<>();
    @Override
    public void attach(ILocalMovieWriteCallback view) {
        checkPresenter = new LocalMovieCheckPresenter();
        checkPresenter.attach(view.getActivity());
        super.attach(view);
    }

    @Override
    public void detach() {
        checkPresenter.deatch();
        super.detach();
    }

    public void startDown(IPreMovieVO preMovieVO) {
        try {
            checkPresenter.checkPermission(rst -> {
                if(rst) {
                    checkPresenter.synLocalRecord();
                    String videoKey = preMovieVO.getVideoKey();
                    MyApp.getInstance().getDaoSession().clear();
                    Boolean exist = MyApp.getInstance().getDaoSession().getLocalMovieDataDoDao().queryBuilder().where(LocalMovieDataDoDao.Properties.VideoKey.eq(videoKey)).count() > 0;
                    if(exist) {
                        if(getView() != null) {
                            getView().movieExist();
                            return;
                        }
                    }
                    if(!exist) {
                        downMovie(preMovieVO);
                    }

                }
                if(!rst) {
                    if(getView()!=null) {
                        getView().noReadPermission();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始下载
     * @param preMovieVO 视频预览
     */
    private void downMovie(IPreMovieVO preMovieVO) {
        String hlsUrl = preMovieVO.getHlsurl();
        if(taskCenter.contains(hlsUrl)) {
            return;
        }
        taskCenter.add(hlsUrl);
        M3U8DownloadTask task = new M3U8DownloadTask(preMovieVO.getVideoKey());
        task.download(preMovieVO.getHlsurl(), new OnDownloadListener() {
            @Override
            public void onDownloading(long itemFileSize, int totalTs, int curTs) {
            }

            @Override
            public void onSuccess() {
                  LocalMovieDataDo localMovieDataDo = new LocalMovieDataDo();
                  localMovieDataDo.setCoverImage(preMovieVO.getPlaceImage());
                  localMovieDataDo.setHeight(preMovieVO.getHeight());
                  localMovieDataDo.setWidth(preMovieVO.getWidth());
                  localMovieDataDo.setVideoKey(preMovieVO.getVideoKey());
                  File file = reName(task.getSaveFilePath(),preMovieVO.getVideoKey() + ".m3u8");
                  if(file == null) {
                      return;
                  }
                  localMovieDataDo.setLocalUrl(file.getPath());
                  MyApp.getInstance().getDaoSession().getLocalMovieDataDoDao().save(localMovieDataDo);
                  taskCenter.remove(preMovieVO.getHlsurl());
            }

            Long lastLength = 0L;
            @Override
            public void onProgress(long curLength) {
                if (curLength - lastLength > 0) {
                    final String speed = NetSpeedUtils.getInstance().displayFileSize(curLength - lastLength) + "/s";
                    lastLength = curLength;
                }
            }

            @Override
            public void onStart() {
                if(getView() != null) {
                    Observable.just(1).observeOn(AndroidSchedulers.mainThread()).subscribe(l ->{
                        getView().downStart();
                    });
                }
            }

            @Override
            public void onError(Throwable errorMsg) {

            }
        });
    }

    private File reName(String path,String newname) {//文件重命名
        //Scanner scanner=new Scanner(System.in);
        File file=new File(path);
        if(file.exists()) {
            File newfile=new File(file.getParent()+File.separator+newname);//创建新名字的抽象文件
            if(file.renameTo(newfile)) {
                file.delete();
                return newfile;
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }

    }



}
