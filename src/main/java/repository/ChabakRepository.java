package repository;

import database.DatabaseConnection;
import domain.Chabak;
import util.ConsoleUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChabakRepository {
    Connection connection;

    private final List<Chabak> chabaks;

    public ChabakRepository() {
        chabaks = new ArrayList<>();
        try {
            connection = DatabaseConnection.get();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * from cb_chabak_location"
            );
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                chabaks.add(new Chabak(rs.getString(1), rs.getString(2), rs.getString(3)
                        , rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getDouble(8), rs.getDouble(9)));
            }

        } catch (SQLException e) {
            ConsoleUtil.dbConnectError(e);
        }
    }

    public List<Chabak> searchByAddress(String query) {
        return chabaks.stream().filter(loc -> loc.getAddress().contains(query)).collect(Collectors.toList());
    }

    public List<Chabak> searchByKeyword(String keyword) {
        System.out.println(keyword);
        return chabaks.stream().filter(loc -> (loc.getAddress() + loc.getPlace_name() + loc.getUtility() + loc.getNotify()).contains(keyword))
                .collect(Collectors.toList());
    }

    public List<Chabak> getChabaks(int num) {
        List<Chabak> returnList = new ArrayList<>();
        for (int i = num*10; i < num*10+10 && i < chabaks.size(); i++) {
            returnList.add(chabaks.get(i));
        }
        return returnList;
    }
}