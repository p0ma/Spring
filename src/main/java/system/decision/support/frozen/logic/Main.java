package system.decision.support.frozen.logic;

import org.springframework.stereotype.Component;

@Component
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.getClass().getPackage().getName());
        String str = main.getClass().getPackage().getName();
        int last = str.lastIndexOf('.');
        System.out.println(str.substring(last + 1, str.length()));

    }


}
