package presentation

class InputWinningNumbersView {

    fun getWinningNumber(): List<Int> {
        println(ENTER_WINNING_NUMBER)

        return readLine()?.split(SEPARATOR)?.mapNotNull { number ->
            number.toIntOrNull()
        } ?: emptyList()
    }

    fun getBonusNumber(): Int? {
        println(ENTER_BONUS_NUMBER)

        return readLine()?.toIntOrNull()
    }

    companion object {
        private const val ENTER_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요."
        private const val SEPARATOR = ","
        private const val ENTER_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    }
}
