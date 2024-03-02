package lotto.model.wallet

import lotto.model.user.UserException
import lotto.model.user.UserException.WalletException

sealed interface WalletEvent {
    data class Success(val wallet: Wallet) : WalletEvent
    data object InvalidDataType : WalletEvent
    data object InvalidPrice : WalletEvent
    data object UnknownError : WalletEvent

    companion object {
        fun checkWallet(event: WalletEvent): WalletException {
            return when (event) {
                is Success -> WalletException(UserException.SUCCESS, event)
                is InvalidDataType -> WalletException(UserException.INVALID_DATA_TYPE, event)
                is InvalidPrice -> WalletException(INVALID_PRICE, event)
                is UnknownError -> WalletException(UserException.UNKNOWN_ERROR, event)
            }
        }

        const val INVALID_PRICE = "구입 금액이 올바르지 않습니다."
    }
}
