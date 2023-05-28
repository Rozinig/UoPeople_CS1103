import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public class SimpleServer {
	
	private final static int LISTENING_PORT = 50505;
	

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
        }
        catch (Exception e) {
        	System.out.println(e);
            System.out.println("Failed to create listening socket.");
            return;
        }
        System.out.println("Listening on port " + LISTENING_PORT);
        try {
            while (true) {
                Socket connection = serverSocket.accept();
                System.out.println("\nConnection from "
                        + connection.getRemoteSocketAddress());
                ConnectionThread thread = new ConnectionThread(connection);
                        thread.start();
            }
        }
        catch (Exception e) {
            System.out.println("Server socket shut down unexpectedly!");
            System.out.println("Error: " + e);
            System.out.println("Exiting.");
        }
        serverSocket.close();
    }

	private static void handleConnection(Socket connection) {
		try {
			String rootDirectory = "/home/rozinig/Downloads/test";
			Scanner in = new Scanner(connection.getInputStream());
			String line = in.nextLine();
			String[] parts = line.split(" ");
			System.out.println(line);
			File file = new File(rootDirectory + parts[1]);
			System.out.println(file);
			if (!parts[0].equals("GET")) {
				sendErrorResponse(501, connection.getOutputStream());
			}
			else if (!parts[2].equals("HTTP/1.1") && !parts[2].equals("HTTP/1.0")) {
				sendErrorResponse(400, connection.getOutputStream());
			}
			else if (!file.exists()) {
				sendErrorResponse(404, connection.getOutputStream());
			}
			else if (file.isDirectory()) {
				sendErrorResponse(404, connection.getOutputStream());
			}
			else if (!file.canRead()) {
				sendErrorResponse(403, connection.getOutputStream());
			}
			else {
				PrintWriter out = new PrintWriter(connection.getOutputStream());
				out.print("HTTP/1.1 200 OK\r\n");
				out.print("Connection: close\r\n");
				out.print("Connection-Type: " + getMimeType(parts[1]) + "\r\n");
				out.print("Connection-Type: " + file.length() + "\r\n");
				out.print("\r\n");
				out.flush();
				sendFile(file, connection.getOutputStream());
				
			}
			
		}
		catch (Exception e) {
			System.out.println("Error while communicating with client: " + e);
		}
		finally {  // make SURE connection is closed before returning!
			try {
				connection.close();
			}
			catch (Exception e) {
			}
			System.out.println("Connection closed.");
		}
	}
	private static String getMimeType(String fileName) {
        int pos = fileName.lastIndexOf('.');
        if (pos < 0)  // no file extension in name
            return "x-application/x-unknown";
        String ext = fileName.substring(pos+1).toLowerCase();
        if (ext.equals("txt")) return "text/plain";
        else if (ext.equals("html")) return "text/html";
        else if (ext.equals("htm")) return "text/html";
        else if (ext.equals("css")) return "text/css";
        else if (ext.equals("js")) return "text/javascript";
        else if (ext.equals("java")) return "text/x-java";
        else if (ext.equals("jpeg")) return "image/jpeg";
        else if (ext.equals("jpg")) return "image/jpeg";
        else if (ext.equals("png")) return "image/png";
        else if (ext.equals("gif")) return "image/gif";
        else if (ext.equals("ico")) return "image/x-icon";
        else if (ext.equals("class")) return "application/java-vm";
        else if (ext.equals("jar")) return "application/java-archive";
        else if (ext.equals("zip")) return "application/zip";
        else if (ext.equals("xml")) return "application/xml";
        else if (ext.equals("xhtml")) return"application/xhtml+xml";
        else return "x-application/x-unknown";
           // Note:  x-application/x-unknown  is something made up;
           // it will probably make the browser offer to save the file.
     }

private static void sendFile(File file, OutputStream socketOut) throws
  IOException {
    InputStream in = new BufferedInputStream(new FileInputStream(file));
    OutputStream out = new BufferedOutputStream(socketOut);
    while (true) {
      int x = in.read(); // read one byte from file
      if (x < 0)
         break; // end of file reached
      out.write(x);  // write the byte to the socket
   }
   out.flush();
   in.close();
}

static void sendErrorResponse(int errorCode, OutputStream socketOut) {
	PrintWriter out = new PrintWriter(socketOut);
	System.out.println("There was an error "+ errorCode);
	if (errorCode == 404) {
	out.print("HTTP/1.1 404 Not Found\r\n");
	out.print("Connection: close\r\n");
	out.print("Content-Type: text/html\r\n");
	out.print("\r\n");
	out.print("<html><head><title>Error</title></head><body>\r\n");
	out.print("<h2>Error: 404 Not Found</h2>\r\n");
	out.print("<p>The resource that you requested does not exist on this server.</p>\r\n");
	out.print("</body></html>\r\n");
	out.print("\r\n");
	out.flush();
	}
	else if (errorCode == 403) {
		out.print("HTTP/1.1 403 Forbidden\r\n");
		out.print("Connection: close\r\n");
		out.print("Content-Type: text/html\r\n");
		out.print("\r\n");
		out.print("<html><head><title>Error</title></head><body>\r\n");
		out.print("<h2>Error: 403 Forbidden</h2>\r\n");
		out.print("<p>The resource that you requested is not able to be read.</p>\r\n");
		out.print("</body></html>\r\n");
		out.print("\r\n");
		out.flush();
		}
	else if (errorCode == 400) {
		out.print("HTTP/1.1 400 Bad Request\r\n");
		out.print("Connection: close\r\n");
		out.print("Content-Type: text/html\r\n");
		out.print("\r\n");
		out.print("<html><head><title>Error</title></head><body>\r\n");
		out.print("<h2>Error: 400 Bad Request</h2>\r\n");
		out.print("<p>There was an error in the HTTP communication</p>\r\n");
		out.print("</body></html>\r\n");
		out.print("\r\n");
		out.flush();
		}
	else if (errorCode == 501) {
		out.print("HTTP/1.1 501 Not Implemented\r\n");
		out.print("Connection: close\r\n");
		out.print("Content-Type: text/html\r\n");
		out.print("\r\n");
		out.print("<html><head><title>Error</title></head><body>\r\n");
		out.print("<h2>Error: 501 Not Implemented</h2>\r\n");
		out.print("<p>That HTTP method is not available.</p>\r\n");
		out.print("</body></html>\r\n");
		out.print("\r\n");
		out.flush();
		}
	else if (errorCode == 500) {
		out.print("HTTP/1.1 500 Internal Server Error\r\n");
		out.print("Connection: close\r\n");
		out.print("Content-Type: text/html\r\n");
		out.print("\r\n");
		out.print("<html><head><title>Error</title></head><body>\r\n");
		out.print("<h2>Error: 500 Internal Server Error</h2>\r\n");
		out.print("<p>There was a problem on our end.</p>\r\n");
		out.print("</body></html>\r\n");
		out.print("\r\n");
		out.flush();
		}
}
private static class ConnectionThread extends Thread {
    Socket connection;
    ConnectionThread(Socket connection) {
       this.connection = connection;
    }
    public void run() {
       handleConnection(connection);
    }
 }

}
