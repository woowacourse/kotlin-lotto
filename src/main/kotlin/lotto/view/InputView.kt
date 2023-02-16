package lotto.view

object InputView {
    private const val ERROR_WRONG_INPUT = "입력값으로 Null 이 입력되었습니다."

    fun getUserInput(): String {
        return readlnOrNull()?.trim() ?: throw IllegalStateException(ERROR_WRONG_INPUT)
    }
}
