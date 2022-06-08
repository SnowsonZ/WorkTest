package com.learn.snnipet.db.clickhouse;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.learn.snnipet.util.ParameterTool;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.clickhouse.ClickHouseStatement;
import ru.yandex.clickhouse.response.ClickHouseResponse;

/**
 * The type Click house test.
 *
 * @author Snowson
 * @since 2019 /7/8 14:25
 */
@Slf4j
public class ClickHouseTest {

    private static final String DRIVER = "ru.yandex.clickhouse.ClickHouseDriver";
    private static String URL = "jdbc:clickhouse://%s:%s/%s?user=%s";
    private static String IP = "127.0.0.1";
    private static String PORT = "8123";
    private static final String USER = "default";
    private static final String PASSWORD = "";
    private Connection conn;
    private static String SQL_SCRIPT_PATH = "sql/update.sql";
    private static String DEFAULT_DATABASE = "default";

    private static final String KEY_IP = "ip";
    private static final String KEY_PORT = "port";
    private static final String KEY_DB = "database";
    private static final String KEY_USER = "user";
    private static final String KEY_PWD = "password";
    private static final String KEY_SCRIPT = "script";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     * @throws IOException            the io exception
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        prepareArgs(args);
        ClickHouseTest clickHouseTest = new ClickHouseTest();
        clickHouseTest.batchUpdate(clickHouseTest.getSqlContent(SQL_SCRIPT_PATH));
        log.info("clickHouseTest success");
    }

    private static void prepareArgs(String[] args) {
        ParameterTool params = ParameterTool.fromArgs(args);
        String pwd = params.get(KEY_PWD, PASSWORD);
        URL = String.format(URL, params.get(KEY_IP, IP), params.get(KEY_PORT, PORT),
                params.get(KEY_DB, DEFAULT_DATABASE), params.get(KEY_USER, USER), Strings.isNullOrEmpty(pwd) ? "" : "&password=" + pwd);
        SQL_SCRIPT_PATH = params.get(KEY_SCRIPT, SQL_SCRIPT_PATH);
    }

    private List<String> getSqlContent(String path) throws IOException {
        if (Strings.isNullOrEmpty(path)) {
            throw new IllegalArgumentException("update script path is null...");
        }
        File file = new File(path);
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] b = new byte[bis.available()];
            bis.read(b);
            return Splitter.on(';').splitToList(new String(b, Charsets.UTF_8).replaceAll("[\r\n]", "")).stream()
                    .filter(item -> !Strings.isNullOrEmpty(item.trim()))
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private void initDriver() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        conn = DriverManager.getConnection(URL);
        conn.setAutoCommit(true);
    }

    private void batchUpdate(List<String> sql) throws SQLException, ClassNotFoundException {
        if (conn == null) {
            initDriver();
        }
        try (Statement statement = conn.createStatement()) {
            for (String s : sql) {
                statement.execute(s);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    /**
     * Query json.
     * json方式查询
     *
     * @param sql the sql
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public void queryJson(String sql) throws SQLException, ClassNotFoundException {
        if (conn == null) {
            initDriver();
        }
        try {
            ClickHouseStatement ps = (ClickHouseStatement) conn.prepareStatement(sql);
            ClickHouseResponse response = ps.executeQueryClickhouseResponse(sql);
            log.info("{}", JSON.toJSONString(response));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
