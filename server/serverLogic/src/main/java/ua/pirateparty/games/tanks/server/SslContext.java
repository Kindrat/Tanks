package ua.pirateparty.games.tanks.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.Security;
import java.util.ResourceBundle;

public class SslContext {

   Logger logger = LoggerFactory.getLogger(SSLContext.class);

   private static final String PROTOCOL = "TLS";
   private static final SslContext INSTANCE = new SslContext();

   private final SSLContext _serverContext;

   /**
    * Returns the singleton instance for this class
    */
   public static SslContext getInstance() {
      return INSTANCE;
   }

   /**
    * Constructor for singleton
    */
   private SslContext() {
      SSLContext serverContext = null;
      try {
         // Key store (Server side certificate)
         String algorithm = Security.getProperty("ssl.KeyManagerFactory.algorithm");
         if (algorithm == null) {
            algorithm = "SunX509";
         }

         try {
            ResourceBundle rb = ResourceBundle.getBundle("application");

            KeyStore ks = KeyStore.getInstance("JKS");
            FileInputStream fin = new FileInputStream(rb.getString("keystore.filename"));
            ks.load(fin, rb.getString("keystore.password").toCharArray());

            // Set up key manager factory to use our key store
            // Assume key password is the same as the key store file
            // password
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
            kmf.init(ks, rb.getString("keystore.password").toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(algorithm);
            tmf.init(ks);

            // Initialise the SSLContext to work with our key managers.
            serverContext = SSLContext.getInstance(PROTOCOL);
            serverContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
         } catch (Exception e) {
            throw new Error("SslContext. Failed to initialize the server-side SSLContext\t", e);
         }
      } catch (Exception ex) {
         logger.error("SslContext. Ssl initialisation failed\t" + ex.getMessage());
         System.exit(1);
      } finally {
         _serverContext = serverContext;
      }
   }

   /**
    * Returns the server context with server side key store
    */
   public SSLContext getServerContext() {
      return _serverContext;
   }
}
