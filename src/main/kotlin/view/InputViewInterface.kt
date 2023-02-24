package view

import domain.Lotto
import domain.LottoPurchaseInfo
import domain.PurchaseLottoMoney
import domain.Ticket
import domain.WinningLotto

interface InputViewInterface {
    fun askMoney(): PurchaseLottoMoney?
    fun askManualCount(purchaseLottoMoney: PurchaseLottoMoney): LottoPurchaseInfo?
    fun askManualNumbers(purchaseInfo: LottoPurchaseInfo): Ticket
    fun askWinningNumbers(): Lotto?
    fun askBonusNumber(winningNumbers: Lotto): WinningLotto?
}
