package persistence;

import dto.AddressDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAOSQLImpl extends DAOSQLImpl<AddressDTO> {

    AddressDAOSQLImpl(SQLDatabase database) {
        super(database, "adres", "id");
    }

    @Override
    AddressDTO getObjectFromResultSet(ResultSet resultSet) {
        try {
            return new AddressDTO(
                    resultSet.getInt("id")
                    , resultSet.getString("straat")
                    , resultSet.getInt("nummer")
                    , resultSet.getString("postcode")
                    , resultSet.getString("plaats")
                    , resultSet.getString("land")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(AddressDTO dto) throws ObjectAlreadyExistsException {
        if (findById(dto.getId() + "") != null) {
            throw new ObjectAlreadyExistsException(tableName, "the database");
        }

        database.insertInto(tableName, 6, statement -> {
            try {
                statement.setInt(1, dto.getId());
                statement.setString(2, dto.getStreet());
                statement.setInt(3, dto.getNumber());
                statement.setString(4, dto.getPostalCode());
                statement.setString(5, dto.getCity());
                statement.setString(6, dto.getCountry());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void update(AddressDTO dto) throws ObjectNotFoundException {
        if (findById(dto.getId() + "") == null) {
            throw new ObjectNotFoundException(tableName, "updating");
        }
        PreparedStatement statement = database.getPreparedStatement("UPDATE " + tableName + " SET straat = ?" +
                ", nummer = ?, postcode = ?, plaats = ?, land = ? WHERE id = ?");

        try {
            statement.setString(1, dto.getStreet());
            statement.setInt(2, dto.getNumber());
            statement.setString(3, dto.getPostalCode());
            statement.setString(4, dto.getCity());
            statement.setString(5, dto.getCountry());
            statement.setInt(6, dto.getId());

            statement.executeUpdate();
            database.endStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
