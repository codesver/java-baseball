package baseball;

public class Validator {

    public void validatePrediction(String predictedNumber) {
        if (!validateSize(predictedNumber) && !validateDigits(predictedNumber))
            throw new IllegalArgumentException("잘못된 입력값입니다.");
        // If false throw IllegalArgumentException
    }

    private boolean validateSize(String predictedNumber) {
        return predictedNumber.length() == 3;
    }

    private boolean validateDigits(String predictedNumber) {
        // Validate if predictedNumber is decimal number
        // Validate if digits of predictedNumber are all different
        return true;
    }
}
