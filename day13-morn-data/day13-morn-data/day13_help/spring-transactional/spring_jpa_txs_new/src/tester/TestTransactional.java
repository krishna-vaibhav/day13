package tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.app.dao.StockDao;

public class TestTransactional {
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");) {

			StockDao dao = context.getBean(StockDao.class);
			dao.increaseStock("1", 1);
			System.out.println("main over....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
