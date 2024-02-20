package lotto.view

class InputView {
    fun readPrice(): String {
        println("구입금액을 입력해 주세요.")
        return readlnOrNull().orEmpty()
    }

    fun readWinningNumbers(): String {
        println("지난 주 당첨번호를 입력해 주세요.")
        return readlnOrNull().orEmpty()
    }

    fun readBonusNumber(): String {
        println("보너스 볼을 입력해 주세요.")
        return readlnOrNull().orEmpty()
    }
}
