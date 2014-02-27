package ua.pirateparty.games.tanks.server.logic.evaluator;

import ua.pirateparty.games.tanks.server.entities.player.Player;

import java.util.Map;

import static ua.pirateparty.games.tanks.server.conf.Constants.direction.*;
import static tanks.util.log.Loggers.globalLogger;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 17.09.12
 * Time: 1:14
 */

public class Validator {

    public boolean validateMoves (Player player, Map<Integer, Object> activities){

        for (Map.Entry<Integer, Object> activity: activities.entrySet()){
            switch (activity.getKey()){
                case 1:
                    activity.setValue(validateMove(activity.getValue()));
                    break;
                case 2:
                    //validateSearch(activity.getValue());
                    break;
                case 3:
                    //validateAttack(activity.getValue());
                    break;
                default:
                    activity.setValue(null);
                    break;
            }
        }
        return false;
    }

    private void validateAttack(Object value) {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void validateSearch(String[] value) {
        //To change body of created methods use File | Settings | File Templates.
    }

    private Integer validateMove(Object value) {
        try{
            Integer direction = Integer.valueOf(value.toString());
            switch (direction){
                case N:
                    return direction;
                case S:
                    return direction;
                case E:
                    return direction;
                case W:
                    return direction;
                case NE:
                    return direction;
                case NW:
                    return direction;
                case SE:
                    return direction;
                case SW:
                    return direction;
                default:
                    return null;
            }
        }catch (Exception e){
            globalLogger.error(e);
            return null;
        }
    }
}
