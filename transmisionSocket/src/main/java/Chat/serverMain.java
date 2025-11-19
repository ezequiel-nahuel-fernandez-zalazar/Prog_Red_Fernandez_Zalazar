package Chat;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class serverMain {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 5000;

        try ( Server server = new Server(Utils.enumType.SERVER, ip, port) ){
            server.setIp(ip);
            server.setPort(port);
            server.serverOn();
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(serverMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(serverMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
