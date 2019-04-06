package com.Ihnsod.spring.common.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerArrayTypeHandler extends BaseTypeHandler<Integer[]> {

    @Override
    public Integer[] getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return getArray(rs.getString(columnName));
    }

    @Override
    public Integer[] getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        return this.getArray(rs.getString(columnIndex));
    }

    @Override
    public Integer[] getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return this.getArray(cs.getString(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    Integer[] parameter, JdbcType jdbcType) throws SQLException {
        //由于BaseTypeHandler中已经把parameter为null的情况做了处理，所以这里我们就不用再判断parameter是否为空了，直接用就可以了
        if (null != parameter && parameter.length > 0) {
            StringBuffer result = new StringBuffer();
            for (Integer value : parameter)
                result.append(value).append(",");
            result.deleteCharAt(result.length() - 1);
            ps.setString(i, result.toString());
        } else {
            ps.setString(i, null);
        }
    }

    private Integer[] getArray(String columnValue) {
        if (columnValue != null && !"".equals(columnValue.trim())) {
            String[] split = columnValue.split(",");
            if (null != split && split.length > 0) {
                Integer[] result = new Integer[split.length];
                for (int i = 0; i < split.length; i++) {
                    result[i] = Integer.valueOf(split[i]);
                }
                return result;
            }
        }
        return null;
    }
}