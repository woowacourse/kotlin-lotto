package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberGeneratorTest {
    @ValueSource(ints = [1, 3, 5])
    @ParameterizedTest
    fun `생성된 숫자는 1에서 45 사이어야 한다`(value: Int) {
        assertThat(SequentialLottoNumberGenerator(listOf(value)).generate()).isBetween(1, 45)
    }
}
