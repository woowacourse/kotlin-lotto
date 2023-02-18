package domain

class RandomLottoMachine : LottoMachine {
    override fun create(start: Int, end: Int): Lotto =
        Lotto((start..end).toList().shuffled().take(6).map { number -> LottoNumber(number) }.toSet())
}
