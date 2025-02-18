package lotto.domain.model

class WinningNumbers(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_NUMBER_SIZE) { INVALID_WINNING_NUMBER_SIZE_MESSAGE }
    }

    private companion object {
        const val LOTTO_NUMBER_SIZE = 6
        const val INVALID_WINNING_NUMBER_SIZE_MESSAGE = "당첨 번호는 6개여야 합니다."
    }
}
