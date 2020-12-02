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
package cs4347.jdbcGame.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import cs4347.jdbcGame.dao.GamesOwnedDAO;
import cs4347.jdbcGame.entity.GamesOwned;
import cs4347.jdbcGame.util.DAOException;

public class GamesOwnedDAOImpl implements GamesOwnedDAO{

	private static final String insertSQL = "INSERT INTO gamesowned (player_ID, game_ID, purchase_date, purchase_price) VALUES (?, ?, ?, ?);";
	@Override
    public GamesOwned create(Connection connection, GamesOwned gamesOwned) throws SQLException, DAOException{
		if (gamesOwned.getId() != null) {
            throw new DAOException("Trying to insert Game with NON-NULL ID");
        }

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

            ps.setLong(1, gamesOwned.getPlayerID());
            ps.setLong(2, gamesOwned.getGameID());
            ps.setDate(3, new java.sql.Date(gamesOwned.getPurchaseDate().getTime()));
            ps.setFloat(4, gamesOwned.getPurchasePrice());
            ps.executeUpdate();

            // Copy the assigned ID to the game instance.
            ResultSet keyRS = ps.getGeneratedKeys();
            keyRS.next();
            int lastKey = keyRS.getInt(1);
            gamesOwned.setId((long) lastKey);
            return gamesOwned;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
    }

	 final static String selectSQL = "SELECT id, player_ID, game_ID, purchase_date, purchase_price FROM gamesowned where id = ?";
    @Override
    public GamesOwned retrieveID(Connection connection, Long gamesOwnedID) throws SQLException, DAOException{
    	   if (gamesOwnedID == null) {
               throw new DAOException("Trying to retrieve Game with NULL ID");
           }

           PreparedStatement ps = null;
           try {
               ps = connection.prepareStatement(selectSQL);
               ps.setLong(1, gamesOwnedID);
               ResultSet rs = ps.executeQuery();
               if (!rs.next()) {
                   return null;
               }

               GamesOwned game = extractFromRS(rs);
               return game;
           }
           finally {
               if (ps != null && !ps.isClosed()) {
                   ps.close();
               }
           }
    }

    public static final String retrievePlayerGameID = "SELECT * FROM gamesowned WHERE player_ID = ? AND game_ID = ?";
    @Override
    public GamesOwned retrievePlayerGameID(Connection connection, Long playerID, Long gameID)
            throws SQLException, DAOException{
    	

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(retrievePlayerGameID);
            ps.setLong(1, playerID);
            ps.setLong(2, gameID);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }

            GamesOwned game = extractFromRS(rs);
            return game;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
    }
    
    final static String retrieveByGameSQL = "SELECT id,player_ID,game_ID,purchase_date,purchase_price FROM gamesowned WHERE game_ID like ?";
    @Override
    public List<GamesOwned> retrieveByGame(Connection connection, Long gameID) throws SQLException, DAOException
    {
    	
    	List<GamesOwned> result = new ArrayList<GamesOwned>();
    	PreparedStatement ps = null;
    	
        try {
        	ps = connection.prepareStatement(retrieveByGameSQL);
    		ps.setLong(1, gameID);
    		ResultSet rs = ps.executeQuery();

    		while (rs.next()) {
    			GamesOwned games = extractFromRS(rs);
    			result.add(games);
    		}
    		return result;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
    }


    final static String retrieveByPlayerSQL = "SELECT id,player_ID,game_ID,purchase_date,purchase_price FROM gamesowned where player_ID like ?";
    @Override
    public List<GamesOwned> retrieveByPlayer(Connection connection, Long playerID) throws SQLException, DAOException
    {
    	
    	List<GamesOwned> result = new ArrayList<GamesOwned>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(retrieveByPlayerSQL);
            ps.setLong(1, playerID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GamesOwned game = extractFromRS(rs);
                result.add(game);
            }
            return result;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
    }

    final static String updateSQL = "UPDATE gamesowned SET player_ID = ?, game_ID = ?, purchase_date = ?, purchase_price = ? WHERE id = ?;";
    @Override
    public int update(Connection connection, GamesOwned gamesOwned) throws SQLException, DAOException{
    	
    	Long id = gamesOwned.getId();
        if (id == null) {
            throw new DAOException("Trying to update Game with NULL ID");
        }

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(updateSQL);

            ps.setLong(1, gamesOwned.getPlayerID());
            ps.setLong(2, gamesOwned.getGameID());
            ps.setDate(3,  new java.sql.Date(gamesOwned.getPurchaseDate().getTime()));
            ps.setFloat(4, gamesOwned.getPurchasePrice());
            ps.setLong(5, id);

            int rows = ps.executeUpdate();
            return rows;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
    }
    
    final static String deleteSQL = "delete from gamesowned where id = ?;";
    @Override
    public int delete(Connection connection, Long gameOwnedID) throws SQLException, DAOException{

         PreparedStatement ps = null;
         try {
             ps = connection.prepareStatement(deleteSQL);
             ps.setLong(1, gameOwnedID);

             int rows = ps.executeUpdate();
             return rows;
         }
         finally {
             if (ps != null && !ps.isClosed()) {
                 ps.close();
             }
         }
    }

   
    final static String countSQL = "select count(*) from gamesowned";
    @Override
    public int count(Connection connection) throws SQLException, DAOException{

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(countSQL);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new DAOException("No Count Returned");
            }
            int count = rs.getInt(1);
            return count;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
    }
    
    private GamesOwned extractFromRS(ResultSet rs) throws SQLException
    {
        GamesOwned game = new GamesOwned();
        game.setId(rs.getLong("id"));
        game.setPlayerID(rs.getLong("player_ID"));
        game.setGameID(rs.getLong("game_ID"));
        game.setPurchaseDate(rs.getDate("purchase_date"));
        game.setPurchasePrice(rs.getFloat("purchase_price"));
        return game;
    }

}
