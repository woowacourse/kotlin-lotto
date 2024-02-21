package lotto.view

object InputView {
    fun getPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")
        val input = readln()
        return input.toInt()
    }

    fun getWinningNumbers(): List<Int> {
        println("지난 주 당첨 번호를 입력해 주세요.")
        val input = readln()
        return input.split(",").map { it.trim().toInt() }
    }

    fun getBonusNumber(): Int {
        println("보너스 번호를 입력해 주세요.")
        val input = readln()
        return input.toInt()
    }
}
