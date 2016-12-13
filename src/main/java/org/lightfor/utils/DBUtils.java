package org.lightfor.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库工具类
 * Created by Light on 2016/8/13.
 */
public enum DBUtils {
    INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);
    private static Connection conn=null;

    public static void init(String driver, String url, String user, String password){
        try {
            Class.forName(driver);
            conn =  DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            logger.error("加载驱动异常", e);
        }
    }

    public static void destroy(){
        try {
            if(conn !=  null){
                conn.close();
            }
        } catch (Exception e) {
            logger.error("清理异常",e);
        }
    }

    public static Connection getConnection(String driver, String url, String user, String password) {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            logger.error("获取连接异常", e);
        }
        return null;
    }

    public static Map<String,String> selectOne(String sql){
        Map<String, String> result = new HashMap<>();
        try {
            PreparedStatement preparedStatement = getPreparedStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                ResultSetMetaData metaData = rs.getMetaData();
                for(int i = metaData.getColumnCount(); i > 0 ; i--){
                    result.put(metaData.getColumnName(i), rs.getString(i));
                }
            }
            rs.close();
            preparedStatement.close();
        } catch (Exception e) {
            logger.error("无参查询单条异常", e);
        }
        return result;
    }

    public static Map<String,String> selectOne(String sql, Map<String,String> paramMap){
        Map<String, String> result = new HashMap<>();
        try {
            PreparedStatement preparedStatement = getPreparedStatement(sql, paramMap);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                ResultSetMetaData metaData = rs.getMetaData();
                for(int i = metaData.getColumnCount(); i > 0 ; i--){
                    result.put(metaData.getColumnName(i), rs.getString(i));
                }
            }
            rs.close();
            preparedStatement.close();
        } catch (Exception e) {
            logger.error("有参查询单条异常", e);
        }
        return result;
    }

    public static Map<String,String> selectOne(String sql, Map<String,String> paramMap, Connection conn){
        Map<String, String> result = new HashMap<>();
        try {
            List<String> params = RegexUtils.returnAllMatch(sql, "#\\{(.*?)\\}");
            sql = sql.replaceAll("#\\{(.*?)\\}", "?");
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            int i = 0;
            for(String param : params){
                preparedStatement.setString(++i,paramMap.get(param));
            }
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                ResultSetMetaData metaData = rs.getMetaData();
                for(i = metaData.getColumnCount(); i > 0 ; i--){
                    result.put(metaData.getColumnName(i), rs.getString(i));
                }
            }
            rs.close();
            preparedStatement.close();
        } catch (Exception e) {
            logger.error("有参查询单条异常", e);
        }
        return result;
    }

    public static List<Map<String,String>> selectAll(String sql){
        List<Map<String, String>> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getPreparedStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            getResult(result, rs);
            rs.close();
            preparedStatement.close();
        } catch (Exception e) {
            logger.error("有参查询全部异常", e);
        }
        return result;
    }

    private static void getResult(List<Map<String, String>> result, ResultSet rs) throws SQLException {
        while(rs.next()){
            Map<String, String> ele = new HashMap<>();
            ResultSetMetaData metaData = rs.getMetaData();
            for(int i = metaData.getColumnCount(); i > 0 ; i--){
                ele.put(metaData.getColumnName(i).toUpperCase(), rs.getString(i));
            }
            result.add(ele);
        }
    }

    public static List<Map<String,String>> selectAll(String sql, Map<String,String> paramMap){
        List<Map<String, String>> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getPreparedStatement(sql, paramMap);
            ResultSet rs = preparedStatement.executeQuery();
            getResult(result, rs);
            rs.close();
            preparedStatement.close();
        } catch (Exception e) {
            logger.error("有参查询全部异常", e);
        }
        return result;
    }


    public static List<Map<String,String>> selectAll(String sql, Map<String,String> paramMap, Connection conn){
        List<Map<String, String>> result = new ArrayList<>();
        try {
            List<String> params = RegexUtils.returnAllMatch(sql, "#\\{(.*?)\\}");
            sql = sql.replaceAll("#\\{(.*?)\\}", "?");
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            int i = 0;
            for(String param : params){
                preparedStatement.setString(++i,paramMap.get(param));
            }
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Map<String, String> ele = new HashMap<>();
                ResultSetMetaData metaData = rs.getMetaData();
                for(i = metaData.getColumnCount(); i > 0 ; i--){
                    ele.put(metaData.getColumnName(i), rs.getString(i));
                }
                result.add(ele);
            }
            rs.close();
            preparedStatement.close();
        } catch (Exception e) {
            logger.error("有参查询全部异常", e);
        }
        return result;
    }

    private static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    private static PreparedStatement getPreparedStatement(String sql, Map<String, String> paramMap) throws SQLException {
        List<String> params = RegexUtils.returnAllMatch(sql, "#\\{(.*?)\\}");
        sql = sql.replaceAll("#\\{(.*?)\\}", "?");
        int i = 0;
        PreparedStatement preparedStatement = getPreparedStatement(sql);
        for(String param : params){
            preparedStatement.setString(++i,paramMap.get(param));
        }
        return preparedStatement;
    }

    public static int insert(String sql){
        try{
            PreparedStatement preparedStatement = getPreparedStatement(sql);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            return i;
        } catch(Exception e){
            logger.error("无参插入异常",e);
        }
        return -1;
    }

    public static int insert(String sql, Map<String, String> paramMap){
        try{
            PreparedStatement preparedStatement = getPreparedStatement(sql, paramMap);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            return i;
        } catch(Exception e){
            logger.error("有参插入异常",e);
        }
        return -1;
    }

    public static int update(String sql){
        try{
            PreparedStatement preparedStatement = getPreparedStatement(sql);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            return i;
        } catch(Exception e){
            logger.error("无参更新异常",e);
        }
        return -1;
    }

    public static int update(String sql, Map<String,String> paramMap){
        try{
            PreparedStatement preparedStatement = getPreparedStatement(sql, paramMap);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            return i;
        } catch(Exception e){
            logger.error("有参更新异常",e);
        }
        return -1;
    }

    public static int delete(String sql){
        try{
            PreparedStatement preparedStatement = getPreparedStatement(sql);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            return i;
        } catch(Exception e){
            logger.error("无参删除异常",e);
        }
        return -1;
    }

    public static int delete(String sql, Map<String,String> paramMap){
        try{
            PreparedStatement preparedStatement = getPreparedStatement(sql, paramMap);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            return i;
        } catch(Exception e){
            logger.error("有参删除异常",e);
        }
        return -1;
    }

    public static void setAutoCommit(boolean autoCommit){
        try {
            conn.setAutoCommit(autoCommit);
        } catch (Exception e) {
            logger.error("设置自动提交异常",e);
        }
    }

    public static void commit(){
        try {
            conn.commit();
        } catch (Exception e) {
            logger.error("提交事务异常",e);
        }
    }

    public static void rollback(){
        try {
            conn.rollback();
        } catch (Exception e) {
            logger.error("回滚事务异常",e);
        }
    }
}
