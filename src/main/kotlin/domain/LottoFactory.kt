package domain

object LottoFactory {

    fun create(count: Count): List<Lotto> = List(count.toInt()) { createLotto() }

    private fun createLotto() = Lotto.create(
        RandomNumberGenerator.generate(LottoNumber.MINIMUM_NUMBER..LottoNumber.MAXIMUM_NUMBER, Lotto.NUMBER_SIZE),
    )
}
