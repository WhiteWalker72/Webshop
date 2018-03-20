package persistence;

import dto.OrderDTO;
import dto.OrderLineDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;
import utils.DateUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDAOSQLImpl extends DAOSQLImpl<OrderDTO> {

    private Integer lastId = null;

    OrderDAOSQLImpl(SQLDatabase database) {
        super(database, "bestelling", "id");
    }

    @Override
    public String getNextUniqueId() {
        if (lastId == null) {
            lastId = findById("" + 1) == null ? 0 : findAll().stream().map(OrderDTO::getId).reduce(Integer.MIN_VALUE, Integer::max);
            System.out.println(lastId);
        }
        lastId += 1;
        return lastId + "";
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
                    , resultSet.getInt("gironummer")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<OrderLineDTO> getOrderLines(int orderId) {
        return PersistenceServices.getInstance().getAllOrderLines().stream().filter(line -> line.getOrderId() == orderId).collect(Collectors.toList());
    }

    @Override
    public void insert(OrderDTO dto) throws ObjectAlreadyExistsException {
        if (findById(dto.getId() + "") != null) {
            throw new ObjectAlreadyExistsException(tableName, "the database");
        }

        database.insertInto(tableName, 5, statement -> {
            try {
                statement.setInt(1, dto.getId());
                statement.setInt(2, dto.getCustomerId());
                statement.setDate(3, DateUtils.fromJavaToSQLDate(dto.getOrderDate()));
                statement.setInt(4, dto.getAddressDTO().getId());
                statement.setInt(5, dto.getGiro());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        for (OrderLineDTO lineDTO : dto.getOrderLines()) {
            PersistenceServices.getInstance().insertOrderLine(lineDTO);
        }
    }

    @Override
    public void update(OrderDTO dto) throws ObjectNotFoundException {
        // Not really necessary
    }

}
