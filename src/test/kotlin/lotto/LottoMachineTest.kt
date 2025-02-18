package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoMachineTest {
    @ParameterizedTest
    @ValueSource(ints = [10, 8])
    fun `로또 구입 개수만큼 로또를 발행한다`(count: Int) {
        val lottos = mutableListOf<List<Int>>()
        repeat(count) {
            val lotto = LottoNumbers()
            lottos.add(lotto.numbers)
        }
        assertThat(lottos.size).isEqualTo(count)
    }
}
