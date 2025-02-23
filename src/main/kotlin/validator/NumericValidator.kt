package validator

import ERROR

class NumericValidator(val value: String) {
    init {
        require(value.isNotBlank()) { EMPTY_INPUT_ERROR }
        require(value.toIntOrNull() != null) { NOT_NUMERIC_ERROR }
    }

    companion object {
        private const val EMPTY_INPUT_ERROR = "$ERROR 빈 값을 입력하셨습니다."
        private const val NOT_NUMERIC_ERROR = "$ERROR 숫자를 입력해야 합니다."
    }
}
