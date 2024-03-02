package lotto.model.winning

import lotto.model.Lotto
import lotto.model.LottoEvent
import lotto.model.LottoNumber
import lotto.model.user.UserException

class BonusNumber(
    private val bonusNumber: LottoNumber
) {

    fun getBonusNumber(): Int {
        return bonusNumber.getNumber()
    }

    companion object {
        fun checkBonusValid(
            input: Int,
            winningLotto: Lotto
        ): BonusEvent {
            return runCatching {
                val lottoNumber = LottoNumber(input)
                if (lottoNumber.getNumber() in winningLotto.getLottoNumber())
                    throw BonusEvent.checkBonusEvent(BonusEvent.InvalidBonusDuplication)
                val bonusNumber = BonusNumber(lottoNumber)
                BonusEvent.Success(bonusNumber)
            }.getOrElse { exception ->
                when (exception) {
                    is UserException.BonusException -> exception.bonusEvent
                    is UserException.LottoException -> {
                        when (exception.lottoEvent) {
                            is LottoEvent.InvalidNumRange -> BonusEvent.InvalidNumRange
                            else -> BonusEvent.UnknownError
                        }
                    }

                    else -> BonusEvent.UnknownError
                }
            }
        }
    }
}
