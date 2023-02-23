package lotto.view

interface InputInterface {
    fun getPurchaseMoney(): Int
    fun getManualLottoCount(): Int
    fun getManualLottoNumbers(count: Int): List<List<Int>>
    fun getWinningLottoNumbers(): List<Int>
    fun getWinningBonusNumber(): Int
}
