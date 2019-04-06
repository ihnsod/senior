package com.Ihnsod.elasticsearch.TransportClient;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.global.GlobalAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.aggregations.bucket.histogram.HistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author: Ihnsod
 * @create: 2019/03/20 20:27
 * ElasticSearch TransportClient 客户端搜索示例
 **/
public class TransportClientSearchDemo {

    private static TransportClient transportClient;

    private static final String INDEX = "forum";
    private static final String TYPE = "article";
    private static final String ID_ONE = "1";
    private static final String ID_TWO = "2";

    private static final String TITLE_FIELD = "title";
    private static final String CONTENT_FIELD = "content";

    static {
        try {
            transportClient = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * matchAllQuery 全文匹配 查询 某index下的所有值
     * 可以设置多个type
     */
    @Test
    public void matchAllQuery() {
        MatchAllQueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        SearchResponse searchResponse = transportClient.prepareSearch(INDEX).setTypes(TYPE).setQuery(queryBuilder).get();
        printIn(searchResponse);
    }

    /**
     * matchQuery 全文搜索 会把我们的搜索词进行分词然后进行搜索
     */
    @Test
    public void matchQuery() {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery(TITLE_FIELD, "java");
        SearchResponse response = transportClient.prepareSearch(INDEX).setTypes(TYPE).setFrom(1).setSize(4).setQuery(queryBuilder).get();
        printIn(response);
    }

    /**
     * matchQuery 多列全文搜索 可以跨字段进行搜索
     * 可以设置多个字段搜索之间的关系等参数
     */
    @Test
    public void multiMatchQuery() {
        MultiMatchQueryBuilder matchQueryBuilder = QueryBuilders.multiMatchQuery("java", TITLE_FIELD, CONTENT_FIELD);
        matchQueryBuilder.operator(Operator.AND);
        SearchResponse response = transportClient.prepareSearch(INDEX).setFrom(1).setSize(5).setQuery(matchQueryBuilder).get();
        printIn(response);
    }

    /**
     * constant_score 过滤查询 不会计算score分值 只进行过滤 性能高
     * termsQuery可以进行多值查询
     */
    @Test
    public void termFilterQuery() {
//        ConstantScoreQueryBuilder queryBuilder = QueryBuilders.constantScoreQuery(QueryBuilders.termQuery(TITLE_FIELD, "java"));
        ConstantScoreQueryBuilder queryBuilder = QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery(TITLE_FIELD, "java", "spark"));
        SearchResponse response = transportClient.prepareSearch(INDEX).setQuery(queryBuilder).get();
        printIn(response);

    }

    /**
     * constant_score 过滤查询 不会计算score分值 只进行过滤
     */
    @Test
    public void constantScoreQuery() {
        ConstantScoreQueryBuilder queryBuilder = QueryBuilders.constantScoreQuery(QueryBuilders.termQuery(TITLE_FIELD, "java"));
        SearchResponse response = transportClient.prepareSearch(INDEX).setQuery(queryBuilder).get();
        printIn(response);

    }

    /**
     * bool多条件组合查询 可以根据自己的需要进行条件组合
     */
    @Test
    public void boolQuery() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
//                .must(QueryBuilders.termQuery(TITLE_FIELD, "java"))
//                .must(QueryBuilders.termQuery(CONTENT_FIELD, "spark"))
//                .should(QueryBuilders.termQuery(TITLE_FIELD, "elasticSearch"))
//                .should(QueryBuilders.termQuery(CONTENT_FIELD, "hadoop"))
//                .mustNot(QueryBuilders.termQuery(TITLE_FIELD, "and"))
                .filter(QueryBuilders.termQuery(CONTENT_FIELD, "programming"));
        SearchResponse response = transportClient.prepareSearch(INDEX).setTypes(TYPE).setQuery(queryBuilder).setFrom(1).setSize(5).get();
        printIn(response);
    }

    /**
     * 使用 scroll 全文检索 使用_doc排序效率高
     */
    @Test
    public void scrollQuery() {
        SearchResponse response = transportClient.prepareSearch(INDEX)
                .setTypes(TYPE)
                .setQuery(QueryBuilders.matchAllQuery())
                .setSize(2)
                .addSort("_doc", SortOrder.DESC)
                .setScroll(new TimeValue(1000))
                .get();
        while (response.getHits().getHits().length > 0) {
            printIn(response);
            response = transportClient.prepareSearchScroll(response.getScrollId())
                    .setScroll(new TimeValue(1000))
                    .get();
        }
    }

    /**
     * match_parse + slop 实现近似匹配（效率低）
     * proximity match  设置的slop距离为最大距离
     */

    @Test
    public void matchParseSlop() {
        MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery(CONTENT_FIELD, "java spark").
                slop(3);
        SearchResponse response = transportClient.prepareSearch(INDEX)
                .setTypes(TYPE)
                .setQuery(queryBuilder)
                .setFrom(0)
                .setSize(10)
                .get();
        printIn(response);
    }

    /**
     * 混合使用match 和近似匹配实现召回率和精准度的平衡（效率低）
     */
    @Test
    public void boolAndMatchParse() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                // must 中的matchQuery提供全文分词匹配
                .must(QueryBuilders.matchQuery(CONTENT_FIELD, "java spark"))
                // 由于should条件只有一个 所以要满足 , should 条件使用matchPhrase进行近似匹配并设置slop值,贡献检索分数
                .should(QueryBuilders.matchPhraseQuery(CONTENT_FIELD, "java spark").slop(50));

        SearchResponse response = transportClient.prepareSearch(INDEX).setTypes(TYPE).setQuery(queryBuilder).get();

        printIn(response);

    }

    /**
     * 后话以上步骤，只对分页的分页的数据进行 近似匹配
     */
    @Test
    public void boolAndMatchParseAndRescoreQuery() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                // must 中的matchQuery提供全文分词匹配
                .must(QueryBuilders.matchQuery(CONTENT_FIELD, "java spark"));


        SearchResponse response = transportClient.prepareSearch(INDEX)
                .setTypes(TYPE)
                .setQuery(queryBuilder)
                .get();

        printIn(response);

    }

    /**
     * 使用前缀完成搜索推荐功能 其实就是根据term去搜索
     * 只不过是根据前缀去搜索，尽量不要使用
     * 因为最后一个term总扫描全部的索引 效率低
     */
    @Test
    public void searchRecommend() {
        MatchPhrasePrefixQueryBuilder prefixQueryBuilder = QueryBuilders.matchPhrasePrefixQuery(CONTENT_FIELD, "java")
                .maxExpansions(10)
                .slop(10);

        SearchResponse response = transportClient.prepareSearch(INDEX)
                .setTypes(TYPE)
                .setQuery(prefixQueryBuilder)
                .get();
        printIn(response);

    }

    /**
     * 优化评分的四种方式
     * 1 should中使用boost
     */

    @Test
    public void optimizationOne() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery(TITLE_FIELD, "java spark").boost(2))
                .should(QueryBuilders.matchQuery(CONTENT_FIELD, "java spark").boost(3));

        SearchResponse response = transportClient.prepareSearch(INDEX).setTypes(TYPE).setQuery(queryBuilder).get();
        printIn(response);
    }

    /**
     * 2 重构查询结构  es的新版本中印象越来越小了
     */
    @Test
    public void optimizationTwo() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery(TITLE_FIELD, "java spark").boost(2))
                .should(QueryBuilders.matchQuery(CONTENT_FIELD, "java spark").boost(3))
                .should(QueryBuilders.matchQuery(CONTENT_FIELD, "java spark").boost(3))
                .should(QueryBuilders.matchQuery(CONTENT_FIELD, "java spark").boost(3));

        //比如上面的查询可以变为下面的语句 在should中再嵌套bool 被嵌套的语句的权重就会降低
        QueryBuilders.boolQuery().should(QueryBuilders.matchQuery(TITLE_FIELD, "java spark").boost(2))
                .should(QueryBuilders.matchQuery(CONTENT_FIELD, "java spark").boost(3))
                .should(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery(TITLE_FIELD, "java spark"))
                        .should(QueryBuilders.matchQuery(CONTENT_FIELD, "java spark")));

        SearchResponse response = transportClient.prepareSearch(INDEX).setTypes(TYPE).setQuery(queryBuilder).get();
        printIn(response);
    }


    /**
     * 3. negative boost  negative的doc，会乘以negative_boost，降低分数
     */
    @Test
    public void optimizationThree() {
        BoostingQueryBuilder queryBuilder = QueryBuilders.boostingQuery(QueryBuilders.matchQuery(TITLE_FIELD, "java spark")
                , QueryBuilders.matchQuery(CONTENT_FIELD, "java spark")).negativeBoost(0.2F);
        SearchResponse response = transportClient.prepareSearch(INDEX).setTypes(TYPE).setQuery(queryBuilder).get();
        printIn(response);

    }

    /**
     * 4. constant_score
     * 如果你压根儿不需要相关度评分，直接走constant_score加filter，所有的doc分数都是1，没有评分的概念了
     */
    @Test
    public void optimizationFour() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.constantScoreQuery(QueryBuilders.matchQuery(TITLE_FIELD, "java spark")))
                .should(QueryBuilders.constantScoreQuery(QueryBuilders.matchQuery(TITLE_FIELD, "java spark")));
        SearchResponse response = transportClient.prepareSearch(INDEX).setTypes(TYPE).setQuery(queryBuilder).get();
        printIn(response);

    }

    /**
     * functionScore 自定义打分机制  不知道API 新版本影响越来越小
     */
    @Test
    public void functionScore() {
        FunctionScoreQueryBuilder queryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders
                .multiMatchQuery("java spark", CONTENT_FIELD, TITLE_FIELD)).boostMode(CombineFunction.MULTIPLY);
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] functionBuilders = queryBuilder.filterFunctionBuilders();

    }

    /**
     * 模糊搜索 fuzzy搜索 可以纠正我们的搜索值进行尝试匹配
     * fuzziness 搜索文本最多纠正几次可以跟数据进行匹配默认为 2
     */
    @Test
    public void fuzzyQuery() {
        // fuzzy查询
        FuzzyQueryBuilder fuzzy = QueryBuilders.fuzzyQuery(CONTENT_FIELD, "jvaa").fuzziness(Fuzziness.TWO);
        //上面这种方式用的比较少，一般都是使用match查询里面套上 fuzzy + fuzziness.auto
        MatchQueryBuilder fuzzyQuery = QueryBuilders.matchQuery(CONTENT_FIELD, "jvaa")
                .fuzziness(Fuzziness.AUTO).operator(Operator.AND);

    }

    /**
     * demo数据
     * PUT /tvs
     * {
     * "mappings": {
     * "sales": {
     * "properties": {
     * "price": {
     * "type": "long"
     * },
     * "color": {
     * "type": "keyword"
     * },
     * "brand": {
     * "type": "keyword"
     * },
     * "sold_date": {
     * "type": "date"
     * }* 			}
     * }* 	}
     * }
     * <p>
     * POST /tvs/sales/_bulk
     * { "index": {}}
     * { "price" : 1000, "color" : "红色", "brand" : "长虹", "sold_date" : "2016-10-28" }
     * { "index": {}}
     * { "price" : 2000, "color" : "红色", "brand" : "长虹", "sold_date" : "2016-11-05" }
     * { "index": {}}
     * { "price" : 3000, "color" : "绿色", "brand" : "小米", "sold_date" : "2016-05-18" }
     * { "index": {}}
     * { "price" : 1500, "color" : "蓝色", "brand" : "TCL", "sold_date" : "2016-07-02" }
     * { "index": {}}
     * { "price" : 1200, "color" : "绿色", "brand" : "TCL", "sold_date" : "2016-08-19" }
     * { "index": {}}
     * { "price" : 2000, "color" : "红色", "brand" : "长虹", "sold_date" : "2016-11-05" }
     * { "index": {}}
     * { "price" : 8000, "color" : "红色", "brand" : "三星", "sold_date" : "2017-01-01" }
     * { "index": {}}
     * { "price" : 2500, "color" : "蓝色", "brand" : "小米", "sold_date" : "2017-02-12" }
     * <p>
     * 聚合操作  1 统计哪种颜色的电视销量最高
     * <p>
     * 默认的排序规则为按照doc_count的降序排序
     */

    private static final String TVS_INDEX = "tvs";
    private static final String TVS_TYPE = "sales";

    @Test
    public void aggQuery() {
        // 参一  自己字的别名 参二为字段  获取值时根据我们定义的别名获取
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("sale_count").field("color");
        SearchResponse response = transportClient.prepareSearch(TVS_INDEX).setTypes(TVS_TYPE).addAggregation(aggregationBuilder).get();
        Terms terms = response.getAggregations().get("sale_count");
        terms.getBuckets().forEach(bucket -> System.err.println(bucket.getKey() + ":" + bucket.getDocCount()));
    }

    @Test
    public void aggAvgQuery() {
        // 由上个步骤再进行一次对Price求平均值  输出有点尴尬~~~
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("sale_count").field("color")
                .subAggregation(AggregationBuilders.avg("avg_price").field("price"));
        SearchResponse response = transportClient.prepareSearch(TVS_INDEX).setTypes(TVS_TYPE).addAggregation(aggregationBuilder).get();
        Terms terms = response.getAggregations().get("sale_count");
        terms.getBuckets().forEach(bucket -> System.err.println(bucket));
    }

    /**
     * 多层分组嵌套
     * 从颜色到品牌进行下钻分析，每种颜色的平均价格，以及找到每种颜色每个品牌的平均价格
     * 比如说，现在红色的电视有4台，同时这4台电视中，有3台是属于长虹的，1台是属于小米的
     */
    @Test
    public void aggDownQuery() {
        //  多次分组下钻时要注意每一层级是嵌套到哪个层级下的
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("group_by_color").field("color")
                .subAggregation(AggregationBuilders.avg("color_avg_price").field("price"))
                .subAggregation(AggregationBuilders.terms("group_by_brand").field("brand")
                        .subAggregation(AggregationBuilders.avg("brand_avg_price").field("price"))
                );
        System.err.println(aggregationBuilder);
        SearchResponse response = transportClient.prepareSearch(TVS_INDEX).setTypes(TVS_TYPE).addAggregation(aggregationBuilder).get();
        response.getAggregations().asList().forEach(aggregation -> System.err.println(aggregation));
    }

    @Test
    public void aggMetric() {
        //  avg min max sum 聚合
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("colors").field("color")
                .subAggregation(AggregationBuilders.avg("avg_price").field("price"))
                .subAggregation(AggregationBuilders.min("min_price").field("price"))
                .subAggregation(AggregationBuilders.max("max_price").field("price"))
                .subAggregation(AggregationBuilders.sum("sum_price").field("price"));
        System.err.println(aggregationBuilder);
        SearchResponse response = transportClient.prepareSearch(TVS_INDEX).setTypes(TVS_TYPE).addAggregation(aggregationBuilder).get();
        response.getAggregations().asList().forEach(aggregation -> System.err.println(aggregation));
    }


    /**
     * histogram：类似于terms，也是进行bucket分组操作，接收一个field，按照这个field的值的各个范围区间，进行bucket分组操作
     * <p>
     * "histogram":
     * <p>
     * {
     * "field":"price",
     * "interval":2000
     * },
     * <p>
     * interval：2000，划分范围，0~2000，2000~4000，4000~6000，6000~8000，8000~10000，buckets
     */
    @Test
    public void histogram() {
        //  histogram 区间划分  注意区间范围为 [0,2000),[2000,4000)  左闭右开
        HistogramAggregationBuilder interval = AggregationBuilders
                .histogram("price")
                .field("price")
                .interval(2000)
                .subAggregation(AggregationBuilders.sum("revenue").field("price"));
        System.err.println(interval);
        SearchResponse response = transportClient
                .prepareSearch(TVS_INDEX)
                .setTypes(TVS_TYPE)
                .addAggregation(interval)
                .get();
        response.getAggregations().asList().forEach(aggregation -> System.err.println(aggregation));
    }

    /**
     * date histogram，按照我们指定的某个date类型的日期field，以及日期interval，按照一定的日期间隔，去划分bucket
     */
    @Test
    public void dateHistogram() {
        //
        DateHistogramAggregationBuilder dateHistogramAggregationBuilder = AggregationBuilders
                .dateHistogram("sales_count_by_date")
                .field("sold_date")
                // 日期间隔
                .dateHistogramInterval(DateHistogramInterval.MONTH)
                // min_doc_count：即使某个日期interval，2017-01-01~2017-01-31中，一条数据都没有，那么这个区间也是要返回的，不然默认是会过滤掉这个区间的
                .minDocCount(0)
                .format("yyyy-MM-dd")
                // 可以穿字符串和时间戳类型的日期  要查询的日期区间
                .extendedBounds(new ExtendedBounds("2016-01-01", "2017-12-31"));
        System.err.println(dateHistogramAggregationBuilder);
        SearchResponse response = transportClient
                .prepareSearch(TVS_INDEX)
                .setTypes(TVS_TYPE)
                .addAggregation(dateHistogramAggregationBuilder)
                .get();
        response.getAggregations().asList().forEach(aggregation -> System.err.println(aggregation));
    }

    /**
     * 下钻分析之统计每季度每个品牌的销售额
     */
    @Test
    public void practiceAggQuery() {
        //
        DateHistogramAggregationBuilder dateHistogramAggregationBuilder = AggregationBuilders
                .dateHistogram("sales_count_by_date")
                .field("sold_date")
                .dateHistogramInterval(DateHistogramInterval.QUARTER)
                .minDocCount(0)
                .format("yyyy-MM-dd")
                .extendedBounds(new ExtendedBounds("2016-01-01", "2017-12-31"))
                .subAggregation(AggregationBuilders.terms("qu_sales_count").field("brand")
                        // 注意此处的求和是每一季度每种品牌的求和
                        .subAggregation(AggregationBuilders.sum("total_sales_count").field("price")))
                // 此处的求和为每个季度销售的总和
                .subAggregation(AggregationBuilders.sum("quarter_total").field("price"));
        System.err.println(dateHistogramAggregationBuilder);
        SearchResponse response = transportClient
                .prepareSearch(TVS_INDEX)
                .setTypes(TVS_TYPE)
                .addAggregation(dateHistogramAggregationBuilder)
                .get();
        response.getAggregations().asList().forEach(aggregation -> System.err.println(aggregation));
    }

    /**
     * 搜索和分组的简单结合
     * select count(*)
     * from tvs.sales
     * where brand like "%长%"
     * group by price
     * <p>
     * es aggregation，scope，任何的聚合，都必须在搜索出来的结果数据中执行，搜索结果，就是聚合分析操作的scope
     */
    @Test
    public void searchAndAgg() {
        // 先查询brand为小米的商品在根据颜色进行分组
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("brand", "小米");
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("group_by_color").field("color");
        SearchResponse response = transportClient
                .prepareSearch(TVS_INDEX)
                .setTypes(TVS_TYPE)
                .addAggregation(aggregationBuilder)
                .setQuery(termQueryBuilder)
                .setSize(0)
                .get();
        Arrays.stream(response.getHits().getHits()).forEach(hit -> System.err.println(hit));
        response.getAggregations().asList().forEach(aggregation -> System.err.println(aggregation));
    }

    /**
     * global：就是global bucket，就是将所有数据纳入聚合的scope，而不管之前的query
     * GET /tvs/sales/_search
     * {
     * "size": 0,
     * "query": {
     * "term": {
     * "brand": {
     * "value": "长虹"
     * }
     * }
     * },
     * "aggs": {
     * "single_brand_avg_price": {
     * "avg": {
     * "field": "price"
     * }
     * },
     * "all": {
     * "global": {},
     * "aggs": {
     * "all_brand_avg_price": {
     * "avg": {
     * "field": "price"
     * }
     * }
     * }
     * }
     * }
     * }
     */
    @Test
    public void globalAndAgg() {
        // 先查询brand为小米的商品在根据颜色进行分组
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("brand", "长虹");
        AvgAggregationBuilder aggregationBuilder = AggregationBuilders.avg("single_brand_avg_price").field("price");
        GlobalAggregationBuilder globalAggregationBuilder = AggregationBuilders.global("all")
                .subAggregation(AggregationBuilders.avg("all_brand_avg_price").field("price"));

        SearchResponse response = transportClient
                .prepareSearch(TVS_INDEX)
                .setTypes(TVS_TYPE)
                .setQuery(termQueryBuilder)
                .addAggregation(aggregationBuilder)
                .addAggregation(globalAggregationBuilder)
                .setSize(0).get();
        Arrays.stream(response.getHits().getHits()).forEach(hit -> System.err.println(hit));
        response.getAggregations().asList().forEach(aggregation -> {
            System.err.println(aggregation);
            System.err.println(aggregation.getMetaData());
        });
    }

    /**
     * GET /tvs/sales/_search
     * {
     * "size": 0,
     * "query": {
     * "constant_score": {
     * "filter": {
     * "range": {
     * "price": {
     * "gte": 1200
     * }
     * }
     * }
     * }
     * },
     * "aggs": {
     * "avg_price": {
     * "avg": {
     * "field": "price"
     * }
     * }
     * }
     * }
     * <p>
     * 过滤+搜索
     */
    @Test
    public void searchFilterAgg() {
        // 6.6 中 filter 没了~~~
    }

    /**
     * agg 分组过滤
     * 只针对分组的过滤 不会对全部的数据进行过滤
     * <p>
     * 查询 品牌为长虹的手机最近1500天的平均价格
     */
    @Test
    public void searchAndAggFilter() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("brand", "长虹");
        FilterAggregationBuilder aggregationBuilder = AggregationBuilders
                .filter("recent_1500d", QueryBuilders.rangeQuery("sold_date").gte("now-1500d"))
                .subAggregation(AggregationBuilders.avg("recent_1500d_avg_price").field("price"));

        SearchResponse response = transportClient.prepareSearch(TVS_INDEX).setTypes(TVS_TYPE)
                .setQuery(termQueryBuilder)
                .addAggregation(aggregationBuilder)
                .setSize(0)
                .get();

        Arrays.stream(response.getHits().getHits()).forEach(hit -> System.err.println(hit));
        response.getAggregations().asList().forEach(aggregation -> {
            System.err.println(aggregation);
            System.err.println(aggregation.getMetaData());
        });

    }

    /**
     * 统计出来每个颜色的电视的销售额，需要按照销售额降序排序？？？？
     */
    @Test
    public void sortAgg() {
        TermsAggregationBuilder subAggregation = AggregationBuilders.terms("colors").field("color")
                // 参1为排序的字段或别名 参2表示是否为升序(ASC)
                .order(BucketOrder.aggregation("avg_price", false))
                .subAggregation(AggregationBuilders.avg("avg_price").field("price"));

        SearchResponse response = transportClient.prepareSearch(TVS_INDEX).setTypes(TVS_TYPE)
                .addAggregation(subAggregation)
                .setSize(0)
                .get();

        Arrays.stream(response.getHits().getHits()).forEach(hit -> System.err.println(hit));
        response.getAggregations().asList().forEach(aggregation -> {
            System.err.println(aggregation);
            System.err.println(aggregation.getMetaData());
        });

    }

    /**
     * 颜色+品牌下钻分析时按最深层metric进行排序
     * 现根据颜色分组在根据品牌分组 在品牌的分组根据每个品牌的平均价格进行降序排序
     */
    @Test
    public void sortAggAndOrder() {
        TermsAggregationBuilder subAggregation = AggregationBuilders.terms("colors").field("color")
                .subAggregation(AggregationBuilders.terms("brands").field("brand")
                        .order(BucketOrder.aggregation("avg_price", false))
                        .subAggregation(AggregationBuilders.avg("avg_price").field("price")));
        SearchResponse response = transportClient.prepareSearch(TVS_INDEX).setTypes(TVS_TYPE)
                .addAggregation(subAggregation)
                .setSize(0)
                .get();

        Arrays.stream(response.getHits().getHits()).forEach(hit -> System.err.println(hit));
        response.getAggregations().asList().forEach(aggregation -> {
            System.err.println(aggregation);
            System.err.println(aggregation.getMetaData());
        });

    }

    /**
     * 去重  根据 日期进行分组 然后根据品牌进行去重
     * 每个月销售的品牌数量 （PS__好像没有毛意义~~~）
     */
    @Test
    public void cardinalityOne() {
        DateHistogramAggregationBuilder dateHistogramAggregationBuilder = AggregationBuilders
                .dateHistogram("group_by_date")
                .field("sold_date")
                .dateHistogramInterval(DateHistogramInterval.MONTH)
                .minDocCount(0)
                .subAggregation(AggregationBuilders.cardinality("distinct_colors").field("brand"));

        SearchResponse response = transportClient.prepareSearch(TVS_INDEX).setTypes(TVS_TYPE).setSize(0)
                .addAggregation(dateHistogramAggregationBuilder).get();

        Arrays.stream(response.getHits().getHits()).forEach(hit -> System.err.println(hit));
        response.getAggregations().asList().forEach(aggregation -> {
            System.err.println(aggregation);
            System.err.println(aggregation.getMetaData());
        });

    }

    /**
     * precision_threshold优化准确率和内存开销
     */
    @Test
    public void cardinalityTwo() {
        CardinalityAggregationBuilder aggregationBuilder = AggregationBuilders.cardinality("distinct_colors")
                // 在多少个unique value以内，cardinality，几乎保证100%准确
                //cardinality算法，会占用precision_threshold * 8 byte 内存消耗，100 * 8 = 800个字节
                .field("brand").precisionThreshold(100);

        SearchResponse response = transportClient.prepareSearch(TVS_INDEX).setTypes(TVS_TYPE).setSize(0)
                .addAggregation(aggregationBuilder).get();

        Arrays.stream(response.getHits().getHits()).forEach(hit -> System.err.println(hit));
        response.getAggregations().asList().forEach(aggregation -> {
            System.err.println(aggregation);
            System.err.println(aggregation.getMetaData());
        });
    }


    private static final String WEBSITE_INDEX = "website";
    private static final String WEBSITE_TYPE = "logs";

    /**
     * percentiles百分比算法以及网站访问时延统计
     *
     * 需求：比如有一个网站，记录下了每次请求的访问的耗时，需要统计tp50，tp90，tp99
     *
     * tp50：50%的请求的耗时最长在多长时间
     * tp90：90%的请求的耗时最长在多长时间
     * tp99：99%的请求的耗时最长在多长时间
     *
     * PUT /website
     * {
     *     "mappings": {
     *         "logs": {
     *             "properties": {
     *                 "latency": {
     *                     "type": "long"
     *                 },
     *                 "province": {
     *                     "type": "keyword"
     *                 },
     *                 "timestamp": {
     *                     "type": "date"
     *                 }
     *             }
     *         }
     *     }
     * }
     *
     * POST /website/logs/_bulk
     * { "index": {}}
     * { "latency" : 105, "province" : "江苏", "timestamp" : "2016-10-28" }
     * { "index": {}}
     * { "latency" : 83, "province" : "江苏", "timestamp" : "2016-10-29" }
     * { "index": {}}
     * { "latency" : 92, "province" : "江苏", "timestamp" : "2016-10-29" }
     * { "index": {}}
     * { "latency" : 112, "province" : "江苏", "timestamp" : "2016-10-28" }
     * { "index": {}}
     * { "latency" : 68, "province" : "江苏", "timestamp" : "2016-10-28" }
     * { "index": {}}
     * { "latency" : 76, "province" : "江苏", "timestamp" : "2016-10-29" }
     * { "index": {}}
     * { "latency" : 101, "province" : "新疆", "timestamp" : "2016-10-28" }
     * { "index": {}}
     * { "latency" : 275, "province" : "新疆", "timestamp" : "2016-10-29" }
     * { "index": {}}
     * { "latency" : 166, "province" : "新疆", "timestamp" : "2016-10-29" }
     * { "index": {}}
     * { "latency" : 654, "province" : "新疆", "timestamp" : "2016-10-28" }
     * { "index": {}}
     * { "latency" : 389, "province" : "新疆", "timestamp" : "2016-10-28" }
     * { "index": {}}
     * { "latency" : 302, "province" : "新疆", "timestamp" : "2016-10-29" }
     */

    @Test
    public void pencentiles() {
        TermsAggregationBuilder builder = AggregationBuilders.terms("group_by_province").field("province")
                .subAggregation(AggregationBuilders.percentiles("latency_percentiles").field("latency")
                        .percentiles(50, 95, 99));
        AvgAggregationBuilder aggregationBuilder = AggregationBuilders.avg("latency_avg").field("latency");
        SearchResponse response = transportClient
                .prepareSearch(WEBSITE_INDEX).setTypes(WEBSITE_TYPE)
                .setSize(0)
                .addAggregation(builder)
                .addAggregation(aggregationBuilder)
                .get();
        Arrays.stream(response.getHits().getHits()).forEach(hit -> System.err.println(hit));
        response.getAggregations().asList().forEach(aggregation -> {
            System.err.println(aggregation);
            System.err.println(aggregation.getMetaData());
        });
    }

    /**
     * percentile ranks
     * compression  使用多少节点去计算 设定的值越大越精确 耗费的
     * 内存也越多，默认为100
     *
     * 限制节点数量最多 compression * 20 = 2000个node去计算
     *
     *
     */
    @Test
    public void pencentilesRank() {
        TermsAggregationBuilder builder = AggregationBuilders.terms("group_by_province").field("province")
                .subAggregation(AggregationBuilders.percentileRanks("latency_percentile_ranks",new double[]{200,1000})
                        .field("latency").compression(100));
        AvgAggregationBuilder aggregationBuilder = AggregationBuilders.avg("latency_avg").field("latency");
        SearchResponse response = transportClient
                .prepareSearch(WEBSITE_INDEX).setTypes(WEBSITE_TYPE)
                .setSize(0)
                .addAggregation(builder)
                .addAggregation(aggregationBuilder)
                .get();
        Arrays.stream(response.getHits().getHits()).forEach(hit -> System.err.println(hit));
        response.getAggregations().asList().forEach(aggregation -> {
            System.err.println(aggregation);
            System.err.println(aggregation.getMetaData());
        });
    }


    private void printIn(SearchResponse response) {
        System.err.println("耗时" + response.getTook().getMillis());
        Arrays.stream(response.getHits().getHits()).forEach(hit -> {
            System.err.println(hit.getId());
            System.err.println(hit.getScore());
            System.err.println(hit.getSourceAsMap().get(TITLE_FIELD));
            System.err.println(hit.getSourceAsMap().get(CONTENT_FIELD));
        });
    }

    private static void closeClient() {
        transportClient.close();
    }
}
