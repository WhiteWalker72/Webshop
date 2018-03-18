package persistence;

import java.sql.PreparedStatement;

interface FillStatementStrategy {

    void fillStatement(PreparedStatement statement);

}
