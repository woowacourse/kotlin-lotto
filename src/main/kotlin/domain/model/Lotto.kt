package domain.model

import ERROR

class Lotto(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_SIZE) { INVALID_LOTTO_SIZE }
        require(numbers.size == numbers.toSet().size) { DUPLICATED_LOTTO_NUMBERS }
    }

    companion object {
        const val LOTTO_MIN = 1
        const val LOTTO_MAX = 45
        const val LOTTO_SIZE = 6
        const val INVALID_LOTTO_SIZE = "$ERROR 로또 번호는 ${LOTTO_SIZE}개 입니다."
        const val DUPLICATED_LOTTO_NUMBERS = "$ERROR 로또 번호는 중복이 없습니다."

        fun from(vararg numbers: Int): Lotto {
            return Lotto(numbers.map { LottoNumber(it) })
        }
    }
}
