package lotto.view

class InputView {
    fun readPurchaseAmount(): String {
        println("구입금액을 입력해 주세요.")
        return readln()
    }

    fun readWinningNumbers(): String {
        println("\n지난 주 당첨 번호를 입력해 주세요.")
        return readln()
    }

    fun readBonusNumber(): String {
        println("보너스 볼을 입력해 주세요.")
        return readln()
    }
}
