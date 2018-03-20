package persistence;

import dto.CategoryDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOSQLImpl extends DAOSQLImpl<CategoryDTO> {

    private Integer lastId = null;

    CategoryDAOSQLImpl(SQLDatabase database) {
        super(database, "categorie", "id");
    }

    @Override
    public String getNextUniqueId() {
        if (lastId == null) {
            lastId = findById("" + 1) == null ? 0 : findAll().stream().map(CategoryDTO::getId).reduce(Integer.MIN_VALUE, Integer::max);
        }
        lastId += 1;
        return lastId + "";
    }

    @Override
    CategoryDTO getObjectFromResultSet(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");

            return new CategoryDTO(
                    id
                    , resultSet.getString("naam")
                    , resultSet.getString("omschrijving")
                    , resultSet.getString("afbeelding")
                    , getProductIdList(id)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Integer> getProductIdList(int categoryId) {
        List<Integer> idList = new ArrayList<>();
        ResultSet resultSet = database.selectAllById("product_categorie", "categorie_id", categoryId + "");

        try {
            if (resultSet != null && resultSet.next()) {
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    idList.add(resultSet.getInt("product_id"));
                }
                if (!resultSet.isClosed()) {
                    database.endStatement(resultSet.getStatement());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idList;
    }

    @Override
    public void insert(CategoryDTO dto) throws ObjectAlreadyExistsException {
        if (findById(dto.getId() + "") != null) {
            throw new ObjectAlreadyExistsException(tableName, "the database");
        }

        database.insertInto(tableName, 4, statement -> {
            try {
                statement.setInt(1, dto.getId());
                statement.setString(2, dto.getName());
                statement.setString(3, dto.getDescription());
                statement.setString(4, dto.getImage());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        insertProductIdList(dto);
    }

    @Override
    public void update(CategoryDTO dto) throws ObjectNotFoundException {
        if (findById(dto.getId() + "") == null) {
            throw new ObjectNotFoundException(tableName, "updating");
        }

        PreparedStatement statement = database.getPreparedStatement("UPDATE " + tableName + " SET naam = ?" +
                ", omschrijving = ?, afbeelding = ? WHERE id = ?");

        try {
            statement.setString(1, dto.getName());
            statement.setString(2, dto.getDescription());
            statement.setString(3, dto.getImage());
            statement.setInt(4, dto.getId());

            statement.executeUpdate();
            database.endStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertProductIdList(dto);
    }

    private void insertProductIdList(CategoryDTO dto) {
        if (!dto.getProductIdList().isEmpty()) {
            String table = "product_categorie";

            try {
                database.deleteFrom(table, "categorie_id", statement -> {
                    try {
                        statement.setInt(1, dto.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            } catch (ObjectNotFoundException e) {
                // The table does not have to contain a category
            }

            for (Integer prodId : dto.getProductIdList()) {
                database.insertInto(table, 2, statement -> {
                    try {
                        statement.setInt(1, prodId);
                        statement.setInt(2, dto.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }


}
