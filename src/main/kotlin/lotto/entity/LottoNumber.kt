package lotto.entity

data class LottoNumber(val value: Int) {
    init {
        require(value in (Lotto.MINIMUM_LOTTO_NUMBER..Lotto.MAXIMUM_LOTTO_NUMBER)) { String.format(ERROR_MESSAGE_RANGE_1_TO_45, value) }
    }

    companion object {
        const val ERROR_MESSAGE_RANGE_1_TO_45 = "당첨 번호는 1에서 45 사이의 숫자여야 합니다. 입력된 당첨 번호는 %d 입니다."
    }
}
