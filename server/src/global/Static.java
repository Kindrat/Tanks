package global;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 17:58
 */
public class Static {

    public static void outLn (String s){
        System.out.println(s);
    }

    public static interface direction{
        public static final int N = 1;
        public static final int S = 2;
        public static final int E = 3;
        public static final int W = 4;
        public static final int NE = 13;
        public static final int NW = 14;
        public static final int SE = 23;
        public static final int SW = 24;
    }
}
