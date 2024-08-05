package controller.network.MessageHandler;

import model.enums.MessageType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageHandlerCreator {
    private static MessageHandlerCreator instance;
    private MessageHandlerCreator(){

    }
    public static MessageHandlerCreator getInstance(){
        if(instance == null) instance = new MessageHandlerCreator();
        return instance;
    }
    public Map<MessageType,MessageHandler> createMessageHandlerMap(){
        Map<MessageType,MessageHandler> messageHandlerMap = new ConcurrentHashMap<>();
        messageHandlerMap.put(MessageType.SIGN_UP,new SignUpHandler());
        messageHandlerMap.put(MessageType.NEW_SQUAD,new NewSquadHandler());
        messageHandlerMap.put(MessageType.SHOW_SQUADS,new ShowSquadsHandler());
        messageHandlerMap.put(MessageType.JOIN_SQUAD,new JoinSquadRequestHandler());
        messageHandlerMap.put(MessageType.REQUEST_TO_ADMIN,new RequestToAdminHandler());
        messageHandlerMap.put(MessageType.LEAVE_SQUAD,new LeaveSquadHandler());
        messageHandlerMap.put(MessageType.SQUAD_DATA,new SquadDataHandler());
        messageHandlerMap.put(MessageType.SERVER_CONNECTION,new ServerConnectionHandler());
        messageHandlerMap.put(MessageType.REMOVE_MEMBER,new RemoveMemberHandler());
        messageHandlerMap.put(MessageType.PAY_TO_VAULT,new PayToVaultHandler());
        messageHandlerMap.put(MessageType.INIT,new InitHandler());
        messageHandlerMap.put(MessageType.START_BATTLE,new StartBattleHandler());
        messageHandlerMap.put(MessageType.DISCONNECTION,new DisconnectionHandler());
        messageHandlerMap.put(MessageType.BATTLE_REQUEST,new BattleRequestHandler());
        messageHandlerMap.put(MessageType.ANSWER_TO_BATTLE_REQUEST,new AnswerToBattleRequestHandler());
        messageHandlerMap.put(MessageType.START_GAME,new StartGameHandler());
        messageHandlerMap.put(MessageType.CHANGE_STATE,new ChangeStateHandler());
        messageHandlerMap.put(MessageType.CHANGE_USER_DATA,new ChangeUserDataHandler());
        messageHandlerMap.put(MessageType.SUMMON,new SummonHandler());
        messageHandlerMap.put(MessageType.ANSWER_TO_SUMMON,new AnswerToSummonHandler());
        messageHandlerMap.put(MessageType.PAUSE_GAME,new PauseGameHandler());
        messageHandlerMap.put(MessageType.BUY_ITEM,new BuyItemHandler());
        messageHandlerMap.put(MessageType.END_GAME,new EndGameHandler());
        messageHandlerMap.put(MessageType.END_BATTLE,new EndBattleHandler());
        return messageHandlerMap;
    }
}
