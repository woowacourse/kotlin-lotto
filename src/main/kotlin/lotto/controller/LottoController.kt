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
        val quantity: Int = price / Lotto.PRICE
        val lottoNumbers: List<Set<LottoNumber>> = List(quantity) { makeLottoNumbers() }
        val lottos = Lottos(List(quantity) { Lotto(makeLottoNumbers()) })
        View.showLottoCount(lottos.value.size)
        View.showLottos(lottoNumbers.map { lottoNumber: Set<LottoNumber> -> lottoNumber })
        return lottos
    }

    private fun makeLottoNumbers(): Set<LottoNumber> =
        LottoNumber.RANGE.shuffled().subList(0, Lotto.SIZE).map { number: Int -> LottoNumber(number) }.toSet()

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
}
