package com.kpaudel.bacnet;

/**
 * Interface for service
 * 
 * @author Krishna
 *
 */
public interface IService {
	public void init();

	public void start() throws Exception ;

	public void stop();

	public boolean isRunning();
}
