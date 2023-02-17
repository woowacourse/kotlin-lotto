package view

import domain.Lotto
import domain.LottoNumber
import domain.PurchaseLottoMoney

interface InputViewInterface {
    fun getMoney(): PurchaseLottoMoney
    fun getWinningNumbers(): Lotto
    fun getBonusNumber(): LottoNumber
}
