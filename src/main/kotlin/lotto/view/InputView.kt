package lotto.view

class InputView {
    fun readLottoPurchaseAmount(): Int = readln().toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_A_NUMBER)

    companion object {
        private const val ERROR_NOT_A_NUMBER = "숫자만 입력 가능합니다."
    }
}
