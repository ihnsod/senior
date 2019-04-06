//package com.Ihnsod.spring.common.mybatis.handler;
//
//import com.idb.raven.common.utils.JsonUtils;
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//public class StringListTypeHandler extends BaseTypeHandler<List> {
//
//    @Override
//    public List getNullableResult(ResultSet rs, String columnName)
//            throws SQLException {
//        return getList(rs.getString(columnName));
//    }
//
//    @Override
//    public List getNullableResult(ResultSet rs, int columnIndex)
//            throws SQLException {
//        return getList(rs.getString(columnIndex));
//    }
//
//    @Override
//    public List getNullableResult(CallableStatement cs, int columnIndex)
//            throws SQLException {
//        return getList(cs.getString(columnIndex));
//    }
//
//    @Override
//    public void setNonNullParameter(PreparedStatement ps, int i,
//                                    List parameter, JdbcType jdbcType) throws SQLException {
//        //由于BaseTypeHandler中已经把parameter为null的情况做了处理，所以这里我们就不用再判断parameter是否为空了，直接用就可以了
//        String s = JsonUtils.objectToJson(parameter);
//        ps.setString(i, s);
//    }
//
//    private List getList(String columnValue) {
//        if (columnValue == null)
//            return null;
//        return JsonUtils.jsonToList(columnValue, Object.class);
//    }
//}