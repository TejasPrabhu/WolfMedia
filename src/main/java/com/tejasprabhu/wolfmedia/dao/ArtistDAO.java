package com.tejasprabhu.wolfmedia.dao;

import com.tejasprabhu.wolfmedia.model.Artist;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistDAO extends GenericDAO<Artist> {

    // Implementing the abstract method to return the table name.
    @Override
    protected String getTableName() {
        return "Artist";
    }

    // Implementing the abstract method to provide a RowMapper for Artist.
    @Override
    protected RowMapper<Artist> getRowMapper() {
        return (resultSet, rowNum) -> new Artist(
                resultSet.getInt("ArtistID"),
                resultSet.getString("ArtistName"),
                resultSet.getString("Status"),
                resultSet.getString("Type"),
                resultSet.getString("Country"),
                resultSet.getString("Genre"),
                resultSet.getInt("MonthlyListeners"),
                resultSet.getInt("LabelID")
        );
    }

    // Implementing the abstract method to return the ID column name.
    @Override
    protected String getIdColumnName() {
        return "ArtistID";
    }

    // Implementing the abstract method to return the ID value of an Artist instance.
    @Override
    protected Object getIdValue(Artist artist) {
        return artist.getArtistID();
    }

}

//package com.tejasprabhu.wolfmedia.dao;
//
//import com.tejasprabhu.wolfmedia.model.Artist;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class ArtistDAO {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public List<Artist> findWithFilters(Map<String, Object> filters) {
//        StringBuilder sql = new StringBuilder("SELECT * FROM Artist WHERE 1=1");
//        List<Object> params = new ArrayList<>();
//
//        for (Map.Entry<String, Object> entry : filters.entrySet()) {
//            sql.append(" AND ").append(entry.getKey()).append(" = ?");
//            params.add(entry.getValue());
//        }
//
//        return jdbcTemplate.query(sql.toString(), params.toArray(), (resultSet, rowNum) ->
//                new Artist(
//                        resultSet.getInt("ArtistID"),
//                        resultSet.getString("ArtistName"),
//                        resultSet.getString("Status"),
//                        resultSet.getString("Type"),
//                        resultSet.getString("Country"),
//                        resultSet.getString("Genre"),
//                        resultSet.getInt("MonthlyListeners"),
//                        resultSet.getInt("LabelID")
//                ));
//    }
//
//
//    public int save(Artist artist) {
//        String sql = "INSERT INTO Artist (ArtistName, Status, Type, Country, Genre, MonthlyListeners, LabelID) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        return jdbcTemplate.update(sql, artist.getArtistName(), artist.getStatus(), artist.getType(), artist.getCountry(), artist.getGenre(), artist.getMonthlyListeners(), artist.getLabelID());
//    }
//
//    public int update(Artist artist) {
//        String sql = "UPDATE Artist SET ArtistName = ?, Status = ?, Type = ?, Country = ?, Genre = ?, MonthlyListeners = ?, LabelID = ? WHERE ArtistID = ?";
//        return jdbcTemplate.update(sql, artist.getArtistName(), artist.getStatus(), artist.getType(), artist.getCountry(), artist.getGenre(), artist.getMonthlyListeners(), artist.getLabelID(), artist.getArtistID());
//    }
//
//    public int delete(int id) {
//        String sql = "DELETE FROM Artist WHERE ArtistID = ?";
//        return jdbcTemplate.update(sql, id);
//    }
//}
