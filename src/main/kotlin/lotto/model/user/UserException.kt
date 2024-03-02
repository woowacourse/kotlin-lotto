package lotto.model.user

import lotto.model.LottoEvent
import lotto.model.manual.ManualEvent
import lotto.model.wallet.WalletEvent
import lotto.model.winning.BonusEvent

sealed class UserException(
    message: String
) : Exception(message){

    class WalletException(
        message: String,
        val walletEvent: WalletEvent
    ) : UserException(message)

    class LottoException(
        message: String,
        val lottoEvent: LottoEvent
    ): UserException(message)

    class BonusException(
        message: String,
        val bonusEvent: BonusEvent
    ): UserException(message)

    class ManualException(
        message: String,
        val manualEvent: ManualEvent
    ): UserException(message)

    companion object {
        const val SUCCESS = ""
        const val UNKNOWN_ERROR = "알 수 없는 에러가 발생하였습니다."
        const val INVALID_DATA_TYPE = "숫자 형식을 입력해야 합니다."
    }
}
