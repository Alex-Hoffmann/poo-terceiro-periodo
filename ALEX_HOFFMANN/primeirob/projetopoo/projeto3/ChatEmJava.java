package ALEX_HOFFMANN.primeirob.projetopoo.projeto3;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/chat")
public class ChatEmJava {

    // Armazena todas as sessões conectadas
    private static Set<Session> clientes = Collections.<Session>synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        clientes.add(session);
        System.out.println("Nova conexão: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Mensagem recebida de " + session.getId() + ": " + message);
        // Envia a mensagem para todos os clientes conectados
        synchronized (clientes) {
            for (Session cliente : clientes) {
                if (cliente.isOpen()) {
                    cliente.getBasicRemote().sendText(message);
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        clientes.remove(session);
        System.out.println("Conexão fechada: " + session.getId());
    }
}
