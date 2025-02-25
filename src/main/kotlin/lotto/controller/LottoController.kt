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
        val publishedLotto = validatePublishLottoList(lottoMachine) + autoPublishLotto(lottoMachine)
        val winningLotto = validateWinningLotto(getWinningLotto(), getBonusNumber())
        val prizeCalculator = PrizeCalculator(winningLotto, publishedLotto, amount)
        showEarningRate(prizeCalculator)
    }

    private fun getAmount(): Amount = Amount(inputView.getMoney())

    private fun validateWinningLotto(
        number: LottoNumbers?,
        bonus: LottoNumber?,
    ): WinningLotto {
        if (number == null || bonus == null) {
            outputView.inputWinningError()
            return validateWinningLotto(getWinningLotto(), getBonusNumber())
        }
        return WinningLotto(number, bonus)
    }

    private fun validatePublishLotto(lottoMachine: LottoMachine): Lotto {
        var lotto = lottoMachine.publishManualLotto(inputView.getManualLotto())
        while (lotto == null) {
            outputView.inputLottoError()
            lotto = lottoMachine.publishManualLotto(inputView.getManualLotto())
        }
        return lotto
    }

    private fun validatePublishLottoList(lottoMachine: LottoMachine): List<Lotto> {
        var count = inputView.getManualCount()
        while (lottoMachine.useMoney(Amount(count * LOTTO_PRIZE)) == false) {
            outputView.inputCountError()
            count = inputView.getManualCount()
        }
        val lottoList = mutableListOf<Lotto>()
        inputView.lottoInputMessage()
        for (i in 1..count) {
            lottoList.add(validatePublishLotto(lottoMachine))
        }
        outputView.printManualLotto(count)
        return lottoList
    }

    private fun autoPublishLotto(lottoMachine: LottoMachine): List<Lotto> {
        val publishedLotto = lottoMachine.publishAutoTickets(Amount(LOTTO_PRIZE))
        outputView.printPublishedLotto(publishedLotto)
        return publishedLotto
    }

    private fun getWinningLotto(): LottoNumbers? {
        val winningInput = inputView.getWinningLotto()
        return LottoNumbers.create(winningInput.mapNotNull { number -> LottoNumber.create(number) })
    }

    private fun getBonusNumber(): LottoNumber? = LottoNumber.create(inputView.getBonusNumber())

    private fun showEarningRate(prizeCalculator: PrizeCalculator) {
        val result = prizeCalculator.result
        val earningRate = prizeCalculator.calculateEarningRate()
        outputView.printPrize(result, earningRate)
    }

    companion object {
        const val LOTTO_PRIZE = 1000
    }
}
