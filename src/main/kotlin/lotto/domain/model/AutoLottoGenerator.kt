package lotto.domain.model

import lotto.Constants

class AutoLottoGenerator : LottoGenerator {
    override fun generateLotto(): LottoTicket =
        LottoTicket(
            LOTTO_RANGE
                .shuffled()
                .take(Constants.LOTTO_PICK_COUNT)
                .sorted()
                .map { LottoNumber(it) },
        )

    companion object {
        private val LOTTO_RANGE = (Constants.MINIMUM_NUMBER..Constants.MAXIMUM_NUMBER)
    }
}
