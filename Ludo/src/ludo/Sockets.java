/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Shreyas
 */
public class Sockets extends Thread {
        Socket socket;
        DataOutputStream dos;
        DataInputStream dis;
        Server server;
        int id;
        public Sockets(Server server,Socket s,int i) {
            try {
                this.server=server;
                socket=s;
                dos=new DataOutputStream(s.getOutputStream());
                dis=new DataInputStream(s.getInputStream());
                id=i;
            }
            catch (Exception e) {
                
            }
        }
        public void write(String str) {
            try {
                dos.writeUTF(str);
                dos.flush();
            }
            catch (Exception e) {
                
            }
            
        }
        @Override
        public void run() {
            try {
                
                while (true) {
                    System.out.println("Started listening");
                    try {
                        String str=dis.readUTF();
                    System.out.println("Client "+id+" : "+str);
                    server.receive(str,id);
                    }catch (Exception e) {
                        
                    }
                    
                }
            }
            catch (Exception e) {
                
            }
        }
    }