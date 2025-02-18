package lotto.domain.model

import lotto.LOTTO_NUMBER_SIZE

class Lotto(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_NUMBER_SIZE) { INVALID_LOTTO_NUMBER_SIZE_MESSAGE }
    }

    private companion object {
        const val INVALID_LOTTO_NUMBER_SIZE_MESSAGE = "로또 번호는 6개여야 합니다."
    }
}
