package Chat;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import Utils.Colors;

public class Server extends Connection implements AutoCloseable {

    private DataInputStream disServer = null;
    private java.io.DataOutputStream dosServer = null;

    public Server(Utils.enumType type, String ip, int port) throws UnknownHostException, IOException {
        super(type, ip, port);
    }

    public void serverOn() {
        try {
            ps.printf(Colors.ANSI_BLUE + "Servidor iniciado. Esperando conexión en %s:%d\n" + Colors.ANSI_RESET, getIp(), getPort());

            sockC = sockS.accept();
            ps.printf(Colors.ANSI_GREEN + "Cliente conectado: %s - %s\n" + Colors.ANSI_RESET,
                    sockC.getInetAddress().getHostAddress(),
                    sockC.getInetAddress().getHostName()
            );

            disServer = new DataInputStream(new java.io.BufferedInputStream(sockC.getInputStream()));
            dosServer = new java.io.DataOutputStream(new java.io.BufferedOutputStream(sockC.getOutputStream()));


            File recibidosDir = new File("recibidos");
            if (!recibidosDir.exists()) {
                recibidosDir.mkdirs();
            }


            while (true) {
                boolean hasMore;
                try {
                    hasMore = disServer.readBoolean();
                } catch (IOException e) {
                    ps.println(Colors.ANSI_RED + "Conexión finalizada por el cliente." + Colors.ANSI_RESET);
                    break;
                }
                if (!hasMore) {
                    ps.println(Colors.ANSI_BLUE + "Cliente ha indicado que no hay más archivos. Cerrando conexión." + Colors.ANSI_RESET);
                    break;
                }

                String filename = disServer.readUTF();
                long filesize = disServer.readLong();

                ps.printf(Colors.ANSI_BLUE + "Recibiendo archivo: %s (%,d bytes)\n" + Colors.ANSI_RESET, filename, filesize);

                File outFile = new File(recibidosDir, filename);

                if (outFile.exists()) {
                    String base = filename;
                    int dot = filename.lastIndexOf('.');
                    String nameOnly = (dot > 0) ? filename.substring(0, dot) : filename;
                    String ext = (dot > 0) ? filename.substring(dot) : "";
                    int i = 1;
                    while (outFile.exists()) {
                        outFile = new File(recibidosDir, nameOnly + "_" + i + ext);
                        i++;
                    }
                }

                try (FileOutputStream fos = new FileOutputStream(outFile);
                     BufferedOutputStream bos = new BufferedOutputStream(fos)) {

                    byte[] buffer = new byte[4096];
                    long remaining = filesize;
                    while (remaining > 0) {
                        int read = disServer.read(buffer, 0, (int)Math.min(buffer.length, remaining));
                        if (read == -1) break;
                        bos.write(buffer, 0, read);
                        remaining -= read;
                    }
                    bos.flush();
                } catch (IOException ex) {
                    ps.println(Colors.ANSI_RED + "Error al escribir el archivo: " + ex.getMessage() + Colors.ANSI_RESET);

                    try {
                        dosServer.writeUTF("ERROR");
                        dosServer.flush();
                    } catch (IOException e) {}
                    continue;
                }

                ps.println(Colors.ANSI_GREEN + "Archivo recibido y guardado correctamente." + Colors.ANSI_RESET);

                dosServer.writeUTF("OK");
                dosServer.flush();
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            ps.println(Colors.ANSI_RED + "Error del servidor: " + ex.getMessage() + Colors.ANSI_RESET);
        } finally {
            try {
                if (disServer != null) disServer.close();
                if (dosServer != null) dosServer.close();
                if (sockC != null && !sockC.isClosed()) sockC.close();
                if (sockS != null && !sockS.isClosed()) sockS.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void close() throws IOException {
        if (disServer != null) disServer.close();
        if (dosServer != null) dosServer.close();
        if (sockC != null && !sockC.isClosed()) sockC.close();
        if (sockS != null && !sockS.isClosed()) sockS.close();
    }
}
