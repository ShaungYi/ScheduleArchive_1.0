package main.Controllers.LaunchScreen;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import main.App;
import main.Controllers.PrototypeController;
import main.Models.PastActivityArchiveModel;

public class LaunchScreen extends PrototypeController {
    @FXML
    Label logoPiece1;
    @FXML
    Label logoPiece2;

    @FXML
    Label loadingLabel;

    @FXML
    Label dot1;
    @FXML
    Label dot2;
    @FXML
    Label dot3;


    public void initialize(){
        new Thread(loadAllPastActivitiesRunnable).start();
        loadingAnimationThread.start();
        Font font = Font.font("Times New Roman", 200);
        logoPiece1.setFont(font);
        logoPiece2.setFont(font);

    }


    Runnable loadAllPastActivitiesRunnable = new Runnable() {
        @Override
        public void run() {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    loadingAnimationThread.stop();

                    Scene creator = App.sceneNavigationModel.loadNewScene("../resources/FXML/Creator/scheduleCreator.fxml", App.launchScreen);
                    App.scheduleCreator = creator;

                }
            });
        }
    };

    Runnable loadingAnimationRunnable = new Runnable() {
        @Override
        public void run() {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            double opacityIncrement = 0.001;
            while (loadingLabel.getOpacity() < 1){

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                double currentIncrement = opacityIncrement;

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        loadingLabel.setOpacity(loadingLabel.getOpacity() + currentIncrement);
                    }
                });
                opacityIncrement = opacityIncrement * 1.5;
//                System.out.println(loadingLabel.getOpacity());
            }
            loadingLabel.setOpacity(1);

            while (true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dot1.setOpacity(1);
                    }
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dot2.setOpacity(1);
                    }
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dot3.setOpacity(1);
                    }
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dot1.setOpacity(0);
                dot2.setOpacity(0);
                dot3.setOpacity(0);

            }

        }
    };

    Thread loadingAnimationThread = new Thread(loadingAnimationRunnable);

}