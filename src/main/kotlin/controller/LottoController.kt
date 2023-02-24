package controller

import domain.Lotto
import domain.LottoPurchaseInfo
import domain.LottoSeller
import domain.LottoStatistics
import domain.PurchaseLottoMoney
import domain.Ticket
import domain.WinningLotto
import view.InputViewInterface
import view.OutputViewInterface

class LottoController(
    private val inputView: InputViewInterface,
    private val outputView: OutputViewInterface
) : Runnable {
    private val lottoSeller: LottoSeller by lazy { LottoSeller() }

    override fun run() {
        outputView.printMessage(INPUT_MESSAGE_FOR_PURCHASE_MONEY)
        val purchaseLottoMoney = getMoney()
        val ticket = initializeTicket(purchaseLottoMoney)
        val winningLotto = getWinningLotto()
        printResult(winningLotto, ticket, purchaseLottoMoney)
    }

    private fun getMoney(): PurchaseLottoMoney {
        return inputView.askMoney() ?: getMoney()
    }

    private fun initializeTicket(purchaseLottoMoney: PurchaseLottoMoney): Ticket {
        outputView.printMessage(INPUT_MESSAGE_MANUAL_LOTTO_COUNT)
        val purchaseInfo = getLottoPurchaseInfo(purchaseLottoMoney)
        val manuals = getManualTicket(purchaseInfo)
        val ticket = getManualAndAutoLotto(purchaseInfo, manuals)
        outputView.printTicket(purchaseInfo, ticket)
        return ticket
    }

    private fun getLottoPurchaseInfo(purchaseLottoMoney: PurchaseLottoMoney): LottoPurchaseInfo {
        return inputView.askManualCount(purchaseLottoMoney) ?: getLottoPurchaseInfo(purchaseLottoMoney)
    }

    private fun getManualTicket(purchaseInfo: LottoPurchaseInfo): Ticket {
        outputView.printMessage(INPUT_MESSAGE_MANUAL_LOTTO_NUMBERS)
        return inputView.askManualNumbers(purchaseInfo)
    }

    private fun getManualAndAutoLotto(purchaseInfo: LottoPurchaseInfo, manuals: Ticket): Ticket {
        return lottoSeller.sellManualAndAuto(purchaseInfo, manuals) ?: initializeTicket(purchaseInfo.purchaseLottoMoney)
    }

    private fun getWinningLotto(): WinningLotto {
        outputView.printMessage(INPUT_MESSAGE_FOR_WINNING_NUMBERS)
        val winningNumbers = getWinningNumbers()
        outputView.printMessage(INPUT_MESSAGE_FOR_BONUS_NUMBER)
        return getBonusNumber(winningNumbers)
    }

    private fun getWinningNumbers(): Lotto {
        return inputView.askWinningNumbers() ?: getWinningNumbers()
    }

    private fun getBonusNumber(winningNumbers: Lotto): WinningLotto {
        return inputView.askBonusNumber(winningNumbers) ?: getBonusNumber(winningNumbers)
    }

    private fun printResult(winningLotto: WinningLotto, ticket: Ticket, purchaseLottoMoney: PurchaseLottoMoney) {
        val lottoStatistics = LottoStatistics(winningLotto)
        val result = lottoStatistics.matchTicket(ticket)
        val profit = lottoStatistics.yield(result, purchaseLottoMoney)
        outputView.printResult(result, profit)
    }

    companion object {
        private const val INPUT_MESSAGE_FOR_PURCHASE_MONEY = "구입 금액을 입력해 주세요."
        private const val INPUT_MESSAGE_MANUAL_LOTTO_NUMBERS = "\n수동으로 구매할 번호를 입력해 주세요."
        private const val INPUT_MESSAGE_MANUAL_LOTTO_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요."
        private const val INPUT_MESSAGE_FOR_WINNING_NUMBERS = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_MESSAGE_FOR_BONUS_NUMBER = "\n보너스 볼을 입력해 주세요."
    }
}
