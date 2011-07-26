

/**
*
* @author keruspe
*/
public class gotoByKerups {

    public static void main(String[] args) {
        Integer loop = 0;
        while (true) {
            A: {
                System.out.println("Yay, here is label A");
                if (++loop < 4)
                    break A;
                System.out.println("3 goto are enough !");
                break;
            }
        }
    }

}