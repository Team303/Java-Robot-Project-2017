package org.usfirst.frc.team303.robot;

/**
 * Demonstrates keeping track of time to execute a thread once every x ms.
 * If execution time is greater than time alloted, it will print the error and adjust to pick up where it left off 
 * 
 * To run: place in a thread, call start(), then run() every execution of the thread's main loop. call stop() to stop.  
 * 
 * @author Bradley Boxer
 *
 */
public class ThreadTimeManagerRush {
	private long startTime = 0;
	private long totalTimeShift = 0;
	private int iteration = 0;
	private int timeBetweenTicks = 0;
	private boolean stopped = true;
	private boolean debug = false;
	private boolean silence = false;
	
	public ThreadTimeManagerRush(int timeBetweenTicks) {
		this.timeBetweenTicks = timeBetweenTicks;
	}

	public int getIteration() {
		return iteration;
	}

	public long getMissedMs() {
		return -totalTimeShift;
	}
	
	public void start() {
		startTime = System.currentTimeMillis();
		stopped = false;
	}
	
	public void run() {
		if(!stopped) {
			iteration++;
			long timeShift = getTimeShift(startTime, iteration, timeBetweenTicks);
			totalTimeShift+=timeShift;
			this.startTime-=timeShift;	
		}
	}

	public void disableDebug() {
		debug = false;
	}
	
	/**
	 * 'debug' prints the amount of time waited after each "tick"
	 */
	public void enableDebug() {
		debug = true;
	}
	
	public void stop() {
		stopped = true;
	}
	
	/**
	 * 'silence' silences all print statements
	 */
	public void silence() {
		silence = true;
	}
	
	private long getTimeShift(long startTime, int iteration, int timeBetweenTicks) {
		long theoreticalCurrentTime = startTime+(iteration*timeBetweenTicks);
		long excessTime = theoreticalCurrentTime-System.currentTimeMillis();
		
		if(debug && !silence && excessTime>0) {
			System.out.println("waited "+excessTime+"ms");
		} else if(debug && !silence && excessTime==0) {
			System.out.println("did nothing");
		}
		
		if(excessTime>=0) {
			try {Thread.sleep(excessTime);} catch (InterruptedException e) {e.printStackTrace();}
			return 0;
		} else {
			if(!silence) {
				System.out.println("Can't keep up! Did the system time change, or is the server overloaded? Missed "+(-excessTime)+"ms");
			}
			return excessTime;
		}
	}
}
