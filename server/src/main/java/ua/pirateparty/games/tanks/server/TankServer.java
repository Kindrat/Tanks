package ua.pirateparty.games.tanks.server;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;
import ua.pirateparty.games.tanks.persistence.domain.Player;
import ua.pirateparty.games.tanks.server.entities.lobby.Lobby;
import ua.pirateparty.games.tanks.server.pipeline.PipelineFactory;
import ua.pirateparty.games.tanks.util.log.Loggers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static ua.pirateparty.games.tanks.util.log.Loggers.globalLogger;
import static ua.pirateparty.games.tanks.util.log.Loggers.resourceUsageLogger;

public class TankServer {

    private static ExecutorService bossExecutor;
    private static ExecutorService workerExecutor;
    private EntityManager manager;
    public static Lobby lobby;

    public TankServer(EntityManager manager) {
        this.manager = manager;
    }

    public static void main(String[] args) {
        //PropertyConfigurator.configure("log4j.properties");
        TankServer.initExecutors();
        TankServer.startGameServer();
        TankServer.startLogger();
        TankServer.initLobby();
        TankServer.testDatabase();
    }

    private static void testDatabase(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tanksPostgres");
        EntityManager manager = factory.createEntityManager();
        TankServer test = new TankServer(manager);

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            test.createEmployees();
        } catch (Exception e) {
            Loggers.globalLogger.error("TankServer.testDatabase\t"+e.getMessage());
        }
        tx.commit();

        test.listEmployees();

        System.out.println(".. done");
    }

    private static void startGameServer() {
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
            gameBootstrap.bind(new InetSocketAddress(40115));
            globalLogger.info("Server started on port: " + 40115);
        }catch(ChannelException e){
            globalLogger.error("TankServer.startGameServer\t"+e);
        }
    }

    private static void initExecutors() {
        bossExecutor = new OrderedMemoryAwareThreadPoolExecutor(1, 400000000, 2000000000, 60, TimeUnit.SECONDS);
        workerExecutor = new OrderedMemoryAwareThreadPoolExecutor(4, 400000000, 2000000000, 60, TimeUnit.SECONDS);
    }

    private static void startLogger() {
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

        };
        timer.schedule(timerTask, 0, 3000000);
    }

    private static void initLobby(){
        lobby = Lobby.getInstance();
    }

    private void createEmployees() {
        manager.persist(new Player());
    }


    private void listEmployees() {
        List<Player> resultList = manager.createQuery("Select a From Player a", Player.class).getResultList();
        System.out.println("num of players:" + resultList.size());
        for (Player next : resultList) {
            System.out.println("next player: " + next);
        }
    }
}
