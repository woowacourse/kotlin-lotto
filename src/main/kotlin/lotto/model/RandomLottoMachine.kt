package lotto.model

object RandomLottoMachine : LottoMachine {
    private val randomLottoNumbers = (LottoNumber.MIN_RANGE..LottoNumber.MAX_RANGE).toList()

    override fun generate(lottoSize: Int): List<Lotto> {
        return List(lottoSize) { Lotto.create(randomLottoNumbers.shuffled().take(Lotto.SIZE)) }
    }
}
