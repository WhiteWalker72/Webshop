package persistence;

import dto.OrderLineDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderLineDAOSQLImpl extends DAOSQLImpl<OrderLineDTO> {

    private Integer lastId = null;

    OrderLineDAOSQLImpl(SQLDatabase database) {
        super(database, "bestellingsregel", "id");
    }

    @Override
    public String getNextUniqueId() {
        if (lastId == null) {
            lastId = findById("" + 1) == null ? 0 : findAll().stream().map(OrderLineDTO::getId).reduce(Integer.MIN_VALUE, Integer::max);
        }
        lastId += 1;
        return lastId + "";
    }

    @Override
    OrderLineDTO getObjectFromResultSet(ResultSet resultSet) {
        try {
            return new OrderLineDTO(
                    resultSet.getInt("id")
                    , resultSet.getInt("bestelling_id")
                    , resultSet.getInt("aantal")
                    , resultSet.getDouble("prijs")
                    , resultSet.getInt("product")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(OrderLineDTO dto) throws ObjectAlreadyExistsException {
        if (findById(dto.getId() + "") != null) {
            throw new ObjectAlreadyExistsException(tableName, "the database");
        }

        database.insertInto(tableName, 5, statement -> {
            try {
                statement.setInt(1, dto.getId());
                statement.setInt(2, dto.getOrderId());
                statement.setInt(3, dto.getAmount());
                statement.setDouble(4, dto.getPrice());
                statement.setInt(5, dto.getProductId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void update(OrderLineDTO dto) throws ObjectNotFoundException {
        // Not really necessary
    }

}
