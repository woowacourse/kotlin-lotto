package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoFactoryTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 100])
    fun `0개 이상 100개 이하의 개수만큼 로또를 생성할 수 있다`(count: Int) {
        val result = LottoFactory.create(Count(count))

        assertThat(result.size).isEqualTo(count)
    }
}
