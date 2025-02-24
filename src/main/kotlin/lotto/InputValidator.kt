package lotto

class InputValidator(
    input: String,
) {
    init {
        require(input.isNotBlank()) { ERROR_PURCHASE_BLANK_MESSAGE }
        require(input.toIntOrNull() != null) { ERROR_PURCHASE_IS_NOT_NUMBER_MESSAGE }
    }

    companion object {
        private const val ERROR_PURCHASE_BLANK_MESSAGE = "[ERROR] 빈 값을 입력하였습니다."
        private const val ERROR_PURCHASE_IS_NOT_NUMBER_MESSAGE = "[ERROR] 숫자 값을 입력해주세요."
    }
}
