package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoResult
import lotto.domain.Lottos
import lotto.domain.WinningLotto
import lotto.view.View

class LottoController {
    private val view = View
    lateinit var winningLotto: WinningLotto
    lateinit var boughtLottos: Lottos

    fun buyLottos() {
        val price: Int = view.readPrice()
        val lottoNumbers: List<List<Int>> = List(price / Lotto.LOTTO_PRICE) { makeRandomNumbers(Lotto.LOTTO_SIZE) }
        val lottos: Lottos =
            Lottos.buy(
                price = price,
                lottos = lottoNumbers.map { lottoNumber: List<Int> -> Lotto(lottoNumber.toSet()) }.toSet(),
            )
        boughtLottos = lottos
        view.showLottos(lottos)
    }

    fun readWinningLotto() {
        winningLotto = view.readWinningLotto()
    }

    fun showResult() {
        val lottoResults: List<LottoResult> = boughtLottos.lottos.map { lotto -> LottoResult.from(winningLotto, lotto) }
        view.showResult(lottoResults = lottoResults, profitRate = lottoResults.getProfitRate)
    }

    private fun makeRandomNumbers(size: Int): List<Int> = (Lotto.LOTTO_NUMBER_MIN..Lotto.LOTTO_NUMBER_MAX).shuffled().subList(0, size)

    private val List<LottoResult>.getProfitRate: Double
        get() {
            val profit: Long = this.sumOf { lottoResult: LottoResult -> lottoResult.prizeAmount.toLong() }
            val profitRate = profit / (this.size * Lotto.LOTTO_PRICE).toDouble()
            return profitRate
        }
}
