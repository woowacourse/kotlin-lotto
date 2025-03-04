package lotto.domain.generator

import lotto.Constants
import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket

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
