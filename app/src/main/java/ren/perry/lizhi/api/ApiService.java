package ren.perry.lizhi.api;

import ren.perry.lizhi.app.Constants;
import ren.perry.lizhi.bean.AboutBean;
import ren.perry.lizhi.bean.AlbumBean;
import ren.perry.lizhi.bean.BannerBean;
import ren.perry.lizhi.bean.MusicBean;
import ren.perry.lizhi.bean.SplashBean;
import ren.perry.lizhi.bean.VersionBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author perrywe
 * @date 2019/4/10
 * WeChat  917351143
 */
public interface ApiService {

    @POST(Constants.Api.module + "/splash")
    Observable<SplashBean> splash();

    @POST(Constants.Api.module + "/version")
    @FormUrlEncoded
    Observable<VersionBean> version(@Field("appId") String appId);

    @POST(Constants.Api.module + "/about")
    Observable<AboutBean> about();

    @POST(Constants.Api.module + "/banner")
    Observable<BannerBean> banner();

    @POST(Constants.Api.module + "/album")
    @FormUrlEncoded
    Observable<AlbumBean> album(@Field("size") int size,
                                @Field("page") int page);

    @POST(Constants.Api.module + "/music")
    @FormUrlEncoded
    Observable<MusicBean> music(@Field("albumId") int albumId,
                                @Field("size") int size,
                                @Field("page") int page);

}