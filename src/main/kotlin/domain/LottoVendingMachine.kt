package domain

class LottoVendingMachine : LottoMachine {
    override fun createRandomLotto(): Lotto = Lotto(LottoNumber.all().shuffled().take(6).toSet())

    override fun createManualLotto(lottoNumbers: Set<Int>): Lotto {
        return Lotto(lottoNumbers.map { LottoNumber.from(it) }.toSet())
    }
}
