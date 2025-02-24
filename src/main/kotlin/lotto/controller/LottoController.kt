package lotto.controller

import lotto.controller.ViewMapper.mapToLottoCount
import lotto.controller.ViewMapper.mapToLottoResultsDescriptions
import lotto.controller.ViewMapper.mapToOutput
import lotto.controller.ViewMapper.mapToProfitRate
import lotto.controller.ViewMapper.mapToRandomLottos
import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.WinLotto
import lotto.view.View

class LottoController {
    private lateinit var winLotto: WinLotto
    private lateinit var boughtLottos: List<Lotto>

    fun start() {
        val pay: Int = View.readPay()
        View.showLottoCount(mapToLottoCount(pay))
        makeLotto(pay)
        showBoughtLottos()
        readWinningLotto()
        showResult()
    }

    private fun makeLotto(pay: Int) {
        val lottos: List<Lotto> = mapToRandomLottos(pay)
        boughtLottos = lottos
    }

    private fun showBoughtLottos() {
        View.showLottos(mapToOutput(boughtLottos))
    }

    private fun readWinningLotto() {
        val lotto = Lotto(View.readLottoNumbers())
        val bonusNumber = LottoNumber(View.readBonusNumber())
        winLotto = WinLotto(lotto, bonusNumber)
    }

    private fun showResult() {
        val lottoResultsDescriptions: List<String> = mapToLottoResultsDescriptions(boughtLottos, winLotto)
        val profitRate: Double = mapToProfitRate(boughtLottos, winLotto)
        View.showResult(lottoResultsDescriptions, profitRate)
    }
}
