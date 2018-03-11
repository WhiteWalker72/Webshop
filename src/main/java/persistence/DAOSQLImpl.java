package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOSQLImpl<T> implements DAO<T> {

    protected final SQLDatabase database;
    protected final String tableName;
    protected final String idColumn;

    DAOSQLImpl(SQLDatabase database, String tableName, String idColumn) {
        this.database = database;
        this.tableName = tableName;
        this.idColumn = idColumn;
    }

    @Override
    public List<T> findAll() {
        ResultSet resultSet = database.selectAll(tableName);
        List<T> list = new ArrayList<>();

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    list.add(getObjectFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public T findById(String identifier) {
        if (identifier == null || identifier.equals("null")) {
            return null;
        }
        ResultSet resultSet = database.selectAllByID(tableName, idColumn, identifier);

        if (resultSet != null) {
            try {
                resultSet.next();
                return getObjectFromResultSet(resultSet);
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    //TODO testing: Not sure if this works if the id isn't a string
    @Override
    public boolean delete(String identifier) {
        return database.deleteFrom(tableName, idColumn, statement -> {
            try {
                statement.setString(1, identifier);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    abstract T getObjectFromResultSet(ResultSet resultSet);

}
