package com.kpaudel.bacnet.views;

import com.kpaudel.bacnet.SimulatorService;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimulatorApplication extends Application {
	private static final String TITLE = "BACnet Simulator";

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(TITLE);

		VBox mainVBox = new VBox(10);
		HBox propsHBox = new HBox(10);
		Label portLabel = new Label("Port");
		TextField portTextField = new TextField(Integer.toHexString(SimulatorService.DEFAULT_PORT).toUpperCase());
		Label deviceId = new Label("Device Id");
		TextField deviceTextField = new TextField(
				Integer.toHexString(SimulatorService.DEFAULT_LOCAL_DEVICE_ID).toUpperCase());

		Button startButton = new Button("Start");
		Button stopButton = new Button("Stop");

		propsHBox.getChildren().addAll(portLabel, portTextField, deviceId, deviceTextField, startButton, stopButton);

		mainVBox.getChildren().add(propsHBox);

		Scene scene = new Scene(mainVBox);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String... args) {
		Application.launch(args);
	}

}
