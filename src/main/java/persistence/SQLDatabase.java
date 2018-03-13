package persistence;

import utils.MathUtils;

import java.sql.*;

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

    public void refreshConnection() {
        closeConnection();
        connection = getConnection(address, port, databaseName, username, password);
    }

    public boolean insertInto(String tableName, int amountOfValues, FillStatementStrategy fillStrategy) {
        if (amountOfValues <= 0) {
            return false;
        }
        StringBuilder questionMarks = new StringBuilder("");
        for (int i = 0; i < amountOfValues - 1; i++) {
            questionMarks.append("?, ");
        }
        questionMarks.append("?");

        refreshConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + tableName + " VALUES(" + questionMarks.toString() + ")");
            fillStrategy.fillStatement(statement);
            statement.executeUpdate();
            endStatement(statement);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteFrom(String tableName, String idColumn, FillStatementStrategy fillStrategy) throws ObjectNotFoundException {
        refreshConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + tableName + " WHERE " + idColumn + " = ?");
            fillStrategy.fillStatement(statement);
            statement.executeUpdate();
            endStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ObjectNotFoundException(tableName, "deleting");
        }
    }

    public ResultSet selectAll(String tableName) {
        return executeDBQuery("SELECT * FROM " + tableName);
    }

    public ResultSet selectAllById(String tableName, String idColumn, String id) {
        refreshConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE " + idColumn + " = ?");
            if (MathUtils.isInt(id)) {
                statement.setInt(1, Integer.parseInt(id));
            } else {
                statement.setString(1, id);
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ResultSet executeDBQuery(String query) {
        refreshConnection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    PreparedStatement getPreparedStatement(String sql) {
        refreshConnection();
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    void endStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

}
