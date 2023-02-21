package domain

class RandomLottoMachine : LottoMachine {
    override fun create(): Lotto =
        Lotto(LottoNumber.all().shuffled().take(6).toSet())
}
