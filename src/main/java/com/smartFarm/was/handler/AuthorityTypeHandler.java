//package com.smartFarm.was.handler;
//
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class AuthorityTypeHandler extends BaseTypeHandler<Authority> {
//
//    @Override
//    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Authority authority, JdbcType jdbcType) throws SQLException {
//        preparedStatement.setString(i, authority.name());
//    }
//
//    @Override
//    public Authority getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
//        return Authority.valueOf(resultSet.getString(columnName));
//    }
//
//    @Override
//    public Authority getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
//        return Authority.valueOf(resultSet.getString(columnIndex));
//    }
//
//    @Override
//    public Authority getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
//        return Authority.valueOf(callableStatement.getString(columnIndex));
//    }
//}
