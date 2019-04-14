package com.briup.webServer.v1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/*
 * Handler	操纵者管理者
 */
public class ServerHandler extends Thread {
    Socket socket = null;
    BufferedReader in = null;
    PrintStream out = null;
    FileInputStream fis = null;
    String method = "";
    String resource = "";
    String storage = "files";

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
            String requestLine = in.readLine();
            System.out.println("成功读取：" + requestLine);
            if (!"".equals(requestLine) && requestLine != null) {
                String[] infos = requestLine.split(" ");
                method = infos[0];
                resource = infos[1];
                System.out.println(infos[1]);
                //假定 资源名是 xxx.bean
                if (resource.contains(".bean")) {
                    if (method.toUpperCase().equals("GET")) {

                    } else if (method.toUpperCase().equals("POST")) {

                    }
                } else {
                    if (resource.equals("/")) {
                        resource = "/index.html";
                        String responseLine = "HTTP/1.1 200 OK";
                    }
                    File file = new File(storage + resource);
                    if (!file.exists()) {
                        file = new File(storage + "/error.html");
                        String responseLine = "HTTP/1.1 200 OK";
                    }
                    fis = new FileInputStream(file);
                    byte[] temp = new byte[1024];
                    while (fis.read(temp) != -1) {
                        out.write(temp);
                        out.flush();
                    }
                    out.close();
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
