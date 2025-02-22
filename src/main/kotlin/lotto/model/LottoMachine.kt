package lotto.model

import lotto.Constants

class LottoMachine {
    fun purchase(count: Int): List<LottoTicket> = List(count) { LottoTicket(generateLotto()) }

    private fun generateLotto(): Set<LottoNumber> =
        (Constants.MINIMUM_NUMBER..Constants.MAXIMUM_NUMBER)
            .shuffled()
            .take(LOTTO_PICK_COUNT)
            .sorted()
            .map { LottoNumber(it) }
            .toSet()

    companion object {
        private const val LOTTO_PICK_COUNT = 6
    }
}
