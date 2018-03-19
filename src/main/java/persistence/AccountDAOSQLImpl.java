package persistence;

import domain.account.Account;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AccountDAOSQLImpl extends DAOSQLImpl<Account> {

    AccountDAOSQLImpl(SQLDatabase database) {
        super(database, "account", "id");
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
                statement.setNull(7, Types.INTEGER);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void update(Account dto) throws ObjectNotFoundException {

    }
}
