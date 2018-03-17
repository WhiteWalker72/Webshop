package persistence;

import exceptions.ObjectNotFoundException;
import utils.MathUtils;

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

        try {
            if (resultSet != null && resultSet.next()) {
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    list.add(getObjectFromResultSet(resultSet));
                }
                if (!resultSet.isClosed()) {
                    database.endStatement(resultSet.getStatement());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public T findById(String identifier) {
        if (identifier == null || identifier.equals("null")) {
            return null;
        }
        ResultSet resultSet = database.selectAllById(tableName, idColumn, identifier);
        try {
            if (resultSet != null && resultSet.next()) {
                resultSet.beforeFirst();

                resultSet.next();
                T object = getObjectFromResultSet(resultSet);

                if (!resultSet.isClosed()) {
                    database.endStatement(resultSet.getStatement());
                }
                return object;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(String identifier) throws ObjectNotFoundException {
        if (findById(identifier) == null) {
            throw new ObjectNotFoundException("object", "deleting", identifier);
        }

        database.deleteFrom(tableName, idColumn, statement -> {
            try {
                if (MathUtils.isInt(identifier)) {
                    statement.setInt(1, Integer.parseInt(identifier));
                } else {
                    statement.setString(1, identifier);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    abstract T getObjectFromResultSet(ResultSet resultSet);

}
