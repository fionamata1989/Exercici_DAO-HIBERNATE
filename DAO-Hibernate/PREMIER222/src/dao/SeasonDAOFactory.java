package dao;

public class SeasonDAOFactory extends DAOManagerJDBCImpl {
	public DAOManager createDAO()
	{
		return new DAOManagerJDBCImpl();
	}
}
