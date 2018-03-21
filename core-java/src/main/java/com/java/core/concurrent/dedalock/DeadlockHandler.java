package com.java.core.concurrent.dedalock;

import java.lang.management.ThreadInfo;

public interface DeadlockHandler {
	void handleDeadlock(final ThreadInfo[] deadlockedThreads);
}
