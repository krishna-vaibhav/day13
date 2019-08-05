package tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.app.dao.BookStoreDao;


public class TestTxPropagation {
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");) {

			BookStoreDao dao=context.getBean("store_dao",BookStoreDao.class);
			System.out.println(dao.purchaseBooks("abc@gmail","1", 1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
