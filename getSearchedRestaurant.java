package com.dreamWedding.restaurants;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import org.json.JSONArray;
import org.json.JSONObject;

@Path("/getSearchedRestaurant")
public class getSearchedRestaurant {
	
	@Path("{n}")
	@GET
	@Produces("application/json")
	public String getSelectedRetaurant(@PathParam("n") String n) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		 Connection connect = null;
		 PreparedStatement preparedStatement = null;
	     ResultSet resultSet = null;
	     n = URLDecoder.decode(n, "UTF-8");
	     n = n.replace("+", " ").replace("&submitSearch=", "");
	     JSONArray jArr = new JSONArray();
		 try {

		        connect = DriverManager
		                .getConnection("jdbc:mysql://localhost/dreamweddingrestaurants",
		                        "root", "9daf623c");
		        preparedStatement = connect
	                    .prepareStatement("select * from restaurants where name=?");
		        preparedStatement.setString(1, n);
		        resultSet = preparedStatement.executeQuery();
		        
		        
		        while(resultSet.next())
		        {
		        	int id_json = resultSet.getInt("id");
		        	String name_json = resultSet.getString("name");
		        	String addr_json = resultSet.getString("address");
		        	String telephone_json = resultSet.getString("telephone");
		        	String email_json = resultSet.getString("email");
		        	String hotel_json = resultSet.getString("hotel");
		        	int capacity_json = resultSet.getInt("capacity");
		        	String garden_json = resultSet.getString("garden");
		        	String pool_json = resultSet.getString("pool");
		        	String degustation_json = resultSet.getString("degustation");
		        	String descr_json = resultSet.getString("description");
		        	String img_json = resultSet.getString("img");
		        	JSONObject jObj = new JSONObject();
		            jObj.put("id", id_json);
		            jObj.put("name", name_json);
		            jObj.put("address", addr_json);
		            jObj.put("telephone", telephone_json);
		            jObj.put("email", email_json);
		            jObj.put("hotel", hotel_json);
		            jObj.put("capacity", capacity_json);
		            jObj.put("garden", garden_json);
		            jObj.put("pool", pool_json);
		            jObj.put("degustation", degustation_json);
		            jObj.put("description", descr_json);
		            jObj.put("img", img_json);
		            jArr.put(jObj);
		        }
		        return jArr.toString();
		}catch (Exception e) {
		    throw e;
		} finally {
			try {
		        if (resultSet != null) {
		            resultSet.close();
		        }
		
		        if (preparedStatement != null) {
		            preparedStatement.close();
		        }
		
		        if (connect != null) {
		            connect.close();
		        }
		    } catch (Exception e) {
		
		    }
		}
		// return Response.status(200).entity(jArr.toString()).build();

	}
}
