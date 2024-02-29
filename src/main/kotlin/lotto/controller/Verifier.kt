package lotto.model.user

import lotto.model.BonusNumber
import lotto.model.ExceptionManager
import lotto.model.GameException
import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoNumbers

private const val SEPARATOR = ","

object UserInputVerifier {
    fun inputCharge(userInput: String?): UserEvent.PurchaseEvent {
        return userInput?.toIntOrNull()
            ?.let { checkPurchasePrice(it) }
            ?: UserEvent.PurchaseEvent.InvalidDataType.also {
                println(ExceptionManager.getException(it).message)
            }
    }

    fun inputManualCount(
        userInput: String?,
        lottoCount: Int
    ): UserEvent.ManualEvent {
        return userInput
            ?.toIntOrNull()
            ?.let {
                checkManualLottoCount(it, lottoCount)
            }
            ?: UserEvent.ManualEvent.InvalidDataType.also {
                println(ExceptionManager.getException(it).message)
            }
    }

    fun inputLottoNumbers(userInput: String?): UserEvent.LottoEvent {
        return userInput?.split(SEPARATOR)
            ?.let { numbers ->
                val lottoNumbers = numbers.map {
                    val number = it.toIntOrNull() ?: return UserEvent.LottoEvent.InvalidDataType.also { event ->
                        println(ExceptionManager.getException(event).message)
                    }
                    LottoNumber(number)
                }
                checkLottoValid(lottoNumbers)
            }
            ?: UserEvent.LottoEvent.InvalidNumCount.also {
                println(ExceptionManager.getException(it).message)
            }
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
        }.getOrElse { exception ->
            println(exception.message)
            UserEvent.ManualEvent.InvalidManualCount
        }
    }

    private fun checkLottoValid(
        lottoNumbers: List<LottoNumber>
    ): UserEvent.LottoEvent {
        return runCatching {
            val lotto = Lotto(lottoNumbers = LottoNumbers(lottoNumbers))
            UserEvent.LottoEvent.Success(lotto = lotto)
        }.getOrElse { exception ->
            println(exception.message)
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
            val lottoNumber = LottoNumber(input)
            if (lottoNumber.getNumber() in winningLotto.getLottoNumber())
                throw ExceptionManager.getException(UserEvent.BonusEvent.InvalidBonusDuplication)
            val bonusNumber = BonusNumber(lottoNumber)
            UserEvent.BonusEvent.Success(bonusNumber)
        }.getOrElse { exception ->
            println(exception.message)
            when (exception) {
                is GameException -> exception.event as UserEvent.BonusEvent
                else -> UserEvent.BonusEvent.UnknownError
            }
        }
    }
}
