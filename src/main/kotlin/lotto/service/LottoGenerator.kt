
package lotto.service

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.MAX_LOTTO_LENGTH
import lotto.domain.MAX_LOTTO_NUMBER
import lotto.domain.MIN_LOTTO_NUMBER
import java.util.LinkedList

object LottoGenerator {
    fun genLotto(): Lotto {
        val lotto = mutableListOf<LottoNumber>()
        val range: LinkedList<Int> = LinkedList(IntRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER).shuffled())
        repeat(MAX_LOTTO_LENGTH) { lotto.add(LottoNumber.of(range.poll())) }

        return Lotto(lotto.toList())
    }

    fun genManyLotto(iterates: Int): List<Lotto> {
        val manyLotto = mutableListOf<Lotto>()
        repeat(iterates) { manyLotto.add(genLotto()) }
        return manyLotto.toList()
    }
}
