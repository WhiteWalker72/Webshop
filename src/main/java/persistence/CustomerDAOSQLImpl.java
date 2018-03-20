package persistence;

import domain.account.Customer;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CustomerDAOSQLImpl extends DAOSQLImpl<Customer> {

    private Integer lastId = null;

    CustomerDAOSQLImpl(SQLDatabase database) {
        super(database, "klant", "id");
    }

    @Override
    public String getNextUniqueId() {
        if (lastId == null) {
            lastId = findById("" + 1) == null ? 0 : findAll().stream().map(Customer::getCustomerID).reduce(Integer.MIN_VALUE, Integer::max);
        }
        lastId += 1;
        return lastId + "";
    }

    @Override
    Customer getObjectFromResultSet(ResultSet resultSet) {
        try {
            return new Customer(
                    resultSet.getInt("id")
                    , resultSet.getString("naam")
                    , resultSet.getInt("adresklant")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(Customer dto) throws ObjectAlreadyExistsException {
        if (findById(dto.getCustomerID() + "") != null) {
            throw new ObjectAlreadyExistsException(tableName, "the database");
        }

        database.insertInto(tableName, 3, statement -> {
            try {
                statement.setNull(1, Types.INTEGER);
                statement.setString(2, dto.getName());
                statement.setInt(3, dto.getAddressID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void update(Customer dto) throws ObjectNotFoundException {

    }
}
