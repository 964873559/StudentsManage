package test;

import org.junit.Assert;
import org.junit.Test;

import service.UsersDAO;
import service.impl.UsersDAOImpl;
import entity.Users;


public class TestUsersDAOImpl {

	@Test
	public void testUserLogin(){
			
		Users u = new Users(1,"littlewhite","123");
		UsersDAO udo = new UsersDAOImpl();
		Assert.assertEquals(true, udo.usersLogin(u));
	}
}
