
public class LambdaTest {
	
	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("익명클래스");
			}
		});
		t.start();
		
		Thread t2 = new Thread(() -> System.out.println("람다식"));
		t2.start();
	}
}
