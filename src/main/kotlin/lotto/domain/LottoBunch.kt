package lotto.domain

import lotto.domain.factory.LottoFactory

class LottoBunch(private val lottoFactory: LottoFactory, purchaseCount: Int) {

    val value = List(purchaseCount) { lottoFactory.createLotto() }
}
