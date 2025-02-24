package lotto.domain.model

import lotto.domain.LottoRules

data class LottoNumber(val number: Int) {
    init {
        require(number in LottoRules.INVALID_LOTTO_MIN_NUMBER.value..LottoRules.INVALID_LOTTO_MAX_NUMBER.value) {
            INVALID_LOTTO_NUMBER_RANGE_MESSAGE.format(number)
        }
    }

    override fun toString(): String {
        return this.number.toString()
    }

    private companion object {
        const val INVALID_LOTTO_NUMBER_RANGE_MESSAGE = "로또의 번호가 %d 입니다. 로또의 각 번호는 1~45이하의 숫자만 가집니다."
    }
}
