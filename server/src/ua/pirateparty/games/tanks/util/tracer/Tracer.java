package ua.pirateparty.games.tanks.util.tracer;

import ua.pirateparty.games.tanks.commands.Command;
import ua.pirateparty.games.tanks.response.Response;

import static ua.pirateparty.games.tanks.global.Config.DEBUG;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 17.10.12
 * Time: 20:52
 */
public class Tracer {
    public static void trace(StringBuilder query){
        if (DEBUG)
            System.out.println("DB query >> "+query.toString());
    }

    public static void trace(Command command){
        if (DEBUG)
            System.out.println("Command >> "+command.toString());
    }

    public static void trace(Response response){
        if (DEBUG)
            System.out.println("Response >> "+response.toString());
    }

    public static void trace(String s){
        if (DEBUG)
            System.out.println("Debug String : "+s);
    }
}
