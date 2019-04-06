//package com.Ihnsod.spring.common.mybatis.handler;
//
//
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Map;
//
//public class MapStringTypeHandler extends BaseTypeHandler<Map> {
//
//    @Override
//    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Map t, JdbcType jdbcType) throws SQLException {
//        preparedStatement.setObject(i, JsonUtils.objectToJson(t));
//    }
//
//    @Override
//    public Map getNullableResult(ResultSet resultSet, String s) throws SQLException {
//        return this.getObject(resultSet.getString(s));
//    }
//
//    @Override
//    public Map getNullableResult(ResultSet resultSet, int i) throws SQLException {
//        return this.getObject(resultSet.getString(i));
//    }
//
//    @Override
//    public Map getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
//        return this.getObject(callableStatement.getString(i));
//    }
//
//    private Map getObject(String columnValue) {
//        if (columnValue == null)
//            return null;
//        return JsonUtils.jsonToPojo(columnValue, Map.class);
//    }
//}
