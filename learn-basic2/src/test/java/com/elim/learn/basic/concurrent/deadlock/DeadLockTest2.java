package com.elim.learn.basic.concurrent.deadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * 该类用于测试死锁，然后通过jconsole和jstack工具来查看死锁。
 * jmap工具可以用来查看内存使用情况，有很详细的信息
 * @author Elim
 * @date 2016年10月6日
 */
public class DeadLockTest2 {

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	@Test
	public void test() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				lock1.lock();
				System.out.println("----getted lock1------");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock2.lock();
				System.out.println("----getted lock2-----");
				lock2.unlock();
				lock1.unlock();
			}
		}).start();

		lock2.lock();
		System.out.println("----getted lock2-----");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock1.lock();
		System.out.println("----getted lock1-----");
		lock1.unlock();
		lock2.unlock();
	}

}
