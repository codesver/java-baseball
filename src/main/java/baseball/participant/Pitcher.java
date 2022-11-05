package baseball.participant;

import camp.nextstep.edu.missionutils.Console;

public class Pitcher {

    private final String name;

    public Pitcher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String predictNumber() {
        return Console.readLine();
    }
}