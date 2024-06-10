package com.ics.api;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("myresource")
public class MyResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Hello World :)";

	}

	@Path("/{name}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello(@PathParam("name") String name) {
		return "iCube Systems: Hello, " + name;

	}

	@POST
	@Path("/saveuser")
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes({MediaType.APPLICATION_JSON,MediaType.MULTIPART_FORM_DATA})
	@Consumes(MediaType.APPLICATION_JSON)
	public Info createuser(Info z1) throws IOException {
		JDBC repo = new JDBC();
		System.out.println(z1);

		repo.jdbc_into(z1);

		return z1;
	}

//	@POST
//	@Path("/manyusers")
//	@Produces(MediaType.APPLICATION_JSON)
////	@Consumes({MediaType.APPLICATION_JSON,MediaType.MULTIPART_FORM_DATA})
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Info createmanyuser(Info z1) throws IOException {
//		JDBC repo=new JDBC();
//		System.out.println(z1);
//		try {
//			repo.jdbc_multi(z1);
//		} catch (SQLException e) {
//			System.out.println(e);
//			e.printStackTrace();
//		}
//		return z1;
//	}
}
