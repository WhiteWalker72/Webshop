package persistence;

import dto.OfferDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;
import utils.DateUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferDAOSQLImpl extends DAOSQLImpl<OfferDTO> {

    private Integer lastId = null;

    OfferDAOSQLImpl(SQLDatabase database) {
        super(database, "aanbieding", "id");
    }

    @Override
    public String getNextUniqueId() {
        if (lastId == null) {
            lastId = findAll().stream().mapToInt(OfferDTO::getId).max().orElse(1) + 1;
        }
        lastId += 1;
        return lastId + "";
    }

    @Override
    OfferDTO getObjectFromResultSet(ResultSet resultSet) {
        try {
            return new OfferDTO(
                    resultSet.getInt("id")
                    , DateUtils.fromSQLToJavaDate(resultSet.getDate("vandatum"))
                    , DateUtils.fromSQLToJavaDate(resultSet.getDate("totdatum"))
                    , resultSet.getString("reclametekst")
                    , resultSet.getDouble("aanbiedingprijs")
                    , resultSet.getInt("product")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(OfferDTO dto) throws ObjectAlreadyExistsException {
        if (findById(dto.getId() + "") != null) {
            throw new ObjectAlreadyExistsException(tableName, "the database");
        }

        database.insertInto(tableName, 6, statement -> {
            try {
                statement.setInt(1, dto.getId());
                statement.setDate(2, DateUtils.fromJavaToSQLDate(dto.getStartDate()));
                statement.setDate(3, DateUtils.fromJavaToSQLDate(dto.getEndDate()));
                statement.setString(4, dto.getText());
                statement.setDouble(5, dto.getOfferPrice());
                statement.setInt(6, dto.getProductId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void update(OfferDTO dto) throws ObjectNotFoundException {
        if (findById(dto.getId() + "") == null) {
            throw new ObjectNotFoundException(tableName, "updating");
        }

        PreparedStatement statement = database.getPreparedStatement("UPDATE " + tableName + " SET vandatum = ?" +
                ", totdatum = ?, reclametekst = ?, aanbiedingprijs = ? WHERE id = ?");

        try {
            statement.setDate(1, DateUtils.fromJavaToSQLDate(dto.getStartDate()));
            statement.setDate(2,  DateUtils.fromJavaToSQLDate(dto.getEndDate()));
            statement.setString(3, dto.getText());
            statement.setDouble(4, dto.getOfferPrice());

            statement.executeUpdate();
            database.endStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
