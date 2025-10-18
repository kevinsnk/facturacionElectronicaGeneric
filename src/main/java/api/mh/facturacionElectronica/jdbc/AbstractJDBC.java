package api.mh.facturacionElectronica.jdbc;

import java.sql.SQLException;

public abstract class AbstractJDBC {

	public abstract void save(Object entity) throws SQLException;
	public abstract void edit(Object entity) throws SQLException;
	public abstract void delete(Object entity) throws SQLException;
}
