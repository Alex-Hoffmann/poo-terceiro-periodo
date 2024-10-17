package ALEX_HOFFMANN.primeirob.projetopoo.projeto3;

import java.net.URI;
import java.util.Scanner;

@ClientEndpoint
public class ClienteWebSocket {

    private static Session session;

    @OnOpen
    public void onOpen(Session session) {
        ClienteWebSocket.session = session;
        System.out.println("Conectado ao servidor WebSocket.");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Mensagem recebida do servidor: " + message);
    }

    @OnClose
    public void onClose() {
        System.out.println("Conexão encerrada.");
    }

    // Conecta ao servidor WebSocket
    public static void conectarAoServidor(String uri) {
        Object ContainerProvider = null;
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            container.connectToServer(ClienteWebSocket.class, URI.create(uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enviarMensagem(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Conectando ao servidor... ");
        conectarAoServidor("ws://localhost:8080/chat");

        // Enviar mensagens do cliente para o servidor
        while (true) {
            System.out.print("Digite uma mensagem (ou 'sair' para encerrar): ");
            String message = scanner.nextLine();
            if ("sair".equalsIgnoreCase(message)) {
                break;
            }
            enviarMensagem(message);
        }

        scanner.close();
    }
}
