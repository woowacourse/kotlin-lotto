package lotto.domain

import lotto.domain.factory.LottoFactory

class LottoBunch(private val lottoFactory: LottoFactory, purchaseCount: Int) {

    val value = List(purchaseCount) { lottoFactory.createLotto() }
    override fun toString(): String =
        value.joinToString(separator = LOTTO_BUNCH_TO_STRING_SEPARATOR) { lotto -> lotto.toString() }

    companion object {
        private const val LOTTO_BUNCH_TO_STRING_SEPARATOR = "\n"
    }
}
