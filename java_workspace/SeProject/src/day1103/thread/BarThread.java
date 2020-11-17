package day1103.thread;

import javax.swing.JProgressBar;

public class BarThread extends Thread{
	int n;
	int interval;
	JProgressBar bar;
	
	//이 thread를 이용하고자하는 자는, bar를 넘기시오.
	public BarThread(JProgressBar bar, int interval) {
		this.bar = bar;
		this.interval = interval;
	}
	
	public void run() {
		while(true) {
			n++;
			bar.setValue(n);
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
