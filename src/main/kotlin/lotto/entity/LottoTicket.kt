package lotto.entity

class LottoTicket(val numbers: List<Int>) {
    init {
        require(numbers.size == Lotto.LOTTO_COUNT) { String.format(ERROR_MESSAGE_LOTTO_NUMBER_IS_SIX, numbers) }
        require(numbers.distinct().size == Lotto.LOTTO_COUNT) { String.format(ERROR_MESSAGE_LOTTO_NUMBER_IS_NOT_DUPLICATE, numbers) }
        require(numbers.all { it in (LottoNumber.MINIMUM_LOTTO_NUMBER..LottoNumber.MAXIMUM_LOTTO_NUMBER) }) { LottoNumber.ERROR_MESSAGE_LOTTO_RANGE_1_TO_45 }
    }

    companion object {
        private const val ERROR_MESSAGE_LOTTO_NUMBER_IS_NOT_DUPLICATE = "로또 번호는 중복되지 않아야 합니다. 입력된 정보 : %s"
        private const val ERROR_MESSAGE_LOTTO_NUMBER_IS_SIX = "로또 번호는 6개의 숫자여야 합니다. 입력된 정보 : %s"
    }
}
