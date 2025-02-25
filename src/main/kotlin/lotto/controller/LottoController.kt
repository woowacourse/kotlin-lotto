package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoGenerator
import lotto.domain.LottoNumber
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
        val totalQuantity: Int = price / Lotto.PRICE
        val manualQuantity: Int = View.readManualQuantity()
        val automaticQuantity: Int = totalQuantity - manualQuantity
        val lottoGenerator = LottoGenerator(manualQuantity, automaticQuantity)
        val manualLottos: Lottos = makeManualLottos(manualQuantity)
        val automaticLottos: Lottos = makeAutomaticLottos(automaticQuantity)
        View.showLottoCount(manualQuantity, automaticQuantity)
        val allLottos = Lottos(manualLottos.value + automaticLottos.value)
        View.showLottos(allLottos)
        return allLottos
    }

    fun makeManualLottos(quantity: Int): Lottos {
        View.requestManualNumbers()
        return Lottos(List(quantity) { Lotto(View.readManualNumbers().map(::LottoNumber).toSet()) })
    }

    fun makeAutomaticLottos(quantity: Int): Lottos {
        return Lottos(List(quantity) { Lotto(makeLottoNumbers()) })
    }

    private fun makeLottoNumbers(): Set<LottoNumber> = LottoNumber.RANGE.shuffled().subList(0, Lotto.SIZE).map(::LottoNumber).toSet()

    private fun readWinningLotto(): WinningLotto {
        val lottoNumbers: List<LottoNumber> = View.readWinningNumbers().map(::LottoNumber)
        val lotto = Lotto(lottoNumbers.toSet())
        val bonusNumber = LottoNumber(View.readBonusNumber())
        return WinningLotto(lotto, bonusNumber)
    }

    private fun showResult(
        winningLotto: WinningLotto,
        userLottos: Lottos,
    ) {
        val lottoResults: LottoResults = LottoResults.from(winningLotto, userLottos)
        val tally = lottoResults.getTally()
        val profitRate = lottoResults.getProfitRate()
        View.showResult(tally, profitRate)
    }
}
