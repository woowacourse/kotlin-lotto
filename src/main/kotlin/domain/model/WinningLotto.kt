package domain.model

class WinningLotto(
    val lotto: Lotto,
    val bonusNumber: BonusNumber,
) {
    init {
        require(bonusNumber.value !in lotto.numbers.map { it.number }) { DUPLICATED_BONUS_NUMBER }
    }

    companion object {
        const val ERROR = "[ERROR]"
        const val DUPLICATED_BONUS_NUMBER = "$ERROR 보너스 번호는 로또 번호는 중복될 수 없습니다."
    }
}
