package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.LottoShop
import lotto.domain.WinLotto
import lotto.view.View

class LottoController {
    val view = View()
    val viewMapper = ViewMapper()

    fun start() {
        runCatching(::run).onFailure { error: Throwable ->
            view.showError(error)
            start()
        }
    }

    private fun run() {
        val lottoShop: LottoShop = createLottoShop()
        val boughtLottos: List<Lotto> = buyLottos(lottoShop)
        val winLotto: WinLotto = createWinLotto()
        showResult(boughtLottos, winLotto)
    }

    private fun buyLottos(lottoShop: LottoShop): List<Lotto> {
        val manualLottos: List<List<Int>> = view.readManualLottosNumbers(lottoShop.manualCount)
        view.showLottoCount(lottoShop.manualCount, lottoShop.randomCount)
        val boughtLottos: List<Lotto> = lottoShop.buyLottos(manualLottos)
        view.showLottos(viewMapper.mapToOutput(boughtLottos))
        return boughtLottos
    }

    private fun createLottoShop(): LottoShop {
        val pay: Int = view.readPay()
        val manualLottoCount: Int = view.readManualLottoCount()
        return LottoShop(pay, manualLottoCount)
    }

    private fun createWinLotto(): WinLotto {
        val lotto = Lotto(view.readLottoNumbers())
        val bonusNumber = LottoNumber(view.readBonusNumber())
        return WinLotto(lotto, bonusNumber)
    }

    private fun showResult(
        boughtLottos: List<Lotto>,
        winLotto: WinLotto,
    ) {
        val lottoResultsDescriptions: List<String> = viewMapper.mapToLottoResultsDescriptions(boughtLottos, winLotto)
        val profitRate: Double = viewMapper.mapToProfitRate(boughtLottos, winLotto)
        view.showResult(lottoResultsDescriptions, profitRate)
    }
}
