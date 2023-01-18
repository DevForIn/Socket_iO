package kr.jiSocketio.socket;

import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import kr.jiSocketio.msg.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SocketModule {

		private final SocketService socketService;
		private final SocketIOServer server;
		
		public SocketModule(SocketIOServer server, SocketService socketService) {
			this.socketService = socketService;
			this.server = server;
			server.addConnectListener(onConnected());
			server.addDisconnectListener(onDisconnected());
			server.addEventListener("send_message", Message.class, onChatReceived());
		}
		
	    private DataListener<Message> onChatReceived() {
	        return (senderClient, data, ackSender) -> {
	            log.info(data.toString());
	            socketService.sendMessage(data.getRoom(),"get_message", senderClient, data.getMessage());
	        };
	    }

	    private ConnectListener onConnected() {
	        return (client) -> {
	            String room = client.getHandshakeData().getSingleUrlParam("room");
	            client.joinRoom(room);
	            log.info("Socket ID[{}]  Connected to socket", client.getSessionId().toString());
	        };
	    }

	    private DisconnectListener onDisconnected() {
	        return client -> {
	            log.info("Client[{}] - Disconnected from socket", client.getSessionId().toString());
	        };
	    }
}
