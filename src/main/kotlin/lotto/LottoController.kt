package lotto

import lotto.domain.Bank
import lotto.domain.Lotto
import lotto.domain.LottoBunch
import lotto.domain.LottoNumber
import lotto.domain.PurchaseMoney
import lotto.domain.Validator.ERROR_INPUT_HANDLER
import lotto.domain.WinningLotto
import lotto.domain.WinningResult
import lotto.domain.factory.LottoFactory
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(private val lottoFactory: LottoFactory) {
    fun runLotto() {
        val purchaseMoney = getPurchaseMoney()
        val lottoBunch = getLottoBunch(purchaseMoney.getPurchaseCount())
        val winningLotto = getWinningLotto()
        confirmLottoWinning(purchaseMoney = purchaseMoney, lottoBunch = lottoBunch, winningLotto = winningLotto)
    }

    fun getLottoBunch(purchaseCount: Int): LottoBunch {
        val lottoes = mutableListOf<Lotto>()
        repeat(purchaseCount) {
            lottoes.add(lottoFactory.createLotto())
        }

        val lottoBunch = LottoBunch(lottoes)
        OutputView.printPurchaseResult(lottoBunch.toString(), purchaseCount)
        return lottoBunch
    }

    private fun getPurchaseMoney(): PurchaseMoney {
        return runCatching {
            PurchaseMoney(InputView.getPurchaseMoney())
        }.getOrElse { error ->
            inputErrorHandler(error, ::getPurchaseMoney) as PurchaseMoney
        }
    }

    private fun getMainLottoNumber(): List<LottoNumber> {
        return kotlin.runCatching {
            InputView.getMainLottoNumbers().map { LottoNumber(it) }
        }.getOrElse { error ->
            inputErrorHandler(error, ::getMainLottoNumber) as List<LottoNumber>
        }
    }

    private fun getBonusLottoNumber(): LottoNumber {
        return runCatching {
            LottoNumber(InputView.getBonusLottoNumber())
        }.getOrElse { error ->
            inputErrorHandler(error, ::getBonusLottoNumber) as LottoNumber
        }
    }

    fun getWinningLotto(): WinningLotto {
        return runCatching {
            WinningLotto(getMainLottoNumber(), getBonusLottoNumber())
        }.getOrElse { error ->
            inputErrorHandler(error, ::getWinningLotto) as WinningLotto
        }
    }

    private fun inputErrorHandler(
        error: Throwable,
        repeatFunction: () -> Any,
    ): Any {
        return when (error) {
            is IllegalArgumentException -> inputIllegalArgumentExceptionHandler(error, repeatFunction)
            else -> throw IllegalStateException(ERROR_INPUT_HANDLER)
        }
    }

    private fun inputIllegalArgumentExceptionHandler(
        error: Throwable,
        repeatFunction: () -> Any,
    ): Any {
        println(error.message)
        return repeatFunction()
    }

    private fun confirmLottoWinning(lottoBunch: LottoBunch, winningLotto: WinningLotto, purchaseMoney: PurchaseMoney) {
        OutputView.printWinningStatsScript()
        val ranks = lottoBunch.value.map { Bank.getRank(it, winningLotto) }
        val winningResult = WinningResult.from(ranks)
        OutputView.printWinningResult(winningResult.toString())

        OutputView.printYieldRate(Bank.getYieldRate(purchaseMoney, Bank.sumTotalPrizeMoney(lottoBunch, winningLotto)))
    }
}
