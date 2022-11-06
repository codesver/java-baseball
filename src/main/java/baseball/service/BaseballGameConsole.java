package baseball.service;

import baseball.participant.Batter;
import baseball.participant.Emcee;
import baseball.participant.Pitcher;
import baseball.participant.Referee;
import baseball.result.Result;
import camp.nextstep.edu.missionutils.Console;

import static baseball.result.ResultType.EXACT_MATCH;

public class BaseballGameConsole {

    private final Referee referee = new Referee();
    private final Batter batter = new Batter();
    private final Emcee emcee = new Emcee();

    public void startWithPitcher(Pitcher pitcher) {
        emcee.showStartingGuide(pitcher.getName());
        boolean replay = true;
        while (replay) {
            playSingleGame(pitcher);
            replay = askReplaying();
        }
    }

    private void playSingleGame(Pitcher pitcher) {
        boolean strikeOut = false;
        String hitNumber = batter.readyToHitNumberBall();

        while (!strikeOut) {
            System.out.print("숫자를 입력해주세요. : ");
            String pitchNumber = pitcher.pitchNumberBall();
            referee.pitchIsBalk(pitchNumber);

            Result result = referee.resultOfPrediction(hitNumber, pitchNumber);
            strikeOut = result.getResultType() == EXACT_MATCH;

            emcee.showResultMessage(result.getResultType(), result.numberOfBall(), result.numberOfStrike());
        }
    }

    private boolean askReplaying() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String replay = Console.readLine();
        referee.validateReplaySelection(replay);
        return replay.equals("1");
    }
}
