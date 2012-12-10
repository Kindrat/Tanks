/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pirateparty.games.tanks.client;

import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import ua.pirateparty.games.tanks.client.pipeline.AmfClientPipelineFactory;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import ua.pirateparty.games.tanks.protocols.amf.commands.login.CPing;

/**
 *
 * @author legioner
 */
public class TanksClient extends javax.swing.JFrame {
    
    public static Channel channel;

    public TanksClient() {
        initComponents();
        connectToServer();        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jTextField1 = new javax.swing.JTextField();
        jConsoleScroll = new javax.swing.JScrollPane();
        jConsole = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuComPing = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jConsoleScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jConsoleScroll.setAutoscrolls(true);

        jConsole.setEditable(false);
        jConsole.setBackground(new java.awt.Color(102, 102, 102));
        jConsole.setColumns(20);
        jConsole.setForeground(new java.awt.Color(102, 255, 0));
        jConsole.setRows(5);
        jConsole.setCaretColor(new java.awt.Color(102, 102, 102));
        jConsole.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jConsole.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jConsole.setDisabledTextColor(new java.awt.Color(51, 255, 0));
        jConsole.setFocusable(false);
        jConsole.setOpaque(false);
        jConsole.setRequestFocusEnabled(false);
        jConsole.setSelectedTextColor(new java.awt.Color(102, 255, 0));
        jConsole.setSelectionColor(new java.awt.Color(102, 102, 102));
        jConsole.setVerifyInputWhenFocusTarget(false);
        jConsoleScroll.setViewportView(jConsole);

        jMenu1.setText("Меню");

        jMenuItem1.setText("jMenuItem1");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Инфо");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Комманды");

        jMenuComPing.setText("Ping");
        jMenuComPing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuComPingActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuComPing);

        jMenuItem3.setText("Login");
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jConsoleScroll))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jConsoleScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuComPingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuComPingActionPerformed
        channel.write(new CPing());
    }//GEN-LAST:event_jMenuComPingActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TanksClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TanksClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TanksClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TanksClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TanksClient().setVisible(true);
            }
        });
    }
    
    private static void connectToServer(){
        ChannelFactory factory = new NioClientSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool());

        ClientBootstrap bootstrap = new ClientBootstrap(factory);

        ChannelPipelineFactory pipelineFactory = new AmfClientPipelineFactory();        

        bootstrap.setPipelineFactory(pipelineFactory);

        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
        bootstrap.setOption("sendBufferSize", 65536);
        bootstrap.setOption("receiveBufferSize", 65536);
        
        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 20202));
        channel = channelFuture.awaitUninterruptibly().getChannel();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea jConsole;
    private javax.swing.JScrollPane jConsoleScroll;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuComPing;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
