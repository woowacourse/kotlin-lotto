package domain.model

class WinningLotto(
    lotto: Lotto,
    bonusNumber: Int,
) {
    init {
        require(bonusNumber !in lotto.numbers) { DUPLICATED_BONUS_NUMBER }
    }

    companion object {
        const val ERROR = "[ERROR]"
        const val DUPLICATED_BONUS_NUMBER = "$ERROR 보너스 번호는 로또 번호는 중복될 수 없습니다."
    }
}
