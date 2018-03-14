package persistence;

import dto.ProductDTO;
import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAOSQLImpl extends DAOSQLImpl<ProductDTO> {

    ProductDAOSQLImpl(SQLDatabase database) {
        super(database, "product", "Id");
    }

    @Override
    ProductDTO getObjectFromResultSet(ResultSet resultSet) {
        try {
            ProductDTO productDTO = new ProductDTO(
                    resultSet.getInt("id")
                    , resultSet.getString("naam")
                    , resultSet.getString("omschrijving"),
                    resultSet.getString("afbeelding"));

            String priceCol = "prijs";
            if (SQLUtils.hasColumn(resultSet, priceCol)) {
                productDTO.withPrice(resultSet.getInt(priceCol));
            }

            return productDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(ProductDTO dto) throws ObjectAlreadyExistsException {
        if (findById(dto.getId() + "") != null) {
            throw new ObjectAlreadyExistsException(tableName);
        }

        database.insertInto(tableName, 5, statement -> {
            try {
                statement.setInt(1, dto.getId());
                statement.setString(2, dto.getName());
                statement.setDouble(3, dto.getPrice());
                statement.setString(4, dto.getDescription());
                statement.setString(5, dto.getImage());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void update(ProductDTO dto) throws ObjectNotFoundException {
        if (findById(dto.getId() + "") == null) {
            throw new ObjectNotFoundException(tableName, "updating");
        }
        PreparedStatement statement = database.getPreparedStatement("UPDATE " + tableName + " SET naam = ?, prijs = ?" +
                ", omschrijving = ?, afbeelding = ? WHERE id = ?");
        try {
            statement.setString(1, dto.getName());
            statement.setDouble(2, dto.getPrice());
            statement.setString(3, dto.getDescription());
            statement.setString(4, dto.getImage());
            statement.setInt(5, dto.getId());

            statement.executeUpdate();
            database.endStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
