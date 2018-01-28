package com.helloworld.rest.dev.concurrent;

import com.helloworld.rest.dev.entities.ThreadSummary;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;

@Component
public class ThreadMonitor {
	private final ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();

	public List<ThreadSummary> checkDeadLocks() {
		List<ThreadSummary> threadSummaries = new ArrayList<>();
		long[] deadlockedThreadIds = mxBean.findDeadlockedThreads();
		if (deadlockedThreadIds != null && deadlockedThreadIds.length > 0) {
			ThreadInfo[] deadlockedThreads = mxBean.getThreadInfo(deadlockedThreadIds);
			if (deadlockedThreads != null && deadlockedThreads.length > 0) {
				for (ThreadInfo threadInfo : deadlockedThreads) {
					if (threadInfo != null) {
						for (Thread thread : Thread.getAllStackTraces().keySet()) {

							if (thread.getId() == threadInfo.getThreadId()) {
								ThreadSummary summary = new ThreadSummary();
								summary.setThreadInfo("Deadlock detected :: " + threadInfo.toString());
								StringBuilder builder = new StringBuilder();
								for (StackTraceElement ste : thread.getStackTrace()) {
									builder.append(ste.toString());
								}
								summary.setTrace(builder.toString());
								threadSummaries.add(summary);
							}
						}
					}
				}
			} //end of if loop
		}
		return threadSummaries;
	} //end of method

}
