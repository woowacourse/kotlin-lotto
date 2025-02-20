package lotto.domain.model

class BonusNumber(winningNumbers: WinningNumbers, val number: LottoNumber) {
    init {
        require(winningNumbers.numbers.contains(number).not()) { DUPLICATE_BONUS_NUMBER_MESSAGE }
    }

    private companion object {
        const val DUPLICATE_BONUS_NUMBER_MESSAGE = "보너스 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
