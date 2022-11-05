package baseball.service;

import baseball.participant.Computer;
import baseball.participant.Player;
import baseball.result.Result;
import baseball.result.ResultType;
import camp.nextstep.edu.missionutils.Console;

import static baseball.result.ResultType.*;

public class BaseballGameConsole {

    private final Validator validator = new Validator();
    private final Computer computer = new Computer();

    public void playGame(Player player) {
        startingGuide(player.getName());
        do {
            playSingleGame(player);
        } while (askReplaying());
    }

    private void startingGuide(String playerName) {
        System.out.println(playerName + "반갑습니다.");
        System.out.println("숫자 야구 게임을 시작합니다.");
    }

    private void playSingleGame(Player player) {
        boolean matchedAllNumber = false;
        String randomNumber = computer.selectRandomNumber();

        do {
            System.out.print("숫자를 입력해주세요. : ");
            String predictedNumber = player.predictNumber();
            validator.validatePrediction(predictedNumber);

            Result result = computer.resultOfPrediction(randomNumber, predictedNumber);
            matchedAllNumber = result.getResultType() == EXACT_MATCH;

            showResultMessage(result.getResultType(), result.numberOfBall(), result.numberOfStrike());
        } while (!matchedAllNumber);
    }

    private void showResultMessage(ResultType resultType, int numberOfBall, int numberOfStrike) {
        if (resultType == EXACT_MATCH)
            System.out.println("3스트라이크\n3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        else if (resultType == BALL_AND_STRIKE)
            System.out.println(numberOfBall + "볼 " + numberOfStrike + "스트라이크");
        else if (resultType == ONLY_STRIKE)
            System.out.println(numberOfStrike + "스트라이크");
        else if (resultType == ONLY_BALL)
            System.out.println(numberOfBall + "볼");
        else if (resultType == NOTHING)
            System.out.println("낫싱");
    }

    private boolean askReplaying() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String replay = Console.readLine();
        validator.validateReplaySelection(replay);
        return replay.equals("1");
    }
}