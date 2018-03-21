package com.java.core.concurrent.dedalock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DeadlockDetector {
	private final DeadlockHandler deadlockHandler;
	private final long period;
	private final TimeUnit unit;
	private final ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
	private final ScheduledExecutorService scheduler =
			Executors.newScheduledThreadPool(1);

	public DeadlockDetector(final DeadlockHandler deadlockHandler,
							final long period, final TimeUnit unit) {
		this.deadlockHandler = deadlockHandler;
		this.period = period;
		this.unit = unit;
	}

	public void start() {
		this.scheduler.scheduleAtFixedRate(
				new DeadLockCheckerTask(), period, period, unit);
	}

	class DeadLockCheckerTask implements Runnable {
		@Override
		public void run() {
			long[] deadlockedThreadIds = mbean.findDeadlockedThreads();
			if (deadlockedThreadIds != null) {
				ThreadInfo[] threadInfos =
						mbean.getThreadInfo(deadlockedThreadIds);
				deadlockHandler.handleDeadlock(threadInfos);
			}
		}
	} //end of runnable

}
