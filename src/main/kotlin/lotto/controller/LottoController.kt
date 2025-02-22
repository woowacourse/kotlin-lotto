package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoMachine
import lotto.domain.model.LottoWinningStats
import lotto.domain.model.WinningLotto
import lotto.domain.service.LottoCalculator
import lotto.domain.value.LottoPayInfo
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun runLotto() {
        val lottoPayInfo = getPayInfo()
        outputView.printLottoPurchaseQuantity(lottoPayInfo)
        val lottos = getLottoByPayInfo(lottoPayInfo)
        outputView.printLottos(lottos)
        val lottoWinningStats = getLottoWInningStats(lottos)
        outputView.printLottoStats(lottoWinningStats)
        outputView.printLottoEarningRate(lottoWinningStats.calculateEarningRate())
    }

    private fun getPayInfo(): LottoPayInfo {
        val lottoPurchaseAmount = inputView.readLottoPurchaseAmount()
        return LottoPayInfo(lottoPurchaseAmount)
    }

    private fun getLottoByPayInfo(payInfo: LottoPayInfo): List<Lotto> {
        val lottoMachine = LottoMachine()
        return lottoMachine.generateLottos(payInfo)
    }

    private fun getWinningLotto(): WinningLotto {
        val winningLottoWithoutBonus = inputView.readWinningLottoWithoutBonusNumber()
        return inputView.getWinningLottoWithBonusNumber(winningLottoWithoutBonus)
    }

    private fun getLottoWInningStats(lottos: List<Lotto>): LottoWinningStats {
        val winningLotto = getWinningLotto()
        val lottoCalculator = LottoCalculator(winningLotto, lottos)
        return lottoCalculator.calculateWinningStats()
    }
}
