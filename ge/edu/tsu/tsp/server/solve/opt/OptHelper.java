package ge.edu.tsu.tsp.server.solve.opt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptHelper {

    public static List<Integer> getRandomRoute(int n) {
        List<Integer> route = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            route.add(i);
        }
        Collections.shuffle(route);
        route.add(route.get(0));
        return route;
    }

    public static void swap(int x, int y, List<Integer> list) {
        int tmp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, tmp);
    }
}
