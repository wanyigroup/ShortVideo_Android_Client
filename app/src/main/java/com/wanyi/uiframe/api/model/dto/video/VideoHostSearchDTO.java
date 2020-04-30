package com.wanyi.uiframe.api.model.dto.video;

import com.wanyi.uiframe.api.model.dto.vo.ISearchTendencyVO;

import java.util.List;

import lombok.Data;

@Data
public class VideoHostSearchDTO {


    /**
     * status : 200
     * data : [{"keyword":"富兰克林四双","count":"4005814"},{"keyword":"朱丹叫错陈立农","count":"2812093"},{"keyword":"俄罗斯遭禁赛4年","count":"2413946"},{"keyword":"保罗晃晕戈贝尔","count":"2320742"},{"keyword":"CBA裁判被误伤","count":"2130216"},{"keyword":"吉喆球衣退役仪式","count":"2071113"},{"keyword":"威少34分3篮板","count":"1865505"},{"keyword":"C罗后悔离开皇马","count":"1714383"},{"keyword":"歌唱家叶矛去世","count":"1540618"},{"keyword":"姜至鹏回应","count":"1527378"},{"keyword":"小米正式进入日本","count":"1416528"},{"keyword":"马来西亚年度汉字","count":"1334707"},{"keyword":"焊接油罐车爆炸","count":"1179956"},{"keyword":"加总理致信李玉刚","count":"979356"},{"keyword":"北控险胜福建","count":"816677"},{"keyword":"王思聪资产被冻结","count":"781984"},{"keyword":"袁姗姗拍戏坠马","count":"680389"},{"keyword":"郑爽联合国大会","count":"658971"},{"keyword":"2019东亚杯","count":"625471"},{"keyword":"海南国际电影节","count":"616262"},{"keyword":"敦促释放孟晚舟","count":"583411"},{"keyword":"国奥绝杀塔吉克斯坦","count":"554052"},{"keyword":"宋祖儿回应恋情","count":"492697"},{"keyword":"上财副教授被开除","count":"437177"},{"keyword":"吉林战胜新疆","count":"384250"},{"keyword":"普京回应禁赛","count":"328068"},{"keyword":"丁俊晖英锦赛冠军","count":"258871"}]
     */

    private int status;
    private List<DataBean> data;

    @Data
    public class DataBean implements ISearchTendencyVO {
        /**
         * keyword : 富兰克林四双
         * count : 4005814
         */

        private String keyword;
        private String count;


        @Override
        public String getTitle() {
            return keyword;
        }

        @Override
        public String getReading() {
            return count;
        }
    }

}
