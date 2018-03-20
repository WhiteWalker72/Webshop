package persistence;

import domain.account.Customer;
import dto.AddressDTO;
import dto.CategoryDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

import java.sql.*;
import java.sql.ResultSet;

public class CustomerDAOSQLImpl extends DAOSQLImpl<Customer> {

    private Integer lastId = null;

    CustomerDAOSQLImpl(SQLDatabase database) {
        super(database, "klant", "id");
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
