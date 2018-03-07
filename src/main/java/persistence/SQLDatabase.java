package persistence;

import utils.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class SQLDatabase {

    private final String address;
    private final int port;
    private final String databaseName;
    private final String username;
    private final String password;
    private Connection connection;

    SQLDatabase(String address, int port, String databaseName, String username, String password) {
        this.address = address;
        this.port = port;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
    }

    abstract Connection getConnection(String address, int port, String databaseName, String username, String password);

    private void refreshConnection() {
        closeConnection();
        connection = getConnection(address, port, databaseName, username, password);
    }

    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean insertInto(String tableName, String... values) {
        try {
            executeDBQuery("INSERT INTO " + tableName + " VALUES(" + formatToQuery(values) + ")");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(String tableName, List<Pair<String, String>> values, String idColumn, String id) {
        try {
            executeDBQuery("UPDATE " + tableName + " SET " + formatUpdateValues(values) + " WHERE " + idColumn + " = " + id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String tableName,  String idColumn, String id) {
        try {
            executeDBQuery("DELETE FROM " + tableName + " WHERE " + idColumn + " = " + id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    ResultSet executeDBQuery(String query) throws SQLException {
        refreshConnection();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(query);
        statement.close();
        closeConnection();
        return set;
    }

    private String formatToQuery(String[] values) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.length - 1; i++) {
            appendBuilderValue(builder, values[i]);
            builder.append(", ");
        }
        appendBuilderValue(builder, values[values.length - 1]);
        return builder.toString();
    }

    private void appendBuilderValue(StringBuilder builder, String value) {
        if (value == null || value.equalsIgnoreCase("null")) {
            builder.append(value);
        } else {
            builder.append("'").append(value).append("'");
        }
    }

    private String formatUpdateValues(List<Pair<String, String>> values) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size() - 1; i++) {
            appendBuilderUpdateValue(builder, values.get(i));
            builder.append(", ");
        }
        appendBuilderUpdateValue(builder, values.get(values.size() - 1));
        return builder.toString();
    }

    private void appendBuilderUpdateValue(StringBuilder builder, Pair<String, String> valuePair) {
        String value = valuePair.getRight();
        if (value == null || value.equalsIgnoreCase("null")) {
            builder.append(valuePair.getLeft()).append(" = ").append(value);
        } else {
            builder.append(valuePair.getLeft()).append(" = '").append(valuePair.getRight()).append("'");
        }
    }

}
