package lotto.model

import lotto.util.LottoConstants

class Lotto(val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == LottoConstants.SIZE) {
            throw IllegalArgumentException(INVALID_LOTTO_SIZE)
        }
    }

    companion object {
        private const val LOTTO_SIZE = 6
        private const val INVALID_LOTTO_SIZE =
            ("로또 번호의 개수가 ${LOTTO_SIZE}가 아니면 예외처리한다.")
    }
}
