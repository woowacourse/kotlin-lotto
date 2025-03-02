package lotto.view

class InputView(
    private val onInvalidInput: (String?) -> Unit,
) {
    /**
     * @return null if input is not a number
     * **/
    fun readNumber(): Int? {
        val input: String = readUntilValid()
        val number: Int = input.trim().toIntOrNull() ?: return null
        return number
    }

    /**
     * @return null if any number in list is not a number
     * **/
    fun readNumbers(): List<Int>? {
        val input: String = readUntilValid()
        val numbers: List<Int> =
            input
                .split(LOTTO_NUMBERS_DELIMITER)
                .map { number: String -> number.trim().toIntOrNull() ?: return null }
        return numbers
    }

    /**
     * @return null if any number in list is not a number
     * **/
    fun readLottosNumbers(size: Int): List<List<Int>>? {
        if (size == 0) return emptyList()

        val lottoNumbers: List<List<Int>> = (1..size).map { readNumbers() ?: return null }
        return lottoNumbers
    }

    private fun readUntilValid(): String {
        var input: String? = readLine()
        while (input == null) {
            onInvalidInput(ERROR_MESSAGE_INVALID_INPUT_STATE)
            input = readLine()
        }
        return input
    }

    companion object {
        private const val LOTTO_NUMBERS_DELIMITER = ","
        private const val ERROR_MESSAGE_INVALID_INPUT_STATE = "정상적으로 입력되지 않았습니다."
    }
}
