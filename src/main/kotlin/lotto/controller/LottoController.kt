package lotto.controller

import lotto.model.Amount
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoNumbers
import lotto.model.PrizeCalculator
import lotto.model.Rank
import lotto.model.ValidationResult
import lotto.model.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    val lottoMachine = LottoMachine()
    lateinit var money: Amount

    fun run() {
        money = getAmount()
        val publishedLotto = publishLottoList(inputView.getManualCount()) + autoPublishLotto()
        val winningLotto =
            publishWinningLotto(
                validatePublishLotto(inputView.getWinningLotto()),
                validateBonusNumber(inputView.getBonusNumber()),
            )
        val prizeCalculator = PrizeCalculator(winningLotto, publishedLotto, money)
        showEarningRate(prizeCalculator)
    }

    private fun getAmount(): Amount = Amount(inputView.getMoney())

    private fun publishWinningLotto(
        numbers: LottoNumbers,
        bonus: LottoNumber,
    ): WinningLotto {
        if (WinningLotto.validation(numbers, bonus) == ValidationResult.Error.DuplicateError) {
            return publishWinningLotto(
                numbers,
                validateBonusNumber(inputView.getBonusNumberRepeat()),
            )
        }
        return WinningLotto(numbers, bonus)
    }

    private fun validatePublishLotto(input: List<Int>): LottoNumbers {
        val validationResult = LottoNumbers.validation(input)
        return when (validationResult) {
            ValidationResult.Error.NumberRangeError ->
                validatePublishLotto(
                    inputView.getManualLottoRepeat(),
                )

            ValidationResult.Error.DuplicateError ->
                validatePublishLotto(
                    inputView.getManualLottoRepeat(),
                )

            ValidationResult.Error.NumberSizeError ->
                validatePublishLotto(
                    inputView.getManualLottoRepeat(),
                )

            else -> LottoNumbers(input.map { it -> LottoNumber(it) })
        }
    }

    private fun publishLottoList(count: Int): List<Lotto> {
        val useMoney = Amount(count * LOTTO_PRIZE)
        if (money.validate(useMoney) == ValidationResult.Error.OverMoneyError) return publishLottoList(inputView.getManualCountRepeat())
        money = money.payMoney(useMoney)
        inputView.lottoInputMessage()
        var lottoList: MutableList<Lotto> = mutableListOf()
        repeat(count) {
            lottoList.add(Lotto(validatePublishLotto(inputView.getManualLotto())))
        }
        outputView.printManualLotto(count)
        return lottoList
    }

    private fun autoPublishLotto(): List<Lotto> {
        val publishedLotto = lottoMachine.publishAutoTickets(money)
        outputView.printPublishedLotto(publishedLotto)
        return publishedLotto
    }

    private fun validateBonusNumber(input: Int): LottoNumber {
        if (LottoNumber.validation(input) == ValidationResult.Error.NumberRangeError) {
            return validateBonusNumber(inputView.getBonusNumberRepeat())
        }
        return LottoNumber(input)
    }

    private fun showEarningRate(prizeCalculator: PrizeCalculator) {
        val result =
            prizeCalculator.rankCount
                .filter { it.key != Rank.MISS }
                .toList()
                .sortedBy { it.first.ordinal }
                .reversed()
                .toMap()
        val earningRate = prizeCalculator.calculateEarningRate()
        outputView.printPrize(result, earningRate)
    }

    companion object {
        const val LOTTO_PRIZE = 1000
    }
}
