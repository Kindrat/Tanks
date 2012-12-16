package ua.pirateparty.games.tanks.server;

import org.apache.log4j.PropertyConfigurator;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;
import ua.pirateparty.games.tanks.server.conf.ExternalConfigReader;
import ua.pirateparty.games.tanks.server.entities.lobby.Lobby;
import ua.pirateparty.games.tanks.server.pipeline.PipelineFactory;

import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static ua.pirateparty.games.tanks.util.log.Loggers.globalLogger;
import static ua.pirateparty.games.tanks.util.log.Loggers.resourceUsageLogger;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 15:20
 */

public class TankServer extends ExternalConfigReader{

    private static ExecutorService bossExecutor;
    private static ExecutorService workerExecutor;
    public static Lobby lobby;

    public static void main(String[] args){
        PropertyConfigurator.configure("log4j.properties");
        TankServer.initExecutors();
        TankServer.startGameServer();
        TankServer.startLogger();
        TankServer.initLobby();
    }

    private static void startGameServer(){
        ChannelFactory gameServer = new NioServerSocketChannelFactory(bossExecutor, workerExecutor, 4);
        PipelineFactory gamePipeline = new PipelineFactory();

        ServerBootstrap gameBootstrap = new ServerBootstrap(gameServer);
        gameBootstrap.setPipelineFactory(gamePipeline);
        gameBootstrap.setOption("backlog", backlog);
        gameBootstrap.setOption("connectTimeoutMillis", connectTimeoutMillis);
        gameBootstrap.setOption("child.tcpNoDelay", true);
        gameBootstrap.setOption("child.keepAlive", true);
        gameBootstrap.setOption("readWriteFair", true);

        try{
            gameBootstrap.bind(new InetSocketAddress(serverPort));
            globalLogger.info("Server started on port: " + serverPort);
        }catch(ChannelException e){
            globalLogger.error(e);
        }
    }

    private static void initExecutors()
    {
        bossExecutor = new OrderedMemoryAwareThreadPoolExecutor(1, 400000000, 2000000000, 60, TimeUnit.SECONDS);
        workerExecutor = new OrderedMemoryAwareThreadPoolExecutor(4, 400000000, 2000000000, 60, TimeUnit.SECONDS);
    }

    private static void startLogger()
    {
        Timer timer = new Timer();
        final int divider = 1048576;
        TimerTask timerTask = new TimerTask() {

            public void run()
            {
                int free = (int)(Runtime.getRuntime().freeMemory() / divider);
                int max = (int)(Runtime.getRuntime().maxMemory() / divider);
                int total = (int)(Runtime.getRuntime().totalMemory() / divider);
                int count = Thread.activeCount();
                String message = (new StringBuilder()).append("Resources used:   ").append(" Free memory: ").append(free).append(" Mb | ").append("Max memory: ").append(max).append(" Mb | ").append("Total memory: ").append(total).append(" Mb | ").append("Active threads: ").append(count).toString();
                resourceUsageLogger.info(message);
            }

        }
                ;
        timer.schedule(timerTask, 0, resourceUsageTimerPeriod);
    }

    private static void initLobby(){
        lobby = Lobby.getInstance();
    }
}
