package view

import domain.Lotto
import domain.LottoNumber
import domain.LottoPurchaseInfo
import domain.PurchaseLottoMoney

interface InputViewInterface {
    fun getMoney(): PurchaseLottoMoney
    fun getManualCount(purchaseLottoMoney: PurchaseLottoMoney): LottoPurchaseInfo
    fun getManualNumbers(purchaseInfo: LottoPurchaseInfo): List<Lotto>
    fun getWinningNumbers(): Lotto
    fun getBonusNumber(): LottoNumber
}
