package lotto.model

import lotto.util.Constant

class LottoGenerator {
    fun generateLotto(): Lotto {
        return Lotto(generateRandomLottoNumbers())
    }

    companion object {
        private fun generateRandomLottoNumbers(): Set<LottoNumber> {
            return Constant
                .LOTTO_NUM_RANGE
                .shuffled()
                .take(Constant.LOTTO_LEN)
                .map { LottoNumber(it) }
                .toSet()
        }
    }
}
