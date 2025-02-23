package lotto.domain

import lotto.generator.LottoRandomGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `로또 번호는 중복되지 않는 6개의 숫자를 갖는다`() {
        val randomGenerator = LottoRandomGenerator()
        val lotto = Lotto(randomGenerator.getRandomNumberList())
        assertThat(lotto.numbers.size).isEqualTo(6)
    }

    @Test
    fun `로또 번호는 오름차순으로 정렬한다`() {
        val randomGenerator = LottoRandomGenerator()
        val lotto = Lotto(randomGenerator.getRandomNumberList())
        assertThat(lotto.numbers).isSorted
    }
}
