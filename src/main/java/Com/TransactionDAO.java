package Com;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
	public static List<TransactionDTO> getAllRecords(int start, int total) throws Exception {

		List<TransactionDTO> tran = new ArrayList<>();
		Connection con = DataBaseCon.getCon();
		String query = "SELECT * FROM Transactions LIMIT ?, ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, start );  
		ps.setInt(2, total);      

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			
			int txnId = rs.getInt(1);
			Date txnDateTime = rs.getDate(2);
			int sourceId = rs.getInt(3);
			int targetId = rs.getInt(4);
			String sourceType = rs.getString(5);
			String destType = rs.getString(6);
			double txnAmount = rs.getDouble(7);
			TransactionDTO t=new TransactionDTO(txnId,txnDateTime,sourceId,targetId,sourceType,destType,txnAmount);
			tran.add(t);
		}
		return tran;
	}
	 public static int getTotalRecords() throws Exception {
		 int count = 0;

	        Connection con = DataBaseCon.getCon();
	        String query = "SELECT COUNT(*) FROM Transactions ";
	        PreparedStatement ps = con.prepareStatement(query);

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	        return count;
	    }

}