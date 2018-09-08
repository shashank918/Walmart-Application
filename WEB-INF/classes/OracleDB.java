import java.sql.*;
import java.text.*;
import java.util.*;

public class OracleDB {
	
	// public static void main(String args[])
	// {
	// 	try
	// 	{
	// 		Connection conn = createConnection();
	// 		Statement stmt=conn.createStatement();
	// 		ResultSet rs = stmt.executeQuery("select * from addressmiddlelayer");
	// 		while(rs.next())  
	// 		{
 //            System.out.println(rs.getInt(2)+"  "+rs.getString(1));
	// 		}
	// 		conn.close();
	// 	}
	// 	catch(Exception e)
	// 	{
	// 		System.out.println(e);
	// 	}
	// }
	
	// public static HashMap<Integer,Product> getAllProductDetails()
	// {
	// 	HashMap<Integer,Product> pro = new HashMap<Integer,Product>();
	// 	try
	// 	{
	// 		Connection conn = createConnection();
	// 		Statement stmt=conn.createStatement();
	// 		ResultSet rs = stmt.executeQuery("select * from products");
	// 		while(rs.next())  
	// 		{
 //              Product p = new Product();
	// 		  p.setId(rs.getInt(1));
	// 		  p.setName(rs.getString(2));
	// 		  p.setUom(rs.getString(3));
	// 		  p.setBrandId(rs.getInt(4));
	// 		  p.setVendorid(rs.getInt(5));
	// 		  p.setStoreid(rs.getInt(6));
	// 		  p.setInventoryCount(rs.getInt(7));
	// 		  p.setImgpath(rs.getString(8));
	// 		  p.setPrice(rs.getInt(9));
	// 		  pro.put(p.getId(),p);
	// 		}
	// 		conn.close();
	// 	}
	// 	catch(Exception e)
	// 	{
	// 		System.out.println(e);
	// 	}
	// 	return pro;
	// }


	
	// get product details less than 50
 //    public static HashMap<Integer,Product> getLessThanFifty()
	// {
	// 	HashMap<Integer,Product> pro = new HashMap<Integer,Product>();
	// 	try
	// 	{
	// 		Connection conn = createConnection();
	// 		Statement stmt=conn.createStatement();
	// 		ResultSet rs = stmt.executeQuery("select * from products where INVENTORY_COUNT < 50");
	// 		while(rs.next())  
	// 		{
	// 			Product p = new Product();
	// 			p.setId(rs.getInt(1));
	// 			p.setName(rs.getString(2));
	// 			p.setUom(rs.getString(3));
	// 			p.setBrandId(rs.getInt(4));
	// 			p.setVendorid(rs.getInt(5));
	// 			p.setStoreid(rs.getInt(6));
	// 			p.setInventoryCount(rs.getInt(7));
	// 			p.setImgpath(rs.getString(8));
	// 			p.setPrice(rs.getInt(9));
	// 			pro.put(p.getId(),p);
	// 		}
	// 		conn.close();
	// 	}
	// 	catch(Exception e)
	// 	{
	// 		System.out.println(e);
	// 	}
	// 	return pro;
	// }
	
	
	

	
	
	// // update purchase header lines
	// public static void updatePurchaseHeaderLines(int id,int qty)
	// {
	// 	int storeid=0;
	// 	int locationid=0;
	// 	int seqVal=0;
	// 	int vendorid=0;
	// 	String uom="";
	// 	int price=0;
	// 	int total=0;
	// 	try
	// 	{
	// 		int prodid = id;
	// 		Connection conn = createConnection();
	// 		Statement stmt=conn.createStatement();
			
	// 		String q1 = "select purchase_header_sequence.nextVal from dual";
			
	// 		ResultSet rs1 = stmt.executeQuery(q1);
	// 		while(rs1.next())  
	// 		{
	// 			seqVal = rs1.getInt(1);
	// 			System.out.println(seqVal);
	// 		}
			
	// 		String q2 = "select PURCHASE_ORDERS_HEADER.storeid,storedetails.LOCATIONID,products.VENDORID,products.UOM,products.PRICE from PURCHASE_ORDERS_HEADER inner join storedetails on PURCHASE_ORDERS_HEADER.STOREID = storedetails.ID inner join products on products.STOREID = PURCHASE_ORDERS_HEADER.STOREID where products.ID="+prodid+"";
	// 		ResultSet rs2 = stmt.executeQuery(q2);
	// 		while(rs2.next())  
	// 		{
	// 			storeid = rs2.getInt(1);
	// 			locationid = rs2.getInt(2);
	// 			vendorid = rs2.getInt(3);
	// 			uom = rs2.getString(4);
	// 			price = rs2.getInt(5);
	// 		}
			
			
	// 		String q3 = "insert into purchase_orders_header "+"values("+seqVal+","+storeid+","+locationid+")";
	// 		stmt.executeUpdate(q3);
	// 		// insert into header lines
			
	// 		total = price * qty;
			
	// 		String q4 = "insert into purchase_orders_lines "+"values(purchase_lines_sequence.nextVal,"+seqVal+","+prodid+","+vendorid+","+qty+",'"+uom+"',"+price+","+total+")";
	// 		stmt.executeUpdate(q4);
			
			
	// 		conn.close();
	// 	}
	// 	catch(Exception e)
	// 	{
	// 		System.out.println(e);
	// 	}
	// } 
	
	
	// public static void updateProductInventory(String id,String qty)
	// {
	// 	try
	// 	{
	// 		int prodid = Integer.parseInt(id);
	// 		int qtys = Integer.parseInt(qty);
	// 		Connection conn = createConnection();
	// 		String query = "update products set INVENTORY_COUNT = INVENTORY_COUNT + "+qtys+" where id= "+prodid+" ";
	// 		Statement s = conn.createStatement();
	// 		s.executeUpdate(query);
	// 		conn.close();
	// 	}
	// 	catch(Exception e)
	// 	{
	// 		System.out.println(e);
	// 	}
	// }

	// login customer exists
	public static int customerExists(String username)
	{
		ResultSet resultSet;
		int result =0;
		try 
		{
		Connection con = createConnection();
		String query = "select * from customerlogins where username = ?";
		PreparedStatement pstm = con.prepareStatement(query);
		pstm.setString(1,username);
		resultSet = pstm.executeQuery();
		if(!resultSet.next())
		{
		 result = 0;
		}
		else
		{
		 result = 1;
		}
		con.close();				
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		} 
		return result;
	}
	
	

	// login customer exists
	public static int empExists(String username)
	{
		ResultSet resultSet;
		int result =0;
		try 
		{
		Connection con = createConnection();
		String query = "select * from employeelogins where username = ?";
		PreparedStatement pstm = con.prepareStatement(query);
		pstm.setString(1,username);
		resultSet = pstm.executeQuery();
		if(!resultSet.next())
		{
		 result = 0;
		}
		else
		{
		 result = 1;
		}
		con.close();				
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		} 
		return result;
	}

	
	
	// login customer
	
	public static HashMap<String,String> getCustomerLogin(String username)
	{
		HashMap<String,String> map = new HashMap<String,String>();
		try
		{
        			
		Connection conn = createConnection();
		Statement stmt=conn.createStatement();
		String q2 = "select username,password from CUSTOMERLOGINS where username = '"+username+"'";
		ResultSet rs2 = stmt.executeQuery(q2);
			while(rs2.next())  
			{
              map.put(rs2.getString(1),rs2.getString(2));
			}
		conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return map;
	}
	
	
	// employee login
	
	public static HashMap<String,String> getEmpLogin(String username)
	{
		HashMap<String,String> map = new HashMap<String,String>();
		try
		{
        			
		Connection conn = createConnection();
		Statement stmt=conn.createStatement();
		String q2 = "select username,password from EMPLOYEELOGINS where username = '"+username+"'";
		ResultSet rs2 = stmt.executeQuery(q2);
			while(rs2.next())  
			{
              map.put(rs2.getString(1),rs2.getString(2));
			}
		conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return map;
	}


	// ------------------------------------------------Sumanth Gouru Code------------------------------------------------

	public static ResultSet getproducts(String c){
		
		ResultSet rs = null;
		Connection con;
		try{
			System.out.println("In OracleDb getproducts category:" + c);
				con = createConnection();
			String sql = "SELECT * FROM products p, productbrands pb WHERE p.brand_id = pb.brand_id and pb.category='"+c+"'";
			Statement stmt=con.createStatement();
			// stmt.setString(1,c);
			 rs = stmt.executeQuery(sql);
			
			 
			
		}
		catch(Exception e)
		{

				System.out.println("Exceptio in getproducts in utilities" + e.getMessage());
			
		}
		
		return rs;
		

	}


	public static HashMap<Integer,Product> getAllProductDetails()
	{
		HashMap<Integer,Product> pro = new HashMap<Integer,Product>();
		try
		{
			Connection conn = createConnection();
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from products");
			while(rs.next())  
			{
              Product p = new Product();
			  p.setId(rs.getInt(1));
			  p.setName(rs.getString(2));
			  p.setUom(rs.getString(3));
			  p.setBrandId(rs.getInt(4));
			  p.setVendorid(rs.getInt(5));
			  p.setStoreid(rs.getInt(6));
			  p.setInventoryCount(rs.getInt(7));
			  p.setImgpath(rs.getString(8));
			  p.setPrice(rs.getInt(9));
			  pro.put(p.getId(),p);
			}
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return pro;
	}
	
	// get product details less than 50
    public static HashMap<Integer,Product> getLessThanFifty()
	{
		HashMap<Integer,Product> pro = new HashMap<Integer,Product>();
		try
		{
			Connection conn = createConnection();
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from products where INVENTORY_COUNT < 50");
			while(rs.next())  
			{
				Product p = new Product();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setUom(rs.getString(3));
				p.setBrandId(rs.getInt(4));
				p.setVendorid(rs.getInt(5));
				p.setStoreid(rs.getInt(6));
				p.setInventoryCount(rs.getInt(7));
				p.setImgpath(rs.getString(8));
				p.setPrice(rs.getInt(9));
				pro.put(p.getId(),p);
			}
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return pro;
	}



	// get purchase header sequence next value
	public static int getPurchaseSeqValue()
	{
		int seqVal=0;
		try
		{
			Connection conn = createConnection();
			Statement stmt=conn.createStatement();
			String q1 = "select purchase_header_sequence.nextVal from dual";
			ResultSet rs1 = stmt.executeQuery(q1);
			while(rs1.next())  
			{
				seqVal = rs1.getInt(1);
			}
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return seqVal;
	} 
	
	
	public static void updatePurchaseHeader(int id,int seqVal)
	{
		try
		{
		int storeid=0;
		int locationid=0; 
		Connection conn = createConnection();
		Statement stmt=conn.createStatement();
			String q2 = "select PURCHASE_ORDERS_HEADER.storeid,storedetails.LOCATIONID,products.VENDORID,products.UOM,products.PRICE from PURCHASE_ORDERS_HEADER inner join storedetails on PURCHASE_ORDERS_HEADER.STOREID = storedetails.ID inner join products on products.STOREID = PURCHASE_ORDERS_HEADER.STOREID where products.ID="+id+"";
			ResultSet rs2 = stmt.executeQuery(q2);
			while(rs2.next())  
			{
				storeid = rs2.getInt(1);
				locationid = rs2.getInt(2);
			}
			String q3 = "insert into purchase_orders_header "+"values("+seqVal+","+storeid+","+locationid+")";
			stmt.executeUpdate(q3);
		conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	} 
	
	
	// update purchase header lines
	public static void updatePurchaseLines(int id,int qty,int seqVal)
	{

			System.out.println("Received Id in OracleDB: " + id);
		int vendorid=0;
		String uom="";
		int price=0;
		int total=0;
		try
		{

			Connection conn = createConnection();
			Statement stmt=conn.createStatement();
			
			String q2 = "select PURCHASE_ORDERS_HEADER.storeid,storedetails.LOCATIONID,products.VENDORID,products.UOM,products.PRICE from PURCHASE_ORDERS_HEADER inner join storedetails on PURCHASE_ORDERS_HEADER.STOREID = storedetails.ID inner join products on products.STOREID = PURCHASE_ORDERS_HEADER.STOREID where products.ID="+id+"";
			ResultSet rs2 = stmt.executeQuery(q2);
			while(rs2.next())  
			{
				vendorid = rs2.getInt(3);
				uom = rs2.getString(4);
				price = rs2.getInt(5);
			}
			total = price * qty;
			
			String q4 = "insert into purchase_orders_lines "+"values(purchase_lines_sequence.nextVal,"+seqVal+","+vendorid+","+id+","+qty+",'"+uom+"',"+price+","+total+")";
			stmt.executeUpdate(q4);
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	} 

	public static boolean checkCapacity(String qty)
	{
		boolean check = false;
		int sumOfPro = 0;
		int capStore=0;
		int q = Integer.parseInt(qty);
		try
		{
			Connection conn = createConnection();
			Statement stmt=conn.createStatement();
			String q1 = "select sum(Inventory_count) from products";
			ResultSet rs1 = stmt.executeQuery(q1);
			while(rs1.next())  
			{
				sumOfPro = rs1.getInt(1);
			}
			String q2 = "select capacity from storedetails where id=2";
			ResultSet rs2 = stmt.executeQuery(q2);
			while(rs2.next())  
			{
				capStore = rs2.getInt(1);
			}
			if((sumOfPro + q) >= capStore)
			{
				check=true;
			}
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return check;
	}
	
	
	public static void updateProductInventory(String id,String qty)
	{
		try
		{
			int prodid = Integer.parseInt(id);
			int qtys = Integer.parseInt(qty);
			Connection conn = createConnection();
			String query = "update products set INVENTORY_COUNT = INVENTORY_COUNT + "+qtys+" where id= "+prodid+" ";
			Statement s = conn.createStatement();
			s.executeUpdate(query);
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	public static Connection createConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		String driverName="oracle.jdbc.driver.OracleDriver";
		String username="sumanthgouru";
		String password="$umanth11";
		String path="jdbc:oracle:thin:@localhost:1521:xe";
		Class.forName(driverName);
		Connection connection = DriverManager.getConnection(path,username,password);
		return connection;
	}
		


}