package com.kpaudel.bacnet;

import java.net.SocketException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.npdu.ip.IpNetwork;
import com.serotonin.bacnet4j.npdu.ip.IpNetworkBuilder;
import com.serotonin.bacnet4j.transport.DefaultTransport;
import com.serotonin.bacnet4j.transport.Transport;

public class SimulatorService implements IService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimulatorService.class);
	public static final int DEFAULT_PORT = 0xBAC1;
	public static final int DEFAULT_LOCAL_DEVICE_ID = 0xCAFE1;
	private LocalDevice localDevice;

	private int port;
	private int deviceId;

	public SimulatorService() {
		this(DEFAULT_PORT, DEFAULT_LOCAL_DEVICE_ID);
	}

	public SimulatorService(int port, int deviceId) {
		this.port = port;
		this.deviceId = deviceId;
		this.init();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public void init() {
		String hostAddress = "127.0.0.1";
		try {
			hostAddress = IpNetwork.getDefaultLocalInetAddress().getHostAddress();
		} catch (UnknownHostException e) {
			LOGGER.error(e.toString());
		} catch (SocketException e) {
			LOGGER.error(e.toString());
		}

		IpNetwork network = new IpNetworkBuilder().withPort(this.port).withSubnet(hostAddress, 24).build();
		Transport transport = new DefaultTransport(network);
		this.localDevice = new LocalDevice(this.deviceId, transport);
	}

	@Override
	public void start() throws Exception {
		this.localDevice.initialize();
	}

	@Override
	public void stop() {
		this.localDevice.terminate();

	}

	@Override
	public boolean isRunning() {
		return this.localDevice.isInitialized();
	}
	
	

}
