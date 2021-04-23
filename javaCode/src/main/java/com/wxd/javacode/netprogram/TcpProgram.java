package com.wxd.javacode.netprogram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpProgram {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(6666);
        System.out.println("server is running...");
        for (; ; ) {
            Socket socket = ss.accept();
            System.out.println("connected from " + socket.getRemoteSocketAddress());
            Thread t = new Handler(socket);
            t.start();
        }
    }

    static class Handler extends Thread {
        Socket sock;

        public Handler(Socket sock) {
            this.sock = sock;
        }

        @Override
        public void run() {
            try (InputStream input = this.sock.getInputStream()) {
                try (OutputStream output = this.sock.getOutputStream()) {
                    handle(input, output);
                }
            } catch (Exception e) {
                try {
                    this.sock.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.out.println("client disconnected");
            }
        }
    }

    private static void handle(InputStream input, OutputStream output) throws IOException {
        var write = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        write.write("hello\n");
        write.flush();
        for (; ; ) {
            String s = reader.readLine();
            if (s.equals("bye")) {
                write.write("bye\n");
                write.flush();
                break;
            }
            write.write("ok:" + s + "\n");
            write.flush();
        }
    }

}
