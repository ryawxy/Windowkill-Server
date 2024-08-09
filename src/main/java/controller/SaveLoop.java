package controller;

import model.data.ClientData;
import myProject.MyProject;

public class SaveLoop extends Thread{


    @Override
    public void run(){


     //   ClientData.loadData();
        MyProject.getInstance().getDatabase().getSquadData().load();
            double timePerFrame = 1000000000.0 / 120;
            double timePerUpdate = 1000000000.0 / 120;

            long previousTime = System.nanoTime();

            int frames = 0;
            int updates = 0;
            long lastCheck = System.currentTimeMillis();

            double deltaUpdate = 0;
            double deltaFrame = 0;

            while (true) {

                long currentTime = System.nanoTime();

                deltaUpdate += (currentTime - previousTime) / timePerUpdate;
                deltaFrame += (currentTime - previousTime) / timePerFrame;

                previousTime = currentTime;


                if (deltaUpdate >= 1) {

                    updates++;
                    deltaUpdate--;
                }

                if (deltaFrame >= 1) {

                    for(ClientData clientData : MyProject.getInstance().getDatabase().getClientsData()) clientData.saveData();
             //       MyProject.getInstance().getDatabase().getSquadData().save();
                frames++;
                deltaFrame--;
            }

        if (System.currentTimeMillis() - lastCheck >= 1000) {
            lastCheck = System.currentTimeMillis();

            frames = 0;
            updates = 0;
        }
    }
}
}
