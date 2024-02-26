package lotto.controller

import lotto.model.BonusNumber
import lotto.model.ExceptionManager
import lotto.model.GameException
import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.UserEvent

private const val SEPARATOR = ","

object Verifier {
    fun inputCharge(userInput: String?): UserEvent.PurchaseEvent {
        return userInput?.toIntOrNull()
            ?.let { checkPurchasePrice(it) }
            ?: UserEvent.PurchaseEvent.InvalidDataType
    }

    fun inputManualCount(
        userInput: String?,
        lottoCount: Int
    ): UserEvent.ManualEvent {
        return userInput
            ?.toIntOrNull()
            ?.let {
                checkManualLottoCount(it, lottoCount)
            } ?: UserEvent.ManualEvent.InvalidDataType
    }

    fun inputLottoNumbers(userInput: String?): UserEvent.LottoEvent {
        return userInput?.split(SEPARATOR)
            ?.let { numbers ->
                val lottoNumbers = numbers.map {
                    it.toIntOrNull() ?: return UserEvent.LottoEvent.InvalidDataType
                }.toSet()
                checkLottoValid(lottoNumbers)
            } ?: UserEvent.LottoEvent.InvalidNumCount
    }

    fun inputBonus(
        userInput: String?,
        winningLotto: Lotto
    ): UserEvent.BonusEvent {
        return userInput?.toIntOrNull()
            ?.let { checkBonusValid(it, winningLotto) }
            ?: UserEvent.BonusEvent.InvalidDataType
    }

    private fun checkPurchasePrice(input: Int): UserEvent.PurchaseEvent {
        return runCatching {
            if (input == 0 || input < Lotto.LOTTO_PRICE) throw ExceptionManager.getException(UserEvent.PurchaseEvent.InvalidPrice)
            UserEvent.PurchaseEvent.Success(input)
        }.getOrElse {
            println(it.message)
            UserEvent.PurchaseEvent.InvalidPrice
        }
    }

    private fun checkManualLottoCount(
        input: Int,
        lottoCount: Int
    ): UserEvent.ManualEvent {
        return runCatching {
            if (input !in 0..lottoCount) throw ExceptionManager.getException(UserEvent.ManualEvent.InvalidManualCount)
            UserEvent.ManualEvent.Success(input)
        }.getOrElse {
            UserEvent.ManualEvent.InvalidManualCount
        }
    }

    private fun checkLottoValid(
        lottoNumbers: Set<Int>
    ): UserEvent.LottoEvent {
        return runCatching {
            val lotto = Lotto(lottoNumber = LottoNumber(lottoNumbers))
            UserEvent.LottoEvent.Success(lotto = lotto)
        }.getOrElse { exception ->
            when (exception) {
                is GameException -> exception.event as UserEvent.LottoEvent
                else -> UserEvent.LottoEvent.UnknownError
            }
        }
    }

    private fun checkBonusValid(
        input: Int,
        winningLotto: Lotto
    ): UserEvent.BonusEvent {
        return runCatching {
            if (input in winningLotto.getLottoNumber())
                throw ExceptionManager.getException(UserEvent.BonusEvent.InvalidBonusDuplication)
            val bonusNumber = BonusNumber(input)
            UserEvent.BonusEvent.Success(bonusNumber)
        }.getOrElse { exception ->
            when(exception){
                is GameException -> exception.event as UserEvent.BonusEvent
                else -> UserEvent.BonusEvent.UnknownError
            }
        }
    }
}
