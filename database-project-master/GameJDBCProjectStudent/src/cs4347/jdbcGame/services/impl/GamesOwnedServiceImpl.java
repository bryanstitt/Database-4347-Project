/* NOTICE: All materials provided by this project, and materials derived 
 * from the project, are the property of the University of Texas. 
 * Project materials, or those derived from the materials, cannot be placed 
 * into publicly accessible locations on the web. Project materials cannot 
 * be shared with other project teams. Making project materials publicly 
 * accessible, or sharing with other project teams will result in the 
 * failure of the team responsible and any team that uses the shared materials. 
 * Sharing project materials or using shared materials will also result 
 * in the reporting of all team members for academic dishonesty. 
 */
package cs4347.jdbcGame.services.impl;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import cs4347.jdbcGame.dao.GamesOwnedDAO;
import cs4347.jdbcGame.dao.impl.GamesOwnedDAOImpl;
import cs4347.jdbcGame.entity.GamesOwned;
import cs4347.jdbcGame.services.GamesOwnedService;
import cs4347.jdbcGame.util.DAOException;

public class GamesOwnedServiceImpl implements GamesOwnedService
{
    private DataSource dataSource;

    public GamesOwnedServiceImpl(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public GamesOwned create(GamesOwned gamesOwned) throws DAOException, SQLException
    {
    	GamesOwnedDAO gamesDAO = new GamesOwnedDAOImpl();
    	Connection connection = dataSource.getConnection();
    	
        try {
            connection.setAutoCommit(false);
            GamesOwned g1 = gamesDAO.create(connection, gamesOwned);   
            connection.commit();
            return g1;
        } catch (Exception ex) {
            connection.rollback();
            throw ex;
        }
        finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public GamesOwned retrieveByID(long gamesOwnedID) throws DAOException, SQLException
    {
    	GamesOwnedDAO gamesDAO = new GamesOwnedDAOImpl();
    	Connection connection = dataSource.getConnection();
    	
        try {
            connection.setAutoCommit(false);
            GamesOwned g1 = gamesDAO.retrieveID(connection, gamesOwnedID);   
            connection.commit();
            return g1;
        } catch (Exception ex) {
            connection.rollback();
            throw ex;
        }
        finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        
    }
    }

    @Override
    public GamesOwned retrievePlayerGameID(long playerID, long gameID) throws DAOException, SQLException
    {
    	GamesOwnedDAO gamesDAO = new GamesOwnedDAOImpl();
    	Connection connection = dataSource.getConnection();
    	
        try {
            connection.setAutoCommit(false);
            GamesOwned g1 = gamesDAO.retrievePlayerGameID(connection, playerID, gameID);   
            connection.commit();
            return g1;
        } catch (Exception ex) {
            connection.rollback();
            throw ex;
        }
        finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        
    }
    }

    @Override
    public List<GamesOwned> retrieveByGame(long gameID) throws DAOException, SQLException
    {
    	GamesOwnedDAO gamesDAO = new GamesOwnedDAOImpl();
    	Connection connection = dataSource.getConnection();
    	
        try {
            connection.setAutoCommit(false);
            List<GamesOwned> g1 = gamesDAO.retrieveByGame(connection, gameID);   
            connection.commit();
            return g1;
        } catch (Exception ex) {
            connection.rollback();
            throw ex;
        }
        finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        
    }
    }

    @Override
    public List<GamesOwned> retrieveByPlayer(long playerID) throws DAOException, SQLException
    {
    	GamesOwnedDAO gamesDAO = new GamesOwnedDAOImpl();
    	Connection connection = dataSource.getConnection();
    	
        try {
            connection.setAutoCommit(false);
            List<GamesOwned> g1 = gamesDAO.retrieveByPlayer(connection, playerID);   
            connection.commit();
            return g1;
        } catch (Exception ex) {
            connection.rollback();
            throw ex;
        }
        finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public int update(GamesOwned gamesOwned) throws DAOException, SQLException
    {
    	GamesOwnedDAO gamesDAO = new GamesOwnedDAOImpl();
    	Connection connection = dataSource.getConnection();
    	
        try {
            connection.setAutoCommit(false);
            int g1 = gamesDAO.update(connection, gamesOwned);   
            connection.commit();
            return g1;
        } catch (Exception ex) {
            connection.rollback();
            throw ex;
        }
        finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public int delete(long gameOwnedID) throws DAOException, SQLException{
    	
    	GamesOwnedDAO gamesDAO = new GamesOwnedDAOImpl();
    	Connection connection = dataSource.getConnection();
    	
        try {
            connection.setAutoCommit(false);
            int g1 = gamesDAO.delete(connection, gameOwnedID);   
            connection.commit();
            return g1;
        } catch (Exception ex) {
            connection.rollback();
            throw ex;
        }
        finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public int count() throws DAOException, SQLException{
    	
    	GamesOwnedDAO gamesDAO = new GamesOwnedDAOImpl();
    	Connection connection = dataSource.getConnection();
    	
        try {
            connection.setAutoCommit(false);
            int g1 = gamesDAO.count(connection);   
            connection.commit();
            return g1;
        } catch (Exception ex) {
            connection.rollback();
            throw ex;
        }
        finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
    }

