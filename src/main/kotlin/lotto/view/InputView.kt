package lotto.view

object InputView {
    fun readPrice(): String {
        println("구입금액을 입력해 주세요.")
        return readlnOrNull().orEmpty()
    }

    fun readWinningNumbers(): List<String> {
        println("지난 주 당첨번호를 입력해 주세요.")
        return readlnOrNull().orEmpty().split(",").map { it.trim() }
    }

    fun readBonusNumber(): String {
        println("보너스 볼을 입력해 주세요.")
        return readlnOrNull().orEmpty()
    }
}
