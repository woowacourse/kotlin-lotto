package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.LottoResults
import lotto.domain.Lottos
import lotto.domain.WinningLotto
import lotto.view.View

class LottoController {
    fun run() {
        val userLottos: Lottos = buyLottos()
        val winningLotto: WinningLotto = readWinningLotto()
        showResult(winningLotto, userLottos)
    }

    private fun buyLottos(): Lottos {
        val price: Int = View.readPrice()
        val lottoCount: Int = price / Lotto.PRICE
        val lottoNumbers: List<List<Int>> = List(lottoCount) { makeRandomNumbers() }
        val lottos: Lottos = convertLottos(lottoCount, lottoNumbers)
        View.showLottoCount(lottos.value.size)
        View.showLottos(lottoNumbers.map { lottoNumber: List<Int> -> lottoNumber.sorted() })
        return lottos
    }

    private fun convertLottos(
        lottoCount: Int,
        lottoNumbers: List<List<Int>>,
    ): Lottos =
        Lottos.buy(
            count = lottoCount,
            lottos =
                lottoNumbers.map { lottoNumber: List<Int> ->
                    Lotto(lottoNumber.map { number: Int -> LottoNumber(number) }.toSet())
                }.toList(),
        )

    private fun readWinningLotto(): WinningLotto {
        val lottoNumbers: List<Int> = View.readLottoNumbers()
        val lotto = Lotto(lottoNumbers.map { number: Int -> LottoNumber(number) }.toSet())
        val bonusNumber = LottoNumber(View.readBonusNumber())
        return WinningLotto(lotto, bonusNumber)
    }

    private fun showResult(
        winningLotto: WinningLotto,
        userLottos: Lottos,
    ) {
        val lottoResults: LottoResults = Lottos.getResult(winningLotto, userLottos)
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
