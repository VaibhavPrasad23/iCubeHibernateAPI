package com.ics.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ics.api.Hibernate.dao.StudentDao;
import com.ics.api.Hibernate.entity.Student;
import com.ics.api.Hibernate.util.HibernateUtil;

@Path("messages")

public class MessageResource {

	MessageService messageservice = new MessageService();

	@GET
	@Path("/msg")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages() {
		return messageservice.getAllMessages();
	}

	JDBC jbc = new JDBC();

	@POST
	@Path("/dbnew")
	public String jdbc_newUser() {
		return jbc.jdbc_add();

	}

	@GET
	@Path("/dball")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Info> getInfoes() {
		System.out.println("vaibhavsss");

		return jbc.jdbc_serve();

	}

	@GET
	@Path("/jod")
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getIt() {
		System.out.println("hello system");

		List<Message> list = new ArrayList<>();

		Message m1 = new Message();
		m1.setId(12);
		m1.setAuthor("vaibhav");
		m1.setMessage("hello vai");

		Message m2 = new Message();
		m2.setId(2220);
		m2.setAuthor(m1.getAuthor());
		m2.setMessage(m1.getMessage());

		Message m3 = Message.builder().author("aaaa").build();

		Message m4 = new Message(258, "Hello World!", "Vaibhav");
		// return null;

		Message m5 = new Message(19, "Hello Jersey!", "Vaibhav");
		// List<Message> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		list.add(m3);
		list.add(m4);
		list.add(m5);
		System.out.println("iCube");

		return list;

	}

	@POST
	@Path("postt")
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message) {
//		return "Post Works!";
		return messageservice.addMessage(message);
	}

	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_XML)
	public Message getMessage(@PathParam("messageId") long id) {
		return messageservice.getMessage(id);
//		return "got it " + messageId;
	}

	@GET
	@Path("/msgs/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Info> jdbc_idSeek(@PathParam("messageId") int id) {
		return jbc.jdbc_specuser(id);

	}

	@GET
	@Path("/msgs/id/{uzer}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Info> jdbc_UserSeek(@PathParam("uzer") String id) {

		return jbc.jdbc_specusername(id);

	}

	@POST
	@Path("/saveusers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public Info createuser(Info z1) throws IOException {
		JDBC repo = new JDBC();
		System.out.println(z1);
		try {
			repo.create(z1);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return z1;
	}

	@DELETE
	@Path("/deleteusers/{id}")
	public void deleteuser(@PathParam("id") int id) throws SQLException {
		JDBC repo = new JDBC();
		if (repo.getoneuserID(id) != null) {
			repo.deleteuserID(id);
		} else {
			System.out.println("NOT FOUND");
		}
	}

	@PUT
	@Path("/updateusers/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateuser(@PathParam("id") int id, Info userupdate) throws SQLException {
		JDBC repo = new JDBC();

		if (repo.getoneuserID(id) != null) {
			userupdate.setId(id);
			repo.updateuser(userupdate);
		} else {
			System.out.println("id not found");
		}
	}
	


	
	//////////////////////
	
	
	
	
	
	
	
	
	
	
    @POST
    @Path("/hibernew")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(Student student) {
        StudentDao studentDao = new StudentDao();
        try {
            studentDao.saveStudent(student);
            return Response.status(Response.Status.CREATED).entity(student).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    @POST
    @Path("/hiber")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createHiberUser() {
		StudentDao studentDao = new StudentDao();
		Student student = new Student("Vaibhav", "Prasad", "vaibhavprasad@gmail.com");
		Student student1 = new Student("John", "Cena", "johncena@gmail.com");
		Student student2 = new Student("Neymar", "Junior", "njr11@gmail.com");


		studentDao.saveStudent(student);
		studentDao.saveStudent(student1);
		studentDao.saveStudent(student2);

        return Response.status(Response.Status.CREATED).entity(studentDao).build();
    }
    
    @GET
    @Path("/hiberget")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents() {
    	StudentDao xo = new StudentDao();
    	return xo.getStudents();
    }
	
	
	
    
    @GET
    @Path("/stu/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("name") String name) {
        try {
    		StudentDao studentDao = new StudentDao();

            Student student = studentDao.getStudent(name);
            if (student != null) {
                return Response.ok(student).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/update/{firstName}/{lastName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName, Student updatedStudent) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "from Student where firstName = :firstName and lastName = :lastName";
            List<Student> students = session.createQuery(hql, Student.class)
                                            .setParameter("firstName", firstName)
                                            .setParameter("lastName", lastName)
                                            .list();
            if (!students.isEmpty()) {
                Student studentToUpdate = students.get(0);
                studentToUpdate.setFirstName(updatedStudent.getFirstName());
                studentToUpdate.setLastName(updatedStudent.getLastName());
                studentToUpdate.setEmail(updatedStudent.getEmail()); // assuming email is a field in Student
                session.update(studentToUpdate);
                transaction.commit();
                return Response.ok(studentToUpdate).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/delete/{firstName}/{lastName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudentByName(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "from Student where firstName = :firstName and lastName = :lastName";
            List<Student> students = session.createQuery(hql, Student.class)
                                            .setParameter("firstName", firstName)
                                            .setParameter("lastName", lastName)
                                            .list();
            if (!students.isEmpty()) {
                for (Student student : students) {
                    session.delete(student);
                }
                transaction.commit();
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
	
	
	
}


