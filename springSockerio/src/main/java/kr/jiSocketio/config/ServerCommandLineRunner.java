package kr.jiSocketio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import com.corundumstudio.socketio.SocketIOServer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ComponentScan
@Slf4j
@RequiredArgsConstructor
public class ServerCommandLineRunner implements CommandLineRunner{
	
	private final SocketIOServer server;
	
	@Override
	public void run(String... args) throws Exception{
		server.start();
	}

}
