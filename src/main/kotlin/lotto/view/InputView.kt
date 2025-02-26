package lotto.view

class InputView {
    fun readNumber(): Int {
        val input: String = readUntilValid()
        val number: Int =
            input
                .trim()
                .toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
        return number
    }

    fun readNumbers(): List<Int> {
        val input: String = readUntilValid()
        val numbers: List<Int> =
            input
                .split(LOTTO_NUMBERS_DELIMITER)
                .map { number: String ->
                    number.trim().toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INVALID_LOTTO_NUMBERS)
                }
        return numbers
    }

    fun readLottosNumbers(size: Int): List<List<Int>> {
        if (size == 0) return emptyList()

        val lottoNumbers: List<List<Int>> = (1..size).map { readNumbers() }
        return lottoNumbers
    }

    private fun readUntilValid(): String {
        var input: String? = readLine()
        while (input == null) {
            println(ERROR_MESSAGE_INVALID_INPUT_STATE)
            input = readLine()
        }
        return input
    }

    companion object {
        private const val LOTTO_NUMBERS_DELIMITER = ","
        private const val ERROR_MESSAGE_INVALID_INPUT_STATE = "정상적으로 입력되지 않았습니다."
        private const val ERROR_MESSAGE_INPUT_NOT_A_NUMBER = "숫자를 입력해주세요."
        private const val ERROR_MESSAGE_INVALID_LOTTO_NUMBERS = "양식에 맞게 입력해주세요. 예시: 1, 2, 3, 4, 5, 6"
    }
}
