package com.Ihnsod.spring.common.handler;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.HashMap;
import java.util.Map;

public class MapResultHandler implements ResultHandler {

    private Map mappedResults = new HashMap();

    @Override
    public void handleResult(ResultContext resultContext) {
        Map map = (Map) resultContext.getResultObject();
        mappedResults.put(map.get("key"), map.get("value"));  // xml 配置里面的property的值，对应的列
    }

    public Map getMappedResult() {
        return mappedResults;
    }

    // see note at bottom of answer as to why I include this method
    private Object getFromMap(Map<String, Object> map, String key) {
        if (map.containsKey(key.toLowerCase())) {
            return map.get(key.toLowerCase());
        } else {
            return map.get(key.toUpperCase());
        }
    }
}
