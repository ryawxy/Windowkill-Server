package model.networkCommunication.Message;

import model.enums.MessageType;
import myProject.Main;

public class WaveChangerMessage extends Message {
    int wave;
    public WaveChangerMessage() {
        setMessageType(MessageType.CHANGE_WAVE);
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }
}
