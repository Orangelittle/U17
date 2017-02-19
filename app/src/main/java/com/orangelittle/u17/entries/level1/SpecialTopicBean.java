package com.orangelittle.u17.entries.level1;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.orangelittle.u17.activity.SpecialTopicDetailActivity;

import java.util.List;

/**
 * Created by wmc on 16-10-5.
 */

public class SpecialTopicBean {

    /**
     * code : 1
     * data : {"stateCode":1,"message":"成功","returnData":{"comics":[{"specialId":58,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/09/29/1475139926_A7w17Abzs8J5.jpg","title":"二次元巡礼","tag":"3","subTitle":"2016-09-30至2018-09-29","deadLine":1538150400,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=58&is_comment=1","description":""},{"specialId":57,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/09/26/1474857076_B53vLqZVvBaa.jpg","title":"第23届编辑部奖提名","tag":"1","subTitle":"2016-09-26至2038-01-19","deadLine":2147483647,"isExpired":true,"url":"","description":"第23届有妖气编辑部奖的评选范围为2016年3月1日至2016年8月31日之间发表的首发作品。\r\n为了保证公平公正的比赛原则，和有妖气签约期间作者的所有作品，以及有妖气商务合作类作品不会参加评奖。\r\n\u201c编辑部奖\u201d旨在给予具有实力、富有特色的作品以奖励。最高奖金高达3000元"},{"specialId":56,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/09/21/1474422738_1m1bzjfmrFZZ.jpg","title":"末日专题","tag":"3","subTitle":"2016-09-24至2018-09-21","deadLine":1537459200,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=56&is_comment=1","description":""},{"specialId":55,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/09/06/1473127044_pegCC4YxKb3p.jpg","title":"9月新书","tag":"1","subTitle":"2016-09-06至2018-09-06","deadLine":1536163200,"isExpired":true,"url":"","description":"书架里的漫画琳琅满目，缺总少了能直击中二魂的那一本？更新日未到，旧章翻来覆去看了几百遍？不要苦恼，有妖气书架攻略计划来啦！优秀作品每月推，品质保证哟！"},{"specialId":54,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/30/1472527581_RmsmDODRmIK8.jpg","title":"开学季","tag":"3","subTitle":"2016-09-21至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=54&is_comment=1","description":""},{"specialId":49,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/11/1470904603_3x0mt0GtZxd8.jpg","title":"盗墓笔记同人","tag":"1","subTitle":"2016-08-19至今","deadLine":0,"isExpired":true,"url":"","description":"最近盗墓笔记真是一波火过一波，作为一名资深稻米，小编深感欣慰。\r\n比起相关电影、电视剧，我盗笔漫画才是最先崛起的！\r\n这里有惊险刺激的探险，也有浪漫温馨的日常；有遵循原著的解密，也有架空世界的性福生活。\r\n看着各个精彩的故事，千言万语汇成一句话：瓶邪王道！"},{"specialId":53,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/10/1470811812_EkGsa1LektB2.jpg","title":"游素兰漫画","tag":"3","subTitle":"2016-08-15至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=53&is_comment=1","description":""},{"specialId":50,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/08/1470652277_GSegnW4R8zuG.jpg","title":"七夕","tag":"3","subTitle":"2016-08-12至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=50&is_comment=1","description":""},{"specialId":52,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/05/1470392825_mKfgb4MBkBlM.png","title":"二次元奥运","tag":"3","subTitle":"2016-08-12至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=52&is_comment=1","description":""},{"specialId":45,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/07/21/1469093381_PLSZCRt6CPm4.jpg","title":"腐海","tag":"3","subTitle":"2016-07-22至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=45&is_comment=1","description":""},{"specialId":44,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/07/07/1467880376_lFkGxFYAZw6a.jpg","title":"惊夏小黑屋","tag":"3","subTitle":"2016-07-08至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=44&is_comment=1","description":""},{"specialId":40,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/06/14/1465886603_91l1tz5x55j8.jpg","title":"魔兽专区","tag":"1","subTitle":"2016-06-14至今","deadLine":0,"isExpired":true,"url":"","description":"想在热议话题插上两嘴？一样的世界观，构架完整的西方魔幻史诗漫画，魔兽世界观科普补完漫画，游戏趣闻漫画，拿去装逼，不谢！"},{"specialId":39,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/06/06/1465201745_PZ2595OXT0tM.jpg","title":"烧脑大对决","tag":"1","subTitle":"2016-07-01至今","deadLine":0,"isExpired":true,"url":"","description":"作为烧脑死忠粉的你们，还在为漫荒而犯愁么？妖气娘彻夜未眠为大家筛选了六部超有趣的推理悬疑类漫画给大家充饥！又有漫画可以看啦(<ゝω·)~☆kira"},{"specialId":27,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/05/25/1464169643_40AIC2JNCJzr.jpg","title":"小千的冷茶馆","tag":"3","subTitle":"2016-06-06至2020-01-01","deadLine":1577808000,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=27&is_comment=1","description":""},{"specialId":29,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/04/21/1461208237_u0uIxr2kX33X.jpg","title":"彩漫","tag":"1","subTitle":"2016-05-11至今","deadLine":0,"isExpired":true,"url":"","description":"黑白漫画不习惯？这里的漫画全部都是彩色哦~这是一个五彩缤纷亮闪闪的世界，快戴好墨镜，防止闪瞎眼！"},{"specialId":30,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/04/22/1461310616_x9lLNp4TjJjj.jpg","title":"魔法少女","tag":"1","subTitle":"2016-06-17至今","deadLine":0,"isExpired":true,"url":"","description":"恋爱魔法、血腥魔女、超能凡人，这个世界到处充满魔法哦~和我签订契约吧"},{"specialId":18,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/01/28/1453959018_CCKlQ6BNdVz8.jpg","title":"萌妹子","tag":"1","subTitle":"2016-05-06至今","deadLine":0,"isExpired":true,"url":"","description":"御姐、萝莉、软萌妹子、霸道女王，全部都喜欢很难抉择怎么破？统统收入后宫吧~萌妹子漫画大集合"},{"specialId":21,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/03/28/1459160477_97985W475v75.jpg","title":"拟人大作战","tag":"1","subTitle":"2016-05-06至今","deadLine":0,"isExpired":true,"url":"","description":"我们没有成精哦，只是你的眼睛打开了新世界的大门，把所有的东西都看成了人类形态~"},{"specialId":22,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/03/28/1459161570_XUAxxd7Z5rY4.jpg","title":"古风","tag":"1","subTitle":"2016-05-11至今","deadLine":0,"isExpired":true,"url":"","description":"喜欢古风美人的同学看过来~什么，你对颜值超过妹子的汉子更有兴趣？也没规定美人一定是妹子不是？总之，有美人看总是赏心悦目滴~"},{"specialId":20,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/04/29/1461918459_OKmWM3tOPAod.jpg","title":"中秋","tag":"1","subTitle":"2016-05-11至今","deadLine":0,"isExpired":true,"url":"","description":"赏花，赏月，赏美人~可以说是本次漫画的主题啦，嗯\u2026\u2026似乎少点什么(⊙v⊙)对！吃的！"}],"hasMore":true,"page":1}}
     */

    private int code;
    /**
     * stateCode : 1
     * message : 成功
     * returnData : {"comics":[{"specialId":58,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/09/29/1475139926_A7w17Abzs8J5.jpg","title":"二次元巡礼","tag":"3","subTitle":"2016-09-30至2018-09-29","deadLine":1538150400,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=58&is_comment=1","description":""},{"specialId":57,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/09/26/1474857076_B53vLqZVvBaa.jpg","title":"第23届编辑部奖提名","tag":"1","subTitle":"2016-09-26至2038-01-19","deadLine":2147483647,"isExpired":true,"url":"","description":"第23届有妖气编辑部奖的评选范围为2016年3月1日至2016年8月31日之间发表的首发作品。\r\n为了保证公平公正的比赛原则，和有妖气签约期间作者的所有作品，以及有妖气商务合作类作品不会参加评奖。\r\n\u201c编辑部奖\u201d旨在给予具有实力、富有特色的作品以奖励。最高奖金高达3000元"},{"specialId":56,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/09/21/1474422738_1m1bzjfmrFZZ.jpg","title":"末日专题","tag":"3","subTitle":"2016-09-24至2018-09-21","deadLine":1537459200,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=56&is_comment=1","description":""},{"specialId":55,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/09/06/1473127044_pegCC4YxKb3p.jpg","title":"9月新书","tag":"1","subTitle":"2016-09-06至2018-09-06","deadLine":1536163200,"isExpired":true,"url":"","description":"书架里的漫画琳琅满目，缺总少了能直击中二魂的那一本？更新日未到，旧章翻来覆去看了几百遍？不要苦恼，有妖气书架攻略计划来啦！优秀作品每月推，品质保证哟！"},{"specialId":54,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/30/1472527581_RmsmDODRmIK8.jpg","title":"开学季","tag":"3","subTitle":"2016-09-21至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=54&is_comment=1","description":""},{"specialId":49,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/11/1470904603_3x0mt0GtZxd8.jpg","title":"盗墓笔记同人","tag":"1","subTitle":"2016-08-19至今","deadLine":0,"isExpired":true,"url":"","description":"最近盗墓笔记真是一波火过一波，作为一名资深稻米，小编深感欣慰。\r\n比起相关电影、电视剧，我盗笔漫画才是最先崛起的！\r\n这里有惊险刺激的探险，也有浪漫温馨的日常；有遵循原著的解密，也有架空世界的性福生活。\r\n看着各个精彩的故事，千言万语汇成一句话：瓶邪王道！"},{"specialId":53,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/10/1470811812_EkGsa1LektB2.jpg","title":"游素兰漫画","tag":"3","subTitle":"2016-08-15至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=53&is_comment=1","description":""},{"specialId":50,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/08/1470652277_GSegnW4R8zuG.jpg","title":"七夕","tag":"3","subTitle":"2016-08-12至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=50&is_comment=1","description":""},{"specialId":52,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/05/1470392825_mKfgb4MBkBlM.png","title":"二次元奥运","tag":"3","subTitle":"2016-08-12至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=52&is_comment=1","description":""},{"specialId":45,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/07/21/1469093381_PLSZCRt6CPm4.jpg","title":"腐海","tag":"3","subTitle":"2016-07-22至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=45&is_comment=1","description":""},{"specialId":44,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/07/07/1467880376_lFkGxFYAZw6a.jpg","title":"惊夏小黑屋","tag":"3","subTitle":"2016-07-08至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=44&is_comment=1","description":""},{"specialId":40,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/06/14/1465886603_91l1tz5x55j8.jpg","title":"魔兽专区","tag":"1","subTitle":"2016-06-14至今","deadLine":0,"isExpired":true,"url":"","description":"想在热议话题插上两嘴？一样的世界观，构架完整的西方魔幻史诗漫画，魔兽世界观科普补完漫画，游戏趣闻漫画，拿去装逼，不谢！"},{"specialId":39,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/06/06/1465201745_PZ2595OXT0tM.jpg","title":"烧脑大对决","tag":"1","subTitle":"2016-07-01至今","deadLine":0,"isExpired":true,"url":"","description":"作为烧脑死忠粉的你们，还在为漫荒而犯愁么？妖气娘彻夜未眠为大家筛选了六部超有趣的推理悬疑类漫画给大家充饥！又有漫画可以看啦(<ゝω·)~☆kira"},{"specialId":27,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/05/25/1464169643_40AIC2JNCJzr.jpg","title":"小千的冷茶馆","tag":"3","subTitle":"2016-06-06至2020-01-01","deadLine":1577808000,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=27&is_comment=1","description":""},{"specialId":29,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/04/21/1461208237_u0uIxr2kX33X.jpg","title":"彩漫","tag":"1","subTitle":"2016-05-11至今","deadLine":0,"isExpired":true,"url":"","description":"黑白漫画不习惯？这里的漫画全部都是彩色哦~这是一个五彩缤纷亮闪闪的世界，快戴好墨镜，防止闪瞎眼！"},{"specialId":30,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/04/22/1461310616_x9lLNp4TjJjj.jpg","title":"魔法少女","tag":"1","subTitle":"2016-06-17至今","deadLine":0,"isExpired":true,"url":"","description":"恋爱魔法、血腥魔女、超能凡人，这个世界到处充满魔法哦~和我签订契约吧"},{"specialId":18,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/01/28/1453959018_CCKlQ6BNdVz8.jpg","title":"萌妹子","tag":"1","subTitle":"2016-05-06至今","deadLine":0,"isExpired":true,"url":"","description":"御姐、萝莉、软萌妹子、霸道女王，全部都喜欢很难抉择怎么破？统统收入后宫吧~萌妹子漫画大集合"},{"specialId":21,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/03/28/1459160477_97985W475v75.jpg","title":"拟人大作战","tag":"1","subTitle":"2016-05-06至今","deadLine":0,"isExpired":true,"url":"","description":"我们没有成精哦，只是你的眼睛打开了新世界的大门，把所有的东西都看成了人类形态~"},{"specialId":22,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/03/28/1459161570_XUAxxd7Z5rY4.jpg","title":"古风","tag":"1","subTitle":"2016-05-11至今","deadLine":0,"isExpired":true,"url":"","description":"喜欢古风美人的同学看过来~什么，你对颜值超过妹子的汉子更有兴趣？也没规定美人一定是妹子不是？总之，有美人看总是赏心悦目滴~"},{"specialId":20,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/04/29/1461918459_OKmWM3tOPAod.jpg","title":"中秋","tag":"1","subTitle":"2016-05-11至今","deadLine":0,"isExpired":true,"url":"","description":"赏花，赏月，赏美人~可以说是本次漫画的主题啦，嗯\u2026\u2026似乎少点什么(⊙v⊙)对！吃的！"}],"hasMore":true,"page":1}
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int stateCode;
        private String message;
        /**
         * comics : [{"specialId":58,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/09/29/1475139926_A7w17Abzs8J5.jpg","title":"二次元巡礼","tag":"3","subTitle":"2016-09-30至2018-09-29","deadLine":1538150400,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=58&is_comment=1","description":""},{"specialId":57,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/09/26/1474857076_B53vLqZVvBaa.jpg","title":"第23届编辑部奖提名","tag":"1","subTitle":"2016-09-26至2038-01-19","deadLine":2147483647,"isExpired":true,"url":"","description":"第23届有妖气编辑部奖的评选范围为2016年3月1日至2016年8月31日之间发表的首发作品。\r\n为了保证公平公正的比赛原则，和有妖气签约期间作者的所有作品，以及有妖气商务合作类作品不会参加评奖。\r\n\u201c编辑部奖\u201d旨在给予具有实力、富有特色的作品以奖励。最高奖金高达3000元"},{"specialId":56,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/09/21/1474422738_1m1bzjfmrFZZ.jpg","title":"末日专题","tag":"3","subTitle":"2016-09-24至2018-09-21","deadLine":1537459200,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=56&is_comment=1","description":""},{"specialId":55,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/09/06/1473127044_pegCC4YxKb3p.jpg","title":"9月新书","tag":"1","subTitle":"2016-09-06至2018-09-06","deadLine":1536163200,"isExpired":true,"url":"","description":"书架里的漫画琳琅满目，缺总少了能直击中二魂的那一本？更新日未到，旧章翻来覆去看了几百遍？不要苦恼，有妖气书架攻略计划来啦！优秀作品每月推，品质保证哟！"},{"specialId":54,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/30/1472527581_RmsmDODRmIK8.jpg","title":"开学季","tag":"3","subTitle":"2016-09-21至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=54&is_comment=1","description":""},{"specialId":49,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/11/1470904603_3x0mt0GtZxd8.jpg","title":"盗墓笔记同人","tag":"1","subTitle":"2016-08-19至今","deadLine":0,"isExpired":true,"url":"","description":"最近盗墓笔记真是一波火过一波，作为一名资深稻米，小编深感欣慰。\r\n比起相关电影、电视剧，我盗笔漫画才是最先崛起的！\r\n这里有惊险刺激的探险，也有浪漫温馨的日常；有遵循原著的解密，也有架空世界的性福生活。\r\n看着各个精彩的故事，千言万语汇成一句话：瓶邪王道！"},{"specialId":53,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/10/1470811812_EkGsa1LektB2.jpg","title":"游素兰漫画","tag":"3","subTitle":"2016-08-15至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=53&is_comment=1","description":""},{"specialId":50,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/08/1470652277_GSegnW4R8zuG.jpg","title":"七夕","tag":"3","subTitle":"2016-08-12至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=50&is_comment=1","description":""},{"specialId":52,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/08/05/1470392825_mKfgb4MBkBlM.png","title":"二次元奥运","tag":"3","subTitle":"2016-08-12至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=52&is_comment=1","description":""},{"specialId":45,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/07/21/1469093381_PLSZCRt6CPm4.jpg","title":"腐海","tag":"3","subTitle":"2016-07-22至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=45&is_comment=1","description":""},{"specialId":44,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/07/07/1467880376_lFkGxFYAZw6a.jpg","title":"惊夏小黑屋","tag":"3","subTitle":"2016-07-08至今","deadLine":0,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=44&is_comment=1","description":""},{"specialId":40,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/06/14/1465886603_91l1tz5x55j8.jpg","title":"魔兽专区","tag":"1","subTitle":"2016-06-14至今","deadLine":0,"isExpired":true,"url":"","description":"想在热议话题插上两嘴？一样的世界观，构架完整的西方魔幻史诗漫画，魔兽世界观科普补完漫画，游戏趣闻漫画，拿去装逼，不谢！"},{"specialId":39,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/06/06/1465201745_PZ2595OXT0tM.jpg","title":"烧脑大对决","tag":"1","subTitle":"2016-07-01至今","deadLine":0,"isExpired":true,"url":"","description":"作为烧脑死忠粉的你们，还在为漫荒而犯愁么？妖气娘彻夜未眠为大家筛选了六部超有趣的推理悬疑类漫画给大家充饥！又有漫画可以看啦(<ゝω·)~☆kira"},{"specialId":27,"specialType":2,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/05/25/1464169643_40AIC2JNCJzr.jpg","title":"小千的冷茶馆","tag":"3","subTitle":"2016-06-06至2020-01-01","deadLine":1577808000,"isExpired":true,"url":"http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=27&is_comment=1","description":""},{"specialId":29,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/04/21/1461208237_u0uIxr2kX33X.jpg","title":"彩漫","tag":"1","subTitle":"2016-05-11至今","deadLine":0,"isExpired":true,"url":"","description":"黑白漫画不习惯？这里的漫画全部都是彩色哦~这是一个五彩缤纷亮闪闪的世界，快戴好墨镜，防止闪瞎眼！"},{"specialId":30,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/04/22/1461310616_x9lLNp4TjJjj.jpg","title":"魔法少女","tag":"1","subTitle":"2016-06-17至今","deadLine":0,"isExpired":true,"url":"","description":"恋爱魔法、血腥魔女、超能凡人，这个世界到处充满魔法哦~和我签订契约吧"},{"specialId":18,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/01/28/1453959018_CCKlQ6BNdVz8.jpg","title":"萌妹子","tag":"1","subTitle":"2016-05-06至今","deadLine":0,"isExpired":true,"url":"","description":"御姐、萝莉、软萌妹子、霸道女王，全部都喜欢很难抉择怎么破？统统收入后宫吧~萌妹子漫画大集合"},{"specialId":21,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/03/28/1459160477_97985W475v75.jpg","title":"拟人大作战","tag":"1","subTitle":"2016-05-06至今","deadLine":0,"isExpired":true,"url":"","description":"我们没有成精哦，只是你的眼睛打开了新世界的大门，把所有的东西都看成了人类形态~"},{"specialId":22,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/03/28/1459161570_XUAxxd7Z5rY4.jpg","title":"古风","tag":"1","subTitle":"2016-05-11至今","deadLine":0,"isExpired":true,"url":"","description":"喜欢古风美人的同学看过来~什么，你对颜值超过妹子的汉子更有兴趣？也没规定美人一定是妹子不是？总之，有美人看总是赏心悦目滴~"},{"specialId":20,"specialType":1,"isComment":1,"cover":"http://image.mylife.u17t.com/2016/04/29/1461918459_OKmWM3tOPAod.jpg","title":"中秋","tag":"1","subTitle":"2016-05-11至今","deadLine":0,"isExpired":true,"url":"","description":"赏花，赏月，赏美人~可以说是本次漫画的主题啦，嗯\u2026\u2026似乎少点什么(⊙v⊙)对！吃的！"}]
         * hasMore : true
         * page : 1
         */

        private ReturnDataBean returnData;

        public int getStateCode() {
            return stateCode;
        }

        public void setStateCode(int stateCode) {
            this.stateCode = stateCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ReturnDataBean getReturnData() {
            return returnData;
        }

        public void setReturnData(ReturnDataBean returnData) {
            this.returnData = returnData;
        }

        public static class ReturnDataBean {
            private boolean hasMore;
            private int page;
            /**
             * specialId : 58
             * specialType : 2
             * isComment : 1
             * cover : http://image.mylife.u17t.com/2016/09/29/1475139926_A7w17Abzs8J5.jpg
             * title : 二次元巡礼
             * tag : 3
             * subTitle : 2016-09-30至2018-09-29
             * deadLine : 1538150400
             * isExpired : true
             * url : http://www.u17.com/z/zt/appspecial/special_comic_list_new.html?special_id=58&is_comment=1
             * description :
             */

            private List<ComicsBean> comics;

            public boolean isHasMore() {
                return hasMore;
            }

            public void setHasMore(boolean hasMore) {
                this.hasMore = hasMore;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public List<ComicsBean> getComics() {
                return comics;
            }

            public void setComics(List<ComicsBean> comics) {
                this.comics = comics;
            }

            public static class ComicsBean implements View.OnClickListener {
                private int specialId;
                private int specialType;
                private int isComment;
                private String cover;
                private String title;
                private String tag;
                private String subTitle;
                private int deadLine;
                private boolean isExpired;
                private String url;
                private String description;

                public int getSpecialId() {
                    return specialId;
                }

                public void setSpecialId(int specialId) {
                    this.specialId = specialId;
                }

                public int getSpecialType() {
                    return specialType;
                }

                public void setSpecialType(int specialType) {
                    this.specialType = specialType;
                }

                public int getIsComment() {
                    return isComment;
                }

                public void setIsComment(int isComment) {
                    this.isComment = isComment;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }

                public String getSubTitle() {
                    return subTitle;
                }

                public void setSubTitle(String subTitle) {
                    this.subTitle = subTitle;
                }

                public int getDeadLine() {
                    return deadLine;
                }

                public void setDeadLine(int deadLine) {
                    this.deadLine = deadLine;
                }

                public boolean isIsExpired() {
                    return isExpired;
                }

                public void setIsExpired(boolean isExpired) {
                    this.isExpired = isExpired;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SpecialTopicDetailActivity.class);
                    intent.putExtra("specialId", specialId+"");
                    intent.putExtra("isComment", isComment+"");
                    intent.putExtra("url", url);
                    intent.putExtra("title", title);
                    v.getContext().startActivity(intent);
                    Toast.makeText(v.getContext(), "hello", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
