package lotto.model

import lotto.util.Constant

class RandomLottoNumberGenerator : LottoNumberGenerator {
    override fun generateNumbers(): Lotto {
        return Lotto(
            Constant
                .LOTTO_NUM_RANGE
                .shuffled()
                .take(Constant.LOTTO_LEN)
                .map { LottoNumber.of(it) }
                .toSet(),
        )
    }
}
