package lotto.view

object InputView {
    private const val ERROR_MESSAGE_ONLY_NUMBER = "숫자로만 이루어져야 합니다"
    private const val ERROR_MESSAGE_SPLIT_BY_COMMA = "숫자는 콤마로 구별되어야 합니다"
    private const val ERROR_MESSAGE_SPLIT_ONLY_NUMBER = "구분된 입력은 숫자로만 이루어져야 합니다"

    fun readInt(): Int {
        val input = readln()
        require(input.toIntOrNull() != null) { ERROR_MESSAGE_ONLY_NUMBER }
        return input.toInt()
    }

    fun readIntList(): List<Int> {
        val input = readln()
        require(input.contains(",")) { ERROR_MESSAGE_SPLIT_BY_COMMA }
        val splittedInput = input.split(",").map { it.trim() }
        require(splittedInput.all { it.toIntOrNull() != null }) { ERROR_MESSAGE_SPLIT_ONLY_NUMBER }
        return splittedInput.map { it.toInt() }
    }
}
