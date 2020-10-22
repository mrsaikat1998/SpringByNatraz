package com.nt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;

import com.nt.dto.StudentDTO;
import com.nt.service.StudentMgmtService;

public class JdbcTemplateCallbackInterfacesTest {

	public static void main(String[] args) {
		ApplicationContext ctx=null;
		StudentMgmtService service=null;
		StudentDTO dto=null;
		//create IOC container
		ctx=new ClassPathXmlApplicationContext("com/nt/cfgs/applicationContext.xml");
		//get SErvice class object
		service=ctx.getBean("studService",StudentMgmtService.class);
		//invoke method
		try {
         dto=service.fetchStudentByNo(453);
         System.out.println(dto);
		}
		catch(DataAccessException dae) {
			if(dae instanceof EmptyResultDataAccessException)
				  System.err.println("Record not found");
			else if(dae instanceof BadSqlGrammarException)
				System.err.println("SQLquery is wrong");
			else
				System.err.println("Other Internal problems");
			//dae.printStackTrace();
		}

		  //close container
		((AbstractApplicationContext) ctx).close();

	}//main
}//class
