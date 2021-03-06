package persistence;

import domain.account.Account;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;

public class AccountDAOSQLImpl extends DAOSQLImpl<Account> {

    private Integer lastId = null;

    AccountDAOSQLImpl(SQLDatabase database) {
        super(database, "account", "gebruikersnaam");
    }

    @Override
    public String getNextUniqueId() {
        PreparedStatement statement = database.getPreparedStatement("SELECT MAX(id) FROM " + tableName);
        try {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                lastId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (lastId == null) {
            lastId = 1;
        }

        return (1 + lastId) + "";
    }

    @Override
    Account getObjectFromResultSet(ResultSet resultSet) {
        try {
            return new Account(
                    resultSet.getInt("id")
                    , resultSet.getDate("opendatum")
                    , resultSet.getInt("isactief")
                    , resultSet.getString("gebruikersnaam")
                    , resultSet.getString("wachtwoord")
                    , resultSet.getInt("klant")
                    , resultSet.getString("salt")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(Account dto) throws ObjectAlreadyExistsException {
        if (findById(dto.getAccountID() + "") != null) {
            throw new ObjectAlreadyExistsException(tableName, "the database");
        }

        database.insertInto(tableName, 7, statement -> {
            try {
                statement.setNull(1, Types.INTEGER);
                statement.setDate(2, new Date(dto.getStartDate().getTime()));
                statement.setInt(3, dto.getIsActive());
                statement.setString(4, dto.getUsername());
                statement.setString(5, dto.getPassword());
                statement.setString(6, dto.getSalt());
                statement.setInt(7, dto.getCustomerId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void update(Account dto) {

    }
}
