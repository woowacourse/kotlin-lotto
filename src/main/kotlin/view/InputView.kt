package view

object InputView {
    private const val SEPARATOR = ", "

    fun inputMoney(): Long {
        return readLine()?.toLongOrNull() ?: throw IllegalArgumentException("[Error] 1000원 단위의 금액으로 입력해야합니다.")
    }

    fun inputWinningNumbers(): List<String> {
        return (readLine() ?: throw IllegalArgumentException("[Error] null값이 입력되었습니다.")).split(SEPARATOR)
    }

    fun inputBonusNumber(): Int {
        return readLine()?.toIntOrNull() ?: throw IllegalArgumentException("[Error] 당첨번호와 겹치지않고 1 ~ 45까지의 숫자를 입력해주세요.")
    }
}
