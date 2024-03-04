package lotto.model

import lotto.util.Constant

class HandpickedLotto(private val handpickedLottoNumber: List<LottoNumber>) : LottoGenerator {
    init {
        require(handpickedLottoNumber.toSet().size == Constant.LOTTO_SIZE)
        require(
            handpickedLottoNumber.all { number ->
                number.getNumber() in Constant.MIN_LOTTO_NUMBER_RANGE..Constant.MAX_LOTTO_NUMBER_RANGE
            },
        )
    }

    override fun generateLotto(): Lotto {
        return Lotto(handpickedLottoNumber.map { it })
    }
}
