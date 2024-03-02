package lotto.model.user

import lotto.model.Lotto
import lotto.model.LottoEvent
import lotto.model.LottoNumber
import lotto.model.manual.ManualEvent
import lotto.model.manual.ManualNumber
import lotto.model.wallet.Wallet
import lotto.model.wallet.WalletEvent
import lotto.model.winning.BonusEvent
import lotto.model.winning.BonusNumber

object UserInputVerifier {
    private const val SEPARATOR = ","

    fun inputCharge(userInput: String?): WalletEvent {
        return userInput?.toIntOrNull()
            ?.let { Wallet.checkPurchasePrice(it) }
            ?: WalletEvent.InvalidDataType
    }

    fun inputLottoNumbers(userInput: String?): LottoEvent {
        return userInput?.split(SEPARATOR)?.let { numbers ->
            Lotto.checkLottoValid(numbers)
        } ?: LottoEvent.InvalidNumCount
    }

    fun inputManualCount(
        userInput: String?,
        lottoCount: Int
    ): ManualEvent {
        return userInput
            ?.toIntOrNull()
            ?.let {
                ManualNumber.checkManualLottoCount(it, lottoCount)
            } ?: ManualEvent.InvalidDataType
    }

    fun inputBonus(
        userInput: String?,
        winningLotto: Lotto
    ): BonusEvent {
        return userInput?.toIntOrNull()
            ?.let { BonusNumber.checkBonusValid(it, winningLotto) }
            ?: BonusEvent.InvalidDataType
    }
}
