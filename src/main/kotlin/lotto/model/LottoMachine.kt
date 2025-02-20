package lotto.model

class LottoMachine {
    fun createLottos(lottoCount: Int): Lottos {
        val lottoBundle = mutableListOf<Lotto>()
        repeat(lottoCount) {
            lottoBundle.add(createLotto())
        }
        return Lottos(lottoBundle.toList())
    }

    private fun createLotto(): Lotto {
        val lottoNumber =
            (LottoNumber.MIN_NUMBER..LottoNumber.MAX_NUMBER)
                .shuffled()
                .take(Lotto.LOTTO_NUMBERS_COUNT)
                .map { number -> LottoNumber(number) }
                .sortedBy { lottoNumber -> lottoNumber.number }
        return Lotto(lottoNumber)
    }
}
