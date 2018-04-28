package com.kpaudel.bacnet.views;

import com.kpaudel.bacnet.SimulatorService;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimulatorApplication extends Application {
	private static final String TITLE = "BACnet Simulator";

	private static final String CONNECTED_ICON = "/globe_connected.png";
	private static final String DISCONNECTED_ICON = "/globe_disconnected.png";

	private SimulatorService simulator;
	private Label statusLabel;
	private TextArea statusTextArea;
	private Image connectedImage, disconnectedImage;

	public SimulatorApplication() {
		this.simulator = new SimulatorService();
		this.connectedImage = new Image(getClass().getResourceAsStream(CONNECTED_ICON));
		this.disconnectedImage = new Image(getClass().getResourceAsStream(DISCONNECTED_ICON));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(TITLE);
		VBox mainVBox = new VBox(10);
		HBox propsHBox = new HBox(10);
		Label portLabel = new Label("Port");
		TextField portTextField = new TextField(
				"0x" + Integer.toHexString(SimulatorService.DEFAULT_PORT).toUpperCase());
		Label deviceIdLabel = new Label("Device Id");
		TextField deviceTextField = new TextField(
				"0x" + Integer.toHexString(SimulatorService.DEFAULT_LOCAL_DEVICE_ID).toUpperCase());

		Button startButton = new Button("Start");
		startButton.setOnAction(actionEvent -> {
			try {
				this.simulator.stop();
				int deviceId = SimulatorService.DEFAULT_LOCAL_DEVICE_ID;
				int port = SimulatorService.DEFAULT_PORT;
				deviceId = Integer.decode(deviceTextField.getText().trim());
				port = Integer.decode(portTextField.getText().trim());
				this.simulator.setDeviceId(deviceId);
				this.simulator.setPort(port);
				this.simulator.init();
				this.simulator.start();
			} catch (Exception e) {
				this.writeStatusLog(e.toString());
			} finally {
				this.changeStatus();
			}

		});
		Button stopButton = new Button("Stop");
		stopButton.setOnAction(actionEvent -> {
			this.simulator.stop();
			this.changeStatus();
		});

		propsHBox.getChildren().addAll(portLabel, portTextField, deviceIdLabel, deviceTextField, startButton,
				stopButton);
		mainVBox.getChildren().add(propsHBox);

		this.statusLabel = new Label();
		mainVBox.getChildren().add(statusLabel);

		this.statusTextArea = new TextArea("");
		statusTextArea.setEditable(false);
		mainVBox.getChildren().add(statusTextArea);

		Scene scene = new Scene(mainVBox);
		primaryStage.setScene(scene);
		primaryStage.show();

		this.changeStatus();

	}

	/**
	 * Changes the connected or disconnected status via icon
	 */
	private void changeStatus() {
		Image statusImage = this.simulator.isRunning() ? this.connectedImage : this.disconnectedImage;
		this.statusLabel.setGraphic(new ImageView(statusImage));
		this.writeStatusLog(this.simulator.isRunning() ? "Running" : "Not Running");
	}

	/**
	 * Writes status log
	 */
	private void writeStatusLog(String text) {
		String previousText = this.statusTextArea.getText();
		String updatedText = text + "\n" + previousText;
		this.statusTextArea.setText(updatedText);
	}

	public static void main(String... args) {
		Application.launch(args);
	}

}
