package domain

import common.MAXIMUM_LOTTO_RANGE
import common.MINIMUM_LOTTO_RANGE

class Lotto(private val _numbers: Set<Int>) {
    val numbers: Set<Int>
        get() = _numbers

    init {
        require(_numbers.size == LOTTO_SIZE) { ERROR_LOTTO_SIZE }
        require(_numbers.all { number -> number in MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE }) { ERROR_LOTTO_RANGE }
    }

    companion object {
        private const val LOTTO_SIZE = 6
        private const val ERROR_LOTTO_SIZE = "로또 번호는 6개여야 합니다."
        private const val ERROR_LOTTO_RANGE = "로또 번호는 1이상 45이하여야 합니다."
    }
}
