package ua.pirateparty.games.tanks.server;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import ua.pirateparty.games.tanks.server.pipeline.PipelineFactory;

import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static ua.pirateparty.games.tanks.global.Static.outLn;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 15:20
 */

public class TankServer {

    private static ExecutorService bossExecutor;
    private static ExecutorService workerExecutor;

    private static final int gameServerPort = 30215;

    public static void main(String[] args){
        System.setErr(System.out);
        TankServer.initExecutors();
        TankServer.startGameServer();
        TankServer.startLogger();
    }

    private static void startGameServer(){
        ChannelFactory gameServer = new NioServerSocketChannelFactory(bossExecutor, workerExecutor, 4);
        PipelineFactory gamePipeline = new PipelineFactory();

        ServerBootstrap gameBootstrap = new ServerBootstrap(gameServer);
        gameBootstrap.setPipelineFactory(gamePipeline);
        gameBootstrap.setOption("backlog", 500);
        gameBootstrap.setOption("connectTimeoutMillis", 10000);
        gameBootstrap.setOption("child.tcpNoDelay", true);
        gameBootstrap.setOption("child.keepAlive", true);
        gameBootstrap.setOption("readWriteFair", true);


        try{
            gameBootstrap.bind(new InetSocketAddress(gameServerPort));
            outLn("==============================");
            outLn("Server started on port: "+gameServerPort);
            outLn("==============================");
        }catch(ChannelException e){
            e.printStackTrace();
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
                String dateTime = DateTime.now().toString(DateTimeFormat.mediumDateTime());
                String s = (new StringBuilder()).append(dateTime).append("\nFree memory: ").append(free).append(" Mb\n").append("Max memory: ").append(max).append(" Mb\n").append("Total memory: ").append(total).append(" Mb\n").append("Active threads: ").append(count).append("\n").toString();
                outLn(s);
            }

        }
                ;
        long period = 600000;
        timer.schedule(timerTask, 0, period);
    }
}
