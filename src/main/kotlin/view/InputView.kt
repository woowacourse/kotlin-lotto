package view

private const val SEPARATOR = ","
private const val INPUT_NULL = "[ERROR] null 값이 들어왔습니다. \n다시 입력해주세요"

class InputView {
    fun inputMoney(): Int {
        val input = runCatching {
            readLine()?.toInt()
        }.getOrNull()
        if (input == null) {
            println(INPUT_NULL)
            return inputMoney()
        }
        return input
    }

    fun inputWinningNumbers(): List<String> {
        return (readLine() ?: throw IllegalArgumentException(INPUT_NULL)).split(SEPARATOR)
    }

    fun inputBonusNumber(): Int {
        return readLine()?.toIntOrNull() ?: throw IllegalArgumentException(INPUT_NULL)
    }
}
