package com.orangelittle.u17.util;


import com.orangelittle.u17.entries.BoutiqueBean;
import com.orangelittle.u17.entries.ClassifyBean;
import com.orangelittle.u17.entries.GameBean;
import com.orangelittle.u17.entries.RecommendBean;
import com.orangelittle.u17.entries.level1.CommentBean;
import com.orangelittle.u17.entries.level1.DanmuBean;
import com.orangelittle.u17.entries.level1.DatailBean;
import com.orangelittle.u17.entries.level1.DetailGuessBean;
import com.orangelittle.u17.entries.level1.GameDetailBean;
import com.orangelittle.u17.entries.level1.Jump_From_SearchBean;
import com.orangelittle.u17.entries.level1.RankBean;
import com.orangelittle.u17.entries.level1.RankListBean;
import com.orangelittle.u17.entries.level1.ReadComicBean;
import com.orangelittle.u17.entries.level1.SpecialTopicBean;
import com.orangelittle.u17.entry.WelcomeBean;
import com.orangelittle.u17.global.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * {@link #getData(ClassType, Callback, Object...)}}
 * <p>
 * 参数说明:
 * ClassType 是枚举变量
 * Callback 是回调接口,传递时请自行添加泛型,
 * int... 请求参数,请严格按照顺序传入
 * <p>
 * 用法如下:
 * NetUtils.getData(ClassType.BoutiqueBean, new Callback<BoutiqueBean>() {
 *
 * @Override public void onResponse(Call<BoutiqueBean> call, Response<BoutiqueBean> response) {
 * <p>
 * }
 * @Override public void onFailure(Call<BoutiqueBean> call, Throwable t) {
 * <p>
 * }
 * });
 */
public class NetUtils {

    public static <T> void getData(com.orangelittle.u17.util.ClassType classType, Callback<T> callback, Object... args) {
        switch (classType) {
            case RecommendBean:
                if (args.length != 1) {
                    throw new RuntimeException("参数args长度不匹配");
                }
                mServer.getRecommendBean((int) args[0]).enqueue((Callback<RecommendBean>) callback);
                break;
            case BoutiqueBean:
                mServer.getBoutiqueBean().enqueue((Callback<BoutiqueBean>) callback);
                break;
            case GameBean:
                if (args.length != 1) {
                    throw new RuntimeException("参数args长度不匹配");
                }
                mGameServer.getGameBean((int) args[0]).enqueue((Callback<GameBean>) callback);
                break;
            case RankBean:
                mServer.getRankBean().enqueue((Callback<RankBean>) callback);
                break;
            case RankListBean:
                if (args.length != 3) {
                    throw new RuntimeException("参数args长度不匹配");
                }
                mServer.getRankListBean((int) args[0], (String) args[1], (int) args[2]).enqueue((Callback<RankListBean>) callback);
                break;
            case DatailBean:
                if (args.length != 1) {
                    throw new RuntimeException("参数args长度不匹配");
                }
                mServer.getDetailBean((String) args[0]).enqueue((Callback<com.orangelittle.u17.entries.level1.DatailBean>) callback);
                break;
            case ClassifyBean:
                mServer.getClassifyBean().enqueue((Callback<ClassifyBean>) callback);
                break;
            case DetailGuessBean:
                mServer.getDetailGuessBean().enqueue((Callback<DetailGuessBean>) callback);
                break;
            case ReadComicBean:
                if (args.length != 1) {
                    throw new RuntimeException("参数args长度不匹配");
                }
                mServer.getReadComicBean((String) args[0]).enqueue((Callback<ReadComicBean>) callback);
                break;
            case DanmuBean:
                if (args.length != 2) {
                    throw new RuntimeException("参数args长度不匹配");
                }
                mServer.getDanmuBean((String) args[0],(String) args[1]).enqueue(((Callback<DanmuBean>) callback));
                break;
            case Jump_From_SearchBean:
                if (args.length != 4) {
                    throw new RuntimeException("参数args长度不匹配");
                }
                mServer.getJump_From_SearchBean((int) args[0], (String) args[1], (int) args[2],(int) args[3]).enqueue((Callback<Jump_From_SearchBean>) callback);
                break;
            case SpecialTopicBean:
                if (args.length != 2) {
                    throw new RuntimeException("参数args长度不匹配");
                }
                mServer.getSpecialTopicBean((String) args[0], (int) args[1]).enqueue((Callback<SpecialTopicBean>) callback);
                break;
            case GameDetailBean:
                mGameServer.getGameDetailBean((int)args[0]).enqueue((Callback<GameDetailBean>) callback);
                break;
            case WelcomeBean:
                mServer.getWelcomeBean().enqueue((Callback<WelcomeBean>) callback);
                break;
            case CommentBean:
                if (args.length != 3) {
                    throw new RuntimeException("参数args长度不匹配");
                }
                mServer.getCommentBean(((String) args[0]), ((String) args[1]), ((int) args[2])).enqueue((Callback<CommentBean>) callback);
                break;
        }
    }

    private static Server mServer;
    private static GameServer mGameServer;

    static {
        Retrofit.Builder builder = new Retrofit.Builder();
        mServer = builder.baseUrl(Constants.BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Server.class);
        mGameServer = builder.baseUrl(Constants.GAME_BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GameServer.class);
    }

    interface Server {
        @GET("comic/recommendList")
        Call<RecommendBean> getRecommendBean(@Query("page") int page);

        @GET("comic/boutiqueList")
        Call<BoutiqueBean> getBoutiqueBean();

        @GET("list/commonComicList")
        Call<RankListBean> getRankListBean(@Query("argValue") int argValue, @Query("argName") String argName, @Query("page") int page);

        @GET("list/commonComicList?")
        Call<Jump_From_SearchBean> getJump_From_SearchBean(@Query("argValue") int argValue, @Query("argName") String argName,@Query("argCon") int argcon, @Query("page") int page);
        @GET("rank/list")
        Call<RankBean> getRankBean();

        @GET("comic/detail_static")
        Call<DatailBean> getDetailBean(@Query("comicid") String comicid);

        @GET("sort/list")
        Call<ClassifyBean> getClassifyBean();

        @GET("comic/guessLike")
        Call<DetailGuessBean> getDetailGuessBean();
        @GET("comic/chapterlist")
        Call<ReadComicBean> getReadComicBean(@Query("comicid") String comicid);

        @GET("tucao/get?android_id=089E019071090000&v=3080105&page_size=200&model=V188&t=1475036453&last_id=100000000&first_id=100000000&type=next_page&come_from=MEIZU&image_id=3828963&comic_id=99874")
        Call<DanmuBean> getDanmuBean(@Query("image_id") String image_id, @Query("comic_id") String comic_id);

        @GET("comic/special")
        Call<SpecialTopicBean> getSpecialTopicBean(@Query("argCon") String argCon,@Query("page") int page);

        @GET("ad/launcher")
        Call<WelcomeBean> getWelcomeBean();
        @GET("comment/list")
        Call<CommentBean> getCommentBean(@Query("thread_id") String thread_id,@Query("object_id") String object_id,@Query("page") int page);
    }

    interface GameServer {
        @GET("v3/appIndex")
        Call<GameBean> getGameBean(@Query("page") int page);

        @GET("appDetails/{appId}")
        Call<GameDetailBean> getGameDetailBean(@Path("appId") int appId);
    }
}
