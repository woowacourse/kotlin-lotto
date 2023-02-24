package view

interface InputViewInterface {
    fun getMoney(): Int
    fun getLottoNumbers(): Set<Int>
    fun getWinningLotto(): Set<Int>
    fun getBonusNumber(): Int
    fun getManualLottoCount(): Int
    fun getManualLotto(count: Int): List<Set<Int>>
}
