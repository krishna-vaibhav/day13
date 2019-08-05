package tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.app.dao.*;

public class TestPhantomRead {
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-jdbc-config.xml");) {

			BookShopDao dao = context.getBean(BookShopDao.class);

			Thread thread1 = new Thread(new Runnable() {
				public void run() {
					try {
						
						dao.checkStockRows(100);
					} catch (RuntimeException e) {
						System.out.println("err in thrd " + Thread.currentThread() + " " + e);
					}

				}
			}, "Thread 1");

			Thread thread2 = new Thread(new Runnable() {
				public void run() {
					dao.insertNewStock("4",60);
				}
			}, "Thread 2");

			thread1.start();
			Thread.sleep(5000);
			thread2.start();
			thread1.join();
			thread2.join();
			System.out.println("main over....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
