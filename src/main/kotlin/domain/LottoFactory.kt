package domain

object LottoFactory {

    fun create(count: Count): List<Lotto> = List(count.value) { createLotto() }

    private fun createLotto() = Lotto.create(RandomNumberGenerator.generate())
}
