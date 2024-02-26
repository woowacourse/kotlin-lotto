package view

interface InputView {
    fun getPurchasePrice(): Int

    fun getWinningTicket(): List<Int>

    fun getBonusNumber(): Int
}
