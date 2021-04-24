package com.GadgetBadget.model;

import java.sql.Connection;
import java.sql.DriverManager;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class DBconnection {

		public static  Connection connecter() {
			Connection con = null;

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3311/assistant?useTimezone=true&serverTimezone=UTC",
							"root", "");
					
					
					// For testing connection
					System.out.print("Successfully connected");
				} catch (Exception e) {
					System.out.print("connection fail");
					e.printStackTrace();
					System.out.print(e);
				}

				return con;
			}
	}
