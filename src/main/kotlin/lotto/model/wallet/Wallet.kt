package lotto.model.wallet

import lotto.model.Lotto
import lotto.model.user.UserException

class Wallet(private val price: Int) {

    fun getPrice(): Int {
        return price
    }

    fun getLottoCount(): Int {
        return price / Lotto.LOTTO_PRICE.toInt()
    }

    companion object {
        fun checkPurchasePrice(price: Int): WalletEvent {
            return runCatching {
                if (price == 0 || price < Lotto.LOTTO_PRICE) throw WalletEvent.checkWallet(WalletEvent.InvalidPrice)
                WalletEvent.Success(Wallet(price))
            }.getOrElse { exception ->
                when (exception) {
                    is UserException.WalletException -> exception.walletEvent
                    else -> WalletEvent.UnknownError
                }
            }
        }
    }
}
