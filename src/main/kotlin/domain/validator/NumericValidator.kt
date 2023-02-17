package domain.validator

class NumericValidator {
    fun validate(input: String): Int {
        require(input.toIntOrNull() != null) {
            NUMERIC_ERROR
        }

        return input.toInt()
    }

    companion object {
        private const val NUMERIC_ERROR = "[ERROR] 숫자가 아닌 입력은 허용하지 않습니다."
    }
}
