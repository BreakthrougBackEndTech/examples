package feature18.inter;

/**
 * @author: zhegong
 **/
interface Formula {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}

