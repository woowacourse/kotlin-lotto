package lotto.domain

class RandomLottoGenerator(
    private val count: Int,
) : LottoGenerator {
    override fun generate(): List<Lotto> =
        List(count) {
            Lotto(
                (LottoNumber.MIN..LottoNumber.MAX)
                    .shuffled()
                    .subList(0, Lotto.Companion.NUMBERS_SIZE),
            )
        }
}
