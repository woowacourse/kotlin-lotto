package lotto.controller

import lotto.domain.*
import lotto.view.View

class LottoController {
    private lateinit var winningLotto: WinningLotto
    private lateinit var boughtLottos: Lottos

    fun run() {
        buyLottos()
        readWinningLotto()
        showResult()
    }

    private fun buyLottos() {
        val price: Int = View.readPrice()
        val lottoCount: Int = price / Lotto.PRICE
        val lottoNumbers: List<List<Int>> = List(lottoCount) { makeRandomNumbers() }
        val lottos: Lottos = convertLottos(lottoCount, lottoNumbers)
        boughtLottos = lottos
        View.showLottoCount(boughtLottos.value.size)
        View.showLottos(lottoNumbers.map { lottoNumber: List<Int> -> lottoNumber.sorted() })
    }

    private fun convertLottos(
        lottoCount: Int,
        lottoNumbers: List<List<Int>>,
    ): Lottos =
        Lottos.buy(
            count = lottoCount,
            lottos =
                lottoNumbers
                    .map { lottoNumber: List<Int> ->
                        Lotto(lottoNumber.map { number: Int -> LottoNumber(number) }.toSet())
                    }.toList(),
        )

    private fun readWinningLotto() {
        val lottoNumbers: List<Int> = View.readLottoNumbers()
        val lotto = Lotto(lottoNumbers.map { number: Int -> LottoNumber(number) }.toSet())
        val bonusNumber = LottoNumber(View.readBonusNumber())
        winningLotto = WinningLotto(lotto, bonusNumber)
    }

    private fun showResult() {
        val lottoResults: LottoResults = Lottos.getResult(winningLotto, boughtLottos)
        val resultTally = countResult(lottoResults)
        View.showResult(resultTally = resultTally, profitRate = lottoResults.getProfitRate())
    }

    private fun countResult(lottoResults: LottoResults): Map<LottoResult, Int> {
        val resultTally = LottoResult.entries.associateWith { 0 }.toMutableMap()
        lottoResults.value.forEach { lottoResult: LottoResult ->
            resultTally[lottoResult] = resultTally.getValue(lottoResult) + 1
        }
        return resultTally.toMap()
    }

    private fun makeRandomNumbers(): List<Int> = LottoNumber.RANGE.shuffled().subList(0, Lotto.SIZE)
}
