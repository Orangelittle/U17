package com.orangelittle.u17.entries;
import android.content.Intent;
import android.view.View;

import com.orangelittle.u17.activity.MainActivity;
import com.orangelittle.u17.global.U17Application;
import com.orangelittle.u17.widget.FlickerProgressBar;
import com.kogitune.activity_transition.ActivityTransitionLauncher;

import java.util.List;

/**
 * Created by Ice on 2016/9/29.
 */

public class GameBean {

    /**
     * message : 处理成功
     * stateCode : 1
     * returnData : {"hasMore":true,"gameheader":{"recommands":[{"appId":44,"dowmLoadUrl":"http://tj.u17t.com/rpqi/click_MzE2XzIyMzNfMjQ2Mg==_MTY4MCoxMDUw","appPackageName":"com.tencent.KiHan","coverUrl":"http://static.u17i.com/wan/upload/images/20160425/975d829fu17595eu174b20u17ba02u174a0faba15715.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160425/975d829fu17595eu174b20u17ba02u174a0faba15715.png","downUrl":"http://download.u17i.com/app/game/hyrz/hyrz_20160920_yyq_1.14.12.10.apk","title":"火影忍者","gameType":"卡牌养成","size":495136784},{"appId":69,"dowmLoadUrl":"http://tj.u17t.com/xsx7/click_MzQzXzI2OTBfMjg4Ng==_MTY4MCoxMDUw","appPackageName":"com.netease.onmyoji","coverUrl":"http://static.u17i.com/wan/upload/images/20160902/349db07eu17a053u174574u178ebcu17bbe63d699ccb.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/349db07eu17a053u174574u178ebcu17bbe63d699ccb.png","downUrl":"http://download.u17i.com/app/game/yys/g37_netease_netease.u17_cps_dev_1.0.7.apk","title":"阴阳师","gameType":"卡牌养成","size":540399805},{"appId":53,"dowmLoadUrl":"http://tj.u17t.com/iobl/click_MzI3XzI0MjRfMjY0MQ==_MTY4MCoxMDUw","appPackageName":"com.ourpalm.zhj.zq ","coverUrl":"http://static.u17i.com/wan/upload/images/20160425/28e1d593u176bfau174b62u17a706u17534ccba2d276.jpg","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160425/28e1d593u176bfau174b62u17a706u17534ccba2d276.jpg","downUrl":"http://download.u17i.com/app/game/zhj/zhj_20160927_yyq_1.6.3.apk","title":"镇魂街","gameType":"即时战略","size":177514509},{"appId":62,"dowmLoadUrl":"http://tj.u17t.com/i5p8/click_MzM2XzI1NDZfMjc1NQ==_MTY4MCoxMDUw","appPackageName":"com.xhhd.bleach","coverUrl":"http://static.u17i.com/wan/upload/images/20160621/b25efa6du17a9edu174b5fu17b080u171e782c901a1c.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160621/b25efa6du17a9edu174b5fu17b080u171e782c901a1c.png","downUrl":"http://download.u17i.com/app/game/ssjx/Bleach20160623_yyq_1.7.16090.apk","title":"死神觉醒","gameType":"角色扮演","size":410701690},{"appId":67,"dowmLoadUrl":"http://tj.u17t.com/u4uj/click_MzQxXzI2NThfMjg1Ng==_MTY4MCoxMDUw","appPackageName":"com.heitao.eva.u17","coverUrl":"http://static.u17i.com/wan/upload/images/20160718/097dfa85u17952bu1743fdu1796a6u17d8ac23589967.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160718/097dfa85u17952bu1743fdu1796a6u17d8ac23589967.png","downUrl":"http://download.u17i.com/app/game/eva001/eva_u17_v1.2.0_2.0.0__201609202000_T160920100082939926.apk","title":"新世纪福音战士OL","gameType":"卡牌养成","size":228564274},{"appId":72,"dowmLoadUrl":"http://tj.u17t.com/g3pf/click_MzQ2XzI3MjBfMjkxNA==_MTY4MCoxMDUw","appPackageName":"com.longtugame.lxjjx.longtu","coverUrl":"http://static.u17i.com/wan/upload/images/20160902/c51bb033u173263u174bb6u178be3u170ed0ed66f6fe.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/c51bb033u173263u174bb6u178be3u170ed0ed66f6fe.png","downUrl":"http://download.u17i.com/app/game/lxjjx/lxjjx_20160920_yyq_1.3.1.283.apk","title":"螺旋境界线","gameType":"角色扮演","size":312209082}],"banner":[{"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/e5bfb383u179254u174542u17ae7eu17056d94963d48.jpg","type":1,"gotoUrl":"","appId":69},{"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/89f70c49u174df4u174dbfu1793cbu17acba4acc5199.jpg","type":1,"gotoUrl":"","appId":76},{"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/9adf35a2u17449du174fe0u17b2dbu175e8bd4387f2d.jpg","type":1,"gotoUrl":"","appId":74},{"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/06dac8ceu1761dau1740f0u17a952u172527e06e3735.jpg","type":1,"gotoUrl":"","appId":72}]},"page":1,"itemList":[{"appId":56,"coverUrl":"http://static.u17i.com/wan/upload/images/20160425/dd46cf03u17e188u17496cu1782e2u17cdf7106e0198.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160425/bd95d5f2u17eef0u174d4fu17bc67u179715a61afbcb.png","title":"山海战记","size":349756920,"desc":"山海战记是一款架空历史，幻想风二次元萌系塔防手游大作，经典塔防玩法，丰富策略与副本，精致画面，炫酷特效，等你来战!","dowmLoadUrl":"http://tj.u17t.com/vif9/click_MzI5XzI0NjZfMjY4MQ==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/shzj/u17_3.16.1_shzj_20160928.apk","downloadTimes":599252,"appPackageName":"com.smiletech.shzj.u17","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":76,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/fe76340eu17d756u17487fu178444u176582ece44514.jpg","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/260c3992u171da6u174136u1782c7u172cb698030151.jpg","title":"战斗吧蘑菇君","size":427024384,"desc":"最自由 随时加入 零等待让你即插即战！一个角色多种职业体验，武器即是技能千般搭配！放肆过瘾 无体力限制让你想战便战","dowmLoadUrl":"http://tj.u17t.com/ksx9/click_MzUwXzI3NjdfMjk1OA==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/mgj/mgj_20160927_yyq_1.1.7_.apk","downloadTimes":24,"appPackageName":"com.skymoons.zdbmgj","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"角色扮演"},{"appId":74,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/47818109u17a18bu174952u17a7a1u17456aef982074.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/fecceeb5u176de8u174e00u17a48au17b268f280b630.png","title":"魔灵战纪","size":72264313,"desc":"3D换装冒险手游，你没玩过这种游戏！","dowmLoadUrl":"http://tj.u17t.com/bupa/click_MzQ4XzI3NjNfMjk1NQ==_MTY4MCoxMDUw","downUrl":"","downloadTimes":19,"appPackageName":"com.zplay.roguelife","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"休闲养成"},{"appId":53,"coverUrl":"http://static.u17i.com/wan/upload/images/20160425/f7591df0u17d2c1u1747beu17add4u1755857acbe0f5.jpg","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160425/28e1d593u176bfau174b62u17a706u17534ccba2d276.jpg","title":"镇魂街","size":177514509,"desc":"有妖气同名漫画改编手游《镇魂街》9月27日公测开启！","dowmLoadUrl":"http://tj.u17t.com/iobl/click_MzI3XzI0MjRfMjY0MQ==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/zhj/zhj_20160927_yyq_1.6.3.apk","downloadTimes":1586079,"appPackageName":"com.ourpalm.zhj.zq ","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"即时战略"},{"appId":65,"coverUrl":"http://static.u17i.com/wan/upload/images/20160718/b05d445du17aba7u174bdeu1795feu17ad8fa225d2b1.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160718/4acfa20cu17338eu17462du17be71u178ed32dea888e.png","title":"天域幻想","size":289184073,"desc":"东方神话动漫幻想题材3D全景飞天卡牌手游","dowmLoadUrl":"http://tj.u17t.com/9ok9/click_MzM5XzI2MzNfMjgzNA==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/tyhx/u17_tianyuhuanxiang_45077.apk","downloadTimes":4821,"appPackageName":"com.xishanju.tggame.azsy","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":29,"coverUrl":"http://static.u17i.com/wan/upload/images/20160718/f6c0ade4u173a4cu174a0cu179b39u17008a50f21525.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160718/305df320u17383fu174a4cu17b55bu178f3c4a6a773b.png","title":"乖离性百万亚瑟王","size":101694250,"desc":"日本超人气卡牌RPG《乖离性百万亚瑟王》有妖气首发公测！","dowmLoadUrl":"http://tj.u17t.com/4z7n/click_MzA3XzE5NThfMjE3Ng==_MTkyMCoxMDgw","downUrl":"http://download.u17i.com/app/game/glxbwysw/bwysw_u17_3.5.1_2016092201.apk","downloadTimes":161849,"appPackageName":"com.ljapps.p1726.lj8","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":73,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/f16da39cu17fe52u17478eu17b58cu17fd30905bfdfe.jpg","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/ac23205cu174bb5u174d67u178518u17da2c5c09e0d3.jpg","title":"镇魂街：对决","size":159792560,"desc":"有妖气《镇魂街：对决》3D卡牌手游9月22日-9月29日开启删档封测！","dowmLoadUrl":"http://tj.u17t.com/rgct/click_MzQ3XzI3MjVfMjkxOQ==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/zhjdj/zhjdj_20160922_yyq_1.0.1.apk","downloadTimes":2478,"appPackageName":"com.ourpalm.zhjol.zq","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":72,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/b9884564u17081du174608u179ae6u17bf9b42be72e3.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/c51bb033u173263u174bb6u178be3u170ed0ed66f6fe.png","title":"螺旋境界线","size":312209082,"desc":"2016年最期待日系手游！","dowmLoadUrl":"http://tj.u17t.com/g3pf/click_MzQ2XzI3MjBfMjkxNA==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/lxjjx/lxjjx_20160920_yyq_1.3.1.283.apk","downloadTimes":2448,"appPackageName":"com.longtugame.lxjjx.longtu","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"角色扮演"},{"appId":70,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/c40cc909u1765bbu17436fu179936u171a77e6f6a59f.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/2e06bd6du17859du1747acu17b2acu17786764f3f000.png","title":"真三少女","size":143229491,"desc":"萌娘、爆衣、弹幕，你想要的都在这里！","dowmLoadUrl":"http://tj.u17t.com/wpaa/click_MzQ0XzI2OTdfMjg5Mw==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/zssn/OACG_Game_10001_CPS_Channel_1055-1473241837.apk","downloadTimes":2699,"appPackageName":"com.zhiyutianxia.zssn.dfecy","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":69,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/db0232b5u177909u1744fbu17bfb6u176f8347c38c5f.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/349db07eu17a053u174574u178ebcu17bbe63d699ccb.png","title":"阴阳师","size":540399805,"desc":"和风匠心巨制，开启唯美奇幻之旅","dowmLoadUrl":"http://tj.u17t.com/xsx7/click_MzQzXzI2OTBfMjg4Ng==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/yys/g37_netease_netease.u17_cps_dev_1.0.7.apk","downloadTimes":41043,"appPackageName":"com.netease.onmyoji","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"}],"Last_Modified":1475118937000}
     */

    private DataBean data;
    /**
     * data : {"message":"处理成功","stateCode":1,"returnData":{"hasMore":true,"gameheader":{"recommands":[{"appId":44,"dowmLoadUrl":"http://tj.u17t.com/rpqi/click_MzE2XzIyMzNfMjQ2Mg==_MTY4MCoxMDUw","appPackageName":"com.tencent.KiHan","coverUrl":"http://static.u17i.com/wan/upload/images/20160425/975d829fu17595eu174b20u17ba02u174a0faba15715.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160425/975d829fu17595eu174b20u17ba02u174a0faba15715.png","downUrl":"http://download.u17i.com/app/game/hyrz/hyrz_20160920_yyq_1.14.12.10.apk","title":"火影忍者","gameType":"卡牌养成","size":495136784},{"appId":69,"dowmLoadUrl":"http://tj.u17t.com/xsx7/click_MzQzXzI2OTBfMjg4Ng==_MTY4MCoxMDUw","appPackageName":"com.netease.onmyoji","coverUrl":"http://static.u17i.com/wan/upload/images/20160902/349db07eu17a053u174574u178ebcu17bbe63d699ccb.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/349db07eu17a053u174574u178ebcu17bbe63d699ccb.png","downUrl":"http://download.u17i.com/app/game/yys/g37_netease_netease.u17_cps_dev_1.0.7.apk","title":"阴阳师","gameType":"卡牌养成","size":540399805},{"appId":53,"dowmLoadUrl":"http://tj.u17t.com/iobl/click_MzI3XzI0MjRfMjY0MQ==_MTY4MCoxMDUw","appPackageName":"com.ourpalm.zhj.zq ","coverUrl":"http://static.u17i.com/wan/upload/images/20160425/28e1d593u176bfau174b62u17a706u17534ccba2d276.jpg","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160425/28e1d593u176bfau174b62u17a706u17534ccba2d276.jpg","downUrl":"http://download.u17i.com/app/game/zhj/zhj_20160927_yyq_1.6.3.apk","title":"镇魂街","gameType":"即时战略","size":177514509},{"appId":62,"dowmLoadUrl":"http://tj.u17t.com/i5p8/click_MzM2XzI1NDZfMjc1NQ==_MTY4MCoxMDUw","appPackageName":"com.xhhd.bleach","coverUrl":"http://static.u17i.com/wan/upload/images/20160621/b25efa6du17a9edu174b5fu17b080u171e782c901a1c.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160621/b25efa6du17a9edu174b5fu17b080u171e782c901a1c.png","downUrl":"http://download.u17i.com/app/game/ssjx/Bleach20160623_yyq_1.7.16090.apk","title":"死神觉醒","gameType":"角色扮演","size":410701690},{"appId":67,"dowmLoadUrl":"http://tj.u17t.com/u4uj/click_MzQxXzI2NThfMjg1Ng==_MTY4MCoxMDUw","appPackageName":"com.heitao.eva.u17","coverUrl":"http://static.u17i.com/wan/upload/images/20160718/097dfa85u17952bu1743fdu1796a6u17d8ac23589967.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160718/097dfa85u17952bu1743fdu1796a6u17d8ac23589967.png","downUrl":"http://download.u17i.com/app/game/eva001/eva_u17_v1.2.0_2.0.0__201609202000_T160920100082939926.apk","title":"新世纪福音战士OL","gameType":"卡牌养成","size":228564274},{"appId":72,"dowmLoadUrl":"http://tj.u17t.com/g3pf/click_MzQ2XzI3MjBfMjkxNA==_MTY4MCoxMDUw","appPackageName":"com.longtugame.lxjjx.longtu","coverUrl":"http://static.u17i.com/wan/upload/images/20160902/c51bb033u173263u174bb6u178be3u170ed0ed66f6fe.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/c51bb033u173263u174bb6u178be3u170ed0ed66f6fe.png","downUrl":"http://download.u17i.com/app/game/lxjjx/lxjjx_20160920_yyq_1.3.1.283.apk","title":"螺旋境界线","gameType":"角色扮演","size":312209082}],"banner":[{"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/e5bfb383u179254u174542u17ae7eu17056d94963d48.jpg","type":1,"gotoUrl":"","appId":69},{"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/89f70c49u174df4u174dbfu1793cbu17acba4acc5199.jpg","type":1,"gotoUrl":"","appId":76},{"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/9adf35a2u17449du174fe0u17b2dbu175e8bd4387f2d.jpg","type":1,"gotoUrl":"","appId":74},{"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/06dac8ceu1761dau1740f0u17a952u172527e06e3735.jpg","type":1,"gotoUrl":"","appId":72}]},"page":1,"itemList":[{"appId":56,"coverUrl":"http://static.u17i.com/wan/upload/images/20160425/dd46cf03u17e188u17496cu1782e2u17cdf7106e0198.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160425/bd95d5f2u17eef0u174d4fu17bc67u179715a61afbcb.png","title":"山海战记","size":349756920,"desc":"山海战记是一款架空历史，幻想风二次元萌系塔防手游大作，经典塔防玩法，丰富策略与副本，精致画面，炫酷特效，等你来战!","dowmLoadUrl":"http://tj.u17t.com/vif9/click_MzI5XzI0NjZfMjY4MQ==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/shzj/u17_3.16.1_shzj_20160928.apk","downloadTimes":599252,"appPackageName":"com.smiletech.shzj.u17","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":76,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/fe76340eu17d756u17487fu178444u176582ece44514.jpg","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/260c3992u171da6u174136u1782c7u172cb698030151.jpg","title":"战斗吧蘑菇君","size":427024384,"desc":"最自由 随时加入 零等待让你即插即战！一个角色多种职业体验，武器即是技能千般搭配！放肆过瘾 无体力限制让你想战便战","dowmLoadUrl":"http://tj.u17t.com/ksx9/click_MzUwXzI3NjdfMjk1OA==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/mgj/mgj_20160927_yyq_1.1.7_.apk","downloadTimes":24,"appPackageName":"com.skymoons.zdbmgj","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"角色扮演"},{"appId":74,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/47818109u17a18bu174952u17a7a1u17456aef982074.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/fecceeb5u176de8u174e00u17a48au17b268f280b630.png","title":"魔灵战纪","size":72264313,"desc":"3D换装冒险手游，你没玩过这种游戏！","dowmLoadUrl":"http://tj.u17t.com/bupa/click_MzQ4XzI3NjNfMjk1NQ==_MTY4MCoxMDUw","downUrl":"","downloadTimes":19,"appPackageName":"com.zplay.roguelife","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"休闲养成"},{"appId":53,"coverUrl":"http://static.u17i.com/wan/upload/images/20160425/f7591df0u17d2c1u1747beu17add4u1755857acbe0f5.jpg","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160425/28e1d593u176bfau174b62u17a706u17534ccba2d276.jpg","title":"镇魂街","size":177514509,"desc":"有妖气同名漫画改编手游《镇魂街》9月27日公测开启！","dowmLoadUrl":"http://tj.u17t.com/iobl/click_MzI3XzI0MjRfMjY0MQ==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/zhj/zhj_20160927_yyq_1.6.3.apk","downloadTimes":1586079,"appPackageName":"com.ourpalm.zhj.zq ","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"即时战略"},{"appId":65,"coverUrl":"http://static.u17i.com/wan/upload/images/20160718/b05d445du17aba7u174bdeu1795feu17ad8fa225d2b1.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160718/4acfa20cu17338eu17462du17be71u178ed32dea888e.png","title":"天域幻想","size":289184073,"desc":"东方神话动漫幻想题材3D全景飞天卡牌手游","dowmLoadUrl":"http://tj.u17t.com/9ok9/click_MzM5XzI2MzNfMjgzNA==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/tyhx/u17_tianyuhuanxiang_45077.apk","downloadTimes":4821,"appPackageName":"com.xishanju.tggame.azsy","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":29,"coverUrl":"http://static.u17i.com/wan/upload/images/20160718/f6c0ade4u173a4cu174a0cu179b39u17008a50f21525.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160718/305df320u17383fu174a4cu17b55bu178f3c4a6a773b.png","title":"乖离性百万亚瑟王","size":101694250,"desc":"日本超人气卡牌RPG《乖离性百万亚瑟王》有妖气首发公测！","dowmLoadUrl":"http://tj.u17t.com/4z7n/click_MzA3XzE5NThfMjE3Ng==_MTkyMCoxMDgw","downUrl":"http://download.u17i.com/app/game/glxbwysw/bwysw_u17_3.5.1_2016092201.apk","downloadTimes":161849,"appPackageName":"com.ljapps.p1726.lj8","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":73,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/f16da39cu17fe52u17478eu17b58cu17fd30905bfdfe.jpg","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/ac23205cu174bb5u174d67u178518u17da2c5c09e0d3.jpg","title":"镇魂街：对决","size":159792560,"desc":"有妖气《镇魂街：对决》3D卡牌手游9月22日-9月29日开启删档封测！","dowmLoadUrl":"http://tj.u17t.com/rgct/click_MzQ3XzI3MjVfMjkxOQ==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/zhjdj/zhjdj_20160922_yyq_1.0.1.apk","downloadTimes":2478,"appPackageName":"com.ourpalm.zhjol.zq","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":72,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/b9884564u17081du174608u179ae6u17bf9b42be72e3.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/c51bb033u173263u174bb6u178be3u170ed0ed66f6fe.png","title":"螺旋境界线","size":312209082,"desc":"2016年最期待日系手游！","dowmLoadUrl":"http://tj.u17t.com/g3pf/click_MzQ2XzI3MjBfMjkxNA==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/lxjjx/lxjjx_20160920_yyq_1.3.1.283.apk","downloadTimes":2448,"appPackageName":"com.longtugame.lxjjx.longtu","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"角色扮演"},{"appId":70,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/c40cc909u1765bbu17436fu179936u171a77e6f6a59f.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/2e06bd6du17859du1747acu17b2acu17786764f3f000.png","title":"真三少女","size":143229491,"desc":"萌娘、爆衣、弹幕，你想要的都在这里！","dowmLoadUrl":"http://tj.u17t.com/wpaa/click_MzQ0XzI2OTdfMjg5Mw==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/zssn/OACG_Game_10001_CPS_Channel_1055-1473241837.apk","downloadTimes":2699,"appPackageName":"com.zhiyutianxia.zssn.dfecy","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":69,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/db0232b5u177909u1744fbu17bfb6u176f8347c38c5f.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/349db07eu17a053u174574u178ebcu17bbe63d699ccb.png","title":"阴阳师","size":540399805,"desc":"和风匠心巨制，开启唯美奇幻之旅","dowmLoadUrl":"http://tj.u17t.com/xsx7/click_MzQzXzI2OTBfMjg4Ng==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/yys/g37_netease_netease.u17_cps_dev_1.0.7.apk","downloadTimes":41043,"appPackageName":"com.netease.onmyoji","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"}],"Last_Modified":1475118937000}}
     * code : 1
     */

    private int code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        private String message;
        private int stateCode;
        /**
         * hasMore : true
         * gameheader : {"recommands":[{"appId":44,"dowmLoadUrl":"http://tj.u17t.com/rpqi/click_MzE2XzIyMzNfMjQ2Mg==_MTY4MCoxMDUw","appPackageName":"com.tencent.KiHan","coverUrl":"http://static.u17i.com/wan/upload/images/20160425/975d829fu17595eu174b20u17ba02u174a0faba15715.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160425/975d829fu17595eu174b20u17ba02u174a0faba15715.png","downUrl":"http://download.u17i.com/app/game/hyrz/hyrz_20160920_yyq_1.14.12.10.apk","title":"火影忍者","gameType":"卡牌养成","size":495136784},{"appId":69,"dowmLoadUrl":"http://tj.u17t.com/xsx7/click_MzQzXzI2OTBfMjg4Ng==_MTY4MCoxMDUw","appPackageName":"com.netease.onmyoji","coverUrl":"http://static.u17i.com/wan/upload/images/20160902/349db07eu17a053u174574u178ebcu17bbe63d699ccb.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/349db07eu17a053u174574u178ebcu17bbe63d699ccb.png","downUrl":"http://download.u17i.com/app/game/yys/g37_netease_netease.u17_cps_dev_1.0.7.apk","title":"阴阳师","gameType":"卡牌养成","size":540399805},{"appId":53,"dowmLoadUrl":"http://tj.u17t.com/iobl/click_MzI3XzI0MjRfMjY0MQ==_MTY4MCoxMDUw","appPackageName":"com.ourpalm.zhj.zq ","coverUrl":"http://static.u17i.com/wan/upload/images/20160425/28e1d593u176bfau174b62u17a706u17534ccba2d276.jpg","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160425/28e1d593u176bfau174b62u17a706u17534ccba2d276.jpg","downUrl":"http://download.u17i.com/app/game/zhj/zhj_20160927_yyq_1.6.3.apk","title":"镇魂街","gameType":"即时战略","size":177514509},{"appId":62,"dowmLoadUrl":"http://tj.u17t.com/i5p8/click_MzM2XzI1NDZfMjc1NQ==_MTY4MCoxMDUw","appPackageName":"com.xhhd.bleach","coverUrl":"http://static.u17i.com/wan/upload/images/20160621/b25efa6du17a9edu174b5fu17b080u171e782c901a1c.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160621/b25efa6du17a9edu174b5fu17b080u171e782c901a1c.png","downUrl":"http://download.u17i.com/app/game/ssjx/Bleach20160623_yyq_1.7.16090.apk","title":"死神觉醒","gameType":"角色扮演","size":410701690},{"appId":67,"dowmLoadUrl":"http://tj.u17t.com/u4uj/click_MzQxXzI2NThfMjg1Ng==_MTY4MCoxMDUw","appPackageName":"com.heitao.eva.u17","coverUrl":"http://static.u17i.com/wan/upload/images/20160718/097dfa85u17952bu1743fdu1796a6u17d8ac23589967.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160718/097dfa85u17952bu1743fdu1796a6u17d8ac23589967.png","downUrl":"http://download.u17i.com/app/game/eva001/eva_u17_v1.2.0_2.0.0__201609202000_T160920100082939926.apk","title":"新世纪福音战士OL","gameType":"卡牌养成","size":228564274},{"appId":72,"dowmLoadUrl":"http://tj.u17t.com/g3pf/click_MzQ2XzI3MjBfMjkxNA==_MTY4MCoxMDUw","appPackageName":"com.longtugame.lxjjx.longtu","coverUrl":"http://static.u17i.com/wan/upload/images/20160902/c51bb033u173263u174bb6u178be3u170ed0ed66f6fe.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/c51bb033u173263u174bb6u178be3u170ed0ed66f6fe.png","downUrl":"http://download.u17i.com/app/game/lxjjx/lxjjx_20160920_yyq_1.3.1.283.apk","title":"螺旋境界线","gameType":"角色扮演","size":312209082}],"banner":[{"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/e5bfb383u179254u174542u17ae7eu17056d94963d48.jpg","type":1,"gotoUrl":"","appId":69},{"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/89f70c49u174df4u174dbfu1793cbu17acba4acc5199.jpg","type":1,"gotoUrl":"","appId":76},{"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/9adf35a2u17449du174fe0u17b2dbu175e8bd4387f2d.jpg","type":1,"gotoUrl":"","appId":74},{"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/06dac8ceu1761dau1740f0u17a952u172527e06e3735.jpg","type":1,"gotoUrl":"","appId":72}]}
         * page : 1
         * itemList : [{"appId":56,"coverUrl":"http://static.u17i.com/wan/upload/images/20160425/dd46cf03u17e188u17496cu1782e2u17cdf7106e0198.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160425/bd95d5f2u17eef0u174d4fu17bc67u179715a61afbcb.png","title":"山海战记","size":349756920,"desc":"山海战记是一款架空历史，幻想风二次元萌系塔防手游大作，经典塔防玩法，丰富策略与副本，精致画面，炫酷特效，等你来战!","dowmLoadUrl":"http://tj.u17t.com/vif9/click_MzI5XzI0NjZfMjY4MQ==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/shzj/u17_3.16.1_shzj_20160928.apk","downloadTimes":599252,"appPackageName":"com.smiletech.shzj.u17","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":76,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/fe76340eu17d756u17487fu178444u176582ece44514.jpg","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/260c3992u171da6u174136u1782c7u172cb698030151.jpg","title":"战斗吧蘑菇君","size":427024384,"desc":"最自由 随时加入 零等待让你即插即战！一个角色多种职业体验，武器即是技能千般搭配！放肆过瘾 无体力限制让你想战便战","dowmLoadUrl":"http://tj.u17t.com/ksx9/click_MzUwXzI3NjdfMjk1OA==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/mgj/mgj_20160927_yyq_1.1.7_.apk","downloadTimes":24,"appPackageName":"com.skymoons.zdbmgj","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"角色扮演"},{"appId":74,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/47818109u17a18bu174952u17a7a1u17456aef982074.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/fecceeb5u176de8u174e00u17a48au17b268f280b630.png","title":"魔灵战纪","size":72264313,"desc":"3D换装冒险手游，你没玩过这种游戏！","dowmLoadUrl":"http://tj.u17t.com/bupa/click_MzQ4XzI3NjNfMjk1NQ==_MTY4MCoxMDUw","downUrl":"","downloadTimes":19,"appPackageName":"com.zplay.roguelife","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"休闲养成"},{"appId":53,"coverUrl":"http://static.u17i.com/wan/upload/images/20160425/f7591df0u17d2c1u1747beu17add4u1755857acbe0f5.jpg","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160425/28e1d593u176bfau174b62u17a706u17534ccba2d276.jpg","title":"镇魂街","size":177514509,"desc":"有妖气同名漫画改编手游《镇魂街》9月27日公测开启！","dowmLoadUrl":"http://tj.u17t.com/iobl/click_MzI3XzI0MjRfMjY0MQ==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/zhj/zhj_20160927_yyq_1.6.3.apk","downloadTimes":1586079,"appPackageName":"com.ourpalm.zhj.zq ","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"即时战略"},{"appId":65,"coverUrl":"http://static.u17i.com/wan/upload/images/20160718/b05d445du17aba7u174bdeu1795feu17ad8fa225d2b1.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160718/4acfa20cu17338eu17462du17be71u178ed32dea888e.png","title":"天域幻想","size":289184073,"desc":"东方神话动漫幻想题材3D全景飞天卡牌手游","dowmLoadUrl":"http://tj.u17t.com/9ok9/click_MzM5XzI2MzNfMjgzNA==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/tyhx/u17_tianyuhuanxiang_45077.apk","downloadTimes":4821,"appPackageName":"com.xishanju.tggame.azsy","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":29,"coverUrl":"http://static.u17i.com/wan/upload/images/20160718/f6c0ade4u173a4cu174a0cu179b39u17008a50f21525.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160718/305df320u17383fu174a4cu17b55bu178f3c4a6a773b.png","title":"乖离性百万亚瑟王","size":101694250,"desc":"日本超人气卡牌RPG《乖离性百万亚瑟王》有妖气首发公测！","dowmLoadUrl":"http://tj.u17t.com/4z7n/click_MzA3XzE5NThfMjE3Ng==_MTkyMCoxMDgw","downUrl":"http://download.u17i.com/app/game/glxbwysw/bwysw_u17_3.5.1_2016092201.apk","downloadTimes":161849,"appPackageName":"com.ljapps.p1726.lj8","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":73,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/f16da39cu17fe52u17478eu17b58cu17fd30905bfdfe.jpg","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/ac23205cu174bb5u174d67u178518u17da2c5c09e0d3.jpg","title":"镇魂街：对决","size":159792560,"desc":"有妖气《镇魂街：对决》3D卡牌手游9月22日-9月29日开启删档封测！","dowmLoadUrl":"http://tj.u17t.com/rgct/click_MzQ3XzI3MjVfMjkxOQ==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/zhjdj/zhjdj_20160922_yyq_1.0.1.apk","downloadTimes":2478,"appPackageName":"com.ourpalm.zhjol.zq","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":72,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/b9884564u17081du174608u179ae6u17bf9b42be72e3.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/c51bb033u173263u174bb6u178be3u170ed0ed66f6fe.png","title":"螺旋境界线","size":312209082,"desc":"2016年最期待日系手游！","dowmLoadUrl":"http://tj.u17t.com/g3pf/click_MzQ2XzI3MjBfMjkxNA==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/lxjjx/lxjjx_20160920_yyq_1.3.1.283.apk","downloadTimes":2448,"appPackageName":"com.longtugame.lxjjx.longtu","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"角色扮演"},{"appId":70,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/c40cc909u1765bbu17436fu179936u171a77e6f6a59f.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/2e06bd6du17859du1747acu17b2acu17786764f3f000.png","title":"真三少女","size":143229491,"desc":"萌娘、爆衣、弹幕，你想要的都在这里！","dowmLoadUrl":"http://tj.u17t.com/wpaa/click_MzQ0XzI2OTdfMjg5Mw==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/zssn/OACG_Game_10001_CPS_Channel_1055-1473241837.apk","downloadTimes":2699,"appPackageName":"com.zhiyutianxia.zssn.dfecy","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"},{"appId":69,"coverUrl":"http://static.u17i.com/wan/upload/images/20160902/db0232b5u177909u1744fbu17bfb6u176f8347c38c5f.png","largeCoverUrl":"http://static.u17i.com/wan/upload/images/20160902/349db07eu17a053u174574u178ebcu17bbe63d699ccb.png","title":"阴阳师","size":540399805,"desc":"和风匠心巨制，开启唯美奇幻之旅","dowmLoadUrl":"http://tj.u17t.com/xsx7/click_MzQzXzI2OTBfMjg4Ng==_MTY4MCoxMDUw","downUrl":"http://download.u17i.com/app/game/yys/g37_netease_netease.u17_cps_dev_1.0.7.apk","downloadTimes":41043,"appPackageName":"com.netease.onmyoji","smallPictureUrls":null,"bigPictureUrls":null,"gameType":"卡牌养成"}]
         * Last_Modified : 1475118937000
         */

        private ReturnDataBean returnData;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStateCode() {
            return stateCode;
        }

        public void setStateCode(int stateCode) {
            this.stateCode = stateCode;
        }

        public ReturnDataBean getReturnData() {
            return returnData;
        }

        public void setReturnData(ReturnDataBean returnData) {
            this.returnData = returnData;
        }

        public static class ReturnDataBean {
            private boolean hasMore;
            private GameheaderBean gameheader;
            private int page;
            private long Last_Modified;
            /**
             * appId : 56
             * coverUrl : http://static.u17i.com/wan/upload/images/20160425/dd46cf03u17e188u17496cu1782e2u17cdf7106e0198.png
             * largeCoverUrl : http://static.u17i.com/wan/upload/images/20160425/bd95d5f2u17eef0u174d4fu17bc67u179715a61afbcb.png
             * title : 山海战记
             * size : 349756920
             * desc : 山海战记是一款架空历史，幻想风二次元萌系塔防手游大作，经典塔防玩法，丰富策略与副本，精致画面，炫酷特效，等你来战!
             * dowmLoadUrl : http://tj.u17t.com/vif9/click_MzI5XzI0NjZfMjY4MQ==_MTY4MCoxMDUw
             * downUrl : http://download.u17i.com/app/game/shzj/u17_3.16.1_shzj_20160928.apk
             * downloadTimes : 599252
             * appPackageName : com.smiletech.shzj.u17
             * smallPictureUrls : null
             * bigPictureUrls : null
             * gameType : 卡牌养成
             */

            private List<ItemListBean> itemList;

            public boolean isHasMore() {
                return hasMore;
            }

            public void setHasMore(boolean hasMore) {
                this.hasMore = hasMore;
            }

            public GameheaderBean getGameheader() {
                return gameheader;
            }

            public void setGameheader(GameheaderBean gameheader) {
                this.gameheader = gameheader;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public long getLast_Modified() {
                return Last_Modified;
            }

            public void setLast_Modified(long Last_Modified) {
                this.Last_Modified = Last_Modified;
            }

            public List<ItemListBean> getItemList() {
                return itemList;
            }

            public void setItemList(List<ItemListBean> itemList) {
                this.itemList = itemList;
            }

            public static class GameheaderBean {
                /**
                 * appId : 44
                 * dowmLoadUrl : http://tj.u17t.com/rpqi/click_MzE2XzIyMzNfMjQ2Mg==_MTY4MCoxMDUw
                 * appPackageName : com.tencent.KiHan
                 * coverUrl : http://static.u17i.com/wan/upload/images/20160425/975d829fu17595eu174b20u17ba02u174a0faba15715.png
                 * largeCoverUrl : http://static.u17i.com/wan/upload/images/20160425/975d829fu17595eu174b20u17ba02u174a0faba15715.png
                 * downUrl : http://download.u17i.com/app/game/hyrz/hyrz_20160920_yyq_1.14.12.10.apk
                 * title : 火影忍者
                 * gameType : 卡牌养成
                 * size : 495136784
                 */

                private List<RecommandsBean> recommands;
                /**
                 * coverUrl : http://static.u17i.com/wan/upload/images/20160902/e5bfb383u179254u174542u17ae7eu17056d94963d48.jpg
                 * type : 1
                 * gotoUrl :
                 * appId : 69
                 */

                private List<BannerBean> banner;

                public List<RecommandsBean> getRecommands() {
                    return recommands;
                }

                public void setRecommands(List<RecommandsBean> recommands) {
                    this.recommands = recommands;
                }

                public List<BannerBean> getBanner() {
                    return banner;
                }

                public void setBanner(List<BannerBean> banner) {
                    this.banner = banner;
                }

                public static class RecommandsBean implements View.OnClickListener {
                    private int appId;
                    private String dowmLoadUrl;
                    private String appPackageName;
                    private String coverUrl;
                    private String largeCoverUrl;
                    private String downUrl;
                    private String title;
                    private String gameType;
                    private int size;

                    public int getAppId() {
                        return appId;
                    }

                    public void setAppId(int appId) {
                        this.appId = appId;
                    }

                    public String getDowmLoadUrl() {
                        return dowmLoadUrl;
                    }

                    public void setDowmLoadUrl(String dowmLoadUrl) {
                        this.dowmLoadUrl = dowmLoadUrl;
                    }

                    public String getAppPackageName() {
                        return appPackageName;
                    }

                    public void setAppPackageName(String appPackageName) {
                        this.appPackageName = appPackageName;
                    }

                    public String getCoverUrl() {
                        return coverUrl;
                    }

                    public void setCoverUrl(String coverUrl) {
                        this.coverUrl = coverUrl;
                    }

                    public String getLargeCoverUrl() {
                        return largeCoverUrl;
                    }

                    public void setLargeCoverUrl(String largeCoverUrl) {
                        this.largeCoverUrl = largeCoverUrl;
                    }

                    public String getDownUrl() {
                        return downUrl;
                    }

                    public void setDownUrl(String downUrl) {
                        this.downUrl = downUrl;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getGameType() {
                        return gameType;
                    }

                    public void setGameType(String gameType) {
                        this.gameType = gameType;
                    }

                    public int getSize() {
                        return size;
                    }

                    public void setSize(int size) {
                        this.size = size;
                    }

                    @Override
                    public void onClick(View v) {
                        if (v instanceof FlickerProgressBar) {
                            ((FlickerProgressBar) v).toggle(downUrl);
                        } else {
                            Intent intent = new Intent("com.gameDetail");
                            intent.putExtra("appId",appId);
//                            U17Application.context.startActivity(intent);

                            ActivityTransitionLauncher.with(((MainActivity) v.getContext())).from(v).launch(intent);
//                            Intent intent = new Intent(v.getContext(), GameDetailActivity.class);
//                            intent.putExtra("appId",appId);
//                            ActivityOptionsCompat options =
//                                    ActivityOptionsCompat.makeSceneTransitionAnimation((MainActivity) v.getContext(),
//                                            ((View) v.getParent()).findViewById(R.id.coverUrl),v.getContext().getResources().getString(R.string.share_element_game));
//                            ActivityCompat.startActivity(v.getContext(), intent, options.toBundle());
                        }
                    }
                }

                public static class BannerBean implements View.OnClickListener{
                    private String coverUrl;
                    private int type;
                    private String gotoUrl;
                    private int appId;

                    public String getCoverUrl() {
                        return coverUrl;
                    }

                    public void setCoverUrl(String coverUrl) {
                        this.coverUrl = coverUrl;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public String getGotoUrl() {
                        return gotoUrl;
                    }

                    public void setGotoUrl(String gotoUrl) {
                        this.gotoUrl = gotoUrl;
                    }

                    public int getAppId() {
                        return appId;
                    }

                    public void setAppId(int appId) {
                        this.appId = appId;
                    }

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.gameDetail");
                        intent.putExtra("appId",appId);
                        U17Application.context.startActivity(intent);
                    }
                }
            }

            public static class ItemListBean implements View.OnClickListener {
                private int appId;
                private String coverUrl;
                private String largeCoverUrl;
                private String title;
                private int size;
                private String desc;
                private String dowmLoadUrl;
                private String downUrl;
                private int downloadTimes;
                private String appPackageName;
                private Object smallPictureUrls;
                private Object bigPictureUrls;
                private String gameType;

                public int getAppId() {
                    return appId;
                }

                public void setAppId(int appId) {
                    this.appId = appId;
                }

                public String getCoverUrl() {
                    return coverUrl;
                }

                public void setCoverUrl(String coverUrl) {
                    this.coverUrl = coverUrl;
                }

                public String getLargeCoverUrl() {
                    return largeCoverUrl;
                }

                public void setLargeCoverUrl(String largeCoverUrl) {
                    this.largeCoverUrl = largeCoverUrl;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getDowmLoadUrl() {
                    return dowmLoadUrl;
                }

                public void setDowmLoadUrl(String dowmLoadUrl) {
                    this.dowmLoadUrl = dowmLoadUrl;
                }

                public String getDownUrl() {
                    return downUrl;
                }

                public void setDownUrl(String downUrl) {
                    this.downUrl = downUrl;
                }

                public int getDownloadTimes() {
                    return downloadTimes;
                }

                public void setDownloadTimes(int downloadTimes) {
                    this.downloadTimes = downloadTimes;
                }

                public String getAppPackageName() {
                    return appPackageName;
                }

                public void setAppPackageName(String appPackageName) {
                    this.appPackageName = appPackageName;
                }

                public Object getSmallPictureUrls() {
                    return smallPictureUrls;
                }

                public void setSmallPictureUrls(Object smallPictureUrls) {
                    this.smallPictureUrls = smallPictureUrls;
                }

                public Object getBigPictureUrls() {
                    return bigPictureUrls;
                }

                public void setBigPictureUrls(Object bigPictureUrls) {
                    this.bigPictureUrls = bigPictureUrls;
                }

                public String getGameType() {
                    return gameType;
                }

                public void setGameType(String gameType) {
                    this.gameType = gameType;
                }

                @Override
                public void onClick(View v) {
                    if (v instanceof FlickerProgressBar) {
                        ((FlickerProgressBar) v).toggle(downUrl);
                    } else {
                        Intent intent = new Intent("com.gameDetail");
                        intent.putExtra("appId",appId);
                        ActivityTransitionLauncher.with(((MainActivity) v.getContext())).from(v).launch(intent);
//                        U17Application.context.startActivity(intent);
                    }
                }
            }
        }
    }
}
