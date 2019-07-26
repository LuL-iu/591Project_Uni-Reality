import java.awt.EventQueue;

/**
 * Main Runner
 * @author liulu
 *
 */
public class UniReality {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Lauch Application 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowBuilder window = new WindowBuilder();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
