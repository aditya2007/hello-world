package com.java.core.concurrent.dedalock;

import java.lang.management.ThreadInfo;
import java.util.Map;

public class DeadlockHandlerImpl implements DeadlockHandler {

	@Override
	public void handleDeadlock(ThreadInfo[] deadlockedThreads) {
		if (deadlockedThreads != null) {
			System.err.println("Deadlock detected!");

			for (ThreadInfo threadInfo : deadlockedThreads) {

				if (threadInfo != null) {

					for (Thread thread : Thread.getAllStackTraces().keySet()) {

						if (thread.getId() == threadInfo.getThreadId()) {
							System.err.println(threadInfo.toString().trim());

							for (StackTraceElement ste : thread.getStackTrace()) {
								System.err.println("\t" + ste.toString().trim());
							}
							//thread.interrupt();
						}
					}
				}
			}
		}
	}
}
