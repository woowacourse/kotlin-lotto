package domain.validator

class NumericValidator {
    fun validate(input: String): Int {
        require(input.isNumeric()) {
            NUMERIC_ERROR
        }

        return input.toInt()
    }

    private fun String.isNumeric(): Boolean =
        this.chars().allMatch { char ->
            Character.isDigit(char)
        }

    companion object {
        private const val NUMERIC_ERROR = "[ERROR] 숫자가 아닌 입력은 허용하지 않습니다."
    }
}
