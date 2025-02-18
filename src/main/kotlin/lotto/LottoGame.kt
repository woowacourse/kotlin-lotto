package lotto

class LottoGame(private val winningNumber: List<Int>) {
    fun getSameNumberCount(lottoNumber: List<Int>): Int {
        return winningNumber.intersect(lottoNumber).size
    }
}
