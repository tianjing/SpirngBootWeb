package com.github.tianjing.test.elasticsearch.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import tgtools.exceptions.APPErrorException;
import tgtools.util.StringUtil;

/**
 * @author 田径
 * @date 2020-03-05 14:53
 * @desc
 **/
@Data
@AllArgsConstructor
@Document(indexName = "teamfour-elasticsearch-client-zhidaoqa-item")
public class ZhiDaoQaDO {
    public ZhiDaoQaDO(){}
    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word" ,searchAnalyzer="ik_max_word")
    private String[] answers;
    @Field(type = FieldType.Text, analyzer = "ik_max_word" ,searchAnalyzer="ik_max_word")
    private String url;
    @Field(type = FieldType.Text, analyzer = "ik_max_word" ,searchAnalyzer="ik_max_word")
    private String question;
    @Field(type = FieldType.Keyword)
    private String[] tags;


    public String getAnswers() {
        return StringUtil.join(answers,",") ;
    }

    public String getTags() {
        return StringUtil.join(tags,",") ;
    }

    public static void main(String[] args) throws APPErrorException {
        String content = "{ \"_id\" : { \"$oid\" : \"5d36e593bc54f451543d9ff0\" }," +
                " \"url\" : \"http://zhidao.baidu.com/question/1515853519504435500.html\"," +
                " \"answers\" : [ \"眼球突出可能是近视，复视则是散光，建议到眼科或眼镜店检查视力，及时进行矫正，防止视力问题更严重。\" ]," +
                " \"question\" : \"眼球突出，复视，视力减退到底是什么病在捣鬼\",\"tags\":[\"视力\",\"人体常识\",\"五官科\"]}";

        ZhiDaoQaDO vZhiDaoQaDO = tgtools.util.JsonParseHelper.parseToObject(content,ZhiDaoQaDO.class,false);
        System.out.println(tgtools.util.JsonParseHelper.parseToJson(vZhiDaoQaDO,false));
        System.out.println(vZhiDaoQaDO);
    }

}

