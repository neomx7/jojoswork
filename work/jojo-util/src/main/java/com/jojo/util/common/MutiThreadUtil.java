/**
 *
 *JOJO
 *
 */
package com.jojo.util.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 多线程执行类，调用步骤如下：
 *
 * 1创建ExecutorService对象
 * getDefaultExecutorService()或自行得到ExecutorService对象
 *
 * 2设置ExecutorService对象
 * setExecutorService(ExecutorService)
 *
 * 3创建FutureTask（真正执行方法）
 *
 * 4异步执行
 * 1)不需要结果
 * asynExecute(FutureTask)
 * 2)需要返回结果
 * asynExecuteResult(FutureTask)
 *
 * 5不推荐关闭.如果有必要，关闭ExecutorService对象
 * destroy()
 *
 * @author cexian.tao
 * @version $Id: MutiThreadUtil.java, v 0.1 2013-12-12 下午2:07:09 cexian.tao Exp $
 */
public class MutiThreadUtil {
	private static final Logger logger = LoggerFactory.getLogger(MutiThreadUtil.class);
	private static int corePoolSize = 1;
	private static int maximumPoolSize = 10;
	private static long keepAliveTime = 3000l;
	private static boolean isTimeOutLimit = false;//是否需要超时设置
	private static long timeOut = 5000l;//超时时间，5s后没有执行完，则抛timeout异常
	private static ExecutorService executorService;

	/**
     * 异步 执行，不阻塞后续代码
     *
     * @param future
     */
    public static void asynExecute(FutureTask<Object> future) {
//        FutureTask<Object> future = new FutureTask<Object>(new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                return asynExecute(event);
//            }
//        });
        executorService.execute(future);
    }

    /**
     * 并发 执行返回结果,会阻塞后续代码执行
     *
     * @param future
     */
    public static Object asynExecuteResult(FutureTask<Object> future) {
//        FutureTask<Object> future = new FutureTask<Object>(new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                return asynExecute(event);
//            }
//        });
    	Object result = null;
        executorService.execute(future);
        try {
        	//阻塞，取得结果，同时设置超时执行时间为5秒。同样可以用future.get()，不设置执行超时时间取得结果
        	if (isTimeOutLimit) {
        		result = future.get(timeOut, TimeUnit.MILLISECONDS);
        	} else {
        		result = future.get();
        	}
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
        return result;
    }

    /**
     * 返回默认ThreadPoolExecutor对象
     *
     * @return
     */
	public static ThreadPoolExecutor getDefaultExecutorService() {
		return new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
				keepAliveTime, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
	}

	/**
	 * 关闭，一般不用关闭
	 */
	public static void destroy() {
		if (executorService != null) {
			executorService.shutdown();
//	        while (!executorService.isTerminated()) {
//          try {
//              executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
//          } catch (InterruptedException e) {
//              logger.error("", e);
//          }
//      }
		}
    }



    /**
	 * Getter method for property <tt>corePoolSize</tt>.
	 *
	 * @return property value of corePoolSize
	 */
	public static int getCorePoolSize() {
		return corePoolSize;
	}


	/**
	 * Setter method for property <tt>corePoolSize</tt>.
	 *
	 * @param corePoolSize value to be assigned to property corePoolSize
	 */
	public static void setCorePoolSize(int corePoolSize) {
		MutiThreadUtil.corePoolSize = corePoolSize;
	}


	/**
	 * Getter method for property <tt>maximumPoolSize</tt>.
	 *
	 * @return property value of maximumPoolSize
	 */
	public static int getMaximumPoolSize() {
		return maximumPoolSize;
	}


	/**
	 * Setter method for property <tt>maximumPoolSize</tt>.
	 *
	 * @param maximumPoolSize value to be assigned to property maximumPoolSize
	 */
	public static void setMaximumPoolSize(int maximumPoolSize) {
		MutiThreadUtil.maximumPoolSize = maximumPoolSize;
	}


	/**
	 * Getter method for property <tt>keepAliveTime</tt>.
	 *
	 * @return property value of keepAliveTime
	 */
	public static long getKeepAliveTime() {
		return keepAliveTime;
	}


	/**
	 * Setter method for property <tt>keepAliveTime</tt>.
	 *
	 * @param keepAliveTime value to be assigned to property keepAliveTime
	 */
	public static void setKeepAliveTime(long keepAliveTime) {
		MutiThreadUtil.keepAliveTime = keepAliveTime;
	}


	/**
	 * Getter method for property <tt>timeOut</tt>.
	 *
	 * @return property value of timeOut
	 */
	public static long getTimeOut() {
		return timeOut;
	}


	/**
	 * Setter method for property <tt>timeOut</tt>.
	 *
	 * @param timeOut value to be assigned to property timeOut
	 */
	public static void setTimeOut(long timeOut) {
		MutiThreadUtil.timeOut = timeOut;
	}


	/**
	 * Getter method for property <tt>isTimeOutLimit</tt>.
	 *
	 * @return property value of isTimeOutLimit
	 */
	public static boolean isTimeOutLimit() {
		return isTimeOutLimit;
	}


	/**
	 * Setter method for property <tt>isTimeOutLimit</tt>.
	 *
	 * @param isTimeOutLimit value to be assigned to property isTimeOutLimit
	 */
	public static void setTimeOutLimit(boolean isTimeOutLimit) {
		MutiThreadUtil.isTimeOutLimit = isTimeOutLimit;
	}


	/**
	 * Getter method for property <tt>executorService</tt>.
	 *
	 * @return property value of executorService
	 */
	public static ExecutorService getExecutorService() {
		return executorService;
	}


	/**
	 * Setter method for property <tt>executorService</tt>.
	 *
	 * @param executorService value to be assigned to property executorService
	 */
	public static void setExecutorService(ExecutorService executorService) {
		MutiThreadUtil.executorService = executorService;
	}
}
