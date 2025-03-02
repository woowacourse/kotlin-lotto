package domain.model.winning

import util.ErrorConstants

sealed class WinningLottoException(override val message: String?) : Throwable(message) {
    class DuplicatedBonusNumberException : WinningLottoException(
        DUPLICATED_BONUS_NUMBER,
    )

    companion object {
        const val DUPLICATED_BONUS_NUMBER = "${ErrorConstants.ERROR} 보너스 번호와 로또 번호는 중복될 수 없습니다."
    }
}
