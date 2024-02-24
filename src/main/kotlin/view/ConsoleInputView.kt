package view

class ConsoleInputView : InputView {
    override fun getPurchasePrice(): Int {
        println("구입금액을 입력해 주세요.")
        return readln().toInt()
    }

    override fun getWinningTicket(): List<Int> {
        println("지난 주 당첨 번호를 입력해 주세요.")
        return readln().split(",").map { it.trim().toInt() }
    }

    override fun getBonusNumber(): Int {
        println("보너스 볼을 입력해 주세요.")
        return readln().toInt()
    }
}
