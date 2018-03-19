package persistence;

import dto.OrderDTO;
import dto.OrderLineDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;
import utils.DateUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOSQLImpl extends DAOSQLImpl<OrderDTO> {

    OrderDAOSQLImpl(SQLDatabase database) {
        super(database, "bestelling", "id");
    }

    @Override
    OrderDTO getObjectFromResultSet(ResultSet resultSet) {
        try {
            int orderId = resultSet.getInt("id");

            return new OrderDTO(
                    orderId
                    , resultSet.getInt("klant")
                    , DateUtils.fromSQLToJavaDate(resultSet.getDate("datum"))
                    , PersistenceServices.getInstance().findAddressById("" + resultSet.getInt("afleveradres"))
                    , getOrderLines(orderId)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<OrderLineDTO> getOrderLines(int orderId) {
        List<OrderLineDTO> orderLines = new ArrayList<>();

        try {
            PreparedStatement statement = database.getPreparedStatement("SELECT * FROM bestelregel WHERE bestelling_id = ?");
            statement.setInt(1, orderId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    orderLines.add(new OrderLineDTO(
                            resultSet.getInt("id")
                            , orderId
                            , resultSet.getInt("aantal")
                            , resultSet.getDouble("prijs")
                            , resultSet.getInt("product")
                    ));
                }
            }
            database.endStatement(statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderLines;
    }

    @Override
    public void insert(OrderDTO dto) throws ObjectAlreadyExistsException {
        if (findById(dto.getId() + "") != null) {
            throw new ObjectAlreadyExistsException(tableName, "the database");
        }

        database.insertInto(tableName, 4, statement -> {
            try {
                statement.setInt(1, dto.getId());
                statement.setInt(2, dto.getCustomerId());
                statement.setDate(3, DateUtils.fromJavaToSQLDate(dto.getOderDate()));
                statement.setInt(4, dto.getAddressDTO().getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        for (OrderLineDTO lineDTO : dto.getOrderLines()) {
            database.insertInto("bestelregel", 5, statement -> {
                try {
                    statement.setInt(1, lineDTO.getId());
                    statement.setInt(2, dto.getId());
                    statement.setInt(3, lineDTO.getAmount());
                    statement.setDouble(4, lineDTO.getPrice());
                    statement.setInt(5, lineDTO.getProductId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void update(OrderDTO dto) throws ObjectNotFoundException {
        // Not really necessary
    }

}
