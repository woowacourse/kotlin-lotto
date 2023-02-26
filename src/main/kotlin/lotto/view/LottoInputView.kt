package lotto.view

interface LottoInputView {
    fun readMoney(): Int
    fun readManualLottoCount(): Int
    fun readManualLottoNumber(count: Int): List<List<Int>>
    fun readWinningLottoNumber(): List<Int>
    fun readBonusNumber(): Int
}
