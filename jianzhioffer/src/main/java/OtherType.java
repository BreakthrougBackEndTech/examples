/**
 * @description:
 * @author: zhegong
 * @create: 2019-10-23 10:09
 **/
public class OtherType {
    public double Power(double base, int exponent) {
        if (Double.compare(base, 0.0) == 0) {
            return 0.0;
        }

        boolean isNegative = false;
        if(exponent < 0){
            isNegative = true;
            exponent = -exponent;
        }

        if(exponent == 0){
            return 1.0;
        }
        if(exponent == 1){
            return base;
        }

        double res = 1.0;
        for(int i=0; i< exponent; i++){
            res*=base;
        }

     /*   for(int i=0; i< exponent>>1; i++){
            res*=base;
        }

        res = res*res;

        if((exponent&1) != 0){
            res = res*base;
        }*/

        if(isNegative){
            res = 1.0/res;
        }

        return res;
    }
}

