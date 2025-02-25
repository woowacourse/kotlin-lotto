package lotto.controller

import lotto.model.Amount
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoNumbers
import lotto.model.PrizeCalculator
import lotto.model.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val amount = getAmount()
        val lottoMachine = LottoMachine(amount)
        val publishedLotto = manuallyPublishLotto(lottoMachine) + autoPublishLotto(lottoMachine)
        val winningLotto = WinningLotto(getWinningLotto(), getBonusNumber())
        val prizeCalculator = PrizeCalculator(winningLotto, publishedLotto, amount)
        showEarningRate(prizeCalculator)
    }

    private fun getAmount(): Amount = Amount(inputView.getMoney())

    private fun manuallyPublishLotto(lottoMachine: LottoMachine): List<Lotto> {
        val count = inputView.getManualCount()
        lottoMachine.useMoney(Amount(count * LOTTO_PRIZE))
        val lottoList = lottoMachine.publishManualLottoList(inputView.getManualLottoList(count))
        outputView.printManualLotto(count)
        return lottoList
    }

    private fun autoPublishLotto(lottoMachine: LottoMachine): List<Lotto> {
        val publishedLotto = lottoMachine.publishAutoTickets(Amount(LOTTO_PRIZE))
        outputView.printPublishedLotto(publishedLotto)
        return publishedLotto
    }

    private fun getWinningLotto(): LottoNumbers {
        val winningInput = inputView.getWinningLotto()
        return LottoNumbers(winningInput.map { number -> LottoNumber(number) })
    }

    private fun getBonusNumber(): LottoNumber = LottoNumber(inputView.getBonusNumber())

    private fun showEarningRate(prizeCalculator: PrizeCalculator) {
        val result = prizeCalculator.result
        val earningRate = prizeCalculator.calculateEarningRate()
        outputView.printPrize(result, earningRate)
    }

    companion object {
        const val LOTTO_PRIZE = 1000
    }
}
