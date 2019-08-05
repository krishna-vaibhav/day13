package tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.app.dao.StockDao;

public class TestUpdateStock {
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");) {

			StockDao dao = context.getBean(StockDao.class);
		
			Thread thread1 = new Thread(new Runnable() {
				public void run() {
					try {
						System.out.println("Invoking dao layer's method");
						dao.increaseStock("1", 5);
					} catch (RuntimeException e) {
					//	System.out.println("err in thrd " + Thread.currentThread() + " " + e);
						e.printStackTrace();
					}

				}
			}, "Thread 1");
			thread1.start();
			thread1.join();
			System.out.println("main over....");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
