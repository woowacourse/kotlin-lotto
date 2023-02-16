package view

private const val SEPARATOR = ","
private const val INPUT_NULL = "[ERROR] null 값이 들어왔습니다."

class InputView {
    fun inputMoney(): Int {
        return readLine()?.toIntOrNull() ?: throw IllegalArgumentException(INPUT_NULL)
    }

    fun inputWinningNumbers(): List<String> {
        return (readLine() ?: throw IllegalArgumentException(INPUT_NULL)).split(SEPARATOR)
    }

    fun inputBonusNumber(): Int {
        return readLine()?.toIntOrNull() ?: throw IllegalArgumentException(INPUT_NULL)
    }
}
